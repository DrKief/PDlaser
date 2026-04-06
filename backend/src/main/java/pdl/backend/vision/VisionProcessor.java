package pdl.backend.vision;

import com.pgvector.PGvector;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.imageio.ImageIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pdl.backend.gallery.core.FileStorageService;
import pdl.backend.gallery.core.MediaRecord;
import pdl.backend.gallery.search.TagRepository;

@Service
public class VisionProcessor {

  private static final Logger log = LoggerFactory.getLogger(VisionProcessor.class);
  private final JdbcTemplate jdbcTemplate;
  private final TagRepository queryRepoLayer;
  private final UploadStatusTracker statusNotifier;
  private final FileStorageService fileStorageService;
  
  private volatile boolean isRunning = true;
  private final List<Thread> workers = new ArrayList<>();

  public VisionProcessor(
    JdbcTemplate jdbcTemplate,
    TagRepository queryRepoLayer,
    UploadStatusTracker statusNotifier,
    FileStorageService fileStorageService
  ) {
    this.jdbcTemplate = jdbcTemplate;
    this.queryRepoLayer = queryRepoLayer;
    this.statusNotifier = statusNotifier;
    this.fileStorageService = fileStorageService;
  }

  @PostConstruct
  public void startWorkers() {
    log.info("Starting 2 dedicated VisionProcessor ML worker threads");
    for (int i = 0; i < 2; i++) {
      Thread worker = new Thread(this::processQueue, "MLWorker-" + i);
      worker.setDaemon(false);
      worker.start();
      workers.add(worker);
    }
  }

  @PreDestroy
  public void shutdown() {
    log.info("Gracefully shutting down VisionProcessor workers (up to 30s timeout)...");
    isRunning = false;
    for (Thread worker : workers) {
      try {
        worker.join(30000);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
  }

  @EventListener(ApplicationReadyEvent.class)
  public void resetProcessingOnStartup() {
    log.info("Resetting any stuck PROCESSING images to PENDING");
    jdbcTemplate.update("UPDATE images SET extraction_status = 'PENDING' WHERE extraction_status = 'PROCESSING'");
  }

  private void processQueue() {
    while (isRunning) {
      try {
        // Atomic lock using SKIP LOCKED
        List<Long> ids = jdbcTemplate.query(
          "UPDATE images SET extraction_status = 'PROCESSING' WHERE id = (" +
          "  SELECT id FROM images WHERE extraction_status = 'PENDING' ORDER BY id ASC LIMIT 1 FOR UPDATE SKIP LOCKED" +
          ") RETURNING id",
          (rs, rowNum) -> rs.getLong(1)
        );

        if (ids.isEmpty()) {
          Thread.sleep(2000);
        } else {
          Long id = ids.get(0);
          try {
            Optional<MediaRecord> recordOpt = fileStorageService.getImageWithData(id);
            if (recordOpt.isEmpty() || recordOpt.get().getData() == null) {
              log.error("Failed to load file data for ID: {}", id);
              queryRepoLayer.updateStatus(id, "FAILED");
              statusNotifier.notify(id, "FAILED");
              continue; // proceed to next item
            }
            processImageDescriptors(id, recordOpt.get().getData());
          } catch (Throwable t) { // MASSIVE CATCH to prevent eternal queue stall
            log.error("SEVERE CRASH processing image ID: {}", id, t);
            queryRepoLayer.updateStatus(id, "FAILED");
            statusNotifier.notify(id, "FAILED");
          }
        }
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        break; // break the loop on interrupt
      } catch (Exception e) {
        log.error("Error in VisionProcessor queue loop", e);
        try {
          Thread.sleep(5000);
        } catch (InterruptedException ie) {
          Thread.currentThread().interrupt();
          break;
        }
      }
    }
  }

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
      float[] semanticVector = SemanticExtractor.extractSemanticFeatures(bimg);

      // Auto-Tagging logic
      List<String> aiTags = SemanticExtractor.getAutoTags(semanticVector);
      for (String tag : aiTags) {
        queryRepoLayer.addKeyword(id, tag, true);
      }

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
    float[] semanticVector = SemanticExtractor.extractSemanticFeatures(bimg);

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
      "gradient",
      hogData,
      "saturation",
      hsvData,
      "rgb",
      rgbData,
      "cielab",
      labData,
      "semantic",
      semanticVector
    );
  }
}
