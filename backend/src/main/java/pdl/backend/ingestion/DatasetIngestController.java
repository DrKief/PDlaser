package pdl.backend.ingestion;

import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class DatasetIngestController {

  private final UnsplashService unsplashService;

  public DatasetIngestController(UnsplashService unsplashService) {
    this.unsplashService = unsplashService;
  }

  @PostMapping("/unsplash/upload")
  public ResponseEntity<?> uploadCatalog(
      @RequestPart("file") MultipartFile file,
      @RequestParam(defaultValue = "1000") int limit
  ) {
    if (file.isEmpty()) return ResponseEntity.badRequest().body("File is empty");
    unsplashService.processTsvUpload(file, limit);
    return ResponseEntity.ok(Map.of("message", "Catalog upload started."));
  }

  @GetMapping("/unsplash/catalog")
  public ResponseEntity<?> getCatalog(
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "24") int size,
    @RequestParam(required = false) String query
  ) {
    return ResponseEntity.ok(unsplashService.getCatalog(page, size, query));
  }

  @PostMapping("/unsplash/import")
  public ResponseEntity<?> importSelected(@RequestBody List<Long> imageIds) {
    unsplashService.importSelectedImages(imageIds);
    return ResponseEntity.ok(Map.of("message", "Import initiated."));
  }

  @GetMapping("/unsplash/status")
  public ResponseEntity<?> getStatus() {
    return ResponseEntity.ok(Map.of("status", unsplashService.getStatus()));
  }
}