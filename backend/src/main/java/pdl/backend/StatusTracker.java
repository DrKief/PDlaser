package pdl.backend;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * Service to manage long-polling requests from the frontend.
 * Allows the frontend to wait for asynchronous processing to finish without spamming the server.
 */
@Service
public class StatusTracker {

  // Thread-safe map holding the pending client connections
  private final Map<Long, DeferredResult<ResponseEntity<Map<String, Object>>>> requests =
    new ConcurrentHashMap<>();

  /**
   * Registers a client connection to wait for a specific image ID's processing to complete.
   * 
   * @param imageId The ID of the image being processed.
   * @param timeoutInMilliseconds Time before the server forces a timeout response.
   * @return DeferredResult to hold open the HTTP connection.
   */
  public DeferredResult<ResponseEntity<Map<String, Object>>> waitFor(
    Long imageId,
    Long timeoutInMilliseconds
  ) {
    DeferredResult<ResponseEntity<Map<String, Object>>> result = new DeferredResult<>(
      timeoutInMilliseconds
    );

    // If the timeout is reached before processing completes, return 202 Accepted (Still pending)
    result.onTimeout(() -> {
      requests.remove(imageId);
      result.setResult(
        ResponseEntity.accepted().body(Map.of("id", imageId, "extraction_status", "PENDING"))
      );
    });

    // Cleanup upon completion
    result.onCompletion(() -> requests.remove(imageId));

    requests.put(imageId, result);
    return result;
  }

  /**
   * Called by BackgroundExtractionWorker when an image finishes processing.
   * Fulfills the DeferredResult, immediately answering the waiting HTTP request.
   * 
   * @param imageId The ID of the image.
   * @param finalStatus The resulting status (COMPLETED or FAILED).
   */
  public void notify(Long imageId, String finalStatus) {
    DeferredResult<ResponseEntity<Map<String, Object>>> result = requests.remove(imageId);
    if (result != null) {
      result.setResult(ResponseEntity.ok(Map.of("id", imageId, "extraction_status", finalStatus)));
    }
  }
}