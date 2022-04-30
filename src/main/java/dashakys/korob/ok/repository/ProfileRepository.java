package dashakys.korob.ok.repository;

import dashakys.korob.ok.model.Credentials;
import dashakys.korob.ok.model.Profile;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends EntityRepository<Profile> {
    Optional<Profile> findByLogin(String login);
    Optional<Profile> findByCredentials(Credentials credentials);
}
