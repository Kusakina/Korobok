package dashakys.korob.ok.service;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.VaadinSessionScope;
import dashakys.korob.ok.model.Purchase;

@SpringComponent
@VaadinSessionScope
public class SelectedPurchaseService {

    private Purchase selectedPurchase;

    public void select(Purchase order) { this.selectedPurchase = order; }
    public Purchase getSelectedPurchase() { return selectedPurchase; }
}
