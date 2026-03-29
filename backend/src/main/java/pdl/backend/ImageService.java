package pdl.backend;

import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.util.HexFormat;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
public class ImageService {

  private static final Logger log = LoggerFactory.getLogger(ImageService.class);
  private final ImageDao imageDao;
  private final AsyncImageProcessor asyncProcessor;

  public ImageService(ImageDao imageDao, AsyncImageProcessor asyncProcessor) {
    this.imageDao = imageDao;
    this.asyncProcessor = asyncProcessor;
  }

  @Transactional
  public void processAndSaveImage(Image img, boolean saveToDisk) {
    String hash = calculateSHA256(img.getData());

    Long existingId = imageDao.findIdByHash(hash);
    if (existingId != null) {
      img.setId(existingId);
      img.setHash(hash);
      return;
    }

    img.setHash(hash);

    // Fast header-only reading for initial metadata DB ingestion
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

    imageDao.createBasic(img, width, height, saveToDisk);

    // Ensure async thread runs strictly AFTER data is globally committed
    TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
      @Override
      public void afterCommit() {
        asyncProcessor.processImageDescriptors(img.getId(), img.getData());
      }
    });
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
}