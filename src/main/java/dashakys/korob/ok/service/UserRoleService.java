package dashakys.korob.ok.service;

import javax.transaction.Transactional;

import dashakys.korob.ok.model.UserRole;
import dashakys.korob.ok.repository.UserRoleRepository;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class UserRoleService extends AbstractEntityService<UserRole, UserRoleRepository> {
    public UserRoleService(UserRoleRepository repository) {
        super(repository);
    }
}
