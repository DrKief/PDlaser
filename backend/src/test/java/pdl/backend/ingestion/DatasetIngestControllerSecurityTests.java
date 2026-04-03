package pdl.backend.ingestion;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import pdl.backend.auth.UserRepository;
import pdl.backend.config.SecurityConfig;

@Disabled("Unsplash admin feature under development")
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
  void adminCanStartImport() throws Exception {
    when(unsplashService.getStatus()).thenReturn("IDLE");

    mockMvc
      .perform(
        post("/admin/unsplash/import").with(
          SecurityMockMvcRequestPostProcessors.jwt().jwt(jwt -> {
            // Must match SecurityConfig:
            // authoritiesClaimName = "role", authorityPrefix = ""
            // hasRole("ADMIN") expects authority "ROLE_ADMIN"
            jwt.claim("role", "ROLE_ADMIN");
            jwt.claim("userId", 1L);
          })
        )
      )
      .andExpect(status().isOk());

    verify(unsplashService).startImport(50, 0);
  }

  @Test
  void nonAdminGetsForbidden() throws Exception {
    mockMvc
      .perform(
        post("/admin/unsplash/import").with(
          SecurityMockMvcRequestPostProcessors.jwt().jwt(jwt -> {
            jwt.claim("role", "ROLE_USER");
            jwt.claim("userId", 2L);
          })
        )
      )
      .andExpect(status().isForbidden());
  }

  @Test
  void anonymousGetsUnauthorized() throws Exception {
    mockMvc.perform(post("/admin/unsplash/import")).andExpect(status().isUnauthorized());
  }
}
