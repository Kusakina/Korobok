package dashakys.korob.ok.view;


import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import dashakys.korob.ok.service.PurchaseService;
import dashakys.korob.ok.service.SelectedPurchaseService;

@Route(value = "purchase")
@PageTitle("purchase")
public class PurchaseView extends Div {
    private final SelectedPurchaseService selectedPurchaseService;
    public PurchaseView(SelectedPurchaseService selectedPurchaseService){
        this.selectedPurchaseService = selectedPurchaseService;
        var layout = new VerticalLayout();
        layout.add(selectedPurchaseService.getSelectedPurchase().getClient().getName());
        add(layout);
    }

}
