package pdl.backend.gallery.search;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/images")
public class ImageSearchEndpointLayer {

  private final VectorSimilarityRepoLayer similarityRepo;

  public ImageSearchEndpointLayer(VectorSimilarityRepoLayer similarityRepo) {
    this.similarityRepo = similarityRepo;
  }

  @GetMapping("/{id}/similar")
  public ResponseEntity<?> getSimilarImages(
    @PathVariable("id") long id,
    @RequestParam(value = "number", defaultValue = "10") int number,
    @RequestParam(value = "descriptor", defaultValue = "weighted") String descriptor
  ) {
    List<String> validDescriptors = List.of("gradient", "saturation", "rgb", "cielab", "weighted");
    if (!validDescriptors.contains(descriptor.toLowerCase())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid descriptor.");
    }

    List<Map<String, Object>> results = similarityRepo.findSimilar(id, descriptor, number);
    if (results == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found");
    }
    return ResponseEntity.ok(results);
  }
}