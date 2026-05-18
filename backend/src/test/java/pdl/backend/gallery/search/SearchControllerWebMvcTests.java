package pdl.backend.gallery.search;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import pdl.backend.auth.UserRepository;
import pdl.backend.vision.VisionProcessor;

@WebMvcTest(SearchController.class)
class SearchControllerWebMvcTests {

  @Autowired
  MockMvc mockMvc;

  @MockitoBean
  SimilarityRepository similarityRepository;

  @MockitoBean
  TagRepository tagRepository;

  @MockitoBean
  VisionProcessor visionProcessor;

  // NEW – needed so Application.dataSeeder() can be created
  @MockitoBean
  UserRepository userRepository;

  @MockitoBean
  PasswordEncoder passwordEncoder;

  @Test
  void similarWithUnknownDescriptorReturnsBadRequest() throws Exception {
    mockMvc
      .perform(get("/images/{id}/similar", 1L).param("descriptor", "unknown"))
      .andExpect(status().isBadRequest());
  }

  @Test
  void ephemeralSearchEmptyFileReturnsBadRequest() throws Exception {
    mockMvc
      .perform(
        multipart("/images/search/ephemeral")
          .file("file", new byte[0])
          .param("descriptor", "semantic")
      )
      .andExpect(status().isBadRequest());
  }
}
