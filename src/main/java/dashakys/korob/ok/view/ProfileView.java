package dashakys.korob.ok.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.Autocomplete;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import dashakys.korob.ok.model.Profile;
import dashakys.korob.ok.model.Purchase;
import dashakys.korob.ok.model.ShopGame;
import dashakys.korob.ok.service.*;

import javax.swing.*;
import java.util.List;
/*@Route(value = "profile")
@PageTitle("profile")*/

public class ProfileView extends Div {
    final Grid<Purchase> purchaseGrid;
    //private final ShopGameService shopGameService;
    //private final GameService gameService;
    private final CredentialsService credentialsService;
    private final ProfileService profileService;
    private final PurchaseService purchaseService;

    ProfileView(ProfileService profileService, PurchaseService purchaseService, CredentialsService credentialsService) {

        this.profileService = profileService;
        this.purchaseService = purchaseService;
        this.credentialsService = credentialsService;

        Button profileSettings = new Button("Изменить учето4ку");
        Dialog dialog = new Dialog();
        VerticalLayout dialogLayout = createDialogLayout(dialog);
        dialog.add(dialogLayout);

        dialog.setCloseOnOutsideClick(true);
        profileSettings.addClickListener(e -> dialog.open());
        var changeProfileButton = new VerticalLayout();
        changeProfileButton.add(profileSettings);

        this.purchaseGrid= new Grid<>(Purchase.class, false);
        var grid = createPurchaseGrid(purchaseGrid);

        add(changeProfileButton,grid);
        listPurchase();

    }
    private VerticalLayout createPurchaseGrid (Grid<Purchase> purchaseGrid){
        purchaseGrid.addColumn(Purchase::getId).setHeader("Номер заказа").setSortable(true);
        purchaseGrid.addColumn(Purchase::getManager).setHeader("Менеджер").setSortable(true);
        purchaseGrid.addColumn(Purchase::getStatus).setHeader("Статус").setSortable(true);
        purchaseGrid.addColumn(Purchase::getCost).setHeader("Цена").setSortable(true);
        //purchaseGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
        purchaseGrid.addColumn ( new ComponentRenderer<>( Button:: new, (button, purchase)-> {
            button.addThemeVariants(ButtonVariant.LUMO_ICON,
                    ButtonVariant.LUMO_ERROR,
                    ButtonVariant.LUMO_TERTIARY);
            button.addClickListener(e -> this.createView(purchase));
            button.setIcon(new Icon(VaadinIcon.PLUS));
        })).setHeader("Просмотреть заказ");

        var layout = new VerticalLayout();
        layout.add(purchaseGrid);
        return layout;

          /*purchaseGrid.addSelectionListener(selectionEvent -> { // Java 8
            // Get selection from the selection model
            Object selected =
                    purchaseGrid.getSelectionModel().getFirstSelectedItem();
            if (selected != null)
                Notification.show("Selected " +
                        grid.getContainerDataSource().getItem(selected)
                                .getItemProperty("name"));

        });*/

       /* purchaseGrid.addItemClickListener(event -> {
            if (event.getSource().get != null){
                UI.getCurrent().navigate(PurchaseView.class, event.getFirstSelectedItem());
            }

        });*/
    }

    private void createView(Purchase purchase){
        purchaseService.select(purchase);
        UI.getCurrent().navigate("purchase");
    }

    private void listPurchase() {
        purchaseGrid.setItems(purchaseService.findAllByClient(profileService.getSelectedProfile()));
    }
    private VerticalLayout createDialogLayout(Dialog dialog) {

        TextField name = new TextField();
        name.setValue(profileService.getSelectedProfile().getName());
        TextField login = new TextField(profileService.getSelectedProfile().getCredentials().getLogin());
        login.setValue(profileService.getSelectedProfile().getCredentials().getLogin());
        PasswordField password = new PasswordField();

        var setButton = new Button("Коробить");
        setButton.addClickListener( event -> {
            try {
                credentialsService.setProfile(name.getValue(), login.getValue(), password.getValue(), profileService.getSelectedProfile());
                //profileService.setName(name.getValue());
                credentialsService.signIn(login.getValue(), password.getValue());
                dialog.close();
                Notification.show("Изменения сохранены");

            } catch (EntityServiceException e) {
                Notification.show(e.getMessage());
            }
        });
        setButton.setWidth("50.0%");


        VerticalLayout profileThings = new VerticalLayout(
                new H2("Смени себя полность, " + profileService.getSelectedProfile().getName()),
                name,
                login,
                password,
                setButton
        );

        profileThings.setSpacing(true);
        profileThings.setPadding(false);
        profileThings.setAlignItems(FlexComponent.Alignment.STRETCH);
        profileThings.getStyle().set("width", "300px").set("max-width", "100%");
        profileThings.setAlignItems(FlexComponent.Alignment.CENTER);
        return profileThings;
    }

}
