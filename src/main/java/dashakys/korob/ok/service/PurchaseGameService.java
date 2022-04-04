package dashakys.korob.ok.service;

import javax.transaction.Transactional;

import dashakys.korob.ok.model.PurchaseGame;
import dashakys.korob.ok.repository.PurchaseGameRepository;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class PurchaseGameService extends AbstractEntityService<PurchaseGame, PurchaseGameRepository> {
    public PurchaseGameService(PurchaseGameRepository repository) {
        super(repository);
    }
}
