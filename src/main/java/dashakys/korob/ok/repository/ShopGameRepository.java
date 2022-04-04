package dashakys.korob.ok.repository;

import java.util.Optional;

import dashakys.korob.ok.model.Game;
import dashakys.korob.ok.model.ShopGame;

public interface ShopGameRepository extends EntityRepository<ShopGame> {

    Optional<ShopGame> findByGame(Game game);
}
