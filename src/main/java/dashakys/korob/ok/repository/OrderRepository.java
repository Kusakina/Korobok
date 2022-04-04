package dashakys.korob.ok.repository;

import java.util.List;

import dashakys.korob.ok.model.Order;
import dashakys.korob.ok.model.Status;
import dashakys.korob.ok.model.Profile;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends EntityRepository<Order> {
    List<Order> findAllByClient(Profile client);
    List<Order> findAllByManager(Profile manager);
    List<Order> findAllByStatus(Status status);
}
