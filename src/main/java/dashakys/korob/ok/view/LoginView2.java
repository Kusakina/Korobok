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
import dashakys.korob.ok.service.CredentialsService;
import dashakys.korob.ok.service.EntityServiceException;

@Route(value = "login2")
@PageTitle("login2")

public class LoginView2 extends Composite {
    CredentialsService credentialsService;
    public LoginView2 (CredentialsService credentialsService){
        this.credentialsService = credentialsService;
    }
    @Override
    protected Component initContent() {

        var login = new TextField("Логин");
        var password = new PasswordField("Пароль");

        var layout = new VerticalLayout(
                new H2("Назвался игроком - полезай в короб!"),
                login,
                password,
                new Button("Закоробиться", event -> {
                    try {
                        credentialsService.signIn(login.getValue(), password.getValue());
                        UI.getCurrent().navigate("userHome");
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

