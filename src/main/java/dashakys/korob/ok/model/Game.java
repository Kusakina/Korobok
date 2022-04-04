package dashakys.korob.ok.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
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
    public Game (String name, String description,
                 String category,
                 int maxPlayers,
                 int minPlayers){
        this.category = category;
        this.description = description;
        this.maxPlayers = maxPlayers;
        this.minPlayers = minPlayers;
        this.name = name;
    }

}
