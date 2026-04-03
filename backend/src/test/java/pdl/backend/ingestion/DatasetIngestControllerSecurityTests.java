package pdl.backend.ingestion;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
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
  void adminCanStartSync() throws Exception {
    when(unsplashService.getStatus()).thenReturn("IDLE");

    String payload = "{\"limit\":50,\"offset\":0}";

    mockMvc
      .perform(
        post("/admin/unsplash/sync")
          .contentType(MediaType.APPLICATION_JSON)
          .content(payload)
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

    verify(unsplashService).syncMetadata(50, 0);
  }

  @Test
  void nonAdminGetsForbidden() throws Exception {
    mockMvc
      .perform(
        post("/admin/unsplash/sync")
          .contentType(MediaType.APPLICATION_JSON)
          .content("{}")
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
