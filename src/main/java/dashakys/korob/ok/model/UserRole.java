package dashakys.korob.ok.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Data
@NoArgsConstructor
public class UserRole extends DatabaseEntity{

    @ManyToOne()
    private User user;
    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    public UserRole(Role role, User user) {
        this.role = role;
        this.user = user;
    }

}
