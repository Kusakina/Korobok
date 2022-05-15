package dashakys.korob.ok.service;

import dashakys.korob.ok.model.Profile;
import dashakys.korob.ok.repository.ProfileRepository;

public class SelectedProfileService extends AbstractEntityService<Profile, ProfileRepository> {

    private Profile selectedProfile;

    public SelectedProfileService(ProfileRepository repository) {
        super(repository);
    }

    public void select(Profile profile) { this.selectedProfile = profile; }
    public Profile getSelectedProfile() {
        return selectedProfile;
    }

}
