package dashakys.korob.ok.service;

import javax.transaction.Transactional;

import dashakys.korob.ok.model.Order;
import dashakys.korob.ok.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class OrderService extends AbstractEntityService<Order, OrderRepository> {

    private Order selectedOrder;

    public OrderService(OrderRepository repository) {
        super(repository);
    }

    public void select(Order order) { this.selectedOrder = order; }
    public Order getSelectedOrder() { return selectedOrder; }
}
