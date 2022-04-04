package dashakys.korob.ok.model;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class Credentials extends DatabaseEntity {

    @OneToOne(cascade = CascadeType.ALL)
    private Profile profile;

    @Column
    private String login;

    @Column
    private long passwordHash;

    public static long calculatePasswordHash(String password) {
        return password.hashCode();
    }
}
