package pdl.backend;

import com.pgvector.PGvector;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Value;

import jakarta.annotation.PostConstruct;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class ImageDao implements Dao<Image> {

    private final JdbcTemplate jdbcTemplate;

    @Value("${app.image.directory:images}")
    private String imageDirectoryPath;

    public ImageDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void initDatabase() {
        jdbcTemplate.execute("CREATE EXTENSION IF NOT EXISTS vector;");

        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS images (" +
                "id BIGSERIAL PRIMARY KEY, " +
                "filename VARCHAR(255) NOT NULL, " +
                "format VARCHAR(10) NOT NULL, " +
                "width INT NOT NULL DEFAULT 0, " +
                "height INT NOT NULL DEFAULT 0" +
                ");");

        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS image_descriptors (" +
                "image_id BIGINT REFERENCES images(id) ON DELETE CASCADE, " +
                "hog_vector vector(31), " + 
                "hsv_vector vector(256), " +
                "rgb_vector vector(512), " +
                "PRIMARY KEY (image_id)" +
                ");");

        // Use HNSW indexing with Euclidean distance (vector_l2_ops) exactly as specified
        jdbcTemplate.execute("CREATE INDEX IF NOT EXISTS idx_hnsw_hog ON image_descriptors USING hnsw (hog_vector vector_l2_ops);");
        jdbcTemplate.execute("CREATE INDEX IF NOT EXISTS idx_hnsw_hsv ON image_descriptors USING hnsw (hsv_vector vector_l2_ops);");
        jdbcTemplate.execute("CREATE INDEX IF NOT EXISTS idx_hnsw_rgb ON image_descriptors USING hnsw (rgb_vector vector_l2_ops);");

        syncDiskAndDatabase();
    }

    private void syncDiskAndDatabase() {
        File dir = new File(imageDirectoryPath);
        if (!dir.exists() || !dir.isDirectory()) return;

        File[] files = dir.listFiles((d, name) -> name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".png") || name.toLowerCase().endsWith(".jpeg"));
        if (files == null) return;

        List<String> dbFiles = jdbcTemplate.query("SELECT filename FROM images", (rs, rowNum) -> rs.getString("filename"));

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

    @Override
    public void create(Image img) {
        createWithoutFileSave(img);
        Path path = Paths.get(imageDirectoryPath, img.getName());
        try {
            Files.write(path, img.getData());
        } catch (IOException e) {
            e.printStackTrace();
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
            System.err.println("Could not process image " + img.getName());
        }

        if (hogData.length != 31) {
            float[] adjustedHog = new float[31];
            System.arraycopy(hogData, 0, adjustedHog, 0, Math.min(hogData.length, 31));
            hogData = adjustedHog;
        }

        Long id = jdbcTemplate.queryForObject(
            "INSERT INTO images (filename, format, width, height) VALUES (?, ?, ?, ?) RETURNING id",
            Long.class, img.getName(), format, width, height
        );
        img.setId(id);

        jdbcTemplate.update(
            "INSERT INTO image_descriptors (image_id, hog_vector, hsv_vector, rgb_vector) VALUES (?, ?, ?, ?)",
            id, new PGvector(hogData), new PGvector(hsvData), new PGvector(rgbData)
        );
    }

    @Override
    public Optional<Image> retrieve(long id) {
        try {
            String filename = jdbcTemplate.queryForObject("SELECT filename FROM images WHERE id = ?", String.class, id);
            Path path = Paths.get(imageDirectoryPath, filename);
            if (Files.exists(path)) {
                byte[] data = Files.readAllBytes(path);
                Image img = new Image(filename, data);
                img.setId(id);
                return Optional.of(img);
            }
        } catch (Exception e) {
            // Exception indicates not found or not mapped on disk
        }
        return Optional.empty();
    }

    // Solves Test Compilation Error: Overloaded to accept int directly
    public Optional<Image> retrieve(int id) {
        return retrieve((long) id);
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
        return jdbcTemplate.queryForMap("SELECT width, height, format FROM images WHERE id = ?", id);
    }

    public List<Map<String, Object>> findSimilar(long targetId, String type, int limit) {
        String vectorColumn;
        if ("gradient".equalsIgnoreCase(type)) vectorColumn = "hog_vector";
        else if ("saturation".equalsIgnoreCase(type)) vectorColumn = "hsv_vector";
        else if ("rgb".equalsIgnoreCase(type)) vectorColumn = "rgb_vector";
        else vectorColumn = "hog_vector";

        // Advanced Search utilizing the Euclidean spatial distance operand "<->" mapped to 0.0 - 1.0 confidence formula
        String sql = "SELECT image_id as id, filename, " +
                     "1.0 / (1.0 + (d." + vectorColumn + " <-> (SELECT " + vectorColumn + " FROM image_descriptors WHERE image_id = ?))) AS similarity_score " +
                     "FROM image_descriptors d " +
                     "JOIN images i ON d.image_id = i.id " +
                     "WHERE image_id != ? " +
                     "ORDER BY d." + vectorColumn + " <-> (SELECT " + vectorColumn + " FROM image_descriptors WHERE image_id = ?) ASC " +
                     "LIMIT ?";

        return jdbcTemplate.queryForList(sql, targetId, targetId, targetId, limit);
    }
}