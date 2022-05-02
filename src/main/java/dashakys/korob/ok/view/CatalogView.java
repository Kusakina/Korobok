package dashakys.korob.ok.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import dashakys.korob.ok.model.Profile;
import dashakys.korob.ok.model.ShopGame;
import dashakys.korob.ok.service.ShopGameService;

public class CatalogView extends VerticalLayout {
    final Grid<ShopGame> shopGameGrid;
    private final ShopGameService shopGameService;
    TextField filter = new TextField();
    public CatalogView(ShopGameService shopGameService) {

        configureFilter ();
        this.shopGameService = shopGameService;
        this.shopGameGrid = new Grid<>(ShopGame.class, false);


        shopGameGrid.addColumn(ShopGame::getGameName).setHeader("Игра");
        shopGameGrid.addColumn(ShopGame::getPrice).setHeader("Цена");
        shopGameGrid.addColumn(ShopGame::getCount).setHeader("Количество");
        //shopGameGrid.addComponentColumn(new Button(VaadinIcon.PLUS.create(), event -> method()));
        shopGameGrid.addColumn ( new ComponentRenderer<>( Button:: new, (button, shopGame)-> {
            button.addThemeVariants(ButtonVariant.LUMO_ICON,
                    ButtonVariant.LUMO_ERROR,
                    ButtonVariant.LUMO_TERTIARY);
            button.addClickListener(e -> this.addToCart(shopGame));
            button.setIcon(new Icon(VaadinIcon.PLUS));
        })).setHeader("Кинуть в короб");
        add(filter,shopGameGrid);
        listGames();
    }

    private void configureFilter() {
        filter.setPlaceholder("Filter by game");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.LAZY);
        filter.addValueChangeListener(e -> updateList());
    }

    private void updateList() {
        shopGameGrid.setItems(shopGameService.findAllByGame(filter.getValue()));
    }

    private void addToCart(ShopGame shopGame){

    }

    private void listGames() {
        shopGameGrid.setItems(shopGameService.findAll());
    }
    private void method(){

    }
}
