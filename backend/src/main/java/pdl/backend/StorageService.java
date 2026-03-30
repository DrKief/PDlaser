package pdl.backend;

import com.pgvector.PGvector;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.HexFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * Service layer orchestrating storage operations and business logic relating to images:
 * - Routing algorithms, hashing, saving, deleting, and similarity requests.
 */
@Service
public class StorageService {

  private static final Logger log = LoggerFactory.getLogger(StorageService.class);

  private final MetadataRepository imageEntityRepository;
  private final VectorRepository imageDescriptorRepository;
  private final AsyncWorker backgroundWorker;

  @Value("${app.image.directory:images}")
  private String imageDirectoryPath;

  public StorageService(
    MetadataRepository imageEntityRepository,
    VectorRepository imageDescriptorRepository,
    AsyncWorker backgroundWorker
  ) {
    this.imageEntityRepository = imageEntityRepository;
    this.imageDescriptorRepository = imageDescriptorRepository;
    this.backgroundWorker = backgroundWorker;
  }

  /**
   * Processes a newly uploaded image, saves it to the DB, saves to disk,
   * and triggers async descriptor extraction.
   *
   * @param img The Metadata object containing the image metadata and raw bytes.
   * @param saveToDisk Boolean flag indicating whether to physically save the file.
   */
  @Transactional
  public void processAndSaveImage(Metadata img, boolean saveToDisk) {
    // 1. Calculate hash to prevent duplicates
    String hash = calculateSHA256(img.getData());

    Optional<Metadata> existing = imageEntityRepository.findByHash(hash);
    if (existing.isPresent()) {
      img.setId(existing.get().getId());
      img.setHash(hash);
      return; // Stop processing, it's a duplicate
    }

    img.setHash(hash);
    img.setFormat(getFileExtension(img.getName()));
    img.setExtractionStatus("PENDING");

    // 2. Fast header-only reading for initial dimensions metadata
    int width = 0;
    int height = 0;
    try (
      ImageInputStream in = ImageIO.createImageInputStream(new ByteArrayInputStream(img.getData()))
    ) {
      Iterator<ImageReader> readers = ImageIO.getImageReaders(in);
      if (readers.hasNext()) {
        ImageReader reader = readers.next();
        reader.setInput(in, true, true);
        width = reader.getWidth(0);
        height = reader.getHeight(0);
        reader.dispose();
      }
    } catch (Exception e) {
      log.warn("Could not fast-read dimensions for image hash {}", hash);
    }

    img.setWidth(width);
    img.setHeight(height);

    // 3. Save to DB (Generates the ID)
    Metadata savedImage = imageEntityRepository.save(img);
    img.setId(savedImage.getId());

    // 4. Save physically to the file system
    if (saveToDisk) {
      try {
        Path path = Paths.get(imageDirectoryPath, img.getName());
        Files.write(path, img.getData());
      } catch (IOException e) {
        throw new RuntimeException("Failed to write image file to disk, rolling back DB insert", e);
      }
    }

    // 5. Ensure the async thread runs strictly AFTER the DB transaction is globally committed
    TransactionSynchronizationManager.registerSynchronization(
      new TransactionSynchronization() {
        @Override
        public void afterCommit() {
          backgroundWorker.processImageDescriptors(img.getId(), img.getData());
        }
      }
    );
  }

  /**
   * Finds similar images dynamically based on an unsaved byte array upload.
   * Extracts vectors on the fly without database insertion, then queries PGvector.
   */
  public List<Map<String, Object>> searchSimilarFromUpload(
    byte[] imageData,
    String type,
    int limit
  ) {
    try {
      BufferedImage bimg = ImageIO.read(new ByteArrayInputStream(imageData));
      if (bimg == null) return List.of();

      BufferedImage resizedImage = FeatureExtractor.resizeImageLanczos3(bimg, 256, 256);
      float[] vectorData;
      String vectorColumn;

      if ("saturation".equalsIgnoreCase(type)) {
        vectorColumn = "hsvvector";
        vectorData = FeatureExtractor.extractHsvHistogram(resizedImage);
      } else if ("rgb".equalsIgnoreCase(type)) {
        vectorColumn = "rgbvector";
        vectorData = FeatureExtractor.extractRgbHistogram(resizedImage);
      } else if ("cielab".equalsIgnoreCase(type)) {
        vectorColumn = "labvector";
        vectorData = FeatureExtractor.extractCieLabHistogram(resizedImage);
      } else {
        vectorColumn = "hogvector";
        float[] hogData = FeatureExtractor.extractGlobalHog(resizedImage);
        if (hogData.length != 31) {
          float[] adjustedHog = new float[31];
          System.arraycopy(hogData, 0, adjustedHog, 0, Math.min(hogData.length, 31));
          hogData = adjustedHog;
        }
        vectorData = hogData;
      }

      PGvector targetVector = new PGvector(vectorData);

      // Pass the raw vector to the repository layer for execution
      return imageDescriptorRepository.findSimilarByVector(targetVector, vectorColumn, limit);
    } catch (Exception e) {
      log.error("Failed to process image upload for similarity search", e);
      return List.of();
    }
  }

  /**
   * Retrieves the DB record and loads the raw file data from the disk.
   *
   * @param id DB ID of the image.
   * @return Optional containing the Metadata record with loaded byte array.
   */
  public Optional<Metadata> getImageWithData(long id) {
    Optional<Metadata> imageOpt = imageEntityRepository.findById(id);
    if (imageOpt.isPresent()) {
      Metadata img = imageOpt.get();
      try {
        Path path = Paths.get(imageDirectoryPath, img.getName());
        if (Files.exists(path)) {
          img.setData(Files.readAllBytes(path));
          return Optional.of(img);
        }
      } catch (IOException e) {
        log.error("Found DB record but failed to load physical file for ID: " + id, e);
      }
    }
    return Optional.empty();
  }

  /**
   * Safely deletes an image from disk and then from the DB.
   *
   * @param id DB ID of the image.
   * @return true if successfully deleted, false if not found.
   */
  @Transactional
  public boolean deleteImage(long id) {
    Optional<Metadata> imageOpt = imageEntityRepository.findById(id);
    if (imageOpt.isPresent()) {
      Metadata img = imageOpt.get();
      // Architectural Patch: Delete File strictly before the Database Row to avoid storage leaks
      try {
        Files.deleteIfExists(Paths.get(imageDirectoryPath, img.getName()));
      } catch (IOException e) {
        log.error("Could not delete file from disk for ID: " + id, e);
      }
      imageEntityRepository.delete(img);
      return true;
    }
    return false;
  }

  // --- Utility Methods ---

  private String calculateSHA256(byte[] data) {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      byte[] hashBytes = digest.digest(data);
      return HexFormat.of().formatHex(hashBytes);
    } catch (Exception e) {
      throw new RuntimeException("Failed to calculate SHA-256 hash", e);
    }
  }

  private String getFileExtension(String filename) {
    if (filename == null || !filename.contains(".")) return "jpeg";
    String ext = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
    if (ext.equals("jpg")) return "jpeg";
    return ext;
  }
}
