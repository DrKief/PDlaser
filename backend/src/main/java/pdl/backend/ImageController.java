package pdl.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.beans.factory.annotation.Value;

import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/images")
public class ImageController {

    private static final Logger log = LoggerFactory.getLogger(ImageController.class);

    @Value("${app.image.directory:images}")
    private String imageDirectoryPath;

    private final ImageDao imageDao;

    public ImageController(ImageDao imageDao) {
        this.imageDao = imageDao;
    }

    @PostConstruct
    public void verifyStartupState() {
        Path path = Paths.get(imageDirectoryPath);
        if (!Files.exists(path) || !Files.isDirectory(path)) {
            log.error("FATAL: Required 'images' directory not found at {}", path.toAbsolutePath());
            System.err.println("Application cannot proceed without the primary data source. Exiting.");
            System.exit(1);
        }
    }

    // Explictly handles when users exceed the 100MB upload limit set in application.yaml
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<Map<String, String>> handleMaxSizeException(MaxUploadSizeExceededException exc) {
        log.warn("Upload rejected: File size exceeded the 100MB limit.");
        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Map.of("error", "File too large. Maximum allowed size is 100MB."));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Image>> getImages() {
        return ResponseEntity.ok(imageDao.retrieveAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getImage(@PathVariable("id") long id) {
        Optional<Image> image = imageDao.retrieve(id);
        if (image.isPresent()) {
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("image/" + getFileExtension(image.get().getName())))
                    .body(image.get().getData());
        }
        log.info("Requested image ID {} not found.", id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Map.of("error", "Image not found"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable("id") long id) {
        Optional<Image> image = imageDao.retrieve(id);
        if (image.isPresent()) {
            imageDao.delete(image.get());
            log.info("Deleted image ID {}", id);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Map.of("message", "Image deleted"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Map.of("error", "Image not found"));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Map.of("error", "File payload is empty"));
        }
        
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Map.of("error", "Invalid media type. Only image/* is allowed"));
        }

        try {
            Image image = new Image(file.getOriginalFilename(), file.getBytes());
            imageDao.create(image);
            log.info("Successfully uploaded image: {}", file.getOriginalFilename());
            
            return ResponseEntity.status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Map.of("message", "Image uploaded successfully"));
                    
        } catch (IOException e) {
            log.error("I/O error reading bytes from uploaded file: {}", file.getOriginalFilename(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Map.of("error", "Failed to process image payload"));
        } catch (Exception e) {
            log.error("Unexpected error during image upload", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Map.of("error", "Internal server error during upload"));
        }
    }

    @GetMapping("/{id}/metadata")
    public ResponseEntity<?> getMetadata(@PathVariable("id") long id) {
        try {
            Map<String, Object> metadata = imageDao.getImageMetadata(id);
            String sizeFormat = metadata.get("width") + "*" + metadata.get("height");
            return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Map.of(
                    "id", id,
                    "format", metadata.get("format"),
                    "size", sizeFormat
                ));
        } catch (EmptyResultDataAccessException e) {
            log.info("Metadata requested for non-existent image ID: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Map.of("error", "Metadata not found for the requested image"));
        } catch (Exception e) {
            log.error("Database error retrieving metadata for image ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Map.of("error", "Internal database error while fetching metadata"));
        }
    }

    @GetMapping(value = "/search", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> searchImagesWithAttributes(@RequestBody SearchCriteria criteria) {
        if (criteria == null || criteria.getImageId() == null) {
            return ResponseEntity.badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Map.of("error", "Request body with valid 'imageId' is required for search."));
        }
        
        try {
            String algorithm = criteria.getAlgorithm() != null ? criteria.getAlgorithm() : "gradient";
            List<Map<String, Object>> results = imageDao.findSimilar(criteria.getImageId(), algorithm, 10);
            return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(results);
        } catch (EmptyResultDataAccessException e) {
            log.info("Vector search initiated with non-existent target image ID: {}", criteria.getImageId());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Map.of("error", "Target image for search not found in the database"));
        } catch (Exception e) {
            log.error("Error executing pgvector search for image ID: {}", criteria.getImageId(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Map.of("error", "Error executing vector similarity search"));
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