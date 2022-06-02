package dashakys.korob.ok.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.editor.Editor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import dashakys.korob.ok.model.Purchase;
import dashakys.korob.ok.model.Role;
import dashakys.korob.ok.model.Status;
import dashakys.korob.ok.service.EntityServiceException;
import dashakys.korob.ok.service.PurchaseService;
import dashakys.korob.ok.service.SelectedProfileService;
import dashakys.korob.ok.service.SelectedPurchaseService;

@Route(value = "orders", layout = AdminHouse.class)
@PageTitle("orders")
public class OrdersView2 extends Div {
    final Grid<Purchase> purchaseGrid;
    OrdersView2(SelectedPurchaseService selectedPurchaseService, SelectedProfileService selectedProfileService, PurchaseService purchaseService){

        this.purchaseGrid =  new Grid<>(Purchase.class, false);

        var grid = createPurchaseGrid(purchaseGrid, selectedPurchaseService, purchaseService);




        if(selectedProfileService.getSelectedProfile()!= null && selectedProfileService.getSelectedProfile().getRole()== Role.ADMIN) {

            add(grid);
            listPurchase(selectedProfileService, purchaseService);
        }


        listPurchase(selectedProfileService, purchaseService);
    }
    private VerticalLayout createPurchaseGrid (Grid<Purchase> purchaseGrid, SelectedPurchaseService selectedPurchaseService, PurchaseService purchaseService) {
        purchaseGrid.addColumn(Purchase::getId).setHeader("Номер заказа").setSortable(true);
        purchaseGrid.addColumn(purchase -> {
            return purchase.getManager().getName();
        }).setHeader("Менеджер").setSortable(true);
        //purchaseGrid.addColumn(Purchase::getManager).setHeader("Менеджер").setSortable(true);
        purchaseGrid.addColumn(Purchase::getStatus).setHeader("Статус").setSortable(true);
        purchaseGrid.addColumn(Purchase::getCost).setHeader("Стоимость").setSortable(true);
        //purchaseGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
        purchaseGrid.addColumn(new ComponentRenderer<>(Button::new, (button, purchase) -> {
            button.addThemeVariants(ButtonVariant.LUMO_ICON,
                    ButtonVariant.LUMO_ERROR,
                    ButtonVariant.LUMO_TERTIARY);
            button.addClickListener(e -> this.createView(purchase, selectedPurchaseService));
            button.setIcon(new Icon(VaadinIcon.PLUS));
            button.setWidth("20.0%");
        })).setHeader("Просмотреть заказ");
        purchaseGrid.addColumn(new ComponentRenderer<>(Button::new, (button, purchase) -> {
            button.addThemeVariants();
            button.addClickListener(e ->{
                Dialog dialog = new Dialog();
                VerticalLayout dialogLayout = createDialog(purchase,purchaseService, dialog);
                dialog.add(dialogLayout);
                dialog.setCloseOnOutsideClick(true);
                dialog.open();
            });
            button.setWidth("20.0%");
            button.setIcon(new Icon(VaadinIcon.EDIT));
        })).setHeader("Изменить статус");

        var layout = new VerticalLayout();
        layout.add(purchaseGrid);
        return layout;
    }

    private VerticalLayout createDialog(Purchase purchase, PurchaseService purchaseService, Dialog dialog) {
        RadioButtonGroup<Status> radioGroup = new RadioButtonGroup<>();
        radioGroup.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        radioGroup.setItems(Status.OPEN, Status.IN_PROGRESS, Status.COMPLETE, Status.CANCELED);
        add(radioGroup);
        Button button =  new Button("Применить");
        button.addClickListener(event ->{
            try{
                purchaseService.update(radioGroup.getValue(), purchase);
                purchaseGrid.getDataProvider().refreshAll();
                purchaseGrid.setItems(purchaseService.findAll());
                Notification.show("Статус изменен");
                dialog.close();

            }catch (EntityServiceException e) {
                Notification.show(e.getMessage());
            }
        });
        button.setWidth("50.0%");


        var layout = new VerticalLayout(
                new H2("изменение статуса заказа"),radioGroup, button
           /* new Button("Применить", event ->{
                updateListBox(categoryCheckBox.getSelectedItems());
            })*/
        );
        layout.setSpacing(true);
        layout.setPadding(false);
        layout.setAlignItems(FlexComponent.Alignment.STRETCH);
        layout.getStyle().set("width", "300px").set("max-width", "100%");
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        return layout;
    }

    private void listPurchase( SelectedProfileService selectedProfileService, PurchaseService purchaseService) {
        var manager = selectedProfileService.getSelectedProfile();
        purchaseGrid.setItems(purchaseService.findAllByManager(manager));
    }
    private void createView(Purchase purchase, SelectedPurchaseService selectedPurchaseService){
        selectedPurchaseService.select(purchase);
        UI.getCurrent().navigate("purchase");
    }
}
