package dashakys.korob.ok.service;

import dashakys.korob.ok.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import dashakys.korob.ok.repository.UserRepository;

public class UserService extends AbstractEntityService<User, JpaRepository<User, Long>> {

    private User selectedUser;

    public UserService (UserRepository userRepository) {
        super(userRepository);
    }

    public void select(String login, String password ) {
        selectedUser.setLogin(login);
        selectedUser.setPassword(password);
    }

    public User selectedUser() {
        return selectedUser;
    }

    public User getSelectedUser() {
        return selectedUser.getValue();
    }

}
