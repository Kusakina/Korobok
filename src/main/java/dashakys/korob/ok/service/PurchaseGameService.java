package dashakys.korob.ok.service;

import javax.transaction.Transactional;

import dashakys.korob.ok.model.OrderedGame;
import dashakys.korob.ok.model.Purchase;
import dashakys.korob.ok.model.PurchaseGame;
import dashakys.korob.ok.model.ShopGame;
import dashakys.korob.ok.repository.PurchaseGameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class PurchaseGameService extends AbstractEntityService<PurchaseGame, PurchaseGameRepository> {

    private final ShopGameService shopGameService;

    public PurchaseGameService(PurchaseGameRepository repository,
                               ShopGameService shopGameService) {
        super(repository);
        this.shopGameService = shopGameService;
    }

    public List<PurchaseGame> findAllByPurchase(Purchase purchase) {
        try {
            return repository.findAllByPurchase(purchase);
        } catch (Exception e) {
            throw new EntityServiceException(e);
        }
    }

    public void addPurchasedGames(Purchase purchase, ShopGame shopGame, int count) {
        if (count > shopGame.getCount()) {
            throw new EntityServiceException(String.format("Запрошенное количество %d больше имеющегося %d", count, shopGame.getCount()));
        }

        if (count > 0) {
            PurchaseGame purchaseGame = new PurchaseGame(purchase, shopGame, count);
            save(purchaseGame);
        }
    }

    public void addPurchasedGames(Purchase purchase, List <OrderedGame> orderedGames) {
        for (var orderedGame : orderedGames) {
            PurchaseGame purchaseGame = new PurchaseGame(
                    purchase,
                    orderedGame.getShopGame(),
                    orderedGame.getCount()
            );
            save(purchaseGame);

            var databaseGame = shopGameService.findByGame(purchaseGame.getShopGame().getGame());
            // var databaseGame = shopGameService.findByGame(orderedGame.getGame());
            //databaseGame.setCount(databaseGame.getCount() - orderedGame.getCount());
            databaseGame.setCount(databaseGame.getCount() - purchaseGame.getCount());
            shopGameService.save(databaseGame);
        }
    }

    public void checkGamesCount(List<OrderedGame> games) {
        StringBuilder error = new StringBuilder();

        for (var orderedGame : games) {
            var shopGame = orderedGame.getShopGame();
            int databaseCount = shopGameService.findByGame(shopGame.getGame()).getCount();
            if (orderedGame.getCount() > databaseCount) {
                error.append(
                        String.format(
                                "Количество выбранного вами товара %s = %d, но на складе имеется только %d \n",
                                orderedGame.getGameName(),
                                orderedGame.getCount(),
                                databaseCount
                        )
                );
            }
        }

        if (error.length() > 0) {
            throw new EntityServiceException(error.toString());
        }
    }
}
