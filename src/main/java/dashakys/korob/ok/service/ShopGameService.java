package dashakys.korob.ok.service;

import javax.transaction.Transactional;

import dashakys.korob.ok.model.Game;
import dashakys.korob.ok.model.ShopGame;
import dashakys.korob.ok.repository.ShopGameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class ShopGameService extends AbstractEntityService<ShopGame, ShopGameRepository> {
    private final GameService gameService;

    public ShopGameService(ShopGameRepository repository,
                           GameService gameService) {
        super(repository);
        this.gameService = gameService;
    }

    public void addShopGame(Game game, int price, int count) {
        if (price < 0) {
            throw new EntityServiceException(String.format("Цена не может быть меньше 0: %d", price));
        }

        if (count < 0) {
            throw new EntityServiceException(String.format("Количество не может быть меньше 0: %d", count));
        }

        ShopGame shopGame = new ShopGame(game, price, count);
        game.setShopGame(shopGame);

        save(shopGame);
        gameService.save(game);
    }
    public List<ShopGame> findAllByGame(String name){
        if (name == null|| name.isEmpty()) {
            return findAll();
        } else{
           try {
               return repository.search(name);
           } catch (Exception e) {
                throw new EntityServiceException(e);
            }
        }
    }
}
