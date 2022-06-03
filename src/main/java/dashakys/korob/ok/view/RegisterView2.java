package dashakys.korob.ok.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import dashakys.korob.ok.service.CredentialsService;
import dashakys.korob.ok.service.EntityServiceException;
import dashakys.korob.ok.service.SelectedProfileService;

@Route("register1")

public class RegisterView2 extends UI {
    private final SelectedProfileService selectedProfileService;

    public RegisterView2(SelectedProfileService selectedProfileService) {
        this.selectedProfileService = selectedProfileService;
    }

    protected Component initContent() {
        var username = new TextField("Имя пользователя");
        var login = new TextField("Логин");
        var password = new TextField("Пароль");

        var layout = new VerticalLayout (
                new H2("Стань частью Короб'ка"),
                username,
                login,
                password,
                new Button("Стать коробчаниным", event -> {
                    try {
                        selectedProfileService.signUp(username.getValue(), login.getValue(), password.getValue());
                    } catch (EntityServiceException e) {
                        Notification.show(e.getMessage());
                    }
                })
        );
        return layout;
    }
}
