package pdl.backend;

import jakarta.annotation.PostConstruct;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/images")
public class ImageController {

  private static final Logger log = LoggerFactory.getLogger(ImageController.class);

  @Value("${app.image.directory:images}")
  private String imageDirectoryPath;

  private final ImageRepository imageRepository;
  private final ImageDao imageDao;
  private final ImageService imageService;
  private final ImageStatusNotifier statusNotifier;

  public ImageController(
    ImageRepository imageRepository,
    ImageDao imageDao,
    ImageService imageService,
    ImageStatusNotifier statusNotifier
  ) {
    this.imageRepository = imageRepository;
    this.imageDao = imageDao;
    this.imageService = imageService;
    this.statusNotifier = statusNotifier;
  }

  @PostConstruct
  public void verifyStartupState() {
    Path path = Paths.get(imageDirectoryPath);
    if (!Files.exists(path) || !Files.isDirectory(path)) {
      log.error("FATAL: Required 'images' directory not found at {}", path.toAbsolutePath());
      throw new IllegalStateException(
        "Besoin 1: Le dossier images n'existe pas ou n'est pas un dossier valide."
      );
    }

    if (!Files.isWritable(path)) {
      log.error(
        "FATAL: Required 'images' directory is not writable. Path: {}",
        path.toAbsolutePath()
      );
      throw new IllegalStateException("directory not writable");
    }
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Map<String, Object>>> getImages() {
    Iterable<Image> images = imageRepository.findAll();
    List<Map<String, Object>> response = new java.util.ArrayList<>();
    for (Image img : images) {
      Map<String, Object> item = new HashMap<>();
      item.put("id", img.getId());
      item.put("name", img.getName());
      item.put("keywords", imageDao.getKeywords(img.getId()));
      response.add(item);
    }
    return ResponseEntity.ok(response);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<?> getImage(@PathVariable("id") long id) {
    Optional<Image> image = imageService.getImageWithData(id);
    if (image.isEmpty() || image.get().getData() == null) {
      throw new GlobalExceptionHandler.RecordNotFoundException("Image not found");
    }

    return ResponseEntity.ok()
      .header(HttpHeaders.CONTENT_TYPE, "image/" + image.get().getFormat())
      .body(image.get().getData());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteImage(@PathVariable("id") long id) {
    boolean deleted = imageService.deleteImage(id);
    if (!deleted) {
      throw new GlobalExceptionHandler.RecordNotFoundException("Image not found");
    }
    return ResponseEntity.noContent().build();
  }

  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<?> addImage(
    @RequestParam("file") MultipartFile file,
    @RequestParam(value = "keywords", required = false) List<String> keywords
  ) throws Exception {
    if (file.isEmpty()) {
      throw new GlobalExceptionHandler.BadRequestException("File is empty");
    }

    String contentType = file.getContentType();
    if (contentType == null || !contentType.startsWith("image/")) {
      throw new GlobalExceptionHandler.UnsupportedFileException("Unsupported Media Type");
    }

    Image image = new Image(file.getOriginalFilename(), file.getBytes());
    imageService.processAndSaveImage(image, true);
    long id = image.getId();

    if (keywords != null && !keywords.isEmpty()) {
      for (String k : keywords) {
        String[] splits = k.split(",");
        for (String tag : splits) {
          imageDao.addKeyword(id, tag.trim());
        }
      }
    }

    return ResponseEntity.status(HttpStatus.ACCEPTED)
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .body(
        Map.of(
          "message",
          "Image accepted for background processing.",
          "id",
          id,
          "status_url",
          "/images/" + id + "/status"
        )
      );
  }

  @GetMapping(value = "/{id}/status", produces = MediaType.APPLICATION_JSON_VALUE)
  public Object getImageStatus(@PathVariable("id") long id) {
    String status = imageDao.getStatus(id);
    if (status == null) {
      throw new GlobalExceptionHandler.RecordNotFoundException("Image not found");
    }

    if ("COMPLETED".equals(status) || "FAILED".equals(status)) {
      return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .body(Map.of("id", id, "extraction_status", status));
    }

    return statusNotifier.waitFor(id, 10000L);
  }

  @GetMapping("/{id}/metadata")
  public ResponseEntity<?> getMetadata(@PathVariable("id") long id) {
    Map<String, Object> rawMeta = imageDao.getImageMetadata(id);

    Map<String, Object> response = new HashMap<>();
    response.put("Name", rawMeta.get("Name"));
    response.put("Type", "image/" + rawMeta.get("format"));
    response.put("Size", rawMeta.get("width") + "*" + rawMeta.get("height"));
    response.put("Keywords", rawMeta.get("Keywords"));
    response.put("Extraction_Status", rawMeta.get("extraction_status"));

    return ResponseEntity.ok()
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .body(response);
  }

  @GetMapping("/{id}/similar")
  public ResponseEntity<?> getSimilarImages(
    @PathVariable("id") long id,
    @RequestParam(value = "number", defaultValue = "10") int number,
    @RequestParam(value = "descriptor", defaultValue = "gradient") String descriptor
  ) {
    List<String> validDescriptors = List.of("gradient", "saturation", "rgb");
    if (!validDescriptors.contains(descriptor.toLowerCase())) {
      throw new GlobalExceptionHandler.BadRequestException("Bad Request. Invalid descriptor.");
    }

    List<Map<String, Object>> results = imageDao.findSimilar(id, descriptor, number);

    if (results == null) {
      throw new GlobalExceptionHandler.RecordNotFoundException("Image not found");
    }

    return ResponseEntity.ok()
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .body(results);
  }

  @PutMapping("/{id}/keywords")
  public ResponseEntity<?> addKeyword(
    @PathVariable("id") long id,
    @RequestParam("tag") String tag
  ) {
    boolean success = imageDao.addKeyword(id, tag);
    if (!success) {
      throw new GlobalExceptionHandler.RecordNotFoundException("Image not found");
    }
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}/keywords")
  public ResponseEntity<?> deleteKeyword(
    @PathVariable("id") long id,
    @RequestParam("tag") String tag
  ) {
    imageDao.getImageMetadata(id);

    if (!imageDao.hasKeyword(id, tag)) {
      throw new GlobalExceptionHandler.BadRequestException("Tag not associated with this image");
    }

    imageDao.deleteKeyword(id, tag);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/keywords")
  public ResponseEntity<?> getAllKeywords() {
    return ResponseEntity.ok()
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .body(imageDao.getAllKeywords());
  }

  @GetMapping("/search")
  public ResponseEntity<?> searchImagesByAttributes(
    @RequestParam(required = false) String name,
    @RequestParam(required = false) String format,
    @RequestParam(required = false) String size,
    @RequestParam(required = false) List<String> keywords
  ) {
    List<Long> ids = imageDao.searchByAttributes(name, format, size, keywords);
    if (ids.isEmpty()) {
      throw new GlobalExceptionHandler.RecordNotFoundException("Aucune image existante trouvée.");
    }

    return ResponseEntity.ok()
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .body(ids);
  }
}
