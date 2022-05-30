package dashakys.korob.ok.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
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
import dashakys.korob.ok.model.Role;
import dashakys.korob.ok.model.ShopGame;
import dashakys.korob.ok.service.*;

import java.util.List;
import java.util.Set;

@Route(value = "adminCatalog", layout = AdminHouse.class)
@PageTitle("adminCatalog")

public class AdminCatalogView extends VerticalLayout {

    //Set<ShopGame>  gameMap;
    final Grid<ShopGame> shopGameGrid;
    //private final ShopGameService shopGameService;
    //private final GameService gameService;
    TextField filter = new TextField();
    Button checkFilters = new Button("Искать по коробочкам");
    public AdminCatalogView (ShopGameService shopGameService,
                        GameService gameService,
                        CartService cartService,
                        SelectedProfileService selectedProfileService) {
        //this.shopGameService = shopGameService;
        //this.gameService = gameService;

        this.shopGameGrid = new Grid<>(ShopGame.class, false);

        //this.myCart = myCart;




        //Button closeButton = new Button(new Icon("lumo", "cross"), (e) -> dialog.close());
        //closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        //dialog.setCloseOnOutsideClick(true);


        checkFilters.addClickListener(e -> {
            Dialog dialog = new Dialog();
            //dialog.getElement().setAttribute("aria-label", "Add note");
            List<String> category = gameService.getCategory();
            VerticalLayout dialogLayout = createDialogLayout(dialog, category,shopGameService, gameService);
            dialog.add(dialogLayout);
            dialog.setCloseOnOutsideClick(true);
            dialog.open();
        });

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
            button.addThemeVariants();
            //button.setText("Изменить");
            button.addClickListener(event -> {
                try {
                    Dialog gameEditor = new Dialog();
                    VerticalLayout gameEditorLayout = editGame(shopGame, gameEditor,shopGameService,gameService);
                    gameEditor.add(gameEditorLayout);
                    gameEditor.setCloseOnOutsideClick(true);
                    gameEditor.open();
                    gameService.getCategory();
                    //editeGame(shopGame, gameEditor,shopGameService,gameService);
                    //UI.getCurrent().getPage().reload();
                } catch (EntityServiceException e) {
                    Notification.show(e.getMessage());
                }
            });
            //button.setIcon(new Icon(VaadinIcon.EDIT));
            Icon b = new Icon("vaadin", "edit");
            button.setIcon(b);
            button.setWidth("20.0%");
        })).setHeader("Изменить");
        shopGameGrid.addColumn ( new ComponentRenderer<>( Button:: new, (button, shopGame)-> {
            button.addThemeVariants(ButtonVariant.LUMO_ICON,
                    ButtonVariant.LUMO_ERROR,
                    ButtonVariant.LUMO_TERTIARY);
            button.addClickListener(event -> {
                try {
                    Dialog deleteDialog = new Dialog();
                    VerticalLayout gameEditorLayout = deleteGame(shopGame,deleteDialog,shopGameService,gameService);
                    deleteDialog.add(gameEditorLayout);
                    deleteDialog.setCloseOnOutsideClick(true);
                    deleteDialog.open();
                    gameService.getCategory();
                    //editeGame(shopGame, gameEditor,shopGameService,gameService);
                    //UI.getCurrent().getPage().reload();
                } catch (EntityServiceException e) {
                    Notification.show(e.getMessage());
                }
            });
            //button.setIcon(new Icon(VaadinIcon.TRASH));
            Icon a = new Icon("vaadin", "trash");
            button.setIcon(a);
            button.setWidth("20.0%");
        })).setHeader("Удалить");
        if(selectedProfileService.getSelectedProfile()!= null && selectedProfileService.getSelectedProfile().getRole()== Role.ADMIN) {
            add(filters, shopGameGrid);
        }
        listGames(shopGameService);
    }

    private VerticalLayout deleteGame(ShopGame shopGame, Dialog deleteDialog, ShopGameService shopGameService, GameService gameService) {
        Label text = new Label("Вы действительно хотите удалить "+ shopGame.getGame().getName() +"?");
        Button deleteButton =  new Button("Применить");
        deleteButton.addClickListener(event ->{
            try{
                gameService.remove(shopGame.getGame());
               // shopGameService.remove(shopGame);
                deleteDialog.close();
                shopGameGrid.getDataProvider().refreshAll();
                shopGameGrid.setItems(shopGameService.findAll());
                Notification.show("Игра удалена");

            }catch (EntityServiceException e) {
                Notification.show(e.getMessage());
            }
        });
        deleteButton.setWidth("50.0%");


        var layout = new VerticalLayout(
                new H2("Удаление игры"), text, deleteButton
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
    private void updateListBox(Set<String> selectedItems, ShopGameService shopGameService, GameService gameService) {

        shopGameGrid.setItems(shopGameService.findByFilter(gameService.findByFilter(selectedItems)));
    }

    private void listGames(ShopGameService shopGameService) {
        shopGameGrid.setItems(shopGameService.findAll());
    }

    private VerticalLayout editGame(ShopGame shopGame, Dialog dialog, ShopGameService shopGameService, GameService gameService) {
        String size = ("80.0%");
        TextField game = new TextField("Название игры");
        game.setValue(shopGame.getGameName());
        game.setWidth(size);
        IntegerField price = new IntegerField("Стоимость игры");
        price.setValue(shopGame.getPrice());
        price.setWidth(size);
        IntegerField count = new IntegerField("Количество коробков");
        count.setValue(shopGame.getCount());
        count.setWidth(size);
        TextArea description = new TextArea("Описание");
        if(shopGame.getGame().getDescription()!= null) {
            description.setValue(shopGame.getGame().getDescription());
        }
        description.setWidth(size);
        TextField category = new TextField("Категория");
        if(shopGame.getGame().getCategory()!= null) {
            category.setValue(shopGame.getGame().getCategory());
        }
        category.setWidth(size);
        IntegerField minPlayer = new IntegerField("Минимальное количество игроков");
        minPlayer.setValue(shopGame.getGame().getMinPlayers());
        minPlayer.setWidth(size);
        IntegerField maxPlayer = new IntegerField("Максимальное количество игроков");
        maxPlayer.setValue(shopGame.getGame().getMaxPlayers());
        maxPlayer.setWidth(size);

        var setButton = new Button("Коробить");
        setButton.addClickListener( event -> {
            try {
                shopGameService.update(
                        price.getValue(),
                        count.getValue(),
                        shopGame
                );
                gameService.update(
                        game.getValue(),
                        description.getValue(),
                        category.getValue(),
                        minPlayer.getValue(),
                        maxPlayer.getValue(),
                        shopGame.getGame()
                );
                dialog.close();
                shopGameGrid.getDataProvider().refreshAll();
                Notification.show("Изменения сохранены");
            } catch (EntityServiceException e) {
                Notification.show(e.getMessage());
            }
        });

        setButton.setWidth("50.0%");

        VerticalLayout GameEditor = new VerticalLayout(
                new H2("Изменение игры, " + shopGame.getGame().getName()),
                game,
                price,
                count,
                description,
                category,
                minPlayer,
                maxPlayer,
                setButton
        );

        GameEditor.setSpacing(true);
        GameEditor.setPadding(false);
        GameEditor.setAlignItems(FlexComponent.Alignment.STRETCH);
        GameEditor.getStyle().set("width", "500px").set("max-width", "100%");
        GameEditor.setAlignItems(FlexComponent.Alignment.CENTER);
        return GameEditor;

    }

    private VerticalLayout createDialogLayout(Dialog dialog, List<String> category, ShopGameService shopGameService, GameService gameService) {

        CheckboxGroup<String> categoryCheckBox = new CheckboxGroup<>();
        categoryCheckBox.setLabel("Категория");
        categoryCheckBox.setItems(category);
        categoryCheckBox.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
        Button addButton =  new Button("Применить");
        addButton.addClickListener(event ->{
            updateListBox(categoryCheckBox.getSelectedItems(),shopGameService, gameService);
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
        layout.getStyle().set("width", "400px").set("max-width", "100%");
        layout.setAlignItems(Alignment.CENTER);
        return layout;
    }

}
