package dashakys.korob.ok.service;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.VaadinSessionScope;
import dashakys.korob.ok.model.Credentials;
import dashakys.korob.ok.model.Profile;
import dashakys.korob.ok.model.Role;
import dashakys.korob.ok.repository.ProfileRepository;

import java.util.Optional;

@SpringComponent
@VaadinSessionScope
public class SelectedProfileService {

    private final CredentialsService credentialsService;

    private Profile selectedProfile;

    public SelectedProfileService(CredentialsService credentialsService) {
        this.credentialsService = credentialsService;
        this.selectedProfile = null;
    }

    public void select(Profile profile) { this.selectedProfile = profile; }
    public Profile getSelectedProfile() {
        return selectedProfile;
    }
    public Profile setSelectedProfile() {
        return this.selectedProfile = null;
    }

    public void update(String name, String login, String password){
        credentialsService.update(name, login, password, getSelectedProfile());
    }

    public void signIn(String login, String password) {
        var profile = credentialsService.authenticate(login, password).orElseThrow(
                () -> new EntityServiceException("Некорректные логин/пароль")
        );
        select(profile);
    }

    public void signUp(String name, String login, String password) {
        credentialsService.register(name, login, password, Role.USER);
        signIn(login, password);
    }
}
