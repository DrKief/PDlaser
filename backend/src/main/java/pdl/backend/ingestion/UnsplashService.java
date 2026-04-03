package pdl.backend.ingestion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
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

  // Safely extracts a column value, defaulting if the column doesn't exist in the TSV
  private String getCol(String[] cols, Map<String, Integer> map, String key, String defaultVal) {
    Integer idx = map.get(key);
    if (idx != null && idx < cols.length && cols[idx] != null && !cols[idx].trim().isEmpty()) {
      return cols[idx].trim();
    }
    return defaultVal;
  }

  @Async("taskExecutor")
  public void downloadAndExtractDataset() {
    status = "DOWNLOADING_ZIP (Resolving redirects...)";
    Path zipPath = Paths.get(datasetDir, "unsplash-lite.zip");
    
    try {
      Files.createDirectories(Paths.get(datasetDir));
      
      // 1. Use Java 21 Native HttpClient with strict auto-redirect across servers
      HttpClient client = HttpClient.newBuilder()
              .followRedirects(HttpClient.Redirect.ALWAYS)
              .connectTimeout(Duration.ofSeconds(20))
              .build();

        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("https://unsplash-datasets.s3.amazonaws.com/lite/latest/unsplash-research-dataset-lite-latest.zip"))
        .GET()
        .build();

      status = "DOWNLOADING_ZIP (Streaming ~700MB archive to disk...)";
      
      // Stream directly to the file to avoid out-of-memory errors
      HttpResponse<Path> response = client.send(request, HttpResponse.BodyHandlers.ofFile(zipPath));

      if (response.statusCode() >= 400) {
          throw new RuntimeException("Server returned HTTP " + response.statusCode());
      }

      // Safety check: Make sure we didn't just download a tiny HTML error page
      long fileSize = Files.size(zipPath);
      if (fileSize < 1024 * 1024) { // If it's less than 1MB, it's definitely fake
          throw new RuntimeException("Downloaded file is suspiciously small (" + fileSize + " bytes). Download failed.");
      }

      // 2. Unzip and Flatten
      status = "EXTRACTING_TSV_FILES";
      int filesExtracted = 0;
      try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipPath.toFile()))) {
        ZipEntry zipEntry = zis.getNextEntry();
        while (zipEntry != null) {
          String entryName = zipEntry.getName();
          // Extract only the filename from potential folder structure in ZIP (Prevents ZipSlip vulnerability)
          String fileName = new File(entryName).getName();
          

          if (fileName.contains(".tsv")) {
              // Normalize Unsplash filenames like 'photos.tsv000' to 'photos.tsv'
              String targetName = fileName.substring(0, fileName.indexOf(".tsv") + 4);
              Path targetPath = Paths.get(datasetDir, targetName);
              
              Files.copy(zis, targetPath, StandardCopyOption.REPLACE_EXISTING);
              filesExtracted++;
          }
          zipEntry = zis.getNextEntry();
        }
      }

      if (filesExtracted == 0) {
          throw new RuntimeException("No .tsv files were found inside the zip archive.");
      }

      // 3. Cleanup the 700MB zip file to save space
      Files.deleteIfExists(zipPath);
      status = "IDLE";
      log.info("Unsplash dataset downloaded and extracted successfully to {}", datasetDir);

    } catch (Exception e) {
      log.error("Failed to download or extract Unsplash dataset", e);
      status = "ERROR: " + e.getMessage();
      // Try to clean up the corrupt zip if it exists
      try { Files.deleteIfExists(zipPath); } catch (Exception ignored) {}
    }
  }

  @Async("taskExecutor")
  public void syncMetadata(int limit, int offset) {
    status = "SYNCING_METADATA";
    File photosFile = new File(datasetDir, "photos.tsv");

    if (!photosFile.exists()) {
      status = "ERROR: photos.tsv missing. Please click 'Download Archive' first.";
      return;
    }

    UserAccount admin = userRepository.findByUsername("admin").orElseThrow();

    try (BufferedReader br = new BufferedReader(new FileReader(photosFile))) {
      String headerLine = br.readLine();
      if (headerLine == null) throw new IllegalStateException("TSV file is empty");

      // Dynamically map columns so it works on both Lite, Full, and Sample datasets
      String[] headers = headerLine.split("\t");
      Map<String, Integer> colMap = new HashMap<>();
      for (int i = 0; i < headers.length; i++) colMap.put(headers[i].trim(), i);

      int currentLine = 0;
      int processed = 0;
      String line;

      while ((line = br.readLine()) != null) {
        if (++currentLine <= offset) continue;
        if (processed >= limit) break;

        String[] cols = line.split("\t", -1);
        String pId = getCol(cols, colMap, "photo_id", null);
        String rawUrl = getCol(cols, colMap, "photo_image_url", getCol(cols, colMap, "photo_url", null));

        if (pId == null || rawUrl == null) continue;

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

        // Avoid inserting duplicates
        Integer existing = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM images WHERE provider_id = ?", Integer.class, pId);

        if (existing != null && existing == 0) {
          jdbcTemplate.update(
            "INSERT INTO images (filename, format, provider, provider_id, remote_url, extraction_status, description, photographer_name, camera_make, location_country, stats_downloads, user_id) " +
              "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
            "unsplash_" + pId + ".jpg", "jpeg", "UNSPLASH", pId, rawUrl, "REMOTE_METADATA",
            description, photographerName, cameraMake, country, downloads, admin.getId()
          );
          processed++;
        }
      }
      status = "COMPLETED: Synced " + processed + " new metadata records.";
    } catch (Exception e) {
      log.error("Metadata sync failed", e);
      status = "ERROR: " + e.getMessage();
    }
  }

  public Map<String, Object> getCatalog(int page, int size, String query) {
    String baseSql = "FROM images WHERE extraction_status = 'REMOTE_METADATA'";
    List<Object> params = new ArrayList<>();

    if (query != null && !query.trim().isEmpty()) {
      baseSql += " AND (camera_make ILIKE ? OR location_country ILIKE ? OR description ILIKE ? OR photographer_name ILIKE ?)";
      String likeQuery = "%" + query.trim() + "%";
      params.add(likeQuery); params.add(likeQuery); params.add(likeQuery); params.add(likeQuery);
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
        if (opt.isEmpty()) continue;
        MediaRecord record = opt.get();
        if (!"REMOTE_METADATA".equals(record.getExtractionStatus())) continue;

        status = "IMPORTING (" + (count + 1) + " / " + imageIds.size() + "): " + record.getName();

        // Safely append query parameters regardless of whether the URL already has a '?'
        String remoteUrl = record.getRemoteUrl();
        String fetchUrl = remoteUrl + (remoteUrl.contains("?") ? "&" : "?") + "w=1080&q=85&fm=jpg";

        byte[] bytes = restTemplate.getForObject(fetchUrl, byte[].class);
        if (bytes != null) {
          record.setData(bytes);
          storageService.processAndSaveImage(record, true);
          Thread.sleep(1000); // Politeness delay to prevent Unsplash CDN ban
        }
      } catch (Exception e) {
        log.error("Failed to import image ID: " + id, e);
      }
      count++;
    }
    status = "IDLE";
  }
}