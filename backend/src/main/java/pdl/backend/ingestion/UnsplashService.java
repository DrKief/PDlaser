package pdl.backend.ingestion;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
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

  public String getStatus() { return status; }

  private String getCol(String[] cols, Map<String, Integer> map, String key, String defaultVal) {
    Integer idx = map.get(key);
    if (idx != null && idx < cols.length && cols[idx] != null && !cols[idx].trim().isEmpty()) {
      return cols[idx].trim();
    }
    return defaultVal;
  }

  @Async("taskExecutor")
  public void processTsvUpload(MultipartFile file, int limit) {
    status = "PROCESSING_UPLOADED_FILE";
    try {
      UserAccount admin = userRepository.findByUsername("admin").orElseThrow();
      
      try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
        String headerLine = br.readLine();
        if (headerLine == null) throw new RuntimeException("File is empty");

        // Unsplash files use Tabs, even if named .csv000
        String[] headers = headerLine.split("\t");
        Map<String, Integer> colMap = new HashMap<>();
        for (int i = 0; i < headers.length; i++) colMap.put(headers[i].trim(), i);

        int processed = 0;
        String line;
        while ((line = br.readLine()) != null && processed < limit) {
          String[] cols = line.split("\t", -1);
          String pId = getCol(cols, colMap, "photo_id", null);
          String rawUrl = getCol(cols, colMap, "photo_image_url", getCol(cols, colMap, "photo_url", null));

          if (pId == null || rawUrl == null) continue;

          // Check if already in our database
          Integer existing = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM images WHERE provider_id = ?", Integer.class, pId);
          
          if (existing != null && existing == 0) {
            String firstName = getCol(cols, colMap, "photographer_first_name", "");
            String lastName = getCol(cols, colMap, "photographer_last_name", "");
            String photographerName = (firstName + " " + lastName).trim();
            
            jdbcTemplate.update(
              "INSERT INTO images (filename, format, provider, provider_id, remote_url, extraction_status, description, photographer_name, camera_make, location_country, stats_downloads, user_id) " +
              "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
              "unsplash_" + pId + ".jpg", "jpeg", "UNSPLASH", pId, rawUrl, "REMOTE_METADATA",
              getCol(cols, colMap, "photo_description", ""), 
              photographerName.isEmpty() ? "Unknown" : photographerName,
              getCol(cols, colMap, "exif_camera_make", "Unknown"),
              getCol(cols, colMap, "photo_location_country", "Unknown"),
              Long.parseLong(getCol(cols, colMap, "stats_downloads", "0")),
              admin.getId()
            );
            processed++;
          }
        }
        status = "IDLE";
        log.info("Successfully indexed {} records from uploaded TSV", processed);
      }
    } catch (Exception e) {
      log.error("Failed to process TSV upload", e);
      status = "ERROR: " + e.getMessage();
    }
  }

  public Map<String, Object> getCatalog(int page, int size, String query) {
    String baseSql = "FROM images WHERE extraction_status = 'REMOTE_METADATA'";
    List<Object> params = new ArrayList<>();

    if (query != null && !query.trim().isEmpty()) {
      baseSql += " AND (camera_make ILIKE ? OR location_country ILIKE ? OR photographer_name ILIKE ?)";
      String likeQuery = "%" + query.trim() + "%";
      params.add(likeQuery); params.add(likeQuery); params.add(likeQuery);
    }

    String countSql = "SELECT COUNT(*) " + baseSql;
    Long totalElements = jdbcTemplate.queryForObject(countSql, Long.class, params.toArray());

    String dataSql = "SELECT id, remote_url, photographer_name, camera_make, location_country, stats_downloads " +
      baseSql + " ORDER BY stats_downloads DESC LIMIT ? OFFSET ?";
    params.add(size);
    params.add(page * size);

    List<Map<String, Object>> content = jdbcTemplate.queryForList(dataSql, params.toArray());

    Map<String, Object> result = new HashMap<>();
    result.put("content", content);
    result.put("totalElements", totalElements != null ? totalElements : 0);
    result.put("totalPages", (int) Math.ceil((double) (totalElements != null ? totalElements : 0) / size));
    return result;
  }

  @Async("taskExecutor")
  public void importSelectedImages(List<Long> imageIds) {
    int count = 0;
    RestTemplate restTemplate = new RestTemplate();
    for (Long id : imageIds) {
      try {
        Optional<MediaRecord> opt = recordRepository.findById(id);
        if (opt.isPresent()) {
          MediaRecord record = opt.get();
          status = "DOWNLOADING (" + (count + 1) + " / " + imageIds.size() + ")";
          byte[] bytes = restTemplate.getForObject(record.getRemoteUrl() + "?w=1080", byte[].class);
          if (bytes != null) {
            record.setData(bytes);
            storageService.processAndSaveImage(record, true);
          }
        }
      } catch (Exception e) { log.error("Download failed for ID " + id, e); }
      count++;
    }
    status = "IDLE";
  }
}