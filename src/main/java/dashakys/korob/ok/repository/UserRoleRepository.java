package dashakys.korob.ok.repository;
import dashakys.korob.ok.model.UserRole;

public interface UserRoleRepository extends EntityRepository<UserRole> {
    UserRoleRepository findByRole (String role);
}
