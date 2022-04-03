package repository;

import model.Game;

public interface GameImageRepository {
    UserRoleRepository findByGame (Game game);
}
