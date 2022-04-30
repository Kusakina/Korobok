package dashakys.korob.ok.service;

import javax.transaction.Transactional;

import dashakys.korob.ok.model.Profile;
import dashakys.korob.ok.model.ProfileRole;
import dashakys.korob.ok.repository.ProfileRoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Transactional
@Service
public class ProfileRoleService extends AbstractEntityService<ProfileRole, ProfileRoleRepository> {
    public ProfileRoleService(ProfileRoleRepository repository) {
        super(repository);
    }
    public Optional<ProfileRole> findByProfile(Profile profile) {
        try {
            return repository.findByProfile(profile);
        } catch (Exception e) {
            throw new EntityServiceException(e);
        }
    }
}
