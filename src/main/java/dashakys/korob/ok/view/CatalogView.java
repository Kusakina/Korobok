package dashakys.korob.ok.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import dashakys.korob.ok.model.Profile;
import dashakys.korob.ok.model.ShopGame;
import dashakys.korob.ok.service.ShopGameService;

public class CatalogView extends VerticalLayout {
    final Grid<ShopGame> shopGameGrid;
    private final ShopGameService shopGameService;

    public CatalogView(ShopGameService shopGameService) {
        this.shopGameService = shopGameService;
        this.shopGameGrid = new Grid<>(ShopGame.class, false);


        shopGameGrid.addColumn(ShopGame::getGame).setHeader("Игра");
        shopGameGrid.addColumn(ShopGame::getPrice).setHeader("Цена");
        shopGameGrid.addColumn(ShopGame::getCount).setHeader("Количество");
        //shopGameGrid.addComponentColumn(new Button(VaadinIcon.PLUS.create(), event -> method()));
        add(shopGameGrid);
        listGames();
    }

    private void listGames() {
        shopGameGrid.setItems(shopGameService.findAll());
    }
    private void method(){

    }
}
