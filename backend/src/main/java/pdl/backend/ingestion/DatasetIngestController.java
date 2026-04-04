package pdl.backend.ingestion;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class DatasetIngestController {

  private final UnsplashService unsplashService;

  public DatasetIngestController(UnsplashService unsplashService) {
    this.unsplashService = unsplashService;
  }

  @PostMapping(value = "/unsplash/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<?> uploadAndSyncMetadata(
    @RequestPart("file") MultipartFile file,
    @RequestParam(defaultValue = "1000") int limit,
    @RequestParam(defaultValue = "0") int offset,
    @RequestParam(defaultValue = "PHOTOS") String fileType
  ) {
    if (
      !unsplashService.getStatus().equals("IDLE") &&
      !unsplashService.getStatus().startsWith("ERROR") &&
      !unsplashService.getStatus().startsWith("COMPLETED")
    ) {
      return ResponseEntity.badRequest().body(Map.of("message", "A job is already in progress."));
    }

    try {
      // Save uploaded file to container's temp directory, then trigger async parsing
      Path tempFile = Files.createTempFile("unsplash_", ".tmp");
      file.transferTo(tempFile.toFile());

      if ("KEYWORDS".equalsIgnoreCase(fileType)) {
        unsplashService.syncKeywordsFromFile(tempFile, limit, offset);
      } else {
        unsplashService.syncMetadataFromFile(tempFile, limit, offset);
      }

      return ResponseEntity.ok(Map.of("message", "File uploaded. Sync initiated."));
    } catch (Exception e) {
      return ResponseEntity.internalServerError().body(
        Map.of("message", "Failed to process uploaded file.")
      );
    }
  }

  @GetMapping("/unsplash/catalog")
  public ResponseEntity<?> getCatalog(
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "30") int size,
    @RequestParam(required = false) String query,
    @RequestParam(required = false) String camera,
    @RequestParam(required = false) String country
  ) {
    return ResponseEntity.ok(unsplashService.getCatalog(page, size, query, camera, country));
  }

  @PostMapping("/unsplash/import")
  public ResponseEntity<?> importSelected(@RequestBody List<Long> imageIds) {
    if (
      !unsplashService.getStatus().equals("IDLE") &&
      !unsplashService.getStatus().startsWith("ERROR") &&
      !unsplashService.getStatus().startsWith("COMPLETED")
    ) {
      return ResponseEntity.badRequest().body(Map.of("message", "A job is already in progress."));
    }
    unsplashService.importSelectedImages(imageIds);
    return ResponseEntity.ok(Map.of("message", "Batch import initiated."));
  }

  @GetMapping("/unsplash/status")
  public ResponseEntity<?> getStatus() {
    return ResponseEntity.ok(Map.of("status", unsplashService.getStatus()));
  }
}
