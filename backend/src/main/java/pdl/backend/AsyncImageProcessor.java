package pdl.backend;

import com.pgvector.PGvector;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import javax.imageio.ImageIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Handles heavy processing of images (resizing, descriptor extraction) in background threads.
 * Utilizes BoofCV for image analysis and PGvector for saving results to PostgreSQL.
 */
@Service
public class AsyncImageProcessor {

  private static final Logger log = LoggerFactory.getLogger(AsyncImageProcessor.class);
  private final JdbcTemplate jdbcTemplate;
  private final ImageDao imageDao;
  private final ImageStatusNotifier statusNotifier;

  public AsyncImageProcessor(
    JdbcTemplate jdbcTemplate,
    ImageDao imageDao,
    ImageStatusNotifier statusNotifier
  ) {
    this.jdbcTemplate = jdbcTemplate;
    this.imageDao = imageDao;
    this.statusNotifier = statusNotifier;
  }

  /**
   * Executes asynchronously to compute and save vector descriptors for a given image.
   *
   * @param id   The database ID of the image.
   * @param data The raw byte data of the image.
   */
  @Async("taskExecutor")
  public void processImageDescriptors(Long id, byte[] data) {
    BufferedImage bimg = null;
    BufferedImage resizedImage = null;

    try {
      // 1. Notify the system that processing has begun
      imageDao.updateStatus(id, "PROCESSING");
      statusNotifier.notify(id, "PROCESSING");

      // 2. Decode the image
      bimg = ImageIO.read(new ByteArrayInputStream(data));
      if (bimg == null) {
        log.warn("Could not decode image for ID: {}", id);
        imageDao.updateStatus(id, "FAILED");
        statusNotifier.notify(id, "FAILED");
        return;
      }

      // 3. Resize for performance and normalization before extraction
      resizedImage = ImageProcessing.resizeImageLanczos3(bimg, 256, 256);

      // 4. Extract Vector Descriptors
      float[] hogData = ImageProcessing.extractGlobalHog(resizedImage);
      float[] hsvData = ImageProcessing.extractHsvHistogram(resizedImage);
      float[] rgbData = ImageProcessing.extractRgbHistogram(resizedImage);

      // Ensure HoG vector size perfectly matches DB schema constraints (31 elements)
      if (hogData.length != 31) {
        float[] adjustedHog = new float[31];
        System.arraycopy(hogData, 0, adjustedHog, 0, Math.min(hogData.length, 31));
        hogData = adjustedHog;
      }

      // 5. Persist descriptors to PostgreSQL utilizing PGvector extension
      jdbcTemplate.update(
        "INSERT INTO imagedescriptors (imageid, hogvector, hsvvector, rgbvector) VALUES (?, ?, ?, ?) " +
          "ON CONFLICT (imageid) DO UPDATE SET hogvector = EXCLUDED.hogvector, hsvvector = EXCLUDED.hsvvector, rgbvector = EXCLUDED.rgbvector",
        id,
        new PGvector(hogData),
        new PGvector(hsvData),
        new PGvector(rgbData)
      );

      // 6. Complete and notify frontend clients waiting via long-polling
      imageDao.updateStatus(id, "COMPLETED");
      statusNotifier.notify(id, "COMPLETED");
      log.info("Successfully processed descriptors asynchronously for image ID: {}", id);
      
    } catch (Exception e) {
      log.error("Failed to process image descriptors asynchronously for ID: " + id, e);
      imageDao.updateStatus(id, "FAILED");
      statusNotifier.notify(id, "FAILED");
    } finally {
      // Free up memory resources
      if (bimg != null) bimg.flush();
      if (resizedImage != null) resizedImage.flush();
    }
  }
}