package dashakys.korob.ok.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.*;

@EqualsAndHashCode(callSuper = true, exclude = { "shopGame" })
@ToString(callSuper = true, exclude = { "shopGame" })
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

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

    public Game(String name) {
        this.name = name;
    }
    public Game(String name,
                String description,
                String category,
                Integer minPlayer,
                Integer maxPlayer) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.maxPlayers = maxPlayer;
        this.minPlayers = minPlayer;
    }
}
