package dashakys.korob.ok.service;

import javax.transaction.Transactional;

import dashakys.korob.ok.model.Credentials;
import dashakys.korob.ok.model.Profile;
import dashakys.korob.ok.model.Role;
import dashakys.korob.ok.repository.ProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ProfileService extends AbstractEntityService<Profile, ProfileRepository> {


    public ProfileService(ProfileRepository repository) {
        super(repository);
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
            // map: Optional<A>, F(A) -> B;
            // Optional<A>.map(F) = [A -> F(A) = B] -> Optional<B>
//            return credentialsService.findByLogin(login)
//                  .map(this::findByCredentials);

            // flatMap: Optional<A>, F(A) -> Optional<B>;
            // Optional<A>.flatMap(B) = [A -> F(A) = Opt<B>] = Opt<B>
//            return credentialsService.findByLogin(login)
//                    .flatMap(this::findByCredentials);

            return repository.findByLogin(login);
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
