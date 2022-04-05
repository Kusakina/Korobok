package dashakys.korob.ok.model;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, exclude = { "credentials" })
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profile extends DatabaseEntity {

    @Column
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private Credentials credentials;
}
