package dashakys.korob.ok.service;

import java.util.Optional;

import javax.transaction.Transactional;

import dashakys.korob.ok.model.Credentials;
import dashakys.korob.ok.model.Profile;
import dashakys.korob.ok.repository.CredentialsRepository;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class CredentialsService extends AbstractEntityService<Credentials, CredentialsRepository> {

    public CredentialsService(CredentialsRepository repository) {
        super(repository);
    }

    public Optional<Profile> authenticate(String login, String password) {
        try {
            Optional<Credentials> credentials = repository.findByLoginAndPasswordHash(
                    login, Credentials.calculatePasswordHash(password)
            );

            return credentials.map(Credentials::getProfile);
        } catch (Exception e) {
            throw new EntityServiceException();
        }
    }
}
