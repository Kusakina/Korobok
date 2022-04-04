package dashakys.korob.ok.repository;

import java.util.Optional;

import dashakys.korob.ok.model.Game;
import dashakys.korob.ok.model.GameImage;

public interface GameImageRepository extends EntityRepository<GameImage> {
    Optional<GameImage> findByGame(Game game);
}
