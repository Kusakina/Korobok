package dashakys.korob.ok.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import dashakys.korob.ok.model.Game;
import dashakys.korob.ok.model.ShopGame;
import dashakys.korob.ok.service.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Route(value = "catalog2", layout = UserHouse.class)
@PageTitle("catalog2")
public class CatalogView2 extends VerticalLayout {
    //Set<ShopGame>  gameMap;
    final Grid<ShopGame> shopGameGrid;
    //private final ShopGameService shopGameService;
    //private final GameService gameService;
    TextField filter = new TextField();
    Button checkFilters = new Button("Искать по коробочкам");
    public CatalogView2(ShopGameService shopGameService,
                        GameService gameService,
                        CartService cartService,
                        SelectedProfileService selectedProfileService) {
        //this.shopGameService = shopGameService;
        //this.gameService = gameService;

        this.shopGameGrid = new Grid<>(ShopGame.class, false);

        //this.myCart = myCart;
        List<String> category = gameService.getCategory();
        Dialog dialog = new Dialog();
        //dialog.getElement().setAttribute("aria-label", "Add note");

        VerticalLayout dialogLayout = createDialogLayout(dialog, category,shopGameService, gameService);
        dialog.add(dialogLayout);
        dialog.setCloseOnOutsideClick(true);

        //Button closeButton = new Button(new Icon("lumo", "cross"), (e) -> dialog.close());
        //closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        dialog.setCloseOnOutsideClick(true);


        checkFilters.addClickListener(e -> dialog.open());

        //ComboBox<String> categoryBox = new ComboBox<>();
        //categoryBox.setItems(category);
        //categoryBox.setItemLabelGenerator();

        HorizontalLayout filters = new HorizontalLayout(filter, checkFilters);
        //filters.setFlexGrow(1, comboBox);
        configureFilter (shopGameService);

        //HashMap<ShopGame, Integer> gameMap = new HashMap<>();

        shopGameGrid.addColumn(ShopGame::getGameName).setHeader("Игра").setSortable(true);
        shopGameGrid.addColumn(ShopGame::getPrice).setHeader("Цена").setSortable(true);
        shopGameGrid.addColumn(ShopGame::getCount).setHeader("Количество").setSortable(true);
        //shopGameGrid.addComponentColumn(new Button(VaadinIcon.PLUS.create(), event -> method()));
        shopGameGrid.addColumn ( new ComponentRenderer<>( Button:: new, (button, shopGame)-> {
            button.addThemeVariants(ButtonVariant.LUMO_ICON,
                    ButtonVariant.LUMO_ERROR,
                    ButtonVariant.LUMO_TERTIARY);
            button.addClickListener(event -> {
                try {
                    cartService.addToCart(shopGame);
                    //UI.getCurrent().getPage().reload();
                } catch (EntityServiceException e) {
                    Notification.show(e.getMessage());
                }
            });
            button.setIcon(new Icon(VaadinIcon.PLUS));
            button.setWidth("20.0%");
        })).setHeader("Кинуть в короб");
        shopGameGrid.addItemClickListener(event -> {
            Dialog gameInfo = new Dialog();
            VerticalLayout gameEditorLayout = showGame(event.getItem(), gameInfo);
            gameInfo.add(gameEditorLayout);
            gameInfo.setCloseOnOutsideClick(true);
            gameInfo.open();
        });
        if(selectedProfileService.getSelectedProfile()!= null) {
            add(filters, shopGameGrid);
        }
        listGames(shopGameService);
    }

    private void configureFilter(ShopGameService shopGameService) {
        filter.setPlaceholder("Поиск по игре");
        filter.setPrefixComponent(VaadinIcon.SEARCH.create());
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.LAZY);
        filter.addValueChangeListener(e -> updateList(shopGameService));
    }

    private void updateList(ShopGameService shopGameService) {
        shopGameGrid.setItems(shopGameService.findAllByName(filter.getValue()));
    }
    private void updateListBox(Set<String> selectedItems, GameService gameService) {
        var games = gameService.findByFilter(selectedItems);
//        var shopGames = shopGameService.findByFilter(games);
        shopGameGrid.setItems(games.stream().map(Game::getShopGame).collect(Collectors.toList()));
    }

    private void listGames(ShopGameService shopGameService) {
        shopGameGrid.setItems(shopGameService.findAll());
    }

    private VerticalLayout createDialogLayout(Dialog dialog, List<String> category, ShopGameService shopGameService, GameService gameService) {

        CheckboxGroup <String> categoryCheckBox = new CheckboxGroup<>();
        categoryCheckBox.setLabel("Категория");
        categoryCheckBox.setItems(category);
        categoryCheckBox.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
       Button addButton =  new Button("Применить");
       addButton.addClickListener(event ->{
            updateListBox(categoryCheckBox.getSelectedItems(), gameService);
            dialog.close();
       });
       addButton.setWidth("50.0%");


        var layout = new VerticalLayout(
                new H2("Выбирай нужное!"), categoryCheckBox,addButton
               /* new Button("Применить", event ->{
                    updateListBox(categoryCheckBox.getSelectedItems());
                })*/
        );
        layout.setSpacing(true);
        layout.setPadding(false);
        layout.setAlignItems(Alignment.STRETCH);
        layout.getStyle().set("width", "300px").set("max-width", "100%");
        layout.setAlignItems(Alignment.CENTER);
        return layout;
    }
    private VerticalLayout showGame(ShopGame shopGame, Dialog dialog) {
        String size = ("80.0%");
        TextField game = new TextField("Название игры");
        game.setValue(shopGame.getGameName());
        game.setWidth(size);
        game.setReadOnly(true);
        IntegerField price = new IntegerField("Стоимость игры");
        price.setValue(shopGame.getPrice());
        price.setWidth(size);
        price.setReadOnly(true);
        IntegerField count = new IntegerField("Количество коробков");
        count.setValue(shopGame.getCount());
        count.setWidth(size);
        count.setReadOnly(true);
        TextArea description = new TextArea("Описание");
        if(shopGame.getGame().getDescription()!= null) {
            if(shopGame.getGame().getDescription()!= "") {
                description.setValue(shopGame.getGame().getDescription());
            }else{
                description.setValue("Информация отсутствует");
            }

        }
        description.setWidth(size);
        description.setReadOnly(true);
        TextField category = new TextField("Категория");
        if(shopGame.getGame().getCategory()!= null) {
            if(shopGame.getGame().getCategory()!="") {
                category.setValue(shopGame.getGame().getCategory());
            } else {
                category.setValue("Информация отсутствует");
            }
        }
        category.setWidth(size);
        category.setReadOnly(true);
        IntegerField minPlayer = new IntegerField("Минимальное количество игроков");
        minPlayer.setValue(shopGame.getGame().getMinPlayers());
        minPlayer.setWidth(size);
        minPlayer.setReadOnly(true);
        IntegerField maxPlayer = new IntegerField("Максимальное количество игроков");
        maxPlayer.setValue(shopGame.getGame().getMaxPlayers());
        maxPlayer.setWidth(size);
        maxPlayer.setReadOnly(true);

        var okButton = new Button("Понятно!");
        okButton.addClickListener( event -> {
            dialog.close();
        });

        okButton.setWidth("50.0%");

        VerticalLayout GameInfo = new VerticalLayout(
                new H2("Информация об игре '" + shopGame.getGame().getName()+"'"),
                game,
                price,
                count,
                description,
                category,
                minPlayer,
                maxPlayer,
                okButton
        );

        GameInfo.setSpacing(true);
        GameInfo.setPadding(false);
        GameInfo.setAlignItems(FlexComponent.Alignment.STRETCH);
        GameInfo.getStyle().set("width", "500px").set("max-width", "100%");
        GameInfo.setAlignItems(FlexComponent.Alignment.CENTER);
        return GameInfo;

    }
}
