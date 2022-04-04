package dashakys.korob.ok.repository;

import java.util.Optional;

import dashakys.korob.ok.model.Game;
import dashakys.korob.ok.model.GameImage;
import org.springframework.stereotype.Repository;

@Repository
public interface GameImageRepository extends EntityRepository<GameImage> {
    Optional<GameImage> findByGame(Game game);
}
