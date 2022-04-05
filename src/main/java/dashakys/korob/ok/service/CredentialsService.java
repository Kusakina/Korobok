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

    private void register(String name, String login, String password) {
        if (name.isBlank()) {
            throw new EntityServiceException("Имя пользователя не может состоять только из пробельных символов");
        }

        if (login.isBlank()) {
            throw new EntityServiceException("Логин не может состоять только из пробельных символов");
        }

        if (password.isBlank()) {
            throw new EntityServiceException("Пароль не может состоять только из пробельных символов");
        }

        if (repository.findByLogin(login).isPresent()) {
            throw new EntityServiceException(String.format("Выбранный логин уже используется: %s", login));
        }

        long passwordHash = Credentials.calculatePasswordHash(password);
        Credentials credentials = new Credentials(login, passwordHash);

        Profile profile = new Profile(name, credentials);

        credentials.setProfile(profile);
        save(credentials);

        profileService.save(profile);
    }

    private Optional<Profile> authenticate(String login, String password) {
        try {
            Optional<Credentials> credentials = repository.findByLoginAndPasswordHash(
                    login, Credentials.calculatePasswordHash(password)
            );

            return credentials.map(Credentials::getProfile);
        } catch (Exception e) {
            throw new EntityServiceException(e);
        }
    }

    public void signIn(String login, String password) {
        authenticate(login, password).ifPresentOrElse(
                profileService::select,
                () -> { throw new EntityServiceException("Некорректные логин/пароль"); }
        );
    }

    public void signUp(String name, String login, String password) {
        register(name, login, password);
        signIn(login, password);
    }
}
