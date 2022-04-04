package repository;

import model.Game;
import model.User;

import javax.persistence.Column;

public interface GameRepository extends EntityRepository<Game>  {
    GameRepository findByName (String name);
    GameRepository findByCategory (String category);
    GameRepository findByMaxPlayers (int maxPlayers);
    GameRepository findByMinPlayers (int minPlayers);
}
