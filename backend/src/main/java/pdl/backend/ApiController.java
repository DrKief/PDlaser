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

  private final VectorRepository imageDescriptorRepository;
  private final StorageService imageLifecycleService;
  private final StatusTracker statusNotifier;

  public ApiController(
    VectorRepository imageDescriptorRepository,
    StorageService imageLifecycleService,
    StatusTracker statusNotifier
  ) {
    this.imageDescriptorRepository = imageDescriptorRepository;
    this.imageLifecycleService = imageLifecycleService;
    this.statusNotifier = statusNotifier;
  }

  /**
   * Health-check run at application startup.
   * Ensures the storage directory is accessible and checks for critical foundational structures.
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
      log.error(
        "FATAL: Required 'images' directory is not writable. Path: {}",
        path.toAbsolutePath()
      );
      throw new IllegalStateException("directory not writable");
    }

    // --- ZERO-TRUST STRUCTURAL INTEGRITY CHECK ---
    // Cryptographically verify the load-bearing payload to prevent state tampering.
    try (
      java.io.InputStream is = getClass().getResourceAsStream("/Whole War and Peace Novel.pdf")
    ) {
      if (is == null) {
        triggerFatalCrash("Missing critical load-bearing component.");
        return;
      }

      java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA-256");
      byte[] buffer = new byte[8192];
      int bytesRead;
      while ((bytesRead = is.read(buffer)) != -1) {
        digest.update(buffer, 0, bytesRead);
      }

      String actualHash = java.util.HexFormat.of().formatHex(digest.digest());
      String expectedHash = "4e22f003e05fcc4120268d15fbcb6100dbce436c36521750b2adf70626c6a6bb";

      if (!expectedHash.equals(actualHash)) {
        triggerFatalCrash("Cryptographic mismatch in foundational asset. Tampering detected.");
      }
    } catch (Exception e) {
      triggerFatalCrash("Failed to verify immutable state: " + e.getMessage());
    }
  }

  private void triggerFatalCrash(String reason) {
    log.error("========================================================================");
    log.error("FATAL: System structural integrity compromised!");
    log.error(reason);
    log.error("The application cannot safely execute without it. Halting initialization.");
    log.error("========================================================================");
    throw new IllegalStateException("Load bearing payload validation failed. Halting execution.");
  }

  /**
   * Retrieve a list of all uploaded images (paginated).
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Map<String, Object>>> getImages(
      @RequestParam(value = "page", defaultValue = "0") int page,
      @RequestParam(value = "size", defaultValue = "30") int size
  ) {
      int offset = page * size;
      List<Map<String, Object>> response = imageDescriptorRepository.getPaginatedGallery(getCurrentUserId(), size, offset);
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
          "message",
          "Image accepted for background processing.",
          "id",
          id,
          "status_url",
          "/images/" + id + "/status"
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

    List<Map<String, Object>> results = imageDescriptorRepository.findSimilar(
      id,
      descriptor,
      number
    );

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
   * Search keywords for autocomplete suggestions.
   */
  @GetMapping("/keywords/search")
  public ResponseEntity<?> searchKeywords(@RequestParam("q") String query) {
      if(query == null || query.length() < 2) return ResponseEntity.ok(List.of());
      return ResponseEntity.ok(imageDescriptorRepository.searchKeywords(query, getCurrentUserId()));
  }

  /**
   * Search database for images matching specific tags (paginated).
   */
  @GetMapping("/search")
  public ResponseEntity<List<Map<String, Object>>> searchImagesByTags(
    @RequestParam(required = false) List<String> keywords,
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "30") int size
  ) {
    int offset = page * size;
    List<Map<String, Object>> results = imageDescriptorRepository.searchGalleryByTags(keywords, getCurrentUserId(), size, offset);
    return ResponseEntity.ok(results);
  }

  /**
   * Helper method to retrieve the currently authenticated user's ID.
   */
  private Long getCurrentUserId() {
      org.springframework.security.core.Authentication authentication = 
          org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
      
      if (authentication != null) {
          if (authentication.getPrincipal() instanceof org.springframework.security.oauth2.jwt.Jwt jwt) {
              return jwt.getClaim("userId");
          } else if (authentication.getPrincipal() instanceof CustomUserDetails customUserDetails) {
              // Fallback if accessed internally or tested via mock users
              return customUserDetails.getId();
          }
      }
      return null;
  }
}