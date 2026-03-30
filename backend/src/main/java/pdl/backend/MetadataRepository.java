package pdl.backend;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JDBC Repository for the Image entity.
 * Provides basic CRUD operations (Save, FindById, FindAll, Delete) automatically.
 */
@Repository
public interface MetadataRepository extends CrudRepository<Metadata, Long> {
  
  /**
   * Custom query method to find an image by its SHA-256 hash.
   * Used during upload to detect and prevent duplicate image storage.
   *
   * @param hash The SHA-256 string.
   * @return An Optional containing the ImageEntity if found.
   */
  Optional<Metadata> findByHash(String hash);
}