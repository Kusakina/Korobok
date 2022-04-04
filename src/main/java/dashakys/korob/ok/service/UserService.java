package dashakys.korob.ok.service;

import javax.transaction.Transactional;

import dashakys.korob.ok.model.User;
import dashakys.korob.ok.repository.UserRepository;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class UserService extends AbstractEntityService<User, UserRepository> {

    private User selectedUser;

    public UserService (UserRepository userRepository) {
        super(userRepository);
    }

    public void select(User user) { this.selectedUser = user; }
    public User getSelectedUser() {
        return selectedUser;
    }
}
