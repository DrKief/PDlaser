package pdl.backend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/images")
public class ApiController {

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

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Map<String, Object>>> getImages(
      @RequestParam(value = "page", defaultValue = "0") int page,
      @RequestParam(value = "size", defaultValue = "30") int size
  ) {
      int offset = page * size;
      List<Map<String, Object>> response = imageDescriptorRepository.getPaginatedGallery(getCurrentUserId(), size, offset);
      return ResponseEntity.ok(response);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<?> getImage(@PathVariable("id") long id) {
    Optional<Metadata> image = imageLifecycleService.getImageWithData(id);
    if (image.isEmpty() || image.get().getData() == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found");
    }

    return ResponseEntity.ok()
      .header(HttpHeaders.CONTENT_TYPE, "image/" + image.get().getFormat())
      .body(image.get().getData());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteImage(@PathVariable("id") long id) {
    boolean deleted = imageLifecycleService.deleteImage(id);
    if (!deleted) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found");
    }
    return ResponseEntity.noContent().build();
  }

  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<?> addImage(
    @RequestParam("file") MultipartFile file,
    @RequestParam(value = "keywords", required = false) List<String> keywords
  ) throws Exception {

    Metadata image = new Metadata(file.getOriginalFilename(), file.getBytes());
    imageLifecycleService.processAndSaveImage(image, true);
    long id = image.getId();

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

  @GetMapping(value = "/{id}/status", produces = MediaType.APPLICATION_JSON_VALUE)
  public Object getImageStatus(@PathVariable("id") long id) {
    String status = imageDescriptorRepository.getStatus(id);
    if (status == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found");
    }

    if ("COMPLETED".equals(status) || "FAILED".equals(status)) {
      return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .body(Map.of("id", id, "extraction_status", status));
    }

    return statusNotifier.waitFor(id, 10000L);
  }

  @GetMapping("/{id}/metadata")
  public ResponseEntity<?> getMetadata(@PathVariable("id") long id) {
    try {
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
    } catch (Exception e) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found");
    }
  }

  @GetMapping("/{id}/similar")
  public ResponseEntity<?> getSimilarImages(
    @PathVariable("id") long id,
    @RequestParam(value = "number", defaultValue = "10") int number,
    @RequestParam(value = "descriptor", defaultValue = "rgb") String descriptor,
    @RequestParam(value = "weights", required = false) Double threshold
  ) {
    List<String> validDescriptors = List.of("gradient", "saturation", "rgb", "cielab", "aggregate");
    if (!validDescriptors.contains(descriptor.toLowerCase())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid descriptor.");
    }

    List<Map<String, Object>> results = imageDescriptorRepository.findSimilar(id, descriptor, number);

    if (results == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found");
    }

    return ResponseEntity.ok()
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .body(results);
  }

  @PutMapping("/{id}/keywords")
  public ResponseEntity<?> addKeyword(
    @PathVariable("id") long id,
    @RequestParam("tag") String tag
  ) {
    boolean success = imageDescriptorRepository.addKeyword(id, tag);
    if (!success) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found");
    }
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}/keywords")
  public ResponseEntity<?> deleteKeyword(
    @PathVariable("id") long id,
    @RequestParam("tag") String tag
  ) {
    try {
        imageDescriptorRepository.getImageMetadata(id);
    } catch (Exception e) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found");
    }

    if (!imageDescriptorRepository.hasKeyword(id, tag)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tag not associated with this image");
    }

    imageDescriptorRepository.deleteKeyword(id, tag);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/keywords")
  public ResponseEntity<?> getAllKeywords() {
    return ResponseEntity.ok()
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .body(imageDescriptorRepository.getAllKeywords());
  }

  @GetMapping("/keywords/search")
  public ResponseEntity<?> searchKeywords(@RequestParam("q") String query) {
      if(query == null || query.length() < 2) return ResponseEntity.ok(List.of());
      return ResponseEntity.ok(imageDescriptorRepository.searchKeywords(query, getCurrentUserId()));
  }

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

  private Long getCurrentUserId() {
      org.springframework.security.core.Authentication authentication = 
          org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
      
      if (authentication != null) {
          if (authentication.getPrincipal() instanceof org.springframework.security.oauth2.jwt.Jwt jwt) {
              return jwt.getClaim("userId");
          } else if (authentication.getPrincipal() instanceof User user) {
              return user.getId();
          }
      }
      return null;
  }
}