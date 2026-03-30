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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Primary REST Controller routing incoming `/images` API endpoints.
 * Handles uploads, downloads, searches, metadata retrieval, and similarity requests.
 */
@RestController
@RequestMapping("/images")
public class ApiController {

  private static final Logger log = LoggerFactory.getLogger(ApiController.class);

  @Value("${app.image.directory:images}")
  private String imageDirectoryPath;

  private final MetadataRepository imageEntityRepository;
  private final VectorRepository imageDescriptorRepository;
  private final StorageService imageLifecycleService;
  private final StatusTracker statusNotifier;

  public ApiController(
    MetadataRepository imageEntityRepository,
    VectorRepository imageDescriptorRepository,
    StorageService imageLifecycleService,
    StatusTracker statusNotifier
  ) {
    this.imageEntityRepository = imageEntityRepository;
    this.imageDescriptorRepository = imageDescriptorRepository;
    this.imageLifecycleService = imageLifecycleService;
    this.statusNotifier = statusNotifier;
  }

  /**
   * Health-check run at application startup.
   * Ensures the storage directory is accessible, otherwise halts the app.
   */
  @PostConstruct
  public void verifyStartupState() {
    Path path = Paths.get(imageDirectoryPath);
    if (!Files.exists(path) || !Files.isDirectory(path)) {
      log.error("FATAL: Required 'images' directory not found at {}", path.toAbsolutePath());
      throw new IllegalStateException(
        "Besoin 1: Le dossier images n'existe pas ou n'est pas un dossier valide."
      );
    }

    if (!Files.isWritable(path)) {
      log.error("FATAL: Required 'images' directory is not writable. Path: {}", path.toAbsolutePath());
      throw new IllegalStateException("directory not writable");
    }
  }

  /**
   * Retrieve a list of all uploaded images (basic info).
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Map<String, Object>>> getImages() {
    Iterable<Metadata> images = imageEntityRepository.findAll();
    List<Map<String, Object>> response = new java.util.ArrayList<>();
    for (Metadata img : images) {
      Map<String, Object> item = new HashMap<>();
      item.put("id", img.getId());
      item.put("name", img.getName());
      item.put("keywords", imageDescriptorRepository.getKeywords(img.getId()));
      response.add(item);
    }
    return ResponseEntity.ok(response);
  }

  /**
   * Retrieve the raw binary data of an image for rendering in the browser.
   */
  @GetMapping(value = "/{id}")
  public ResponseEntity<?> getImage(@PathVariable("id") long id) {
    Optional<Metadata> image = imageLifecycleService.getImageWithData(id);
    if (image.isEmpty() || image.get().getData() == null) {
      throw new ErrorHandler.RecordNotFoundException("Image not found");
    }

    return ResponseEntity.ok()
      .header(HttpHeaders.CONTENT_TYPE, "image/" + image.get().getFormat())
      .body(image.get().getData());
  }

  /**
   * Delete an image from both the file system and database.
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteImage(@PathVariable("id") long id) {
    boolean deleted = imageLifecycleService.deleteImage(id);
    if (!deleted) {
      throw new ErrorHandler.RecordNotFoundException("Image not found");
    }
    return ResponseEntity.noContent().build();
  }

  /**
   * Upload a new image. Triggers asynchronous background processing.
   */
  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<?> addImage(
    @RequestParam("file") MultipartFile file,
    @RequestParam(value = "keywords", required = false) List<String> keywords
  ) throws Exception {
    if (file.isEmpty()) {
      throw new ErrorHandler.BadRequestException("File is empty");
    }

    String contentType = file.getContentType();
    if (contentType == null || !contentType.startsWith("image/")) {
      throw new ErrorHandler.UnsupportedFileException("Unsupported Media Type");
    }

    Metadata image = new Metadata(file.getOriginalFilename(), file.getBytes());
    imageLifecycleService.processAndSaveImage(image, true);
    long id = image.getId();

    // Attach keywords if provided during upload
    if (keywords != null && !keywords.isEmpty()) {
      for (String k : keywords) {
        String[] splits = k.split(",");
        for (String tag : splits) {
          imageDescriptorRepository.addKeyword(id, tag.trim());
        }
      }
    }

    return ResponseEntity.status(HttpStatus.ACCEPTED)
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .body(
        Map.of(
          "message", "Image accepted for background processing.",
          "id", id,
          "status_url", "/images/" + id + "/status"
        )
      );
  }

  /**
   * Endpoint for the frontend to poll (Long-Polling) the processing status of an image.
   */
  @GetMapping(value = "/{id}/status", produces = MediaType.APPLICATION_JSON_VALUE)
  public Object getImageStatus(@PathVariable("id") long id) {
    String status = imageDescriptorRepository.getStatus(id);
    if (status == null) {
      throw new ErrorHandler.RecordNotFoundException("Image not found");
    }

    // If done, return immediately
    if ("COMPLETED".equals(status) || "FAILED".equals(status)) {
      return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .body(Map.of("id", id, "extraction_status", status));
    }

    // Otherwise, wait up to 10 seconds for the async thread to finish
    return statusNotifier.waitFor(id, 10000L);
  }

  /**
   * Retrieve full metadata (dimensions, format, keywords, status) for an image.
   */
  @GetMapping("/{id}/metadata")
  public ResponseEntity<?> getMetadata(@PathVariable("id") long id) {
    Map<String, Object> rawMeta = imageDescriptorRepository.getImageMetadata(id);

    Map<String, Object> response = new HashMap<>();
    response.put("Name", rawMeta.get("Name"));
    response.put("Type", "image/" + rawMeta.get("format"));
    response.put("Size", rawMeta.get("width") + "*" + rawMeta.get("height"));
    response.put("Keywords", rawMeta.get("Keywords"));
    response.put("Extraction_Status", rawMeta.get("extraction_status"));

    return ResponseEntity.ok()
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .body(response);
  }

  /**
   * Find highly similar images using PGvector capabilities.
   */
  @GetMapping("/{id}/similar")
  public ResponseEntity<?> getSimilarImages(
    @PathVariable("id") long id,
    @RequestParam(value = "number", defaultValue = "10") int number,
    @RequestParam(value = "descriptor", defaultValue = "rgb") String descriptor,
    @RequestParam(value = "weights", required = false) Double threshold
  ) {
    List<String> validDescriptors = List.of("gradient", "saturation", "rgb", "cielab", "aggregate");
    if (!validDescriptors.contains(descriptor.toLowerCase())) {
      throw new ErrorHandler.BadRequestException("Bad Request. Invalid descriptor.");
    }

    List<Map<String, Object>> results = imageDescriptorRepository.findSimilar(id, descriptor, number);

    if (results == null) {
      throw new ErrorHandler.RecordNotFoundException("Image not found");
    }

    return ResponseEntity.ok()
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .body(results);
  }

  /**
   * Add a keyword tag to a specific image.
   */
  @PutMapping("/{id}/keywords")
  public ResponseEntity<?> addKeyword(
    @PathVariable("id") long id,
    @RequestParam("tag") String tag
  ) {
    boolean success = imageDescriptorRepository.addKeyword(id, tag);
    if (!success) {
      throw new ErrorHandler.RecordNotFoundException("Image not found");
    }
    return ResponseEntity.noContent().build();
  }

  /**
   * Delete a keyword tag from a specific image.
   */
  @DeleteMapping("/{id}/keywords")
  public ResponseEntity<?> deleteKeyword(
    @PathVariable("id") long id,
    @RequestParam("tag") String tag
  ) {
    imageDescriptorRepository.getImageMetadata(id); // Throws if image not found

    if (!imageDescriptorRepository.hasKeyword(id, tag)) {
      throw new ErrorHandler.BadRequestException("Tag not associated with this image");
    }

    imageDescriptorRepository.deleteKeyword(id, tag);
    return ResponseEntity.noContent().build();
  }

  /**
   * Retrieve all unique keywords across the entire application.
   */
  @GetMapping("/keywords")
  public ResponseEntity<?> getAllKeywords() {
    return ResponseEntity.ok()
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .body(imageDescriptorRepository.getAllKeywords());
  }

  /**
   * Search database for images matching specific attributes (name, format, size, keywords).
   */
  @GetMapping("/search")
  public ResponseEntity<?> searchImagesByAttributes(
    @RequestParam(required = false) String name,
    @RequestParam(required = false) String format,
    @RequestParam(required = false) String size,
    @RequestParam(required = false) List<String> keywords
  ) {
    List<Long> ids = imageDescriptorRepository.searchByAttributes(name, format, size, keywords);
    if (ids.isEmpty()) {
      throw new ErrorHandler.RecordNotFoundException("Aucune image existante trouvée.");
    }

    return ResponseEntity.ok()
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .body(ids);
  }
}