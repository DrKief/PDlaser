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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ImageController.class)
public class ImageControllerTests {

  @MockitoBean
  private ImageDao imageDAO;

  @MockitoBean
  private ImageService imageService;

  @Autowired
  private MockMvc mockMvc;

  @BeforeEach
  public void setUp() {
    reset(imageDAO, imageService);
  }

  @Test
  public void getImageShouldReturnSuccess() throws Exception {
    Image image = new Image("test.jpg", new byte[0]);
    when(imageDAO.retrieve(0)).thenReturn(Optional.of(image));
    this.mockMvc.perform(get("/images/0"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.IMAGE_JPEG_VALUE));
    verify(imageDAO).retrieve(0);
  }

  @Test
  public void getImageShouldReturnNotFound() throws Exception {
    when(imageDAO.retrieve(99)).thenReturn(Optional.empty());
    this.mockMvc.perform(get("/images/99")).andExpect(status().isNotFound());
  }

  @Test
  public void addImageShouldReturnSuccess() throws Exception {
    MockMultipartFile file = new MockMultipartFile(
      "file",
      "test.jpg",
      MediaType.IMAGE_JPEG_VALUE,
      "content".getBytes()
    );
    // Modified expectation to match 202 Accepted Async Architecture
    this.mockMvc.perform(multipart("/images").file(file)).andExpect(status().isAccepted());
    verify(imageService).processAndSaveImage(any(Image.class), eq(true));
  }

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

  @Test
  public void deleteImageShouldReturnSuccess() throws Exception {
    Image image = new Image("test.jpg", new byte[0]);
    when(imageDAO.retrieve(0)).thenReturn(Optional.of(image));
    this.mockMvc.perform(delete("/images/0")).andExpect(status().isNoContent());
    verify(imageDAO).delete(any(Image.class));
  }

  @Test
  public void deleteImageShouldReturnNotFound() throws Exception {
    when(imageDAO.retrieve(99)).thenReturn(Optional.empty());
    this.mockMvc.perform(delete("/images/99")).andExpect(status().isNotFound());
  }
}