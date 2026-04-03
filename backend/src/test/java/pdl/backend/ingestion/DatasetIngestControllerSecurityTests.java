package pdl.backend.ingestion;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import pdl.backend.auth.UserRepository;
import pdl.backend.config.SecurityConfig;

@WebMvcTest(DatasetIngestController.class)
@Import(SecurityConfig.class)
class DatasetIngestControllerSecurityTests {

  @Autowired
  MockMvc mockMvc;

  @MockitoBean
  UnsplashService unsplashService;

  @MockitoBean
  UserRepository userRepository;

  @Test
  void adminCanStartUploadSync() throws Exception {
    when(unsplashService.getStatus()).thenReturn("IDLE");

    MockMultipartFile mockFile = new MockMultipartFile(
        "file",
        "photos.csv000",
        "text/plain",
        "header\ndata".getBytes()
    );

    mockMvc
      .perform(
        multipart("/admin/unsplash/upload")
          .file(mockFile)
          .param("limit", "50")
          .param("offset", "0")
          .with(
            SecurityMockMvcRequestPostProcessors.jwt()
              .authorities(
                new org.springframework.security.core.authority.SimpleGrantedAuthority("ROLE_ADMIN")
              )
              .jwt(jwt -> {
                jwt.claim("role", "ROLE_ADMIN");
                jwt.claim("userId", 1L);
              })
          )
      )
      .andExpect(status().isOk());

    verify(unsplashService).syncMetadataFromFile(any(Path.class), eq(50), eq(0));
  }

  @Test
  void nonAdminGetsForbiddenOnUpload() throws Exception {
    MockMultipartFile mockFile = new MockMultipartFile("file", "test", "text/plain", "data".getBytes());

    mockMvc
      .perform(
        multipart("/admin/unsplash/upload")
          .file(mockFile)
          .with(
            SecurityMockMvcRequestPostProcessors.jwt().jwt(jwt -> {
              jwt.claim("role", "ROLE_USER");
              jwt.claim("userId", 2L);
            })
          )
      )
      .andExpect(status().isForbidden());
  }
}