package pdl.backend.vision;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

@Service
public class UploadStatusTracker {

  private final Map<Long, DeferredResult<ResponseEntity<Map<String, Object>>>> requests =
    new ConcurrentHashMap<>();

  public DeferredResult<ResponseEntity<Map<String, Object>>> waitFor(
    Long imageId,
    Long timeoutInMilliseconds
  ) {
    DeferredResult<ResponseEntity<Map<String, Object>>> result = new DeferredResult<>(
      timeoutInMilliseconds
    );

    result.onTimeout(() -> {
      requests.remove(imageId);
      result.setResult(
        ResponseEntity.accepted().body(Map.of("id", imageId, "extraction_status", "PENDING"))
      );
    });

    result.onCompletion(() -> requests.remove(imageId));
    requests.put(imageId, result);
    return result;
  }

  public void notify(Long imageId, String finalStatus) {
    DeferredResult<ResponseEntity<Map<String, Object>>> result = requests.remove(imageId);
    if (result != null) {
      result.setResult(ResponseEntity.ok(Map.of("id", imageId, "extraction_status", finalStatus)));
    }
  }
}
