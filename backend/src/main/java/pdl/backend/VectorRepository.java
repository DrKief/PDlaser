package pdl.backend;

import com.pgvector.PGvector;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Data Access Object utilizing JdbcTemplate for complex queries bypassing standard ORM mappings.
 * Handles Keyword management, dynamic searching, and complex PGvector proximity searches.
 */
@Repository
public class VectorRepository {

    private static final Logger log = LoggerFactory.getLogger(VectorRepository.class);

    @NonNull
    private final JdbcTemplate jdbcTemplate;

    public VectorRepository(@NonNull JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Normalizes tags (e.g., replaces spaces with underscores, sets to lowercase).
     */
    private String normalizeTag(String tag) {
        if (tag == null) return null;
        return tag.trim().toLowerCase().replaceAll("\\s+", "_");
    }

    // --- Processing Status ---

    public void updateStatus(long id, String status) {
        jdbcTemplate.update("UPDATE images SET extraction_status = ? WHERE id = ?", status, id);
    }

    public String getStatus(long id) {
        try {
            return jdbcTemplate.queryForObject(
                "SELECT extraction_status FROM images WHERE id = ?",
                String.class,
                id
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // --- Metadata & Keywords ---

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
            return false; // Image doesn't exist
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

    // --- Gallery & Display ---

    public List<Map<String, Object>> getPaginatedGallery(Long currentUserId, int limit, int offset) {
        String sql = "SELECT i.id, i.extraction_status, u.username as uploader " +
            "FROM images i LEFT JOIN users u ON i.user_id = u.id " +
            "WHERE i.is_private = false OR i.user_id = ? " +
            "ORDER BY i.id DESC LIMIT ? OFFSET ?";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Map<String, Object> map = new java.util.HashMap<>();
            map.put("id", rs.getLong("id"));
            map.put("uploader", rs.getString("uploader") != null ? rs.getString("uploader") : "System");
            map.put("keywords", getKeywords(rs.getLong("id")));
            map.put("extraction_status", rs.getString("extraction_status"));
            return map;
        }, currentUserId, limit, offset);
    }

    // --- Similarity Search (PGvector) ---

    /**
     * Finds similar images utilizing PGvector nearest-neighbor searches.
     * Calculates similarity distance and returns formatted scores.
     *
     * @param targetId DB ID of the source image.
     * @param type Descriptor type (gradient/hog, saturation/hsv, rgb, cielab).
     * @param limit Max results to return.
     * @return List of matching results with score.
     */
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
        } catch (Exception e) {
            log.error("Failed to retrieve vector for similarity search on ID: " + targetId, e);
            return null;
        }

        // Dynamically assign the correct mathematical operator to utilize the underlying index
        String operator = vectorColumn.equals("labvector") ? "<=>" : "<->";

        String sql =
            "WITH vector_matches AS (" +
            "  SELECT imageid, " +
            vectorColumn +
            " " +
            operator +
            " ? as distance " +
            "  FROM imagedescriptors " +
            "  WHERE imageid != ? " +
            "  ORDER BY " +
            vectorColumn +
            " " +
            operator +
            " ? ASC LIMIT ?" +
            ") " +
            "SELECT v.imageid as id, i.filename, (1.0 - (1.0 / (1.0 + v.distance))) AS score " +
            "FROM vector_matches v " +
            "JOIN images i ON v.imageid = i.id " +
            "ORDER BY v.distance ASC";

        return jdbcTemplate.queryForList(sql, targetVector, targetId, targetVector, limit);
    }

    /**
     * Focused SQL executor for similarity matching given an already extracted PGVector.
     */
    public List<Map<String, Object>> findSimilarByVector(
        PGvector targetVector,
        String vectorColumn,
        int limit
    ) {
        // Dynamically assign the correct mathematical operator for the index
        String operator = vectorColumn.equals("labvector") ? "<=>" : "<->";

        String sql =
            "WITH vector_matches AS (" +
            "  SELECT imageid, " +
            vectorColumn +
            " " +
            operator +
            " ? as distance " +
            "  FROM imagedescriptors " +
            "  ORDER BY " +
            vectorColumn +
            " " +
            operator +
            " ? ASC LIMIT ?" +
            ") " +
            "SELECT v.imageid as id, i.filename, (1.0 - (1.0 / (1.0 + v.distance))) AS score " +
            "FROM vector_matches v " +
            "JOIN images i ON v.imageid = i.id " +
            "ORDER BY v.distance ASC";

        return jdbcTemplate.queryForList(sql, targetVector, targetVector, limit);
    }

    // --- Search ---

    /**
     * Debounced Autocomplete Query
     */
    public List<String> searchKeywords(String query, Long currentUserId) {
        String normalized = normalizeTag(query);
        return jdbcTemplate.queryForList(
            "SELECT DISTINCT k.keyword FROM imagekeywords k " +
            "JOIN images i ON k.imageid = i.id " +
            "WHERE (i.is_private = false OR i.user_id = ?) AND k.keyword LIKE ? " +
            "ORDER BY k.keyword ASC LIMIT 8",
            String.class, currentUserId, "%" + normalized + "%"
        );
    }

    /**
     * Refined Tag-Only Search
     */
    public List<Long> searchByAttributes(List<String> keywords, Long currentUserId) {
        StringBuilder sql = new StringBuilder("SELECT DISTINCT i.id FROM images i ");
        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("currentUserId", currentUserId);
        
        if (keywords != null && !keywords.isEmpty()) {
            sql.append("JOIN imagekeywords k ON i.id = k.imageid ");
            sql.append("WHERE (i.is_private = false OR i.user_id = :currentUserId) ");
            List<String> normalizedKeywords = keywords.stream().map(this::normalizeTag).collect(Collectors.toList());
            sql.append("AND k.keyword IN (:keywords) ");
            params.addValue("keywords", normalizedKeywords);
        } else {
            sql.append("WHERE (i.is_private = false OR i.user_id = :currentUserId) ");
        }

        return new NamedParameterJdbcTemplate(jdbcTemplate).queryForList(sql.toString(), params, Long.class);
    }

    /**
     * Complex dynamic search builder utilizing NamedParameterJdbcTemplate.
     * Combines optional filters: name, format, resolution size, and keyword matches.
     */
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