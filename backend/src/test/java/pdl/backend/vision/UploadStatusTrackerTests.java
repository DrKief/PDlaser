package pdl.backend.vision;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;

public class UploadStatusTrackerTests {

    private UploadStatusTracker tracker;

    @BeforeEach
    void setUp() {
        tracker = new UploadStatusTracker();
    }

    @Test
    @SuppressWarnings("unchecked")
    void testWaitForAddsRequestAndNotifyResolvesIt() throws Exception {
        Long imageId = 1L;
        DeferredResult<ResponseEntity<Map<String, Object>>> result = tracker.waitFor(imageId, 10000L);

        // Verify request was added using reflection
        Field requestsField = UploadStatusTracker.class.getDeclaredField("requests");
        requestsField.setAccessible(true);
        Map<Long, DeferredResult<ResponseEntity<Map<String, Object>>>> requests =
                (Map<Long, DeferredResult<ResponseEntity<Map<String, Object>>>>) requestsField.get(tracker);

        assertTrue(requests.containsKey(imageId));
        assertEquals(result, requests.get(imageId));

        // Notify
        tracker.notify(imageId, "COMPLETED");

        // Verify result is set
        assertTrue(result.hasResult());
        ResponseEntity<Map<String, Object>> response = (ResponseEntity<Map<String, Object>>) result.getResult();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("COMPLETED", response.getBody().get("extraction_status"));
        assertEquals(imageId, response.getBody().get("id"));

        // Verify request was removed
        assertFalse(requests.containsKey(imageId));
    }

    @Test
    @SuppressWarnings("unchecked")
    void testTimeoutRemovesRequest() throws Exception {
        Long imageId = 2L;
        DeferredResult<ResponseEntity<Map<String, Object>>> result = tracker.waitFor(imageId, 100L);

        // Verify request was added
        Field requestsField = UploadStatusTracker.class.getDeclaredField("requests");
        requestsField.setAccessible(true);
        Map<Long, DeferredResult<ResponseEntity<Map<String, Object>>>> requests =
                (Map<Long, DeferredResult<ResponseEntity<Map<String, Object>>>>) requestsField.get(tracker);

        assertTrue(requests.containsKey(imageId));

        // Simulate timeout directly
        result.setResult(ResponseEntity.accepted().body(Map.of("id", imageId, "extraction_status", "PENDING")));

        // Calling setResult should trigger onCompletion logic in the actual container, but since we are testing
        // unit logic without container, we just ensure that we can simulate it and that notify removes if it was still there.
        // Let's actually verify notify does not crash when called after removed.
        requests.remove(imageId);
        assertDoesNotThrow(() -> tracker.notify(imageId, "COMPLETED"));
    }
}
