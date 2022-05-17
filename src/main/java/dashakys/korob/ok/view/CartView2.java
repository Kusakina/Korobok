package dashakys.korob.ok.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import dashakys.korob.ok.model.ShopGame;
import dashakys.korob.ok.service.*;

import java.util.List;
@Route(value = "cart", layout = UserHouse.class)
@PageTitle("cart")
public class CartView2 extends VerticalLayout {
    final Grid<ShopGame> shopGameGrid;
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
        this.shopGameGrid = new Grid<>(ShopGame.class, false);

        shopGameGrid.addColumn(ShopGame::getGameName).setHeader("Игра").setSortable(true);
        shopGameGrid.addColumn(ShopGame::getPrice).setHeader("Цена").setSortable(true);
        shopGameGrid.addColumn(ShopGame::getCount).setHeader("Количество").setSortable(true);
        shopGameGrid.addColumn ( new ComponentRenderer<>( Button:: new, (button, shopGame)-> {
            button.addThemeVariants(ButtonVariant.LUMO_ICON,
                    ButtonVariant.LUMO_ERROR,
                    ButtonVariant.LUMO_TERTIARY);
            button.addClickListener(event -> this.lowerGame(shopGame, shopGameService, cartService));
            button.setIcon(new Icon(VaadinIcon.MINUS));
        })).setHeader("уменьшить количество");

        shopGameGrid.addColumn ( new ComponentRenderer<>( Button:: new, (button, shopGame)-> {
           button.addThemeVariants(ButtonVariant.LUMO_ICON,
                    ButtonVariant.LUMO_ERROR,
                    ButtonVariant.LUMO_TERTIARY);
            button.addClickListener(event -> this.removeGame(shopGameService,shopGame, cartService));
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
            add(shopGameGrid, toCreatePurchase);
        }
        listGames(cartService.getGames());
    }
    private void listGames(List <ShopGame> list) {
        shopGameGrid.setItems(list);
    }

    private void removeGame(ShopGameService shopGameService, ShopGame shopGame, CartService cartService)
    {
        //shopGameService.findByGame(shopGame.getGame());
        cartService.remove( shopGameService.findByGame(shopGame.getGame()));
        //cartService.remove(shopGameGrid.getEditor().getItem());
        listGames(cartService.getGames());
        shopGameGrid.getDataProvider().refreshAll();
        //UI.getCurrent().getPage().reload();

    }

    private void lowerGame(ShopGame shopGame, ShopGameService shopGameService, CartService cartService)
    {
        int n = shopGame.getCount() - 1 ;
        //int n = shopGameGrid.getEditor().getItem().getCount() - 1;
        if (n == 0) {
            //cartService.remove(shopGame);
            //UI.getCurrent().getPage().reload();
            //cartService.remove(shopGameGrid.getEditor().getItem());
            removeGame(shopGameService,shopGame,cartService);
        } else {
            cartService.setCount(shopGameService.findByGame(shopGame.getGame()), n);
            //shopGameGrid.getEditor().getItem().setCount(n);
            //shopGame.setCount(n);
            //cartService.setCount(shopGame,n);
            //UI.getCurrent().getPage().reload();

            //shopGameGrid.getDataProvider().refreshAll();
            listGames(cartService.getGames());
            shopGameGrid.getDataProvider().refreshItem(shopGameGrid.getEditor().getItem());
        }
    }

}
