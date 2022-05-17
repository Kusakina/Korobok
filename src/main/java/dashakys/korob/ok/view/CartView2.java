package dashakys.korob.ok.view;

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
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import dashakys.korob.ok.model.OrderedGame;
import dashakys.korob.ok.model.ShopGame;
import dashakys.korob.ok.service.*;

import java.util.List;
@Route(value = "cart", layout = UserHouse.class)
@PageTitle("cart")
public class CartView2 extends VerticalLayout {
    final Grid<OrderedGame> orderedGameGrid;
    //private final ShopGameService shopGameService;
    //private final ProfileService profileService;
    //private final SelectedProfileService selectedProfileService;
    //private final GameService gameService;
    //private final PurchaseGameService purchaseGameService;
    public CartView2(ShopGameService shopGameService,
                     GameService gameService,
                     PurchaseService purchaseService,
                     PurchaseGameService purchaseGameService,
                     ProfileService profileService,
                     SelectedProfileService selectedProfileService,
                     CartService cartService){

        //this.shopGameService = shopGameService;
        //this.gameService = gameService;
        //this.purchaseGameService = purchaseGameService;
        //this.profileService = profileService;
        //this.selectedProfileService = selectedProfileService;
        this.orderedGameGrid = new Grid<>(OrderedGame.class, false);

        orderedGameGrid.addColumn(OrderedGame::getGameName).setHeader("Игра").setSortable(true);
        orderedGameGrid.addColumn(OrderedGame::getPrice).setHeader("Цена").setSortable(true);
        orderedGameGrid.addColumn(OrderedGame::getCount).setHeader("Количество").setSortable(true);
        orderedGameGrid.addColumn ( new ComponentRenderer<>( Button:: new, (button, orderedGame)-> {
            button.addThemeVariants(ButtonVariant.LUMO_ICON,
                    ButtonVariant.LUMO_ERROR,
                    ButtonVariant.LUMO_TERTIARY);
            button.addClickListener(event -> this.lowerGame(shopGameService, orderedGame, cartService));
            button.setIcon(new Icon(VaadinIcon.MINUS));
        })).setHeader("уменьшить количество");

        orderedGameGrid.addColumn ( new ComponentRenderer<>( Button:: new, (button, orderedGame)-> {
           button.addThemeVariants(ButtonVariant.LUMO_ICON,
                    ButtonVariant.LUMO_ERROR,
                    ButtonVariant.LUMO_TERTIARY);
            button.addClickListener(event -> this.removeGame(orderedGame, cartService));
            button.setIcon(new Icon(VaadinIcon.TRASH));
        })).setHeader("Удалить");

        var total = new Label("Итого: " + cartService.getTotalCost());
        var toCreatePurchase = new HorizontalLayout();
        var createOrder = new Button("Создать заказ", event -> {
            try {
                var a = cartService.getCount();
                purchaseService.createOrder(
                        cartService.getGames(),
                        selectedProfileService.getSelectedProfile()
                );
               // UI.getCurrent().navigate("login2");
            } catch (EntityServiceException | ViewException e) {
                Notification.show(e.getMessage());
            }
        });

        if (cartService.size() != 0) {
            toCreatePurchase.add(total, createOrder);
        }
        if(selectedProfileService.getSelectedProfile()!= null) {
            add(orderedGameGrid, toCreatePurchase);
        }
        listGames(cartService.getGames());
    }
    private void listGames(List <OrderedGame> list) {
        orderedGameGrid.setItems(list);
    }

    private void removeGame(OrderedGame orderedGame, CartService cartService) {
        //shopGameService.findByGame(shopGame.getGame());
        cartService.remove(orderedGame);
        //cartService.remove(shopGameGrid.getEditor().getItem());
        listGames(cartService.getGames());
        orderedGameGrid.getDataProvider().refreshAll();
        //UI.getCurrent().getPage().reload();

    }

    private void lowerGame(ShopGameService shopGameService, OrderedGame orderedGame, CartService cartService)
    {
        int n = orderedGame.getCount() - 1 ;
        //int n = shopGameGrid.getEditor().getItem().getCount() - 1;
        if (n == 0) {
            //cartService.remove(shopGame);
            //UI.getCurrent().getPage().reload();
            //cartService.remove(shopGameGrid.getEditor().getItem());
            removeGame(orderedGame,cartService);
        } else {
            cartService.setCount(shopGameService.findByGame(orderedGame.getShopGame().getGame()), n);
            //shopGameGrid.getEditor().getItem().setCount(n);
            //shopGame.setCount(n);
            //cartService.setCount(shopGame,n);
            //UI.getCurrent().getPage().reload();

            //shopGameGrid.getDataProvider().refreshAll();
            listGames(cartService.getGames());
            orderedGameGrid.getDataProvider().refreshItem(orderedGameGrid.getEditor().getItem());
        }
    }

}
