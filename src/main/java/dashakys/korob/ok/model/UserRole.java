package dashakys.korob.ok.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class UserRole extends DatabaseEntity{

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private User user;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    public UserRole(Role role, User user) {
        this.role = role;
        this.user = user;
    }

}
