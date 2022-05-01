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
