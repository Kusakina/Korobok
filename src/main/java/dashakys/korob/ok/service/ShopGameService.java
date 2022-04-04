package dashakys.korob.ok.service;

import javax.transaction.Transactional;

import dashakys.korob.ok.model.ShopGame;
import dashakys.korob.ok.repository.ShopGameRepository;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class ShopGameService extends AbstractEntityService<ShopGame, ShopGameRepository> {
    public ShopGameService(ShopGameRepository repository) {
        super(repository);
    }
}
