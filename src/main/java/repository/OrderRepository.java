package repository;

import model.Order;
import model.Status;
import model.User;

public interface OrderRepository extends EntityRepository<Order> {
    OrderRepository findByUser (User user);
    OrderRepository findByStatus (Status status);
}
