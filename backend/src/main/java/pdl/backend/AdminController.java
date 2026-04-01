package pdl.backend;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final UnsplashIngester unsplashIngester;

    public AdminController(UnsplashIngester unsplashIngester) {
        this.unsplashIngester = unsplashIngester;
    }

    @PostMapping("/unsplash/import")
    public ResponseEntity<?> startUnsplashImport() {
        if (unsplashIngester.getStatus().startsWith("IMPORTING")) {
            return ResponseEntity.badRequest().body(Map.of("message", "Import already in progress."));
        }
        unsplashIngester.startImport();
        return ResponseEntity.ok(Map.of("message", "Dataset import initiated in background."));
    }

    @GetMapping("/unsplash/status")
    public ResponseEntity<?> getImportStatus() {
        return ResponseEntity.ok(Map.of("status", unsplashIngester.getStatus()));
    }
}