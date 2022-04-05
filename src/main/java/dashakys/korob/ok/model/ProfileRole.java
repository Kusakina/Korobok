package dashakys.korob.ok.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class ProfileRole extends DatabaseEntity{

    @ManyToOne
    private Profile profile;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;
}
