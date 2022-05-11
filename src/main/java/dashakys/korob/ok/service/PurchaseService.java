package dashakys.korob.ok.service;

import javax.transaction.Transactional;

import dashakys.korob.ok.model.Credentials;
import dashakys.korob.ok.model.Profile;
import dashakys.korob.ok.model.Purchase;
import dashakys.korob.ok.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class PurchaseService extends AbstractEntityService<Purchase, PurchaseRepository> {

    private Purchase selectedPurchase;

    public PurchaseService(PurchaseRepository repository) {
        super(repository);
    }

    public void select(Purchase order) { this.selectedPurchase = order; }
    public Purchase getSelectedPurchase() { return selectedPurchase; }
    public List <Purchase> findAllByClient(Profile client) {
        try {
            return repository.findAllByClient(client);
        } catch (Exception e) {
            throw new EntityServiceException(e);
        }
    }

}
