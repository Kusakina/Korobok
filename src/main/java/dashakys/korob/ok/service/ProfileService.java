package dashakys.korob.ok.service;

import javax.transaction.Transactional;

import dashakys.korob.ok.model.Credentials;
import dashakys.korob.ok.model.Profile;
import dashakys.korob.ok.model.Role;
import dashakys.korob.ok.repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ProfileService extends AbstractEntityService<Profile, ProfileRepository> {

    private final CredentialsService credentialsService;
    private Profile selectedProfile;

    public ProfileService(ProfileRepository repository,
                          CredentialsService credentialsService) {
        super(repository);
        this.credentialsService = credentialsService;
    }

    public void select(Profile profile) { this.selectedProfile = profile; }
    public Profile getSelectedProfile() {
        return selectedProfile;
    }

    public Optional<Profile> findByCredentials(Credentials credentials) {
        try {
            return repository.findByCredentials(credentials);
        } catch (Exception e) {
            throw new EntityServiceException(e);
        }
    }

    public Optional<Profile> findByLogin(String login) {
        try {
           return findByCredentials((credentialsService.findByLogin(login)).get());
        } catch (Exception e) {
            throw new EntityServiceException(e);
        }
    }

    public List<Profile> findAllByRole(Role role) {
        try {
            return repository.findAllByRole(role);
        } catch (Exception e) {
            throw new EntityServiceException(e);
        }
    }
}
