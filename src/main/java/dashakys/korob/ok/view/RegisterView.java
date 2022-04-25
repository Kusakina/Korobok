package dashakys.korob.ok.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import dashakys.korob.ok.service.CredentialsService;
import dashakys.korob.ok.service.EntityServiceException;

@Route("register")

public class RegisterView extends Composite {
    CredentialsService credentialsService;
    public RegisterView(CredentialsService credentialsService){
        this.credentialsService = credentialsService;
    }
    @Override
    protected Component initContent() {
        var username = new TextField("Имя пльзователя");
        var login = new TextField("Логин");
        var password = new TextField("Пароль");

        var layout = new VerticalLayout(
                new H2("Стань частью Короб'ка"),
                username,
                login,
                password,
                new Button("Стать коробчаниным", event -> {
                    try {
                        credentialsService.signUp(username.getValue(), login.getValue(), password.getValue());
                    } catch (EntityServiceException e) {
                        Notification.show(e.getMessage());
                    }
                })
        );
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        return layout;
    }
}
