package dashakys.korob.ok.repository;

import dashakys.korob.ok.model.Order;
import dashakys.korob.ok.model.Status;
import dashakys.korob.ok.model.User;

public interface OrderRepository extends EntityRepository<Order> {
    OrderRepository findByUser (User user);
    OrderRepository findByStatus (Status status);
}
