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
