package dashakys.korob.ok.service;

import javax.transaction.Transactional;

import dashakys.korob.ok.model.Profile;
import dashakys.korob.ok.repository.ProfileRepository;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class ProfileService extends AbstractEntityService<Profile, ProfileRepository> {

    private Profile selectedProfile;

    public ProfileService(ProfileRepository profileRepository) {
        super(profileRepository);
    }

    public void select(Profile profile) { this.selectedProfile = profile; }
    public Profile getSelectedProfile() {
        return selectedProfile;
    }
}
