package dashakys.korob.ok.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import dashakys.korob.ok.service.CredentialsService;
import dashakys.korob.ok.service.EntityServiceException;

@Route(value = "login")
@PageTitle("login")

public class LoginView extends Div {
    public LoginView(CredentialsService credentialsService){
        setId("login-view");
        var login = new TextField("Login");
        var password = new TextField("Password");
        add(
                new H1("Назвался игроком - полезай в короб!"),
                login,
                password,
                new Button("Закоробиться", event -> {
                    try{
                        credentialsService.signIn(login.getValue(), password.getValue());
                        UI.getCurrent().navigate("home");
                    }
                    catch (EntityServiceException e){
                        Notification.show("Неверный логин/пароль");

                    }
                }),
                new RouterLink("Еще не залезал в закрома короба?", RegisterView.class)
        );

    }
}
