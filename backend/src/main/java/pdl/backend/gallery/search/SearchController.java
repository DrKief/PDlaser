package pdl.backend.gallery.search;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.pgvector.PGvector;
import pdl.backend.vision.VisionProcessor;

@RestController
@RequestMapping("/images")
public class SearchController {

  private final SimilarityRepository similarityRepo;
  private final VisionProcessor visionProcessor;

  public SearchController(SimilarityRepository similarityRepo, VisionProcessor visionProcessor) {
    this.similarityRepo = similarityRepo;
    this.visionProcessor = visionProcessor;
  }

  @GetMapping("/{id}/similar")
  public ResponseEntity<?> getSimilarImages(
    @PathVariable("id") long id,
    @RequestParam(value = "number", defaultValue = "10") int number,
    @RequestParam(value = "descriptor", defaultValue = "weighted") String descriptor
  ) {
    // Added "semantic" to allowed query types
    List<String> validDescriptors = List.of("gradient", "saturation", "rgb", "cielab", "weighted", "semantic");
    if (!validDescriptors.contains(descriptor.toLowerCase())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid descriptor.");
    }

    List<Map<String, Object>> results = similarityRepo.findSimilar(id, descriptor, number);
    if (results == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found");
    }
    return ResponseEntity.ok(results);
  }

  @PostMapping("/search/ephemeral")
  public ResponseEntity<?> searchByEphemeralUpload(
      @RequestPart("file") MultipartFile file,
      @RequestParam(value = "number", defaultValue = "10") int number,
      @RequestParam(value = "descriptor", defaultValue = "semantic") String descriptor
  ) {
      try {
          if (file.isEmpty()) {
              return ResponseEntity.badRequest().body(Map.of("error", "File is empty"));
          }

          // 1. Process the image directly in RAM
          Map<String, float[]> vectors = visionProcessor.extractEphemeralVectors(file.getBytes());
          
          // 2. Grab the requested algorithm's vector
          float[] targetArray = vectors.get(descriptor.toLowerCase());
          if (targetArray == null) {
              return ResponseEntity.badRequest().body(Map.of("error", "Invalid descriptor"));
          }

          // 3. Search the DB without inserting
          PGvector pgVector = new PGvector(targetArray);
          List<Map<String, Object>> results = similarityRepo.findSimilarByVector(pgVector, descriptor, number);
          
          return ResponseEntity.ok(results);
          
      } catch (Exception e) {
          return ResponseEntity.internalServerError().body(Map.of("error", "Failed to process image"));
      }
  }
}