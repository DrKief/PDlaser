package pdl.backend.ingestion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

  private String getCol(String[] cols, Map<String, Integer> map, String key, String defaultVal) {
    Integer idx = map.get(key);
    if (idx != null && idx < cols.length && cols[idx] != null && !cols[idx].trim().isEmpty()) {
      return cols[idx].trim();
    }
    return defaultVal;
  }

  @Async
  public void syncMetadataFromFile(Path tempFilePath, int limit, int offset) {
    status = "SYNCING_METADATA";
    UserAccount admin = userRepository.findByUsername("admin").orElseThrow();

    try (BufferedReader br = new BufferedReader(new FileReader(tempFilePath.toFile()))) {
      String headerLine = br.readLine();
      if (headerLine == null) throw new IllegalStateException("Uploaded file is empty");

      // Auto-detect if Unsplash used tabs or commas for this file version
      String separator = headerLine.contains("\t") ? "\t" : ",";

      String[] headers = headerLine.split(separator);
      Map<String, Integer> colMap = new HashMap<>();
      for (int i = 0; i < headers.length; i++) colMap.put(headers[i].trim(), i);

      int currentLine = 0;
      int processed = 0;
      String line;

      while ((line = br.readLine()) != null) {
        if (++currentLine <= offset) continue;
        if (processed >= limit) break;

        String[] cols = line.split(separator, -1);
        String pId = getCol(cols, colMap, "photo_id", null);
        String rawUrl = getCol(
          cols,
          colMap,
          "photo_image_url",
          getCol(cols, colMap, "photo_url", null)
        );

        if (pId == null || rawUrl == null) continue;
        if (!rawUrl.startsWith("http://") && !rawUrl.startsWith("https://")) continue;

        String description = getCol(cols, colMap, "photo_description", "No description");
        String firstName = getCol(cols, colMap, "photographer_first_name", "");
        String lastName = getCol(cols, colMap, "photographer_last_name", "");
        String photographerName = (firstName + " " + lastName).trim();
        if (photographerName.isEmpty()) photographerName = "Unknown";
        String cameraMake = getCol(cols, colMap, "exif_camera_make", "Unknown");
        String country = getCol(cols, colMap, "photo_location_country", "Unknown");

        long downloads = 0;
        try {
          downloads = Long.parseLong(getCol(cols, colMap, "stats_downloads", "0"));
        } catch (Exception ignored) {}

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
          processed++;
        }
      }
      status = "COMPLETED: Synced " + processed + " new metadata records.";
    } catch (Exception e) {
      log.error("Metadata sync failed", e);
      status = "ERROR: " + e.getMessage();
    } finally {
      // Always clean up the temporary file from the container's disk
      try {
        Files.deleteIfExists(tempFilePath);
      } catch (Exception ignored) {}
    }
  }

  @Async
  public void syncKeywordsFromFile(Path tempFilePath, int limit, int offset) {
    status = "SYNCING_KEYWORDS";

    try (BufferedReader br = new BufferedReader(new FileReader(tempFilePath.toFile()))) {
      String headerLine = br.readLine();
      if (headerLine == null) throw new IllegalStateException("Uploaded file is empty");

      String separator = headerLine.contains("\t") ? "\t" : ",";
      String[] headers = headerLine.split(separator);
      Map<String, Integer> colMap = new HashMap<>();
      for (int i = 0; i < headers.length; i++) colMap.put(headers[i].trim(), i);

      // Load all known Unsplash photo IDs to local DB IDs
      Map<String, Long> localPhotoIds = new HashMap<>();
      jdbcTemplate.query("SELECT provider_id, id FROM images WHERE provider = 'UNSPLASH'", rs -> {
        localPhotoIds.put(rs.getString("provider_id"), rs.getLong("id"));
      });

      int currentLine = 0;
      int processed = 0;
      String line;

      List<Object[]> batchArgs = new ArrayList<>();

      while ((line = br.readLine()) != null) {
        if (++currentLine <= offset) continue;
        if (processed >= limit) break;

        String[] cols = line.split(separator, -1);
        String pId = getCol(cols, colMap, "photo_id", null);
        String keyword = getCol(cols, colMap, "keyword", null);

        if (pId == null || keyword == null || keyword.trim().isEmpty()) continue;

        // Skip if we don't have the photo in our database
        Long localId = localPhotoIds.get(pId);
        if (localId == null) continue;

        // We are processing all keywords regardless of confidence

        String normalizedKeyword = keyword.trim().toLowerCase().replaceAll("\\s+", "_");

        batchArgs.add(new Object[] { localId, normalizedKeyword, false });
        processed++;

        if (batchArgs.size() >= 1000) {
          jdbcTemplate.batchUpdate(
            "INSERT INTO imagekeywords (imageid, keyword, is_ai_generated) VALUES (?, ?, ?) ON CONFLICT DO NOTHING",
            batchArgs
          );
          batchArgs.clear();
        }
      }

      if (!batchArgs.isEmpty()) {
        jdbcTemplate.batchUpdate(
          "INSERT INTO imagekeywords (imageid, keyword, is_ai_generated) VALUES (?, ?, ?) ON CONFLICT DO NOTHING",
          batchArgs
        );
      }

      status = "COMPLETED: Linked " + processed + " tags to photos.";
    } catch (Exception e) {
      log.error("Keywords sync failed", e);
      status = "ERROR: " + e.getMessage();
    } finally {
      // Always clean up the temporary file from the container's disk
      try {
        Files.deleteIfExists(tempFilePath);
      } catch (Exception ignored) {}
    }
  }

  public Map<String, Object> getCatalog(
    int page,
    int size,
    String query,
    String camera,
    String country
  ) {
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

    if (camera != null && !camera.trim().isEmpty()) {
      baseSql += " AND camera_make ILIKE ?";
      params.add("%" + camera.trim() + "%");
    }

    if (country != null && !country.trim().isEmpty()) {
      baseSql += " AND location_country ILIKE ?";
      params.add("%" + country.trim() + "%");
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

  @Async
  public void importSelectedImages(List<Long> imageIds) {
    if (imageIds == null || imageIds.isEmpty()) return;

    // 1. Synchronously mark all selected images as DOWNLOADING immediately
    // This instantly hides them from the frontend catalog
    List<Object[]> batchArgs = new ArrayList<>();
    for (Long id : imageIds) {
      batchArgs.add(new Object[] { id });
    }
    jdbcTemplate.batchUpdate(
      "UPDATE images SET extraction_status = 'DOWNLOADING' WHERE id = ? AND extraction_status = 'REMOTE_METADATA'",
      batchArgs
    );

    // 2. Start the slow, polite download process
    int count = 0;
    RestTemplate restTemplate = new RestTemplate();

    for (Long id : imageIds) {
      try {
        Optional<MediaRecord> opt = recordRepository.findById(id);
        if (opt.isEmpty()) continue;
        MediaRecord record = opt.get();
        // Check for DOWNLOADING since we just updated it
        if (!"DOWNLOADING".equals(record.getExtractionStatus())) continue;

        status = "IMPORTING (" + (count + 1) + " / " + imageIds.size() + "): " + record.getName();

        String remoteUrl = record.getRemoteUrl();
        String fetchUrl = remoteUrl + (remoteUrl.contains("?") ? "&" : "?") + "w=1080&q=85&fm=jpg";

        if (!fetchUrl.startsWith("http://") && !fetchUrl.startsWith("https://")) {
          fetchUrl = "https://" + fetchUrl;
        }

        byte[] bytes = restTemplate.getForObject(URI.create(fetchUrl), byte[].class);
        if (bytes != null) {
          record.setData(bytes);
          // storageService sets it to PENDING and our ML queue takes over
          storageService.processAndSaveImage(record, true);
        } else {
          // If bytes are null, it failed to fetch. Revert to REMOTE_METADATA so it shows up again.
          jdbcTemplate.update(
            "UPDATE images SET extraction_status = 'REMOTE_METADATA' WHERE id = ?",
            id
          );
        }
      } catch (Exception e) {
        log.error("Failed to import image ID: " + id, e);
        // On any HTTP or network error, revert the status so it isn't permanently stuck as DOWNLOADING
        jdbcTemplate.update(
          "UPDATE images SET extraction_status = 'REMOTE_METADATA' WHERE id = ?",
          id
        );
      } finally {
        try {
          Thread.sleep(1000); // Politeness delay regardless of success or failure
        } catch (InterruptedException ie) {
          Thread.currentThread().interrupt();
        }
      }
      count++;
    }
    status = "IDLE";
  }

  public void importBatchImages(int limit, String query, String camera, String country) {
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

    if (camera != null && !camera.trim().isEmpty()) {
      baseSql += " AND camera_make ILIKE ?";
      params.add("%" + camera.trim() + "%");
    }

    if (country != null && !country.trim().isEmpty()) {
      baseSql += " AND location_country ILIKE ?";
      params.add("%" + country.trim() + "%");
    }

    String dataSql = "SELECT id " + baseSql + " ORDER BY stats_downloads DESC LIMIT ?";
    params.add(limit);

    List<Long> idsToImport = jdbcTemplate.queryForList(dataSql, Long.class, params.toArray());

    if (!idsToImport.isEmpty()) {
      importSelectedImages(idsToImport);
    }
  }
}
