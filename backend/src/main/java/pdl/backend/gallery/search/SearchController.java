package pdl.backend.gallery.search;

import com.pgvector.PGvector;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
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
    List<String> validDescriptors = List.of(
      "gradient",
      "saturation",
      "rgb",
      "cielab",
      "weighted",
      "semantic"
    );
    if (!validDescriptors.contains(descriptor.toLowerCase())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid descriptor.");
    }

    List<Map<String, Object>> results = similarityRepo.findSimilar(id, descriptor, number);
    if (results == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found");
    }
    return ResponseEntity.ok(results);
  }

  @PostMapping("/search/batch")
  public ResponseEntity<?> searchByMultipleIds(
    @RequestBody List<Long> ids,
    @RequestParam(value = "number", defaultValue = "10") int number,
    @RequestParam(value = "descriptor", defaultValue = "semantic") String descriptor
  ) {
    if (ids == null || ids.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "At least one image ID is required");
    }

    if (ids.size() > 5) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Maximum 5 image IDs allowed");
    }

    List<String> validDescriptors = List.of(
      "gradient",
      "saturation",
      "rgb",
      "cielab",
      "semantic"
    );
    String descriptorLower = descriptor.toLowerCase();
    if (!validDescriptors.contains(descriptorLower)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid descriptor");
    }

    try {
      List<float[]> allVectors = similarityRepo.getVectorsForIds(ids, descriptorLower);
      
      for (float[] v : allVectors) {
        if (v == null) {
          throw new ResponseStatusException(HttpStatus.NOT_FOUND, "One or more images not found or not processed");
        }
      }

      float[] aggregatedVector = VectorAggregator.aggregate(allVectors);
      PGvector pgVector = new PGvector(aggregatedVector);
      
      List<Map<String, Object>> results = similarityRepo.findSimilarByVectorExcluding(
        pgVector,
        descriptor,
        number,
        ids
      );

      return ResponseEntity.ok(results);
    } catch (ResponseStatusException e) {
      throw e;
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to process images", e);
    }
  }

  @PostMapping("/search/ephemeral")
  public ResponseEntity<?> searchByEphemeralUpload(
    @RequestPart("files") List<MultipartFile> files,
    @RequestParam(value = "number", defaultValue = "10") int number,
    @RequestParam(value = "descriptor", defaultValue = "semantic") String descriptor
  ) {
    if (files == null || files.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "At least one image is required");
    }

    if (files.size() > 5) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Maximum 5 images allowed");
    }

    for (MultipartFile file : files) {
      if (file.isEmpty() || file.getSize() == 0) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "One or more files are empty");
      }
    }

    try {
      List<String> validDescriptors = List.of(
        "gradient",
        "saturation",
        "rgb",
        "cielab",
        "semantic"
      );
      String descriptorLower = descriptor.toLowerCase();
      if (!validDescriptors.contains(descriptorLower)) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid descriptor");
      }

      List<float[]> allVectors = new ArrayList<>();
      for (MultipartFile file : files) {
        Map<String, float[]> vectors = visionProcessor.extractEphemeralVectors(file.getBytes());
        float[] targetVector = vectors.get(descriptorLower);
        if (targetVector == null) {
          throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid descriptor");
        }
        allVectors.add(targetVector);
      }

      float[] aggregatedVector = VectorAggregator.aggregate(allVectors);

      PGvector pgVector = new PGvector(aggregatedVector);
      List<Map<String, Object>> results = similarityRepo.findSimilarByVector(
        pgVector,
        descriptor,
        number
      );

      return ResponseEntity.ok(results);
    } catch (ResponseStatusException e) {
      throw e;
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to process image", e);
    }
  }
}
