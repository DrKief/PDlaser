package pdl.backend;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Unit Tests for the ApiController class utilizing MockMvc.
 * Mocks out the service layers to purely test HTTP request/response mappings,
 * status codes, and exception handler bindings.
 */
@WebMvcTest(ApiController.class)
@Import(ErrorHandler.class)
public class ApiControllerTests {

  @MockitoBean
  private MetadataRepository imageEntityRepository;

  @MockitoBean
  private VectorRepository imageDescriptorRepository;

  @MockitoBean
  private StorageService imageLifecycleService;

  @MockitoBean
  private StatusTracker statusNotifier;

  @Autowired
  private MockMvc mockMvc;

  @BeforeEach
  public void setUp() {
    // Reset mocks before every test to ensure isolated execution state
    reset(imageEntityRepository, imageDescriptorRepository, imageLifecycleService, statusNotifier);
  }

  /**
   * Tests that fetching an existing image returns a 200 OK and valid image data.
   */
  @Test
  public void getImageShouldReturnSuccess() throws Exception {
    Metadata image = new Metadata("test.jpg", new byte[0]);
    image.setFormat("jpeg");
    when(imageLifecycleService.getImageWithData(0)).thenReturn(Optional.of(image));

    this.mockMvc.perform(get("/images/0"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.IMAGE_JPEG_VALUE));

    verify(imageLifecycleService).getImageWithData(0);
  }

  /**
   * Tests that requesting an unknown image ID correctly returns a 404 Not Found.
   */
  @Test
  public void getImageShouldReturnNotFound() throws Exception {
    when(imageLifecycleService.getImageWithData(99)).thenReturn(Optional.empty());

    this.mockMvc.perform(get("/images/99")).andExpect(status().isNotFound());
  }

  /**
   * Tests that uploading a valid multipart file returns a 202 Accepted.
   */
  @Test
  public void addImageShouldReturnSuccess() throws Exception {
    MockMultipartFile file = new MockMultipartFile(
      "file",
      "test.jpg",
      MediaType.IMAGE_JPEG_VALUE,
      "content".getBytes()
    );
    this.mockMvc.perform(multipart("/images").file(file)).andExpect(status().isAccepted());

    // Verifies that the service was called correctly to process and save
    verify(imageLifecycleService).processAndSaveImage(any(Metadata.class), eq(true));
  }

  /**
   * Tests that uploading a non-image file type gets rejected with 415 Unsupported Media Type.
   */
  @Test
  public void addImageShouldReturnUnsupportedMediaType() throws Exception {
    MockMultipartFile file = new MockMultipartFile(
      "file",
      "test.txt",
      MediaType.TEXT_PLAIN_VALUE,
      "content".getBytes()
    );
    this.mockMvc.perform(multipart("/images").file(file)).andExpect(
      status().isUnsupportedMediaType()
    );
  }

  /**
   * Tests that requesting a deletion of an existing image returns 204 No Content.
   */
  @Test
  public void deleteImageShouldReturnSuccess() throws Exception {
    when(imageLifecycleService.deleteImage(0)).thenReturn(true);

    this.mockMvc.perform(delete("/images/0")).andExpect(status().isNoContent());
    verify(imageLifecycleService).deleteImage(0);
  }

  /**
   * Tests that requesting deletion of a non-existent image returns 404 Not Found.
   */
  @Test
  public void deleteImageShouldReturnNotFound() throws Exception {
    when(imageLifecycleService.deleteImage(99)).thenReturn(false);

    this.mockMvc.perform(delete("/images/99")).andExpect(status().isNotFound());
  }
}
