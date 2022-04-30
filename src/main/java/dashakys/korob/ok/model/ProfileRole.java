package dashakys.korob.ok.model;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileRole extends DatabaseEntity{

    @ManyToOne
    private Profile profile;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;
}
