package dashakys.korob.ok.model;

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

public class ShopGame {
    @OneToOne
    private Game game;
    @Column
    private int price;
    @Column
    private int count;
    public ShopGame(int price, int count){
        this.count = count;
        this.price = price;
    }

}
