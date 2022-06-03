package dashakys.korob.ok.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import dashakys.korob.ok.model.OrderedGame;
import dashakys.korob.ok.model.ShopGame;
import dashakys.korob.ok.service.*;

import java.util.List;

public class CartView extends VerticalLayout {
    final Grid<OrderedGame> orderedGameGrid;
    private final ShopGameService shopGameService;
    private final ProfileService profileService;
    private final SelectedProfileService selectedProfileService;
    private final GameService gameService;
    private final PurchaseGameService purchaseGameService;
    public CartView (ShopGameService shopGameService,
                     GameService gameService,
                     PurchaseService purchaseService,
                     PurchaseGameService purchaseGameService,
                     ProfileService profileService,
                     SelectedProfileService selectedProfileService,
                     CartService cartService){

        this.shopGameService = shopGameService;
        this.gameService = gameService;
        this.purchaseGameService = purchaseGameService;
        this.profileService = profileService;
        this.selectedProfileService = selectedProfileService;
        this.orderedGameGrid = new Grid<>(OrderedGame.class, false);

        orderedGameGrid.addColumn(OrderedGame::getGameName).setHeader("Игра").setSortable(true);
        orderedGameGrid.addColumn(OrderedGame::getPrice).setHeader("Цена").setSortable(true);
        orderedGameGrid.addColumn(OrderedGame::getCount).setHeader("Количество").setSortable(true);
        orderedGameGrid.addColumn ( new ComponentRenderer<>( Button:: new, (button, shopGame)-> {
            button.addThemeVariants(ButtonVariant.LUMO_ICON,
                    ButtonVariant.LUMO_ERROR,
                    ButtonVariant.LUMO_TERTIARY);
            button.addClickListener(e -> {
                int n = orderedGameGrid.getEditor().getItem().getCount() - 1;
                if (n == 0) {
                    cartService.remove(orderedGameGrid.getEditor().getItem());
                } else {
                    orderedGameGrid.getEditor().getItem().setCount(n);
                    orderedGameGrid.getDataProvider().refreshItem(orderedGameGrid.getEditor().getItem());
                }
                button.setIcon(new Icon(VaadinIcon.MINUS));
            });
        })).setHeader("уменьшить количество");

        orderedGameGrid.addColumn ( new ComponentRenderer<>( Button:: new, (button, shopGame)-> {
            button.addThemeVariants(ButtonVariant.LUMO_ICON,
                    ButtonVariant.LUMO_ERROR,
                    ButtonVariant.LUMO_TERTIARY);
            button.addClickListener(e -> {
                cartService.remove(orderedGameGrid.getEditor().getItem());
                orderedGameGrid.getDataProvider().refreshAll();
            });
            button.setIcon(new Icon(VaadinIcon.TRASH));
        })).setHeader("Удалить");

        var total = new Label("Итого: " + cartService.getTotalCost());
        var toCreatePurchase = new HorizontalLayout();
        var createOrder = new Button("Создать заказ", event -> {
            try {
                purchaseService.createOrder(
                        cartService.getGames(),
                        selectedProfileService.getSelectedProfile()
                );
                UI.getCurrent().navigate("userHome");
            } catch (EntityServiceException | ViewException e) {
                Notification.show(e.getMessage());
            }
        });

        if (cartService.size() != 0) {
            toCreatePurchase.add(total, createOrder);
        }

        add(orderedGameGrid, toCreatePurchase);
        listGames(cartService.getGames());
    }
    private void listGames(List<OrderedGame> list) {
        orderedGameGrid.setItems(list);
    }

}
