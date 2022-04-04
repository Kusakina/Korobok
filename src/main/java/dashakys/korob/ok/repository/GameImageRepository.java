package dashakys.korob.ok.repository;

import dashakys.korob.ok.model.Game;

public interface GameImageRepository {
    UserRoleRepository findByGame (Game game);
}
