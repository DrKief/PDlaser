package pdl.backend.auth;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final JwtEncoder jwtEncoder;

  public AuthController(
    UserRepository userRepository,
    PasswordEncoder passwordEncoder,
    AuthenticationManager authenticationManager,
    JwtEncoder jwtEncoder
  ) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.authenticationManager = authenticationManager;
    this.jwtEncoder = jwtEncoder;
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody Map<String, String> request) {
    if (userRepository.findByUsername(request.get("username")).isPresent()) {
      return ResponseEntity.badRequest().body(Map.of("error", "Username taken"));
    }
    UserAccount user = new UserAccount(
      request.get("username"),
      passwordEncoder.encode(request.get("password")),
      "ROLE_USER"
    );
    userRepository.save(user);
    return ResponseEntity.ok(Map.of("message", "User registered successfully"));
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(request.get("username"), request.get("password"))
    );

    UserAccount userDetails = (UserAccount) authentication.getPrincipal();
    Instant now = Instant.now();
    String role = userDetails.getAuthorities().iterator().next().getAuthority();

    JwtClaimsSet claims = JwtClaimsSet.builder()
      .issuer("pdl-backend")
      .issuedAt(now)
      .expiresAt(now.plus(24, ChronoUnit.HOURS))
      .subject(userDetails.getUsername())
      .claim("role", role)
      .claim("userId", userDetails.getId())
      .build();

    JwsHeader jwsHeader = JwsHeader.with(MacAlgorithm.HS256).build();
    String token = this.jwtEncoder.encode(
      JwtEncoderParameters.from(jwsHeader, claims)
    ).getTokenValue();

    return ResponseEntity.ok(Map.of("token", token));
  }
}
