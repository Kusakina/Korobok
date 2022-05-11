package dashakys.korob.ok.repository;

import java.util.List;
import java.util.Optional;

import dashakys.korob.ok.model.Purchase;
import dashakys.korob.ok.model.Status;
import dashakys.korob.ok.model.Profile;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends EntityRepository<Purchase> {
    List<Purchase> findAllByClient(Profile client);
    List<Purchase> findAllByManager(Profile manager);
    List<Purchase> findAllByStatus(Status status);
}
