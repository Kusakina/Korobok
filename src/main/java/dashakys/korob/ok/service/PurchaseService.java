package dashakys.korob.ok.service;

import javax.transaction.Transactional;

import dashakys.korob.ok.model.Purchase;
import dashakys.korob.ok.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class PurchaseService extends AbstractEntityService<Purchase, PurchaseRepository> {

    private Purchase selectedPurchase;

    public PurchaseService(PurchaseRepository repository) {
        super(repository);
    }

    public void select(Purchase order) { this.selectedPurchase = order; }
    public Purchase getSelectedPurchase() { return selectedPurchase; }
}
