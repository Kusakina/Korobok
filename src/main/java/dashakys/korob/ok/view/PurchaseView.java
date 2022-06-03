package dashakys.korob.ok.view;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import dashakys.korob.ok.model.*;
import dashakys.korob.ok.service.*;

import java.util.List;

@Route(value = "purchase")
@PageTitle("purchase")
public class PurchaseView extends Div {
    private final SelectedPurchaseService selectedPurchaseService;
    final Grid <PurchaseGame> orderGrid;
    public PurchaseView(SelectedPurchaseService selectedPurchaseService,  PurchaseGameService purchaseGameService, SelectedProfileService selectedProfileService){
        this.selectedPurchaseService = selectedPurchaseService;
        this.orderGrid = new Grid<>(PurchaseGame.class, false);
        //purchaseGrid.addColumn(purchase -> {return purchase.getManager().getName(); }).setHeader("Менеджер").setSortable(true);
        orderGrid.addColumn(purchaseGame -> {return purchaseGame.getShopGame().getGameName(); }).setHeader("Название игры").setSortable(true);
        orderGrid.addColumn(purchaseGame -> {return purchaseGame.getShopGame().getPrice(); }).setHeader("Цена").setSortable(true);
        orderGrid.addColumn(purchaseGame -> {return purchaseGame.getCount(); }).setHeader("Количество").setSortable(true);
        orderGrid.addColumn(purchaseGame -> {return purchaseGame.getCost(); }).setHeader("Суммарно").setSortable(true);
        listGames(selectedPurchaseService.getGames(purchaseGameService));
        var layout = new VerticalLayout();
        layout.add(orderGrid);
        var layout2 = new VerticalLayout();
        Button exit = new Button("Назад", event -> {
            if (selectedProfileService.getSelectedProfile().getRole() == Role.USER) {
                UI.getCurrent().navigate("profile2");
            } else {
                UI.getCurrent().navigate("orders");
            }
        });
        exit.setWidth("30.0%");

        if(selectedProfileService.getSelectedProfile()!= null) {
            add(exit,layout);
        } else {
            var a = new VerticalLayout(new H2("Короб'ок так просто не откроется!"));
            add(a);
        }
    }
    private void listGames(List<PurchaseGame> list) {
        orderGrid.setItems(list);
    }
}
