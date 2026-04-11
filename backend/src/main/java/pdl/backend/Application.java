package pdl.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.password.PasswordEncoder;
import pdl.backend.auth.UserAccount;
import pdl.backend.auth.UserRepository;

@SpringBootApplication
@EnableAsync
public class Application {

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
}