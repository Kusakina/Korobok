package model;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor

public class User extends DatabaseEntity{
    @Column
    private int userId;
    @Column
    private String name;
    @Column
    private String login;
    @Column
    private String password;

    public User (int userId, String name, String login, String password) {
        this.userId = userId;
        this.name = name;
        this.login =login;

    }
}
