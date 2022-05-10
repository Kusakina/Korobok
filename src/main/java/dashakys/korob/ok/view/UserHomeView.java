package dashakys.korob.ok.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabVariant;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import dashakys.korob.ok.service.*;

import java.util.HashMap;
import java.util.Map;

@Route(value = "userHome")
@PageTitle("userHome")

public class UserHomeView extends Div {
    UserHomeView(ShopGameService shopGameService, GameService gameService, ProfileService profileService, CredentialsService credentialsService) {
        Tab catalogue = new Tab(
                VaadinIcon.TAGS.create(),
                new Span("Каталог")
        );
        Tab profile = new Tab(
                VaadinIcon.USER.create(),
                new Span("Профиль")
        );
        Tab cart = new Tab(
                VaadinIcon.CART.create(),
                new Span("Короб")
        );

        Tab logout = new Tab(
                VaadinIcon.OUT.create(),
                new Span("Выход")
        );
        //  Tab [] list_tabs = new Tab[] { catalogue, profile, cart, logout};
        // Set the icon on top
  /*  for (int i = 0; i < list_tabs[i], ++i ){

        tab.addThemeVariants(TabVariant.LUMO_ICON_ON_TOP);
    }*/

        Tabs tabs = new Tabs(catalogue, profile, cart, logout);
        tabs.addThemeVariants(TabsVariant.LUMO_CENTERED);
        Map<Tab, Class> tabsMap = new HashMap<>();
        tabsMap.put(logout, LoginView.class);


        Map<Tab, Integer> tabsInt = new HashMap<>();
        tabsInt.put(catalogue, 1 );
        tabsInt.put(cart, 2);
        //tabsInt.put(profile,3);
        tabsInt.put(logout,4);

        Map<Tab, Component> ViewMap = new HashMap<>();
        ViewMap.put(catalogue, new CatalogView(shopGameService,gameService));
        ViewMap.put(cart, new RegisterView(credentialsService));
        Component a = (Component) ViewMap.get(catalogue);
        Component b = (Component) ViewMap.get(cart);
        //Component d = (Component) ViewMap.get(logout);
        a.setVisible(true);
        b.setVisible(false);
        //d.setVisible(false);
        add(tabs,a,b);


        //tabsMap.put(profile, RegisterView.class);
        final int[] last = {1};
        tabs.addSelectedChangeListener(event -> {
            if (event.getSelectedTab()== logout){
               UI.getCurrent().navigate("login2");
            }
            this.getComponentAt(last[0]).setVisible(false);
            this.getComponentAt(tabsInt.get(event.getSelectedTab())).setVisible(true);
            var left = tabsInt.get(event.getSelectedTab());
            last[0] = left;

            // UI.getCurrent().navigate(tabsMap.get(event.getSelectedTab()));});
     /*   Tab selected = tabs.getSelectedTab();
        VerticalLayout selectedView = new VerticalLayout();
        if (selected == catalogue){

        }*/

        });


    }

}