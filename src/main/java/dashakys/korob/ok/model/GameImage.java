package dashakys.korob.ok.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class GameImage extends DatabaseEntity {

    @OneToOne(cascade = CascadeType.ALL)
    private Game game;
    @Column
    private String file;
}
