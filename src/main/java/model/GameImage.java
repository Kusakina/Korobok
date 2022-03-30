package model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private String file;
    public GameImage (String file){
        this.file = file;
    }
}
