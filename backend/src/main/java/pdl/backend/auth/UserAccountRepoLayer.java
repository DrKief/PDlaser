package pdl.backend.auth;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepoLayer extends CrudRepository<UserAccountLayer, Long> {
    Optional<UserAccountLayer> findByUsername(String username);
}