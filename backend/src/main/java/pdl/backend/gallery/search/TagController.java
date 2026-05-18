package pdl.backend.gallery.search;

import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pdl.backend.auth.UserAccount;

@RestController
@RequestMapping("/images")
public class TagController {

  private final TagRepository queryRepo;

  public TagController(TagRepository queryRepo) {
    this.queryRepo = queryRepo;
  }

  @PutMapping("/{id}/keywords")
  public ResponseEntity<?> addKeyword(
    @PathVariable("id") long id,
    @RequestParam("tag") String tag
  ) {
    if (!queryRepo.addKeyword(id, tag)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}/keywords")
  public ResponseEntity<?> deleteKeyword(
    @PathVariable("id") long id,
    @RequestParam("tag") String tag
  ) {
    try {
      queryRepo.getImageMetadata(id);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    if (!queryRepo.hasKeyword(id, tag)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    queryRepo.deleteKeyword(id, tag);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/keywords")
  public ResponseEntity<?> getAllKeywords() {
    return ResponseEntity.ok(queryRepo.getAllKeywords(getCurrentUserId()));
  }

  @GetMapping("/keywords/search")
  public ResponseEntity<?> searchKeywords(@RequestParam("q") String query) {
    if (query == null || query.length() < 2) return ResponseEntity.ok(List.of());
    return ResponseEntity.ok(queryRepo.searchKeywords(query, getCurrentUserId()));
  }

  @GetMapping("/keywords/popular")
  public ResponseEntity<List<String>> getPopularKeywords(
    @RequestParam(defaultValue = "15") int limit
  ) {
    return ResponseEntity.ok(queryRepo.getPopularKeywords(limit));
  }

  @GetMapping("/search")
  public ResponseEntity<Map<String, Object>> searchImagesByTags(
    @RequestParam(required = false) List<String> keywords,
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "30") int size,
    @RequestParam(value = "mine", defaultValue = "false") boolean mine
  ) {
    Long userId = getCurrentUserId();
    int offset = page * size;

    List<Map<String, Object>> content = queryRepo.searchGalleryByTags(
      keywords,
      userId,
      size,
      offset,
      mine
    );
    long totalElements = queryRepo.getSearchGalleryByTagsTotalCount(keywords, userId, mine);
    boolean hasNext = (offset + size) < totalElements;

    Map<String, Object> response = new java.util.HashMap<>();
    response.put("content", content);
    response.put("totalElements", totalElements);
    response.put("hasNext", hasNext);

    return ResponseEntity.ok(response);
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
