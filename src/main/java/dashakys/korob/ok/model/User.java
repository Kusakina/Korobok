package dashakys.korob.ok.model;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class User extends DatabaseEntity {

    @Column
    private String name;

    public User (String name) {
        this.name = name;
    }
}
