package dashakys.korob.ok.view;


import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import dashakys.korob.ok.service.PurchaseService;

@Route(value = "purchase")
@PageTitle("purchase")
public class PurchaseView extends Div {
    private final PurchaseService purchaseService;
    public PurchaseView(PurchaseService purchaseService){
    this.purchaseService =purchaseService;
    var a = new VerticalLayout();
    a.add(purchaseService.getSelectedPurchase().getClient().getName());
    add(a);
    }

}
