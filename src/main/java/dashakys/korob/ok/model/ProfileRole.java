package dashakys.korob.ok.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class ProfileRole extends DatabaseEntity{

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Profile profile;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;
}
