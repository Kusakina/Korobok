package repository;
import model.User;
import model.UserRole;

public interface UserRoleRepository extends EntityRepository<UserRole> {
    UserRoleRepository findByRole (String role);
}
