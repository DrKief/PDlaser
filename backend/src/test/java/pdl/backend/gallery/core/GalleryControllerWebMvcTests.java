package pdl.backend.gallery.core;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import pdl.backend.auth.UserRepository;
import pdl.backend.gallery.search.TagRepository;
import pdl.backend.vision.UploadStatusTracker;

@WebMvcTest(GalleryController.class)
class GalleryControllerWebMvcTests {

  @Autowired
  MockMvc mockMvc;

  @MockitoBean
  FileStorageService storageService;

  @MockitoBean
  TagRepository tagRepository;

  @MockitoBean
  UploadStatusTracker uploadStatusTracker;

  @MockitoBean
  UserRepository userRepository;

  @MockitoBean
  PasswordEncoder passwordEncoder;

  @Test
  void getImageReturns404WhenNotFound() throws Exception {
    when(storageService.getImageWithData(42L)).thenReturn(Optional.empty());

    mockMvc.perform(get("/images/{id}", 42L)).andExpect(status().isNotFound());
  }
}
