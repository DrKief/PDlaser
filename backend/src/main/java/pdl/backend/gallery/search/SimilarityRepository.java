package pdl.backend.gallery.search;

import com.pgvector.PGvector;
import java.util.ArrayList;
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
        "  ORDER BY (? * (d.hogvector <=> ?) + ? * (d.labvector <=> ?) + ? * (d.hsvvector <=> ?)) ASC LIMIT ?" +
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
        WEIGHT_HOG,
        vectors[0],
        WEIGHT_LAB,
        vectors[2],
        WEIGHT_HSV,
        vectors[1],
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
      "  ORDER BY " +
      vectorColumn +
      " <=> ? LIMIT ?" +
      ") " +
      "SELECT v.imageid as id, i.filename, (1.0 - (1.0 / (1.0 + v.distance))) AS score " +
      "FROM vector_matches v JOIN images i ON v.imageid = i.id " +
      "ORDER BY v.distance ASC";

    return jdbcTemplate.queryForList(sql, targetVector, targetId, targetVector, limit);
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
      "  ORDER BY " +
      vectorColumn +
      " <=> ? LIMIT ?" +
      ") " +
      "SELECT v.imageid as id, i.filename, (1.0 - (1.0 / (1.0 + v.distance))) AS score " +
      "FROM vector_matches v JOIN images i ON v.imageid = i.id " +
      "ORDER BY v.distance ASC";

    return jdbcTemplate.queryForList(sql, targetVector, targetVector, limit);
  }

  public List<float[]> getVectorsForIds(List<Long> ids, String type) {
    String vectorColumn = switch (type.toLowerCase()) {
      case "gradient" -> "hogvector";
      case "saturation" -> "hsvvector";
      case "rgb" -> "rgbvector";
      case "cielab" -> "labvector";
      case "semantic" -> "semanticvector";
      default -> "hogvector";
    };

    String placeholders = String.join(",", java.util.Collections.nCopies(ids.size(), "?"));
    String sql = String.format("SELECT imageid, %s FROM imagedescriptors WHERE imageid IN (%s)", vectorColumn, placeholders);

    List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, ids.toArray());
    
    List<float[]> vectors = new ArrayList<>();
    for (Long id : ids) {
      float[] vector = null;
      for (Map<String, Object> row : rows) {
        if (row.get("imageid").equals(id)) {
          PGvector pgVector = (PGvector) row.get(vectorColumn);
          if (pgVector != null) {
            vector = pgVector.toArray();
          }
          break;
        }
      }
      vectors.add(vector);
    }
    
    return vectors;
  }

  public List<Map<String, Object>> findSimilarByVectorExcluding(
    PGvector targetVector,
    String type,
    int limit,
    List<Long> excludeIds
  ) {
    String vectorColumn = switch (type.toLowerCase()) {
      case "gradient" -> "hogvector";
      case "saturation" -> "hsvvector";
      case "rgb" -> "rgbvector";
      case "cielab" -> "labvector";
      case "semantic" -> "semanticvector";
      default -> "hogvector";
    };

    String placeholders = String.join(",", java.util.Collections.nCopies(excludeIds.size(), "?"));
    String excludeClause = String.format("AND d.imageid NOT IN (%s)", placeholders);

    String sql =
      "WITH vector_matches AS (" +
      "  SELECT d.imageid, " +
      vectorColumn +
      " <=> ? as distance " +
      "  FROM imagedescriptors d " +
      "  JOIN images i ON d.imageid = i.id " +
      "  WHERE i.extraction_status = 'COMPLETED' " +
      excludeClause +
      "  ORDER BY " +
      vectorColumn +
      " <=> ? LIMIT ?" +
      ") " +
      "SELECT v.imageid as id, i.filename, (1.0 - (1.0 / (1.0 + v.distance))) AS score " +
      "FROM vector_matches v JOIN images i ON v.imageid = i.id " +
      "ORDER BY v.distance ASC";

    List<Object> params = new ArrayList<>();
    params.add(targetVector);
    params.addAll(excludeIds);
    params.add(targetVector);
    params.add(limit);

    return jdbcTemplate.queryForList(sql, params.toArray());
  }
}
