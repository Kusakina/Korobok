package dashakys.korob.ok.service;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.VaadinSessionScope;
import dashakys.korob.ok.model.OrderedGame;
import dashakys.korob.ok.model.Purchase;
import dashakys.korob.ok.model.PurchaseGame;

import java.util.List;

@SpringComponent
@VaadinSessionScope
public class SelectedPurchaseService {

    private Purchase selectedPurchase;

    public void select(Purchase order) { this.selectedPurchase = order; }
    public Purchase getSelectedPurchase() { return selectedPurchase; }
    public List<PurchaseGame> getGames(PurchaseGameService purchaseGameService){
        return purchaseGameService.findAllByPurchase(selectedPurchase);
    }
}
