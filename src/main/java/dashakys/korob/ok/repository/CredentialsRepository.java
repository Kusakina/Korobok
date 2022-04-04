package dashakys.korob.ok.repository;

import java.util.Optional;

import dashakys.korob.ok.model.Credentials;
import dashakys.korob.ok.model.Game;
import dashakys.korob.ok.model.GameImage;
import dashakys.korob.ok.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CredentialsRepository extends EntityRepository<Credentials> {
    Optional<Credentials> findByUser(User user);
    Optional<Credentials> findByLoginAndAndPasswordHash(String login, long password);
}
