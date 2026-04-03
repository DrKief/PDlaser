package pdl.backend.ingestion;

import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class DatasetIngestController {

  private final UnsplashService unsplashService;

  public DatasetIngestController(UnsplashService unsplashService) {
    this.unsplashService = unsplashService;
  }

  @PostMapping("/unsplash/sync")
  public ResponseEntity<?> syncMetadata(@RequestBody Map<String, Integer> request) {
    if (unsplashService.getStatus().startsWith("SYNCING") || unsplashService.getStatus().startsWith("DOWNLOADING")) {
      return ResponseEntity.badRequest().body(Map.of("message", "Job already in progress."));
    }
    int limit = request.getOrDefault("limit", 1000);
    int offset = request.getOrDefault("offset", 0);
    unsplashService.syncMetadata(limit, offset);
    return ResponseEntity.ok(Map.of("message", "Metadata sync initiated."));
  }

  @GetMapping("/unsplash/catalog")
  public ResponseEntity<?> getCatalog(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "30") int size,
      @RequestParam(required = false) String query
  ) {
      return ResponseEntity.ok(unsplashService.getCatalog(page, size, query));
  }

  @PostMapping("/unsplash/import")
  public ResponseEntity<?> importSelected(@RequestBody List<Long> imageIds) {
    if (unsplashService.getStatus().startsWith("SYNCING") || unsplashService.getStatus().startsWith("DOWNLOADING")) {
      return ResponseEntity.badRequest().body(Map.of("message", "Job already in progress."));
    }
    unsplashService.importSelectedImages(imageIds);
    return ResponseEntity.ok(Map.of("message", "Batch import initiated."));
  }

  @GetMapping("/unsplash/status")
  public ResponseEntity<?> getStatus() {
    return ResponseEntity.ok(Map.of("status", unsplashService.getStatus()));
  }
}