package dashakys.korob.ok.view;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import dashakys.korob.ok.model.Role;
import dashakys.korob.ok.service.SelectedProfileService;

@Route(value = "adminHouse")
@PageTitle("adminHouse")

public class AdminHouse extends AppLayout {

    public AdminHouse(SelectedProfileService selectedProfileService) {

        FlexLayout centeredLayout = new FlexLayout();
        centeredLayout.setSizeFull();
        centeredLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        centeredLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        if (selectedProfileService.getSelectedProfile()!= null && selectedProfileService.getSelectedProfile().getRole()== Role.ADMIN){
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
        RouterLink a = new RouterLink("Заказы",OrdersView2.class);
        a.setHighlightCondition(HighlightConditions.sameLocation());
        Tab orders = new Tab(a, VaadinIcon.SHOP.create());
        RouterLink b = new RouterLink("Каталог",AdminCatalogView.class);
        b.setHighlightCondition(HighlightConditions.sameLocation());
        Tab catalogue = new Tab(b, VaadinIcon.BARCODE.create());
        RouterLink с = new RouterLink("Добавить игру",GameView.class);
        с.setHighlightCondition(HighlightConditions.sameLocation());
        Tab games = new Tab(с, VaadinIcon.GAMEPAD.create());
        RouterLink d = new RouterLink("Выход",LoginView2.class);
        //d.setHighlightCondition(HighlightConditions.sameLocation());
        //UI ui = getUI().get();
        //d.afterNavigation(e-> getSession().close());
        Tab logout = new Tab(d, VaadinIcon.OUT.create());
        Tabs tabs = new Tabs(orders, catalogue, games, logout);
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
