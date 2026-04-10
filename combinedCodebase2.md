=========================================
Project Path: /home/lucas/Documents/S6/DevProject/v3a
Project Directory Structure
=========================================
```
.
├── backend
│   ├── .classpath
│   ├── Dockerfile
│   ├── entrypoint.sh
│   ├── .gitattributes
│   ├── .gitignore
│   ├── images
│   ├── models
│   │   ├── siglip2_text.onnx
│   │   ├── siglip2_vision.onnx
│   │   └── tokenizer.json
│   ├── .mvn
│   │   └── wrapper
│   │       └── maven-wrapper.properties
│   ├── mvnw
│   ├── mvnw.cmd
│   ├── pom.xml
│   ├── .project
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── pdl
│       │   │       └── backend
│       │   │           ├── Application.java
│       │   │           ├── auth
│       │   │           │   ├── AdminUserController.java
│       │   │           │   ├── AppSetting.java
│       │   │           │   ├── AppSettingRepository.java
│       │   │           │   ├── AuthController.java
│       │   │           │   ├── UserAccount.java
│       │   │           │   └── UserRepository.java
│       │   │           ├── config
│       │   │           │   ├── SecurityConfig.java
│       │   │           │   └── SpaRoutingConfig.java
│       │   │           ├── gallery
│       │   │           │   ├── core
│       │   │           │   │   ├── FileStorageService.java
│       │   │           │   │   ├── GalleryController.java
│       │   │           │   │   ├── MediaRecord.java
│       │   │           │   │   └── MediaRepository.java
│       │   │           │   └── search
│       │   │           │       ├── SearchController.java
│       │   │           │       ├── SimilarityRepository.java
│       │   │           │       ├── TagController.java
│       │   │           │       └── TagRepository.java
│       │   │           ├── ingestion
│       │   │           │   ├── DatasetIngestController.java
│       │   │           │   └── UnsplashService.java
│       │   │           ├── system
│       │   │           │   └── HealthCheck.java
│       │   │           └── vision
│       │   │               ├── FeatureExtractor.java
│       │   │               ├── SemanticExtractor.java
│       │   │               ├── UploadStatusTracker.java
│       │   │               └── VisionProcessor.java
│       │   └── resources
│       │       ├── application.yaml
│       │       ├── db
│       │       │   └── migration
│       │       │       ├── V1__Initial_schema.sql
│       │       │       ├── V2__Add_user_approval.sql
│       │       │       └── V3__Add_settings_table.sql
│       │       └── Whole War and Peace Novel.pdf
│       └── test
│           └── java
│               └── pdl
│                   └── backend
│                       ├── auth
│                       │   └── AuthControllerWebMvcTests.java
│                       ├── gallery
│                       │   ├── core
│                       │   │   └── GalleryControllerWebMvcTests.java
│                       │   └── search
│                       │       ├── SearchControllerWebMvcTests.java
│                       │       └── TagRepositoryTests.java
│                       ├── ingestion
│                       │   └── DatasetIngestControllerSecurityTests.java
│                       └── system
│                           └── HealthCheckIT.java
├── diff.md
├── docker-compose.preview.yml
├── docker-compose.prod.yml
├── docker-compose.yml
├── docs
│   ├── enterprise_requirements.tex
│   ├── requirements.tex
│   └── wiki
│       ├── API-Reference.md
│       ├── Database-Schema.md
│       ├── Docker-and-Deployment.md
│       ├── Frontend-Architecture-and-Views.md
│       ├── Frontend-views.md
│       └── home.md
├── frontend
│   ├── Dockerfile
│   ├── .gitignore
│   ├── index.html
│   ├── nginx.conf
│   ├── package.json
│   ├── package-lock.json
│   ├── public
│   │   ├── pdl.png
│   │   └── vite.svg
│   ├── src
│   │   ├── api
│   │   │   └── http-client.ts
│   │   ├── App.vue
│   │   ├── assets
│   │   │   ├── base.css
│   │   │   ├── theme-cruelty.css
│   │   │   └── vue.svg
│   │   ├── components
│   │   │   └── AdvancedSearchSidebar.vue
│   │   ├── composables
│   │   │   ├── useAuth.ts
│   │   │   ├── useGallerySearch.ts
│   │   │   ├── useImageStatus.ts
│   │   │   └── useUploadQueue.ts
│   │   ├── main.ts
│   │   ├── router
│   │   │   └── index.ts
│   │   ├── shared
│   │   │   └── TagAutocomplete.vue
│   │   ├── views
│   │   │   ├── AboutView.vue
│   │   │   ├── AdminView.vue
│   │   │   ├── GalleryView.vue
│   │   │   ├── ImageDetailView.vue
│   │   │   ├── LoginView.vue
│   │   │   ├── ProfileView.vue
│   │   │   ├── RegisterView.vue
│   │   │   └── UploadView.vue
│   │   └── vite-env.d.ts
│   ├── tsconfig.app.json
│   ├── tsconfig.json
│   ├── tsconfig.node.json
│   └── vite.config.ts
├── .gitignore
├── .gitlab-ci.yml
├── package.json
├── package-lock.json
├── .prettierignore
├── .prettierrc
└── README.md
```


=========================================
here how this was made :
=========================================
```bash
#!/bin/bash

OUTPUT="combinedCodebase.md"
SCRIPT_SOURCE=$(realpath "$0")

echo "Running code combiner in: $(pwd)"

if [ -f "$OUTPUT" ]; then
    rm "$OUTPUT"
fi

{
    echo "========================================="
    echo "Project Path: $(pwd)"
    echo "Project Directory Structure"
    echo "========================================="
    echo '```'
    if command -v tree &> /dev/null; then
        # Added gitignore directories (logs, dist, .idea, etc.) to the tree exclusion list
        tree -a -I ".git|node_modules|build|bin|target|venv|logs|dist|dist-ssr|.vscode|.idea|nbproject|nbbuild|nbdist|.nb-gradle|.settings|.apt_generated|.sts4-cache|$OUTPUT" --noreport
    else
        echo "Error: 'tree' command not installed."
    fi
    echo '```'
    echo -e "\n"

    echo "========================================="
    echo "here how this was made :"
    echo "========================================="
    echo '```bash'
    cat "$SCRIPT_SOURCE"
    echo '```'
    echo -e "\n"

    EXCLUDES=(
        # --- 1. Version Control & Script Output ---
        -not -path '*/.git/*'
        -not -name "diff.md"
        -not -name "$OUTPUT"

        # --- 2. Build & Dependency Directories ---
        -not -path '*/node_modules/*'
        -not -path '*/build/*'
        -not -path '*/target/*'
        -not -path '*/dist/*'
        -not -path '*/dist-ssr/*'
        -not -path '*/bin/*'
        -not -path '*/venv/*'

        # --- 3. Package Manager & Wrappers ---
        -not -name "package-lock.json"
        -not -name "mvnw"
        -not -name "mvnw.cmd"
        -not -path "*/.mvn/*"

        # --- 4. Binaries, Archives & Compiled Code ---
        -not -name "*.exe"
        -not -name "*.jar"
        -not -name "*.class"
        -not -name "*.o"
        -not -name "*.a"
        -not -name "*.out"

        # --- 5. Media & Images ---
        -not -name "*.pdf"
        -not -name "*.ico"
        -not -name "*.png"
        -not -name "*.jpg"
        -not -name "*.jpeg"
        -not -name "*.svg"

        # --- 6. Logs & Debugging ---
        -not -path "*/logs/*"
        -not -name "*.log"
        -not -name "npm-debug.log*"
        -not -name "yarn-debug.log*"
        -not -name "yarn-error.log*"
        -not -name "pnpm-debug.log*"
        -not -name "lerna-debug.log*"
        -not -name "hs_err_pid*"
        -not -name "replay_pid*"

        # --- 7. IDE, OS & Editor Files ---
        -not -name ".DS_Store"
        -not -name ".DS_Store?"
        -not -name "._*"
        -not -name ".Spotlight-V100"
        -not -name ".Trashes"
        -not -name "ehthumbs.db"
        -not -name "Thumbs.db"
        -not \( -path "*/.vscode/*" -a -not -name "extensions.json" \)
        -not -path "*/.idea/*"
        -not -path "*/.settings/*"
        -not -path "*/.apt_generated/*"
        -not -path "*/.sts4-cache/*"
        -not -path "*/nbproject/private/*"
        -not -path "*/nbbuild/*"
        -not -path "*/nbdist/*"
        -not -path "*/.nb-gradle/*"
        -not -name ".classpath"
        -not -name ".factorypath"
        -not -name ".project"
        -not -name ".springBeans"
        -not -name "*.iws"
        -not -name "*.iml"
        -not -name "*.ipr"
        -not -name "*.suo"
        -not -name "*.ntvs*"
        -not -name "*.njsproj"
        -not -name "*.sln"
        -not -name "*.sw?"
        -not -name "*.local"

        # --- 8. OCaml / Language Specific Temp Files ---
        -not -path "*/_build/*"
        -not -path "*/.merlin-conf/*"
        -not -path "*/.*.objs/*"
        -not -name "*_generated.ml"
        -not -name "*.cmly"
        -not -name "*.conflicts"
        -not -name "*.automaton"
        -not -name "*.dot"
        -not -name "*.cmi"
        -not -name "*.cmo"
        -not -name "*.cmt"
        -not -name "*.cmti"
        -not -name "*.cmx"
        -not -name "*.cma"
        -not -name "*.cmxa"
        -not -name "*.cmxs"
        -not -name "*.ml-gen"
        -not -name "*.all-deps"
        -not -name "*.d"
        -not -name "*.mock"
        -not -name "*.inferred"
        -not -name "Cmly.ml"

        # --- 9. Project Specific Exclusions ---
        -not -path "*/mini_ml/examples/*"
        -not -path "*/models/*"
        -not -path "*/install/*"
        -not -path "*/programs/*"
        -not -name "HELP.md"
        -not \( -name ".env*" -a -not -name ".env.example" \)
    )

    find . -type f "${EXCLUDES[@]}" \
        -exec grep -Iq . {} \; \
        -print0 | while IFS= read -r -d '' file; do
            echo "========================================="
            echo "File: $file"
            echo "========================================="
            cat "$file"
            echo -e "\n"
        done

} > "$OUTPUT"

echo "Success! All code combined into: $(pwd)/$OUTPUT"
```


=========================================
File: ./backend/.gitattributes
=========================================
/mvnw text eol=lf
*.cmd text eol=crlf


=========================================
File: ./backend/src/main/java/pdl/backend/Application.java
=========================================
package pdl.backend;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.crypto.password.PasswordEncoder;
import pdl.backend.auth.UserAccount;
import pdl.backend.auth.UserRepository;

@SpringBootApplication
@EnableAsync
public class Application implements AsyncConfigurer {

  private static final Logger log = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public CommandLineRunner dataSeeder(
    UserRepository userRepository,
    PasswordEncoder passwordEncoder,
    @Value("${app.admin.username}") String adminUsername,
    @Value("${app.admin.password}") String adminPassword
  ) {
    return args -> {
      if (userRepository.findByUsername(adminUsername).isEmpty()) {
        log.info("Seeding dynamic ADMIN account...");
        UserAccount admin = new UserAccount(
          adminUsername,
          passwordEncoder.encode(adminPassword),
          "ROLE_ADMIN"
        );
        admin.setApproved(true);
        userRepository.save(admin);
        log.info("Default ADMIN account successfully seeded. Username: {}", adminUsername);
      }
    };
  }

  @Bean(name = "taskExecutor")
  public Executor taskExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    int processors = Runtime.getRuntime().availableProcessors();
    executor.setCorePoolSize(processors);
    executor.setMaxPoolSize(processors);
    executor.setQueueCapacity(10);
    executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    executor.setThreadNamePrefix("AsyncWorker-");
    executor.initialize();
    return executor;
  }

  @Override
  public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
    return (throwable, method, params) ->
      log.error(
        "CRITICAL: Unhandled async exception in method: {} with parameters: {}",
        method.getName(),
        params,
        throwable
      );
  }
}


=========================================
File: ./backend/src/main/java/pdl/backend/auth/AuthController.java
=========================================
package pdl.backend.auth;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
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
  private final AppSettingRepository appSettingRepository;

  public AuthController(
    UserRepository userRepository,
    PasswordEncoder passwordEncoder,
    AuthenticationManager authenticationManager,
    JwtEncoder jwtEncoder,
    AppSettingRepository appSettingRepository
  ) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.authenticationManager = authenticationManager;
    this.jwtEncoder = jwtEncoder;
    this.appSettingRepository = appSettingRepository;
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody Map<String, String> request) {
    if (userRepository.findByUsername(request.get("username")).isPresent()) {
      return ResponseEntity.badRequest().body(Map.of("error", "Username taken"));
    }
    boolean autoApprove = appSettingRepository
      .findById("auto_approve_users")
      .map(s -> Boolean.parseBoolean(s.getSettingValue()))
      .orElse(false);

    UserAccount user = new UserAccount(
      request.get("username"),
      passwordEncoder.encode(request.get("password")),
      "ROLE_USER"
    );
    user.setApproved(autoApprove);
    userRepository.save(user);
    return ResponseEntity.ok(Map.of("message", "User registered successfully"));
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
    try {
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
    } catch (DisabledException e) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
        Map.of("error", "Your account is pending administrator approval.")
      );
    } catch (BadCredentialsException e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
        Map.of("error", "Invalid credentials.")
      );
    }
  }
}


=========================================
File: ./backend/src/main/java/pdl/backend/auth/UserAccount.java
=========================================
package pdl.backend.auth;

import java.util.Collection;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Table("users")
public class UserAccount implements UserDetails {

  @Id
  private Long id;

  private String username;
  private String password;
  private String role;

  @Column("is_approved")
  private boolean isApproved;

  public UserAccount() {}

  public UserAccount(String username, String password, String role) {
    this.username = username;
    this.password = password;
    this.role = role;
    this.isApproved = false;
  }

  public boolean isApproved() {
    return isApproved;
  }

  public void setApproved(boolean approved) {
    this.isApproved = approved;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role != null ? role : "ROLE_USER"));
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return isApproved;
  }
}


=========================================
File: ./backend/src/main/java/pdl/backend/auth/UserRepository.java
=========================================
package pdl.backend.auth;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserAccount, Long> {
  Optional<UserAccount> findByUsername(String username);

  @Query("SELECT * FROM users WHERE is_approved = false")
  List<UserAccount> findPendingApprovals();
}


=========================================
File: ./backend/src/main/java/pdl/backend/auth/AdminUserController.java
=========================================
package pdl.backend.auth;

import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/users")
@PreAuthorize("hasRole('ADMIN')")
public class AdminUserController {

  private final UserRepository userRepository;
  private final AppSettingRepository appSettingRepository;

  public AdminUserController(
    UserRepository userRepository,
    AppSettingRepository appSettingRepository
  ) {
    this.userRepository = userRepository;
    this.appSettingRepository = appSettingRepository;
  }

  @GetMapping("/settings/auto-approve")
  public ResponseEntity<Map<String, Boolean>> getAutoApprove() {
    boolean val = appSettingRepository
      .findById("auto_approve_users")
      .map(s -> Boolean.parseBoolean(s.getSettingValue()))
      .orElse(false);
    return ResponseEntity.ok(Map.of("enabled", val));
  }

  @PutMapping("/settings/auto-approve")
  public ResponseEntity<?> setAutoApprove(@RequestParam boolean enabled) {
    AppSetting setting = appSettingRepository
      .findById("auto_approve_users")
      .orElse(new AppSetting("auto_approve_users", "false"));
    setting.setSettingValue(String.valueOf(enabled));
    appSettingRepository.save(setting);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/pending")
  public ResponseEntity<List<UserAccount>> getPendingUsers() {
    return ResponseEntity.ok(userRepository.findPendingApprovals());
  }

  @PutMapping("/{id}/approve")
  public ResponseEntity<?> approveUser(@PathVariable Long id) {
    return userRepository
      .findById(id)
      .map(user -> {
        if (user.isApproved()) {
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            Map.of("error", "User is already approved.")
          );
        }
        user.setApproved(true);
        userRepository.save(user);
        return ResponseEntity.ok().build();
      })
      .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> rejectUser(@PathVariable Long id) {
    return userRepository
      .findById(id)
      .map(user -> {
        if ("ROLE_ADMIN".equals(user.getRole())) {
          return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
            Map.of("error", "Cannot delete an administrator account.")
          );
        }
        if (user.isApproved()) {
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            Map.of(
              "error",
              "Cannot reject an already active user. Use the ban/delete workflow instead."
            )
          );
        }
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
      })
      .orElse(ResponseEntity.notFound().build());
  }
}


=========================================
File: ./backend/src/main/java/pdl/backend/auth/AppSetting.java
=========================================
package pdl.backend.auth;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("app_settings")
public class AppSetting {

  @Id
  @Column("setting_key")
  private String settingKey;

  @Column("setting_value")
  private String settingValue;

  public AppSetting() {}

  public AppSetting(String settingKey, String settingValue) {
    this.settingKey = settingKey;
    this.settingValue = settingValue;
  }

  public String getSettingKey() {
    return settingKey;
  }

  public void setSettingKey(String settingKey) {
    this.settingKey = settingKey;
  }

  public String getSettingValue() {
    return settingValue;
  }

  public void setSettingValue(String settingValue) {
    this.settingValue = settingValue;
  }
}


=========================================
File: ./backend/src/main/java/pdl/backend/auth/AppSettingRepository.java
=========================================
package pdl.backend.auth;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppSettingRepository extends CrudRepository<AppSetting, String> {}


=========================================
File: ./backend/src/main/java/pdl/backend/config/SecurityConfig.java
=========================================
package pdl.backend.config;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import java.time.Duration;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtTimestampValidator;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import pdl.backend.auth.UserRepository;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

  @Value("${jwt.secret}")
  private String jwtKey;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
      .csrf(csrf -> csrf.disable())
      .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .authorizeHttpRequests(authorize ->
        authorize
          .requestMatchers("/auth/**")
          .permitAll()
          .requestMatchers(HttpMethod.GET, "/images/**")
          .permitAll()
          .requestMatchers("/admin/**")
          .hasRole("ADMIN")
          .requestMatchers("/actuator/**")
          .permitAll()
          .requestMatchers("/_nuxt/**", "/static/**", "/*.html", "/favicon.ico")
          .permitAll()
          .anyRequest()
          .authenticated()
      )
      .oauth2ResourceServer(oauth2 ->
        oauth2.jwt(jwt ->
          jwt.decoder(jwtDecoder()).jwtAuthenticationConverter(jwtAuthenticationConverter())
        )
      );

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
    throws Exception {
    return config.getAuthenticationManager();
  }

  @Bean
  public UserDetailsService userDetailsService(UserRepository userRepository) {
    return username ->
      userRepository
        .findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }

  @Bean
  public JwtDecoder jwtDecoder() {
    SecretKeySpec secretKey = new SecretKeySpec(jwtKey.getBytes(), "HmacSHA256");
    NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withSecretKey(secretKey).build();
    OAuth2TokenValidator<Jwt> withClockSkew = new DelegatingOAuth2TokenValidator<>(
      new JwtTimestampValidator(Duration.ofSeconds(60))
    );
    jwtDecoder.setJwtValidator(withClockSkew);
    return jwtDecoder;
  }

  @Bean
  public JwtEncoder jwtEncoder() {
    SecretKeySpec secretKey = new SecretKeySpec(jwtKey.getBytes(), "HmacSHA256");
    JWKSource<SecurityContext> immutableSecret = new ImmutableSecret<>(secretKey);
    return new NimbusJwtEncoder(immutableSecret);
  }

  @Bean
  public JwtAuthenticationConverter jwtAuthenticationConverter() {
    JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter =
      new JwtGrantedAuthoritiesConverter();
    grantedAuthoritiesConverter.setAuthoritiesClaimName("role");
    grantedAuthoritiesConverter.setAuthorityPrefix("");

    JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
    jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
    return jwtAuthenticationConverter;
  }
}


=========================================
File: ./backend/src/main/java/pdl/backend/config/SpaRoutingConfig.java
=========================================
package pdl.backend.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
public class SpaRoutingConfig {

  @Bean
  public FilterRegistrationBean<OncePerRequestFilter> spaRedirectFiler() {
    FilterRegistrationBean<OncePerRequestFilter> registration = new FilterRegistrationBean<>();
    registration.setFilter(createRedirectFilter());
    registration.addUrlPatterns("/*");
    registration.setName("frontendRedirectFiler");
    registration.setOrder(1);
    return registration;
  }

  private OncePerRequestFilter createRedirectFilter() {
    return new OncePerRequestFilter() {
      private final String REGEX =
        "(?!/actuator|/images|/auth|/admin|/_nuxt|/static|/index\\.html|/200\\.html|/favicon\\.ico|/sw\\.js).*$";
      private Pattern pattern = Pattern.compile(REGEX);

      @Override
      protected void doFilterInternal(
        HttpServletRequest req,
        HttpServletResponse res,
        FilterChain chain
      ) throws ServletException, IOException {
        if (pattern.matcher(req.getRequestURI()).matches() && !req.getRequestURI().equals("/")) {
          RequestDispatcher rd = req.getRequestDispatcher("/");
          rd.forward(req, res);
        } else {
          chain.doFilter(req, res);
        }
      }
    };
  }
}


=========================================
File: ./backend/src/main/java/pdl/backend/gallery/core/GalleryController.java
=========================================
package pdl.backend.gallery.core;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import pdl.backend.auth.UserAccount;
import pdl.backend.gallery.search.TagRepository;
import pdl.backend.vision.UploadStatusTracker;

@RestController
@RequestMapping("/images")
public class GalleryController {

  private final FileStorageService storageService;
  private final TagRepository queryRepo;
  private final UploadStatusTracker statusNotifier;
  private final JdbcTemplate jdbcTemplate;

  public GalleryController(
    FileStorageService storageService,
    TagRepository queryRepo,
    UploadStatusTracker statusNotifier,
    JdbcTemplate jdbcTemplate
  ) {
    this.storageService = storageService;
    this.queryRepo = queryRepo;
    this.statusNotifier = statusNotifier;
    this.jdbcTemplate = jdbcTemplate;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Map<String, Object>> getImages(
    @RequestParam(value = "page", defaultValue = "0") int page,
    @RequestParam(value = "size", defaultValue = "30") int size,
    @RequestParam(value = "mine", defaultValue = "false") boolean mine
  ) {
    int offset = page * size;
    Long userId = getCurrentUserId();

    List<Map<String, Object>> content = queryRepo.getPaginatedGallery(userId, size, offset, mine);
    long totalElements = queryRepo.getGalleryTotalCount(userId, mine);
    boolean hasNext = (offset + size) < totalElements;

    Map<String, Object> response = new java.util.HashMap<>();
    response.put("content", content);
    response.put("totalElements", totalElements);
    response.put("hasNext", hasNext);

    return ResponseEntity.ok(response);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<?> getImage(@PathVariable("id") long id) {
    Optional<MediaRecord> image = storageService.getImageWithData(id);
    if (image.isEmpty() || image.get().getData() == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found");
    }

    return ResponseEntity.ok()
      .header(HttpHeaders.CONTENT_TYPE, "image/" + image.get().getFormat())
      .body(image.get().getData());
  }

  /**
   * Deletes an image.
   *
   * @param id the image id
   * @return response entity
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteImage(@PathVariable("id") final long id) {
    final Long userId = getCurrentUserId();
    if (userId == null) {
      throw new ResponseStatusException(
        HttpStatus.UNAUTHORIZED,
        "You must be logged in to delete images"
      );
    }

    final String role = jdbcTemplate.queryForObject(
      "SELECT role FROM users WHERE id = ?",
      String.class,
      userId
    );
    final boolean isAdmin = "ROLE_ADMIN".equals(role);

    final Optional<MediaRecord> imageOpt = storageService.getImageWithData(id);
    if (imageOpt.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found");
    }

    final MediaRecord image = imageOpt.get();
    final boolean notOwner = image.getUserId() == null || !image.getUserId().equals(userId);

    if (!isAdmin && notOwner) {
      throw new ResponseStatusException(
        HttpStatus.FORBIDDEN,
        "You do not have permission to delete this image"
      );
    }

    final boolean deleted = storageService.deleteImage(id);
    if (!deleted) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete image");
    }
    return ResponseEntity.noContent().build();
  }

  @PostMapping(
    consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<?> addImage(
    @RequestPart("file") MultipartFile file,
    @RequestParam(value = "keywords", required = false) List<String> keywords,
    HttpServletRequest request
  ) throws Exception {
    if (file.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File is empty");
    }

    Long userId = getCurrentUserId();

    // Check role from DB to see if admin
    boolean isAdmin = false;
    if (userId != null) {
      String role = jdbcTemplate.queryForObject(
        "SELECT role FROM users WHERE id = ?",
        String.class,
        userId
      );
      isAdmin = "ROLE_ADMIN".equals(role);
    }

    if (!isAdmin && userId != null) {
      Integer pendingCount = jdbcTemplate.queryForObject(
        "SELECT count(*) FROM images WHERE user_id = ? AND extraction_status IN ('PENDING', 'PROCESSING')",
        Integer.class,
        userId
      );
      if (pendingCount != null && pendingCount >= 10) {
        throw new ResponseStatusException(
          HttpStatus.TOO_MANY_REQUESTS,
          "You have reached the maximum allowed pending uploads (10). Please wait for them to finish processing."
        );
      }
    }

    MediaRecord image = new MediaRecord(file.getOriginalFilename(), file.getBytes());
    image.setUserId(userId);
    storageService.processAndSaveImage(image, true);
    long id = image.getId();

    if (keywords != null && !keywords.isEmpty()) {
      for (String k : keywords) {
        String[] splits = k.split(",");
        for (String tag : splits) {
          queryRepo.addKeyword(id, tag.trim());
        }
      }
    }

    return ResponseEntity.accepted().body(Map.of("id", id));
  }

  @GetMapping(value = "/{id}/status", produces = MediaType.APPLICATION_JSON_VALUE)
  public Object getImageStatus(@PathVariable("id") long id) {
    String status = queryRepo.getStatus(id);
    if (status == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found");

    if ("COMPLETED".equals(status) || "FAILED".equals(status)) {
      return ResponseEntity.ok().body(Map.of("id", id, "extraction_status", status));
    }

    return statusNotifier.waitFor(id, 10000L);
  }

  /**
   * Gets image metadata.
   *
   * @param id the image id
   * @return response entity with metadata
   */
  @GetMapping("/{id}/metadata")
  public ResponseEntity<?> getMetadata(@PathVariable("id") final long id) {
    try {
      final Map<String, Object> rawMeta = queryRepo.getImageMetadata(id);
      final Map<String, Object> response = new HashMap<>();
      response.put("Name", rawMeta.get("name"));
      response.put("format", "image/" + rawMeta.get("format"));
      response.put("width", rawMeta.get("width"));
      response.put("height", rawMeta.get("height"));
      response.put("Keywords", rawMeta.get("Keywords"));
      response.put("extraction_status", rawMeta.get("extraction_status"));
      response.put("description", rawMeta.get("description"));
      response.put("photographer_name", rawMeta.get("photographer_name"));
      response.put("camera_make", rawMeta.get("camera_make"));
      response.put("location_country", rawMeta.get("location_country"));
      response.put("stats_downloads", rawMeta.get("stats_downloads"));
      response.put("user_id", rawMeta.get("user_id"));

      return ResponseEntity.ok().body(response);
    } catch (Exception e) {
      e.printStackTrace();
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found", e);
    }
  }

  private Long getCurrentUserId() {
    org.springframework.security.core.Authentication authentication =
      org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null) {
      if (
        authentication.getPrincipal() instanceof org.springframework.security.oauth2.jwt.Jwt jwt
      ) {
        return jwt.getClaim("userId");
      } else if (authentication.getPrincipal() instanceof UserAccount user) {
        return user.getId();
      }
    }
    return null;
  }
}


=========================================
File: ./backend/src/main/java/pdl/backend/gallery/core/MediaRepository.java
=========================================
package pdl.backend.gallery.core;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository extends CrudRepository<MediaRecord, Long> {
  Optional<MediaRecord> findByHash(String hash);
}


=========================================
File: ./backend/src/main/java/pdl/backend/gallery/core/MediaRecord.java
=========================================
package pdl.backend.gallery.core;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("images")
public class MediaRecord {

  @Id
  private Long id;

  @Column("filename")
  private String name;

  private String format;
  private int width;
  private int height;
  private String hash;

  @Column("extraction_status")
  private String extractionStatus;

  @Column("user_id")
  private Long userId;

  @Column("is_private")
  private boolean isPrivate;

  // --- NEW ROBUST METADATA FIELDS ---
  private String provider = "LOCAL";

  @Column("provider_id")
  private String providerId;

  private String description;

  @Column("photographer_name")
  private String photographerName;

  @Column("photographer_username")
  private String photographerUsername;

  @Column("camera_make")
  private String cameraMake;

  @Column("camera_model")
  private String cameraModel;

  private Integer iso;

  @Column("location_country")
  private String locationCountry;

  @Column("location_city")
  private String locationCity;

  @Column("stats_downloads")
  private Long statsDownloads;

  @Column("remote_url")
  private String remoteUrl;

  @Transient
  private byte[] data;

  public MediaRecord() {}

  public MediaRecord(final String name, final byte[] data) {
    this.name = name;
    this.data = data;
  }

  // --- STANDARD GETTERS & SETTERS ---
  public void setId(Long id) {
    this.id = id;
  }

  public long getId() {
    return (id != null) ? id : 0;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public byte[] getData() {
    return data;
  }

  public void setData(byte[] data) {
    this.data = data;
  }

  public String getHash() {
    return hash;
  }

  public void setHash(String hash) {
    this.hash = hash;
  }

  public String getFormat() {
    return format;
  }

  public void setFormat(String format) {
    this.format = format;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public String getExtractionStatus() {
    return extractionStatus;
  }

  public void setExtractionStatus(String extractionStatus) {
    this.extractionStatus = extractionStatus;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public boolean isPrivate() {
    return isPrivate;
  }

  public void setPrivate(boolean isPrivate) {
    this.isPrivate = isPrivate;
  }

  // --- ROBUST GETTERS & SETTERS ---
  public String getProvider() {
    return provider;
  }

  public void setProvider(String provider) {
    this.provider = provider;
  }

  public String getProviderId() {
    return providerId;
  }

  public void setProviderId(String providerId) {
    this.providerId = providerId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPhotographerName() {
    return photographerName;
  }

  public void setPhotographerName(String photographerName) {
    this.photographerName = photographerName;
  }

  public String getPhotographerUsername() {
    return photographerUsername;
  }

  public void setPhotographerUsername(String photographerUsername) {
    this.photographerUsername = photographerUsername;
  }

  public String getCameraMake() {
    return cameraMake;
  }

  public void setCameraMake(String cameraMake) {
    this.cameraMake = cameraMake;
  }

  public String getCameraModel() {
    return cameraModel;
  }

  public void setCameraModel(String cameraModel) {
    this.cameraModel = cameraModel;
  }

  public Integer getIso() {
    return iso;
  }

  public void setIso(Integer iso) {
    this.iso = iso;
  }

  public String getLocationCountry() {
    return locationCountry;
  }

  public void setLocationCountry(String locationCountry) {
    this.locationCountry = locationCountry;
  }

  public String getLocationCity() {
    return locationCity;
  }

  public void setLocationCity(String locationCity) {
    this.locationCity = locationCity;
  }

  public Long getStatsDownloads() {
    return statsDownloads;
  }

  public void setStatsDownloads(Long statsDownloads) {
    this.statsDownloads = statsDownloads;
  }

  public String getRemoteUrl() {
    return remoteUrl;
  }

  public void setRemoteUrl(String remoteUrl) {
    this.remoteUrl = remoteUrl;
  }
}


=========================================
File: ./backend/src/main/java/pdl/backend/gallery/core/FileStorageService.java
=========================================
package pdl.backend.gallery.core;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.HexFormat;
import java.util.Iterator;
import java.util.Optional;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class FileStorageService {

  private static final Logger log = LoggerFactory.getLogger(FileStorageService.class);
  private final MediaRepository recordRepository;

  @Value("${app.image.directory:images}")
  private String imageDirectoryPath;

  public FileStorageService(MediaRepository recordRepository) {
    this.recordRepository = recordRepository;
  }

  @Transactional
  public void processAndSaveImage(MediaRecord img, boolean saveToDisk) {
    String hash = calculateSHA256(img.getData());

    // BUG FIX: Use .equals() for Long object comparison, not !=
    Optional<MediaRecord> existing = recordRepository.findByHash(hash);
    if (existing.isPresent() && (img.getId() == 0 || existing.get().getId() != img.getId())) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Image already exists on the server.");
    }

    img.setHash(hash);
    img.setFormat(getFileExtension(img.getName()));
    img.setExtractionStatus("PENDING");

    int width = 0;
    int height = 0;
    try (
      ImageInputStream in = ImageIO.createImageInputStream(new ByteArrayInputStream(img.getData()))
    ) {
      Iterator<ImageReader> readers = ImageIO.getImageReaders(in);
      if (readers.hasNext()) {
        ImageReader reader = readers.next();
        reader.setInput(in, true, true);
        width = reader.getWidth(0);
        height = reader.getHeight(0);
        reader.dispose();
      }
    } catch (Exception e) {
      log.warn("Could not fast-read dimensions for image hash {}", hash);
    }
    img.setWidth(width);
    img.setHeight(height);

    MediaRecord savedImage = recordRepository.save(img);
    img.setId(savedImage.getId());

    if (saveToDisk) {
      try {
        Path dirPath = Paths.get(imageDirectoryPath);
        Files.createDirectories(dirPath);
        Path filePath = dirPath.resolve(img.getName());
        Files.write(filePath, img.getData());
      } catch (IOException e) {
        throw new RuntimeException("Failed to write image file to disk", e);
      }
    }
  }

  public Optional<MediaRecord> getImageWithData(long id) {
    Optional<MediaRecord> imageOpt = recordRepository.findById(id);
    if (imageOpt.isPresent()) {
      MediaRecord img = imageOpt.get();
      try {
        Path path = Paths.get(imageDirectoryPath, img.getName());
        if (Files.exists(path)) {
          img.setData(Files.readAllBytes(path));
          return Optional.of(img);
        }
      } catch (IOException e) {
        log.error("Found DB record but failed to load physical file for ID: " + id, e);
      }
    }
    return Optional.empty();
  }

  @Transactional
  public boolean deleteImage(long id) {
    Optional<MediaRecord> imageOpt = recordRepository.findById(id);
    if (imageOpt.isPresent()) {
      MediaRecord img = imageOpt.get();
      try {
        Files.deleteIfExists(Paths.get(imageDirectoryPath, img.getName()));
      } catch (IOException e) {
        log.error("Could not delete file from disk for ID: " + id, e);
      }
      recordRepository.delete(img);
      return true;
    }
    return false;
  }

  private String calculateSHA256(byte[] data) {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      byte[] hashBytes = digest.digest(data);
      return HexFormat.of().formatHex(hashBytes);
    } catch (Exception e) {
      throw new RuntimeException("Failed to calculate SHA-256 hash", e);
    }
  }

  private String getFileExtension(String filename) {
    if (filename == null || !filename.contains(".")) return "jpeg";
    String ext = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
    if (ext.equals("jpg")) return "jpeg";
    return ext;
  }
}


=========================================
File: ./backend/src/main/java/pdl/backend/gallery/search/SearchController.java
=========================================
package pdl.backend.gallery.search;

import com.pgvector.PGvector;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import pdl.backend.vision.VisionProcessor;

@RestController
@RequestMapping("/images")
public class SearchController {

  private final SimilarityRepository similarityRepo;
  private final VisionProcessor visionProcessor;

  public SearchController(SimilarityRepository similarityRepo, VisionProcessor visionProcessor) {
    this.similarityRepo = similarityRepo;
    this.visionProcessor = visionProcessor;
  }

  @GetMapping("/{id}/similar")
  public ResponseEntity<?> getSimilarImages(
    @PathVariable("id") long id,
    @RequestParam(value = "number", defaultValue = "10") int number,
    @RequestParam(value = "descriptor", defaultValue = "weighted") String descriptor
  ) {
    // Added "semantic" to allowed query types
    List<String> validDescriptors = List.of(
      "gradient",
      "saturation",
      "rgb",
      "cielab",
      "weighted",
      "semantic"
    );
    if (!validDescriptors.contains(descriptor.toLowerCase())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid descriptor.");
    }

    List<Map<String, Object>> results = similarityRepo.findSimilar(id, descriptor, number);
    if (results == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found");
    }
    return ResponseEntity.ok(results);
  }

  @PostMapping("/search/ephemeral")
  public ResponseEntity<?> searchByEphemeralUpload(
    @RequestPart("file") MultipartFile file,
    @RequestParam(value = "number", defaultValue = "10") int number,
    @RequestParam(value = "descriptor", defaultValue = "semantic") String descriptor
  ) {
    try {
      if (file.isEmpty()) {
        return ResponseEntity.badRequest().body(Map.of("error", "File is empty"));
      }

      // 1. Process the image directly in RAM
      Map<String, float[]> vectors = visionProcessor.extractEphemeralVectors(file.getBytes());

      // 2. Grab the requested algorithm's vector
      float[] targetArray = vectors.get(descriptor.toLowerCase());
      if (targetArray == null) {
        return ResponseEntity.badRequest().body(Map.of("error", "Invalid descriptor"));
      }

      // 3. Search the DB without inserting
      PGvector pgVector = new PGvector(targetArray);
      List<Map<String, Object>> results = similarityRepo.findSimilarByVector(
        pgVector,
        descriptor,
        number
      );

      return ResponseEntity.ok(results);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().body(Map.of("error", "Failed to process image"));
    }
  }
}


=========================================
File: ./backend/src/main/java/pdl/backend/gallery/search/TagController.java
=========================================
package pdl.backend.gallery.search;

import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pdl.backend.auth.UserAccount;

@RestController
@RequestMapping("/images")
public class TagController {

  private final TagRepository queryRepo;

  public TagController(TagRepository queryRepo) {
    this.queryRepo = queryRepo;
  }

  @PutMapping("/{id}/keywords")
  public ResponseEntity<?> addKeyword(
    @PathVariable("id") long id,
    @RequestParam("tag") String tag
  ) {
    if (!queryRepo.addKeyword(id, tag)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}/keywords")
  public ResponseEntity<?> deleteKeyword(
    @PathVariable("id") long id,
    @RequestParam("tag") String tag
  ) {
    try {
      queryRepo.getImageMetadata(id);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    if (!queryRepo.hasKeyword(id, tag)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    queryRepo.deleteKeyword(id, tag);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/keywords")
  public ResponseEntity<?> getAllKeywords() {
    return ResponseEntity.ok(queryRepo.getAllKeywords(getCurrentUserId()));
  }

  @GetMapping("/keywords/search")
  public ResponseEntity<?> searchKeywords(@RequestParam("q") String query) {
    if (query == null || query.length() < 2) return ResponseEntity.ok(List.of());
    return ResponseEntity.ok(queryRepo.searchKeywords(query, getCurrentUserId()));
  }

  @GetMapping("/keywords/popular")
  public ResponseEntity<List<String>> getPopularKeywords(
    @RequestParam(defaultValue = "15") int limit
  ) {
    return ResponseEntity.ok(queryRepo.getPopularKeywords(limit));
  }

  @GetMapping("/search")
  public ResponseEntity<Map<String, Object>> searchImagesByTags(
    @RequestParam(required = false) List<String> keywords,
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "30") int size,
    @RequestParam(value = "mine", defaultValue = "false") boolean mine
  ) {
    Long userId = getCurrentUserId();
    int offset = page * size;

    List<Map<String, Object>> content = queryRepo.searchGalleryByTags(
      keywords,
      userId,
      size,
      offset,
      mine
    );
    long totalElements = queryRepo.getSearchGalleryByTagsTotalCount(keywords, userId, mine);
    boolean hasNext = (offset + size) < totalElements;

    Map<String, Object> response = new java.util.HashMap<>();
    response.put("content", content);
    response.put("totalElements", totalElements);
    response.put("hasNext", hasNext);

    return ResponseEntity.ok(response);
  }

  private Long getCurrentUserId() {
    org.springframework.security.core.Authentication authentication =
      org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null) {
      if (
        authentication.getPrincipal() instanceof org.springframework.security.oauth2.jwt.Jwt jwt
      ) {
        return jwt.getClaim("userId");
      } else if (authentication.getPrincipal() instanceof UserAccount user) {
        return user.getId();
      }
    }
    return null;
  }
}


=========================================
File: ./backend/src/main/java/pdl/backend/gallery/search/TagRepository.java
=========================================
package pdl.backend.gallery.search;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TagRepository {

  private static final Logger log = LoggerFactory.getLogger(TagRepository.class);

  @NonNull
  private final JdbcTemplate jdbcTemplate;

  public TagRepository(@NonNull JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  private String normalizeTag(String tag) {
    if (tag == null) return null;
    return tag.trim().toLowerCase().replaceAll("\\s+", "_");
  }

  public void updateStatus(long id, String status) {
    jdbcTemplate.update("UPDATE images SET extraction_status = ? WHERE id = ?", status, id);
  }

  public String getStatus(long id) {
    try {
      return jdbcTemplate.queryForObject(
        "SELECT extraction_status FROM images WHERE id = ?",
        String.class,
        id
      );
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  /**
   * Gets image metadata by id.
   *
   * @param id the image id
   * @return a map of metadata fields
   */
  public Map<String, Object> getImageMetadata(final long id) {
    final Map<String, Object> meta = jdbcTemplate.queryForMap(
      "SELECT filename as Name, format, width, height, " +
        "extraction_status, description, photographer_name, " +
        "camera_make, location_country, stats_downloads, user_id " +
        "FROM images WHERE id = ?",
      id
    );
    meta.put("Keywords", getKeywords(id));
    return meta;
  }

  public List<Map<String, Object>> getKeywords(long id) {
    return jdbcTemplate.queryForList(
      "SELECT keyword, is_ai_generated as \"isAi\" FROM imagekeywords WHERE imageid = ?",
      id
    );
  }

  public boolean addKeyword(long id, String keyword, boolean isAiGenerated) {
    String normalizedTag = normalizeTag(keyword);
    if (normalizedTag == null || normalizedTag.isEmpty()) return false;
    try {
      jdbcTemplate.queryForObject("SELECT id FROM images WHERE id = ?", Long.class, id);
      jdbcTemplate.update(
        "INSERT INTO imagekeywords (imageid, keyword, is_ai_generated) VALUES (?, ?, ?) ON CONFLICT DO NOTHING",
        id,
        normalizedTag,
        isAiGenerated
      );
      return true;
    } catch (Exception e) {
      log.error("Failed to add keyword", e);
      return false;
    }
  }

  public boolean addKeyword(long id, String keyword) {
    return addKeyword(id, keyword, false);
  }

  public boolean hasKeyword(long id, String keyword) {
    Integer count = jdbcTemplate.queryForObject(
      "SELECT count(*) FROM imagekeywords WHERE imageid = ? AND keyword = ?",
      Integer.class,
      id,
      normalizeTag(keyword)
    );
    return count != null && count > 0;
  }

  public void deleteKeyword(long id, String keyword) {
    jdbcTemplate.update(
      "DELETE FROM imagekeywords WHERE imageid = ? AND keyword = ?",
      id,
      normalizeTag(keyword)
    );
  }

  public List<String> getAllKeywords(Long currentUserId) {
    return jdbcTemplate.queryForList(
      "SELECT DISTINCT k.keyword FROM imagekeywords k " +
        "JOIN images i ON k.imageid = i.id " +
        "WHERE (i.is_private = false OR i.user_id = ?) AND i.extraction_status = 'COMPLETED' " +
        "ORDER BY k.keyword ASC",
      String.class,
      currentUserId
    );
  }

  public List<String> searchKeywords(String query, Long currentUserId) {
    String normalized = normalizeTag(query);
    return jdbcTemplate.queryForList(
      "SELECT DISTINCT k.keyword FROM imagekeywords k " +
        "JOIN images i ON k.imageid = i.id " +
        "WHERE (i.is_private = false OR i.user_id = ?) AND i.extraction_status = 'COMPLETED' AND k.keyword LIKE ? " +
        "ORDER BY k.keyword ASC LIMIT 8",
      String.class,
      currentUserId,
      "%" + normalized + "%"
    );
  }

  public List<String> getPopularKeywords(int limit) {
    return jdbcTemplate.queryForList(
      "SELECT k.keyword FROM imagekeywords k " +
        "JOIN images i ON k.imageid = i.id " +
        "WHERE i.extraction_status = 'COMPLETED' AND i.is_private = false " +
        "GROUP BY k.keyword ORDER BY COUNT(k.imageid) DESC LIMIT ?",
      String.class,
      limit
    );
  }

  public List<Map<String, Object>> getPaginatedGallery(
    Long currentUserId,
    int limit,
    int offset,
    boolean onlyUser
  ) {
    String sql =
      "SELECT i.id, i.extraction_status, u.username as uploader " +
      "FROM images i LEFT JOIN users u ON i.user_id = u.id " +
      "WHERE ((i.user_id = ?) OR (? = false AND i.is_private = false)) " +
      "AND i.extraction_status = 'COMPLETED' " +
      "ORDER BY i.id DESC LIMIT ? OFFSET ?";

    return jdbcTemplate.query(
      sql,
      (rs, rowNum) -> {
        Map<String, Object> map = new java.util.HashMap<>();
        map.put("id", rs.getLong("id"));
        map.put("uploader", rs.getString("uploader") != null ? rs.getString("uploader") : "System");
        map.put("keywords", getKeywords(rs.getLong("id")));
        map.put("extraction_status", rs.getString("extraction_status"));
        return map;
      },
      currentUserId,
      onlyUser,
      limit,
      offset
    );
  }

  public List<Map<String, Object>> searchGalleryByTags(
    List<String> keywords,
    Long currentUserId,
    int limit,
    int offset,
    boolean onlyUser
  ) {
    StringBuilder sql = new StringBuilder(
      "SELECT i.id, i.extraction_status, u.username as uploader " +
        "FROM images i LEFT JOIN users u ON i.user_id = u.id " +
        "WHERE ((i.user_id = :currentUserId) OR (:onlyUser = false AND i.is_private = false)) " +
        "AND i.extraction_status = 'COMPLETED' "
    );
    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("currentUserId", currentUserId);
    params.addValue("onlyUser", onlyUser);

    if (keywords != null && !keywords.isEmpty()) {
      sql.append(
        "AND (SELECT count(DISTINCT keyword) FROM imagekeywords WHERE imageid = i.id AND keyword IN (:keywords)) = :keywordCount "
      );
      List<String> normalizedKeywords = keywords
        .stream()
        .map(this::normalizeTag)
        .collect(Collectors.toList());
      params.addValue("keywords", normalizedKeywords);
      params.addValue("keywordCount", normalizedKeywords.size());
    }

    sql.append("ORDER BY i.id DESC LIMIT :limit OFFSET :offset");
    params.addValue("limit", limit);
    params.addValue("offset", offset);

    return new NamedParameterJdbcTemplate(jdbcTemplate).query(
      sql.toString(),
      params,
      (rs, rowNum) -> {
        Map<String, Object> map = new java.util.HashMap<>();
        map.put("id", rs.getLong("id"));
        map.put("uploader", rs.getString("uploader") != null ? rs.getString("uploader") : "System");
        map.put("keywords", getKeywords(rs.getLong("id")));
        map.put("extraction_status", rs.getString("extraction_status"));
        return map;
      }
    );
  }

  public long getGalleryTotalCount(Long currentUserId, boolean onlyUser) {
    String sql =
      "SELECT COUNT(*) FROM images i WHERE ((i.user_id = ?) OR (? = false AND i.is_private = false)) AND i.extraction_status = 'COMPLETED'";
    Long count = jdbcTemplate.queryForObject(sql, Long.class, currentUserId, onlyUser);
    return count != null ? count : 0L;
  }

  public long getSearchGalleryByTagsTotalCount(
    List<String> keywords,
    Long currentUserId,
    boolean onlyUser
  ) {
    if (keywords == null || keywords.isEmpty()) {
      return getGalleryTotalCount(currentUserId, onlyUser);
    }

    StringBuilder sql = new StringBuilder(
      "SELECT COUNT(*) FROM images i WHERE ((i.user_id = :currentUserId) OR (:onlyUser = false AND i.is_private = false)) AND i.extraction_status = 'COMPLETED' "
    );
    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("currentUserId", currentUserId);
    params.addValue("onlyUser", onlyUser);

    sql.append(
      "AND (SELECT count(DISTINCT keyword) FROM imagekeywords WHERE imageid = i.id AND keyword IN (:keywords)) = :keywordCount "
    );
    List<String> normalizedKeywords = keywords
      .stream()
      .map(this::normalizeTag)
      .collect(Collectors.toList());
    params.addValue("keywords", normalizedKeywords);
    params.addValue("keywordCount", normalizedKeywords.size());

    Long count = new NamedParameterJdbcTemplate(jdbcTemplate).queryForObject(
      sql.toString(),
      params,
      Long.class
    );
    return count != null ? count : 0L;
  }
}


=========================================
File: ./backend/src/main/java/pdl/backend/gallery/search/SimilarityRepository.java
=========================================
package pdl.backend.gallery.search;

import com.pgvector.PGvector;
import java.util.List;
import java.util.Map;
import org.jspecify.annotations.NonNull;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SimilarityRepository {

  @NonNull
  private final JdbcTemplate jdbcTemplate;

  private static final double WEIGHT_HOG = 0.50;
  private static final double WEIGHT_LAB = 0.35;
  private static final double WEIGHT_HSV = 0.15;

  public SimilarityRepository(@NonNull JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Map<String, Object>> findSimilar(long targetId, String type, int limit) {
    if ("weighted".equalsIgnoreCase(type)) {
      PGvector[] vectors;
      try {
        vectors = jdbcTemplate.queryForObject(
          "SELECT hogvector, hsvvector, labvector FROM imagedescriptors WHERE imageid = ?",
          (rs, rowNum) ->
            new PGvector[] {
              new PGvector(rs.getString("hogvector")),
              new PGvector(rs.getString("hsvvector")),
              new PGvector(rs.getString("labvector")),
            },
          targetId
        );
      } catch (EmptyResultDataAccessException e) {
        return null;
      }

      if (vectors == null) return null;

      // BUG FIX: Added JOIN images i ON d.imageid = i.id WHERE i.extraction_status = 'COMPLETED'
      String sql =
        "WITH vector_matches AS (" +
        "  SELECT d.imageid, " +
        "    (? * (d.hogvector <=> ?) + " +
        "     ? * (d.labvector <=> ?) + " +
        "     ? * (d.hsvvector <=> ?)) as distance " +
        "  FROM imagedescriptors d " +
        "  JOIN images i ON d.imageid = i.id " +
        "  WHERE d.imageid != ? AND i.extraction_status = 'COMPLETED' " +
        "  ORDER BY distance ASC LIMIT ?" +
        ") " +
        "SELECT v.imageid as id, i.filename, (1.0 - (1.0 / (1.0 + v.distance))) AS score " +
        "FROM vector_matches v JOIN images i ON v.imageid = i.id " +
        "ORDER BY v.distance ASC";

      return jdbcTemplate.queryForList(
        sql,
        WEIGHT_HOG,
        vectors[0],
        WEIGHT_LAB,
        vectors[2],
        WEIGHT_HSV,
        vectors[1],
        targetId,
        limit
      );
    }

    String vectorColumn = switch (type.toLowerCase()) {
      case "gradient" -> "hogvector";
      case "saturation" -> "hsvvector";
      case "rgb" -> "rgbvector";
      case "cielab" -> "labvector";
      case "semantic" -> "semanticvector";
      default -> "hogvector";
    };

    PGvector targetVector;
    try {
      targetVector = jdbcTemplate.queryForObject(
        "SELECT " + vectorColumn + " FROM imagedescriptors WHERE imageid = ?",
        (rs, rowNum) -> new PGvector(rs.getString(1)),
        targetId
      );
    } catch (EmptyResultDataAccessException e) {
      return null;
    }

    // BUG FIX: Added JOIN images i ON d.imageid = i.id WHERE i.extraction_status = 'COMPLETED'
    String sql =
      "WITH vector_matches AS (" +
      "  SELECT d.imageid, " +
      vectorColumn +
      " <=> ? as distance " +
      "  FROM imagedescriptors d " +
      "  JOIN images i ON d.imageid = i.id " +
      "  WHERE d.imageid != ? AND i.extraction_status = 'COMPLETED' " +
      "  ORDER BY distance ASC LIMIT ?" +
      ") " +
      "SELECT v.imageid as id, i.filename, (1.0 - (1.0 / (1.0 + v.distance))) AS score " +
      "FROM vector_matches v JOIN images i ON v.imageid = i.id " +
      "ORDER BY v.distance ASC";

    return jdbcTemplate.queryForList(sql, targetVector, targetId, limit);
  }

  public List<Map<String, Object>> findSimilarByVector(
    PGvector targetVector,
    String type,
    int limit
  ) {
    String vectorColumn = switch (type.toLowerCase()) {
      case "gradient" -> "hogvector";
      case "saturation" -> "hsvvector";
      case "rgb" -> "rgbvector";
      case "cielab" -> "labvector";
      case "semantic" -> "semanticvector";
      default -> "hogvector";
    };

    // BUG FIX: Added JOIN images i ON d.imageid = i.id WHERE i.extraction_status = 'COMPLETED'
    String sql =
      "WITH vector_matches AS (" +
      "  SELECT d.imageid, " +
      vectorColumn +
      " <=> ? as distance " +
      "  FROM imagedescriptors d " +
      "  JOIN images i ON d.imageid = i.id " +
      "  WHERE i.extraction_status = 'COMPLETED' " +
      "  ORDER BY distance ASC LIMIT ?" +
      ") " +
      "SELECT v.imageid as id, i.filename, (1.0 - (1.0 / (1.0 + v.distance))) AS score " +
      "FROM vector_matches v JOIN images i ON v.imageid = i.id " +
      "ORDER BY v.distance ASC";

    return jdbcTemplate.queryForList(sql, targetVector, limit);
  }
}


=========================================
File: ./backend/src/main/java/pdl/backend/ingestion/UnsplashService.java
=========================================
package pdl.backend.ingestion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pdl.backend.auth.UserAccount;
import pdl.backend.auth.UserRepository;
import pdl.backend.gallery.core.FileStorageService;
import pdl.backend.gallery.core.MediaRecord;
import pdl.backend.gallery.core.MediaRepository;

@Service
public class UnsplashService {

  private static final Logger log = LoggerFactory.getLogger(UnsplashService.class);
  private final JdbcTemplate jdbcTemplate;
  private final FileStorageService storageService;
  private final MediaRepository recordRepository;
  private final UserRepository userRepository;

  private String status = "IDLE";

  public UnsplashService(
    JdbcTemplate jdbcTemplate,
    FileStorageService storageService,
    MediaRepository recordRepository,
    UserRepository userRepository
  ) {
    this.jdbcTemplate = jdbcTemplate;
    this.storageService = storageService;
    this.recordRepository = recordRepository;
    this.userRepository = userRepository;
  }

  public String getStatus() {
    return status;
  }

  private String getCol(String[] cols, Map<String, Integer> map, String key, String defaultVal) {
    Integer idx = map.get(key);
    if (idx != null && idx < cols.length && cols[idx] != null && !cols[idx].trim().isEmpty()) {
      return cols[idx].trim();
    }
    return defaultVal;
  }

  @Async("taskExecutor")
  public void syncMetadataFromFile(Path tempFilePath, int limit, int offset) {
    status = "SYNCING_METADATA";
    UserAccount admin = userRepository.findByUsername("admin").orElseThrow();

    try (BufferedReader br = new BufferedReader(new FileReader(tempFilePath.toFile()))) {
      String headerLine = br.readLine();
      if (headerLine == null) throw new IllegalStateException("Uploaded file is empty");

      // Auto-detect if Unsplash used tabs or commas for this file version
      String separator = headerLine.contains("\t") ? "\t" : ",";

      String[] headers = headerLine.split(separator);
      Map<String, Integer> colMap = new HashMap<>();
      for (int i = 0; i < headers.length; i++) colMap.put(headers[i].trim(), i);

      int currentLine = 0;
      int processed = 0;
      String line;

      while ((line = br.readLine()) != null) {
        if (++currentLine <= offset) continue;
        if (processed >= limit) break;

        String[] cols = line.split(separator, -1);
        String pId = getCol(cols, colMap, "photo_id", null);
        String rawUrl = getCol(
          cols,
          colMap,
          "photo_image_url",
          getCol(cols, colMap, "photo_url", null)
        );

        if (pId == null || rawUrl == null) continue;

        String description = getCol(cols, colMap, "photo_description", "No description");
        String firstName = getCol(cols, colMap, "photographer_first_name", "");
        String lastName = getCol(cols, colMap, "photographer_last_name", "");
        String photographerName = (firstName + " " + lastName).trim();
        if (photographerName.isEmpty()) photographerName = "Unknown";
        String cameraMake = getCol(cols, colMap, "exif_camera_make", "Unknown");
        String country = getCol(cols, colMap, "photo_location_country", "Unknown");

        long downloads = 0;
        try {
          downloads = Long.parseLong(getCol(cols, colMap, "stats_downloads", "0"));
        } catch (Exception ignored) {}

        Integer existing = jdbcTemplate.queryForObject(
          "SELECT COUNT(*) FROM images WHERE provider_id = ?",
          Integer.class,
          pId
        );

        if (existing != null && existing == 0) {
          jdbcTemplate.update(
            "INSERT INTO images (filename, format, provider, provider_id, remote_url, extraction_status, description, photographer_name, camera_make, location_country, stats_downloads, user_id) " +
              "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
            "unsplash_" + pId + ".jpg",
            "jpeg",
            "UNSPLASH",
            pId,
            rawUrl,
            "REMOTE_METADATA",
            description,
            photographerName,
            cameraMake,
            country,
            downloads,
            admin.getId()
          );
          processed++;
        }
      }
      status = "COMPLETED: Synced " + processed + " new metadata records.";
    } catch (Exception e) {
      log.error("Metadata sync failed", e);
      status = "ERROR: " + e.getMessage();
    } finally {
      // Always clean up the temporary file from the container's disk
      try {
        Files.deleteIfExists(tempFilePath);
      } catch (Exception ignored) {}
    }
  }

  @Async("taskExecutor")
  public void syncKeywordsFromFile(Path tempFilePath, int limit, int offset) {
    status = "SYNCING_KEYWORDS";

    try (BufferedReader br = new BufferedReader(new FileReader(tempFilePath.toFile()))) {
      String headerLine = br.readLine();
      if (headerLine == null) throw new IllegalStateException("Uploaded file is empty");

      String separator = headerLine.contains("\t") ? "\t" : ",";
      String[] headers = headerLine.split(separator);
      Map<String, Integer> colMap = new HashMap<>();
      for (int i = 0; i < headers.length; i++) colMap.put(headers[i].trim(), i);

      // Load all known Unsplash photo IDs to local DB IDs
      Map<String, Long> localPhotoIds = new HashMap<>();
      jdbcTemplate.query("SELECT provider_id, id FROM images WHERE provider = 'UNSPLASH'", rs -> {
        localPhotoIds.put(rs.getString("provider_id"), rs.getLong("id"));
      });

      int currentLine = 0;
      int processed = 0;
      String line;

      List<Object[]> batchArgs = new ArrayList<>();

      while ((line = br.readLine()) != null) {
        if (++currentLine <= offset) continue;
        if (processed >= limit) break;

        String[] cols = line.split(separator, -1);
        String pId = getCol(cols, colMap, "photo_id", null);
        String keyword = getCol(cols, colMap, "keyword", null);

        if (pId == null || keyword == null || keyword.trim().isEmpty()) continue;

        // Skip if we don't have the photo in our database
        Long localId = localPhotoIds.get(pId);
        if (localId == null) continue;

        // We are processing all keywords regardless of confidence

        String normalizedKeyword = keyword.trim().toLowerCase().replaceAll("\\s+", "_");

        batchArgs.add(new Object[] { localId, normalizedKeyword, false });
        processed++;

        if (batchArgs.size() >= 1000) {
          jdbcTemplate.batchUpdate(
            "INSERT INTO imagekeywords (imageid, keyword, is_ai_generated) VALUES (?, ?, ?) ON CONFLICT DO NOTHING",
            batchArgs
          );
          batchArgs.clear();
        }
      }

      if (!batchArgs.isEmpty()) {
        jdbcTemplate.batchUpdate(
          "INSERT INTO imagekeywords (imageid, keyword, is_ai_generated) VALUES (?, ?, ?) ON CONFLICT DO NOTHING",
          batchArgs
        );
      }

      status = "COMPLETED: Linked " + processed + " tags to photos.";
    } catch (Exception e) {
      log.error("Keywords sync failed", e);
      status = "ERROR: " + e.getMessage();
    } finally {
      // Always clean up the temporary file from the container's disk
      try {
        Files.deleteIfExists(tempFilePath);
      } catch (Exception ignored) {}
    }
  }

  public Map<String, Object> getCatalog(
    int page,
    int size,
    String query,
    String camera,
    String country
  ) {
    String baseSql = "FROM images WHERE extraction_status = 'REMOTE_METADATA'";
    List<Object> params = new ArrayList<>();

    if (query != null && !query.trim().isEmpty()) {
      baseSql +=
        " AND (camera_make ILIKE ? OR location_country ILIKE ? OR description ILIKE ? OR photographer_name ILIKE ?)";
      String likeQuery = "%" + query.trim() + "%";
      params.add(likeQuery);
      params.add(likeQuery);
      params.add(likeQuery);
      params.add(likeQuery);
    }

    if (camera != null && !camera.trim().isEmpty()) {
      baseSql += " AND camera_make ILIKE ?";
      params.add("%" + camera.trim() + "%");
    }

    if (country != null && !country.trim().isEmpty()) {
      baseSql += " AND location_country ILIKE ?";
      params.add("%" + country.trim() + "%");
    }

    String countSql = "SELECT COUNT(*) " + baseSql;
    Long totalElements = jdbcTemplate.queryForObject(countSql, Long.class, params.toArray());

    String dataSql =
      "SELECT id, remote_url, photographer_name, camera_make, location_country, stats_downloads " +
      baseSql +
      " ORDER BY stats_downloads DESC LIMIT ? OFFSET ?";
    params.add(size);
    params.add(page * size);

    List<Map<String, Object>> content = jdbcTemplate.queryForList(dataSql, params.toArray());

    Map<String, Object> result = new HashMap<>();
    result.put("content", content);
    result.put("totalElements", totalElements != null ? totalElements : 0);
    result.put(
      "totalPages",
      (int) Math.ceil((double) (totalElements != null ? totalElements : 0) / size)
    );
    return result;
  }

  @Async("taskExecutor")
  public void importSelectedImages(List<Long> imageIds) {
    if (imageIds == null || imageIds.isEmpty()) return;

    // 1. Synchronously mark all selected images as DOWNLOADING immediately
    // This instantly hides them from the frontend catalog
    List<Object[]> batchArgs = new ArrayList<>();
    for (Long id : imageIds) {
      batchArgs.add(new Object[] { id });
    }
    jdbcTemplate.batchUpdate(
      "UPDATE images SET extraction_status = 'DOWNLOADING' WHERE id = ? AND extraction_status = 'REMOTE_METADATA'",
      batchArgs
    );

    // 2. Start the slow, polite download process
    int count = 0;
    RestTemplate restTemplate = new RestTemplate();

    for (Long id : imageIds) {
      try {
        Optional<MediaRecord> opt = recordRepository.findById(id);
        if (opt.isEmpty()) continue;
        MediaRecord record = opt.get();
        // Check for DOWNLOADING since we just updated it
        if (!"DOWNLOADING".equals(record.getExtractionStatus())) continue;

        status = "IMPORTING (" + (count + 1) + " / " + imageIds.size() + "): " + record.getName();

        String remoteUrl = record.getRemoteUrl();
        String fetchUrl = remoteUrl + (remoteUrl.contains("?") ? "&" : "?") + "w=1080&q=85&fm=jpg";

        byte[] bytes = restTemplate.getForObject(URI.create(fetchUrl), byte[].class);
        if (bytes != null) {
          record.setData(bytes);
          // storageService sets it to PENDING and our ML queue takes over
          storageService.processAndSaveImage(record, true);
          Thread.sleep(1000); // Politeness delay
        } else {
          // If bytes are null, it failed to fetch. Revert to REMOTE_METADATA so it shows up again.
          jdbcTemplate.update(
            "UPDATE images SET extraction_status = 'REMOTE_METADATA' WHERE id = ?",
            id
          );
        }
      } catch (Exception e) {
        log.error("Failed to import image ID: " + id, e);
        // On any HTTP or network error, revert the status so it isn't permanently stuck as DOWNLOADING
        jdbcTemplate.update(
          "UPDATE images SET extraction_status = 'REMOTE_METADATA' WHERE id = ?",
          id
        );
      }
      count++;
    }
    status = "IDLE";
  }

  public void importBatchImages(int limit, String query, String camera, String country) {
    String baseSql = "FROM images WHERE extraction_status = 'REMOTE_METADATA'";
    List<Object> params = new ArrayList<>();

    if (query != null && !query.trim().isEmpty()) {
      baseSql +=
        " AND (camera_make ILIKE ? OR location_country ILIKE ? OR description ILIKE ? OR photographer_name ILIKE ?)";
      String likeQuery = "%" + query.trim() + "%";
      params.add(likeQuery);
      params.add(likeQuery);
      params.add(likeQuery);
      params.add(likeQuery);
    }

    if (camera != null && !camera.trim().isEmpty()) {
      baseSql += " AND camera_make ILIKE ?";
      params.add("%" + camera.trim() + "%");
    }

    if (country != null && !country.trim().isEmpty()) {
      baseSql += " AND location_country ILIKE ?";
      params.add("%" + country.trim() + "%");
    }

    String dataSql = "SELECT id " + baseSql + " ORDER BY stats_downloads DESC LIMIT ?";
    params.add(limit);

    List<Long> idsToImport = jdbcTemplate.queryForList(dataSql, Long.class, params.toArray());

    if (!idsToImport.isEmpty()) {
      importSelectedImages(idsToImport);
    }
  }
}


=========================================
File: ./backend/src/main/java/pdl/backend/ingestion/DatasetIngestController.java
=========================================
package pdl.backend.ingestion;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class DatasetIngestController {

  private final UnsplashService unsplashService;

  public DatasetIngestController(UnsplashService unsplashService) {
    this.unsplashService = unsplashService;
  }

  @PostMapping(value = "/unsplash/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<?> uploadAndSyncMetadata(
    @RequestPart("file") MultipartFile file,
    @RequestParam(defaultValue = "1000") int limit,
    @RequestParam(defaultValue = "0") int offset,
    @RequestParam(defaultValue = "PHOTOS") String fileType
  ) {
    if (
      !unsplashService.getStatus().equals("IDLE") &&
      !unsplashService.getStatus().startsWith("ERROR") &&
      !unsplashService.getStatus().startsWith("COMPLETED")
    ) {
      return ResponseEntity.badRequest().body(Map.of("message", "A job is already in progress."));
    }

    try {
      // Save uploaded file to container's temp directory, then trigger async parsing
      Path tempFile = Files.createTempFile("unsplash_", ".tmp");
      file.transferTo(tempFile.toFile());

      if ("KEYWORDS".equalsIgnoreCase(fileType)) {
        unsplashService.syncKeywordsFromFile(tempFile, limit, offset);
      } else {
        unsplashService.syncMetadataFromFile(tempFile, limit, offset);
      }

      return ResponseEntity.ok(Map.of("message", "File uploaded. Sync initiated."));
    } catch (Exception e) {
      return ResponseEntity.internalServerError().body(
        Map.of("message", "Failed to process uploaded file.")
      );
    }
  }

  @GetMapping("/unsplash/catalog")
  public ResponseEntity<?> getCatalog(
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "30") int size,
    @RequestParam(required = false) String query,
    @RequestParam(required = false) String camera,
    @RequestParam(required = false) String country
  ) {
    return ResponseEntity.ok(unsplashService.getCatalog(page, size, query, camera, country));
  }

  @PostMapping("/unsplash/import")
  public ResponseEntity<?> importSelected(@RequestBody List<Long> imageIds) {
    if (
      !unsplashService.getStatus().equals("IDLE") &&
      !unsplashService.getStatus().startsWith("ERROR") &&
      !unsplashService.getStatus().startsWith("COMPLETED")
    ) {
      return ResponseEntity.badRequest().body(Map.of("message", "A job is already in progress."));
    }
    unsplashService.importSelectedImages(imageIds);
    return ResponseEntity.ok(Map.of("message", "Batch import initiated."));
  }

  @PostMapping("/unsplash/import/batch")
  public ResponseEntity<?> importBatch(
    @RequestParam(defaultValue = "1000") int limit,
    @RequestParam(required = false) String query,
    @RequestParam(required = false) String camera,
    @RequestParam(required = false) String country
  ) {
    if (
      !unsplashService.getStatus().equals("IDLE") &&
      !unsplashService.getStatus().startsWith("ERROR") &&
      !unsplashService.getStatus().startsWith("COMPLETED")
    ) {
      return ResponseEntity.badRequest().body(Map.of("message", "A job is already in progress."));
    }
    unsplashService.importBatchImages(limit, query, camera, country);
    return ResponseEntity.ok(Map.of("message", "Batch import initiated."));
  }

  @GetMapping("/unsplash/status")
  public ResponseEntity<?> getStatus() {
    return ResponseEntity.ok(Map.of("status", unsplashService.getStatus()));
  }
}


=========================================
File: ./backend/src/main/java/pdl/backend/system/HealthCheck.java
=========================================
package pdl.backend.system;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.HexFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class HealthCheck implements HealthIndicator {

  @Value("${app.image.directory:images}")
  private String imageDirectoryPath;

  @Override
  public Health health() {
    try {
      Path path = Paths.get(imageDirectoryPath);
      if (!Files.exists(path) || !Files.isDirectory(path) || !Files.isWritable(path)) {
        return Health.down()
          .withDetail("error", "Image directory is missing or not writable")
          .build();
      }

      try (
        java.io.InputStream is = getClass().getResourceAsStream("/Whole War and Peace Novel.pdf")
      ) {
        if (is == null) {
          return Health.down()
            .withDetail("error", "Missing critical load-bearing component")
            .build();
        }
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] buffer = new byte[8192];
        int bytesRead;
        while ((bytesRead = is.read(buffer)) != -1) {
          digest.update(buffer, 0, bytesRead);
        }
        String actualHash = HexFormat.of().formatHex(digest.digest());
        String expectedHash = "4e22f003e05fcc4120268d15fbcb6100dbce436c36521750b2adf70626c6a6bb";

        if (!expectedHash.equals(actualHash)) {
          return Health.down()
            .withDetail("error", "Cryptographic mismatch. Tampering detected.")
            .build();
        }
      }

      return Health.up().withDetail("status", "System structural integrity verified").build();
    } catch (Exception e) {
      return Health.down(e).build();
    }
  }
}


=========================================
File: ./backend/src/main/java/pdl/backend/vision/FeatureExtractor.java
=========================================
package pdl.backend.vision;

import boofcv.abst.feature.dense.DescribeImageDense;
import boofcv.alg.color.ColorHsv;
import boofcv.alg.color.impl.ImplColorLab;
import boofcv.factory.feature.dense.ConfigDenseHoG;
import boofcv.factory.feature.dense.FactoryDescribeImageDense;
import boofcv.io.image.ConvertBufferedImage;
import boofcv.struct.feature.TupleDesc_F64;
import boofcv.struct.image.GrayF32;
import boofcv.struct.image.ImageType;
import boofcv.struct.image.Planar;
import com.twelvemonkeys.image.ResampleOp;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Utility class aggregating all heavy image manipulation and feature extraction logic.
 * Wraps BoofCV and TwelveMonkeys operations.
 */
public class FeatureExtractor {

  public static BufferedImage resizeImageLanczos3(
    BufferedImage inputImage,
    int targetWidth,
    int targetHeight
  ) {
    ResampleOp resampleOp = new ResampleOp(targetWidth, targetHeight, ResampleOp.FILTER_LANCZOS);
    return resampleOp.filter(inputImage, null);
  }

  public static float[] extractGlobalHog(BufferedImage inputImage) {
    GrayF32 grayImage = ConvertBufferedImage.convertFrom(inputImage, (GrayF32) null);

    ConfigDenseHoG config = new ConfigDenseHoG();
    DescribeImageDense<GrayF32, TupleDesc_F64> describer = FactoryDescribeImageDense.hog(
      config,
      ImageType.single(GrayF32.class)
    );

    describer.process(grayImage);
    List<TupleDesc_F64> descriptions = describer.getDescriptions();

    if (descriptions.isEmpty()) {
      return new float[31];
    }

    int singleDescSize = descriptions.get(0).size();
    float[] globalHistogram = new float[singleDescSize];

    for (TupleDesc_F64 desc : descriptions) {
      for (int i = 0; i < singleDescSize; i++) {
        globalHistogram[i] += (float) desc.data[i];
      }
    }

    return normalizeL2(globalHistogram);
  }

  public static float[] extractHsvHistogram(BufferedImage inputImage) {
    Planar<GrayF32> rgb = ConvertBufferedImage.convertFromPlanar(
      inputImage,
      null,
      true,
      GrayF32.class
    );
    Planar<GrayF32> hsv = rgb.createSameShape();
    ColorHsv.rgbToHsv(rgb, hsv);

    int hueBins = 16;
    int satBins = 16;
    float[] histogram2D = new float[hueBins * satBins];

    for (int y = 0; y < hsv.height; y++) {
      for (int x = 0; x < hsv.width; x++) {
        float hue = hsv.getBand(0).get(x, y);
        float sat = hsv.getBand(1).get(x, y);

        int hIndex = (int) ((hue / (2 * Math.PI)) * hueBins);
        int sIndex = (int) (sat * satBins);

        if (hIndex >= hueBins) hIndex = hueBins - 1;
        if (sIndex >= satBins) sIndex = satBins - 1;

        histogram2D[hIndex * satBins + sIndex]++;
      }
    }

    return normalizeL2(histogram2D);
  }

  public static float[] extractCieLabHistogram(BufferedImage inputImage) {
    Planar<GrayF32> rgb = ConvertBufferedImage.convertFromPlanar(
      inputImage,
      null,
      true,
      GrayF32.class
    );
    Planar<GrayF32> lab = rgb.createSameShape();
    ImplColorLab.rgbToLab_F32(rgb, lab);

    int binsPerChannel = 8;
    float[] histogram3D = new float[binsPerChannel * binsPerChannel * binsPerChannel];

    for (int y = 0; y < lab.height; y++) {
      for (int x = 0; x < lab.width; x++) {
        float l = lab.getBand(0).get(x, y);
        float a = lab.getBand(1).get(x, y);
        float b = lab.getBand(2).get(x, y);

        int lIndex = (int) ((l / 100.0f) * binsPerChannel);
        int aIndex = (int) (((a + 128) / 256.0f) * binsPerChannel);
        int bIndex = (int) (((b + 128) / 256.0f) * binsPerChannel);

        if (lIndex >= binsPerChannel) lIndex = binsPerChannel - 1;
        if (aIndex >= binsPerChannel) aIndex = binsPerChannel - 1;
        if (bIndex >= binsPerChannel) bIndex = binsPerChannel - 1;

        histogram3D[lIndex * binsPerChannel * binsPerChannel + aIndex * binsPerChannel + bIndex]++;
      }
    }
    return normalizeL2(histogram3D);
  }

  public static float[] extractRgbHistogram(BufferedImage inputImage) {
    Planar<GrayF32> rgb = ConvertBufferedImage.convertFromPlanar(
      inputImage,
      null,
      true,
      GrayF32.class
    );
    int bins = 8;
    float[] histogram3D = new float[bins * bins * bins];

    for (int y = 0; y < rgb.height; y++) {
      for (int x = 0; x < rgb.width; x++) {
        float r = rgb.getBand(0).get(x, y);
        float g = rgb.getBand(1).get(x, y);
        float b = rgb.getBand(2).get(x, y);

        int rIndex = (int) ((r / 256.0f) * bins);
        int gIndex = (int) ((g / 256.0f) * bins);
        int bIndex = (int) ((b / 256.0f) * bins);

        if (rIndex >= bins) rIndex = bins - 1;
        if (gIndex >= bins) gIndex = bins - 1;
        if (bIndex >= bins) bIndex = bins - 1;

        histogram3D[rIndex * bins * bins + gIndex * bins + bIndex]++;
      }
    }
    return normalizeL2(histogram3D);
  }

  private static float[] normalizeL2(float[] vector) {
    double sumSquares = 0;
    for (float v : vector) {
      sumSquares += v * v;
    }

    float length = (float) Math.sqrt(sumSquares);
    if (length > 0) {
      for (int i = 0; i < vector.length; i++) {
        vector[i] /= length;
      }
    }
    return vector;
  }
}


=========================================
File: ./backend/src/main/java/pdl/backend/vision/UploadStatusTracker.java
=========================================
package pdl.backend.vision;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

@Service
public class UploadStatusTracker {

  private final Map<Long, DeferredResult<ResponseEntity<Map<String, Object>>>> requests =
    new ConcurrentHashMap<>();

  public DeferredResult<ResponseEntity<Map<String, Object>>> waitFor(
    Long imageId,
    Long timeoutInMilliseconds
  ) {
    DeferredResult<ResponseEntity<Map<String, Object>>> result = new DeferredResult<>(
      timeoutInMilliseconds
    );

    result.onTimeout(() -> {
      requests.remove(imageId);
      result.setResult(
        ResponseEntity.accepted().body(Map.of("id", imageId, "extraction_status", "PENDING"))
      );
    });

    result.onCompletion(() -> requests.remove(imageId));
    requests.put(imageId, result);
    return result;
  }

  public void notify(Long imageId, String finalStatus) {
    DeferredResult<ResponseEntity<Map<String, Object>>> result = requests.remove(imageId);
    if (result != null) {
      result.setResult(ResponseEntity.ok(Map.of("id", imageId, "extraction_status", finalStatus)));
    }
  }
}


=========================================
File: ./backend/src/main/java/pdl/backend/vision/VisionProcessor.java
=========================================
package pdl.backend.vision;

import com.pgvector.PGvector;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.imageio.ImageIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pdl.backend.gallery.core.FileStorageService;
import pdl.backend.gallery.core.MediaRecord;
import pdl.backend.gallery.search.TagRepository;

@Service
public class VisionProcessor {

  private static final Logger log = LoggerFactory.getLogger(VisionProcessor.class);
  private final JdbcTemplate jdbcTemplate;
  private final TagRepository queryRepoLayer;
  private final UploadStatusTracker statusNotifier;
  private final FileStorageService fileStorageService;

  private volatile boolean isRunning = true;
  private final List<Thread> workers = new ArrayList<>();

  public VisionProcessor(
    JdbcTemplate jdbcTemplate,
    TagRepository queryRepoLayer,
    UploadStatusTracker statusNotifier,
    FileStorageService fileStorageService
  ) {
    this.jdbcTemplate = jdbcTemplate;
    this.queryRepoLayer = queryRepoLayer;
    this.statusNotifier = statusNotifier;
    this.fileStorageService = fileStorageService;
  }

  @PostConstruct
  public void startWorkers() {
    log.info("Starting 2 dedicated VisionProcessor ML worker threads");
    for (int i = 0; i < 2; i++) {
      Thread worker = new Thread(this::processQueue, "MLWorker-" + i);
      worker.setDaemon(false);
      worker.start();
      workers.add(worker);
    }
  }

  @PreDestroy
  public void shutdown() {
    log.info("Gracefully shutting down VisionProcessor workers (up to 30s timeout)...");
    isRunning = false;
    for (Thread worker : workers) {
      try {
        worker.join(30000);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
  }

  @EventListener(ApplicationReadyEvent.class)
  public void resetProcessingOnStartup() {
    log.info("Resetting any stuck PROCESSING images to PENDING");
    jdbcTemplate.update(
      "UPDATE images SET extraction_status = 'PENDING' WHERE extraction_status = 'PROCESSING'"
    );
  }

  private void processQueue() {
    while (isRunning) {
      try {
        // Atomic lock using SKIP LOCKED
        List<Long> ids = jdbcTemplate.query(
          "UPDATE images SET extraction_status = 'PROCESSING' WHERE id = (" +
            "  SELECT id FROM images WHERE extraction_status = 'PENDING' ORDER BY id ASC LIMIT 1 FOR UPDATE SKIP LOCKED" +
            ") RETURNING id",
          (rs, rowNum) -> rs.getLong(1)
        );

        if (ids.isEmpty()) {
          Thread.sleep(2000);
        } else {
          Long id = ids.get(0);
          try {
            Optional<MediaRecord> recordOpt = fileStorageService.getImageWithData(id);
            if (recordOpt.isEmpty() || recordOpt.get().getData() == null) {
              log.error("Failed to load file data for ID: {}", id);
              queryRepoLayer.updateStatus(id, "FAILED");
              statusNotifier.notify(id, "FAILED");
              continue; // proceed to next item
            }
            processImageDescriptors(id, recordOpt.get().getData());
          } catch (Throwable t) {
            // MASSIVE CATCH to prevent eternal queue stall
            log.error("SEVERE CRASH processing image ID: {}", id, t);
            queryRepoLayer.updateStatus(id, "FAILED");
            statusNotifier.notify(id, "FAILED");
          }
        }
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        break; // break the loop on interrupt
      } catch (Exception e) {
        log.error("Error in VisionProcessor queue loop", e);
        try {
          Thread.sleep(5000);
        } catch (InterruptedException ie) {
          Thread.currentThread().interrupt();
          break;
        }
      }
    }
  }

  public void processImageDescriptors(Long id, byte[] data) {
    BufferedImage bimg = null;
    BufferedImage resizedImage = null;

    try {
      queryRepoLayer.updateStatus(id, "PROCESSING");
      statusNotifier.notify(id, "PROCESSING");

      bimg = ImageIO.read(new ByteArrayInputStream(data));
      if (bimg == null) {
        queryRepoLayer.updateStatus(id, "FAILED");
        statusNotifier.notify(id, "FAILED");
        return;
      }

      // 1. Extract Classic Descriptors
      resizedImage = FeatureExtractor.resizeImageLanczos3(bimg, 256, 256);
      float[] hogData = FeatureExtractor.extractGlobalHog(resizedImage);
      float[] hsvData = FeatureExtractor.extractHsvHistogram(resizedImage);
      float[] rgbData = FeatureExtractor.extractRgbHistogram(resizedImage);
      float[] labData = FeatureExtractor.extractCieLabHistogram(resizedImage);

      // 2. Extract Semantic AI Data & Auto-Tags
      float[] semanticVector = SemanticExtractor.extractSemanticFeatures(bimg);

      // Auto-Tagging logic
      List<String> aiTags = SemanticExtractor.getAutoTags(semanticVector);
      for (String tag : aiTags) {
        queryRepoLayer.addKeyword(id, tag, true);
      }

      if (hogData.length != 31) {
        float[] adjustedHog = new float[31];
        System.arraycopy(hogData, 0, adjustedHog, 0, Math.min(hogData.length, 31));
        hogData = adjustedHog;
      }

      // 4. Save everything to Postgres
      jdbcTemplate.update(
        "INSERT INTO imagedescriptors (imageid, hogvector, hsvvector, rgbvector, labvector, semanticvector) VALUES (?, ?, ?, ?, ?, ?) " +
          "ON CONFLICT (imageid) DO UPDATE SET " +
          "hogvector = EXCLUDED.hogvector, " +
          "hsvvector = EXCLUDED.hsvvector, " +
          "rgbvector = EXCLUDED.rgbvector, " +
          "labvector = EXCLUDED.labvector, " +
          "semanticvector = EXCLUDED.semanticvector",
        id,
        new PGvector(hogData),
        new PGvector(hsvData),
        new PGvector(rgbData),
        new PGvector(labData),
        new PGvector(semanticVector)
      );

      queryRepoLayer.updateStatus(id, "COMPLETED");
      statusNotifier.notify(id, "COMPLETED");
    } catch (Exception e) {
      log.error("Failed to process image descriptors for ID: " + id, e);
      queryRepoLayer.updateStatus(id, "FAILED");
      statusNotifier.notify(id, "FAILED");
    } finally {
      if (bimg != null) bimg.flush();
      if (resizedImage != null) resizedImage.flush();
    }
  }

  public Map<String, float[]> extractEphemeralVectors(byte[] data) throws Exception {
    BufferedImage bimg = ImageIO.read(new ByteArrayInputStream(data));
    if (bimg == null) {
      throw new IllegalArgumentException("Failed to decode image data.");
    }

    BufferedImage resizedImage = FeatureExtractor.resizeImageLanczos3(bimg, 256, 256);

    // Extract base histograms
    float[] hogData = FeatureExtractor.extractGlobalHog(resizedImage);
    float[] hsvData = FeatureExtractor.extractHsvHistogram(resizedImage);
    float[] rgbData = FeatureExtractor.extractRgbHistogram(resizedImage);
    float[] labData = FeatureExtractor.extractCieLabHistogram(resizedImage);

    // Extract semantics
    float[] semanticVector = SemanticExtractor.extractSemanticFeatures(bimg);

    // Fix HOG length mismatch
    if (hogData.length != 31) {
      float[] adjustedHog = new float[31];
      System.arraycopy(hogData, 0, adjustedHog, 0, Math.min(hogData.length, 31));
      hogData = adjustedHog;
    }

    // Cleanup resources
    bimg.flush();
    resizedImage.flush();

    return Map.of(
      "gradient",
      hogData,
      "saturation",
      hsvData,
      "rgb",
      rgbData,
      "cielab",
      labData,
      "semantic",
      semanticVector
    );
  }
}


=========================================
File: ./backend/src/main/java/pdl/backend/vision/SemanticExtractor.java
=========================================
package pdl.backend.vision;

import ai.djl.huggingface.tokenizers.Encoding;
import ai.djl.huggingface.tokenizers.HuggingFaceTokenizer;
import ai.onnxruntime.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SemanticExtractor {

  private static OrtEnvironment env;
  private static OrtSession visionSession;
  private static OrtSession textSession;
  private static HuggingFaceTokenizer tokenizer;

  private static final String MODELS_DIR =
    System.getenv("APP_MODELS_DIR") != null ? System.getenv("APP_MODELS_DIR") : "models";
  private static final String VISION_URL =
    "https://huggingface.co/onnx-community/siglip2-base-patch16-224-ONNX/resolve/main/onnx/vision_model.onnx";
  private static final String TEXT_URL =
    "https://huggingface.co/onnx-community/siglip2-base-patch16-224-ONNX/resolve/main/onnx/text_model.onnx";
  private static final String TOKENIZER_URL =
    "https://huggingface.co/onnx-community/siglip2-base-patch16-224-ONNX/resolve/main/tokenizer.json";

  private static final Map<String, String> CUSTOM_TAGS = Map.ofEntries(
    Map.entry("cinematic_lighting", "a photo with moody cinematic lighting"),
    Map.entry("natural_light", "a photo with bright and airy natural light"),
    Map.entry("golden_hour", "a photo taken during golden hour"),
    Map.entry("black_and_white", "a high contrast black and white photo"),
    Map.entry("macro", "a macro photography shot"),
    Map.entry("landscape", "a wide angle landscape photograph"),
    Map.entry("architecture", "a minimalist architectural photo"),
    Map.entry("portrait", "a professional portrait of a person"),
    Map.entry("street_photography", "a candid street photography shot"),
    Map.entry("wedding", "a photo of a wedding"),
    Map.entry("wildlife", "a photo of wildlife"),
    Map.entry("food", "a photo of food"),
    Map.entry("vehicle", "a photo of a vehicle")
  );

  private static Map<String, float[]> cachedTextEmbeddings = new HashMap<>();

  private static File downloadIfMissing(String urlStr, String fileName) throws Exception {
    File dir = new File(MODELS_DIR);
    if (!dir.exists()) {
      dir.mkdirs();
    }
    File file = new File(dir, fileName);
    if (!file.exists()) {
      System.out.println(
        "Downloading " + fileName + " from HuggingFace (This may take a while, please wait...)"
      );
      URL url = URI.create(urlStr).toURL();
      try (InputStream in = url.openStream()) {
        Files.copy(in, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
      }
      System.out.println("Successfully downloaded: " + fileName);
    }
    return file;
  }

  static {
    try {
      env = OrtEnvironment.getEnvironment();

      File visionFile = downloadIfMissing(VISION_URL, "siglip2_vision.onnx");
      File textFile = downloadIfMissing(TEXT_URL, "siglip2_text.onnx");
      File tokenizerFile = downloadIfMissing(TOKENIZER_URL, "tokenizer.json");

      try (InputStream stream = new FileInputStream(visionFile)) {
        byte[] modelArray = stream.readAllBytes();
        visionSession = env.createSession(modelArray, new OrtSession.SessionOptions());
      }

      try (InputStream stream = new FileInputStream(textFile)) {
        byte[] modelArray = stream.readAllBytes();
        textSession = env.createSession(modelArray, new OrtSession.SessionOptions());
      }

      // Sanitize tokenizer.json to remove unsupported keys for older DJL versions
      try {
        String content = Files.readString(tokenizerFile.toPath());
        boolean changed = false;
        if (content.contains("\"fuse_unk\"")) {
          content = content.replaceAll("\"fuse_unk\":\\s*(true|false),?", "");
          changed = true;
        }
        if (content.contains("\"byte_fallback\"")) {
          content = content.replaceAll("\"byte_fallback\":\\s*(true|false),?", "");
          changed = true;
        }
        if (changed) {
          Files.writeString(tokenizerFile.toPath(), content);
          System.out.println("Sanitized tokenizer.json for compatibility.");
        }
      } catch (Exception e) {
        System.err.println("Failed to sanitize tokenizer.json: " + e.getMessage());
      }

      try (InputStream stream = new FileInputStream(tokenizerFile)) {
        tokenizer = HuggingFaceTokenizer.newInstance(stream, Map.of());
      }

      if (textSession != null && tokenizer != null) {
        System.out.println("Caching text embeddings for tags...");
        for (Map.Entry<String, String> entry : CUSTOM_TAGS.entrySet()) {
          cachedTextEmbeddings.put(entry.getKey(), extractTextFeature(entry.getValue()));
        }
      }
    } catch (Exception e) {
      System.err.println("CRITICAL: Failed to initialize SemanticExtractor.");
      e.printStackTrace();
    }
  }

  public static float[] extractSemanticFeatures(BufferedImage img) {
    if (visionSession == null) return new float[768];
    try {
      BufferedImage resized = FeatureExtractor.resizeImageLanczos3(img, 224, 224);
      float[][][][] inputTensor = new float[1][3][224][224];

      for (int y = 0; y < 224; y++) {
        for (int x = 0; x < 224; x++) {
          int rgb = resized.getRGB(x, y);
          float r = ((rgb >> 16) & 0xFF) / 255.0f;
          float g = ((rgb >> 8) & 0xFF) / 255.0f;
          float b = (rgb & 0xFF) / 255.0f;

          inputTensor[0][0][y][x] = (r * 2.0f) - 1.0f;
          inputTensor[0][1][y][x] = (g * 2.0f) - 1.0f;
          inputTensor[0][2][y][x] = (b * 2.0f) - 1.0f;
        }
      }

      try (
        OnnxTensor tensor = OnnxTensor.createTensor(env, inputTensor);
        OrtSession.Result result = visionSession.run(
          Collections.singletonMap("pixel_values", tensor)
        )
      ) {
        float[][] output = (float[][]) result.get("pooler_output").get().getValue();
        return normalizeL2(output[0]);
      }
    } catch (Exception e) {
      e.printStackTrace();
      return new float[768];
    }
  }

  private static float[] extractTextFeature(String text) {
    if (textSession == null || tokenizer == null) return new float[768];
    try {
      Encoding encoding = tokenizer.encode(text);
      long[] ids = encoding.getIds();

      long[][] inputIds = new long[1][ids.length];
      System.arraycopy(ids, 0, inputIds[0], 0, ids.length);

      try (
        OnnxTensor tensor = OnnxTensor.createTensor(env, inputIds);
        OrtSession.Result result = textSession.run(Collections.singletonMap("input_ids", tensor))
      ) {
        float[][] output = (float[][]) result.get("pooler_output").get().getValue();
        return normalizeL2(output[0]);
      }
    } catch (Exception e) {
      e.printStackTrace();
      return new float[768];
    }
  }

  public static List<String> getAutoTags(float[] imageVector) {
    List<String> detected = new ArrayList<>();
    if (cachedTextEmbeddings.isEmpty() || imageVector.length != 768) return detected;

    List<Map.Entry<String, float[]>> entries = new ArrayList<>(cachedTextEmbeddings.entrySet());

    entries.sort((a, b) ->
      Float.compare(dotProduct(imageVector, b.getValue()), dotProduct(imageVector, a.getValue()))
    );

    float threshold = 0.2f;

    for (int i = 0; i < Math.min(3, entries.size()); i++) {
      Map.Entry<String, float[]> entry = entries.get(i);
      float score = dotProduct(imageVector, entry.getValue());

      if (score > threshold) {
        detected.add(entry.getKey());
      }
    }

    return detected;
  }

  private static float dotProduct(float[] a, float[] b) {
    float sum = 0;
    for (int i = 0; i < a.length; i++) sum += a[i] * b[i];
    return sum;
  }

  public static float[] normalizeL2(float[] vector) {
    double sumSq = 0;
    for (float v : vector) sumSq += v * v;
    float length = (float) Math.sqrt(sumSq);
    if (length > 1e-9) {
      for (int i = 0; i < vector.length; i++) vector[i] /= length;
    }
    return vector;
  }
}


=========================================
File: ./backend/src/main/resources/db/migration/V1__Initial_schema.sql
=========================================
CREATE EXTENSION IF NOT EXISTS vector;

CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) DEFAULT 'ROLE_USER'
);

CREATE TABLE IF NOT EXISTS images (
    id BIGSERIAL PRIMARY KEY,
    filename VARCHAR(255) NOT NULL,
    format VARCHAR(10) NOT NULL,
    width INT NOT NULL DEFAULT 0,
    height INT NOT NULL DEFAULT 0,
    hash VARCHAR(64) UNIQUE,
    extraction_status VARCHAR(20) DEFAULT 'PENDING',
    user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
    is_private BOOLEAN DEFAULT false,
    provider VARCHAR(50) DEFAULT 'LOCAL',
    provider_id VARCHAR(100),
    description TEXT,
    photographer_name VARCHAR(255),
    photographer_username VARCHAR(255),
    camera_make VARCHAR(255),
    camera_model VARCHAR(255),
    iso INT,
    location_country VARCHAR(255),
    location_city VARCHAR(255),
    stats_downloads BIGINT DEFAULT 0,
    remote_url TEXT
);

CREATE TABLE IF NOT EXISTS imagedescriptors (
    imageid BIGINT REFERENCES images(id) ON DELETE CASCADE,
    hogvector vector(31),
    hsvvector vector(256),
    rgbvector vector(512),
    labvector vector(512),
    semanticvector vector(768),
    PRIMARY KEY (imageid)
);

CREATE TABLE IF NOT EXISTS imagekeywords (
    imageid BIGINT REFERENCES images(id) ON DELETE CASCADE,
    keyword VARCHAR(255) NOT NULL,
    is_ai_generated BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (imageid, keyword)
);

CREATE INDEX IF NOT EXISTS idx_hnsw_hog ON imagedescriptors USING hnsw (hogvector vector_l2_ops) WITH (m=4, ef_construction=32);
CREATE INDEX IF NOT EXISTS idx_hnsw_hsv ON imagedescriptors USING hnsw (hsvvector vector_l2_ops) WITH (m=16, ef_construction=128);
CREATE INDEX IF NOT EXISTS idx_hnsw_rgb ON imagedescriptors USING hnsw (rgbvector vector_l2_ops) WITH (m=32, ef_construction=256);
CREATE INDEX IF NOT EXISTS idx_hnsw_lab ON imagedescriptors USING hnsw (labvector vector_cosine_ops) WITH (m=32, ef_construction=256);
CREATE INDEX IF NOT EXISTS idx_hnsw_semantic ON imagedescriptors USING hnsw (semanticvector vector_cosine_ops) WITH (m=32, ef_construction=256);

CREATE INDEX IF NOT EXISTS idx_images_provider_id ON images(provider_id);
CREATE INDEX IF NOT EXISTS idx_images_status ON images(extraction_status);


=========================================
File: ./backend/src/main/resources/db/migration/V2__Add_user_approval.sql
=========================================
ALTER TABLE users ADD COLUMN is_approved BOOLEAN DEFAULT FALSE;

-- Ensure existing users (like the original admin) are approved so no one is locked out
UPDATE users SET is_approved = TRUE;


=========================================
File: ./backend/src/main/resources/db/migration/V3__Add_settings_table.sql
=========================================
CREATE TABLE app_settings (
    setting_key VARCHAR(255) PRIMARY KEY,
    setting_value VARCHAR(255) NOT NULL
);

INSERT INTO app_settings (setting_key, setting_value) VALUES ('auto_approve_users', 'false');


=========================================
File: ./backend/src/main/resources/application.yaml
=========================================
spring:
  config:
    import: optional:file:.env.properties
  mvc:
    problemdetails:
      enabled: true
  datasource:
    url: jdbc:postgresql://${HOST_NAME:localhost}:5432/${DATABASE_NAME:pdl-db}
    username: ${DATABASE_USER:default}
    password: ${DATABASE_PASSWORD:password}
    driver-class-name: org.postgresql.Driver
  flyway:
    enabled: true
    locations: classpath:db/migration
    baseline-on-migrate: true
  servlet:
    multipart:
      max-file-size: 100MB
      max-request-size: 100MB

jwt:
  secret: ${JWT_SECRET:8a9b3c4d5e6f7a8b9c0d1e2f3a4b5c6d7e8f9a0b1c2d3e4f5a6b7c8d9e0f1a2b3c4d5e6f7a8b9c0}

app:
  admin:
    username: ${ADMIN_USERNAME:admin}
    password: ${ADMIN_PASSWORD:admin}
  unsplash:
    dataset-dir: ${DATASET_DIR:/var/lib/pdl/datasets}

management:
  endpoint:
    health:
      show-details: always
  endpoints:
    web:
      exposure:
        include: health,info


=========================================
File: ./backend/src/test/java/pdl/backend/auth/AuthControllerWebMvcTests.java
=========================================
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


=========================================
File: ./backend/src/test/java/pdl/backend/gallery/core/GalleryControllerWebMvcTests.java
=========================================
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

  @MockitoBean
  org.springframework.jdbc.core.JdbcTemplate jdbcTemplate;

  @Test
  void getImageReturns404WhenNotFound() throws Exception {
    when(storageService.getImageWithData(42L)).thenReturn(Optional.empty());

    mockMvc.perform(get("/images/{id}", 42L)).andExpect(status().isNotFound());
  }
}


=========================================
File: ./backend/src/test/java/pdl/backend/gallery/search/SearchControllerWebMvcTests.java
=========================================
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


=========================================
File: ./backend/src/test/java/pdl/backend/gallery/search/TagRepositoryTests.java
=========================================
package pdl.backend.gallery.search;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.jdbc.core.JdbcTemplate;

class TagRepositoryTests {

  @Test
  void addKeywordNormalizesAndInsertsOnce() {
    JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);

    // First query: TagRepository checks that the image exists
    when(
      jdbcTemplate.queryForObject(
        eq("SELECT id FROM images WHERE id = ?"),
        eq(Long.class),
        anyLong()
      )
    ).thenReturn(1L);

    TagRepository repo = new TagRepository(jdbcTemplate);

    boolean result = repo.addKeyword(1L, " Big Sun Set ");

    assertThat(result).isTrue();

    ArgumentCaptor<String> keywordCaptor = ArgumentCaptor.forClass(String.class);
    verify(jdbcTemplate).update(
      eq(
        "INSERT INTO imagekeywords (imageid, keyword, is_ai_generated) VALUES (?, ?, ?) ON CONFLICT DO NOTHING"
      ),
      eq(1L),
      keywordCaptor.capture(),
      eq(false)
    );

    // normalizeTag: trim, lower-case, whitespace -> underscore
    assertThat(keywordCaptor.getValue()).isEqualTo("big_sun_set");
  }
}


=========================================
File: ./backend/src/test/java/pdl/backend/ingestion/DatasetIngestControllerSecurityTests.java
=========================================
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
          .param("fileType", "PHOTOS")
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
  void adminCanStartKeywordSync() throws Exception {
    when(unsplashService.getStatus()).thenReturn("IDLE");

    MockMultipartFile mockFile = new MockMultipartFile(
      "file",
      "keywords.tsv",
      "text/plain",
      "header\ndata".getBytes()
    );

    mockMvc
      .perform(
        multipart("/admin/unsplash/upload")
          .file(mockFile)
          .param("limit", "100")
          .param("offset", "0")
          .param("fileType", "KEYWORDS")
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

    verify(unsplashService).syncKeywordsFromFile(any(Path.class), eq(100), eq(0));
  }

  @Test
  void nonAdminGetsForbiddenOnUpload() throws Exception {
    MockMultipartFile mockFile = new MockMultipartFile(
      "file",
      "test",
      "text/plain",
      "data".getBytes()
    );

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


=========================================
File: ./backend/src/test/java/pdl/backend/system/HealthCheckIT.java
=========================================
package pdl.backend.system;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HealthCheckIT {

  @Autowired
  HealthCheck healthCheck;

  @Test
  void healthReturnsNonNullStatus() {
    Health health = healthCheck.health();
    assertThat(health).isNotNull();
  }
}


=========================================
File: ./backend/.gitignore
=========================================
# Maven
target/
!.mvn/wrapper/maven-wrapper.jar
!**/src/main/**/target/
!**/src/test/**/target/

# Spring Boot
HELP.md

# Java Crash Logs
hs_err_pid*
replay_pid*

# IDE Specifics (Java)
.apt_generated
.classpath
.factorypath
.project
.settings
.springBeans
.sts4-cache
*.iws
*.iml
*.ipr
/nbproject/private/
/nbbuild/
/dist/
/nbdist/
/.nb-gradle/
build/

# AI Models
models/


=========================================
File: ./backend/Dockerfile
=========================================
# syntax=docker/dockerfile:1

# Stage 1: Build
FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder
WORKDIR /app
ENV MAVEN_ARGS="-B -ntp"
COPY pom.xml .
# Cache the Maven repository so downloads aren't repeated on every build
RUN --mount=type=cache,target=/root/.m2 mvn $MAVEN_ARGS dependency:go-offline

COPY src ./src
RUN --mount=type=cache,target=/root/.m2 mvn $MAVEN_ARGS clean package -DskipTests

# Stage 2: Run
FROM eclipse-temurin:21-jre
WORKDIR /app

RUN apt-get update && apt-get install -y gosu && rm -rf /var/lib/apt/lists/*

# Create a non-root user without forcing UID 1000 (avoids Ubuntu OS collisions)
RUN groupadd -r appgroup && useradd -r -g appgroup appuser

# Pre-create BOTH images and datasets directories
RUN mkdir -p /var/lib/pdl/images /var/lib/pdl/datasets /var/lib/pdl/models \
  && chown -R appuser:appgroup /var/lib/pdl

COPY --from=builder /app/target/*.jar app.jar
COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

EXPOSE 8080

ENTRYPOINT ["/entrypoint.sh"]
CMD ["java", "-jar", "app.jar"]


=========================================
File: ./backend/pom.xml
=========================================
<?xml version="1.0" encoding="UTF-8" ?>
<project
  xmlns="http://maven.apache.org/POM/4.0.0"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd"
>
  <modelVersion>4.0.0</modelVersion>
  <parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>4.0.5</version>
    <relativePath />
  </parent>
  <groupId>pdl</groupId>
  <artifactId>backend</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <name>backend</name>
  <description>Demo project for Spring Boot</description>

  <properties>
    <java.version>21</java.version>
    <spring-boot.version>4.0.5</spring-boot.version>
    <mockito.version>5.23.0</mockito.version>
    <argLine />
  </properties>

  <dependencies>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-webmvc</artifactId>
    </dependency>

    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-restclient</artifactId>
    </dependency>

    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-webmvc-test</artifactId>
      <scope>test</scope>
    </dependency>

    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-jdbc</artifactId>
    </dependency>
    <dependency>
      <groupId>org.postgresql</groupId>
      <artifactId>postgresql</artifactId>
    </dependency>
    <dependency>
      <groupId>com.pgvector</groupId>
      <artifactId>pgvector</artifactId>
      <version>0.1.6</version>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-flyway</artifactId>
    </dependency>
    <dependency>
      <groupId>org.flywaydb</groupId>
      <artifactId>flyway-database-postgresql</artifactId>
    </dependency>
    <dependency>
      <groupId>org.boofcv</groupId>
      <artifactId>boofcv-core</artifactId>
      <version>1.3.0</version>
    </dependency>
    <dependency>
      <groupId>org.boofcv</groupId>
      <artifactId>boofcv-io</artifactId>
      <version>1.3.0</version>
    </dependency>
    <dependency>
      <groupId>com.twelvemonkeys.imageio</groupId>
      <artifactId>imageio-core</artifactId>
      <version>3.13.1</version>
    </dependency>
    <dependency>
      <groupId>com.twelvemonkeys.common</groupId>
      <artifactId>common-image</artifactId>
      <version>3.13.1</version>
    </dependency>
    <dependency>
      <groupId>com.twelvemonkeys.imageio</groupId>
      <artifactId>imageio-jpeg</artifactId>
      <version>3.13.1</version>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-security-oauth2-resource-server</artifactId>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>

    <dependency>
      <groupId>org.mockito</groupId>
      <artifactId>mockito-core</artifactId>
      <version>${mockito.version}</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>com.microsoft.onnxruntime</groupId>
      <artifactId>onnxruntime</artifactId>
      <version>1.24.3</version>
    </dependency>
    <dependency>
      <groupId>ai.djl.huggingface</groupId>
      <artifactId>tokenizers</artifactId>
      <version>0.33.0</version>
    </dependency>
    <dependency>
      <groupId>org.springframework.security</groupId>
      <artifactId>spring-security-test</artifactId>
      <scope>test</scope>
    </dependency>
  </dependencies>

  <build>
    <plugins>
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
      </plugin>

      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-surefire-plugin</artifactId>
        <version>3.5.5</version>
        <configuration>
          <argLine>
            @{argLine}
            -javaagent:${settings.localRepository}/org/mockito/mockito-core/${mockito.version}/mockito-core-${mockito.version}.jar
            -Xshare:off
          </argLine>
        </configuration>
      </plugin>
    </plugins>
  </build>
</project>


=========================================
File: ./backend/entrypoint.sh
=========================================
#!/bin/bash
set -e

echo "Fixing permissions for /var/lib/pdl..."
chown -R appuser:appgroup /var/lib/pdl

echo "Starting application as appuser..."
exec gosu appuser "$@"


=========================================
File: ./.gitlab-ci.yml
=========================================
image: registry.u-bordeaux.fr/pdl/jdk-mvn-node:latest

variables:
  MAVEN_CLI_OPTS: "--batch-mode --errors --fail-at-end --show-version"
  MAVEN_OPTS: "-Dmaven.repo.local=.m2/repository"

cache:
  paths:
    - .m2/repository/
    - frontend/node_modules/

stages:
  - build
  - test

build_job:
  stage: build
  script:
    - cd backend
    - mvn $MAVEN_CLI_OPTS compile
  artifacts:
    expire_in: 10 min
    paths:
      - backend/target/
  tags:
    - Docker
    - Quirrel

test_job:
  stage: test
  dependencies:
    - build_job
  variables:
    APP_IMAGE_DIRECTORY: "src/main/resources/images"
  script:
    - cd backend
    - mkdir -p src/main/resources/images
    - mvn $MAVEN_CLI_OPTS test
  tags:
    - Docker
    - Quirrel


=========================================
File: ./.prettierignore
=========================================
node_modules/
target/
dist/
build/
.git/


=========================================
File: ./.gitignore
=========================================
# OS generated files
.DS_Store
.DS_Store?
._*
.Spotlight-V100
.Trashes
ehthumbs.db
Thumbs.db

# Global IDEs
.vscode/
.idea/
node_modules/
*.suo
*.ntvs*
*.njsproj
*.sln
*.sw?

# Environment Variables
.env
.env.*
!.env.example
/datasets/keywords.tsv
/datasets/keywords.tsv
/datasets/photos.tsv
/datasets/unsplash-lite.zip

# AI Models
models/


=========================================
File: ./.prettierrc
=========================================
{
  "printWidth": 100,
  "tabWidth": 2,
  "useTabs": false,
  "xmlWhitespaceSensitivity": "ignore",
  "plugins": ["prettier-plugin-java", "@prettier/plugin-xml", "prettier-plugin-sh"]
}


=========================================
File: ./docs/wiki/API-Reference.md
=========================================
# API Reference

All backend endpoints are relative to the application's base URL (e.g., `http://localhost:8080`).

**Note on Error Handling:** The Spring Boot API uses RFC 7807 Problem Details for standardizing error responses. All errors globally return a unique `traceId` for debugging tracing in production.

## 1. Authentication & Users

### Register User

Creates a new account with `ROLE_USER` privileges.

- **URL:** `/auth/register`
- **Method:** `POST`
- **Body:** `{ "username": "alice", "password": "secure" }`
- **Response:** `200 OK` (Success), `400 Bad Request` (Username taken)

### Login

Authenticates a user via `AuthenticationManager` and issues a stateless JSON Web Token (JWT) valid for 24 hours.

- **URL:** `/auth/login`
- **Method:** `POST`
- **Body:** `{ "username": "alice", "password": "secure" }`
- **Response:** `200 OK` with `{ "token": "ey..." }`

---

## 2. Core Image Operations

### List All Images (Paginated)

Retrieves a paginated list of all images. Fully respects privacy scoping and ownership.

- **URL:** `/images`
- **Method:** `GET`
- **Query Params:** `page` (default: 0), `size` (default: 30), `mine` (boolean, default: false)
- **Response:** `200 OK`
  ```json
  {
    "content": [
      { "id": 1, "uploader": "alice", "keywords": ["summer"], "extraction_status": "COMPLETED" }
    ],
    "totalElements": 1,
    "hasNext": false
  }
  ```

### Get Image Content

Retrieves the actual binary payload of a specific image.

- **URL:** `/images/{id}`
- **Method:** `GET`
- **Response:** `200 OK` (Binary image data), `404 Not Found`

### Upload Image (Asynchronous)

Uploads a new image file. Calculates SHA-256 hashes to actively prevent storage duplication. Feature vectors (HOG, HSV, RGB, CIELAB, Semantic) are extracted **asynchronously** in the background using Java 21 Virtual Threads.

- **URL:** `/images`
- **Method:** `POST`
- **Content-Type:** `multipart/form-data`
- **Headers:** `Authorization: Bearer <JWT>`
- **Body:** `file` (Image binary), `keywords` (Optional, list of tags)
- **Response:** `202 Accepted` with `{ "id": 1 }`, `415 Unsupported Media Type`, `409 Conflict` (Duplicate Hash)

### Check Image Processing Status (HTTP Long-Polling)

Retrieves the background processing status. Uses Spring's `DeferredResult` to hold the HTTP connection open securely in memory until the status changes or a 10-second timeout occurs.

- **URL:** `/images/{id}/status`
- **Method:** `GET`
- **Response:** `200 OK` (State Changed) or `202 Accepted` (Timeout - client should reconnect)
  ```json
  {
    "id": 1,
    "extraction_status": "COMPLETED" // PENDING, PROCESSING, COMPLETED, FAILED
  }
  ```

### Delete Image

Permanently deletes an image. Protected by JWT Authority claims (must be original uploader or Admin).

- **URL:** `/images/{id}`
- **Method:** `DELETE`
- **Headers:** `Authorization: Bearer <JWT>`
- **Response:** `204 No Content`, `403 Forbidden`, `404 Not Found`

---

## 3. Metadata & Keywords

### Get Image Metadata

Retrieves file metadata, dimensions, processing status, and associated keywords.

- **URL:** `/images/{id}/metadata`
- **Method:** `GET`
- **Response:** `200 OK`
  ```json
  {
    "Name": "vacation.jpg",
    "Type": "image/jpeg",
    "Size": "800*600",
    "Keywords": ["nature", "ai:outdoor"],
    "Extraction_Status": "COMPLETED"
  }
  ```

### Get/Search Keywords

Endpoints optimized for fast Vue autocomplete rendering.

- **URL:** `/images/keywords` (GET all unique keywords available to user context)
- **URL:** `/images/keywords/search?q=xyz` (Search string mapping)
- **URL:** `/images/keywords/popular?limit=15` (Top tags sorted by count)
- **Method:** `GET`
- **Response:** `200 OK` (Array of strings)

### Add / Delete Keyword

- **URL:** `/images/{id}/keywords?tag=xyz`
- **Method:** `PUT` (Add) / `DELETE` (Remove)
- **Response:** `204 No Content`, `404 Not Found`

---

## 4. Search & Similarity

### Search by Attributes (Paginated)

Finds images matching specific keyword tags dynamically mapped via Spring Data JDBC.

- **URL:** `/images/search`
- **Method:** `GET`
- **Query Params:** `keywords` (List), `page`, `size`, `mine`
- **Response:** `200 OK` (Paginated Object)

### Find Similar Images (Database Target)

Uses `pgvector` HNSW indexes to execute hyper-fast mathematical distance calculations.

- **URL:** `/images/{id}/similar`
- **Method:** `GET`
- **Query Params:** `number` (Limit), `descriptor` (`semantic`, `cielab`, `weighted`, `gradient`, `saturation`, `rgb`)
- **Response:** `200 OK`
  ```json
  [{ "id": 5, "filename": "beach.jpg", "score": 0.9842 }]
  ```

### Ephemeral Similarity Search (Upload Target)

Upload an image strictly to search the database. Vectors are extracted entirely in RAM and passed into the `pgvector` distance operator without ever writing the file to disk or indexing the image in the database.

- **URL:** `/images/search/ephemeral`
- **Method:** `POST`
- **Content-Type:** `multipart/form-data`
- **Body:** `file` (Binary), `number` (Limit), `descriptor` (Algorithm)
- **Response:** `200 OK` (Array of similar images)

---

## 5. Administrative Controls

_Requires `ROLE_ADMIN` JWT claim. Triggers background ThreadPool execution._

- **Start Bulk Import:** `POST /admin/unsplash/import` (Body: `{ "limit": 50, "offset": 0 }`)
- **Start Keyword Import:** `POST /admin/unsplash/import/keyword` (Body: `{ "keyword": "mountain", "limit": 25 }`)
- **Check Admin Status:** `GET /admin/unsplash/status`


=========================================
File: ./docs/wiki/Docker-and-Deployment.md
=========================================
# Docker & Deployment Infrastructure

The application utilizes a sophisticated containerized microservices architecture. Configuration is split across three Docker Compose files depending on the target environment to strictly govern database states and port exposures.

## Architectural Environments

1. **`docker-compose.yml` (Local Development)**
   - Uses `pgvector/pgvector:pg18`.
   - **Decision:** Uses `tmpfs` (temporary file storage in RAM) for the database. This enforces a clean slate and fast teardown for local testing environments, guaranteeing Flyway migrations execute flawlessly from zero.
   - Exposes Backend (`8080`) and Frontend (`3000`) locally to the host machine.
2. **`docker-compose.preview.yml` (Staging / PRs)**
   - Similar to local development, utilizes `tmpfs` for ephemeral database testing to prevent pull requests from corrupting standing staging data.
   - Relies on CI/CD to inject passwords via environment variables.
3. **`docker-compose.prod.yml` (Production)**
   - Configures persistent Docker Volumes (`pgdata`, `backend_images`).
   - Designed to be managed by Dokploy without exposing internal Java/Postgres ports to the outside world directly.

---

## Service Breakdown

### 1. Database (`db`)

- **Image:** `pgvector/pgvector:pg18`
- **Role:** Stores all persistent metadata, keywords, and executes lightning-fast vector distance calculations natively using the HNSW graph indices.
- **Volume:** `pgdata:/var/lib/postgresql/data` (in production).

### 2. Backend (`backend`)

- **Build Context:** `./backend`
- **Dockerfile:** Multi-stage optimized build.
  1. Uses `maven:3.9-eclipse-temurin-21-alpine` to download dependencies and compile the `.jar` utilizing layer caching.
  2. Runs securely on a minimized `eclipse-temurin:21-jre` image as a non-root user (`appuser`). (Note: Standard Ubuntu base is used instead of Alpine to provide the necessary dynamic C libraries required for native ONNX AI model execution).
  3. Pre-creates the `/var/lib/pdl/images` directory and guarantees appropriate permissions for `appuser`.
- **Zero-Trust Subresource Integrity (SRI):** To guarantee immutable deployments and defend against localized supply chain tampering, the backend `.jar` is compiled with a dense cryptographic payload (_The Whole War and Peace Novel.pdf_). Upon initialization, the server executes a strict, blocking SHA-256 checksum of this internal asset to verify deterministic state (`HealthCheck.java`). Any compiler optimizations or malicious tampering that alter this payload's byte-stream will instantly trigger a fatal container exit, forcing a secure `CrashLoopBackOff`.

### 3. Frontend (`frontend`)

- **Build Context:** `./frontend`
- **Dockerfile:** Multi-stage build.
  1. Uses `node` (Alpine) to compile the Vite 8.0 build.
  2. The static `dist` folder is served via a lightweight, secure `nginx:alpine` container.
- **Nginx Configuration (`nginx.conf`):**
  - **SPA Routing:** `try_files $uri $uri/ /index.html;` ensures Vue Router 5 handles HTML5 history mode correctly.
  - **Upload Limits:** Sets `client_max_body_size 100M;` to permit large batch image uploads.
  - **Decision (Reverse Proxy):** Directs all traffic for `/images`, `/auth`, and `/admin` to `http://backend:8080`. This architecture completely bypasses CORS requirement complexity and keeps the backend entirely insulated behind the Nginx proxy in production environments.


=========================================
File: ./docs/wiki/home.md
=========================================
# PDLaser Documentation Wiki

Welcome to the official documentation for the **PDLaser Image Management System (v3a)**.

This wiki contains the comprehensive technical documentation detailing our architectural decisions, backend API, frontend Vue structure, database schema tuning, and deployment infrastructure.

## 📚 Navigation

### [API Reference](API-Reference)

Comprehensive guide to the Spring Boot 4.0.5 REST API, including core image operations, ephemeral similarity endpoints, JWT authentication, and administrative dataset ingestion.

### [Frontend Architecture & Views](Frontend-Architecture-and-Views)

Detailed overview of the Vue 3.5 SPA, exploring the component structure, Vue Router 5 configuration, HTTP Long-Polling logic, Axios interceptors, and the Cruelty Squad easter-egg theme (HUX).

### [Frontend Views Routing](Frontend-views)

In-depth breakdown of the specific route targets (`/gallery`, `/search`, `/profile`, `/admin`, etc.) and the expected client-side data flows on each page.

### [Database Schema & HNSW](Database-Schema)

Extensive details on the PostgreSQL 18 implementation, including the `pgvector` extension usage, table structures (`images`, `imagedescriptors`, `imagekeywords`, `users`), and the mathematically tuned multi-dimensional HNSW vector indexing parameters.

### [Docker & Deployment](Docker-and-Deployment)

Guide to our containerized microservices architecture, explaining the different Docker Compose environments (local `tmpfs`, preview, production), Dokploy integration, Nginx reverse proxying, and Cryptographic Subresource Integrity checks.

---

## ✍️ Wiki Usage

**Make sure to keep this wiki updated at all times.**

### Adding pages

Follow the current format. A wiki page should contain a list of features, technical details, and how to use them. Every wiki page must be written in Markdown. Wiki pages should only be added for **LARGE** architectural parts of the project.

To create a new wiki page, follow these instructions:

1. Edit this home wiki page.
2. Create a new `### [Title](Slug)` link under the Navigation section.
3. Save changes to home.
4. Click on your newly created link.
5. Create the page and add your markdown documentation.

### Code Formatting

The project uses a unified Prettier configuration defined in the root `package.json` to enforce styling rules across both the Java backend and Vue frontend. **Before committing code, always ensure your files are formatted:**

```bash
npm run format
```


=========================================
File: ./docs/wiki/Frontend-views.md
=========================================
# Frontend Views & Routing

This document outlines the Vue Router 5 configuration and the expected behavioral data flows of the client-side pages.

### 1. Gallery View (`/` or `/gallery`)

The primary browsing interface for the archive.

- **Component:** `src/views/GalleryView.vue`
- **Functionality:** Renders a masonry grid of image cards fetched via backend pagination. Displays tags and Long-Polling extraction statuses.
- **Interaction:** Clicking an image navigates to its detail view. Authenticated users can permanently delete their own uploads directly from this grid, triggering optimistic DOM updates. Features an integrated off-canvas sidebar (`AdvancedSearchSidebar.vue`) for configuring Similarity searches.

### 2. Upload View (`/upload`)

Interface for adding new images to the server. Protected by Vue Router guards (Requires Authentication).

- **Component:** `src/views/UploadView.vue`
- **Functionality:** Tracks a queue of up to 10 incoming files. Generates temporary `URL.createObjectURL()` previews. Handles the batch POST requests and visually tracks the Long-Polling completion status of the backend's AI extraction pipeline, showing real-time feedback (Uploading, Extracting, Completed).

### 3. Image Detail View (`/image/:id`)

Deep-dive inspection for a single artifact.

- **Component:** `src/views/ImageDetailView.vue`
- **Functionality:** Displays the high-resolution image, exact metadata dimensions, and allows the rapid inline addition or removal of keywords/tags via the `/keywords` endpoint. Includes a "Find Visually Similar" trigger button to route back to the Gallery with similarity context.

### 4. Authentication Views (`/login` & `/register`)

- **Components:** `LoginView.vue`, `RegisterView.vue`
- **Functionality:** Standardized forms that POST credentials to the backend `/auth` endpoints. On successful login, the JWT is stored in `localStorage` and the user is redirected to their intended route (or Home).

### 5. My Archive (`/profile`)

Personal management dashboard.

- **Component:** `src/views/ProfileView.vue`
- **Functionality:** Automatically calls the `/images?mine=true` endpoint to filter the gallery exclusively to images owned by the currently authenticated JWT subject.

### 6. Admin Dashboard (`/admin`)

Infrastructure management interface. Restricted strictly to accounts possessing the `ROLE_ADMIN` JWT claim.

- **Component:** `src/views/AdminView.vue`
- **Functionality:** Exposes inputs to trigger the backend's background Unsplash Dataset Ingestion engine (`/admin/unsplash/import`). Polls the active status of the worker threads to monitor dataset batching.

### 7. About / System Architecture (`/about`)

- **Component:** `src/views/AboutView.vue`
- **Functionality:** A technical summary page explaining the pgvector indexing, HNSW algorithms, SigLIP 2 implementations, and providing live mathematical estimations of the current Database Tensor Memory Size based on active indexing dimensions retrieved dynamically from the API.


=========================================
File: ./docs/wiki/Frontend-Architecture-and-Views.md
=========================================
# Frontend Architecture

The frontend is a strictly-typed Single Page Application (SPA) built with **Vue.js 3.5 (Composition API)**, **TypeScript 6.0**, and **Vite 8.0**.

## Global Systems & Architectural Decisions

### Axios Interceptors & State (`http-client.ts` & `useAuth.ts`)

API calls are routed through a globally configured Axios instance.

- **Proxying:** During local development, Vite proxies requests to the backend. In production, the Nginx container proxies these routes. This removes the need for CORS headers.
- **Authorization Header:** The request interceptor automatically attaches `Bearer <token>` to outbound requests if a JWT exists in `localStorage`.
- **401 Handlers:** If the server rejects a token (expired/invalid signature), the response interceptor instantly flushes `localStorage` and forces a hard redirect to the `/login` view, maintaining strict state security across the entire SPA.

### Asynchronous Status Tracking (`useImageStatus.ts`)

Due to the intense computational processing required for feature extraction (Lanczos3 downsampling, BoofCV histograms, SigLIP 2 AI logits), the frontend utilizes a custom composable (`useImageStatus`) to handle background tracking of uploaded images.

**Decision (HTTP Long-Polling):** Instead of utilizing WebSockets (which require complex state management and infrastructure like Redis), we built an optimized Long-Polling loop. The client sends a request to `/images/{id}/status`, and the Spring Boot backend (`UploadStatusTracker.java`) hangs the connection securely using `DeferredResult` until the Virtual Threads finish the descriptor extraction. This updates the UI instantly without aggressively spamming the network with polling requests.

### Global Themes & Easter Egg (Cruelty Squad HUX)

The `App.vue` component contains a global theme manager featuring standard **Light** and **Dark** modes, alongside a hidden **"Cruelty"** aesthetic mode (inspired by the game _Cruelty Squad_).

- **Toggles:** Handled via top-nav buttons.
- **Effects (Hostile User Experience):** The Cruelty theme overrides CSS variables to bright neon colors, pixelates images with CRT scanline masks, skews fonts to 'Impact', and implements custom procedural `AudioContext` sawtooth sound effects on every mouse click.
- **Persistence:** Theme state is saved in `localStorage` to persist seamlessly across reloads.


=========================================
File: ./docs/wiki/Database-Schema.md
=========================================
# Database Schema & HNSW Graph Indexing

The application persists data using **PostgreSQL 18**. It heavily leverages the `pgvector` extension to store multi-dimensional mathematical vectors for content-based image retrieval (similarity search).

_Note: The database schema is strictly version-controlled and automatically migrated on initialization using **Flyway** (`src/main/resources/db/migration`)._

## Tables

### 1. `users` (Introduced V2)

Handles authentication and role-based access control.
| Column | Type | Constraints | Description |
|---|---|---|---|
| `id` | BIGSERIAL | PRIMARY KEY | Unique user identifier. |
| `username` | VARCHAR(255) | UNIQUE, NOT NULL | Account login name. |
| `password` | VARCHAR(255) | NOT NULL | BCrypt encoded password hash. |
| `role` | VARCHAR(50) | DEFAULT 'ROLE_USER' | JWT Authority claim (e.g., `ROLE_ADMIN`). |

### 2. `images` (Introduced V1)

Stores core metadata. The actual binary content is safely persisted on the filesystem (`/var/lib/pdl/images`), while this table maintains references to prevent DB bloat.
| Column | Type | Constraints | Description |
|---|---|---|---|
| `id` | BIGSERIAL | PRIMARY KEY | Unique identifier. |
| `filename` | VARCHAR(255) | NOT NULL | Original filename. |
| `format` | VARCHAR(10) | NOT NULL | File extension (`jpeg`, `png`). |
| `width` | INT | DEFAULT 0 | Image width in pixels (Extracted via fast headers). |
| `height` | INT | DEFAULT 0 | Image height in pixels. |
| `hash` | VARCHAR(64) | UNIQUE | SHA-256 hash used to prevent storage duplicate uploads. |
| `extraction_status` | VARCHAR(20)| DEFAULT 'PENDING' | Status of async descriptor extraction. |
| `user_id` | BIGINT | FK (`users.id`) | Links to the uploader account (`ON DELETE CASCADE`). |
| `is_private` | BOOLEAN | DEFAULT false | Controls public visibility scoping across queries. |

### 3. `imagedescriptors` (Introduced V1, Expanded V3)

Stores the mathematically extracted feature vectors (calculated asynchronously via BoofCV 1.3.0 and ONNX 1.17.1) used for visual similarity scoring.
| Column | Type | Constraints | Description |
|---|---|---|---|
| `imageid` | BIGINT | PK, FK (`images.id`) | Links to the parent image (`ON DELETE CASCADE`). |
| `hogvector` | vector(31) | | Histogram of Oriented Gradients (Shape/Edge data). |
| `hsvvector` | vector(256) | | Hue/Saturation/Value histogram (Color Intensity). |
| `rgbvector` | vector(512) | | Standard RGB color distribution. |
| `labvector` | vector(512) | | CIELAB color space distribution (Human vision mapping). |
| `semanticvector`| vector(768)| | Semantic Logits extracted from SigLIP 2 AI model. |

## Architectural Decision: HNSW Indexes

Instead of utilizing standard B-Trees or flat IVFFlat indices, this table utilizes `hnsw` (Hierarchical Navigable Small World) indexes on all vector columns to radically accelerate database-level querying without pulling data into Java memory.

The index parameters were carefully tuned based on their respective dimensions and distance operators to balance build-time RAM with ultra-fast search recall:

- **HOG (31D):** `vector_l2_ops` | `m=4, ef_construction=32`
- **HSV (256D):** `vector_l2_ops` | `m=16, ef_construction=128`
- **RGB (512D):** `vector_l2_ops` | `m=32, ef_construction=256`
- **CIELAB (512D):** `vector_cosine_ops` | `m=32, ef_construction=256`
- **Semantic (1000D):** `vector_cosine_ops` | `m=32, ef_construction=256`

### 4. `imagekeywords` (Introduced V1)

Handles many-to-many tag relationships. Tags are automatically normalized (converted to lowercase, spaces replaced by underscores) before insertion. AI tags are prefixed with `ai:`.
| Column | Type | Constraints | Description |
|---|---|---|---|
| `imageid` | BIGINT | PK, FK (`images.id`) | Links to the parent image (`ON DELETE CASCADE`). |
| `keyword` | VARCHAR(255) | PK | The text tag (e.g., `nature`, `ai:car`). |


=========================================
File: ./docs/requirements.tex
=========================================
\documentclass[12pt, a4paper]{article}
\usepackage[utf8]{inputenc}
\usepackage[T1]{fontenc}
\usepackage{lmodern}
\usepackage{geometry}
\geometry{margin=2.5cm}
\usepackage{hyperref}
\hypersetup{
    colorlinks=true,
    linkcolor=blue,
    urlcolor=cyan,
    pdftitle={Software Development Project - Requirements Document}
}

\begin{document}

\title{\textbf{Software Development Project - Requirements Document}}
\author{
    \textbf{Lucas Koumasonas \& Nikita Semenov} \\
    L3 Computer Science -- University of Bordeaux
}
\date{}
\maketitle

\section*{Introduction}
This document presents the rigorous requirements for developing an AI-augmented image similarity search application using a client-server architecture backed by PostgreSQL 18 for high-dimensional vector indexing.

The application processes color images saved in the following formats:
\begin{itemize}
    \item JPEG / JPG
    \item PNG
\end{itemize}

\subsection*{Requirement Classifications}
To clarify the state of each requirement relative to the initial specifications, the following tags are used throughout the document.

\begin{itemize}
    \item \textbf{[Original to Requirement X]}: The requirement remains functionally identical to the initial specifications and is fully implemented.
    \item \textbf{[Amended (Up) to Requirement X]}: The requirement was modified to provide a significantly better, more optimized, or more feature-rich implementation than originally asked.
    \item \textbf{[Extension]}: A completely new feature not present in the original specifications.
    \item \textbf{[Extended Amendment to Requirement X]}: Placed in a dedicated section at the end of this document, these highlight highly complex architectural shifts or advanced features that drastically expand upon a base requirement.
\end{itemize}

\vspace{1em}
\noindent\fbox{%
    \parbox{\textwidth}{%
        \textbf{Important Note on Downgrades:} There are absolutely no downgraded or omitted requirements in this release. Every single original requirement has been either fully implemented or significantly exceeded.
    }%
}

\clearpage

\section{Architectural \& Organizational Decisions}
To ensure the system scales efficiently under the load of computational computer vision tasks and machine learning inference, the following organizational standards were established:

\begin{itemize}
    \item \textbf{Decoupled Background Execution:} Image vectorization (BoofCV) and Semantic extraction (ONNX) are computationally blocking. We deliberately offloaded these tasks to background Virtual Threads (Java 21) via a custom \texttt{ThreadPoolTaskExecutor}. This ensures the main Tomcat HTTP event loop is never blocked by mathematical computations.
    \item \textbf{Ephemeral Database Environments:} For local and staging developments, we strictly utilize \texttt{tmpfs} (RAM-based) Docker mounts for PostgreSQL 18. This organizational decision enforces a pristine state upon every container boot, ensuring Flyway schema migrations are always tested from a true zero-state, preventing configuration drift.
    \item \textbf{HNSW Vector Indexing:} Instead of using traditional B-Trees or flat indices, we implemented Hierarchical Navigable Small World (HNSW) graphs in PostgreSQL 18 via \texttt{pgvector}. We specifically tuned the `m` and `ef\_construction` parameters to favor read-speed and recall accuracy for our 768-dimensional semantic vectors.
    \item \textbf{Stateless Long-Polling:} To provide the frontend with real-time feedback on background image processing without the massive infrastructure overhead of WebSockets and Redis, we implemented HTTP Long-Polling using Spring's \texttt{DeferredResult}.
\end{itemize}

\clearpage

\section{Common Core}

\subsection*{Server}

\noindent\textbf{Requirement 1 : [Amended (Up) to Requirement 1] Initialize a set of images present on the server}\\
\textbf{Description :} When the server is launched, it verifies the existence of the image directory. \textbf{Amendment:} The server calculates a SHA-256 hash of the binary file to ensure images are not duplicated in storage. To optimize initialization, the server utilizes fast header-only stream reading (\texttt{ImageReader}) to extract width/height dimensions instantly without fully loading multi-megabyte images into RAM. A default \texttt{admin} user is also seeded into the database automatically.
\vspace{1em}

\noindent\textbf{Requirement 2 : [Amended (Up) to Requirement 2] Manage images present on the server}\\
\textbf{Description :} The server manages a set of images, allowing access to raw data and metadata. \textbf{Amendment:} Image management is now strictly bound to an authentication context. Images track their uploader's \texttt{user\_id} and support privacy scoping (public vs. private visibility) to secure multi-tenant use cases.
\vspace{1em}

\noindent\textbf{Requirement 3 : [Amended (Up) to Requirement 3] Index an image}\\
\textbf{Description :} The server calculates an image's descriptors. \textbf{Amendment:} Descriptors are mapped to PostgreSQL 18 \texttt{pgvector} formats. The comprehensively supported descriptors are:
\begin{enumerate}
    \item 1D Histogram of Oriented Gradients (HOG - 31 dimensions).
    \item 2D Hue/Saturation Histogram (HSV - 256 dimensions).
    \item 3D RGB Histogram (512 dimensions).
    \item 3D CIELAB Color Space Histogram (512 dimensions).
    \item \textbf{[New]} 1D Semantic Logits Vector (768 dimensions via SigLIP 2 AI).
\end{enumerate}
\vspace{1em}

\noindent\textbf{Requirement 4 : [Amended (Up) to Requirement 4] Search for images similar to a given image}\\
\textbf{Description :} The server allows constructing a sorted list of the N most similar images. \textbf{Amendment:} Now supports deep semantic similarity searches using AI-generated contextual data. The search is accelerated at the database level utilizing HNSW graphs mapping L2 and Cosine distances directly within Postgres.
\vspace{1em}

\noindent\textbf{Requirement 5 : [Original to Requirement 5] Search for images with a list of given attributes}\\
\textbf{Description :} The server constructs a list of images possessing one or more attributes among: File name, Image size, Format, and a set of relational keywords.
\vspace{1em}

\subsection*{Communication}

\noindent\textbf{Requirement 6 : [Amended (Up) to Requirement 6] Transfer the list of existing images}\\
\textbf{Description :} The list of available images is sent when receiving a \texttt{GET} request at \texttt{/images}. \textbf{Amendment:} To heavily optimize frontend DOM rendering, the result is now \textbf{paginated} (\texttt{page}, \texttt{size}) and provided in JSON format, avoiding the N+1 query problem. It natively filters based on the user's JWT authentication token and supports ownership isolation (\texttt{mine} query parameter).
\vspace{1em}

\noindent\textbf{Requirement 7 : [Amended (Up) to Requirement 7] Add an image}\\
\textbf{Description :} Sending a \texttt{POST} request to \texttt{/images} with \texttt{multipart/form-data} adds an image to the server. \textbf{Amendment:} The endpoint accepts an optional list of keywords to tag images immediately upon upload. It leverages asynchronous processing and responds with \textbf{202 Accepted}. Additionally, the server utilizes a local AI model to automatically append inferred semantic tags (prefixed with \texttt{ai:}) to the image metadata.
\vspace{1em}

\noindent\textbf{Requirement 8 : [Original to Requirement 8] Retrieve an image}\\
\textbf{Description :} Sending a \texttt{GET} request to \texttt{/images/id} returns the binary payload of the image stored with the identifier \texttt{id}.
\vspace{1em}

\noindent\textbf{Requirement 9 : [Amended (Up) to Requirement 9] Delete an image}\\
\textbf{Description :} Sending a \texttt{DELETE} request to \texttt{/images/id} deletes the image. \textbf{Amendment:} Deletion is strictly protected by JWT claims. Only the original uploader (or a system administrator) possesses the authority to delete a specific physical file and its database record.
\vspace{1em}

\noindent\textbf{Requirement 10 : [Amended (Up) to Requirement 10] Transfer the list of most similar images}\\
\textbf{Description :} A \texttt{GET} request to \texttt{/images/id/similar} returns a JSON array of the N most similar images. Valid algorithms now natively include \texttt{semantic}, \texttt{cielab}, and a hybrid \texttt{weighted} Euclidean fusion.
\vspace{1em}

\noindent\textbf{Requirement 11 : [Original to Requirement 11] Retrieve image metadata}\\
\textbf{Description :} A \texttt{GET} request to \texttt{/images/id/metadata} returns metadata including Name, Type, Size, Keywords, and the background \texttt{Extraction\_Status}.
\vspace{1em}

\noindent\textbf{Requirement 12 : [Original to Requirement 12] Add a keyword to an image}\\
\textbf{Description :} A \texttt{PUT} request to \texttt{/images/id/keywords?tag=TAG} securely adds the keyword TAG to the image metadata.
\vspace{1em}

\noindent\textbf{Requirement 13 : [Original to Requirement 13] Delete a keyword from an image}\\
\textbf{Description :} A \texttt{DELETE} request to \texttt{/images/id/keywords?tag=TAG} removes the keyword TAG from the image.
\vspace{1em}

\noindent\textbf{Requirement 14 : [Amended (Up) to Requirement 14] Transfer the list of keywords}\\
\textbf{Description :} A \texttt{GET} request to \texttt{/images/keywords} returns the distinct list of all unique keywords. \textbf{Amendment:} Supports sophisticated search queries (\texttt{/search?q=}) and limits output to the most popular keywords (\texttt{/popular}) to drive a high-performance Vue 3 frontend autocomplete component.
\vspace{1em}

\noindent\textbf{Requirement 15 : [Amended (Up) to Requirement 15] Search for images by attributes}\\
\textbf{Description :} During a \texttt{GET} request to \texttt{/images/search}, the client passes a list of attributes via standard Query Parameters, adhering to modern RESTful standards.

\clearpage

\section{Client}

\subsection*{User Interface Operations}

\noindent\textbf{Requirement 16 : [Amended (Up) to Requirement 16] Browse images available on the server}\\
\textbf{Description :} The user can view available images displayed in a responsive Gallery view. \textbf{Amendment:} To support massive Unsplash datasets, the gallery UI dynamically handles backend pagination logic, offering Next/Prev controls to prevent DOM overload.
\vspace{1em}

\noindent\textbf{Requirement 17 : [Amended (Up) to Requirement 17] Select an image and display similar images}\\
\textbf{Description :} The user can request visually similar images from the database. \textbf{Amendment:} The UI now displays a mathematically calculated "Percentage Match" based on the inverse distance returned by the pgvector operator.
\vspace{1em}

\noindent\textbf{Requirement 18 : [Original to Requirement 18] Save an image to disk}\\
\textbf{Description :} The user can save a loaded image to their local file system using native browser rendering behavior.
\vspace{1em}

\noindent\textbf{Requirement 19 : [Amended (Up) to Requirement 19] Add an image to the server}\\
\textbf{Description :} The user can upload images. \textbf{Amendment:} The UI tracks a multi-file queue (up to 10 images) and uses a dedicated \texttt{useImageStatus} composable to dynamically poll the background processing status, displaying precise visual badges (Uploading, Extracting, Completed).
\vspace{1em}

\noindent\textbf{Requirement 20 : [Original to Requirement 20] Delete an image}\\
\textbf{Description :} The user can permanently delete an image from the server. The Vue 3.5 reactive engine executes an optimistic DOM update to remove the element instantly.
\vspace{1em}

\noindent\textbf{Requirement 21 \& 22 : [Original] Add / Delete Keywords}\\
\textbf{Description :} The user can add or delete tags associated with an image directly from the detailed view interface.
\vspace{1em}

\noindent\textbf{Requirement 23 : [Original to Requirement 23] Search for an image by its attributes}\\
\textbf{Description :} The client offers an advanced slide-out Search Sidebar to dynamically filter images by text attributes or trigger mathematical similarity searches.

\clearpage

\section{Non-Functional Requirements}

\subsection*{Environment \& Quality}

\noindent\textbf{Requirement 24 : [Amended (Up) to Requirement 24] Continuous Integration \& Code Quality}\\
\textbf{Description :} Gitlab CI/CD pipelines are utilized for compilation and automated testing on isolated Docker runners. \textbf{Amendment:} Strict automated code formatting is enforced globally via Prettier integrations (\texttt{.prettierrc}) to ensure uniform style across all pull requests.
\vspace{1em}

\noindent\textbf{Requirement 25 : [Original to Requirement 25] Server compatibility}\\
\textbf{Description :} The server is strictly bound to Java (JDK 21) utilizing Spring Boot 4.0.5, BoofCV 1.3.0, ONNX Runtime 1.17.1, PostgreSQL 18, and \texttt{pgvector} 0.1.6. Spring Data JDBC manages the persistence layer.
\vspace{1em}

\noindent\textbf{Requirement 26 : [Original to Requirement 26] Client compatibility}\\
\textbf{Description :} The SPA client is strictly typed in TypeScript 6.0 and relies on the Vue.js 3.5 framework (Composition API) built via Vite 8.0.
\vspace{1em}

\noindent\textbf{Requirement 27 : [Original to Requirement 27] Documentation}\\
\textbf{Description :} A comprehensively expanded \texttt{README.md} and fully maintained GitLab Wiki tracks architectural diagrams, design decisions, endpoints, and deployment guidelines.

\clearpage

\section{Extensions}

\subsection*{Architecture \& Data Integrity}

\noindent\textbf{Requirement 28 : [Extension] Global ProblemDetail Exception Handling (RFC 7807)}\\
\textbf{Description :} The application features a globally centralized \texttt{ErrorHandler.java} class. All internal server exceptions are gracefully captured and translated into standardized RFC 7807 \texttt{ProblemDetail} JSON responses, appending a unique \texttt{traceId} to aid rapid debugging in production.
\vspace{1em}

\noindent\textbf{Requirement 29 : [Extension] SHA-256 Deduplication}\\
\textbf{Description :} To prevent identical images from bloating the database and filesystem, the server automatically calculates a SHA-256 hash of the binary file during upload.
\vspace{1em}

\noindent\textbf{Requirement 30 : [Extension] Automated Schema Migrations (Flyway)}\\
\textbf{Description :} The database schema is version-controlled via Flyway. Upon boot, the application automatically executes migrations (\texttt{V1} through \texttt{V3}) to ensure tables, relations, and HNSW indexes are perfectly synced with the backend codebase.
\vspace{1em}

\noindent\textbf{Requirement 31 : [Extension] Docker Orchestration \& Nginx Reverse Proxy}\\
\textbf{Description :} The stack is fully containerized. To bypass CORS complexity and ensure production-grade security, the compiled Vue frontend is served via an \textbf{Nginx} container. Nginx intercepts all traffic, routing \texttt{/images}, \texttt{/auth}, and \texttt{/admin} directly to the backend container, keeping the Java application entirely hidden from public port exposure.

\subsection*{Security \& Dataset Management}

\noindent\textbf{Requirement 32 : [Extension] Stateless JWT Authentication \& Authorization}\\
\textbf{Description :} The application features a robust role-based access control (RBAC) system. Users can register and authenticate to receive JSON Web Tokens (JWT). The system supports \texttt{ROLE\_USER} and \texttt{ROLE\_ADMIN} privileges, tying image ownership directly to authenticated accounts to support private visibility scopes and protect deletion endpoints.
\vspace{1em}

\noindent\textbf{Requirement 33 : [Extension] Admin Unsplash Dataset Ingestion}\\
\textbf{Description :} Authorized administrators have access to an automated asynchronous ingestion engine capable of securely downloading, parsing, and vectorizing massive datasets (e.g., Unsplash TSV metadata) directly into the system's storage and \texttt{pgvector} indexes without blocking the main event loop.
\vspace{1em}

\noindent\textbf{Requirement 34 : [Extension] Client Profile \& Administration Interfaces}\\
\textbf{Description :} The client provides a dedicated \texttt{/profile} interface for authenticated users to view, manage, and permanently delete their own uploaded images ("My Archive"), as well as an \texttt{/admin} view for elevated users to trigger the dataset ingestion API.

\subsection*{User Interface \& Experience}

\noindent\textbf{Requirement 35 : [Extension] Cruelty Squad Audio-Visual Theme (HUX)}\\
\textbf{Description :} A hidden Easter egg theme toggle exists in the frontend. When activated, it drastically alters the CSS rendering engine to mimic the abrasive aesthetic of "Cruelty Squad", applying CRT scanlines, and overriding global DOM events to procedurally generate raw sawtooth frequency sound effects via the Web Audio API.

\clearpage

\section{Extended Amendments}
\textit{This section highlights highly complex architectural implementations that drastically expand upon and modernize the original foundational requirements.}

\subsection*{Advanced Image Processing \& AI}

\noindent\textbf{Requirement 36 : [Extended Amendment to Requirement 3] Lanczos3 Image Pre-processing}\\
\textbf{Description :} To standardize and drastically accelerate backend image feature extraction, the application intercepts uploaded images and automatically resamples them to a uniform spatial resolution ($256\times256$) leveraging the high-quality Lanczos3 algorithm before any descriptors are computed.
\vspace{1em}

\noindent\textbf{Requirement 37 : [Extended Amendment to Requirement 3] Semantic Vectors \& SigLIP 2 AI Auto-Tagging}\\
\textbf{Description :} Beyond manual keyword tagging, the backend integrates Microsoft's ONNX Runtime (v1.17.1) to execute a SigLIP 2 Vision Transformer locally. Uploaded images are automatically analyzed to extract 768-dimensional semantic logits, which are used both for highly accurate AI context matching and to automatically append probable \texttt{ai:} prefixed tags to the image metadata.
\vspace{1em}

\noindent\textbf{Requirement 38 : [Extended Amendment to Requirement 3 \& 7] Asynchronous Image Processing \& Long Polling}\\
\textbf{Description :} Descriptor extraction tasks are offloaded to background execution threads via a custom \texttt{ThreadPoolTaskExecutor}. To track this securely without the overhead of WebSockets, the client utilizes a \textbf{Long-Polling} mechanism (\texttt{UploadStatusTracker.java}) via \texttt{DeferredResult}. The HTTP connection is held open until the server finishes processing, providing instant frontend feedback without aggressively spamming the REST API.
\vspace{1.5em}

\subsection*{Advanced Search \& Vector Mathematics}

\noindent\textbf{Requirement 39 : [Extended Amendment to Requirement 4 \& 10] pgvector HNSW Indexing}\\
\textbf{Description :} The backend directly saves feature arrays into PostgreSQL 18 \texttt{vector} columns. \textbf{Hierarchical Navigable Small World (HNSW)} indexes are utilized to allow lightning-fast similarity searching at the database level. Specifically, \texttt{vector\_cosine\_ops} is used for CIELAB and Semantic vectors, while \texttt{vector\_l2\_ops} is optimized for HOG, HSV, and RGB.
\vspace{1em}

\noindent\textbf{Requirement 40 : [Extended Amendment to Requirement 4 \& 10] CIELAB Color Space Mapping}\\
\textbf{Description :} The CIELAB color space (which mathematically maps directly to human visual perception, separating lightness from color channels) was engineered and integrated as an entirely new advanced 512-dimensional feature descriptor.
\vspace{1em}

\noindent\textbf{Requirement 41 : [Extended Amendment to Requirement 17] Ephemeral Similarity Search}\\
\textbf{Description :} The client UI permits users to upload an image strictly to execute an immediate similarity query. The backend dynamically intercepts the stream, extracts the vectors on the fly in RAM, executes the \texttt{pgvector} comparison, and returns the results \textit{without} ever inserting or indexing the source image in the database.

\clearpage

\section{Critical Compliance \& Structural Integrity}

\noindent\textbf{Requirement 42 : [Extended Amendment] Zero-Trust Cryptographic Integrity Validation}\\
\textbf{Description :} To prevent supply chain tampering and ensure compliance with immutable deployment strategies, the application boot sequence incorporates a zero-trust structural integrity check. The server actively streams and cryptographically hashes a dense, multi-megabyte internal static asset (\textit{The Whole War and Peace Novel.pdf}) packaged within the compiled artifact, verifying it against a hardcoded SHA-256 constraint (\texttt{4e22...a6bb}). If this deterministic baseline drifts, or if the asset is omitted by a compiler attempting to illegally optimize container size, the application instantly triggers a fatal execution halt to protect the runtime memory state.
\vspace{1.5em}

\noindent\textbf{Requirement 43 : [Extended Amendment] Enterprise DevOps Operability Validation Document}\\
\textbf{Description :} To guarantee 99.999\% uptime, a secondary \texttt{enterprise\_requirements.tex} document is actively maintained within this repository. It evaluates the systemic limitations of our current infrastructure and establishes the strategic guidance pipelines required by our lead Cloud Architects.

\end{document}

=========================================
File: ./docs/enterprise_requirements.tex
=========================================
\documentclass[12pt, a4paper]{article}
\usepackage[utf8]{inputenc}
\usepackage[T1]{fontenc}
\usepackage{lmodern}
\usepackage{geometry}
\geometry{margin=2.5cm}
\usepackage{hyperref}
\usepackage{titlesec}
\usepackage{enumitem}
\usepackage{abstract}

% Academic formatting tweaks
\titleformat{\section}{\large\bfseries\scshape}{\thesection.}{1em}{}
\titleformat{\subsection}{\normalsize\bfseries\itshape}{\thesubsection.}{1em}{}

\begin{document}

\title{\textbf{Systemic Limitations of Monolithic Efficiency in Image Retrieval Architectures: A Paradigm Shift Towards Holistic Poly-Cloud Orchestration}}
\author{
    \textbf{Lucas Koumasonas} \\
    \textit{Lead DevOps \& Chaos Engineering Architect} \vspace{0.5em} \\
    \textbf{Nikita Semenov} \\
    \textit{Principal Cloud Orchestration \& Distributed Systems Engineer} \vspace{1em} \\
    \textit{Department of Enterprise Scalability \& Distributed Systems} \\
    \textit{Based on a Longitudinal Study of 35.5 Years of Industry Experience}
}
\date{\today}
\maketitle

\begin{abstract}
\noindent \textbf{Abstract---}Recent evaluations of the PDLaser v3a Image Management System reveal an architecture functioning with anomalous efficiency. While the application successfully executes content-based image retrieval via PostgreSQL 18 \texttt{pgvector} HNSW indexing and asynchronous BoofCV descriptor extraction, empirical observations indicate a critical lack of operational complexity. "It works" is insufficient as a non-functional enterprise requirement. This paper presents a very reasonable architectural audit of PDLaser v3a, identifying systemic flaws such as suboptimal cloud-billing generation, the circumvention of poly-cloud orchestration overhead, and the utilization of overly simplified HTTP Long-Polling mechanisms. Finally, we propose a strategic framework to transition the current architecture into a fail-safe, redundant, hybrid-native distributed monolith utilizing Kubernetes, Kafka, and cryptographic ledger integrations to maximize quarterly infrastructure expenditures.
\end{abstract}

\vspace{2em}

\pagebreak

\section{Introduction}
The modern enterprise cloud ecosystem is predicated on the fundamental principle that scalability requires an exponential increase in moving parts. If an organization intends to deploy highly available systems, standard orchestration is insufficient; architectural design must proactively anticipate the integration of Cassandra, Kafka, and Spark clusters. 

Currently, PDLaser v3a operates as a statically typed distributed monolith leveraging Java 21, Spring Boot 4.0.5, and a Vue.js 3.5 single-page application compiled via Vite 8.0. While this stack circumvents the inherent volatility of children's magic (e.g., dynamically typed serverless microservices), our audit reveals that the deployment pipeline severely under-utilizes cloud vendor Service Level Agreements (SLAs). This paper outlines the critical limitations of the current implementation and provides a rigorous, V-Model-compliant roadmap for future iterations.

\section{Empirical Analysis of Systemic Limitations}

Our analysis isolates three primary anti-patterns currently active within the PDLaser repository. These mechanisms incorrectly prioritize computational efficiency over infrastructure expansiveness.

\subsection{Storage Ephemerality and Billing Evasion}
In local and staging environments, the application utilizes \texttt{tmpfs} Docker mounts, forcing the PostgreSQL database to operate entirely within volatile RAM. Consequently, Flyway migration scripts reconstruct the schema from a zero-state upon every initialization.
\begin{itemize}
    \item \textbf{The Limitation:} This paradigm is dangerously fast and completely bypasses persistent I/O cloud billing mechanisms. Historical data migration records establish that localized Euclidean distance operations should incur a baseline expenditure of approximately 180,000 AWS credits per fiscal quarter. The current \texttt{tmpfs} implementation represents a severe liquidity bottleneck.
\end{itemize}

\subsection{The Archaic Abstraction of Long-Polling}
To monitor asynchronous BoofCV feature extraction and ONNX SigLIP 2 semantics, the \texttt{VisionProcessor.java} relies on an \texttt{UploadStatusTracker} Long-Polling mechanism using \texttt{DeferredResult}.
\begin{itemize}
    \item \textbf{The Limitation:} The client simply maintains an open HTTP connection until a 200 OK response is yielded. This lacks the requisite layers of enterprise abstraction. In a production-grade system, these events must be published to a distributed Kafka event-stream, monitored by a secondary Redis instance, validated against a Zookeeper quorum, and finally pushed to the client via WebSockets.
\end{itemize}

\subsection{Hostile User Experience (HUX) as a Load Balancer}
The frontend SPA incorporates a "Cruelty Squad" theme toggle that overrides the CSS rendering engine to generate procedural CRT scanlines and hooks into the Web Audio API to emit raw sawtooth wave frequencies on every DOM click event.
\begin{itemize}
    \item \textbf{The Advantage:} While initially flagged as a bug, the engineering team has classified this as a "Hostile User Experience" (HUX) load-balancing strategy. By making the interface physically painful to look at and listen to, user retention drops by 94\%, effectively acting as a client-side rate limiter and saving database read cycles. We recommend standardizing this approach across all B2B portals.
\end{itemize}

\subsection{Cryptographic Structural Integrity and Computational Under-Utilization}
To enforce a zero-trust boot sequence, the \texttt{HealthCheck.java} actuator dynamically streams and calculates the SHA-256 hash of a dense, multi-megabyte internal payload (\textit{The Whole War and Peace Novel.pdf}) against the static signature \texttt{4e22f003e05...}.
\begin{itemize}
    \item \textbf{The Limitation:} While synchronously calculating a cryptographic hash of 1,200 pages of text successfully introduces excellent, billable artificial startup latency, it is drastically under-engineered. 
    \item \textbf{The Reality:} Verifying 19th-century literature using standard SHA-256 is vulnerable to quantum decryption. Furthermore, this hashing must not be executed locally on the container. The byte-stream should be published via gRPC to a dedicated Apache Spark cluster for distributed cryptographic consensus before the backend is permitted to boot.
\end{itemize}

\section{Strategic Guidance and Next-Gen Architecture}

To rectify these limitations, future development sprints will abandon Agile methodologies and adopt a strict V-Model deployment pipeline. The following architectural shifts are mandatory.

\subsection{Phase 1: Migration to Poly-Cloud Kubernetes}
The current Nginx reverse proxy Docker configuration is highly stable and must therefore be deprecated immediately. 
\begin{itemize}
    \item \textbf{Implementation Strategy:} The stack will be migrated to a fully managed Kubernetes cluster. This introduces the requisite orchestration overhead and grants the organization the portability necessary to move production outages between AWS and GCP within minutes.
\end{itemize}

\subsection{Phase 2: Telemetry and Schrödinger’s Latency}
Error handling is currently standardized via RFC 7807 \texttt{ProblemDetail} JSON responses equipped with unique \texttt{traceId} tags. This format is far too legible for a dedicated DevSecOps pod.
\begin{itemize}
    \item \textbf{Implementation Strategy:} Grafana and Loki pipelines will be integrated and aggressively scaled until log ingress costs demonstrably exceed the revenue generated by the application's uptime. This will allow the platform to achieve a state of Schrödinger's Latency: \textit{If a Grafana dashboard flickers yellow while no one is watching, did latency truly spike?}
\end{itemize}

\pagebreak

\section{Conclusion}
The PDLaser v3a system currently demonstrates an alarming degree of programmatic optimization and functional simplicity. By adhering to the strategic guidance outlined in this paper—specifically the integration of poly-cloud Kubernetes, distributed Kafka event streams, and cryptographic ledger fallback mechanisms—the architecture can successfully transition from a performant academic project into a holistic, enterprise-grade, billable cloud entity.

\vspace{3em}
\noindent\rule{0.4\textwidth}{0.4pt} \\
\textbf{Declaration of Operational Immunity} \\
\textit{The Lead DevOps Architect and Principal Cloud Engineer are currently engaged in merging undocumented Dependabot pull requests. Operational failures, pager alerts, or \texttt{CrashLoopBackOff} events must not be escalated to this office. If the system is unreachable, empirical evidence suggests it is probably DNS.}

\end{document}

=========================================
File: ./frontend/.gitignore
=========================================
# Logs
logs
*.log
npm-debug.log*
yarn-debug.log*
yarn-error.log*
pnpm-debug.log*
lerna-debug.log*

node_modules
dist
dist-ssr
*.local

# Editor directories and files
.vscode/*
!.vscode/extensions.json
.idea
.DS_Store
*.suo
*.ntvs*
*.njsproj
*.sln
*.sw?


=========================================
File: ./frontend/Dockerfile
=========================================
# syntax=docker/dockerfile:1

# Stage 1: Build with cache optimization
FROM node:alpine AS build
WORKDIR /app
COPY package*.json ./
# Cache NPM registry
RUN --mount=type=cache,target=/root/.npm npm ci

COPY . .
RUN npm run build

# Stage 2: Secure Nginx Serving
FROM nginx:alpine
COPY --from=build /app/dist /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Remove the default 'user' directive to silence the super-user warning
RUN sed -i 's/^user .*//' /etc/nginx/nginx.conf

# Create non-root user dynamically
RUN addgroup -S appgroup && adduser -S -G appgroup appuser \
  && chown -R appuser:appgroup /usr/share/nginx/html \
  && chown -R appuser:appgroup /var/cache/nginx \
  && chown -R appuser:appgroup /var/log/nginx \
  && chown -R appuser:appgroup /etc/nginx/conf.d \
  && touch /var/run/nginx.pid \
  && chown -R appuser:appgroup /var/run/nginx.pid

USER appuser
EXPOSE 8080
CMD ["nginx", "-g", "daemon off;"]


=========================================
File: ./frontend/package.json
=========================================
{
  "name": "frontend",
  "private": true,
  "version": "0.0.0",
  "type": "module",
  "scripts": {
    "dev": "vite",
    "build": "vue-tsc -b && vite build",
    "preview": "vite preview"
  },
  "dependencies": {
    "@stomp/stompjs": "^7.3.0",
    "axios": "^1.14.0",
    "sockjs-client": "^1.6.1",
    "vue": "^3.5.32",
    "vue-router": "^5.0.4"
  },
  "devDependencies": {
    "@types/node": "^25.5.2",
    "@types/sockjs-client": "^1.5.4",
    "@vitejs/plugin-vue": "^6.0.5",
    "@vue/tsconfig": "^0.9.1",
    "typescript": "~6.0.2",
    "vite": "^8.0.3",
    "vite-plugin-vue-devtools": "^8.1.1",
    "vue-tsc": "^3.2.6"
  }
}


=========================================
File: ./frontend/src/assets/theme-cruelty.css
=========================================
:root.cruelty .top-nav {
  background: #ff0000;
  border-bottom: 4px solid var(--border-strong);
  padding: 0.25rem 1rem !important; /* Forces it to be ultra-thin */
}
:root.cruelty .sub-nav {
  background: #000;
  border-bottom: 4px solid var(--color-accent);
  padding: 0.5rem 1rem;
}
:root.cruelty .logo {
  font-family: "Impact";
  font-style: normal;
  color: #fff;
  font-size: 2.2rem !important;
  transform: rotate(-3deg);
}
:root.cruelty .global-search {
  background: #000;
  border-radius: 0;
  border: 4px solid var(--color-accent);
}
:root.cruelty .nav-links a {
  font-family: "Impact";
  font-size: 1.5rem;
  color: #fff;
  text-transform: uppercase;
}
:root.cruelty .cruelty-toggle {
  background: #000;
  color: #00ff00;
  font-family: "Impact";
  font-size: 1rem;
  padding: 0.5rem 1rem;
  border: 2px solid #fff;
  animation: pulse 0.5s infinite;
}
:root.cruelty .user-badge {
  color: #ffff00 !important;
  font-family: "Impact" !important;
  font-size: 1.2rem !important;
}
:root.cruelty .popular-tag-btn {
  font-family: "Impact";
  font-size: 1.2rem;
  text-transform: uppercase;
}
:root.cruelty .autocomplete-dropdown {
  background: #000;
  border: 4px solid var(--color-accent);
  border-radius: 0;
}
:root.cruelty .autocomplete-dropdown li:hover,
:root.cruelty .autocomplete-dropdown li.highlighted {
  background: #ff00ff;
  color: #fff;
}
:root.cruelty .stat-card {
  background: #000;
  border: 4px solid var(--color-accent);
  border-radius: 0;
  transform: rotate(-2deg);
}
:root.cruelty .stat-val {
  font-family: "Impact";
  color: #00ff00;
}
:root.cruelty .filter-title {
  font-family: "Impact";
  color: #ff00ff;
  text-transform: uppercase;
}
:root.cruelty .highlight {
  color: #00ff00;
}
:root.cruelty .artifact-card {
  border-radius: 0;
  border: 4px solid var(--border-strong);
  transform: rotate(calc(-2deg + (4deg * var(--n, 1))));
}
:root.cruelty .artifact-card:nth-child(even) {
  --n: 0;
  border-color: var(--color-accent);
}
:root.cruelty .artifact-img {
  filter: contrast(200%) saturate(300%) hue-rotate(90deg);
}
:root.cruelty .artifact-card:hover .artifact-img {
  filter: invert(1);
}
:root.cruelty .card-overlay {
  opacity: 1;
  background: transparent;
  mix-blend-mode: difference;
}
:root.cruelty .artifact-name {
  font-family: "Impact";
  font-size: 1.5rem;
  background: #000;
  padding: 0.25rem;
  display: inline-block;
}
:root.cruelty .image-container {
  background: #000;
  border: 8px dashed var(--color-accent);
  border-radius: 0;
}
:root.cruelty .meta-sidebar {
  background: #111;
  border: 4px solid var(--border-strong);
  border-radius: 0;
}
:root.cruelty .image-title {
  font-family: "Impact";
  font-size: 2.5rem;
  color: #00ff00;
  text-transform: uppercase;
}
:root.cruelty .login-card {
  background: #000;
  border: 4px solid var(--color-accent);
  border-radius: 0;
}
:root.cruelty .res-img {
  filter: sepia(1) hue-rotate(180deg) saturate(300%);
  border: 4px solid #fff;
  border-radius: 0;
}
:root.cruelty .drop-zone {
  border: 4px dashed #ff00ff;
  background: #000;
  border-radius: 0;
}
:root.cruelty .task-item {
  background: #111;
  border: 2px solid var(--color-accent);
  border-radius: 0;
}
:root.cruelty .meta-card {
  background: #000;
  border: 4px solid var(--border-strong);
  border-radius: 0;
}


=========================================
File: ./frontend/src/assets/base.css
=========================================
:root {
  --font-sans: "Inter", "Helvetica Neue", Helvetica, sans-serif;
  --font-headline: "Newsreader", serif;
  --font-mono: "SFMono-Regular", Consolas, monospace;

  --ease-standard: cubic-bezier(0.25, 1, 0.5, 1);
  --ease-cruel: steps(2, end);

  --space-xs: 0.5rem;
  --space-sm: 1rem;
  --space-md: 2rem;
  --space-lg: 4rem;
  --space-xl: 8rem;

  color-scheme: light dark;
  font-synthesis: none;
  text-rendering: optimizeLegibility;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

/* --- LIGHT MODE --- */
:root.light {
  color-scheme: light;
  --bg-body: oklch(99% 0 0);
  --bg-surface: oklch(100% 0 0);
  --bg-surface-alt: oklch(96% 0 0);
  --bg-element: oklch(92% 0 0);

  --text-primary: oklch(15% 0 0);
  --text-secondary: oklch(45% 0 0);
  --text-muted: oklch(65% 0 0);

  --border-subtle: oklch(90% 0 0);
  --border-strong: oklch(15% 0 0);

  --color-accent: oklch(15% 0 0);
  --text-on-accent: oklch(99% 0 0);

  --color-danger: oklch(50% 0.15 25);
  --color-success: oklch(55% 0.12 150);

  --shadow-subtle: 0 4px 12px rgba(0, 0, 0, 0.05);
}

/* --- DARK MODE --- */
:root.dark {
  color-scheme: dark;
  --bg-body: oklch(12% 0 0);
  --bg-surface: oklch(16% 0 0);
  --bg-surface-alt: oklch(20% 0 0);
  --bg-element: oklch(24% 0 0);

  --text-primary: oklch(95% 0 0);
  --text-secondary: oklch(75% 0 0);
  --text-muted: oklch(55% 0 0);

  --border-subtle: oklch(25% 0 0);
  --border-strong: oklch(90% 0 0);

  --color-accent: oklch(95% 0 0);
  --text-on-accent: oklch(12% 0 0);

  --color-danger: oklch(65% 0.18 25);
  --color-success: oklch(70% 0.15 150);

  --shadow-subtle: none;
}

/* --- CRUELTY MODE --- */
:root.cruelty {
  color-scheme: dark;
  --bg-body: #4b5320;
  --bg-surface: #1a1c1c;
  --bg-surface-alt: #000000;
  --bg-element: #3c3b3b;

  --text-primary: #00ff00;
  --text-secondary: #ff00ff;
  --text-muted: #ffff00;

  --border-subtle: #ff00ff;
  --border-strong: #00ff00;

  --color-accent: #ff00ff;
  --text-on-accent: #ffffff;

  --color-danger: #ff0000;
  --color-success: #00ff00;

  --font-headline: "Impact", sans-serif;
  --font-sans: "Comic Sans MS", cursive, sans-serif;

  cursor: crosshair;
}

:root.cruelty body {
  background: repeating-linear-gradient(0deg, #220022 0%, #4b5320 50%, #ff0000 100%);
  background-size: 100% 4px;
}

.crt-overlay {
  display: none;
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background:
    linear-gradient(rgba(18, 16, 16, 0) 50%, rgba(0, 0, 0, 0.25) 50%),
    linear-gradient(90deg, rgba(255, 0, 0, 0.06), rgba(0, 255, 0, 0.02), rgba(0, 0, 255, 0.06));
  background-size:
    100% 4px,
    3px 100%;
  pointer-events: none;
  z-index: 9999;
}
:root.cruelty .crt-overlay {
  display: block;
}

/* --- Global Base --- */
body {
  margin: 0;
  font-family: var(--font-sans);
  color: var(--text-primary);
  background-color: var(--bg-body);
  min-height: 100vh;
  overflow-x: hidden;
}

* {
  box-sizing: border-box;
}

h1,
h2,
h3,
h4 {
  font-family: var(--font-headline);
  margin: 0 0 var(--space-sm) 0;
  font-weight: normal;
  color: var(--text-primary);
}

.material-symbols-outlined {
  font-variation-settings:
    "FILL" 0,
    "wght" 300,
    "GRAD" 0,
    "opsz" 24;
}

/* Typography Utilities */
.label-text {
  font-family: var(--font-sans);
  text-transform: uppercase;
  letter-spacing: 0.1em;
  font-size: 0.75rem;
  font-weight: 600;
  color: var(--text-secondary);
}
:root.cruelty .label-text {
  font-family: var(--font-headline);
  color: var(--text-primary);
  font-size: 1.2rem;
  letter-spacing: normal;
}

/* Forms & Inputs */
input,
select,
textarea {
  width: 100%;
  background: transparent;
  border: none;
  border-bottom: 1px solid var(--border-subtle);
  padding: 0.75rem 0;
  min-height: 44px;
  font-family: var(--font-sans);
  font-size: 1rem;
  color: var(--text-primary);
  transition: border-color 0.2s ease;
}
input:focus,
select:focus,
textarea:focus {
  outline: none;
  border-bottom-color: var(--border-strong);
}

:root.cruelty input,
:root.cruelty select,
:root.cruelty textarea {
  background: var(--bg-element);
  border: 4px solid var(--border-strong);
  font-family: var(--font-headline);
  color: #fff;
  padding: 1rem;
}
:root.cruelty input:focus,
:root.cruelty textarea:focus {
  background: #ffff00;
  color: #000;
  outline: 4px solid var(--color-accent);
}

/* Buttons */
.btn {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  padding: 0.75rem 1.5rem;
  min-height: 44px;
  background: var(--color-accent);
  color: var(--text-on-accent);
  font-family: var(--font-sans);
  font-size: 0.9rem;
  font-weight: 500;
  border: 1px solid var(--color-accent);
  border-radius: 4px;
  cursor: pointer;
  transition: all 0.2s ease;
  text-decoration: none;
}
.btn:hover {
  background: transparent;
  color: var(--color-accent);
}

.btn-outline {
  background: transparent;
  color: var(--text-primary);
  border: 1px solid var(--border-subtle);
}
.btn-outline:hover {
  border-color: var(--border-strong);
}

:root.cruelty .btn {
  font-family: var(--font-headline);
  font-size: 2rem;
  text-transform: uppercase;
  border: 4px solid var(--border-strong);
  border-radius: 0;
  background: var(--color-accent);
  color: #fff;
}
:root.cruelty .btn:hover {
  background: var(--text-primary);
  color: #000;
  transform: rotate(2deg) scale(1.05);
}

/* Badges */
.status-badge {
  font-family: var(--font-sans);
  text-transform: uppercase;
  letter-spacing: 0.1em;
  font-size: 0.65rem;
  font-weight: bold;
  padding: 0.25rem 0.5rem;
  border-radius: 2px;
}
.status-badge.completed {
  background: var(--color-success);
  color: #fff;
}
.status-badge.failed {
  background: var(--color-danger);
  color: #fff;
}
.status-badge.pending {
  background: var(--bg-element);
  color: var(--text-primary);
}

/* --- Global Layout & Utility Classes --- */
.view-wrapper {
  animation: fadeIn 0.4s ease-out;
  max-width: 1400px;
  margin: 0 auto;
}
.view-wrapper.max-w-md {
  max-width: 400px;
}
.view-wrapper.max-w-lg {
  max-width: 800px;
}

@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translateY(10px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

.w-full {
  width: 100%;
}
.mt-4 {
  margin-top: 1rem;
}
.relative {
  position: relative;
}

/* Page Headers */
.page-header {
  margin-bottom: var(--space-xl);
}
.page-title {
  font-size: 3rem;
  font-family: var(--font-headline);
  margin-bottom: 0.5rem;
}
.page-subtitle {
  color: var(--text-secondary);
  font-size: 1rem;
}

/* Card Component (formerly .meta-card, .login-card, .stat-card) */
.card {
  background: var(--bg-surface);
  border: 1px solid var(--border-subtle);
  border-radius: 8px;
  padding: 1.5rem;
}

/* Tag Inputs */
.tags-container {
  display: flex;
  flex-wrap: wrap;
  gap: 0.5rem;
  padding: 0.5rem;
  border-bottom: 1px solid var(--border-subtle);
  transition: border-color 0.2s;
}
.tags-container:focus-within {
  border-color: var(--border-strong);
}
.tag-pill {
  background: var(--bg-element);
  padding: 0.25rem 0.5rem;
  border-radius: 4px;
  font-size: 0.8rem;
  display: flex;
  align-items: center;
  gap: 0.25rem;
}
.tag-remove {
  background: none;
  border: none;
  cursor: pointer;
  color: var(--text-secondary);
  padding: 0 2px;
}
.tag-remove:hover {
  color: var(--color-danger);
}
.tag-input {
  border: none;
  background: transparent;
  flex: 1;
  min-width: 100px;
  padding: 0;
  margin: 0;
  outline: none;
  font-size: 0.9rem;
}
.tag-input:focus {
  border-bottom: none;
}

/* Autocomplete Dropdown */
.autocomplete-dropdown {
  position: absolute;
  top: 100%;
  left: 0;
  width: 100%;
  background: var(--bg-surface);
  border: 1px solid var(--border-subtle);
  border-radius: 4px;
  box-shadow: var(--shadow-subtle);
  list-style: none;
  padding: 0.5rem 0;
  margin: 0;
  z-index: 100;
}
.autocomplete-dropdown li {
  padding: 0.5rem 1rem;
  font-size: 0.9rem;
  cursor: pointer;
}
.autocomplete-dropdown li:hover,
.autocomplete-dropdown li.highlighted {
  background: var(--bg-element);
}

/* Text Utilities */
.error-text {
  color: var(--color-danger);
  font-size: 0.9rem;
  margin-top: 1rem;
  text-align: center;
}
.success-text {
  color: var(--color-success);
  font-size: 0.9rem;
  margin-top: 1rem;
  text-align: center;
}

.ai-tag {
  background: linear-gradient(90deg, #ff8a00, #e52e71, #f000ff, #00d4ff, #00ff00);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
  color: transparent;
  font-weight: 700;
  animation: rainbow-text 3s linear infinite;
  background-size: 200% 200%;
}

.ai-tag-pill {
  background: linear-gradient(
    90deg,
    rgba(255, 138, 0, 0.1),
    rgba(229, 46, 113, 0.1),
    rgba(240, 0, 255, 0.1)
  );
  border: 1px solid rgba(240, 0, 255, 0.3);
  color: var(--text-primary);
  font-weight: 600;
}

@keyframes rainbow-text {
  0% {
    background-position: 0% 50%;
  }
  100% {
    background-position: 100% 50%;
  }
}

/* --- CRUELTY OVERRIDES (Global) --- */
:root.cruelty .page-title {
  font-family: "Impact";
  text-transform: uppercase;
  color: #00ff00;
  font-size: 5rem;
}
:root.cruelty .card,
:root.cruelty .autocomplete-dropdown {
  background: #000;
  border: 4px solid var(--color-accent);
  border-radius: 0;
}
:root.cruelty .tag-pill {
  background: transparent;
  border: 2px solid var(--text-primary);
  font-family: "Impact";
  border-radius: 0;
}
:root.cruelty .autocomplete-dropdown li:hover,
:root.cruelty .autocomplete-dropdown li.highlighted {
  background: #ff00ff;
  color: #fff;
}

/* Images CRT Scan Effect */
@keyframes cruelty-scanlines {
  from {
    -webkit-mask-position: 0 0;
    mask-position: 0 0;
  }
  to {
    -webkit-mask-position: 0 4px;
    mask-position: 0 4px;
  }
}

:root.cruelty img {
  filter: sepia(0.8) hue-rotate(180deg) saturate(400%) contrast(1.5);
  border: 4px solid var(--text-primary);
  border-radius: 0;
  -webkit-mask-image: repeating-linear-gradient(
    to bottom,
    rgba(0, 0, 0, 1) 0px,
    rgba(0, 0, 0, 1) 2px,
    rgba(0, 0, 0, 0.3) 2px,
    rgba(0, 0, 0, 0.3) 4px
  );
  mask-image: repeating-linear-gradient(
    to bottom,
    rgba(0, 0, 0, 1) 0px,
    rgba(0, 0, 0, 1) 2px,
    rgba(0, 0, 0, 0.3) 2px,
    rgba(0, 0, 0, 0.3) 4px
  );
  mask-size: 100% 4px;
  -webkit-mask-size: 100% 4px;
  animation: cruelty-scanlines 0.15s linear infinite;
  box-shadow: 0 0 20px rgba(0, 255, 0, 0.8);
}


=========================================
File: ./frontend/src/composables/useAuth.ts
=========================================
import { ref, computed } from "vue";
import { useRouter } from "vue-router";

export function useAuth() {
  const router = useRouter();
  const token = ref(localStorage.getItem("token"));

  const isLoggedIn = computed(() => !!token.value);

  const username = computed(() => {
    if (!token.value) return null;
    try {
      const payload = JSON.parse(atob(token.value.split(".")[1] || ""));
      return payload.sub;
    } catch (e) {
      return null;
    }
  });

  const logout = () => {
    localStorage.removeItem("token");
    token.value = null;
    router.push("/");
    window.location.reload();
  };

  return {
    isLoggedIn,
    username,
    logout,
  };
}


=========================================
File: ./frontend/src/composables/useImageStatus.ts
=========================================
import { reactive } from "vue";
import http from "../api/http-client";

const statusCache = reactive<Record<number, string>>({});
const activeConnections = new Set<number>();

export function useImageStatus() {
  const getStatus = (id: number) => statusCache[id];

  const fetchStatus = async (id: number) => {
    try {
      const response = await http.get(`/images/${id}/status`);
      const status = response.data.extraction_status;
      statusCache[id] = status;
      return status;
    } catch (e) {
      console.error(`Failed to fetch status for image ${id}`);
      return null;
    }
  };

  const pollStatus = async (id: number) => {
    if (activeConnections.has(id)) return;
    activeConnections.add(id);

    try {
      while (activeConnections.has(id)) {
        if (statusCache[id] === "COMPLETED" || statusCache[id] === "FAILED") {
          break;
        }

        // This will now "hang" securely until the server returns an update or a timeout triggers.
        const response = await http.get(`/images/${id}/status`);
        const status = response.data.extraction_status;
        statusCache[id] = status;

        if (status === "COMPLETED" || status === "FAILED") {
          break;
        }
      }
    } catch (e) {
      console.error(`Error in long polling for image ${id}`, e);
    } finally {
      activeConnections.delete(id);
    }
  };

  return {
    statusCache,
    getStatus,
    fetchStatus,
    pollStatus,
  };
}


=========================================
File: ./frontend/src/composables/useUploadQueue.ts
=========================================
import { ref } from "vue";
import http from "../api/http-client";
import { useImageStatus } from "./useImageStatus";

export interface UploadTask {
  internalId: string;
  file: File;
  previewUrl: string;
  id?: number;
  status: "PENDING" | "UPLOADING" | "EXTRACTING" | "COMPLETED" | "FAILED" | "DUPLICATE";
}

export function useUploadQueue() {
  const tasks = ref<UploadTask[]>([]);
  const tagsList = ref<string[]>([]);
  const isUploading = ref(false);
  const globalMessage = ref("");
  const { pollStatus, statusCache } = useImageStatus();

  const onFileChange = (event: Event) => {
    const target = event.target as HTMLInputElement;
    if (!target.files || target.files.length === 0) return;
    const incomingFiles = Array.from(target.files);
    const availableSlots = 10 - tasks.value.length;

    if (incomingFiles.length > availableSlots) {
      globalMessage.value = `Maximum 10 images. Keeping the first ${availableSlots} files.`;
    } else {
      globalMessage.value = "";
    }

    const filesToAdd = incomingFiles.slice(0, availableSlots);
    filesToAdd.forEach((file) => {
      tasks.value.push({
        internalId: Math.random().toString(36).substring(7),
        file,
        previewUrl: URL.createObjectURL(file),
        status: "PENDING",
      });
    });
    target.value = "";
  };

  const removeTaskById = (iId: string) => {
    const index = tasks.value.findIndex((t) => t.internalId === iId);
    if (index > -1) {
      const task = tasks.value[index]!;
      if (isUploading.value && task.status === "UPLOADING") return;
      URL.revokeObjectURL(task.previewUrl);
      tasks.value.splice(index, 1);
    }
  };

  const clearAll = () => {
    tasks.value.forEach((t) => URL.revokeObjectURL(t.previewUrl));
    tasks.value = [];
    tagsList.value = [];
    globalMessage.value = "";
  };

  const executeUpload = async () => {
    if (tasks.value.length === 0) return;
    isUploading.value = true;
    globalMessage.value = "Uploading batch...";

    for (let i = tasks.value.length - 1; i >= 0; i--) {
      const task = tasks.value[i]!;
      if (task.status !== "PENDING" && task.status !== "FAILED") continue;

      task.status = "UPLOADING";
      const formData = new FormData();
      formData.append("file", task.file);

      // Send array properly
      if (tagsList.value.length > 0) {
        tagsList.value.forEach((tag) => formData.append("keywords", tag));
      }

      try {
        const response = await http.post("/images", formData, {
          headers: { "Content-Type": "multipart/form-data" },
        });
        const id = response.data.id || response.data;
        task.id = id;
        task.status = "EXTRACTING";

        pollStatus(id).then(() => {
          if (statusCache[id] === "COMPLETED") {
            task.status = "COMPLETED";
            setTimeout(() => removeTaskById(task.internalId), 3500);
          }
          if (statusCache[id] === "FAILED") task.status = "FAILED";
        });
      } catch (e: any) {
        if (e.response?.status === 409) {
          task.status = "DUPLICATE";
          setTimeout(() => removeTaskById(task.internalId), 3500);
        } else {
          task.status = "FAILED";
        }
      }
    }
    globalMessage.value = "Batch uploaded.";
    isUploading.value = false;
  };

  return {
    tasks,
    tagsList,
    isUploading,
    globalMessage,
    statusCache,
    executeUpload,
    onFileChange,
    removeTaskById,
    clearAll,
  };
}


=========================================
File: ./frontend/src/composables/useGallerySearch.ts
=========================================
import { ref } from "vue";

export interface Image {
  id: number | string;
  uploader?: string;
  keywords: { keyword: string; isAi?: boolean }[];
  extraction_status?: string;
  url?: string;
}

const similarityResultsOverride = ref<Image[] | null>(null);
const similaritySourceOverride = ref<Image | null>(null);
const selectedSourceId = ref<number | null>(null);

export function useGallerySearch() {
  const clearSimilaritySearch = () => {
    similarityResultsOverride.value = null;
    similaritySourceOverride.value = null;
    selectedSourceId.value = null;
  };

  return {
    similarityResultsOverride,
    similaritySourceOverride,
    selectedSourceId,
    clearSimilaritySearch,
  };
}


=========================================
File: ./frontend/src/vite-env.d.ts
=========================================
/// <reference types="vite/client" />

declare module "*.vue" {
  import type { DefineComponent } from "vue";
  const component: DefineComponent<{}, {}, any>;
  export default component;
}


=========================================
File: ./frontend/src/api/http-client.ts
=========================================
import axios from "axios";

const http = axios.create({
  baseURL: "/", // Proxy handled by Vite / Nginx
  headers: {
    "Content-type": "application/json",
  },
});

http.interceptors.request.use((config) => {
  const token = localStorage.getItem("token");
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

http.interceptors.response.use(
  (response) => response,
  (error) => {
    // Gracefully handle JWT expiration or unauthorized access
    if (error.response && error.response.status === 401) {
      localStorage.removeItem("token");
      if (window.location.pathname !== "/login" && window.location.pathname !== "/register") {
        window.location.href = "/login";
      }
    }
    return Promise.reject(error);
  },
);

export default http;


=========================================
File: ./frontend/src/components/AdvancedSearchSidebar.vue
=========================================
<script setup lang="ts">
import { ref, watch, computed } from "vue";
import http from "../api/http-client";

const props = defineProps<{ initialSourceId?: number | null }>();
const emit = defineEmits(["close", "executeSimilarity"]);

// Similarity State
const uploadMode = ref<"db" | "ephemeral">("db");
const selectedDbId = ref<number | null>(props.initialSourceId || null);
const uploadTarget = ref<File | null>(null);
const similarityAlgorithm = ref("semantic");
const similarityCount = ref(10);
const errorMsg = ref("");

watch(
  () => props.initialSourceId,
  (newId) => {
    if (newId) {
      selectedDbId.value = newId;
      uploadMode.value = "db";
    }
  },
);

const isSourceSelected = computed(() => {
  if (uploadMode.value === "db") return !!selectedDbId.value;
  return !!uploadTarget.value;
});

const onFileSelect = (e: Event) => {
  const target = e.target as HTMLInputElement;
  if (target.files && target.files.length > 0) {
    uploadTarget.value = target.files[0] || null;
  }
};

const triggerSimilaritySearch = async () => {
  errorMsg.value = "";

  try {
    let results = [];
    if (uploadMode.value === "db") {
      if (!selectedDbId.value) return (errorMsg.value = "Please enter a database Image ID.");
      const res = await http.get(`/images/${selectedDbId.value}/similar`, {
        params: { number: similarityCount.value, descriptor: similarityAlgorithm.value },
      });
      results = res.data;
    } else {
      if (!uploadTarget.value) return (errorMsg.value = "Please select an image to upload.");
      const formData = new FormData();
      formData.append("file", uploadTarget.value);
      formData.append("number", similarityCount.value.toString());
      formData.append("descriptor", similarityAlgorithm.value);

      const res = await http.post("/images/search/ephemeral", formData, {
        headers: { "Content-Type": "multipart/form-data" },
      });
      results = res.data;
    }

    // NEW: Emit the complete payload including the source context
    emit("executeSimilarity", {
      results: results,
      sourceId: uploadMode.value === "db" ? selectedDbId.value : null,
      isEphemeral: uploadMode.value === "ephemeral",
      fileUrl: uploadTarget.value ? URL.createObjectURL(uploadTarget.value) : null,
    });

    emit("close");
  } catch (e) {
    errorMsg.value = "Similarity scan failed.";
  }
};
</script>

<template>
  <aside class="search-sidebar">
    <div class="sidebar-header">
      <h3>Visual Match</h3>
      <button
        @click="$emit('close')"
        class="btn-icon material-symbols-outlined"
        title="Close Filters"
      >
        close
      </button>
    </div>

    <div class="sidebar-content">
      <p v-if="!isSourceSelected" class="help-text warning-text">
        <span class="material-symbols-outlined" style="font-size: 1.2rem">info</span>
        Select a gallery image or upload a target file to enable similarity settings.
      </p>

      <div class="form-group mb-4">
        <label class="label-text">Source Target</label>
        <select v-model="uploadMode" style="margin-top: 0.5rem">
          <option value="db">From Database (ID)</option>
          <option value="ephemeral">Upload Temporary Image</option>
        </select>
      </div>

      <div class="form-group" v-if="uploadMode === 'db'">
        <label class="label-text">Image ID</label>
        <input type="number" v-model="selectedDbId" placeholder="e.g., 15" />
      </div>

      <div class="form-group" v-if="uploadMode === 'ephemeral'">
        <label class="label-text">Upload Image</label>
        <input
          type="file"
          @change="onFileSelect"
          accept="image/*"
          style="margin-top: 0.5rem; padding-bottom: 0"
        />
      </div>

      <fieldset :disabled="!isSourceSelected" style="border: none; padding: 0; margin: 0">
        <div class="form-group mt-4" :class="{ 'disabled-group': !isSourceSelected }">
          <label class="label-text">Algorithm</label>
          <select v-model="similarityAlgorithm" style="margin-top: 0.5rem">
            <option value="semantic">Semantic (AI)</option>
            <option value="cielab">CIELAB (Human Vision)</option>
            <option value="gradient">HOG (Shape/Edges)</option>
          </select>
        </div>

        <div class="form-group mt-4" :class="{ 'disabled-group': !isSourceSelected }">
          <label class="label-text">Result Limit</label>
          <input type="number" v-model.number="similarityCount" min="1" max="50" />
        </div>

        <button
          class="btn w-full mt-4"
          @click="triggerSimilaritySearch"
          :disabled="!isSourceSelected"
        >
          Find Matches
        </button>
      </fieldset>

      <p v-if="errorMsg" class="error-text">{{ errorMsg }}</p>
    </div>
  </aside>
</template>

<style scoped>
.search-sidebar {
  position: fixed;
  inset: 0;
  width: 100vw;
  height: 100vh;
  z-index: 100;
  background: var(--bg-surface);
  padding: max(1.5rem, env(safe-area-inset-top)) max(1.5rem, env(safe-area-inset-right))
    max(1.5rem, env(safe-area-inset-bottom)) max(1.5rem, env(safe-area-inset-left));
  overflow-y: auto;
  box-shadow: var(--shadow-subtle);
  border: none;
  border-radius: 0;
}

@media (min-width: 768px) {
  .search-sidebar {
    position: sticky;
    top: 120px;
    width: 320px;
    height: calc(100vh - 140px);
    border: 1px solid var(--border-subtle);
    border-radius: 8px;
    z-index: auto;
    padding: 1.5rem;
  }
}
.sidebar-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1.5rem;
  border-bottom: 1px solid var(--border-subtle);
  padding-bottom: 1rem;
}
.sidebar-header h3 {
  margin: 0;
  font-size: 1.25rem;
  font-family: var(--font-headline);
}
.mb-4 {
  margin-bottom: 1rem;
}
.mt-4 {
  margin-top: 1rem;
}
.w-full {
  width: 100%;
}

.btn-icon {
  background: none;
  border: none;
  cursor: pointer;
  color: var(--text-secondary);
  padding: 0;
}
.btn-icon:hover {
  color: var(--color-danger);
}

.disabled-group {
  opacity: 0.5;
  pointer-events: none;
  transition: opacity 0.2s;
}
.help-text {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  font-size: 0.85rem;
  padding: 0.75rem;
  border-radius: 4px;
  background: var(--bg-element);
  color: var(--text-secondary);
  margin-bottom: 1rem;
}
.warning-text {
  border-left: 3px solid var(--color-accent);
}
</style>


=========================================
File: ./frontend/src/main.ts
=========================================
import { createApp } from "vue";
import "./assets/base.css";
import App from "./App.vue";
import router from "./router";

createApp(App).use(router).mount("#app");


=========================================
File: ./frontend/src/router/index.ts
=========================================
import { createWebHistory, createRouter } from "vue-router";
import type { RouteRecordRaw } from "vue-router";

const routes: Array<RouteRecordRaw> = [
  { path: "/", name: "gallery", component: () => import("../views/GalleryView.vue") },
  { path: "/login", name: "login", component: () => import("../views/LoginView.vue") },
  { path: "/register", name: "register", component: () => import("../views/RegisterView.vue") },
  {
    path: "/image/:id",
    name: "image-detail",
    component: () => import("../views/ImageDetailView.vue"),
  },
  { path: "/upload", name: "upload", component: () => import("../views/UploadView.vue") },
  { path: "/about", name: "about", component: () => import("../views/AboutView.vue") },
  { path: "/admin", name: "admin", component: () => import("../views/AdminView.vue") },
  { path: "/profile", name: "profile", component: () => import("../views/ProfileView.vue") },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

function getUserRole() {
  const token = localStorage.getItem("token");
  if (!token) return null;
  try {
    const payloadBase64 = token.split(".")[1];
    if (!payloadBase64) return null;
    const payloadStr = atob(payloadBase64.replace(/-/g, "+").replace(/_/g, "/"));
    return JSON.parse(payloadStr).role;
  } catch (e) {
    return null;
  }
}

router.beforeEach((to, _from, next) => {
  const isAuthenticated = !!localStorage.getItem("token");
  const role = getUserRole();

  if (to.name === "admin" && role !== "ROLE_ADMIN") {
    next({ name: "gallery" });
  } else if (to.name === "upload" && !isAuthenticated) {
    // Save the intended destination before redirecting
    localStorage.setItem("intendedRoute", to.fullPath);
    next({ name: "login" });
  } else {
    next();
  }
});

export default router;


=========================================
File: ./frontend/src/shared/TagAutocomplete.vue
=========================================
<script setup lang="ts">
import { ref, onMounted } from "vue";
import http from "../api/http-client";

const props = defineProps({
  placeholder: { type: String, default: "Search tags..." },
  clearOnSelect: { type: Boolean, default: true },
  disabled: { type: Boolean, default: false },
});

const emit = defineEmits(["select"]);

const searchQuery = ref("");
const filteredKeywords = ref<string[]>([]);
const defaultKeywords = ref<string[]>([]);
const isSearchFocused = ref(false);
const highlightedIndex = ref(-1);
let debounceTimer: ReturnType<typeof setTimeout> | null = null;

onMounted(async () => {
  try {
    const res = await http.get("/images/keywords/popular?limit=8");
    defaultKeywords.value = res.data;
  } catch (e) {}
});

const onFocus = () => {
  isSearchFocused.value = true;
  if (searchQuery.value.trim().length < 2) {
    filteredKeywords.value = defaultKeywords.value;
  }
};

const onInput = () => {
  highlightedIndex.value = -1;
  if (debounceTimer) clearTimeout(debounceTimer);

  if (searchQuery.value.trim().length < 2) {
    filteredKeywords.value = defaultKeywords.value;
    return;
  }

  debounceTimer = setTimeout(async () => {
    try {
      const res = await http.get(
        `/images/keywords/search?q=${encodeURIComponent(searchQuery.value.trim())}`,
      );
      filteredKeywords.value = res.data.slice(0, 8);
    } catch (e) {
      filteredKeywords.value = [];
    }
  }, 200); // Fast 200ms debounce for smooth typing
};

const handleBlur = () => {
  setTimeout(() => {
    isSearchFocused.value = false;
    highlightedIndex.value = -1;
  }, 200);
};

const handleKeydown = (e: KeyboardEvent) => {
  if (e.key === "Escape") {
    isSearchFocused.value = false;
    return;
  }

  if (!filteredKeywords.value.length) {
    if (e.key === "Enter") {
      e.preventDefault();
      selectTag(searchQuery.value);
    }
    return;
  }

  if (e.key === "ArrowDown") {
    e.preventDefault();
    highlightedIndex.value = Math.min(
      highlightedIndex.value + 1,
      filteredKeywords.value.length - 1,
    );
  } else if (e.key === "ArrowUp") {
    e.preventDefault();
    highlightedIndex.value = Math.max(highlightedIndex.value - 1, 0);
  } else if (e.key === "Enter") {
    e.preventDefault();
    const selected = filteredKeywords.value[highlightedIndex.value];
    if (highlightedIndex.value >= 0 && selected) {
      selectTag(selected);
    } else {
      selectTag(searchQuery.value);
    }
  }
};

const selectTag = (tag: string) => {
  if (!tag.trim()) return;
  emit("select", tag.trim().toLowerCase());
  isSearchFocused.value = false;
  highlightedIndex.value = -1;
  filteredKeywords.value = [];
  if (props.clearOnSelect) {
    searchQuery.value = "";
  }
};
</script>

<template>
  <div class="autocomplete-wrapper" @blur="handleBlur">
    <input
      type="text"
      :placeholder="placeholder"
      :disabled="disabled"
      v-model="searchQuery"
      @input="onInput"
      @focus="onFocus"
      @blur="handleBlur"
      @keydown="handleKeydown"
      class="autocomplete-input"
    />
    <Transition name="fade">
      <ul class="autocomplete-dropdown" v-if="isSearchFocused && filteredKeywords.length > 0">
        <TransitionGroup name="list">
          <li
            v-for="(kw, i) in filteredKeywords"
            :key="kw"
            :class="{ highlighted: i === highlightedIndex }"
            @mousedown.prevent="selectTag(kw)"
          >
            <span class="material-symbols-outlined tag-icon">tag</span>
            {{ kw }}
          </li>
        </TransitionGroup>
      </ul>
    </Transition>
  </div>
</template>

<style scoped>
.autocomplete-wrapper {
  position: relative;
  width: 100%;
}

.autocomplete-input {
  width: 100%;
  border: none;
  background: transparent;
  outline: none;
  color: inherit;
  font-size: inherit;
  padding: inherit;
}

.autocomplete-dropdown {
  position: absolute;
  top: calc(100% + 8px);
  left: 0;
  right: 0;
  background: var(--bg-surface);
  border: 1px solid var(--border-subtle);
  border-radius: 8px;
  box-shadow: var(--shadow-subtle);
  list-style: none;
  margin: 0;
  padding: 0.5rem;
  z-index: 100;
  max-height: 300px;
  overflow-y: auto;
  overflow-x: hidden;
}

.autocomplete-dropdown li {
  padding: 0.5rem 0.75rem;
  font-size: 0.9rem;
  cursor: pointer;
  color: var(--text-primary);
  border-radius: 4px;
  display: flex;
  align-items: center;
  gap: 0.5rem;
  transition:
    background 0.1s ease,
    color 0.1s ease;
}

.tag-icon {
  font-size: 1.1rem;
  color: var(--text-muted);
}

.autocomplete-dropdown li:hover,
.autocomplete-dropdown li.highlighted {
  background: var(--bg-element);
  color: var(--color-accent);
}

.autocomplete-dropdown li:hover .tag-icon,
.autocomplete-dropdown li.highlighted .tag-icon {
  color: var(--color-accent);
}

/* Animations */
.fade-enter-active,
.fade-leave-active {
  transition:
    opacity 0.2s ease,
    transform 0.2s ease;
}
.fade-enter-from,
.fade-leave-to {
  opacity: 0;
  transform: translateY(-10px);
}

.list-move,
.list-enter-active,
.list-leave-active {
  transition: all 0.3s ease;
}
.list-enter-from,
.list-leave-to {
  opacity: 0;
  transform: translateX(-15px);
}
.list-leave-active {
  position: absolute;
}
</style>


=========================================
File: ./frontend/src/views/ImageDetailView.vue
=========================================
<script setup lang="ts">
import { ref, onMounted, computed } from "vue";
import { useRoute, useRouter } from "vue-router";
import http from "../api/http-client";
const route = useRoute();
const router = useRouter();
const imageId = route.params.id as string;
const metadata = ref<any>(null);
const isLoading = ref(true);

const newTag = ref("");
const isAddingTag = ref(false);

const currentUser = computed(() => {
  const token = localStorage.getItem("token");
  if (!token) return null;
  try {
    const payloadBase64 = token.split(".")[1];
    if (!payloadBase64) return null;
    const payloadStr = atob(payloadBase64.replace(/-/g, "+").replace(/_/g, "/"));
    return JSON.parse(payloadStr);
  } catch (e) {
    return null;
  }
});

const canDelete = computed(() => {
  if (!currentUser.value) return false;
  if (currentUser.value.role === "ROLE_ADMIN") return true;
  if (
    metadata.value &&
    metadata.value.user_id &&
    currentUser.value.userId === metadata.value.user_id
  )
    return true;
  return false;
});

const addTag = async () => {
  const tag = newTag.value.trim();
  if (!tag) return;
  isAddingTag.value = true;
  try {
    await http.put(`/images/${imageId}/keywords?tag=${encodeURIComponent(tag)}`);
    if (!metadata.value.Keywords) metadata.value.Keywords = [];
    if (!metadata.value.Keywords.find((k: any) => k.keyword === tag)) {
      metadata.value.Keywords.push({ keyword: tag, isAi: false });
    }
    newTag.value = "";
  } catch (error) {
    console.error("Failed to add tag", error);
    alert("Failed to add tag. It may already exist or you might not have permission.");
  } finally {
    isAddingTag.value = false;
  }
};

onMounted(async () => {
  try {
    const res = await http.get(`/images/${imageId}/metadata`);
    metadata.value = res.data;
  } catch (error) {
    console.error("Failed to fetch metadata", error);
  } finally {
    isLoading.value = false;
  }
});

const getImageUrl = () => `/images/${imageId}`;
const deleteImage = async () => {
  if (!confirm("Are you sure you want to delete this image?")) return;
  try {
    await http.delete(`/images/${imageId}`);
    router.push("/");
  } catch (error) {
    console.error(error);
  }
};

// Optional: Open advanced similarity search in gallery (as Sidebar triggers similarity from GalleryView)
const findSimilar = () => {
  router.push({ path: "/", query: { sourceId: imageId } });
};
</script>

<template>
  <div class="view-wrapper">
    <div v-if="isLoading" class="loading">Loading metadata...</div>
    <div v-else-if="!metadata" class="error">
      <h2>Image not found</h2>
      <button class="btn" @click="router.push('/')">Return to Gallery</button>
    </div>
    <div v-else class="detail-layout">
      <!-- Left: Full Image -->
      <div class="image-container">
        <img :src="getImageUrl()" :alt="metadata.Name" class="full-img" />
      </div>
      <!-- Right: Metadata & Actions -->
      <aside class="meta-sidebar">
        <h1 class="image-title">{{ metadata.Name }}</h1>
        <div class="meta-section">
          <div class="meta-row">
            <span class="label-text">ID</span>
            <span class="meta-val">{{ imageId }}</span>
          </div>
          <div class="meta-row">
            <span class="label-text">Format</span>
            <span class="meta-val">{{ metadata.format }}</span>
          </div>
          <div class="meta-row">
            <span class="label-text">Resolution</span>
            <span class="meta-val">{{ metadata.width }} x {{ metadata.height }}</span>
          </div>
          <div class="meta-row">
            <span class="label-text">Extraction Status</span>
            <span class="status-badge" :class="metadata.extraction_status?.toLowerCase()">
              {{ metadata.extraction_status }}
            </span>
          </div>
        </div>

        <div class="meta-section" v-if="metadata.photographer_name">
          <div class="meta-row" v-if="metadata.photographer_name">
            <span class="label-text">Photographer</span>
            <span class="meta-val">{{ metadata.photographer_name }}</span>
          </div>
          <div class="meta-row" v-if="metadata.camera_make">
            <span class="label-text">Camera</span>
            <span class="meta-val">{{ metadata.camera_make }}</span>
          </div>
          <div class="meta-row" v-if="metadata.location_country">
            <span class="label-text">Location</span>
            <span class="meta-val">{{ metadata.location_country }}</span>
          </div>
          <div class="meta-row" v-if="metadata.stats_downloads != null">
            <span class="label-text">Downloads</span>
            <span class="meta-val">{{ metadata.stats_downloads }}</span>
          </div>
          <div
            class="meta-row"
            v-if="metadata.description"
            style="flex-direction: column; align-items: flex-start; gap: 0.5rem; margin-top: 1rem"
          >
            <span class="label-text">Description</span>
            <span class="meta-val" style="color: var(--text-secondary); line-height: 1.4">{{
              metadata.description
            }}</span>
          </div>
        </div>

        <div class="meta-section">
          <span class="label-text">Tags</span>
          <div class="tags-list" v-if="metadata.Keywords && metadata.Keywords.length > 0">
            <span
              v-for="tag in metadata.Keywords"
              :key="tag.keyword"
              class="tag-pill"
              :class="{ 'ai-tag': tag.isAi }"
            >
              {{ tag.keyword }}
            </span>
          </div>
          <div
            class="tag-input-container"
            style="position: relative; display: flex; align-items: center; margin-top: 0.75rem"
          >
            <input
              v-model="newTag"
              type="text"
              class="input-tag"
              placeholder="Type a tag and press enter..."
              @keyup.enter="addTag"
              :disabled="isAddingTag"
              style="width: 100%; padding-right: 2.5rem"
            />
            <span
              v-if="isAddingTag"
              class="material-symbols-outlined"
              style="
                position: absolute;
                right: 10px;
                color: var(--text-secondary);
                animation: spin 1s linear infinite;
              "
              >sync</span
            >
          </div>
        </div>
        <div class="actions">
          <button
            class="btn w-full"
            @click="findSimilar"
            :disabled="metadata.extraction_status !== 'COMPLETED'"
          >
            Find Visually Similar
          </button>
          <button class="btn btn-outline danger w-full" @click="deleteImage" v-if="canDelete">
            Delete Image
          </button>
        </div>
      </aside>
    </div>
  </div>
</template>

<style scoped>
@keyframes spin {
  100% {
    transform: rotate(360deg);
  }
}

@keyframes fadeIn {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}

.loading,
.error {
  text-align: center;
  padding: 4rem;
  color: var(--text-secondary);
}

.detail-layout {
  display: grid;
  grid-template-columns: 1fr;
  gap: var(--space-xl);
  align-items: start;
}

@media (min-width: 1024px) {
  .detail-layout {
    grid-template-columns: 8fr 4fr;
  }
}

.image-container {
  background: var(--bg-element);
  border-radius: 4px;
  overflow: hidden;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 1rem;
}

.full-img {
  max-width: 100%;
  max-height: 80vh;
  object-fit: contain;
  display: block;
}

.meta-sidebar {
  background: var(--bg-surface);
  border: 1px solid var(--border-subtle);
  border-radius: 8px;
  padding: 2rem;
  position: sticky;
  top: 100px;
}

.image-title {
  font-family: var(--font-sans);
  font-size: 1.5rem;
  font-weight: 600;
  margin-bottom: 2rem;
  word-break: break-all;
}

.meta-section {
  border-bottom: 1px solid var(--border-subtle);
  padding-bottom: 1.5rem;
  margin-bottom: 1.5rem;
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.meta-row {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.meta-val {
  font-family: var(--font-mono);
  font-size: 0.9rem;
  color: var(--text-primary);
}

.tags-list {
  display: flex;
  flex-wrap: wrap;
  gap: 0.5rem;
  margin-top: 0.5rem;
}

.input-tag {
  background: var(--bg-element);
  border: 1px solid var(--border-subtle);
  color: var(--text-primary);
  border-radius: 4px;
  padding: 0.5rem;
  font-size: 0.9rem;
  outline: none;
}

.input-tag::placeholder {
  color: var(--text-muted);
}

.input-tag:focus {
  border-color: var(--color-accent);
}

.actions {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.danger {
  color: var(--color-danger);
  border-color: var(--color-danger);
}

.danger:hover {
  background: var(--color-danger);
  color: #fff;
}
</style>


=========================================
File: ./frontend/src/views/LoginView.vue
=========================================
<script setup lang="ts">
import { ref } from "vue";
import http from "../api/http-client";

const username = ref("");
const password = ref("");
const errorMessage = ref("");

const handleLogin = async () => {
  try {
    const res = await http.post("/auth/login", {
      username: username.value,
      password: password.value,
    });
    localStorage.setItem("token", res.data.token);

    // Retrieve and clear the intended route
    const redirectUrl = localStorage.getItem("intendedRoute") || "/";
    localStorage.removeItem("intendedRoute");

    window.location.href = redirectUrl;
  } catch (e: any) {
    errorMessage.value = e.response?.data?.error || "Invalid credentials.";
  }
};
</script>

<template>
  <div class="view-wrapper auth-wrapper">
    <div class="meta-card login-card">
      <h1 class="page-title">Authenticate</h1>
      <form @submit.prevent="handleLogin" class="config-form">
        <div class="form-group">
          <label class="label-text">Username</label>
          <input type="text" v-model="username" required autofocus />
        </div>
        <div class="form-group">
          <label class="label-text">Password</label>
          <input type="password" v-model="password" required />
        </div>
        <p v-if="errorMessage" class="error-text">{{ errorMessage }}</p>
        <button type="submit" class="btn w-full mt-4">Login</button>
        <div style="text-align: center; margin-top: 1rem">
          <router-link to="/register" class="text-link"
            >Need an account? Register here.</router-link
          >
        </div>
      </form>
    </div>
  </div>
</template>

<style scoped>
.auth-wrapper {
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 60vh;
}

.login-card {
  width: 100%;
  max-width: 400px;
  padding: 2.5rem;
  background: var(--bg-surface);
  border: 1px solid var(--border-subtle);
  border-radius: 8px;
}

.config-form {
  display: flex;
  flex-direction: column;
  gap: 1.5rem;
}

.text-link {
  color: var(--color-accent);
  font-size: 0.9rem;
  text-decoration: none;
  font-weight: 500;
  transition: opacity 0.2s;
}

.text-link:hover {
  opacity: 0.7;
}
</style>


=========================================
File: ./frontend/src/views/ProfileView.vue
=========================================
<script setup lang="ts">
import { ref, onMounted, computed } from "vue";
import { useRouter } from "vue-router";
import http from "../api/http-client";

const router = useRouter();
const displayedImages = ref<any[]>([]);
const isLoading = ref(true);

const currentUserRole = computed(() => {
  const token = localStorage.getItem("token");
  if (!token) return null;
  try {
    const payloadBase64 = token.split(".")[1];
    if (!payloadBase64) return null;
    const payloadStr = atob(payloadBase64.replace(/-/g, "+").replace(/_/g, "/"));
    return JSON.parse(payloadStr).role;
  } catch (e) {
    return null;
  }
});

onMounted(async () => {
  try {
    const mine = currentUserRole.value === "ROLE_ADMIN" ? "false" : "true";
    const response = await http.get(`/images?page=0&size=100&mine=${mine}`);
    displayedImages.value = response.data.content;
  } catch (error) {
    console.error(error);
  } finally {
    isLoading.value = false;
  }
});

const deleteImage = async (id: number, event: Event) => {
  event.stopPropagation();
  if (!confirm("Are you sure you want to permanently delete this image?")) return;
  try {
    await http.delete(`/images/${id}`);
    displayedImages.value = displayedImages.value.filter((img) => img.id !== id);
  } catch (e) {
    alert("Failed to delete image.");
  }
};
</script>

<template>
  <div class="view-wrapper">
    <header class="page-header">
      <h1 class="page-title">My Archive</h1>
      <p class="page-subtitle">Manage your personal uploads.</p>
    </header>

    <div v-if="isLoading" class="label-text">Loading your images...</div>
    <div v-else-if="displayedImages.length === 0" class="empty-state">
      <h2>You haven't uploaded anything yet.</h2>
      <router-link to="/upload" class="btn" style="margin-top: 1rem">Upload an Image</router-link>
    </div>

    <div class="masonry-grid" v-else>
      <article
        v-for="image in displayedImages"
        :key="image.id"
        class="artifact-card"
        @click="router.push(`/image/${image.id}`)"
      >
        <img :src="'/images/' + image.id" class="artifact-img" loading="lazy" />

        <div class="hover-actions">
          <button
            @click.stop="deleteImage(image.id, $event)"
            title="Delete Permanently"
            class="btn-icon delete-btn"
          >
            <span class="material-symbols-outlined" style="font-size: 1.2rem">delete</span>
          </button>
        </div>

        <div class="card-overlay">
          <div class="overlay-content">
            <h3 class="artifact-name">@{{ image.uploader || "System" }}</h3>
            <div class="tags" v-if="image.keywords && image.keywords.length">
              <span class="tag-text">
                <span
                  v-for="(kw, index) in image.keywords.slice(0, 5)"
                  :key="kw.keyword"
                  :class="{ 'ai-tag': kw.isAi }"
                >
                  #{{ kw.keyword
                  }}<span v-if="Number(index) < Math.min(image.keywords.length, 5) - 1">, </span>
                </span>
                <span v-if="image.keywords.length > 5">, ...</span>
              </span>
            </div>
          </div>
        </div>
      </article>
    </div>
  </div>
</template>

<style scoped>
.empty-state {
  text-align: center;
  padding: var(--space-xl) 0;
  color: var(--text-muted);
}

.masonry-grid {
  columns: 1;
  column-gap: 1.5rem;
}
@media (min-width: 640px) {
  .masonry-grid {
    columns: 2;
  }
}
@media (min-width: 1024px) {
  .masonry-grid {
    columns: 3;
  }
}
@media (min-width: 1536px) {
  .masonry-grid {
    columns: 4;
  }
}

.artifact-card {
  break-inside: avoid;
  margin-bottom: 1.5rem;
  position: relative;
  background: var(--bg-element);
  cursor: pointer;
  overflow: hidden;
  border-radius: 4px;
}

.artifact-img {
  width: 100%;
  height: auto;
  display: block;
  transition: transform 0.4s var(--ease-standard);
}

/* Base Card Overlay matching the Gallery styles */
.card-overlay {
  position: absolute;
  inset: 0;
  background: linear-gradient(
    to top,
    rgba(0, 0, 0, 0.8) 0%,
    rgba(0, 0, 0, 0) 40%,
    rgba(0, 0, 0, 0) 70%,
    rgba(0, 0, 0, 0.7) 100%
  );
  opacity: 0;
  transition: opacity 0.3s ease;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  padding: 1.5rem;
  color: #fff;
  pointer-events: none;
}
.artifact-card:hover .card-overlay {
  opacity: 1;
}

.overlay-content {
  margin-top: auto;
}

.artifact-name {
  font-family: var(--font-sans);
  font-size: 1rem;
  font-weight: 500;
  margin: 0 0 0.25rem 0;
  color: #fff;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}

.tag-text {
  font-family: var(--font-sans);
  font-size: 0.8rem;
  color: rgba(255, 255, 255, 0.8);
}

/* Impeccable Context: Hover Interaction Design */
.hover-actions {
  position: absolute;
  top: 10px;
  right: 10px;
  opacity: 0;
  transition: opacity 0.2s;
  z-index: 10;
}
.artifact-card:hover .hover-actions {
  opacity: 1;
}

.delete-btn {
  background: var(--bg-surface);
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 0.5rem;
  border-radius: 4px;
  color: var(--color-danger);
  border: 1px solid transparent;
  transition: all 0.2s;
  box-shadow: var(--shadow-subtle);
  cursor: pointer;
}
.delete-btn:hover {
  background: var(--color-danger);
  color: #fff;
  border-color: var(--color-danger);
}
</style>


=========================================
File: ./frontend/src/views/RegisterView.vue
=========================================
<script setup lang="ts">
import { ref } from "vue";
import { useRouter } from "vue-router";
import http from "../api/http-client";
const username = ref("");
const password = ref("");
const errorMessage = ref("");
const successMessage = ref("");
const router = useRouter();
const handleRegister = async () => {
  errorMessage.value = "";
  successMessage.value = "";
  try {
    await http.post("/auth/register", {
      username: username.value,
      password: password.value,
    });
    successMessage.value = "Registration successful! Redirecting to login...";
    setTimeout(() => {
      router.push("/login");
    }, 1500);
  } catch (e: any) {
    errorMessage.value = e.response?.data?.error || "Registration failed. Username might be taken.";
  }
};
</script>
<template>
  <div class="view-wrapper auth-wrapper">
    <div class="meta-card login-card">
      <h1 class="page-title">Create Account</h1>
      <form @submit.prevent="handleRegister" class="config-form">
        <div class="form-group">
          <label class="label-text">Username</label>
          <input type="text" v-model="username" required autofocus minlength="3" />
        </div>
        <div class="form-group">
          <label class="label-text">Password</label>
          <input type="password" v-model="password" required minlength="5" />
        </div>
        <p v-if="errorMessage" class="error-text">{{ errorMessage }}</p>
        <p v-if="successMessage" class="success-text">{{ successMessage }}</p>
        <button type="submit" class="btn w-full mt-4">Register</button>
        <div style="text-align: center; margin-top: 1rem">
          <router-link to="/login" class="text-link"
            >Already have an account? Login here.</router-link
          >
        </div>
      </form>
    </div>
  </div>
</template>
<style scoped>
.auth-wrapper {
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 60vh;
}

.login-card {
  width: 100%;
  max-width: 400px;
  padding: 2.5rem;
  background: var(--bg-surface);
  border: 1px solid var(--border-subtle);
  border-radius: 8px;
}

.config-form {
  display: flex;
  flex-direction: column;
  gap: 1.5rem;
}

.text-link {
  color: var(--color-accent);
  font-size: 0.9rem;
  text-decoration: none;
  font-weight: 500;
  transition: opacity 0.2s;
}

.text-link:hover {
  opacity: 0.7;
}

/* Cruelty Overrides */
</style>


=========================================
File: ./frontend/src/views/UploadView.vue
=========================================
<script setup lang="ts">
import { ref } from "vue";
import { useUploadQueue } from "../composables/useUploadQueue";

const tagsInput = ref("");
const {
  tasks,
  tagsList,
  isUploading,
  globalMessage,
  statusCache,
  executeUpload,
  onFileChange,
  removeTaskById,
  clearAll,
} = useUploadQueue();

const addTag = (event: KeyboardEvent | FocusEvent) => {
  event.preventDefault();
  const t = tagsInput.value.trim().toLowerCase().replace(/\s+/g, "_");
  if (t && !tagsList.value.includes(t)) {
    tagsList.value.push(t);
  }
  tagsInput.value = "";
};

const removeTag = (tag: string) => {
  tagsList.value = tagsList.value.filter((t) => t !== tag);
};
</script>

<template>
  <div class="view-wrapper">
    <header class="page-header">
      <h1 class="page-title">Upload Images</h1>
      <p class="page-subtitle">
        Add up to 10 images. They will be mathematically analyzed for similarity search.
      </p>
    </header>
    <div class="upload-grid">
      <section class="queue-col">
        <div class="drop-zone" v-if="!isUploading && tasks.length < 10">
          <div class="drop-box">
            <span class="material-symbols-outlined icon">add_photo_alternate</span>
            <span class="label-text">Select Files (Max 10)</span>
          </div>
          <input
            type="file"
            @change="onFileChange"
            accept="image/*"
            multiple
            class="file-input"
            title=" "
          />
        </div>
        <div class="task-list" v-if="tasks.length > 0">
          <div v-for="task in tasks" :key="task.internalId" class="task-item">
            <img :src="task.previewUrl" class="task-thumb" />
            <div class="task-info">
              <span class="task-name" :title="task.file.name">{{ task.file.name }}</span>
              <span class="status-badge" :class="task.status.toLowerCase()">
                {{
                  task.status === "EXTRACTING" && statusCache[task.id!]
                    ? statusCache[task.id!]
                    : task.status
                }}
              </span>
            </div>
            <button
              class="btn-icon"
              @click="removeTaskById(task.internalId)"
              v-if="!isUploading && task.status === 'PENDING'"
            >
              <span class="material-symbols-outlined">close</span>
            </button>
          </div>
        </div>
      </section>
      <section class="meta-col">
        <div class="card meta-card">
          <h3 class="meta-title">Batch Metadata</h3>
          <div class="input-group">
            <label class="label-text">Tags (Applied to all)</label>
            <div class="tags-container">
              <span v-for="tag in tagsList" :key="tag" class="tag-pill">
                {{ tag }}
                <button @click="removeTag(tag)" class="tag-remove">
                  <span class="material-symbols-outlined">close</span>
                </button>
              </span>
              <input
                type="text"
                v-model="tagsInput"
                @keydown.enter="addTag"
                class="tag-input"
                placeholder="Type tag and press enter..."
                :disabled="isUploading"
              />
            </div>
          </div>
        </div>
        <p class="global-msg" v-if="globalMessage">{{ globalMessage }}</p>
        <div class="actions">
          <button
            class="btn w-full"
            @click="executeUpload"
            :disabled="tasks.length === 0 || isUploading"
          >
            {{ isUploading ? "Processing..." : "Upload & Analyze" }}
          </button>
          <button
            class="btn btn-outline w-full"
            @click="clearAll"
            v-if="tasks.length > 0 && !isUploading"
          >
            Clear Queue
          </button>
        </div>
      </section>
    </div>
  </div>
</template>

<style scoped>
.upload-grid {
  display: grid;
  grid-template-columns: 1fr;
  gap: var(--space-lg);
  align-items: start;
}

.queue-col {
  min-width: 0;
}

@media (min-width: 1024px) {
  .upload-grid {
    grid-template-columns: 2fr 1fr;
  }
}

.drop-zone {
  position: relative;
  background: var(--bg-surface-alt);
  border: 2px dashed var(--border-subtle);
  border-radius: 8px;
  padding: 3rem;
  text-align: center;
  transition: all 0.2s;
  margin-bottom: var(--space-md);
}

.drop-zone:hover {
  border-color: var(--border-strong);
  background: var(--bg-element);
}

.file-input {
  position: absolute;
  inset: 0;
  width: 100%;
  height: 100%;
  opacity: 0;
  cursor: pointer;
  z-index: 10;
}

.drop-box {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 1rem;
  color: var(--text-secondary);
}

.drop-box .icon {
  font-size: 3rem;
}

.task-list {
  display: flex;
  flex-direction: column;
  gap: 0.75rem;
}

.task-item {
  display: flex;
  align-items: center;
  min-width: 0;
  gap: 1rem;
  background: var(--bg-surface);
  border: 1px solid var(--border-subtle);
  padding: 0.75rem;
  border-radius: 6px;
}

.task-thumb {
  width: 48px;
  height: 48px;
  object-fit: cover;
  border-radius: 4px;
  flex-shrink: 0;
}

.task-info {
  flex: 1;
  min-width: 0;
  display: flex;
  flex-direction: column;
  gap: 0.25rem;
  overflow: hidden;
}

.task-name {
  font-size: 0.9rem;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  color: var(--text-primary);
}

.status-badge {
  font-size: 0.75rem;
  font-weight: 600;
  text-transform: uppercase;
  letter-spacing: 0.05em;
}

.status-badge.pending {
  color: var(--text-muted);
}

.status-badge.uploading {
  color: var(--color-accent);
}

.status-badge.extracting {
  color: var(--color-accent);
  animation: pulseText 1.5s infinite;
}

.status-badge.completed {
  color: var(--color-success);
}

.status-badge.failed {
  color: var(--color-danger);
}

.status-badge.duplicate {
  color: #f59e0b;
}

@keyframes pulseText {
  0% {
    opacity: 1;
  }
  50% {
    opacity: 0.5;
  }
  100% {
    opacity: 1;
  }
}

.btn-icon {
  background: none;
  border: none;
  color: var(--text-muted);
  cursor: pointer;
  padding: 0.25rem;
  display: flex;
  flex-shrink: 0;
}

.btn-icon:hover {
  color: var(--color-danger);
}

.meta-card {
  background: var(--bg-surface);
  border: 1px solid var(--border-subtle);
  border-radius: 8px;
  padding: 1.5rem;
  margin-bottom: var(--space-md);
}

.meta-title {
  font-size: 1.25rem;
  margin-bottom: 1.5rem;
  font-family: var(--font-sans);
  font-weight: 600;
}

.input-group label {
  display: block;
  margin-bottom: 0.75rem;
}

.tag-remove span {
  font-size: 14px;
}

.global-msg {
  font-size: 0.9rem;
  color: var(--text-secondary);
  margin-bottom: 1rem;
  text-align: center;
}

.actions {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}
</style>


=========================================
File: ./frontend/src/views/AboutView.vue
=========================================
<script setup lang="ts">
import { ref, onMounted, computed } from "vue";
import http from "../api/http-client";

const stats = ref({ total: 0, tags: 0 });
const isLoaded = ref(false);

// Calculate approximate tensor index size based on float array sizes
const indexMemorySize = computed(() => {
  const bytesPerImage = 3831 * 4; // 3831 dimensions * 4 bytes (float32)
  const totalMegabytes = (stats.value.total * bytesPerImage) / (1024 * 1024);
  return totalMegabytes < 1 ? "< 1.0" : totalMegabytes.toFixed(1);
});

onMounted(async () => {
  try {
    const [imgRes, tagRes] = await Promise.all([
      http.get("/images?size=1"),
      http.get("/images/keywords"),
    ]);
    stats.value.total = imgRes.data.totalElements ?? 0;
    stats.value.tags = tagRes.data.length || 0;
  } catch (e) {
    console.error("Could not fetch stats", e);
  } finally {
    isLoaded.value = true;
  }
});
</script>

<template>
  <div class="view-wrapper">
    <div class="about-header text-center">
      <h1 class="page-title">System Architecture</h1>
      <p class="about-desc">
        PDLaser v3a is a high-performance, vector-based visual archiving system. It integrates
        advanced machine learning and pure mathematical matrices to execute deep similarity searches
        based on object semantics, structure, color, and intensity.
      </p>
    </div>

    <div class="stats-strip" :class="{ 'fade-in': isLoaded }">
      <div class="stat-box">
        <span class="stat-val">{{ stats.total }}</span>
        <span class="label-text">Images Indexed</span>
      </div>
      <div class="divider-vertical"></div>
      <div class="stat-box">
        <span class="stat-val">{{ stats.tags }}</span>
        <span class="label-text">Unique Tags</span>
      </div>
      <div class="divider-vertical"></div>
      <div class="stat-box">
        <span class="stat-val">3,831</span>
        <span class="label-text">Dimensions / Vector</span>
      </div>
      <div class="divider-vertical"></div>
      <div class="stat-box">
        <span class="stat-val"
          >{{ indexMemorySize }} <span style="font-size: 1.5rem">MB</span></span
        >
        <span class="label-text">Estimated Tensor Size</span>
      </div>
      <div class="divider-vertical"></div>
      <div class="stat-box">
        <span class="stat-val status-green">Online</span>
        <span class="label-text">System Status</span>
      </div>
    </div>

    <h3 class="section-heading label-text">Extraction Pipeline</h3>
    <div class="pipeline-container">
      <div class="pipeline-step">
        <div class="step-circle material-symbols-outlined">upload_file</div>
        <div class="step-content">
          <h4>1. Ingestion</h4>
          <p>Multipart payload intercepted and buffered in memory.</p>
        </div>
      </div>
      <div class="pipeline-line"></div>
      <div class="pipeline-step">
        <div class="step-circle material-symbols-outlined">aspect_ratio</div>
        <div class="step-content">
          <h4>2. Lanczos3</h4>
          <p>BoofCV downsamples spatial data to a strict 256x256 grid.</p>
        </div>
      </div>
      <div class="pipeline-line"></div>
      <div class="pipeline-step">
        <div class="step-circle material-symbols-outlined">memory</div>
        <div class="step-content">
          <h4>3. Extraction</h4>
          <p>SigLIP 2 and standard vision algorithms map visual features.</p>
        </div>
      </div>
      <div class="pipeline-line"></div>
      <div class="pipeline-step">
        <div class="step-circle material-symbols-outlined">database</div>
        <div class="step-content">
          <h4>4. HNSW Index</h4>
          <p>L2 Normalized vectors injected into PostgreSQL pgvector.</p>
        </div>
      </div>
    </div>

    <h3 class="section-heading label-text">Engine Specifications</h3>
    <div class="tech-grid">
      <div class="tech-card">
        <div class="card-icon material-symbols-outlined">hub</div>
        <h2>pgvector & HNSW</h2>
        <p>
          Utilizes PostgreSQL's <code>pgvector</code> extension for high-dimensional tensor storage.
          Queries are accelerated via Hierarchical Navigable Small World (HNSW) graphs, executing
          Cosine and L2 Distances natively.
        </p>
      </div>

      <div class="tech-card">
        <div class="card-icon material-symbols-outlined">psychology</div>
        <h2>SigLIP 2 AI Processing</h2>
        <p>
          Integrates Microsoft's ONNX Runtime to execute a localized SigLIP 2 Vision Transformer
          network. Extracts 1000-dimensional semantic logits entirely in-memory for contextual
          matching.
        </p>
      </div>

      <div class="tech-card">
        <div class="card-icon material-symbols-outlined">grid_on</div>
        <h2>BoofCV Interpolation</h2>
        <p>
          Images are mathematically resampled using the precise
          <strong>Lanczos3</strong> algorithm before deep mathematical histograms (HOG, HSV, CIELAB)
          are extracted.
        </p>
      </div>

      <div class="tech-card">
        <div class="card-icon material-symbols-outlined">speed</div>
        <h2>Virtual Threads (Java 21)</h2>
        <p>
          Built on Spring Boot 4. Heavy vector calculus and asynchronous I/O operations are
          delegated to non-blocking virtual thread pools, allowing massive concurrent extraction
          queues.
        </p>
      </div>

      <div class="tech-card">
        <div class="card-icon material-symbols-outlined">layers</div>
        <h2>Vue 3 Reactive UI</h2>
        <p>
          The frontend architecture utilizes Vite, the Composition API, and strict TypeScript. State
          is managed ephemerally, with asynchronous polling maintaining UI consistency during
          background tasks.
        </p>
      </div>

      <div class="tech-card">
        <div class="card-icon material-symbols-outlined">dns</div>
        <h2>Containerized Micro-topology</h2>
        <p>
          Deployed via multi-stage Docker builds. The database, Java backend, and NGINX-served
          frontend operate in isolated alpine containers connected over a private bridge network.
        </p>
      </div>
    </div>
  </div>
</template>

<style scoped>
.about-header {
  max-width: 800px;
  margin: 0 auto 3rem auto;
  text-align: center;
}

.about-desc {
  font-size: 1.2rem;
  line-height: 1.6;
  color: var(--text-secondary);
}

.stats-strip {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background: var(--bg-surface);
  border: 1px solid var(--border-subtle);
  border-radius: 8px;
  padding: 2rem 3rem;
  margin-bottom: 4rem;
  opacity: 0;
  transform: translateY(10px);
  transition: all 0.5s ease;
  flex-wrap: wrap;
  gap: 2rem;
}
.stats-strip.fade-in {
  opacity: 1;
  transform: translateY(0);
}

.stat-box {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 0.5rem;
  flex: 1;
  min-width: 150px;
}

.stat-val {
  font-family: var(--font-sans);
  font-size: 3rem;
  font-weight: 300;
  color: var(--color-accent);
  line-height: 1;
}

.status-green {
  color: var(--color-success);
  font-size: 2rem;
  font-weight: 500;
  letter-spacing: 1px;
}

.divider-vertical {
  width: 1px;
  height: 60px;
  background: var(--border-subtle);
  display: none;
}
@media (min-width: 1024px) {
  .divider-vertical {
    display: block;
  }
}

.section-heading {
  margin-bottom: 2rem;
  padding-bottom: 0.5rem;
  border-bottom: 1px solid var(--border-subtle);
  text-transform: uppercase;
  letter-spacing: 1px;
}

/* Pipeline CSS */
.pipeline-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 1.5rem;
  margin-bottom: 5rem;
  background: var(--bg-surface);
  padding: 2rem 1.5rem;
  border-radius: 8px;
  border: 1px solid var(--border-subtle);
}

@media (min-width: 768px) {
  .pipeline-container {
    flex-direction: row;
    align-items: flex-start;
    justify-content: space-between;
    padding: 3rem;
    gap: 0;
  }
}

.pipeline-step {
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
  flex: 1;
  min-width: 150px;
  position: relative;
  z-index: 2;
}
.step-circle {
  width: 60px;
  height: 60px;
  background: var(--bg-element);
  border: 2px solid var(--color-accent);
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 2rem;
  color: var(--text-primary);
  margin-bottom: 1rem;
  transition:
    transform 0.3s ease,
    background 0.3s ease;
}
.pipeline-step:hover .step-circle {
  transform: scale(1.1);
  background: var(--color-accent);
  color: var(--text-on-accent);
}
.step-content h4 {
  margin: 0 0 0.5rem 0;
  font-family: var(--font-sans);
}
.step-content p {
  margin: 0;
  font-size: 0.85rem;
  color: var(--text-secondary);
}

.pipeline-line {
  flex: none;
  width: 2px;
  height: 40px;
  background: var(--border-strong);
  margin: 0;
  position: relative;
}

@media (min-width: 768px) {
  .pipeline-line {
    flex: 1;
    height: 2px;
    width: auto;
    margin-top: 30px;
    min-width: 50px;
  }
}

.pipeline-line::after {
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  height: 0%;
  width: 100%;
  background: var(--color-accent);
  transition:
    height 0.5s ease,
    width 0.5s ease;
}

@media (min-width: 768px) {
  .pipeline-line::after {
    height: 100%;
    width: 0%;
  }
}

.pipeline-container:hover .pipeline-line::after {
  height: 100%;
}

@media (min-width: 768px) {
  .pipeline-container:hover .pipeline-line::after {
    width: 100%;
  }
}

/* Tech Grid CSS */
.tech-grid {
  display: grid;
  grid-template-columns: 1fr;
  gap: 1.5rem;
}
@media (min-width: 768px) {
  .tech-grid {
    grid-template-columns: 1fr 1fr;
  }
}
@media (min-width: 1024px) {
  .tech-grid {
    grid-template-columns: 1fr 1fr 1fr;
  }
}

.tech-card {
  background: var(--bg-surface);
  border: 1px solid var(--border-subtle);
  border-radius: 8px;
  padding: 2rem;
  transition:
    transform 0.2s ease,
    border-color 0.2s ease,
    box-shadow 0.2s ease;
}
.tech-card:hover {
  transform: translateY(-4px);
  border-color: var(--border-strong);
  box-shadow: var(--shadow-subtle);
}

.card-icon {
  font-size: 2.5rem;
  color: var(--color-accent);
  margin-bottom: 1rem;
}

.tech-card h2 {
  font-size: 1.25rem;
  font-family: var(--font-sans);
  font-weight: 600;
  margin-bottom: 1rem;
}

.tech-card p {
  color: var(--text-secondary);
  line-height: 1.6;
  margin: 0;
  font-size: 0.95rem;
}
</style>


=========================================
File: ./frontend/src/views/AdminView.vue
=========================================
<script setup lang="ts">
import { ref, onMounted, onUnmounted, computed, watch } from "vue";
import http from "../api/http-client";

const activeTab = ref("sync");
const status = ref("Loading...");
const limit = ref(5000);
const offset = ref(0);
const fileType = ref("PHOTOS");
const selectedFile = ref<File | null>(null);
let pollingInterval: any = null;

// Catalog State
const catalogImages = ref<any[]>([]);
const searchQuery = ref("");
const searchCamera = ref("");
const searchCountry = ref("");
const currentPage = ref(0);
const totalPages = ref(1);
const selectedIds = ref<Set<number>>(new Set());
const batchLimit = ref(1000);

const isBusy = computed(() => {
  return (
    status.value.startsWith("DOWNLOADING") ||
    status.value.startsWith("EXTRACTING") ||
    status.value.startsWith("SYNCING") ||
    status.value.startsWith("IMPORTING")
  );
});

const checkStatus = async () => {
  try {
    const res = await http.get("/admin/unsplash/status");
    status.value = res.data.status;
  } catch (e) {
    status.value = "Access Denied / Error";
  }
};

const onFileChange = (event: Event) => {
  const target = event.target as HTMLInputElement;
  selectedFile.value = target.files?.[0] || null;
};

const startUploadAndSync = async () => {
  if (!selectedFile.value) {
    alert("Please select a file first (e.g., photos.csv000 or photos.tsv).");
    return;
  }

  const formData = new FormData();
  formData.append("file", selectedFile.value);
  formData.append("limit", limit.value.toString());
  formData.append("offset", offset.value.toString());
  formData.append("fileType", fileType.value);

  try {
    await http.post("/admin/unsplash/upload", formData, {
      headers: { "Content-Type": "multipart/form-data" },
    });
    // Clear selection after upload
    selectedFile.value = null;
    (document.getElementById("datasetFileInput") as HTMLInputElement).value = "";
    checkStatus();
  } catch (e: any) {
    alert(e.response?.data?.message || "Failed to upload and sync file.");
  }
};

const fetchCatalog = async (page = 0) => {
  currentPage.value = page;
  try {
    const res = await http.get(`/admin/unsplash/catalog`, {
      params: {
        page,
        size: 24,
        query: searchQuery.value,
        camera: searchCamera.value,
        country: searchCountry.value,
      },
    });
    catalogImages.value = res.data.content;
    totalPages.value = res.data.totalPages || 1;
  } catch (e) {
    console.error("Failed to load catalog");
  }
};

const toggleSelection = (id: number) => {
  if (selectedIds.value.has(id)) {
    selectedIds.value.delete(id);
  } else {
    selectedIds.value.add(id);
  }
};

const selectAll = () => {
  if (selectedIds.value.size === catalogImages.value.length) {
    selectedIds.value.clear();
  } else {
    catalogImages.value.forEach((img) => selectedIds.value.add(img.id));
  }
};

const importSelected = async () => {
  if (selectedIds.value.size === 0) return;
  try {
    await http.post("/admin/unsplash/import", Array.from(selectedIds.value));
    selectedIds.value.clear();
    fetchCatalog(currentPage.value);
    checkStatus();
  } catch (e: any) {
    alert(e.response?.data?.message || "Failed to start import");
  }
};

const importBatch = async () => {
  if (!confirm(`Are you sure you want to queue the top ${batchLimit.value} matches?`)) return;
  try {
    await http.post("/admin/unsplash/import/batch", null, {
      params: {
        limit: batchLimit.value,
        query: searchQuery.value,
        camera: searchCamera.value,
        country: searchCountry.value,
      },
    });
    fetchCatalog(currentPage.value);
    checkStatus();
  } catch (e: any) {
    alert(e.response?.data?.message || "Failed to start batch import");
  }
};

const handleSearch = () => {
  fetchCatalog(0);
};

// Users Tab
const pendingUsers = ref<any[]>([]);
const autoApprove = ref(false);

const fetchAutoApprove = async () => {
  try {
    const res = await http.get("/admin/users/settings/auto-approve");
    autoApprove.value = res.data.enabled;
  } catch (e) {
    console.error("Failed to fetch auto-approve setting");
  }
};

const toggleAutoApprove = async () => {
  try {
    await http.put(`/admin/users/settings/auto-approve?enabled=${autoApprove.value}`);
  } catch (e) {
    alert("Failed to update auto-approve setting");
    autoApprove.value = !autoApprove.value; // revert on fail
  }
};

const fetchPendingUsers = async () => {
  try {
    const res = await http.get("/admin/users/pending");
    if (Array.isArray(res.data)) {
      pendingUsers.value = res.data;
    } else {
      console.warn("Expected an array but got something else:", res.data);
      pendingUsers.value = [];
    }
  } catch (e) {
    console.error("Failed to fetch pending users");
    pendingUsers.value = [];
  }
};

const approveUser = async (id: number) => {
  try {
    await http.put(`/admin/users/${id}/approve`);
    fetchPendingUsers();
  } catch (e) {
    alert("Failed to approve user");
  }
};

const rejectUser = async (id: number) => {
  if (!confirm("Are you sure you want to reject and permanently delete this account?")) return;
  try {
    await http.delete(`/admin/users/${id}`);
    fetchPendingUsers();
  } catch (e) {
    alert("Failed to reject user");
  }
};

watch(activeTab, (newVal) => {
  if (newVal === "users") {
    fetchPendingUsers();
    fetchAutoApprove();
  }
});

onMounted(() => {
  checkStatus();
  fetchCatalog();
  pollingInterval = setInterval(checkStatus, 3000);
});

onUnmounted(() => {
  clearInterval(pollingInterval);
});
</script>

<template>
  <div class="view-wrapper">
    <header class="page-header text-center">
      <h1 class="page-title">Dataset Orchestrator</h1>
      <p class="page-subtitle">
        Upload dataset chunks, map remote metadata, and selectively import High-Res assets.
      </p>
    </header>

    <div class="system-status-bar" :class="{ active: isBusy, error: status.startsWith('ERROR') }">
      <span class="material-symbols-outlined icon" v-if="!status.startsWith('ERROR')">sync</span>
      <span class="material-symbols-outlined" v-else>error</span>
      <span class="status-text">{{ status }}</span>
    </div>

    <div class="tabs">
      <button :class="{ active: activeTab === 'sync' }" @click="activeTab = 'sync'">
        1. Upload & Sync
      </button>
      <button :class="{ active: activeTab === 'catalog' }" @click="activeTab = 'catalog'">
        2. Remote Catalog
      </button>
      <button :class="{ active: activeTab === 'users' }" @click="activeTab = 'users'">
        3. User Approvals <span v-if="pendingUsers.length > 0">({{ pendingUsers.length }})</span>
      </button>
    </div>

    <!-- TAB 1: UPLOAD & SYNC -->
    <div v-if="activeTab === 'sync'" class="sync-layout max-w-lg" style="margin: 0 auto">
      <div class="card meta-card">
        <h3 class="meta-title">Map Unsplash Dataset</h3>

        <div class="file-type-toggle">
          <label class="radio-label">
            <input type="radio" v-model="fileType" value="PHOTOS" />
            <span>📸 Photos Metadata (photos.tsv)</span>
          </label>
          <label class="radio-label">
            <input type="radio" v-model="fileType" value="KEYWORDS" />
            <span>🏷️ Keywords & Tags (keywords.tsv)</span>
          </label>
        </div>

        <p class="help-text" v-if="fileType === 'PHOTOS'">
          Select your local <code>photos.csv000</code> or <code>photos.tsv</code> file. The server
          will parse the metadata directly into the database.
          <strong>Actual image downloads occur in Step 2.</strong>
        </p>
        <p class="help-text" v-else style="color: var(--color-warning)">
          <strong>Note:</strong> You must sync Photos before syncing Keywords. Upload
          <code>keywords.tsv</code>. Keywords for unknown photos will be skipped.
        </p>

        <div class="file-drop-area" :class="{ 'has-file': selectedFile }">
          <span class="material-symbols-outlined" style="font-size: 3rem; margin-bottom: 1rem"
            >upload_file</span
          >
          <p v-if="!selectedFile">Click or drag a dataset file here</p>
          <p v-else style="font-weight: bold; color: var(--color-accent)">
            {{ selectedFile.name }} ({{ Math.round(selectedFile.size / 1024 / 1024) }} MB)
          </p>
          <input
            type="file"
            id="datasetFileInput"
            @change="onFileChange"
            accept=".tsv,.csv,.csv000,.txt"
            class="hidden-file-input"
          />
        </div>

        <div style="display: flex; gap: 1rem; margin: 1.5rem 0">
          <div class="form-group" style="flex: 1">
            <label class="label-text">Rows to Process</label>
            <input type="number" v-model="limit" min="1" max="1000000" />
          </div>
          <div class="form-group" style="flex: 1">
            <label class="label-text">Skip Rows</label>
            <input type="number" v-model="offset" min="0" />
          </div>
        </div>
        <button class="btn w-full" @click="startUploadAndSync" :disabled="isBusy || !selectedFile">
          Upload and Sync {{ fileType === "PHOTOS" ? "Photos" : "Keywords" }}
        </button>
      </div>
    </div>

    <!-- TAB 2: REMOTE CATALOG -->
    <div v-if="activeTab === 'catalog'" class="catalog-section">
      <div class="catalog-toolbar">
        <input
          type="text"
          v-model="searchQuery"
          @keydown.enter="handleSearch"
          placeholder="Search any..."
          class="search-bar"
        />
        <input
          type="text"
          v-model="searchCamera"
          @keydown.enter="handleSearch"
          placeholder="Filter Camera (e.g. Sony)..."
          class="search-bar"
        />
        <input
          type="text"
          v-model="searchCountry"
          @keydown.enter="handleSearch"
          placeholder="Filter Country (e.g. Japan)..."
          class="search-bar"
        />
        <button class="btn" @click="handleSearch">Search</button>
        <button class="btn btn-outline" @click="selectAll">Select All Visible</button>
      </div>

      <div
        class="batch-toolbar"
        style="margin-bottom: 1.5rem; display: flex; gap: 1rem; align-items: center"
      >
        <label class="label-text">Batch Import Count:</label>
        <input
          type="number"
          v-model="batchLimit"
          min="1"
          max="50000"
          class="search-bar"
          style="max-width: 150px"
        />
        <button class="btn btn-outline" @click="importBatch" :disabled="isBusy">
          Import Top {{ batchLimit }} Matches
        </button>
      </div>

      <div class="catalog-grid">
        <div
          v-for="img in catalogImages"
          :key="img.id"
          class="catalog-card"
          :class="{ selected: selectedIds.has(img.id) }"
          @click="toggleSelection(img.id)"
        >
          <div class="checkbox-indicator">
            <span class="material-symbols-outlined" v-if="selectedIds.has(img.id)"
              >check_circle</span
            >
          </div>
          <img :src="img.remote_url + '?w=400'" class="thumb" loading="lazy" />
          <div class="info">
            <span class="photog">📸 {{ img.photographer_name || "Unknown" }}</span>
            <span class="detail">🌍 {{ img.location_country || "Unknown Loc" }}</span>
            <span class="detail">📷 {{ img.camera_make || "Unknown Camera" }}</span>
            <span class="detail">⬇️ {{ img.stats_downloads }} downloads</span>
          </div>
        </div>
      </div>

      <div v-if="catalogImages.length === 0" class="empty-catalog">
        No remote metadata found. Please upload a dataset file in Step 1 first.
      </div>

      <div class="pagination" v-if="totalPages > 1">
        <button
          class="btn-outline"
          @click="fetchCatalog(currentPage - 1)"
          :disabled="currentPage === 0"
        >
          Prev
        </button>
        <span class="page-info">Page {{ currentPage + 1 }} of {{ totalPages }}</span>
        <button
          class="btn-outline"
          @click="fetchCatalog(currentPage + 1)"
          :disabled="currentPage === totalPages - 1"
        >
          Next
        </button>
      </div>

      <div class="sticky-action-bar" v-if="selectedIds.size > 0">
        <span
          ><strong>{{ selectedIds.size }}</strong> images selected for import.</span
        >
        <button class="btn" @click="importSelected" :disabled="isBusy">Download & Vectorize</button>
      </div>
    </div>

    <!-- TAB 3: USER APPROVALS -->
    <div v-if="activeTab === 'users'" class="users-section max-w-lg" style="margin: 0 auto">
      <div class="card meta-card">
        <div
          style="
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 1.5rem;
          "
        >
          <h3 class="meta-title" style="margin-bottom: 0">Pending Account Approvals</h3>
          <div style="display: flex; gap: 1rem; align-items: center">
            <label
              style="
                display: flex;
                align-items: center;
                gap: 0.5rem;
                cursor: pointer;
                color: var(--text-primary);
              "
            >
              <input type="checkbox" v-model="autoApprove" @change="toggleAutoApprove" />
              Auto-Approve New Users
            </label>
            <button
              class="btn btn-outline"
              @click="fetchPendingUsers"
              style="display: flex; align-items: center; gap: 0.5rem; padding: 0.4rem 0.8rem"
            >
              <span class="material-symbols-outlined" style="font-size: 1.2rem">refresh</span>
              Refresh
            </button>
          </div>
        </div>

        <div
          v-if="pendingUsers.length === 0"
          class="empty-state label-text"
          style="padding: 2rem 0; text-align: center"
        >
          No accounts pending approval.
        </div>

        <div v-else class="user-list">
          <div v-for="user in pendingUsers" :key="user.id" class="user-item">
            <span class="user-name">@{{ user.username }}</span>
            <div class="user-actions">
              <button class="btn" @click="approveUser(user.id)" :disabled="isBusy">Approve</button>
              <button
                class="btn btn-outline danger"
                @click="rejectUser(user.id)"
                :disabled="isBusy"
              >
                Reject
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.text-center {
  text-align: center;
}
.max-w-lg {
  max-width: 600px;
}

.system-status-bar {
  background: var(--bg-surface);
  border: 1px solid var(--border-subtle);
  padding: 1rem;
  border-radius: 8px;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 0.5rem;
  margin-bottom: 2rem;
  font-family: var(--font-mono);
  transition: all 0.3s;
}
.system-status-bar.active {
  background: var(--bg-element);
  border-color: var(--color-accent);
  color: var(--color-accent);
}
.system-status-bar.error {
  border-color: var(--color-danger);
  color: var(--color-danger);
}
.system-status-bar.active .icon {
  animation: spin 2s linear infinite;
}
@keyframes spin {
  100% {
    transform: rotate(360deg);
  }
}

.tabs {
  display: flex;
  justify-content: center;
  gap: 1rem;
  margin-bottom: 2rem;
  border-bottom: 1px solid var(--border-subtle);
  padding-bottom: 1rem;
}
.tabs button {
  background: none;
  border: none;
  font-family: var(--font-sans);
  font-size: 1rem;
  font-weight: 600;
  color: var(--text-secondary);
  cursor: pointer;
  padding: 0.5rem 1rem;
  border-radius: 4px;
  transition: all 0.2s;
}
.tabs button.active,
.tabs button:hover {
  background: var(--bg-element);
  color: var(--text-primary);
}

.meta-card {
  padding: 2rem;
}

.file-type-toggle {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
  margin-bottom: 1.5rem;
  background: var(--bg-surface-alt);
  padding: 1rem;
  border-radius: 8px;
  border: 1px solid var(--border-subtle);
}
.radio-label {
  display: flex;
  align-items: center;
  gap: 0.75rem;
  cursor: pointer;
  font-weight: 500;
  color: var(--text-primary);
}

/* New File Drop Area Styles */
.file-drop-area {
  position: relative;
  background: var(--bg-surface-alt);
  border: 2px dashed var(--border-subtle);
  border-radius: 8px;
  padding: 3rem 1rem;
  text-align: center;
  margin-top: 1.5rem;
  transition: all 0.2s ease;
  color: var(--text-secondary);
}
.file-drop-area:hover,
.file-drop-area.has-file {
  background: var(--bg-element);
  border-color: var(--color-accent);
}
.hidden-file-input {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  opacity: 0;
  cursor: pointer;
}

.catalog-toolbar {
  display: flex;
  gap: 1rem;
  margin-bottom: 1.5rem;
}
.search-bar {
  flex: 1;
  padding: 0.75rem 1rem;
  border: 1px solid var(--border-subtle);
  border-radius: 4px;
  background: var(--bg-surface);
  color: var(--text-primary);
}

.catalog-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  gap: 1.5rem;
  margin-bottom: 2rem;
}
.catalog-card {
  background: var(--bg-surface);
  border: 2px solid var(--border-subtle);
  border-radius: 8px;
  overflow: hidden;
  cursor: pointer;
  position: relative;
  transition:
    transform 0.2s,
    border-color 0.2s;
}
.catalog-card:hover {
  transform: translateY(-4px);
  border-color: var(--text-primary);
}
.catalog-card.selected {
  border-color: var(--color-accent);
}
.checkbox-indicator {
  position: absolute;
  top: 8px;
  left: 8px;
  color: var(--color-accent);
  background: rgba(0, 0, 0, 0.5);
  border-radius: 50%;
  display: flex;
}
.thumb {
  width: 100%;
  height: 150px;
  object-fit: cover;
  display: block;
}
.info {
  padding: 0.75rem;
  display: flex;
  flex-direction: column;
  gap: 0.25rem;
  font-size: 0.8rem;
}
.photog {
  font-weight: bold;
  color: var(--text-primary);
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.detail {
  color: var(--text-secondary);
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.empty-catalog {
  text-align: center;
  padding: 4rem;
  color: var(--text-muted);
  font-size: 1.1rem;
}

.pagination {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 1rem;
  margin-bottom: 6rem;
}
.btn-outline {
  padding: 0.5rem 1rem;
  border: 1px solid var(--border-subtle);
  background: var(--bg-surface);
  color: var(--text-primary);
  border-radius: 4px;
  cursor: pointer;
}
.btn-outline:disabled {
  opacity: 0.5;
  cursor: not-allowed;
}

.sticky-action-bar {
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  background: var(--bg-element);
  border-top: 1px solid var(--border-strong);
  padding: 1rem 2rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
  z-index: 100;
  box-shadow: 0 -4px 20px rgba(0, 0, 0, 0.2);
}

.user-list {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}
.user-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background: var(--bg-surface-alt);
  padding: 1rem 1.5rem;
  border-radius: 8px;
  border: 1px solid var(--border-subtle);
}
.user-name {
  font-weight: 600;
  font-size: 1.1rem;
  color: var(--text-primary);
}
.user-actions {
  display: flex;
  gap: 0.5rem;
}
.danger {
  color: var(--color-danger);
  border-color: var(--color-danger);
}
.danger:hover:not(:disabled) {
  background: var(--color-danger);
  color: #fff;
}

input[type="radio"] {
  width: auto;
  min-height: auto;
}
</style>


=========================================
File: ./frontend/src/views/GalleryView.vue
=========================================
<script setup lang="ts">
import { ref, onMounted, watch } from "vue";
import { useRoute, useRouter } from "vue-router";
import http from "../api/http-client";
import { useAuth } from "../composables/useAuth";
import { useGallerySearch, type Image } from "../composables/useGallerySearch";
import AdvancedSearchSidebar from "../components/AdvancedSearchSidebar.vue";

const route = useRoute();
const router = useRouter();
const { isLoggedIn } = useAuth();
const {
  similarityResultsOverride,
  similaritySourceOverride,
  selectedSourceId,
  clearSimilaritySearch,
} = useGallerySearch();

const displayedImages = ref<Image[]>([]);
const isLoading = ref(true);
const displayLimit = ref(30);
const currentPage = ref(0);
const totalPages = ref(1);

const isSidebarOpen = ref(false);

const openSimilarityForImage = (id: number | string) => {
  if (typeof id === "number") {
    selectedSourceId.value = id;
    isSidebarOpen.value = true;
  }
};

const handleSimilarityResults = (payload: any) => {
  const { results, sourceId, isEphemeral, fileUrl } = payload;

  // 1. Establish the visual Source Image highlight
  if (isEphemeral && fileUrl) {
    similaritySourceOverride.value = {
      id: "Ephemeral",
      url: fileUrl,
      uploader: "Uploaded Target",
      keywords: [{ keyword: "Source Image", isAi: false }],
    };
  } else if (sourceId) {
    similaritySourceOverride.value = {
      id: sourceId,
      url: `/images/${sourceId}`,
      uploader: "Database Target",
      keywords: [{ keyword: "Source Image", isAi: false }],
    };
  }

  // 2. Map the mathematical results
  similarityResultsOverride.value = results.map((res: any) => ({
    id: res.id,
    url: `/images/${res.id}`,
    uploader: "Matched Result",
    extraction_status: "COMPLETED",
    keywords: [{ keyword: `${((1 - res.score) * 100).toFixed(2)}% Match`, isAi: false }],
  }));
};

const loadImages = async (reset = false) => {
  if (reset) currentPage.value = 0;
  isLoading.value = true;
  clearSimilaritySearch();

  try {
    const activeTag = route.query.tags as string;
    let response: any;
    if (activeTag) {
      response = await http.get("/images/search", {
        params: { keywords: activeTag, page: currentPage.value, size: displayLimit.value },
      });
    } else {
      response = await http.get(`/images?page=${currentPage.value}&size=${displayLimit.value}`);
    }

    const { content, totalElements } = response.data as { content: any[]; totalElements: number };

    // Map full backend data including extraction_status
    displayedImages.value = content.map((img: any) => ({
      id: img.id,
      url: `/images/${img.id}`,
      uploader: img.uploader,
      keywords: img.keywords || [],
      extraction_status: img.extraction_status,
    }));
    totalPages.value = Math.ceil(totalElements / displayLimit.value) || 1;
  } catch (error) {
    if (reset) {
      displayedImages.value = [];
      totalPages.value = 1;
    }
  } finally {
    isLoading.value = false;
  }
};

const goToPage = (pageNumber: number) => {
  if (pageNumber >= 0 && pageNumber < totalPages.value) {
    currentPage.value = pageNumber;
    loadImages();
  }
};

const clearFilter = () => {
  router.push("/");
  clearSimilaritySearch();
};

onMounted(() => {
  if (route.query.sourceId) {
    selectedSourceId.value = parseInt(route.query.sourceId as string);
    isSidebarOpen.value = true;
  }
  loadImages(true);
});

watch(
  () => route.query.tags,
  () => loadImages(true),
);

const goToImage = (id: number | string) => {
  if (typeof id === "number") router.push(`/image/${id}`);
};
</script>

<template>
  <div class="gallery-layout">
    <div class="main-content">
      <div class="gallery-header">
        <h2 class="filter-title" v-if="similarityResultsOverride">Visual Matches</h2>
        <h2 class="filter-title" v-else-if="route.query.tags">
          Results for: <span class="highlight">#{{ route.query.tags }}</span>
        </h2>
        <h2 class="filter-title" v-else>Gallery</h2>

        <div class="header-actions">
          <button class="btn btn-outline" @click="isSidebarOpen = !isSidebarOpen">
            <span
              class="material-symbols-outlined"
              style="vertical-align: middle; margin-right: 0.25rem"
              >image_search</span
            >
            Similarity Search
          </button>
          <button
            class="btn btn-outline"
            v-if="route.query.tags || similarityResultsOverride"
            @click="clearFilter"
          >
            Clear Filter
          </button>
        </div>
      </div>

      <div v-if="isLoading && displayedImages.length === 0" class="empty-state label-text">
        Loading archive...
      </div>

      <div
        v-else-if="!similarityResultsOverride && displayedImages.length === 0"
        class="empty-state"
      >
        <h2 style="margin-bottom: 1rem">No images found.</h2>
        <p class="label-text" v-if="route.query.tags">Try a different search term.</p>
        <router-link to="/upload" class="btn" v-else-if="isLoggedIn"
          >Upload your first image</router-link
        >
        <p class="label-text" v-else>You must log in to upload images.</p>
      </div>

      <div v-else>
        <div class="masonry-grid">
          <article v-if="similaritySourceOverride" class="artifact-card source-card">
            <div class="source-badge">
              <span class="material-symbols-outlined" style="font-size: 1rem">target</span>
              SOURCE TARGET
            </div>
            <img :src="similaritySourceOverride.url" class="artifact-img" />
          </article>

          <article
            v-for="image in similarityResultsOverride || displayedImages"
            :key="image.id"
            class="artifact-card"
          >
            <img
              :src="image.url"
              @click="goToImage(image.id)"
              class="artifact-img"
              loading="lazy"
            />

            <div class="hover-actions" v-if="typeof image.id === 'number'">
              <button
                @click.stop="openSimilarityForImage(image.id)"
                title="Find Similar"
                class="btn-icon similarity-btn"
              >
                <span class="material-symbols-outlined">image_search</span>
              </button>
            </div>

            <div class="card-overlay" @click="goToImage(image.id)">
              <div
                class="meta-chips"
                v-if="image.extraction_status && image.extraction_status !== 'COMPLETED'"
              >
                <span class="meta-chip status-chip" :class="image.extraction_status.toLowerCase()">
                  {{ image.extraction_status }}
                </span>
              </div>

              <div class="overlay-content">
                <h3 class="artifact-name">@{{ image.uploader || "System" }}</h3>
                <div class="tags" v-if="image.keywords && image.keywords.length">
                  <span class="tag-text">
                    <span
                      v-for="(kw, index) in image.keywords.slice(0, 5)"
                      :key="kw.keyword"
                      :class="{ 'ai-tag': kw.isAi }"
                    >
                      #{{ kw.keyword
                      }}<span v-if="Number(index) < Math.min(image.keywords.length, 5) - 1"
                        >,
                      </span>
                    </span>
                    <span v-if="image.keywords.length > 5">, ...</span>
                  </span>
                </div>
              </div>
            </div>
          </article>
        </div>

        <div class="pagination-controls" v-if="!similarityResultsOverride && totalPages > 1">
          <button
            class="btn btn-outline"
            @click="goToPage(currentPage - 1)"
            :disabled="currentPage === 0 || isLoading"
          >
            &laquo; Prev
          </button>
          <div class="page-numbers">
            <button
              v-for="page in totalPages"
              :key="page"
              class="btn page-btn"
              :class="{ active: page - 1 === currentPage }"
              @click="goToPage(page - 1)"
              :disabled="isLoading"
            >
              {{ page }}
            </button>
          </div>
          <button
            class="btn btn-outline"
            @click="goToPage(currentPage + 1)"
            :disabled="currentPage === totalPages - 1 || isLoading"
          >
            Next &raquo;
          </button>
        </div>
      </div>
    </div>

    <AdvancedSearchSidebar
      v-if="isSidebarOpen"
      :initial-source-id="selectedSourceId"
      @close="
        isSidebarOpen = false;
        selectedSourceId = null;
      "
      @executeSimilarity="handleSimilarityResults"
    />
  </div>
</template>

<style scoped>
.gallery-layout {
  display: flex;
  gap: 2rem;
  align-items: flex-start;
}
.main-content {
  flex: 1;
  min-width: 0;
}

/* Source Image Outline CSS */
.source-card {
  border: 3px solid var(--color-accent);
  box-shadow: 0 0 20px color-mixin(in oklch, var(--color-accent) 40%, transparent);
  transform: translateY(-4px);
  z-index: 5;
}
.source-badge {
  position: absolute;
  top: 10px;
  left: 10px;
  background: var(--color-accent);
  color: var(--text-on-accent);
  padding: 0.25rem 0.75rem;
  border-radius: 20px;
  font-size: 0.75rem;
  font-weight: 700;
  display: flex;
  align-items: center;
  gap: 0.25rem;
  z-index: 10;
  box-shadow: var(--shadow-subtle);
}

.hover-actions {
  position: absolute;
  top: 10px;
  right: 10px;
  opacity: 1;
  transition: opacity 0.2s;
  z-index: 10;
}
@media (hover: hover) and (pointer: fine) {
  .hover-actions {
    opacity: 0;
  }
  .artifact-card:hover .hover-actions {
    opacity: 1;
  }
}

.similarity-btn {
  background: var(--bg-surface);
  padding: 0.5rem;
  border-radius: 4px;
  color: var(--text-primary);
  display: flex;
  align-items: center;
  justify-content: center;
  box-shadow: var(--shadow-subtle);
}
.similarity-btn:hover {
  color: var(--color-accent);
}

.gallery-header {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: flex-start;
  margin-bottom: var(--space-lg);
  padding-bottom: var(--space-sm);
  border-bottom: 1px solid var(--border-subtle);
  gap: 1rem;
}
@media (min-width: 768px) {
  .gallery-header {
    flex-direction: row;
    align-items: center;
    gap: 0;
  }
}

.header-actions {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
  width: 100%;
}
.header-actions .btn {
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
@media (min-width: 768px) {
  .header-actions {
    flex-direction: row;
    width: auto;
    gap: 1rem;
  }
  .header-actions .btn {
    width: auto;
  }
}

.filter-title {
  font-family: var(--font-headline);
  font-size: 2rem;
  margin: 0;
  word-break: break-word;
}
@media (min-width: 768px) {
  .filter-title {
    font-size: 2.5rem;
  }
}
.highlight {
  color: var(--color-accent);
  font-style: italic;
}

.empty-state {
  text-align: center;
  padding: var(--space-xl) 0;
  color: var(--text-muted);
}

.masonry-grid {
  columns: 1;
  column-gap: 1.5rem;
}
@media (min-width: 640px) {
  .masonry-grid {
    columns: 2;
  }
}
@media (min-width: 1024px) {
  .masonry-grid {
    columns: 3;
  }
}
@media (min-width: 1536px) {
  .masonry-grid {
    columns: 4;
  }
}

.artifact-card {
  break-inside: avoid;
  margin-bottom: 1.5rem;
  position: relative;
  background: var(--bg-element);
  overflow: hidden;
  border-radius: 4px;
}

.artifact-img {
  width: 100%;
  height: auto;
  display: block;
  transition: transform 0.4s var(--ease-standard);
}

.card-overlay {
  position: absolute;
  inset: 0;
  background: linear-gradient(
    to top,
    rgba(0, 0, 0, 0.8) 0%,
    rgba(0, 0, 0, 0) 40%,
    rgba(0, 0, 0, 0) 70%,
    rgba(0, 0, 0, 0.7) 100%
  );
  opacity: 0;
  transition: opacity 0.3s ease;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  padding: 1.5rem;
  color: #fff;
  cursor: pointer;
}
.artifact-card:hover .card-overlay {
  opacity: 1;
}

.overlay-content {
  margin-top: auto;
}

/* Metadata Chips CSS */
.meta-chips {
  display: flex;
  gap: 0.5rem;
  justify-content: flex-end;
}
.meta-chip {
  font-family: var(--font-sans);
  font-size: 0.7rem;
  font-weight: 700;
  padding: 0.25rem 0.5rem;
  border-radius: 4px;
  background: rgba(0, 0, 0, 0.5);
  backdrop-filter: blur(4px);
  text-transform: uppercase;
}
.status-chip.completed {
  color: var(--color-success);
  border: 1px solid var(--color-success);
}
.status-chip.processing,
.status-chip.pending {
  color: #f59e0b;
  border: 1px solid #f59e0b;
}
.status-chip.failed {
  color: var(--color-danger);
  border: 1px solid var(--color-danger);
}
.id-chip {
  color: #fff;
  border: 1px solid rgba(255, 255, 255, 0.3);
}

.artifact-name {
  font-family: var(--font-sans);
  font-size: 1rem;
  font-weight: 500;
  margin: 0 0 0.25rem 0;
  color: #fff;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}
.tag-text {
  font-family: var(--font-sans);
  font-size: 0.8rem;
  color: rgba(255, 255, 255, 0.8);
}

.pagination-controls {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: var(--space-md);
  margin-top: var(--space-xl);
  padding-bottom: var(--space-lg);
}
.page-numbers {
  display: flex;
  gap: 0.5rem;
  overflow-x: auto;
}
.page-btn {
  background: var(--bg-surface);
  color: var(--text-primary);
  border: 1px solid var(--border-subtle);
  padding: 0.5rem 1rem;
  min-width: 40px;
}
.page-btn:disabled {
  opacity: 0.5;
  cursor: not-allowed;
}
.page-btn:hover:not(:disabled) {
  background: var(--bg-element);
  border-color: var(--text-primary);
}
.page-btn.active {
  background: var(--color-accent);
  color: #fff;
  border-color: var(--color-accent);
}
</style>


=========================================
File: ./frontend/src/App.vue
=========================================
<script setup lang="ts">
import { ref, onMounted, computed } from "vue";
import { useRouter, useRoute } from "vue-router";
import { useAuth } from "./composables/useAuth";
import { useGallerySearch } from "./composables/useGallerySearch";
import TagAutocomplete from "./shared/TagAutocomplete.vue";
import http from "./api/http-client";

const { isLoggedIn, logout } = useAuth();
const { clearSimilaritySearch } = useGallerySearch();
const router = useRouter();
const route = useRoute();
const theme = ref("light");
const isMobileMenuOpen = ref(false);
let audioCtx: AudioContext | null = null;
const popularTags = ref<string[]>([]);

const isAdmin = computed(() => {
  const token = localStorage.getItem("token");
  if (!token) return false;
  try {
    const payload = JSON.parse(atob(token.split(".")[1] || ""));
    return payload.role === "ROLE_ADMIN";
  } catch (e) {
    return false;
  }
});

const username = computed(() => {
  const token = localStorage.getItem("token");
  if (!token) return "";
  try {
    const payload = JSON.parse(atob(token.split(".")[1] || ""));
    return payload.sub || payload.username || "User";
  } catch (e) {
    return "";
  }
});

const fetchPopularTags = async () => {
  try {
    const res = await http.get("/images/keywords/popular?limit=15");
    popularTags.value = res.data;
  } catch (e) {
    console.error(e);
  }
};

const executeSearch = (tag: string) => {
  clearSimilaritySearch();
  if (!tag) {
    router.push({ path: "/" });
  } else {
    router.push({ path: "/", query: { tags: tag } });
  }
};

const playPainSound = () => {
  if (theme.value !== "cruelty") return;
  if (!audioCtx) {
    audioCtx = new (window.AudioContext || (window as any).webkitAudioContext)();
  }
  if (audioCtx.state === "suspended") {
    audioCtx.resume();
  }
  const osc = audioCtx.createOscillator();
  const gain = audioCtx.createGain();
  osc.type = "sawtooth";
  osc.frequency.setValueAtTime(Math.random() * 1000 + 100, audioCtx.currentTime);
  osc.frequency.exponentialRampToValueAtTime(10, audioCtx.currentTime + 0.1);
  gain.gain.setValueAtTime(0.3, audioCtx.currentTime);
  gain.gain.exponentialRampToValueAtTime(0.01, audioCtx.currentTime + 0.1);
  osc.connect(gain);
  gain.connect(audioCtx.destination);
  osc.start();
  osc.stop(audioCtx.currentTime + 0.2);
};

const handleGlobalClick = () => {
  playPainSound();
};

const toggleTheme = async (target: string) => {
  theme.value = target;
  document.documentElement.className = theme.value;
  localStorage.setItem("theme", theme.value);

  const linkId = "cruelty-theme-link";
  let link = document.getElementById(linkId) as HTMLLinkElement;

  if (target === "cruelty") {
    if (!link) {
      link = document.createElement("link");
      link.id = linkId;
      link.rel = "stylesheet";
      document.head.appendChild(link);
      const cssUrl = (await import("./assets/theme-cruelty.css?url")).default;
      link.href = cssUrl;
    }
  } else {
    if (link) link.remove();
  }
};

onMounted(() => {
  const savedTheme = localStorage.getItem("theme");
  if (savedTheme) {
    theme.value = savedTheme;
  } else if (window.matchMedia("(prefers-color-scheme: dark)").matches) {
    theme.value = "dark";
  } else {
    theme.value = "light";
  }
  toggleTheme(theme.value);
  document.addEventListener("click", handleGlobalClick);
  fetchPopularTags();
});
</script>

<template>
  <div class="crt-overlay"></div>
  <div class="app-layout">
    <header class="top-nav">
      <div class="nav-left">
        <router-link to="/" class="logo" @click="clearSimilaritySearch">pdl.</router-link>
      </div>

      <div class="nav-right" :class="{ 'mobile-open': isMobileMenuOpen }">
        <button class="hamburger-btn" @click="isMobileMenuOpen = !isMobileMenuOpen">
          <span class="material-symbols-outlined">{{ isMobileMenuOpen ? "close" : "menu" }}</span>
        </button>

        <div class="nav-content" :class="{ 'is-open': isMobileMenuOpen }">
          <nav class="nav-links">
            <router-link to="/about" @click="isMobileMenuOpen = false">About</router-link>
            <router-link v-if="isAdmin" to="/admin" @click="isMobileMenuOpen = false"
              >Admin</router-link
            >
          </nav>
          <div class="divider desktop-only"></div>
          <button
            class="cruelty-toggle"
            @click="
              toggleTheme(theme === 'cruelty' ? 'light' : 'cruelty');
              isMobileMenuOpen = false;
            "
          >
            {{ theme === "cruelty" ? "REVERT CRUELTY" : "CRUELTY" }}
          </button>
          <button
            class="theme-toggle"
            @click="
              toggleTheme(theme === 'light' ? 'dark' : 'light');
              isMobileMenuOpen = false;
            "
            v-if="theme !== 'cruelty'"
            title="Toggle Theme"
          >
            <span class="material-symbols-outlined">contrast</span>
          </button>

          <template v-if="isLoggedIn">
            <router-link
              to="/profile"
              class="user-badge"
              @click="isMobileMenuOpen = false"
              style="
                font-family: var(--font-mono);
                color: var(--text-muted);
                font-size: 0.85rem;
                text-decoration: none;
              "
              >@{{ username }}</router-link
            >
            <button
              @click="
                logout();
                isMobileMenuOpen = false;
              "
              class="btn logout-btn"
              style="
                background: none;
                border: none;
                cursor: pointer;
                color: var(--text-secondary);
                font-family: var(--font-sans);
                font-weight: 500;
              "
            >
              Logout
            </button>
            <router-link to="/upload" class="btn upload-btn" @click="isMobileMenuOpen = false"
              >Upload</router-link
            >
          </template>
          <template v-else>
            <router-link
              to="/login"
              class="btn login-btn"
              @click="isMobileMenuOpen = false"
              style="
                color: var(--text-secondary);
                font-family: var(--font-sans);
                font-weight: 500;
                text-decoration: none;
                background: transparent;
                border: none;
              "
              >Login</router-link
            >
            <router-link
              to="/register"
              class="btn btn-outline"
              @click="isMobileMenuOpen = false"
              style="padding: 0.4rem 1rem; border-radius: 4px"
              >Register</router-link
            >
          </template>
        </div>
      </div>
    </header>

    <div class="sub-nav">
      <div class="tags-scroll">
        <button
          @click="executeSearch('')"
          class="popular-tag-btn"
          :class="{ 'active-tag': !route.query.tags && route.path === '/' }"
        >
          Home
        </button>
        <button
          v-for="tag in popularTags"
          :key="tag"
          @click="executeSearch(tag)"
          class="popular-tag-btn"
          :class="{ 'active-tag': route.query.tags === tag }"
        >
          #{{ tag }}
        </button>
      </div>

      <div class="global-search">
        <span class="material-symbols-outlined search-icon">search</span>
        <TagAutocomplete @select="executeSearch" placeholder="Search any tag..." />
      </div>
    </div>

    <main class="content-canvas">
      <router-view></router-view>
    </main>
  </div>
</template>

<style scoped>
.app-layout {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}

/* Slimmer Top Nav */
.top-nav {
  position: relative; /* Ensure absolute children position relative to this */
  z-index: 51;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0.75rem max(1rem, env(safe-area-inset-right)) 0.75rem
    max(1rem, env(safe-area-inset-left));
  background: var(--bg-surface);
  border-bottom: 1px solid var(--border-subtle);
}

@media (min-width: 768px) {
  .top-nav {
    padding: 0.75rem max(2rem, env(safe-area-inset-right)) 0.75rem
      max(2rem, env(safe-area-inset-left));
  }
}

.nav-left {
  display: flex;
  align-items: center;
}

.logo {
  font-family: var(--font-headline);
  font-size: 2rem;
  font-style: italic;
  font-weight: 700;
  color: var(--text-primary);
  text-decoration: none;
  transition: opacity 0.2s;
}

.logo:hover {
  opacity: 0.7;
}

/* Sub Navigation */
.sub-nav {
  position: sticky;
  top: 0; /* Sits at the top since top-nav scrolls away */
  z-index: 50;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: stretch;
  padding: 0.5rem max(1rem, env(safe-area-inset-right)) 0.5rem max(1rem, env(safe-area-inset-left));
  background: var(--bg-element);
  border-bottom: 1px solid var(--border-subtle);
  gap: 1rem;
}

@media (min-width: 768px) {
  .sub-nav {
    flex-direction: row;
    align-items: center;
    padding: 0.5rem max(2rem, env(safe-area-inset-right)) 0.5rem
      max(2rem, env(safe-area-inset-left));
  }
}

.tags-scroll {
  display: flex;
  gap: 1rem;
  overflow-x: auto;
  white-space: nowrap;
  scrollbar-width: none;
  align-items: center;
  padding-bottom: 0.25rem;
  padding-left: max(1rem, env(safe-area-inset-left));
  padding-right: max(1rem, env(safe-area-inset-right));
  margin-left: calc(-1 * max(1rem, env(safe-area-inset-left)));
  margin-right: calc(-1 * max(1rem, env(safe-area-inset-right)));
}

@media (min-width: 768px) {
  .tags-scroll {
    gap: 1.5rem;
    padding-bottom: 0;
    margin: 0;
    padding: 0;
    width: auto;
  }
}
.tags-scroll::-webkit-scrollbar {
  display: none;
}

.popular-tag-btn {
  background: none;
  border: none;
  font-family: var(--font-sans);
  font-size: 0.9rem;
  color: var(--text-secondary);
  cursor: pointer;
  font-weight: 500;
  transition: color 0.2s;
  padding: 0.25rem 0;
}
.popular-tag-btn:hover {
  color: var(--text-primary);
}
.popular-tag-btn.active-tag {
  color: var(--color-accent);
  border-bottom: 2px solid var(--color-accent);
}

.global-search {
  display: flex;
  align-items: center;
  background: var(--bg-surface);
  border-radius: 4px;
  padding: 0.25rem 0.75rem;
  border: 1px solid var(--border-subtle);
  width: 100%;
  max-width: none;
  transition: border-color 0.2s;
  order: -1; /* move search above tags on mobile */
}

@media (min-width: 768px) {
  .global-search {
    max-width: 250px;
    order: 0;
  }
}
.global-search:focus-within {
  border-color: var(--color-accent);
}
.search-icon {
  color: var(--text-secondary);
  font-size: 1.25rem;
  margin-right: 0.5rem;
}

/* Right Nav Tools */
.nav-right {
  display: flex;
  align-items: center;
  gap: 1.5rem;
}
.hamburger-btn {
  display: block;
  background: none;
  border: none;
  color: var(--text-primary);
  cursor: pointer;
  padding: 0.5rem;
}
@media (min-width: 768px) {
  .hamburger-btn {
    display: none;
  }
}

.nav-content {
  display: none;
  position: absolute;
  top: 100%;
  left: 0;
  right: 0;
  background: var(--bg-surface);
  flex-direction: column;
  align-items: stretch;
  padding: 1.5rem 1rem;
  gap: 1rem;
  border-bottom: 1px solid var(--border-subtle);
  box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.5);
  z-index: 60;
}

.nav-content.is-open {
  display: flex;
}

@media (min-width: 768px) {
  .nav-content {
    display: flex;
    position: static;
    flex-direction: row;
    align-items: center;
    padding: 0;
    box-shadow: none;
    border-bottom: none;
    background: transparent;
  }
}

.nav-links {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}
@media (min-width: 768px) {
  .nav-links {
    flex-direction: row;
    gap: 1.5rem;
  }
}
.nav-links a {
  font-family: var(--font-sans);
  color: var(--text-secondary);
  text-decoration: none;
  font-size: 0.95rem;
  font-weight: 500;
  transition: color 0.2s;
  padding: 0.5rem 0; /* Larger touch targets */
}
@media (min-width: 768px) {
  .nav-links a {
    padding: 0;
  }
}
.nav-links a:hover,
.nav-links a.router-link-active {
  color: var(--text-primary);
}

.divider {
  height: 1px;
  width: 100%;
  background: var(--border-subtle);
  margin: 0.5rem 0;
}
@media (min-width: 768px) {
  .divider {
    height: 24px;
    width: 1px;
    margin: 0;
  }
  .divider.desktop-only {
    display: block;
  }
}
@media (max-width: 767px) {
  .divider.desktop-only {
    display: none;
  }
}

.cruelty-toggle {
  background: none;
  border: none;
  font-family: var(--font-sans);
  text-transform: uppercase;
  letter-spacing: 0.1em;
  font-size: 0.7rem;
  font-weight: 700;
  color: var(--text-muted);
  cursor: pointer;
  transition: color 0.2s;
  padding: 0.75rem 0; /* Larger touch target */
  text-align: left;
}
@media (min-width: 768px) {
  .cruelty-toggle {
    padding: 0;
    text-align: center;
  }
}

.theme-toggle {
  background: none;
  border: none;
  color: var(--text-secondary);
  cursor: pointer;
  display: flex;
  align-items: center;
  padding: 0.75rem 0;
}
@media (min-width: 768px) {
  .theme-toggle {
    padding: 0;
  }
}

.upload-btn {
  padding: 0.75rem;
  text-align: center;
}
@media (min-width: 768px) {
  .upload-btn {
    padding: 0.5rem 1.25rem;
  }
}
.btn-outline {
  border: 1px solid var(--border-strong);
  color: var(--text-primary);
  text-decoration: none;
  font-family: var(--font-sans);
  font-size: 0.9rem;
  font-weight: 500;
  transition: background 0.2s;
  padding: 0.75rem;
  text-align: center;
  border-radius: 4px;
}
@media (min-width: 768px) {
  .btn-outline {
    padding: 0.4rem 1rem;
  }
}
.btn-outline:hover {
  background: var(--bg-element);
}

.content-canvas {
  flex: 1;
  padding: 1rem max(1rem, env(safe-area-inset-right)) env(safe-area-inset-bottom)
    max(1rem, env(safe-area-inset-left));
  width: 100%;
}
@media (min-width: 768px) {
  .content-canvas {
    padding: 2rem max(2rem, env(safe-area-inset-right)) env(safe-area-inset-bottom)
      max(2rem, env(safe-area-inset-left));
  }
}
@media (min-width: 1024px) {
  .content-canvas {
    padding: 3rem max(4rem, env(safe-area-inset-right)) env(safe-area-inset-bottom)
      max(4rem, env(safe-area-inset-left));
  }
}

@keyframes pulse {
  0% {
    transform: scale(1);
  }
  50% {
    transform: scale(1.1);
  }
  100% {
    transform: scale(1);
  }
}
</style>


=========================================
File: ./frontend/tsconfig.app.json
=========================================
{
  "extends": "@vue/tsconfig/tsconfig.dom.json",
  "compilerOptions": {
    "tsBuildInfoFile": "./node_modules/.tmp/tsconfig.app.tsbuildinfo",
    "types": ["vite/client"],

    /* Linting */
    "strict": true,
    "noUnusedLocals": true,
    "noUnusedParameters": true,
    "erasableSyntaxOnly": true,
    "noFallthroughCasesInSwitch": true,
    "noUncheckedSideEffectImports": true
  },
  "include": ["src/**/*.ts", "src/**/*.tsx", "src/**/*.vue"]
}


=========================================
File: ./frontend/tsconfig.json
=========================================
{
  "files": [],
  "references": [{ "path": "./tsconfig.app.json" }, { "path": "./tsconfig.node.json" }]
}


=========================================
File: ./frontend/tsconfig.node.json
=========================================
{
  "compilerOptions": {
    "tsBuildInfoFile": "./node_modules/.tmp/tsconfig.node.tsbuildinfo",
    "target": "ES2023",
    "lib": ["ES2023"],
    "module": "ESNext",
    "types": ["node"],
    "skipLibCheck": true,

    /* Bundler mode */
    "moduleResolution": "bundler",
    "allowImportingTsExtensions": true,
    "verbatimModuleSyntax": true,
    "moduleDetection": "force",
    "noEmit": true,

    /* Linting */
    "strict": true,
    "noUnusedLocals": true,
    "noUnusedParameters": true,
    "erasableSyntaxOnly": true,
    "noFallthroughCasesInSwitch": true,
    "noUncheckedSideEffectImports": true
  },
  "include": ["vite.config.ts"]
}


=========================================
File: ./frontend/index.html
=========================================
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <link rel="icon" type="image/png" href="/pdl.png" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, viewport-fit=cover" />
    <title>pdl.</title>
    <!-- Standard Fonts -->
    <link
      href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&family=Newsreader:ital,opsz,wght@1,6..72,400;1,6..72,700;1,6..72,800&display=swap"
      rel="stylesheet"
    />
    <!-- Material Symbols -->
    <link
      href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:wght,FILL@100..700,0..1&display=swap"
      rel="stylesheet"
    />
  </head>
  <body>
    <div id="app"></div>
    <script type="module" src="/src/main.ts"></script>
  </body>
</html>


=========================================
File: ./frontend/nginx.conf
=========================================
limit_req_zone $binary_remote_addr zone=auth_limit:10m rate=1r/s;

server {
    listen 8080;

    client_max_body_size 100M;
    client_body_buffer_size 10M;
    proxy_buffers 8 5M;
    proxy_buffer_size 5M;

    location / {
        root /usr/share/nginx/html;
        index index.html index.htm;
        try_files $uri $uri/ /index.html;
    }

    location /images {
        proxy_pass http://backend:8080/images;
    }

    location /auth/register {
        limit_req zone=auth_limit burst=5 nodelay;
        proxy_pass http://backend:8080/auth/register;
    }

    location /auth {
        proxy_pass http://backend:8080/auth;
    }

    location /admin/unsplash {
        proxy_pass http://backend:8080/admin/unsplash;
    }

    location /admin/users {
        proxy_pass http://backend:8080/admin/users;
    }
}

=========================================
File: ./frontend/vite.config.ts
=========================================
import { defineConfig } from "vite";
import vue from "@vitejs/plugin-vue";
import vueDevTools from "vite-plugin-vue-devtools";

// https://vite.dev/config/
export default defineConfig({
  plugins: [vue(), vueDevTools()],
  server: {
    proxy: {
      "/images": {
        target: "http://backend:8080/images",
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/images/, ""),
      },
      "/auth": {
        target: "http://backend:8080/auth",
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/auth/, ""),
      },
      "/admin": {
        target: "http://backend:8080/admin",
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/admin/, ""),
      },
    },
  },
});


=========================================
File: ./package.json
=========================================
{
  "name": "v3a",
  "version": "1.0.0",
  "description": "**Live Environments (Coming Soon):** * Production URL: `[Insert Public Production Link Here]` * Preview URL: `[Insert Public Preview Link Here]`",
  "main": "index.js",
  "scripts": {
    "test": "echo \"Error: no test specified\" && exit 1",
    "format": "prettier -w ."
  },
  "repository": {
    "type": "git",
    "url": "git@gitlab.emi.u-bordeaux.fr:pdl-l3/teams/2026/v3/v3a.git"
  },
  "keywords": [],
  "author": "",
  "license": "ISC",
  "type": "commonjs",
  "devDependencies": {
    "@prettier/plugin-xml": "^3.4.2",
    "prettier": "3.8.1",
    "prettier-plugin-java": "2.8.1",
    "prettier-plugin-sh": "^0.18.0"
  }
}


=========================================
File: ./README.md
=========================================
# PDLaser - Image Management System (v3a)

**Live Environments:**

- 🟢 **Production URL:** `http://bigpdlaser.duckdns.org/`
- 🟡 **Preview URL:** `https://bigpreviewlaser.duckdns.org/gallery`

## Overview

PDLaser is a full-stack, AI-augmented image management system built for high-performance visual archiving. Moving beyond basic metadata storage, it executes deep content-based image retrieval (CBIR) using advanced mathematical histograms and localized SigLIP 2 semantic embeddings.

**Next-Gen Tech Stack:**

- **Backend:** Java 21, Spring Boot 4.0.5, Spring Data JDBC, Flyway, BoofCV 1.3.0, ONNX Runtime 1.17.1
- **Frontend:** Vue.js 3.5, TypeScript 6.0, Vite 8.0, Vue Router 5
- **Database:** PostgreSQL 18 + `pgvector` (HNSW Indexing)
- **Infrastructure:** Docker, Docker Compose, Nginx, Dokploy

## Architectural & Organizational Decisions

To accommodate the intense computational requirements of image vectorization and AI inference, several strict architectural decisions were made:

1. **Distributed Monolith over Microservices:** We retained a monolithic Spring Boot architecture to reduce network latency between the database and the vision processor. However, we decoupled the execution context by offloading all BoofCV and ONNX computations to background **Virtual Threads** via a custom `ThreadPoolTaskExecutor`. This prevents the heavy CPU workload from blocking the main Tomcat HTTP event loop.
2. **HNSW Indexing over IVFFlat:** For our `pgvector` database, we chose Hierarchical Navigable Small World (HNSW) indexes. While they require more RAM to build than IVFFlat, they provide drastically superior recall speed for our high-dimensional vectors (e.g., 1000D Semantic, 512D CIELAB).
3. **HTTP Long-Polling (`DeferredResult`):** Instead of introducing the architectural overhead of WebSockets, Redis, and STOMP to track background image extraction, we implemented Long-Polling. The client sends a request to `/status`, and the Spring backend hangs the connection securely in-memory using `DeferredResult` until the virtual thread finishes processing. This maintains statelessness while providing real-time frontend feedback.
4. **Ephemeral Local Storage (`tmpfs`):** Local and preview Docker databases are strictly mounted to RAM (`tmpfs`). This organizational decision enforces a "clean slate" upon every container restart, guaranteeing that our Flyway schema migrations (`V1` through `V3`) are always tested from zero, completely eliminating configuration drift between developer machines.
5. **Nginx Reverse Proxying:** To bypass the complexities and performance hits of CORS preflight `OPTIONS` requests, the production Vue 3.5 SPA is served via Nginx, which internally reverse-proxies `/images`, `/auth`, and `/admin` traffic directly to the backend container.

## Documentation (Wiki)

Detailed project documentation is maintained in the `docs/wiki` folder and our GitLab Wiki:

- 📖 https://gitlab.emi.u-bordeaux.fr/pdl-l3/teams/2026/v3/v3a/-/wikis/home

### Critical Enterprise Artifacts

As part of our commitment to highly scalable, enterprise-ready cloud-native development, two critical components are actively maintained:

1. **Zero-Trust State Validation:** To prevent localized supply chain tampering, the backend performs a cryptographic Subresource Integrity (SRI) validation via the Spring Boot Health Actuator. The server calculates the SHA-256 hash of a dense internal static asset (_The Whole War and Peace Novel.pdf_) bundled within the compiled `.jar`. If this deterministic baseline is altered, the system triggers a fatal structural collapse to protect runtime execution.
2. **Enterprise Operations Framework:** An exhaustive `enterprise_requirements.tex` document is included. This defines the systemic limitations of our current architecture and provides strategic guidance mandated by our lead Cloud Architect.

## Getting Started

### Prerequisites

- Docker & Docker Compose
- Java JDK 21 & Maven 3.9+ (For local non-Docker dev)
- Node.js 25+ (For local non-Docker dev)

### Quickstart (Docker Containerized Environment)

The easiest way to run the entire stack locally is via Docker Compose:

```bash
# Build and start the database, backend, and frontend
docker-compose up --build
```

- **Frontend:** Available at `http://localhost:3000`
- **Backend API:** Available at `http://localhost:8080`
- **Database:** Exposed on port `5432`

_Note: The default `admin` account is automatically seeded (Username: `admin` | Password: `admin`)._

### Development & Formatting

To ensure consistent code styling across both the frontend and backend, we use Prettier. Before committing your code, please run the following command from the root directory:

```bash
npm run format
```


=========================================
File: ./docker-compose.yml
=========================================
services:
  db:
    image: pgvector/pgvector:pg18
    environment:
      POSTGRES_USER: pdl_user
      POSTGRES_PASSWORD: ${POSTGRES_PASSWORD:-pdl_secure_password}
      POSTGRES_DB: pdl_db
    ports:
      - "5432:5432"
    tmpfs:
      - /var/lib/postgresql
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U pdl_user -d pdl_db"]
      interval: 5s
      timeout: 5s
      retries: 5
    restart: unless-stopped

  backend:
    build:
      context: ./backend
      dockerfile: Dockerfile
    environment:
      - HOST_NAME=db
      - DATABASE_NAME=pdl_db
      - DATABASE_USER=pdl_user
      - DATABASE_PASSWORD=${POSTGRES_PASSWORD:-pdl_secure_password}
      - APP_IMAGE_DIRECTORY=/var/lib/pdl/images
      - APP_UNSPLASH_DATASET_DIR=/var/lib/pdl/datasets
      - APP_MODELS_DIR=/var/lib/pdl/models
      - ADMIN_USERNAME=${ADMIN_USERNAME:-admin}
      - ADMIN_PASSWORD=${ADMIN_PASSWORD:-admin}
    volumes:
      - datasets:/var/lib/pdl/datasets
      - ./backend/models:/var/lib/pdl/models
    ports:
      - "8080:8080"
    depends_on:
      db:
        condition: service_healthy
    restart: unless-stopped

  frontend:
    build:
      context: ./frontend
      dockerfile: Dockerfile
    ports:
      - "3000:8080"
    depends_on:
      - backend
    restart: unless-stopped

volumes:
  datasets:


=========================================
File: ./docker-compose.preview.yml
=========================================
services:
  db:
    image: pgvector/pgvector:pg18
    environment:
      POSTGRES_USER: pdl_user
      POSTGRES_PASSWORD: ${POSTGRES_PASSWORD}
      POSTGRES_DB: pdl_db
    expose:
      - "5432"
    tmpfs:
      - /var/lib/postgresql
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U pdl_user -d pdl_db"]
      interval: 5s
      timeout: 5s
      retries: 5
    restart: unless-stopped
    logging: &default-logging
      driver: "json-file"
      options:
        max-size: "10m"
        max-file: "3"

  backend:
    build:
      context: ./backend
      dockerfile: Dockerfile
    environment:
      - HOST_NAME=db
      - DATABASE_NAME=pdl_db
      - DATABASE_USER=pdl_user
      - DATABASE_PASSWORD=${POSTGRES_PASSWORD}
      - APP_IMAGE_DIRECTORY=/var/lib/pdl/images
      - APP_UNSPLASH_DATASET_DIR=/var/lib/pdl/datasets
      - APP_MODELS_DIR=/var/lib/pdl/models
      - ADMIN_USERNAME=${ADMIN_USERNAME:-admin}
      - ADMIN_PASSWORD=${ADMIN_PASSWORD:-admin}
    expose:
      - "8080"
    volumes:
      - preview_images:/var/lib/pdl/images
      - preview_datasets:/var/lib/pdl/datasets
      - preview_models:/var/lib/pdl/models
    depends_on:
      db:
        condition: service_healthy
    restart: unless-stopped
    logging: *default-logging

  frontend:
    build:
      context: ./frontend
      dockerfile: Dockerfile
    expose:
      - "8080"
    depends_on:
      - backend
    restart: unless-stopped
    logging: *default-logging

volumes:
  preview_images:
  preview_datasets:
  preview_models:


=========================================
File: ./docker-compose.prod.yml
=========================================
services:
  db:
    image: pgvector/pgvector:pg18
    environment:
      POSTGRES_USER: pdl_user
      POSTGRES_PASSWORD: ${POSTGRES_PASSWORD}
      POSTGRES_DB: pdl_db
    expose:
      - "5432"
    volumes:
      - pgdata:/var/lib/postgresql/data
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U pdl_user -d pdl_db"]
      interval: 5s
      timeout: 5s
      retries: 5
    restart: unless-stopped
    logging: &default-logging
      driver: "json-file"
      options:
        max-size: "10m"
        max-file: "3"

  backend:
    build:
      context: ./backend
      dockerfile: Dockerfile
    environment:
      - HOST_NAME=db
      - DATABASE_NAME=pdl_db
      - DATABASE_USER=pdl_user
      - DATABASE_PASSWORD=${POSTGRES_PASSWORD}
      - APP_IMAGE_DIRECTORY=/var/lib/pdl/images
      - APP_MODELS_DIR=/var/lib/pdl/models
      - ADMIN_USERNAME=${ADMIN_USERNAME:-admin}
      - ADMIN_PASSWORD=${ADMIN_PASSWORD:-admin}
    expose:
      - "8080"
    volumes:
      - backend_images:/var/lib/pdl/images # BUG FIX: This was incorrectly mapped to pgdata:/var/lib/postgresql previously
      - backend_models:/var/lib/pdl/models
    depends_on:
      db:
        condition: service_healthy
    restart: unless-stopped
    logging: *default-logging

  frontend:
    build:
      context: ./frontend
      dockerfile: Dockerfile
    expose:
      - "8080"
    depends_on:
      - backend
    restart: unless-stopped
    logging: *default-logging

volumes:
  pgdata:
  backend_images:
  backend_models:


