package pdl.backend.gallery.core;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import pdl.backend.auth.UserAccount;
import pdl.backend.gallery.search.TagRepository;
import pdl.backend.vision.UploadStatusTracker;

@RestController
@RequestMapping("/images")
public class GalleryController {

  private final FileStorageService storageService;
  private final TagRepository queryRepo;
  private final UploadStatusTracker statusNotifier;

  public GalleryController(
    FileStorageService storageService,
    TagRepository queryRepo,
    UploadStatusTracker statusNotifier
  ) {
    this.storageService = storageService;
    this.queryRepo = queryRepo;
    this.statusNotifier = statusNotifier;
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

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteImage(@PathVariable("id") long id) {
    boolean deleted = storageService.deleteImage(id);
    if (!deleted) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found");
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

    MediaRecord image = new MediaRecord(file.getOriginalFilename(), file.getBytes());
    image.setUserId(getCurrentUserId());
    storageService.processAndSaveImage(image, true);
    long id = image.getId();

    if (keywords != null && !keywords.isEmpty()) {
      for (String k : keywords) {
        String[] splits = k.split(",");
        for (String tag : splits) {
          queryRepo.addKeyword(id, tag.trim());
        }
      }
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

  @GetMapping("/{id}/metadata")
  public ResponseEntity<?> getMetadata(@PathVariable("id") long id) {
    try {
      Map<String, Object> rawMeta = queryRepo.getImageMetadata(id);
      Map<String, Object> response = new HashMap<>();
      response.put("Name", rawMeta.get("Name"));
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

      return ResponseEntity.ok().body(response);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found");
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
}
