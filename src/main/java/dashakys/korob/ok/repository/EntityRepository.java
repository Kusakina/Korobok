package dashakys.korob.ok.repository;

import dashakys.korob.ok.model.DatabaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntityRepository<T extends DatabaseEntity> extends JpaRepository<T, Long> {
}
