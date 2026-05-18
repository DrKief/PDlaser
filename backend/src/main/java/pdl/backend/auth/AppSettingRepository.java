package pdl.backend.auth;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppSettingRepository extends CrudRepository<AppSetting, String> {}
