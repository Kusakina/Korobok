package model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
@Getter
@Setter
@Entity
@Data
@NoArgsConstructor
public class GameImage {
    @OneToOne
    private Game game;
    @Column
    private String file;
    public GameImage (Game game, String file){
        this.game = game;
        this.file = file;
    }
}
