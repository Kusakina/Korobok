package dashakys.korob.ok.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import dashakys.korob.ok.model.Game;
import dashakys.korob.ok.model.Profile;
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

    public Game create(String name,
                       String description,
                       String category,
                       Integer minPlayer,
                       Integer maxPlayer) {
        checkName(name);
        checkPlayers(minPlayer,maxPlayer);

        Game game = new Game(name, description, category, minPlayer, maxPlayer);
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
       return repository.searchUniqueCategories();
    }

    public Set<Game> findByFilter(Set<String> selectedItems){
        if (selectedItems.isEmpty()) {
            return Set.copyOf(findAll());
        }

        try {
            return repository.findByCategoryIn(selectedItems);
        } catch (Exception e) {
            throw new EntityServiceException(e);
        }
    }

    public void checkFields(String name, Integer minPlayer, Integer maxPlayer, Game game) {
        checkPlayers(minPlayer, maxPlayer);
        if (!name.equals(game.getName())){
            checkName(name);
        }

    }
    public void checkName(String name){
        if (name.isBlank()) {
            throw new EntityServiceException("Название игры не должно состоять только из пробельных символов");
        }

        if (findByName(name).isPresent()) {
            throw new EntityServiceException(String.format("Игра с названием %s уже сущеcтвует", name));
        }
    }
    public void checkPlayers(Integer minPlayer, Integer maxPlayer){
        if (minPlayer!= null && minPlayer <= 0) {
            throw new EntityServiceException("Боюсь, так поиграть не получится?\n Укажите хотя бы одного игрока!");
        }
        if (maxPlayer!= null && maxPlayer <= 0) {
            throw new EntityServiceException("Боюсь, так поиграть не получится?\n Укажите хотя бы одного игрока!");
        }

    }
    public void update(String name, String description,
                                String category,
                                Integer minPlayer,
                                Integer maxPlayer
                                ,Game game){
        checkFields(name, minPlayer, maxPlayer, game);
        game.setName(name);
        game.setDescription(description);
        game.setCategory(category);
        game.setMinPlayers(minPlayer);
        game.setMaxPlayers(maxPlayer);
        this.save(game);
    }
}
