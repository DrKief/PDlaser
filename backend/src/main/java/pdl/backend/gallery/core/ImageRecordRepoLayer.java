package pdl.backend.gallery.core;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRecordRepoLayer extends CrudRepository<ImageRecordLayer, Long> {
  Optional<ImageRecordLayer> findByHash(String hash);
}