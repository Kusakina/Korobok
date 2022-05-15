package dashakys.korob.ok.service;

import dashakys.korob.ok.model.Credentials;
import dashakys.korob.ok.model.Profile;
import dashakys.korob.ok.model.Role;
import dashakys.korob.ok.repository.CredentialsRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Service
public class CredentialsService extends AbstractEntityService<Credentials, CredentialsRepository> {

    private final ProfileService profileService;

    public CredentialsService(CredentialsRepository repository,
                              ProfileService profileService) {
        super(repository);
        this.profileService = profileService;
    }

    public Optional<Credentials> findByLogin(String login) {
        try {
            return repository.findByLogin(login);
        } catch (Exception e) {
            throw new EntityServiceException(e);
        }
    }

    public Optional<Credentials> findByLoginAndPasswordHash(String login, long passwordHash) {
        try {
            return repository.findByLoginAndPasswordHash(
                    login, passwordHash
            );
        } catch (Exception e) {
            throw new EntityServiceException(e);
        }
    }

    public static void checkFields(String name, String login, String password) {
        if (name.isBlank()) {
            throw new EntityServiceException("Имя пользователя не может состоять только из пробельных символов");
        }
        if (login.isBlank()) {
            throw new EntityServiceException("Логин не может состоять только из пробельных символов");
        }

        if (password.isBlank()) {
            throw new EntityServiceException("Пароль не может состоять только из пробельных символов");
        }
    }

    public void checkLoginAlreadyExists(String login) {
        if (findByLogin(login).isPresent()) {
            throw new EntityServiceException(String.format("Выбранный логин уже используется: %s", login));
        }
    }

    public void register(String name, String login, String password, Role role) {
        checkFields(name, login, password);
        checkLoginAlreadyExists(login);

        Credentials credentials = new Credentials(login, password);
        Profile profile = new Profile(name, role, credentials);

        credentials.setProfile(profile);
        save(credentials);
        profileService.save(profile);
    }

    public void update(String name, String login, String password, Profile profile){
        checkFields(name, login, password);
        if (!login.equals(profile.getCredentials().getLogin())) {
            checkLoginAlreadyExists(login);
        }

        profile.setName(name);
        profile.getCredentials().setLogin(login);
        profile.getCredentials().setPassword(password);
        profileService.save(profile);
    }

    public Optional<Profile> authenticate(String login, String password) {
        try {
            Optional<Credentials> credentials = findByLoginAndPasswordHash(
                    login, Credentials.calculatePasswordHash(password)
            );

            return credentials.map(Credentials::getProfile);
        } catch (Exception e) {
            throw new EntityServiceException(e);
        }
    }
}
