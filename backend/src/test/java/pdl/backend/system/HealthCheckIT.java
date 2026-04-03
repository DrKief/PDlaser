package pdl.backend.system;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.health.contributor.Health;

import static org.assertj.core.api.Assertions.assertThat;

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