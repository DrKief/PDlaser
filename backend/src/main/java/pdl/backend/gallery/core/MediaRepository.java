package pdl.backend.gallery.core;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository extends CrudRepository<MediaRecord, Long> {
  Optional<MediaRecord> findByHash(String hash);
}
