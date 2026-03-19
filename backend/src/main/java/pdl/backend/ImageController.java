package pdl.backend;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;

import jakarta.annotation.PostConstruct;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Value("${app.image.directory:images}")
    private String imageDirectoryPath;

    private final ImageDao imageDao;

    public ImageController(ImageDao imageDao) {
        this.imageDao = imageDao;
    }

    // Strict Requirement: Fail-Fast Startup State
    @PostConstruct
    public void verifyStartupState() {
        Path path = Paths.get(imageDirectoryPath);
        if (!Files.exists(path) || !Files.isDirectory(path)) {
            System.err.println("FATAL: Required 'images' directory not found at " + path.toAbsolutePath());
            System.err.println("Application cannot proceed without the primary data source. Exiting.");
            System.exit(1);
        }
    }

    // Resolves Frontend 404 List Route
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Image>> getImages() {
        return ResponseEntity.ok(imageDao.retrieveAll());
    }

    // Resolves Frontend 404 Raw Bytes Fetch Route
    @GetMapping(value = "/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<?> getImage(@PathVariable("id") long id) {
        Optional<Image> image = imageDao.retrieve(id);
        if (image.isPresent()) {
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("image/" + getFileExtension(image.get().getName())))
                    .body(image.get().getData());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Image not found");
    }

    // Resolves Frontend 404 Deletion Route
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable("id") long id) {
        Optional<Image> image = imageDao.retrieve(id);
        if (image.isPresent()) {
            imageDao.delete(image.get());
            return ResponseEntity.ok("Image deleted");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Image not found");
    }

    // Resolves Frontend 404 Creation/Upload Route
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }
        
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("Only images are allowed");
        }

        try {
            Image image = new Image(file.getOriginalFilename(), file.getBytes());
            imageDao.create(image);
            return ResponseEntity.status(HttpStatus.CREATED).body("Image uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");
        }
    }

    @GetMapping("/{id}/metadata")
    public ResponseEntity<?> getMetadata(@PathVariable("id") long id) {
        try {
            Map<String, Object> metadata = imageDao.getImageMetadata(id);
            String sizeFormat = metadata.get("width") + "*" + metadata.get("height");
            return ResponseEntity.ok().body(Map.of(
                "id", id,
                "format", metadata.get("format"),
                "size", sizeFormat
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Metadata not found");
        }
    }

    // Strict Requirement: API Payload Specification Adherence (GET + RequestBody)
    @GetMapping(value = "/search", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> searchImagesWithAttributes(@RequestBody SearchCriteria criteria) {
        if (criteria == null || criteria.getImageId() == null) {
            return ResponseEntity.badRequest().body("Request body with imageId is required for search.");
        }
        
        try {
            String algorithm = criteria.getAlgorithm() != null ? criteria.getAlgorithm() : "gradient";
            List<Map<String, Object>> results = imageDao.findSimilar(criteria.getImageId(), algorithm, 10);
            return ResponseEntity.ok(results);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error executing search");
        }
    }

    public static class SearchCriteria {
        private Long imageId;
        private String algorithm;

        public Long getImageId() { return imageId; }
        public void setImageId(Long imageId) { this.imageId = imageId; }
        public String getAlgorithm() { return algorithm; }
        public void setAlgorithm(String algorithm) { this.algorithm = algorithm; }
    }

    private String getFileExtension(String filename) {
        if (filename == null || !filename.contains(".")) return "jpeg";
        String ext = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
        if (ext.equals("jpg")) return "jpeg";
        return ext;
    }
}