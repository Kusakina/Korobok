package dashakys.korob.ok.repository;

import java.util.List;
import java.util.Optional;

import dashakys.korob.ok.model.*;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseGameRepository extends EntityRepository<PurchaseGame> {
    List<PurchaseGame> findAllByPurchase(Purchase order);
    List<PurchaseGame> findAllByShopGame(ShopGame shopgame);
    Optional<PurchaseGame> findByPurchaseAndShopGame(Purchase order, ShopGame shopGame);
}
