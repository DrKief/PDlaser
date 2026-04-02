package pdl.backend.gallery.search;

import java.util.List;
import java.util.Map;
import org.jspecify.annotations.NonNull;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.pgvector.PGvector;

@Repository
public class VectorSimilarityRepoLayer {

    @NonNull private final JdbcTemplate jdbcTemplate;

    public VectorSimilarityRepoLayer(@NonNull JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> findSimilar(long targetId, String type, int limit) {
        String vectorColumn = switch (type.toLowerCase()) {
            case "gradient" -> "hogvector";
            case "saturation" -> "hsvvector";
            case "rgb" -> "rgbvector";
            case "cielab" -> "labvector";
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

        String operator = vectorColumn.equals("labvector") ? "<=>" : "<->";

        String sql =
            "WITH vector_matches AS (" +
            "  SELECT imageid, " + vectorColumn + " " + operator + " ? as distance " +
            "  FROM imagedescriptors WHERE imageid != ? " +
            "  ORDER BY " + vectorColumn + " " + operator + " ? ASC LIMIT ?" +
            ") " +
            "SELECT v.imageid as id, i.filename, (1.0 - (1.0 / (1.0 + v.distance))) AS score " +
            "FROM vector_matches v JOIN images i ON v.imageid = i.id " +
            "ORDER BY v.distance ASC";

        return jdbcTemplate.queryForList(sql, targetVector, targetId, targetVector, limit);
    }
}