package dashakys.korob.ok.service;

import dashakys.korob.ok.model.Order;
import dashakys.korob.ok.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public class OrderService extends AbstractEntityService<Order, JpaRepository<Order, Long>> {
    private Order selectedOrder;
    public OrderService(JpaRepository<Order, Long> repository) {
        super(repository);
    }
    public void select(Long id) {
        selectedOrder.setId(id);
    }

    public void selectClient(User user) {
        selectedOrder.setClient(user);
    }
    public void selectManager(User user) {
        selectedOrder.setManager(user);
    }
    public void getSelectClient(User user) {
        selectedOrder.getClient();
    }
    public void getSelectManager(User user) {
        selectedOrder.getManager();
    }
    public void getSelectedCost() {
        selectedOrder.getCost();
    }
}
