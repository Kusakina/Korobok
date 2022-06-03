package dashakys.korob.ok.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.spring.annotation.VaadinSessionScope;
import dashakys.korob.ok.service.SelectedProfileService;

import java.util.HashMap;
import java.util.Map;

@Route(value = "userHouse")
@PageTitle("userHouse")

public class UserHouse extends AppLayout {

    public UserHouse(SelectedProfileService selectedProfileService) {

        FlexLayout centeredLayout = new FlexLayout();
        centeredLayout.setSizeFull();
        centeredLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        centeredLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        if(selectedProfileService.getSelectedProfile()!= null) {
            centeredLayout.add(getTabs(selectedProfileService));

            addToNavbar(false, centeredLayout);
        }else{
            var a = new VerticalLayout(new H2("Короб'ок так просто не откроется!"));
            addToNavbar(a);
        }
        //Tabs tabs = getTabs();
        //new VerticalLayout(new DrawerToggle());
        //addToNavbar(tabs);
    }


    private Tabs getTabs(SelectedProfileService selectedProfileService){
        RouterLink с = new RouterLink("Короб",CartView2.class);
        с.setHighlightCondition(HighlightConditions.sameLocation());
        Tab cart = new Tab(с, VaadinIcon.CART.create());
        RouterLink a = new RouterLink("Каталог",CatalogView2.class);
        a.setHighlightCondition(HighlightConditions.sameLocation());
        Tab catalogue = new Tab(a, VaadinIcon.TAGS.create());
        RouterLink b = new RouterLink("Профиль",ProfileView2.class);
        b.setHighlightCondition(HighlightConditions.sameLocation());
        Tab profile = new Tab(b, VaadinIcon.USER.create());
        RouterLink d = new RouterLink("Выход",LoginView2.class);
        Tab logout = new Tab(d, VaadinIcon.OUT.create());
        //d.setHighlightCondition(HighlightConditions.sameLocation());
        //UI ui = getUI().get();
        //d.afterNavigation(e-> getSession().close());
        Tabs tabs = new Tabs(cart, catalogue, profile, logout);
        //if (tabs.getSelectedTab() == logout){
            //UI.getCurrent().navigate(LoginView2.class);
            //ui.getPage().executeJs("");
            //ui.getSession().close();
           // UI.getCurrent().navigate(LoginView2.class);
        //}
        tabs.addThemeVariants(TabsVariant.LUMO_CENTERED);

        return tabs;
    }
}
