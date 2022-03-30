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
    private String name;
    @Column
    private String login;
    @Column
    private String password;

    public User (String name) {
        this.name = name;
    }
}
