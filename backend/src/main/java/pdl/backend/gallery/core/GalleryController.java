package pdl.backend.gallery.core;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import pdl.backend.auth.UserAccount;
import pdl.backend.gallery.search.TagRepository;
import pdl.backend.vision.UploadStatusTracker;

@RestController
@RequestMapping("/images")
public class GalleryController {

  private static final Logger log = LoggerFactory.getLogger(GalleryController.class);

  private final FileStorageService storageService;
  private final TagRepository queryRepo;
  private final UploadStatusTracker statusNotifier;
  private final JdbcTemplate jdbcTemplate;

  public GalleryController(
    FileStorageService storageService,
    TagRepository queryRepo,
    UploadStatusTracker statusNotifier,
    JdbcTemplate jdbcTemplate
  ) {
    this.storageService = storageService;
    this.queryRepo = queryRepo;
    this.statusNotifier = statusNotifier;
    this.jdbcTemplate = jdbcTemplate;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Map<String, Object>> getImages(
    @RequestParam(value = "page", defaultValue = "0") int page,
    @RequestParam(value = "size", defaultValue = "30") int size,
    @RequestParam(value = "mine", defaultValue = "false") boolean mine
  ) {
    int offset = page * size;
    Long userId = getCurrentUserId();

    List<Map<String, Object>> content = queryRepo.getPaginatedGallery(userId, size, offset, mine);
    long totalElements = queryRepo.getGalleryTotalCount(userId, mine);
    boolean hasNext = (offset + size) < totalElements;

    Map<String, Object> response = new java.util.HashMap<>();
    response.put("content", content);
    response.put("totalElements", totalElements);
    response.put("hasNext", hasNext);

    return ResponseEntity.ok(response);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<?> getImage(@PathVariable("id") long id) {
    Optional<MediaRecord> image = storageService.getImageWithData(id);
    if (image.isEmpty() || image.get().getData() == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found");
    }

    return ResponseEntity.ok()
      .header(HttpHeaders.CONTENT_TYPE, "image/" + image.get().getFormat())
      .body(image.get().getData());
  }

  /**
   * Deletes an image.
   *
   * @param id the image id
   * @return response entity
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteImage(@PathVariable("id") final long id) {
    final Long userId = getCurrentUserId();
    if (userId == null) {
      throw new ResponseStatusException(
        HttpStatus.UNAUTHORIZED,
        "You must be logged in to delete images"
      );
    }

    final boolean isAdmin = isAdmin();

    final Optional<MediaRecord> imageOpt = storageService.getImageWithData(id);
    if (imageOpt.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found");
    }

    final MediaRecord image = imageOpt.get();
    final boolean notOwner = image.getUserId() == null || !image.getUserId().equals(userId);

    if (!isAdmin && notOwner) {
      throw new ResponseStatusException(
        HttpStatus.FORBIDDEN,
        "You do not have permission to delete this image"
      );
    }

    final boolean deleted = storageService.deleteImage(id);
    if (!deleted) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete image");
    }
    return ResponseEntity.noContent().build();
  }

  @PostMapping(
    consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<?> addImage(
    @RequestPart("file") MultipartFile file,
    @RequestParam(value = "keywords", required = false) List<String> keywords,
    HttpServletRequest request
  ) throws Exception {
    if (file.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File is empty");
    }

    Long userId = getCurrentUserId();
    boolean isAdmin = isAdmin();

    if (!isAdmin && userId != null) {
      Integer pendingCount = jdbcTemplate.queryForObject(
        "SELECT count(*) FROM images WHERE user_id = ? AND extraction_status IN ('PENDING', 'PROCESSING')",
        Integer.class,
        userId
      );
      if (pendingCount != null && pendingCount >= 10) {
        throw new ResponseStatusException(
          HttpStatus.TOO_MANY_REQUESTS,
          "You have reached the maximum allowed pending uploads (10). Please wait for them to finish processing."
        );
      }
    }

    MediaRecord image = new MediaRecord(file.getOriginalFilename(), file.getBytes());
    image.setUserId(userId);
    storageService.processAndSaveImage(image, true);
    long id = image.getId();

    if (keywords != null && !keywords.isEmpty()) {
      List<String> allTags = new ArrayList<>();
      for (String k : keywords) {
        String[] splits = k.split(",");
        for (String tag : splits) {
          allTags.add(tag.trim());
        }
      }
      queryRepo.addKeywords(id, allTags);
    }

    return ResponseEntity.accepted().body(Map.of("id", id));
  }

  @GetMapping(value = "/{id}/status", produces = MediaType.APPLICATION_JSON_VALUE)
  public Object getImageStatus(@PathVariable("id") long id) {
    String status = queryRepo.getStatus(id);
    if (status == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found");

    if ("COMPLETED".equals(status) || "FAILED".equals(status)) {
      return ResponseEntity.ok().body(Map.of("id", id, "extraction_status", status));
    }

    return statusNotifier.waitFor(id, 10000L);
  }

  /**
   * Gets image metadata.
   *
   * @param id the image id
   * @return response entity with metadata
   */
  @GetMapping("/{id}/metadata")
  public ResponseEntity<?> getMetadata(@PathVariable("id") final long id) {
    try {
      final Map<String, Object> rawMeta = queryRepo.getImageMetadata(id);
      final Map<String, Object> response = new HashMap<>();
      response.put("Name", rawMeta.get("name"));
      response.put("format", "image/" + rawMeta.get("format"));
      response.put("width", rawMeta.get("width"));
      response.put("height", rawMeta.get("height"));
      response.put("Keywords", rawMeta.get("Keywords"));
      response.put("extraction_status", rawMeta.get("extraction_status"));
      response.put("description", rawMeta.get("description"));
      response.put("photographer_name", rawMeta.get("photographer_name"));
      response.put("camera_make", rawMeta.get("camera_make"));
      response.put("location_country", rawMeta.get("location_country"));
      response.put("stats_downloads", rawMeta.get("stats_downloads"));
      response.put("user_id", rawMeta.get("user_id"));

      return ResponseEntity.ok().body(response);
    } catch (Exception e) {
      log.error("Error retrieving metadata for image id: {}", id, e);
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found", e);
    }
  }

  @GetMapping(value = "/{id}/download")
  public ResponseEntity<org.springframework.core.io.InputStreamResource> downloadImage(
    @PathVariable("id") long id
  ) {
    Map<String, Object> metadata;
    try {
      metadata = queryRepo.getImageMetadata(id);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image metadata not found");
    }

    String filename = (String) metadata.get("name");
    String format = (String) metadata.get("format");

    try {
      // Get the stream from S3
      var s3Stream = storageService.streamImageFromS3(id, filename);

      return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
        .header(HttpHeaders.CONTENT_TYPE, "image/" + format)
        .body(new org.springframework.core.io.InputStreamResource(s3Stream));
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image data not found in storage");
    }
  }

  private Long getCurrentUserId() {
    org.springframework.security.core.Authentication authentication =
      org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null) {
      if (
        authentication.getPrincipal() instanceof org.springframework.security.oauth2.jwt.Jwt jwt
      ) {
        return jwt.getClaim("userId");
      } else if (authentication.getPrincipal() instanceof UserAccount user) {
        return user.getId();
      }
    }
    return null;
  }

  private boolean isAdmin() {
    org.springframework.security.core.Authentication auth =
      org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
    return (
      auth != null &&
      auth
        .getAuthorities()
        .stream()
        .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))
    );
  }
}
