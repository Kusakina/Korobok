package dashakys.korob.ok.repository;
import java.util.List;
import java.util.Optional;

import dashakys.korob.ok.model.Role;
import dashakys.korob.ok.model.User;
import dashakys.korob.ok.model.UserRole;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends EntityRepository<UserRole> {
    List<UserRole> findAllByRole(Role role);
    List<UserRole> findAllByUser(User user);
    Optional<UserRole> findByUserAndRole(User user, Role role);
}
