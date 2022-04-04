package dashakys.korob.ok.service;

import javax.transaction.Transactional;

import dashakys.korob.ok.model.OrderGame;
import dashakys.korob.ok.repository.OrderGameRepository;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class OrderGameService extends AbstractEntityService<OrderGame, OrderGameRepository> {
    public OrderGameService(OrderGameRepository repository) {
        super(repository);
    }
}
