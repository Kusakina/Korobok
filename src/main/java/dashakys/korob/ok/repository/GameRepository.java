package dashakys.korob.ok.repository;

import dashakys.korob.ok.model.Game;

public interface GameRepository extends EntityRepository<Game>  {
    GameRepository findByName (String name);
    GameRepository findByCategory (String category);
    GameRepository findByMaxPlayers (int maxPlayers);
    GameRepository findByMinPlayers (int minPlayers);
}
