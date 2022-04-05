package dashakys.korob.ok.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
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
}
