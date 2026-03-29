package pdl.backend;

import com.pgvector.PGvector;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public class ImageDao {

  private static final Logger log = LoggerFactory.getLogger(ImageDao.class);

  @NonNull
  private final JdbcTemplate jdbcTemplate;

  public ImageDao(@NonNull JdbcTemplate jdbcTemplate) {
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
      return jdbcTemplate.queryForObject("SELECT extraction_status FROM images WHERE id = ?", String.class, id);
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  public Map<String, Object> getImageMetadata(long id) {
    Map<String, Object> meta = jdbcTemplate.queryForMap(
      "SELECT filename as Name, format, width, height, extraction_status FROM images WHERE id = ?",
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

  public List<String> getKeywords(long id) {
    return jdbcTemplate.queryForList(
      "SELECT keyword FROM imagekeywords WHERE imageid = ?",
      String.class,
      id
    );
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
      "WITH vector_matches AS (" +
      "  SELECT imageid, " + vectorColumn + " <-> ? as distance " +
      "  FROM imagedescriptors " +
      "  WHERE imageid != ? " +
      "  ORDER BY " + vectorColumn + " <-> ? ASC LIMIT ?" +
      ") " +
      "SELECT v.imageid as id, i.filename, (1.0 - (1.0 / (1.0 + v.distance))) AS score " +
      "FROM vector_matches v " +
      "JOIN images i ON v.imageid = i.id " +
      "ORDER BY v.distance ASC";

    return jdbcTemplate.queryForList(sql, targetVector, targetId, targetVector, limit);
  }

  public List<Map<String, Object>> findSimilarFromUpload(byte[] imageData, String type, int limit) {
    try {
      BufferedImage bimg = ImageIO.read(new ByteArrayInputStream(imageData));
      if (bimg == null) return List.of();

      BufferedImage resizedImage = ImageProcessing.resizeImageLanczos3(bimg, 256, 256);

      String vectorColumn;
      PGvector targetVector;

      if ("saturation".equalsIgnoreCase(type)) {
        vectorColumn = "hsvvector";
        targetVector = new PGvector(ImageProcessing.extractHsvHistogram(resizedImage));
      } else if ("rgb".equalsIgnoreCase(type)) {
        vectorColumn = "rgbvector";
        targetVector = new PGvector(ImageProcessing.extractRgbHistogram(resizedImage));
      } else {
        vectorColumn = "hogvector";
        float[] hogData = ImageProcessing.extractGlobalHog(resizedImage);
        if (hogData.length != 31) {
          float[] adjustedHog = new float[31];
          System.arraycopy(hogData, 0, adjustedHog, 0, Math.min(hogData.length, 31));
          hogData = adjustedHog;
        }
        targetVector = new PGvector(hogData);
      }

      String sql =
        "WITH vector_matches AS (" +
        "  SELECT imageid, " + vectorColumn + " <-> ? as distance " +
        "  FROM imagedescriptors " +
        "  ORDER BY " + vectorColumn + " <-> ? ASC LIMIT ?" +
        ") " +
        "SELECT v.imageid as id, i.filename, (1.0 - (1.0 / (1.0 + v.distance))) AS score " +
        "FROM vector_matches v " +
        "JOIN images i ON v.imageid = i.id " +
        "ORDER BY v.distance ASC";

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