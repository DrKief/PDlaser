package pdl.backend.auth;

import java.util.Optional;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserAccount, Long> {
  Optional<UserAccount> findByUsername(String username);

  @Query("SELECT * FROM users WHERE is_approved = false")
  List<UserAccount> findPendingApprovals();
}
