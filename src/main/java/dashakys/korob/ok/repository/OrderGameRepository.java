package dashakys.korob.ok.repository;

import java.util.List;
import java.util.Optional;

import dashakys.korob.ok.model.*;

public interface OrderGameRepository extends EntityRepository<OrderGame> {
    List<OrderGame> findAllByOrder(Order order);
    List<OrderGame> findAllByShopGame(ShopGame shopgame);
    Optional<OrderGame> findByOrderAndShopGame(Order order, ShopGame shopGame);
}
