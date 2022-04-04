package dashakys.korob.ok.repository;

import java.util.List;
import java.util.Optional;

import dashakys.korob.ok.model.Game;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends EntityRepository<Game>  {
    Optional<Game> findByName(String name);
    List<Game> findAllByCategory(String category);
    List<Game> findAllByMaxPlayers(int maxPlayers);
    List<Game> findAllByMinPlayers(int minPlayers);
}
