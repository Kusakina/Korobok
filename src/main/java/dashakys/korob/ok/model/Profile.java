package dashakys.korob.ok.model;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true, exclude = { "credentials" })
@ToString(callSuper = true, exclude = { "credentials" })
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profile extends DatabaseEntity {

    @Column
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(cascade = CascadeType.ALL)
    private Credentials credentials;
}
