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
public class DatasetIngestionLayer {

    private final UnsplashImporterLayer unsplashImporter;

    public DatasetIngestionLayer(UnsplashImporterLayer unsplashImporter) {
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
        return ResponseEntity.ok(Map.of("message", "Dataset import initiated. Limit: " + limit + ", Offset: " + offset));
    }

    @GetMapping("/unsplash/status")
    public ResponseEntity<?> getImportStatus() {
        return ResponseEntity.ok(Map.of("status", unsplashImporter.getStatus()));
    }
}
