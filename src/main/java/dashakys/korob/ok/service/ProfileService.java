package dashakys.korob.ok.service;

import javax.transaction.Transactional;

import dashakys.korob.ok.model.Profile;
import dashakys.korob.ok.repository.ProfileRepository;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class ProfileService extends AbstractEntityService<Profile, ProfileRepository> {

    private Profile selectedUser;

    public ProfileService(ProfileRepository userRepository) {
        super(userRepository);
    }

    public void select(Profile user) { this.selectedUser = user; }
    public Profile getSelectedUser() {
        return selectedUser;
    }
}
