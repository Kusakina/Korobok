package dashakys.korob.ok.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseGame extends DatabaseEntity{

    @ManyToOne
    private Purchase purchase;

    @ManyToOne
    private ShopGame shopGame;

    @Column
    private int count;

    public int getCost() {
        return shopGame.getPrice() * count;
    }
}
