package pdl.backend;

import com.pgvector.PGvector;
import jakarta.annotation.PostConstruct;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.HexFormat;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ImageDao implements Dao<Image> {

  private static final Logger log = LoggerFactory.getLogger(ImageDao.class);

  @NonNull
  private final JdbcTemplate jdbcTemplate;

  @Value("${app.image.directory:images}")
  private String imageDirectoryPath;

  public ImageDao(@NonNull JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @PostConstruct
  public void initDatabase() {
    jdbcTemplate.execute("CREATE EXTENSION IF NOT EXISTS vector");
    jdbcTemplate.execute(
      "CREATE TABLE IF NOT EXISTS images (" +
        "id BIGSERIAL PRIMARY KEY, " +
        "filename VARCHAR(255) NOT NULL, " +
        "format VARCHAR(10) NOT NULL, " +
        "width INT NOT NULL DEFAULT 0, " +
        "height INT NOT NULL DEFAULT 0, " +
        "hash VARCHAR(64) UNIQUE)"
    );

    jdbcTemplate.execute(
      "CREATE TABLE IF NOT EXISTS imagedescriptors (" +
        "imageid BIGINT REFERENCES images(id) ON DELETE CASCADE, " +
        "hogvector vector(31), " +
        "hsvvector vector(256), " +
        "rgbvector vector(512), " +
        "PRIMARY KEY (imageid))"
    );

    jdbcTemplate.execute(
      "CREATE TABLE IF NOT EXISTS imagekeywords (" +
        "imageid BIGINT REFERENCES images(id) ON DELETE CASCADE, " +
        "keyword VARCHAR(255) NOT NULL, " +
        "PRIMARY KEY (imageid, keyword))"
    );

    jdbcTemplate.execute(
      "CREATE INDEX IF NOT EXISTS idx_hnsw_hog ON imagedescriptors USING hnsw (hogvector vector_l2_ops) WITH (m=16, ef_construction=64)"
    );
    jdbcTemplate.execute(
      "CREATE INDEX IF NOT EXISTS idx_hnsw_hsv ON imagedescriptors USING hnsw (hsvvector vector_l2_ops) WITH (m=16, ef_construction=64)"
    );
    jdbcTemplate.execute(
      "CREATE INDEX IF NOT EXISTS idx_hnsw_rgb ON imagedescriptors USING hnsw (rgbvector vector_l2_ops) WITH (m=16, ef_construction=64)"
    );

    syncDiskAndDatabase();
  }

  private void syncDiskAndDatabase() {
    File dir = new File(imageDirectoryPath);
    if (!dir.exists() || !dir.isDirectory()) return;

    File[] files = dir.listFiles(
      (d, name) ->
        name.toLowerCase().endsWith(".jpg") ||
        name.toLowerCase().endsWith(".png") ||
        name.toLowerCase().endsWith(".jpeg")
    );

    if (files == null) return;

    List<String> dbFiles = jdbcTemplate.query("SELECT filename FROM images", (rs, rowNum) ->
      rs.getString("filename")
    );

    for (File file : files) {
      if (!dbFiles.contains(file.getName())) {
        try {
          byte[] data = Files.readAllBytes(file.toPath());
          Image img = new Image(file.getName(), data);
          createWithoutFileSave(img);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
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

  // Normalization utility for tags
  private String normalizeTag(String tag) {
    if (tag == null) return null;
    return tag.trim().toLowerCase().replaceAll("\\s+", "_"); // " nature " -> "nature", "cool dog" -> "cool_dog"
  }

  @Override
  @Transactional
  public void create(Image img) {
    String hash = calculateSHA256(img.getData());

    // Check if image already exists in DB
    List<Long> existingIds = jdbcTemplate.queryForList(
      "SELECT id FROM images WHERE hash = ?",
      Long.class,
      hash
    );
    if (!existingIds.isEmpty()) {
      img.setId(existingIds.get(0));
      img.setHash(hash);
      return; // Exit early, do not save to DB or Disk
    }

    img.setHash(hash);
    createWithoutFileSave(img);

    Path path = Paths.get(imageDirectoryPath, img.getName());
    try {
      Files.write(path, img.getData());
    } catch (IOException e) {
      throw new RuntimeException("Failed to write image file to disk, rolling back DB insert", e);
    }
  }

  private void createWithoutFileSave(Image img) {
    String format = img.getName().substring(img.getName().lastIndexOf('.') + 1);
    int width = 0;
    int height = 0;

    float[] hogData = new float[31];
    float[] hsvData = new float[256];
    float[] rgbData = new float[512];

    try {
      BufferedImage bimg = ImageIO.read(new ByteArrayInputStream(img.getData()));
      if (bimg != null) {
        width = bimg.getWidth();
        height = bimg.getHeight();
        hogData = ImageProcessing.extractGlobalHog(bimg);
        hsvData = ImageProcessing.extractHsvHistogram(bimg);
        rgbData = ImageProcessing.extractRgbHistogram(bimg);
      }
    } catch (Exception e) {
      log.error("Could not process image data for: " + img.getName(), e);
    }

    if (hogData.length != 31) {
      float[] adjustedHog = new float[31];
      System.arraycopy(hogData, 0, adjustedHog, 0, Math.min(hogData.length, 31));
      hogData = adjustedHog;
    }

    Long id = jdbcTemplate.queryForObject(
      "INSERT INTO images (filename, format, width, height, hash) VALUES (?, ?, ?, ?, ?) RETURNING id",
      Long.class,
      img.getName(),
      format,
      width,
      height,
      img.getHash()
    );

    img.setId(id);

    jdbcTemplate.update(
      "INSERT INTO imagedescriptors (imageid, hogvector, hsvvector, rgbvector) VALUES (?, ?, ?, ?)",
      id,
      new PGvector(hogData),
      new PGvector(hsvData),
      new PGvector(rgbData)
    );
  }

  @Override
  public Optional<Image> retrieve(final long id) {
    try {
      String filename = jdbcTemplate.queryForObject(
        "SELECT filename FROM images WHERE id = ?",
        String.class,
        id
      );
      Path path = Paths.get(imageDirectoryPath, filename);
      if (Files.exists(path)) {
        byte[] data = Files.readAllBytes(path);
        Image img = new Image(filename, data);
        img.setId(id);
        return Optional.of(img);
      }
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    } catch (Exception e) {
      log.error("Unexpected error retrieving image ID: " + id, e);
    }
    return Optional.empty();
  }

  @Override
  public List<Image> retrieveAll() {
    return jdbcTemplate.query("SELECT id, filename FROM images", (rs, rowNum) -> {
      Image img = new Image(rs.getString("filename"), new byte[0]);
      img.setId(rs.getLong("id"));
      return img;
    });
  }

  @Override
  public void update(Image image, String[] params) {}

  @Override
  public void delete(Image image) {
    jdbcTemplate.update("DELETE FROM images WHERE id = ?", image.getId());
    Path path = Paths.get(imageDirectoryPath, image.getName());
    try {
      Files.deleteIfExists(path);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Map<String, Object> getImageMetadata(long id) {
    Map<String, Object> meta = jdbcTemplate.queryForMap(
      "SELECT filename as Name, format, width, height FROM images WHERE id = ?",
      id
    );
    List<String> keywords = jdbcTemplate.queryForList(
      "SELECT keyword FROM imagekeywords WHERE imageid = ?",
      String.class,
      id
    );
    meta.put("Keywords", keywords);
    return meta;
  }

  public boolean addKeyword(long id, String keyword) {
    String normalizedTag = normalizeTag(keyword);
    if (normalizedTag == null || normalizedTag.isEmpty()) return false;

    try {
      jdbcTemplate.queryForObject("SELECT id FROM images WHERE id = ?", Long.class, id);
      jdbcTemplate.update(
        "INSERT INTO imagekeywords (imageid, keyword) VALUES (?, ?) ON CONFLICT DO NOTHING",
        id,
        normalizedTag
      );
      return true;
    } catch (EmptyResultDataAccessException e) {
      return false;
    } catch (Exception e) {
      log.error("Failed to add keyword '" + keyword + "' to image ID: " + id, e);
      return false;
    }
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

  public List<String> getAllKeywords() {
    return jdbcTemplate.queryForList(
      "SELECT DISTINCT keyword FROM imagekeywords ORDER BY keyword ASC",
      String.class
    );
  }

  public List<Map<String, Object>> findSimilar(long targetId, String type, int limit) {
    String vectorColumn;
    if ("gradient".equalsIgnoreCase(type)) vectorColumn = "hogvector";
    else if ("saturation".equalsIgnoreCase(type)) vectorColumn = "hsvvector";
    else if ("rgb".equalsIgnoreCase(type)) vectorColumn = "rgbvector";
    else vectorColumn = "hogvector";

    PGvector targetVector;
    try {
      targetVector = jdbcTemplate.queryForObject(
        "SELECT " + vectorColumn + " FROM imagedescriptors WHERE imageid = ?",
        (rs, rowNum) -> new PGvector(rs.getString(1)),
        targetId
      );
    } catch (EmptyResultDataAccessException e) {
      return null;
    } catch (Exception e) {
      log.error("Failed to retrieve vector for similarity search on ID: " + targetId, e);
      return null;
    }

    String sql =
      "SELECT d.imageid as id, i.filename, (1.0 - (1.0 / (1.0 + (d." +
      vectorColumn +
      " <-> ?)))) AS score " +
      "FROM imagedescriptors d JOIN images i ON d.imageid = i.id " +
      "WHERE imageid != ? ORDER BY d." +
      vectorColumn +
      " <-> ? ASC LIMIT ?";

    return jdbcTemplate.queryForList(sql, targetVector, targetId, targetVector, limit);
  }

  public List<Map<String, Object>> findSimilarFromUpload(byte[] imageData, String type, int limit) {
    try {
      BufferedImage bimg = ImageIO.read(new ByteArrayInputStream(imageData));
      if (bimg == null) return List.of();

      String vectorColumn;
      PGvector targetVector;

      if ("saturation".equalsIgnoreCase(type)) {
        vectorColumn = "hsvvector";
        targetVector = new PGvector(ImageProcessing.extractHsvHistogram(bimg));
      } else if ("rgb".equalsIgnoreCase(type)) {
        vectorColumn = "rgbvector";
        targetVector = new PGvector(ImageProcessing.extractRgbHistogram(bimg));
      } else {
        vectorColumn = "hogvector";
        float[] hogData = ImageProcessing.extractGlobalHog(bimg);
        if (hogData.length != 31) {
          float[] adjustedHog = new float[31];
          System.arraycopy(hogData, 0, adjustedHog, 0, Math.min(hogData.length, 31));
          hogData = adjustedHog;
        }
        targetVector = new PGvector(hogData);
      }

      String sql =
        "SELECT d.imageid as id, i.filename, (1.0 - (1.0 / (1.0 + (d." +
        vectorColumn +
        " <-> ?)))) AS score " +
        "FROM imagedescriptors d JOIN images i ON d.imageid = i.id " +
        "ORDER BY d." +
        vectorColumn +
        " <-> ? ASC LIMIT ?";

      return jdbcTemplate.queryForList(sql, targetVector, targetVector, limit);
    } catch (Exception e) {
      e.printStackTrace();
      return List.of();
    }
  }

  public List<Long> searchByAttributes(
    String name,
    String format,
    String size,
    List<String> keywords
  ) {
    StringBuilder sql = new StringBuilder("SELECT DISTINCT i.id FROM images i ");
    MapSqlParameterSource params = new MapSqlParameterSource();

    if (keywords != null && !keywords.isEmpty()) {
      sql.append("LEFT JOIN imagekeywords k ON i.id = k.imageid ");
    }

    sql.append("WHERE 1=1 ");

    if (name != null && !name.isEmpty()) {
      sql.append("AND i.filename LIKE :name ");
      params.addValue("name", "%" + name + "%");
    }
    if (format != null && !format.isEmpty()) {
      sql.append("AND i.format = :format ");
      params.addValue("format", format);
    }
    if (size != null && !size.isEmpty()) {
      String[] parts = size.split(",");
      if (parts.length == 2) {
        sql.append("AND i.width = :width AND i.height = :height ");
        params.addValue("width", Integer.parseInt(parts[0]));
        params.addValue("height", Integer.parseInt(parts[1]));
      }
    }
    if (keywords != null && !keywords.isEmpty()) {
      // Normalize all keywords before searching
      List<String> normalizedKeywords = keywords
        .stream()
        .map(this::normalizeTag)
        .collect(Collectors.toList());
      sql.append("AND k.keyword IN (:keywords) ");
      params.addValue("keywords", normalizedKeywords);
    }

    NamedParameterJdbcTemplate namedTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    return namedTemplate.queryForList(
      java.util.Objects.requireNonNull(sql.toString()),
      params,
      Long.class
    );
  }
}
