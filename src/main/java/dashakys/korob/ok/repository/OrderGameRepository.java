package dashakys.korob.ok.repository;

import dashakys.korob.ok.model.*;

public interface OrderGameRepository extends EntityRepository<OrderGame> {
    OrderGameRepository findByOrder (Order order);
    OrderGameRepository findByGame (Game game);
    OrderGameRepository findByShopGame (ShopGame shopgame);
}
