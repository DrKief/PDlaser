package pdl.backend;

import jakarta.annotation.PostConstruct;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

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

  // Besoin 1: Throw explicit error if dir is missing.
  @PostConstruct
  public void verifyStartupState() {
    Path path = Paths.get(imageDirectoryPath);
    if (!Files.exists(path) || !Files.isDirectory(path)) {
      log.error("FATAL: Required 'images' directory not found at {}", path.toAbsolutePath());
      throw new IllegalStateException(
          "Besoin 1: Le dossier images n'existe pas ou n'est pas un dossier valide.");
    }
  }

  @ExceptionHandler(MaxUploadSizeExceededException.class)
  public ResponseEntity<Map<String, String>> handleMaxSizeException(
      MaxUploadSizeExceededException exc) {
    return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .body(Map.of("error", "File too large."));
  }

  // Besoin 6: Get all images
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Image>> getImages() {
    return ResponseEntity.ok(imageDao.retrieveAll());
  }

  // Besoin 8: Get Image Data
  @GetMapping(value = "/{id}")
  public ResponseEntity<?> getImage(@PathVariable("id") long id) {
    Optional<Image> image = imageDao.retrieve(id);
    if (image.isPresent()) {
      return ResponseEntity.ok()
          .header(HttpHeaders.CONTENT_TYPE, "image/" + getFileExtension(image.get().getName()))
          .body(image.get().getData());
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .body(Map.of("error", "Image not found"));
  }

  // Besoin 9: Delete Image
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteImage(@PathVariable("id") long id) {
    Optional<Image> image = imageDao.retrieve(id);
    if (image.isPresent()) {
      imageDao.delete(image.get());
      return ResponseEntity.noContent().build(); // 204 No Content
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .body(Map.of("error", "Image not found"));
  }

  // Besoin 7: Add Image
  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<?> addImage(@RequestParam("file") MultipartFile file) {
    if (file.isEmpty()) {
      return ResponseEntity.badRequest()
          .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
          .body(Map.of("error", "File is empty"));
    }

    String contentType = file.getContentType();
    if (contentType == null || !contentType.startsWith("image/")) {
      return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
          .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
          .body(Map.of("error", "Unsupported Media Type"));
    }

    try {
      Image image = new Image(file.getOriginalFilename(), file.getBytes());
      imageDao.create(image);
      return ResponseEntity.status(HttpStatus.CREATED)
          .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
          .body(Map.of("message", "Image uploaded"));
    } catch (Exception e) {
      log.error("Failed to upload image: " + file.getOriginalFilename(), e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
          .body(Map.of("error", "Internal server error"));
    }
  }

  // Besoin 11: Get Metadata
  @GetMapping("/{id}/metadata")
  public ResponseEntity<?> getMetadata(@PathVariable("id") long id) {
    try {
      Map<String, Object> rawMeta = imageDao.getImageMetadata(id);

      Map<String, Object> response = new HashMap<>();
      response.put("Name", rawMeta.get("Name"));

      String ext = getFileExtension((String) rawMeta.get("Name"));
      response.put("Type", "image/" + ext); // Maps to MediaType format

      response.put("Size", rawMeta.get("width") + "*" + rawMeta.get("height"));
      response.put("Keywords", rawMeta.get("Keywords"));

      return ResponseEntity.ok()
          .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
          .body(response);
    } catch (EmptyResultDataAccessException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
          .body(Map.of("error", "Image not found"));
    }
  }

  // Besoin 10: Find Similar (Standardized to use Query Params)
  @GetMapping("/{id}/similar")
  public ResponseEntity<?> getSimilarImages(
      @PathVariable("id") long id,
      @RequestParam(value = "number", defaultValue = "10") int number,
      @RequestParam(value = "descriptor", defaultValue = "gradient") String descriptor) {

    List<String> validDescriptors = List.of("gradient", "saturation", "rgb");
    if (!validDescriptors.contains(descriptor.toLowerCase())) {
      return ResponseEntity.badRequest()
          .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
          .body(Map.of("error", "Bad Request. Invalid descriptor."));
    }

    List<Map<String, Object>> results = imageDao.findSimilar(id, descriptor, number);

    if (results == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
          .body(Map.of("error", "Image not found"));
    }

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .body(results);
  }

  // Besoin 12: Add Keyword
  @PutMapping("/{id}/keywords")
  public ResponseEntity<?> addKeyword(
      @PathVariable("id") long id, @RequestParam("tag") String tag) {
    boolean success = imageDao.addKeyword(id, tag);
    if (success) {
      return ResponseEntity.noContent().build(); // 204 No Content
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .body(Map.of("error", "Image not found"));
  }

  // Besoin 13: Delete Keyword
  @DeleteMapping("/{id}/keywords")
  public ResponseEntity<?> deleteKeyword(
      @PathVariable("id") long id, @RequestParam("tag") String tag) {
    try {
      imageDao.getImageMetadata(id);

      if (!imageDao.hasKeyword(id, tag)) {
        return ResponseEntity.badRequest()
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .body(Map.of("error", "Tag not associated with this image"));
      }

      imageDao.deleteKeyword(id, tag);
      return ResponseEntity.noContent().build(); // 204 No Content

    } catch (EmptyResultDataAccessException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
          .body(Map.of("error", "Image not found"));
    }
  }

  // Besoin 14: Get all keywords
  @GetMapping("/keywords")
  public ResponseEntity<?> getAllKeywords() {
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .body(imageDao.getAllKeywords());
  }

  // Besoin 15: Search by Attributes (Standardized to use Query Params instead of JSON Body)
  @GetMapping("/search")
  public ResponseEntity<?> searchImagesByAttributes(
      @RequestParam(required = false) String name,
      @RequestParam(required = false) String format,
      @RequestParam(required = false) String size,
      @RequestParam(required = false) List<String> keywords) {

    List<Long> ids = imageDao.searchByAttributes(name, format, size, keywords);
    if (ids.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
          .body(Map.of("error", "Aucune image existante trouvée.")); // 404
    }
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .body(ids);
  }

  private String getFileExtension(String filename) {
    if (filename == null || !filename.contains(".")) return "jpeg";
    String ext = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
    if (ext.equals("jpg")) return "jpeg";
    return ext;
  }
}
