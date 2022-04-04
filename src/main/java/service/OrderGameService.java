package service;

import model.Game;
import model.OrderGame;
import org.springframework.data.jpa.repository.JpaRepository;

public class OrderGameService extends AbstractEntityService<OrderGame, JpaRepository<OrderGame, Long>> {
        private Game selectedGame;
        public OrderGameService(JpaRepository<OrderGame, Long> repository) {
            super(repository);
        }

}
