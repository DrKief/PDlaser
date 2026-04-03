package pdl.backend.ingestion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pdl.backend.auth.UserAccount;
import pdl.backend.auth.UserRepository;
import pdl.backend.gallery.core.FileStorageService;
import pdl.backend.gallery.core.MediaRecord;
import pdl.backend.gallery.core.MediaRepository;

@Service
public class UnsplashService {

  private static final Logger log = LoggerFactory.getLogger(UnsplashService.class);
  private final JdbcTemplate jdbcTemplate;
  private final FileStorageService storageService;
  private final MediaRepository recordRepository;
  private final UserRepository userRepository;

  @Value("${app.unsplash.dataset-dir}")
  private String datasetDir;

  private String status = "IDLE";

  public UnsplashService(
    JdbcTemplate jdbcTemplate,
    FileStorageService storageService,
    MediaRepository recordRepository,
    UserRepository userRepository
  ) {
    this.jdbcTemplate = jdbcTemplate;
    this.storageService = storageService;
    this.recordRepository = recordRepository;
    this.userRepository = userRepository;
  }

  public String getStatus() {
    return status;
  }

  @Async("taskExecutor")
  public void syncMetadata(int limit, int offset) {
    status = "SYNCING_METADATA";
    File photosFile = new File(datasetDir, "photos.tsv");

    if (!photosFile.exists()) {
      status = "ERROR: photos.tsv missing at " + datasetDir;
      return;
    }

    UserAccount admin = userRepository.findByUsername("admin").orElseThrow();

    try (BufferedReader br = new BufferedReader(new FileReader(photosFile))) {
      String line;
      br.readLine(); // Skip TSV Header

      int currentLine = 0;
      int processed = 0;

      while ((line = br.readLine()) != null) {
        if (++currentLine <= offset) continue;
        if (processed >= limit) break;

        String[] cols = line.split("\t", -1);
        if (cols.length < 3) continue;

        String pId = cols[0];
        String rawUrl = cols[2];
        String description = (cols.length > 8) ? cols[8] : "";
        String photographerName = (cols.length > 11)
          ? (cols[10] + " " + cols[11]).trim()
          : "Unknown";
        String cameraMake = (cols.length > 12) ? cols[12] : "";
        String country = (cols.length > 21) ? cols[21] : "";

        long downloads = 0;
        if (cols.length > 24 && !cols[24].isEmpty()) {
          try {
            downloads = Long.parseLong(cols[24]);
          } catch (Exception ignored) {}
        }

        // Avoid inserting duplicates
        Integer existing = jdbcTemplate.queryForObject(
          "SELECT COUNT(*) FROM images WHERE provider_id = ?",
          Integer.class,
          pId
        );

        if (existing != null && existing == 0) {
          jdbcTemplate.update(
            "INSERT INTO images (filename, format, provider, provider_id, remote_url, extraction_status, description, photographer_name, camera_make, location_country, stats_downloads, user_id) " +
              "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
            "unsplash_" + pId + ".jpg",
            "jpeg",
            "UNSPLASH",
            pId,
            rawUrl,
            "REMOTE_METADATA",
            description,
            photographerName,
            cameraMake,
            country,
            downloads,
            admin.getId()
          );
        }
        processed++;
      }
      status = "COMPLETED: Synced " + processed + " metadata records into Catalog.";
    } catch (Exception e) {
      log.error("Metadata sync failed", e);
      status = "ERROR: " + e.getMessage();
    }
  }

  public Map<String, Object> getCatalog(int page, int size, String query) {
    String baseSql = "FROM images WHERE extraction_status = 'REMOTE_METADATA'";
    List<Object> params = new ArrayList<>();

    if (query != null && !query.trim().isEmpty()) {
      baseSql +=
        " AND (camera_make ILIKE ? OR location_country ILIKE ? OR description ILIKE ? OR photographer_name ILIKE ?)";
      String likeQuery = "%" + query.trim() + "%";
      params.add(likeQuery);
      params.add(likeQuery);
      params.add(likeQuery);
      params.add(likeQuery);
    }

    String countSql = "SELECT COUNT(*) " + baseSql;
    Long totalElements = jdbcTemplate.queryForObject(countSql, Long.class, params.toArray());

    String dataSql =
      "SELECT id, remote_url, photographer_name, camera_make, location_country, stats_downloads " +
      baseSql +
      " ORDER BY stats_downloads DESC LIMIT ? OFFSET ?";
    params.add(size);
    params.add(page * size);

    List<Map<String, Object>> content = jdbcTemplate.queryForList(dataSql, params.toArray());

    Map<String, Object> result = new HashMap<>();
    result.put("content", content);
    result.put("totalElements", totalElements != null ? totalElements : 0);
    result.put(
      "totalPages",
      (int) Math.ceil((double) (totalElements != null ? totalElements : 0) / size)
    );
    return result;
  }

  @Async("taskExecutor")
  public void importSelectedImages(List<Long> imageIds) {
    status = "IMPORTING_BATCH (0 / " + imageIds.size() + ")";
    int count = 0;
    RestTemplate restTemplate = new RestTemplate();

    for (Long id : imageIds) {
      try {
        // Confirm it's still waiting to be downloaded
        String currentStatus = jdbcTemplate.queryForObject(
          "SELECT extraction_status FROM images WHERE id = ?",
          String.class,
          id
        );
        if (!"REMOTE_METADATA".equals(currentStatus)) continue;

        Optional<MediaRecord> opt = recordRepository.findById(id);
        if (opt.isEmpty()) continue;

        MediaRecord record = opt.get();
        status = "DOWNLOADING (" + (count + 1) + " / " + imageIds.size() + "): " + record.getName();

        // Download the binary from the Unsplash CDN
        byte[] bytes = restTemplate.getForObject(record.getRemoteUrl() + "?w=1080", byte[].class);
        if (bytes != null) {
          record.setData(bytes);
          // processAndSaveImage will do an UPDATE because record.getId() is populated,
          // perfectly preserving our EXIF metadata while extracting vectors!
          storageService.processAndSaveImage(record, true);
          Thread.sleep(1500); // Respect API limits
        }
      } catch (Exception e) {
        log.error("Failed to import remote image ID: " + id, e);
      }
      count++;
    }
    status = "COMPLETED: Imported " + count + " images.";
  }
}
