package dashakys.korob.ok.repository;

import java.util.Optional;

import dashakys.korob.ok.model.Credentials;
import dashakys.korob.ok.model.Profile;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialsRepository extends EntityRepository<Credentials> {
    Optional<Credentials> findByProfile(Profile profile);
    Optional<Credentials> findByLoginAndPasswordHash(String login, long passwordHash);
}
