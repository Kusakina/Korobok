package dashakys.korob.ok.service;

import dashakys.korob.ok.model.Game;
import dashakys.korob.ok.model.OrderGame;
import org.springframework.data.jpa.repository.JpaRepository;

public class OrderGameService extends AbstractEntityService<OrderGame, JpaRepository<OrderGame, Long>> {
        private Game selectedGame;
        public OrderGameService(JpaRepository<OrderGame, Long> repository) {
            super(repository);
        }

}
