package dashakys.korob.ok.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
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
}
