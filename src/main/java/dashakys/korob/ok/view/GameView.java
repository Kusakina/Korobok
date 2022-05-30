package dashakys.korob.ok.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import dashakys.korob.ok.model.Game;
import dashakys.korob.ok.model.ShopGame;
import dashakys.korob.ok.service.EntityServiceException;
import dashakys.korob.ok.service.GameService;
import dashakys.korob.ok.service.SelectedProfileService;
import dashakys.korob.ok.service.ShopGameService;

@Route(value = "game", layout = AdminHouse.class)
@PageTitle("game")
public class GameView extends Div {
    public GameView(SelectedProfileService selectedProfileService, ShopGameService shopGameService, GameService gameService) {
        if (selectedProfileService.getSelectedProfile() != null) {
            add(addGame(shopGameService, gameService));
        }

    }

    private Component addGame(ShopGameService shopGameService, GameService gameService) {
        TextField name = new TextField("Название игры");
        name.setWidth("50.0%");
        IntegerField price = new IntegerField("Стоимость игры");
        price.setWidth("50.0%");
        IntegerField count = new IntegerField("Количество коробков");
        count.setWidth("50.0%");
        TextArea description = new TextArea("Описание");
        description.setWidth("50.0%");
        TextField category = new TextField("Категория");
        category.setWidth("50.0%");
        IntegerField minPlayer = new IntegerField("Минимальное количество игроков");
        minPlayer.setWidth("50.0%");
        IntegerField maxPlayer = new IntegerField("Максимальное количество игроков");
        maxPlayer.setWidth("50.0%");

        var setButton = new Button("Добавить коробок");
        setButton.addClickListener(event -> {
            try {
                Game game = gameService.create(
                        name.getValue(),
                        description.getValue(),
                        category.getValue(),
                        minPlayer.getValue(),
                        maxPlayer.getValue()
                );
                shopGameService.create(
                        price.getValue(),
                        count.getValue(),
                        game
                );

                Notification.show("Игра добавлена");
                clearFields(name, description, category, price, count, minPlayer,maxPlayer);
            } catch (EntityServiceException e) {
                Notification.show(e.getMessage());
            }
        });

        setButton.setWidth("50.0%");

        VerticalLayout addingGame = new VerticalLayout(
                new H2("Добавляй короба в закрома!"),
                name,
                price,
                count,
                description,
                category,
                minPlayer,
                maxPlayer,
                setButton
        );
        addingGame.setAlignItems(FlexComponent.Alignment.CENTER);

        return addingGame;

    }
    private void clearFields(TextField name,
                             TextArea description,
                             TextField category,
                             IntegerField price,
                             IntegerField count,
                             IntegerField minPlayer,
                             IntegerField maxPlayer){
        name.clear();
        description.clear();
        category.clear();
        minPlayer.clear();
        maxPlayer.clear();
        price.clear();
        count.clear();
    }
}
