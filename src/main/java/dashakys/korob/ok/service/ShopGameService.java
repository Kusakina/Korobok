package dashakys.korob.ok.service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;

import dashakys.korob.ok.model.Game;
import dashakys.korob.ok.model.Profile;
import dashakys.korob.ok.model.ShopGame;
import dashakys.korob.ok.repository.ShopGameRepository;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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

    public List<ShopGame> findAllByName(String name){
        if (name == null || name.isEmpty()) {
            return findAll();
        } else{
           try {
               return repository.search(name);
           } catch (Exception e) {
                throw new EntityServiceException(e);
           }
        }
    }

    public List<ShopGame> findByFilter(Set<Game> selectedItems){
        if (selectedItems.isEmpty()) {
            return findAll();
        } else{
            try {
                return repository.findByGameIn(selectedItems);
            } catch (Exception e) {
                throw new EntityServiceException(e);
            }
        }
    }

    public ShopGame findByGame(Game game) {
        try {
            return repository.findByGame(game).get();
        } catch (Exception e) {
            throw new EntityServiceException(e);
        }
    }

    public static void checkFields(Integer price, Integer count) {
        if (price <= 0) {
            throw new EntityServiceException("Мы ценим свой товар!");
        }
        if (price == null) {
            throw new EntityServiceException("Забыл оценить товар!");
        }
        if (count < 0) {
            throw new EntityServiceException("Ой, кажется ты обсчитался с количеством товара!");
        }
        if (count == null) {
            throw new EntityServiceException("Забыл посчитать товар!");
        }

    }

    public void update(Integer price, Integer count, ShopGame shopGame){
        checkFields(price, count);
        shopGame.setPrice(price);
        shopGame.setCount(count);
        this.save(shopGame);
    }
    public void create(Integer price, Integer count, Game game){
        checkFields(price, count);
        addShopGame(game, price, count);
    }
}
