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

  @Async("taskExecutor")
  public void processImageDescriptors(Long id, byte[] data) {
    BufferedImage bimg = null;
    BufferedImage resizedImage = null;

    try {
      imageDao.updateStatus(id, "PROCESSING");
      statusNotifier.notify(id, "PROCESSING");

      bimg = ImageIO.read(new ByteArrayInputStream(data));
      if (bimg == null) {
        log.warn("Could not decode image for ID: {}", id);
        imageDao.updateStatus(id, "FAILED");
        statusNotifier.notify(id, "FAILED");
        return;
      }

      resizedImage = ImageProcessing.resizeImageLanczos3(bimg, 256, 256);

      float[] hogData = ImageProcessing.extractGlobalHog(resizedImage);
      float[] hsvData = ImageProcessing.extractHsvHistogram(resizedImage);
      float[] rgbData = ImageProcessing.extractRgbHistogram(resizedImage);

      if (hogData.length != 31) {
        float[] adjustedHog = new float[31];
        System.arraycopy(hogData, 0, adjustedHog, 0, Math.min(hogData.length, 31));
        hogData = adjustedHog;
      }

      jdbcTemplate.update(
        "INSERT INTO imagedescriptors (imageid, hogvector, hsvvector, rgbvector) VALUES (?, ?, ?, ?) " +
          "ON CONFLICT (imageid) DO UPDATE SET hogvector = EXCLUDED.hogvector, hsvvector = EXCLUDED.hsvvector, rgbvector = EXCLUDED.rgbvector",
        id,
        new PGvector(hogData),
        new PGvector(hsvData),
        new PGvector(rgbData)
      );

      imageDao.updateStatus(id, "COMPLETED");
      statusNotifier.notify(id, "COMPLETED");
      log.info("Successfully processed descriptors asynchronously for image ID: {}", id);
    } catch (Exception e) {
      log.error("Failed to process image descriptors asynchronously for ID: " + id, e);
      imageDao.updateStatus(id, "FAILED");
      statusNotifier.notify(id, "FAILED");
    } finally {
      if (bimg != null) {
        bimg.flush();
      }
      if (resizedImage != null) {
        resizedImage.flush();
      }
    }
  }
}
