package dashakys.korob.ok.repository;

import java.util.Optional;

import dashakys.korob.ok.model.Credentials;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialsRepository extends EntityRepository<Credentials> {
    Optional<Credentials> findByLogin(String login);
    Optional<Credentials> findByLoginAndPasswordHash(String login, long passwordHash);
}
