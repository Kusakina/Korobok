package dashakys.korob.ok.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopGame extends DatabaseEntity {
    @OneToOne(mappedBy = "shopGame")
    private Game game;
    @Column
    private int price;
    @Column
    private int count;

    public ShopGame(ShopGame shopGame){
        this.game = shopGame.game;
        this.count = 1;
        this.price = shopGame.price;
    }

    @Override
    public String toString() {
        return "ShopGame{" +
                "game=" + game.getName() +
                ", price=" + price +
                ", count=" + count +
                '}';
    }

    public String getGameName() {
        return getGame().getName();
    }
}
