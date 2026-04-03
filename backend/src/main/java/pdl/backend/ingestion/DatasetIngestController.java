package pdl.backend.ingestion;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class DatasetIngestController {

  private final UnsplashService unsplashImporter;

  public DatasetIngestController(UnsplashService unsplashImporter) {
    this.unsplashImporter = unsplashImporter;
  }

  @PostMapping("/unsplash/import")
  public ResponseEntity<?> startUnsplashImport(@RequestBody Map<String, Integer> request) {
    if (unsplashImporter.getStatus().startsWith("IMPORTING")) {
      return ResponseEntity.badRequest().body(Map.of("message", "Import already in progress."));
    }
    int limit = request.getOrDefault("limit", 50);
    int offset = request.getOrDefault("offset", 0);
    unsplashImporter.startImport(limit, offset);
    return ResponseEntity.ok(
      Map.of("message", "Dataset import initiated. Limit: " + limit + ", Offset: " + offset)
    );
  }

  @PostMapping("/unsplash/import/keyword")
  public ResponseEntity<?> startUnsplashKeywordImport(@RequestBody Map<String, Object> request) {
    if (
      unsplashImporter.getStatus().startsWith("IMPORTING") ||
      unsplashImporter.getStatus().startsWith("FINDING")
    ) {
      return ResponseEntity.badRequest().body(Map.of("message", "Import already in progress."));
    }
    String keyword = (String) request.get("keyword");
    if (keyword == null || keyword.trim().isEmpty()) {
      return ResponseEntity.badRequest().body(Map.of("message", "Keyword is required."));
    }
    int limit = 50;
    if (request.containsKey("limit")) {
      Object limitObj = request.get("limit");
      if (limitObj instanceof Number) {
        limit = ((Number) limitObj).intValue();
      } else if (limitObj instanceof String) {
        try {
          limit = Integer.parseInt((String) limitObj);
        } catch (NumberFormatException e) {
          return ResponseEntity.badRequest().body(
            Map.of("message", "Limit must be a valid number.")
          );
        }
      }
    }
    unsplashImporter.importByKeyword(keyword, limit);
    return ResponseEntity.ok(
      Map.of("message", "Dataset import initiated for keyword: " + keyword + ", Limit: " + limit)
    );
  }

<<<<<<< HEAD
  @GetMapping("/unsplash/status")
  public ResponseEntity<?> getImportStatus() {
    return ResponseEntity.ok(Map.of("status", unsplashImporter.getStatus()));
  }
=======
    @PostMapping("/unsplash/import/full")
    public ResponseEntity<?> startUnsplashFullImport() {
        if (unsplashImporter.getStatus().startsWith("IMPORTING") || unsplashImporter.getStatus().startsWith("FINDING")) {
            return ResponseEntity.badRequest().body(Map.of("message", "Import already in progress."));
        }
        unsplashImporter.importFullDataset();
        return ResponseEntity.ok(Map.of("message", "Raw Postgres dataset import initiated."));
    }

    @GetMapping("/unsplash/status")
    public ResponseEntity<?> getImportStatus() {
        return ResponseEntity.ok(Map.of("status", unsplashImporter.getStatus()));
    }
>>>>>>> 2524825 (refactor : minor css contrast changes started work on ingestion straight from unsplash dataset (WIP))
}
