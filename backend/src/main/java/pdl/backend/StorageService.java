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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.web.server.ResponseStatusException;

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

  @Transactional
  public void processAndSaveImage(Metadata img, boolean saveToDisk) {
    String hash = calculateSHA256(img.getData());
    
    Optional<Metadata> existing = imageEntityRepository.findByHash(hash);
    if (existing.isPresent()) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Image already exists on the server.");
    }

    img.setHash(hash);
    img.setFormat(getFileExtension(img.getName()));
    img.setExtractionStatus("PENDING");

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

    Metadata savedImage = imageEntityRepository.save(img);
    img.setId(savedImage.getId());

    if (saveToDisk) {
      try {
        Path path = Paths.get(imageDirectoryPath, img.getName());
        Files.write(path, img.getData());
      } catch (IOException e) {
        throw new RuntimeException("Failed to write image file to disk, rolling back DB insert", e);
      }
    }

    TransactionSynchronizationManager.registerSynchronization(
      new TransactionSynchronization() {
        @Override
        public void afterCommit() {
          backgroundWorker.processImageDescriptors(img.getId(), img.getData());
        }
      }
    );
  }

  public List<Map<String, Object>> searchSimilarFromUpload(byte[] imageData, String type, int limit) {
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
      return imageDescriptorRepository.findSimilarByVector(targetVector, vectorColumn, limit);
    } catch (Exception e) {
      log.error("Failed to process image upload for similarity search", e);
      return List.of();
    }
  }

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

  @Transactional
  public boolean deleteImage(long id) {
    Optional<Metadata> imageOpt = imageEntityRepository.findById(id);
    if (imageOpt.isPresent()) {
      Metadata img = imageOpt.get();
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