package dashakys.korob.ok.model;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profile extends DatabaseEntity {

    @Column
    private String name;
}
