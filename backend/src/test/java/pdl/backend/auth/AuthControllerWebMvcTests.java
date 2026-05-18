package pdl.backend.auth;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AuthController.class)
class AuthControllerWebMvcTests {

  @Autowired
  MockMvc mockMvc;

  @MockitoBean
  UserRepository userRepository;

  @MockitoBean
  PasswordEncoder passwordEncoder;

  @MockitoBean
  AuthenticationManager authenticationManager;

  @MockitoBean
  JwtEncoder jwtEncoder;

  @MockitoBean
  AppSettingRepository appSettingRepository;

  @Test
  void registerReturnsBadRequestWhenUsernameTaken() throws Exception {
    when(userRepository.findByUsername("alice")).thenReturn(
      Optional.of(new UserAccount("alice", "pw", "ROLE_USER"))
    );

    String payload = """
      {"username":"alice","password":"secret"}
      """;

    mockMvc
      .perform(post("/auth/register").contentType(MediaType.APPLICATION_JSON).content(payload))
      .andExpect(status().isBadRequest());
  }

  @Test
  void registerReturnsOkWhenUsernameFree() throws Exception {
    when(userRepository.findByUsername("bob")).thenReturn(Optional.empty());
    when(passwordEncoder.encode("secret")).thenReturn("encoded-secret");

    String payload = """
      {"username":"bob","password":"secret"}
      """;

    mockMvc
      .perform(post("/auth/register").contentType(MediaType.APPLICATION_JSON).content(payload))
      .andExpect(status().isOk());
  }
}
