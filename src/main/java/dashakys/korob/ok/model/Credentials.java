package dashakys.korob.ok.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class Credentials extends DatabaseEntity {

    @OneToOne(mappedBy = "credentials")
    private Profile profile;

    @Column
    private String login;

    @Column
    private long passwordHash;

    public static long calculatePasswordHash(String password) {
        return password.hashCode();
    }

    public Credentials(String login, long passwordHash) {
        this.login = login;
        this.passwordHash = passwordHash;
    }

    @Override
    public String toString() {
        return "Credentials{" +
                "profile=" + profile.getName() +
                ", login='" + login + '\'' +
                ", passwordHash=" + passwordHash +
                '}';
    }
}
