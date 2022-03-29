package model;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class Credentials extends DatabaseEntity {

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @Column
    private String login;

    @Column
    private long passwordHash;

    public Credentials(User user) {
        this.user = user;
    }

    public static long calculatePasswordHash(String password) {
        return password.hashCode();
    }
}
