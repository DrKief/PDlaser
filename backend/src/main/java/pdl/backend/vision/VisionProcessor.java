package pdl.backend.vision;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.pgvector.PGvector;

import pdl.backend.gallery.search.TagRepository;

@Service
public class VisionProcessor {

  private static final Logger log = LoggerFactory.getLogger(VisionProcessor.class);
  private final JdbcTemplate jdbcTemplate;
  private final TagRepository queryRepoLayer;
  private final UploadStatusTracker statusNotifier;

  public VisionProcessor(
    JdbcTemplate jdbcTemplate,
    TagRepository queryRepoLayer,
    UploadStatusTracker statusNotifier
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
      resizedImage = FeatureExtractor.resizeImageLanczos3(bimg, 256, 256);
      float[] hogData = FeatureExtractor.extractGlobalHog(resizedImage);
      float[] hsvData = FeatureExtractor.extractHsvHistogram(resizedImage);
      float[] rgbData = FeatureExtractor.extractRgbHistogram(resizedImage);
      float[] labData = FeatureExtractor.extractCieLabHistogram(resizedImage);

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

  public Map<String, float[]> extractEphemeralVectors(byte[] data) throws Exception {
      BufferedImage bimg = ImageIO.read(new ByteArrayInputStream(data));
      if (bimg == null) {
          throw new IllegalArgumentException("Failed to decode image data.");
      }

      BufferedImage resizedImage = FeatureExtractor.resizeImageLanczos3(bimg, 256, 256);
      
      // Extract base histograms
      float[] hogData = FeatureExtractor.extractGlobalHog(resizedImage);
      float[] hsvData = FeatureExtractor.extractHsvHistogram(resizedImage);
      float[] rgbData = FeatureExtractor.extractRgbHistogram(resizedImage);
      float[] labData = FeatureExtractor.extractCieLabHistogram(resizedImage);
      
      // Extract semantics
      float[] rawSemanticData = SemanticExtractor.extractSemanticFeatures(bimg);
      float[] semanticVector = SemanticExtractor.normalizeL2(rawSemanticData.clone());

      // Fix HOG length mismatch
      if (hogData.length != 31) {
          float[] adjustedHog = new float[31];
          System.arraycopy(hogData, 0, adjustedHog, 0, Math.min(hogData.length, 31));
          hogData = adjustedHog;
      }

      // Cleanup resources
      bimg.flush();
      resizedImage.flush();

      return Map.of(
          "gradient", hogData,
          "saturation", hsvData,
          "rgb", rgbData,
          "cielab", labData,
          "semantic", semanticVector
      );
  }
}