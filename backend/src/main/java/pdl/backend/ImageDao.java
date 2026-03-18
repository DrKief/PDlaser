package pdl.backend;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.sql.PreparedStatement;
import java.sql.Statement;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class ImageDao implements Dao<Image>, InitializingBean {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String IMAGE_DIR = "src/main/resources/images";

    @Override
    public void afterPropertiesSet() throws Exception {
        File directory = new File(IMAGE_DIR);
        
        if (!directory.exists()) {
            throw new RuntimeException("Le dossier images n'existe pas : " + directory.getAbsolutePath());
        }

        try {
            jdbcTemplate.execute("CREATE EXTENSION IF NOT EXISTS vector");
        } catch (Exception e) {
            System.out.println("Notice: Could not create vector extension (likely lacking superuser rights or already exists). Proceeding...");
        }

        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS images (" +
                "id bigserial PRIMARY KEY, " +
                "name varchar(255), " +
                "format varchar(10), " +
                "rgb_desc vector(512), " +   
                "hsv_desc vector(256), " +   
                "grad_desc vector(180))"     
        );
        
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS keywords (" +
                "image_id bigint REFERENCES images(id) ON DELETE CASCADE, " +
                "keyword varchar(255), " +
                "PRIMARY KEY (image_id, keyword))"
        );

        scanAndIndexFolder(directory);
    }

    private void scanAndIndexFolder(File directory) {
        File[] files = directory.listFiles((dir, name) -> {
            String lower = name.toLowerCase();
            return lower.endsWith(".jpg") || lower.endsWith(".jpeg") || lower.endsWith(".png");
        });

        if (files == null) return;

        for (File file : files) {
            String fileName = file.getName();
            Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM images WHERE name = ?", Integer.class, fileName);
                
            if (count != null && count == 0) {
                try {
                    byte[] data = Files.readAllBytes(file.toPath());
                    Image img = new Image(fileName, data);
                    insertIntoDbOnly(img);
                    System.out.println("Indexed new image found in folder: " + fileName);
                } catch (IOException e) {
                    System.err.println("Failed to read image during scan: " + fileName);
                }
            }
        }
    }

    private void insertIntoDbOnly(Image img) {
        String sql = "INSERT INTO images (name) VALUES (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, img.getName());
            return ps;
        }, keyHolder);

        if (keyHolder.getKeys() != null && keyHolder.getKeys().get("id") != null) {
            img.setId(((Number) keyHolder.getKeys().get("id")).longValue());
        }
    }

    @Override
    public void create(final Image img) {
        insertIntoDbOnly(img);
        try {
            Path path = Paths.get(IMAGE_DIR, img.getId() + ".jpg");
            Files.write(path, img.getData());
        } catch (IOException e) {
            throw new RuntimeException("Failed to save image to disk", e);
        }
    }

    @Override
    public Optional<Image> retrieve(final long id) {
        String sql = "SELECT * FROM images WHERE id = ?";
        List<Image> images = jdbcTemplate.query(sql, imageRowMapper(), id);
        return images.isEmpty() ? Optional.empty() : Optional.of(images.get(0));
}

    @Override
    public List<Image> retrieveAll() {
        String sql = "SELECT * FROM images";
        return jdbcTemplate.query(sql, imageRowMapper());
    }

    private String getFileExtension(String filename) {
        if (filename == null) return "";
        int i = filename.lastIndexOf('.');
        return (i > 0) ? filename.substring(i).toLowerCase() : "";
    }

    @Override
    public void delete(final Image img) {
        String[] possibleExtensions = {".jpg", ".jpeg", ".png", getFileExtension(img.getName()), ""};
        
        for (String ext : possibleExtensions) {
            try {
                Path path = Paths.get(IMAGE_DIR, img.getId() + ext);
                Files.deleteIfExists(path);
            } catch (IOException e) {
            }
        }
        
        jdbcTemplate.update("DELETE FROM images WHERE id = ?", img.getId());
    }

    @Override
    public void update(final Image img, final String[] params) {
    }

    private RowMapper<Image> imageRowMapper() {
        return (rs, rowNum) -> {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            byte[] data = new byte[0];
            try {
                Path path = Paths.get(IMAGE_DIR, id + ".jpg");
                if (Files.exists(path)) {
                    data = Files.readAllBytes(path);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            Image img = new Image(name, data);
            img.setId(id);
            return img;
        };
    }
}