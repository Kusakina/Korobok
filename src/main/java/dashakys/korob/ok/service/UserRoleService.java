package dashakys.korob.ok.service;

import dashakys.korob.ok.model.Role;
import dashakys.korob.ok.model.User;
import dashakys.korob.ok.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public class UserRoleService extends AbstractEntityService<UserRole, JpaRepository<UserRole, Long>> {
    private UserRole selectedUserRole;
    public UserRoleService(JpaRepository<UserRole, Long> repository) {
        super(repository);
    }
    public void select(Role role, User user) {
        selectedUserRole.setRole(role);
        selectedUserRole.setUser(user);
    }
    public void getUser() {
        selectedUserRole.getUser();
    }
    public void getRole() {
        selectedUserRole.getRole();
    }
}
