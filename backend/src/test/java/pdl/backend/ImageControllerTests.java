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
  private ImageRepository imageRepository;

  @MockitoBean
  private ImageDao imageDAO;

  @MockitoBean
  private ImageService imageService;

  @Autowired
  private MockMvc mockMvc;

  @BeforeEach
  public void setUp() {
    reset(imageRepository, imageDAO, imageService);
  }

  @Test
  public void getImageShouldReturnSuccess() throws Exception {
    Image image = new Image("test.jpg", new byte[0]);
    image.setFormat("jpeg");
    when(imageService.getImageWithData(0)).thenReturn(Optional.of(image));
    this.mockMvc.perform(get("/images/0"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.IMAGE_JPEG_VALUE));
    verify(imageService).getImageWithData(0);
  }

  @Test
  public void getImageShouldReturnNotFound() throws Exception {
    when(imageService.getImageWithData(99)).thenReturn(Optional.empty());
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
    when(imageService.deleteImage(0)).thenReturn(true);
    this.mockMvc.perform(delete("/images/0")).andExpect(status().isNoContent());
    verify(imageService).deleteImage(0);
  }

  @Test
  public void deleteImageShouldReturnNotFound() throws Exception {
    when(imageService.deleteImage(99)).thenReturn(false);
    this.mockMvc.perform(delete("/images/99")).andExpect(status().isNotFound());
  }
}
