package service;

import model.Game;
import model.Order;
import model.ShopGame;
import org.springframework.data.jpa.repository.JpaRepository;
import repository.ShopGameRepository;

public class ShopGameService extends AbstractEntityService<ShopGame, JpaRepository<ShopGame, Long>> {
    private ShopGame selectedShopGame;
    public ShopGameService(JpaRepository<ShopGame, Long> repository) {
        super(repository);
    }
    public void select(Game game) {
        selectedShopGame.setGame(game);
    }
    public void getPrice() {
        selectedShopGame.getPrice();
    }
    public void getGame() {
        selectedShopGame.getGame();
    }
}
