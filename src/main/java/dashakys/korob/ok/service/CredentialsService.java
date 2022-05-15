package dashakys.korob.ok.service;

import dashakys.korob.ok.model.Credentials;
import dashakys.korob.ok.model.Profile;
import dashakys.korob.ok.model.Role;
import dashakys.korob.ok.repository.CredentialsRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.Stream;

@Transactional
@Service
public class CredentialsService extends AbstractEntityService<Credentials, CredentialsRepository> {

    private final ProfileService profileService;
    //private final SelectedProfileService selectedProfileService;

    public CredentialsService(CredentialsRepository repository,
                              ProfileService profileService, SelectedProfileService selectedProfileService) {
        super(repository);
        this.profileService = profileService;
        //this.selectedProfileService = selectedProfileService;
    }

    public Optional<Credentials> findByLogin(String login) {
        try {
            return repository.findByLogin(login);
        } catch (Exception e) {
            throw new EntityServiceException(e);
        }
    }
    public void setProfile(String name, String login, String password, Profile profile){
        if (name.isBlank()) {
            throw new EntityServiceException("Имя пользователя не может состоять только из пробельных символов");
        }
        if (login.isBlank()) {
            throw new EntityServiceException("Логин не может состоять только из пробельных символов");
        }

        if (password.isBlank()) {
            throw new EntityServiceException("Пароль не может состоять только из пробельных символов");
        }
        if (repository.findByLogin(login).isPresent() && (login!= profile.getCredentials().getLogin()) ) {
            throw new EntityServiceException(String.format("Выбранный логин уже используется: %s", login));
        }
        profile.setCredentials(new Credentials(login,password.hashCode()));
        profile.setName(name);
        profileService.save(profile);
    }

    public void register(String name, String login, String password, Role role) {
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

        Profile profile = new Profile(name, role, credentials);

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

    public Optional signIn(String login, String password) {
        Profile _value;
        authenticate(login, password).ifPresentOrElse(
                (value) ->
                {_value = new Profile(value.getName(),value.getRole(),value.getCredentials());},
                () -> { throw new EntityServiceException("Некорректные логин/пароль"); }
        );
        return _value;
    }

    public void signUp(String name, String login, String password) {
        register(name, login, password, Role.USER);
        signIn(login, password);
    }
}
