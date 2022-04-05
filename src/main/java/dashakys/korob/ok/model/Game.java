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
public class Game extends DatabaseEntity {
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String category;
    @Column
    private int maxPlayers;
    @Column
    private int minPlayers;

    @OneToOne(cascade = CascadeType.ALL)
    private ShopGame shopGame;
}
