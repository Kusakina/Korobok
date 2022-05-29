package dashakys.korob.ok.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import dashakys.korob.ok.model.Role;
import dashakys.korob.ok.service.CartService;
import dashakys.korob.ok.service.CredentialsService;
import dashakys.korob.ok.service.EntityServiceException;
import dashakys.korob.ok.service.SelectedProfileService;

//@Route(value = "login2")
@Route(value = "")
@PageTitle("login2")

public class LoginView2 extends Composite {
    private final SelectedProfileService selectedProfileService;
    private final CartService cartService;

    public LoginView2(SelectedProfileService selectedProfileService, CartService cartService) {
        this.selectedProfileService = selectedProfileService;
        this.cartService = cartService;
    }

    @Override
    protected Component initContent() {
        selectedProfileService.setSelectedProfile();
        cartService.removeAll();
        var login = new TextField("Логин");
        var password = new PasswordField("Пароль");

        var layout = new VerticalLayout(
                new H2("Назвался игроком - полезай в короб!"),
                login,
                password,
                new Button("Закоробиться", event -> {
                    try {
                        selectedProfileService.signIn(login.getValue(), password.getValue());
                        if (selectedProfileService.getSelectedProfile().getRole() == Role.ADMIN){
                            UI.getCurrent().navigate("adminHouse");
                        } else {
                            UI.getCurrent().navigate("userHouse");
                        }
                    } catch (EntityServiceException e) {
                        Notification.show("Неверный логин/пароль");
                    }

                }),
                new RouterLink("Еще не залезал в закрома короба?", RegisterView.class)
        );
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        return layout;
    }
}

