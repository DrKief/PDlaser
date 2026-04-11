package pdl.backend.gallery.core;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.time.Duration;
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
import org.springframework.web.server.ResponseStatusException;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;

@Service
public class FileStorageService {

  private static final Logger log = LoggerFactory.getLogger(FileStorageService.class);
  private final MediaRepository recordRepository;
  private final S3Client s3Client;
  private final S3Presigner s3Presigner;

  @Value("${s3.bucket}")
  private String bucketName;

  public FileStorageService(
    MediaRepository recordRepository,
    S3Client s3Client,
    S3Presigner s3Presigner
  ) {
    this.recordRepository = recordRepository;
    this.s3Client = s3Client;
    this.s3Presigner = s3Presigner;
  }

  @Transactional
  public void processAndSaveImage(MediaRecord img, boolean saveToS3) {
    String hash = calculateSHA256(img.getData());

    // BUG FIX: Use .equals() for Long object comparison, not !=
    Optional<MediaRecord> existing = recordRepository.findByHash(hash);
    if (existing.isPresent() && (img.getId() == 0 || existing.get().getId() != img.getId())) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Image already exists.");
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

    MediaRecord savedImage = recordRepository.save(img);
    img.setId(savedImage.getId());

    if (saveToS3) {
      String s3Key = savedImage.getId() + "_" + img.getName();
      PutObjectRequest putOb = PutObjectRequest.builder()
        .bucket(bucketName)
        .key(s3Key)
        .contentType("image/" + img.getFormat())
        .build();
      s3Client.putObject(putOb, RequestBody.fromBytes(img.getData()));
    }
  }

  public Optional<MediaRecord> getImageWithData(long id) {
    Optional<MediaRecord> imageOpt = recordRepository.findById(id);
    if (imageOpt.isPresent()) {
      MediaRecord img = imageOpt.get();
      try {
        String s3Key = img.getId() + "_" + img.getName();
        GetObjectRequest getOb = GetObjectRequest.builder().bucket(bucketName).key(s3Key).build();
        byte[] bytes = s3Client.getObjectAsBytes(getOb).asByteArray();
        img.setData(bytes);
        return Optional.of(img);
      } catch (S3Exception e) {
        log.error("Failed to load from Garage S3 for ID: " + id, e);
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
        String s3Key = img.getId() + "_" + img.getName();
        DeleteObjectRequest delReq = DeleteObjectRequest.builder()
          .bucket(bucketName)
          .key(s3Key)
          .build();
        s3Client.deleteObject(delReq);
      } catch (S3Exception e) {
        log.error("Failed to delete from Garage S3 for ID: " + id, e);
      }
      recordRepository.delete(img);
      return true;
    }
    return false;
  }

  // --- NEW: Stream from S3 ---
  public ResponseInputStream<GetObjectResponse> streamImageFromS3(long id, String filename) {
    String s3Key = id + "_" + filename;
    GetObjectRequest getOb = GetObjectRequest.builder().bucket(bucketName).key(s3Key).build();
    return s3Client.getObject(getOb);
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
