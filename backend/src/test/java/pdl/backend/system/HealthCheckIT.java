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
