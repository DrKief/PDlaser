package pdl.backend.gallery.processing;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.List;
import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.pgvector.PGvector;

import pdl.backend.gallery.tags.ImageQueryRepoLayer;
import pdl.backend.vision.ImageFeatureExtractorLayer;
import pdl.backend.vision.SemanticExtractor;

@Service
public class ImageProcessingLayer {

  private static final Logger log = LoggerFactory.getLogger(ImageProcessingLayer.class);
  private final JdbcTemplate jdbcTemplate;
  private final ImageQueryRepoLayer queryRepoLayer;
  private final UploadStatusTrackerLayer statusNotifier;

  public ImageProcessingLayer(
    JdbcTemplate jdbcTemplate,
    ImageQueryRepoLayer queryRepoLayer,
    UploadStatusTrackerLayer statusNotifier
  ) {
    this.jdbcTemplate = jdbcTemplate;
    this.queryRepoLayer = queryRepoLayer;
    this.statusNotifier = statusNotifier;
  }

  @Async("taskExecutor")
  public void processImageDescriptors(Long id, byte[] data) {
    BufferedImage bimg = null;
    BufferedImage resizedImage = null;

    try {
      queryRepoLayer.updateStatus(id, "PROCESSING");
      statusNotifier.notify(id, "PROCESSING");

      bimg = ImageIO.read(new ByteArrayInputStream(data));
      if (bimg == null) {
        queryRepoLayer.updateStatus(id, "FAILED");
        statusNotifier.notify(id, "FAILED");
        return;
      }

      // 1. Extract Classic Descriptors
      resizedImage = ImageFeatureExtractorLayer.resizeImageLanczos3(bimg, 256, 256);
      float[] hogData = ImageFeatureExtractorLayer.extractGlobalHog(resizedImage);
      float[] hsvData = ImageFeatureExtractorLayer.extractHsvHistogram(resizedImage);
      float[] rgbData = ImageFeatureExtractorLayer.extractRgbHistogram(resizedImage);
      float[] labData = ImageFeatureExtractorLayer.extractCieLabHistogram(resizedImage);

      // 2. Extract Semantic AI Data & Auto-Tags
      float[] rawSemanticData = SemanticExtractor.extractSemanticFeatures(bimg);
      
      // Auto-Tagging logic
      List<String> aiTags = SemanticExtractor.getAutoTags(rawSemanticData);
      for (String tag : aiTags) {
        queryRepoLayer.addKeyword(id, "ai:" + tag);
      }

      // 3. Normalize vector for the similarity search database
      float[] semanticVector = SemanticExtractor.normalizeL2(rawSemanticData.clone());

      if (hogData.length != 31) {
        float[] adjustedHog = new float[31];
        System.arraycopy(hogData, 0, adjustedHog, 0, Math.min(hogData.length, 31));
        hogData = adjustedHog;
      }

      // 4. Save everything to Postgres
      jdbcTemplate.update(
        "INSERT INTO imagedescriptors (imageid, hogvector, hsvvector, rgbvector, labvector, semanticvector) VALUES (?, ?, ?, ?, ?, ?) " +
          "ON CONFLICT (imageid) DO UPDATE SET " +
          "hogvector = EXCLUDED.hogvector, " +
          "hsvvector = EXCLUDED.hsvvector, " +
          "rgbvector = EXCLUDED.rgbvector, " +
          "labvector = EXCLUDED.labvector, " +
          "semanticvector = EXCLUDED.semanticvector",
        id,
        new PGvector(hogData),
        new PGvector(hsvData),
        new PGvector(rgbData),
        new PGvector(labData),
        new PGvector(semanticVector)
      );

      queryRepoLayer.updateStatus(id, "COMPLETED");
      statusNotifier.notify(id, "COMPLETED");
    } catch (Exception e) {
      log.error("Failed to process image descriptors for ID: " + id, e);
      queryRepoLayer.updateStatus(id, "FAILED");
      statusNotifier.notify(id, "FAILED");
    } finally {
      if (bimg != null) bimg.flush();
      if (resizedImage != null) resizedImage.flush();
    }
  }
}