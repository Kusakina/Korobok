package dashakys.korob.ok.service;

import java.util.Optional;

import javax.transaction.Transactional;

import dashakys.korob.ok.model.Game;
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
}
