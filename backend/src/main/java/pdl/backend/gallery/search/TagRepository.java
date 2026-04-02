package pdl.backend.gallery.search;

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

@Repository
public class TagRepository {

    private static final Logger log = LoggerFactory.getLogger(TagRepository.class);

    @NonNull
    private final JdbcTemplate jdbcTemplate;

    public TagRepository(@NonNull JdbcTemplate jdbcTemplate) {
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
            "SELECT filename as Name, format, width, height, extraction_status FROM images WHERE id = ?", id
        );
        meta.put("Keywords", getKeywords(id));
        return meta;
    }

    public List<String> getKeywords(long id) {
        return jdbcTemplate.queryForList("SELECT keyword FROM imagekeywords WHERE imageid = ?", String.class, id);
    }

    public boolean addKeyword(long id, String keyword) {
        String normalizedTag = normalizeTag(keyword);
        if (normalizedTag == null || normalizedTag.isEmpty()) return false;
        try {
            jdbcTemplate.queryForObject("SELECT id FROM images WHERE id = ?", Long.class, id);
            jdbcTemplate.update("INSERT INTO imagekeywords (imageid, keyword) VALUES (?, ?) ON CONFLICT DO NOTHING", id, normalizedTag);
            return true;
        } catch (Exception e) {
            log.error("Failed to add keyword", e);
            return false;
        }
    }

    public boolean hasKeyword(long id, String keyword) {
        Integer count = jdbcTemplate.queryForObject("SELECT count(*) FROM imagekeywords WHERE imageid = ? AND keyword = ?", Integer.class, id, normalizeTag(keyword));
        return count != null && count > 0;
    }

    public void deleteKeyword(long id, String keyword) {
        jdbcTemplate.update("DELETE FROM imagekeywords WHERE imageid = ? AND keyword = ?", id, normalizeTag(keyword));
    }

    public List<String> getAllKeywords(Long currentUserId) {
        return jdbcTemplate.queryForList(
            "SELECT DISTINCT k.keyword FROM imagekeywords k " +
            "JOIN images i ON k.imageid = i.id " +
            "WHERE i.is_private = false OR i.user_id = ? " +
            "ORDER BY k.keyword ASC", 
            String.class, currentUserId
        );
    }

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

    public List<Map<String, Object>> searchGalleryByTags(List<String> keywords, Long currentUserId, int limit, int offset) {
        StringBuilder sql = new StringBuilder(
            "SELECT i.id, i.extraction_status, u.username as uploader " +
            "FROM images i LEFT JOIN users u ON i.user_id = u.id " +
            "WHERE (i.is_private = false OR i.user_id = :currentUserId) "
        );
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("currentUserId", currentUserId);

        if (keywords != null && !keywords.isEmpty()) {
            sql.append("AND (SELECT count(DISTINCT keyword) FROM imagekeywords WHERE imageid = i.id AND keyword IN (:keywords)) = :keywordCount ");
            List<String> normalizedKeywords = keywords.stream().map(this::normalizeTag).collect(Collectors.toList());
            params.addValue("keywords", normalizedKeywords);
            params.addValue("keywordCount", normalizedKeywords.size());
        }

        sql.append("ORDER BY i.id DESC LIMIT :limit OFFSET :offset");
        params.addValue("limit", limit);
        params.addValue("offset", offset);

        return new NamedParameterJdbcTemplate(jdbcTemplate).query(sql.toString(), params, (rs, rowNum) -> {
            Map<String, Object> map = new java.util.HashMap<>();
            map.put("id", rs.getLong("id"));
            map.put("uploader", rs.getString("uploader") != null ? rs.getString("uploader") : "System");
            map.put("keywords", getKeywords(rs.getLong("id")));
            map.put("extraction_status", rs.getString("extraction_status"));
            return map;
        });
    }

    public long getGalleryTotalCount(Long currentUserId) {
        String sql = "SELECT COUNT(*) FROM images i WHERE i.is_private = false OR i.user_id = ?";
        Long count = jdbcTemplate.queryForObject(sql, Long.class, currentUserId);
        return count != null ? count : 0L;
    }

    public long getSearchGalleryByTagsTotalCount(List<String> keywords, Long currentUserId) {
        if (keywords == null || keywords.isEmpty()) {
            return getGalleryTotalCount(currentUserId);
        }

        StringBuilder sql = new StringBuilder(
            "SELECT COUNT(*) FROM images i WHERE (i.is_private = false OR i.user_id = :currentUserId) "
        );
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("currentUserId", currentUserId);

        sql.append("AND (SELECT count(DISTINCT keyword) FROM imagekeywords WHERE imageid = i.id AND keyword IN (:keywords)) = :keywordCount ");
        List<String> normalizedKeywords = keywords.stream().map(this::normalizeTag).collect(Collectors.toList());
        params.addValue("keywords", normalizedKeywords);
        params.addValue("keywordCount", normalizedKeywords.size());

        Long count = new NamedParameterJdbcTemplate(jdbcTemplate).queryForObject(sql.toString(), params, Long.class);
        return count != null ? count : 0L;
    }
}