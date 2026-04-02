package pdl.backend.ingestion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import pdl.backend.auth.UserAccountLayer;
import pdl.backend.auth.UserAccountRepoLayer;
import pdl.backend.gallery.core.ImageRecordLayer;
import pdl.backend.gallery.core.ImageStorageLayer;
import pdl.backend.gallery.tags.ImageQueryRepoLayer;

@Service
public class UnsplashImporterLayer {
    private static final Logger log = LoggerFactory.getLogger(UnsplashImporterLayer.class);
    private final ImageStorageLayer storageService;
    private final ImageQueryRepoLayer queryRepoLayer;
    private final UserAccountRepoLayer userRepository;
    
    @Value("${app.unsplash.dataset-dir}")
    private String datasetDir;
    private String status = "IDLE";
    
    public UnsplashImporterLayer(ImageStorageLayer storageService, ImageQueryRepoLayer queryRepoLayer, UserAccountRepoLayer userRepository) {
        this.storageService = storageService;
        this.queryRepoLayer = queryRepoLayer;
        this.userRepository = userRepository;
    }

    public String getStatus() { return status; }

    @Async("taskExecutor")
    public void startImport(int limit, int offset) {
        File photosFile = new File(datasetDir, "photos.tsv");
        File keywordsFile = new File(datasetDir, "keywords.tsv");

        if (!photosFile.exists() || !keywordsFile.exists()) {
            status = "ERROR: TSV datasets missing at " + datasetDir;
            return;
        }

        UserAccountLayer admin = userRepository.findByUsername("admin").orElseThrow();
        Map<String, Long> unsplashToInternalId = new HashMap<>();
        RestTemplate restTemplate = new RestTemplate();

        status = "IMPORTING_PHOTOS (Processing " + limit + " images, offset " + offset + ")";
        try (BufferedReader br = new BufferedReader(new FileReader(photosFile))) {
            String line;
            boolean first = true;
            int currentLine = 0;
            int processed = 0;

            while ((line = br.readLine()) != null) {
                if (first) { first = false; continue; }
                if (++currentLine <= offset) continue;
                if (processed >= limit) break;

                String[] cols = line.split("\t");
                if (cols.length < 3) continue;
                
                String photoId = cols[0];
                String url = cols[2] + "?w=1080"; 
                status = "IMPORTING_PHOTOS (Progress: " + (processed + 1) + " / " + limit + ")";

                try {
                    byte[] imageBytes = restTemplate.getForObject(url, byte[].class);
                    if (imageBytes != null) {
                        ImageRecordLayer img = new ImageRecordLayer("unsplash_" + photoId + ".jpg", imageBytes);
                        img.setUserId(admin.getId());
                        img.setPrivate(false);
                        storageService.processAndSaveImage(img, true);
                        unsplashToInternalId.put(photoId, img.getId());
                        Thread.sleep(2500); 
                    }
                } catch (ResponseStatusException e) {
                    log.info("Unsplash image {} already exists.", photoId);
                } catch (Exception e) {
                    log.error("Failed to download {}", url, e);
                }
                processed++;
            }
        } catch (Exception e) {
            status = "ERROR_PHOTOS: " + e.getMessage();
            return;
        }

        status = "IMPORTING_KEYWORDS (Applying tags)";
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
                    queryRepoLayer.addKeyword(internalId, keyword);
                }
            }
        } catch (Exception e) {
            status = "ERROR_KEYWORDS: " + e.getMessage();
            return;
        }

        status = "COMPLETED";
    }
}