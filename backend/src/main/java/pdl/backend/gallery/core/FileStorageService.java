package pdl.backend.gallery.core;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.HexFormat;
import java.util.Iterator;
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
import pdl.backend.vision.VisionProcessor;

@Service
public class FileStorageService {

  private static final Logger log = LoggerFactory.getLogger(FileStorageService.class);
  private final MediaRepository recordRepository;
  private final VisionProcessor backgroundWorker;

  @Value("${app.image.directory:images}")
  private String imageDirectoryPath;

  public FileStorageService(MediaRepository recordRepository, VisionProcessor backgroundWorker) {
    this.recordRepository = recordRepository;
    this.backgroundWorker = backgroundWorker;
  }

  @Transactional
  public void processAndSaveImage(MediaRecord img, boolean saveToDisk) {
    String hash = calculateSHA256(img.getData());

    // Prevent duplicates EXCEPT when updating an existing REMOTE_METADATA record
  Optional<MediaRecord> existing = recordRepository.findByHash(hash);
  if (existing.isPresent() && (img.getId() == 0 || existing.get().getId() != img.getId())) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Image already exists on the server.");
  }

    img.setHash(hash);
    img.setFormat(getFileExtension(img.getName()));
    img.setExtractionStatus("PENDING");

    int width = 0;
    int height = 0;
    try (ImageInputStream in = ImageIO.createImageInputStream(new ByteArrayInputStream(img.getData()))) {
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

    MediaRecord savedImage = recordRepository.save(img); // Triggers UPDATE if ID exists, INSERT if new.
    img.setId(savedImage.getId());

    if (saveToDisk) {
      try {
        Path dirPath = Paths.get(imageDirectoryPath);
        Files.createDirectories(dirPath);
        Path filePath = dirPath.resolve(img.getName());
        Files.write(filePath, img.getData());
      } catch (IOException e) {
        throw new RuntimeException("Failed to write image file to disk", e);
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

  public Optional<MediaRecord> getImageWithData(long id) {
    Optional<MediaRecord> imageOpt = recordRepository.findById(id);
    if (imageOpt.isPresent()) {
      MediaRecord img = imageOpt.get();
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
    Optional<MediaRecord> imageOpt = recordRepository.findById(id);
    if (imageOpt.isPresent()) {
      MediaRecord img = imageOpt.get();
      try {
        Files.deleteIfExists(Paths.get(imageDirectoryPath, img.getName()));
      } catch (IOException e) {
        log.error("Could not delete file from disk for ID: " + id, e);
      }
      recordRepository.delete(img);
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