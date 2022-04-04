package dashakys.korob.ok.repository;

import java.util.Optional;

import dashakys.korob.ok.model.Game;
import dashakys.korob.ok.model.ShopGame;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopGameRepository extends EntityRepository<ShopGame> {

    Optional<ShopGame> findByGame(Game game);
}
