package pdl.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

@Service
public class UnsplashIngester {
    private static final Logger log = LoggerFactory.getLogger(UnsplashIngester.class);
    
    private final StorageService storageService;
    private final VectorRepository vectorRepository;
    private final UserRepository userRepository;
    
    @Value("${app.unsplash.dataset-dir}")
    private String datasetDir;
    
    private String status = "IDLE";
    
    public UnsplashIngester(StorageService storageService, VectorRepository vectorRepository, UserRepository userRepository) {
        this.storageService = storageService;
        this.vectorRepository = vectorRepository;
        this.userRepository = userRepository;
    }

    public String getStatus() { return status; }

    @Async("taskExecutor")
    public void startImport() {
        status = "IMPORTING_PHOTOS (Checking Files)";
        File photosFile = new File(datasetDir, "photos.tsv");
        File keywordsFile = new File(datasetDir, "keywords.tsv");

        if (!photosFile.exists() || !keywordsFile.exists()) {
            status = "ERROR: TSV datasets missing at " + datasetDir;
            return;
        }

        User admin = userRepository.findByUsername("admin").orElseThrow();
        Map<String, Long> unsplashToInternalId = new HashMap<>();
        RestTemplate restTemplate = new RestTemplate();

        try (BufferedReader br = new BufferedReader(new FileReader(photosFile))) {
            String line;
            boolean first = true;
            int count = 0;
            while ((line = br.readLine()) != null) {
                if (first) { first = false; continue; }
                String[] cols = line.split("\t");
                if (cols.length < 3) continue;
                
                String photoId = cols[0];
                String url = cols[2] + "?w=1080"; // Resize dynamically to save memory
                status = "IMPORTING_PHOTOS (" + (++count) + " processed)";

                try {
                    byte[] imageBytes = restTemplate.getForObject(url, byte[].class);
                    if (imageBytes != null) {
                        Metadata img = new Metadata("unsplash_" + photoId + ".jpg", imageBytes);
                        img.setUserId(admin.getId());
                        img.setPrivate(false);
                        storageService.processAndSaveImage(img, true);
                        unsplashToInternalId.put(photoId, img.getId());
                        Thread.sleep(4000); // Throttling: Crucial to prevent taskExecutor queue overflow
                    }
                } catch (ErrorHandler.DuplicateImageException e) {
                    log.info("Unsplash image {} already exists.", photoId);
                } catch (Exception e) {
                    log.error("Failed to download {}", url, e);
                }
            }
        } catch (Exception e) {
            status = "ERROR_PHOTOS: " + e.getMessage();
            return;
        }

        status = "IMPORTING_KEYWORDS (Processing Tags)";
        try (BufferedReader br = new BufferedReader(new FileReader(keywordsFile))) {
            String line;
            boolean first = true;
            while ((line = br.readLine()) != null) {
                if (first) { first = false; continue; }
                String[] cols = line.split("\t");
                if (cols.length < 2) continue;
                
                String photoId = cols[0];
                String keyword = cols[1];

                Long internalId = unsplashToInternalId.get(photoId);
                if (internalId != null) {
                    vectorRepository.addKeyword(internalId, keyword);
                }
            }
        } catch (Exception e) {
            status = "ERROR_KEYWORDS: " + e.getMessage();
            return;
        }

        status = "COMPLETED";
    }
}