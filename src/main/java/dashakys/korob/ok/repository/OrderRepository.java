package dashakys.korob.ok.repository;

import java.util.List;

import dashakys.korob.ok.model.Order;
import dashakys.korob.ok.model.Status;
import dashakys.korob.ok.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends EntityRepository<Order> {
    List<Order> findAllByClient(User client);
    List<Order> findAllByManager(User manager);
    List<Order> findAllByStatus(Status status);
}
