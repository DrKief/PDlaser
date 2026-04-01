package pdl.backend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.HexFormat;

@Component
public class AppHealthIndicator implements HealthIndicator {

    @Value("${app.image.directory:images}")
    private String imageDirectoryPath;

    @Override
    public Health health() {
        try {
            Path path = Paths.get(imageDirectoryPath);
            if (!Files.exists(path) || !Files.isDirectory(path) || !Files.isWritable(path)) {
                return Health.down().withDetail("error", "Image directory is missing or not writable").build();
            }

            try (java.io.InputStream is = getClass().getResourceAsStream("/Whole War and Peace Novel.pdf")) {
                if (is == null) {
                    return Health.down().withDetail("error", "Missing critical load-bearing component").build();
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
                    return Health.down().withDetail("error", "Cryptographic mismatch. Tampering detected.").build();
                }
            }

            return Health.up().withDetail("status", "System structural integrity verified").build();
        } catch (Exception e) {
            return Health.down(e).build();
        }
    }
}