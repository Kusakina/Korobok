package dashakys.korob.ok.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import dashakys.korob.ok.model.Game;
import dashakys.korob.ok.model.Profile;
import dashakys.korob.ok.model.ShopGame;
import dashakys.korob.ok.repository.GameRepository;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class GameService extends AbstractEntityService<Game, GameRepository> {

    private Game selectedGame;

    public GameService(GameRepository repository) {
        super(repository);
    }

    public void select(Long id) {
        selectedGame.setId(id);
    }

    public Game getSelectedGame() {
        return selectedGame;
    }

    public Optional<Game> findByName(String name) {
        try {
            return repository.findByName(name);
        } catch (Exception e) {
            throw new EntityServiceException(e);
        }
    }


    public Game addGame(String name) {
        if (name.isBlank()) {
            throw new EntityServiceException("Название игры не должно состоять только из пробельных символов");
        }

        if (findByName(name).isPresent()) {
            throw new EntityServiceException(String.format("Игра с названием %s уже сущеcтвует", name));
        }

        Game game = new Game(name);
        save(game);

        return game;
    }

    public List<Game> findAllPurchasedByProfile(Profile profile) {
        try {
            return repository.findAllPurchasedByProfile(profile);
        } catch (Exception e) {
            throw new EntityServiceException(e);
        }
    }
    public List<String> getCategory(){
       return repository.searchCategory();
    }
    public  Set<Game> findByFilter(Set<String> selectedItems){
            try {
                return repository.findByCategoryIn(selectedItems);
            } catch (Exception e) {
                throw new EntityServiceException(e);
            }
    }
}
