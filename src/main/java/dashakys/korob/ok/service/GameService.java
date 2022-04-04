package dashakys.korob.ok.service;

import dashakys.korob.ok.model.Game;
import dashakys.korob.ok.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public class GameService extends AbstractEntityService<Game, JpaRepository<Game, Long>> {
    private Game selectedGame;
    public GameService(JpaRepository<Game, Long> repository) {
        super(repository);
    }
    public void select(Long id) {
        selectedGame.setId(id);
    }

    public User selectedGame() {
        return selectedGame();
    }

    @Override
    protected Game findByName(String name) {
        try {
            return repository.findByName(
                    name.getGame()
            );
        } catch (Exception e) {
            throw new EntityServiceException();
        }
    }
}
