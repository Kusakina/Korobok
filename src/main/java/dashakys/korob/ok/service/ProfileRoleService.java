package dashakys.korob.ok.service;

import javax.transaction.Transactional;

import dashakys.korob.ok.model.ProfileRole;
import dashakys.korob.ok.repository.ProfileRoleRepository;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class ProfileRoleService extends AbstractEntityService<ProfileRole, ProfileRoleRepository> {
    public ProfileRoleService(ProfileRoleRepository repository) {
        super(repository);
    }
}
