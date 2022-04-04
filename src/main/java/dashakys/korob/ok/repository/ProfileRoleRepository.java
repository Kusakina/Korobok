package dashakys.korob.ok.repository;
import java.util.List;
import java.util.Optional;

import dashakys.korob.ok.model.Role;
import dashakys.korob.ok.model.Profile;
import dashakys.korob.ok.model.ProfileRole;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRoleRepository extends EntityRepository<ProfileRole> {
    List<ProfileRole> findAllByRole(Role role);
    List<ProfileRole> findAllByProfile(Profile profile);
    Optional<ProfileRole> findByProfileAndRole(Profile profile, Role role);
}
