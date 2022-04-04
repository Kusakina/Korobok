package model;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.criteria.CriteriaBuilder;

@Getter
@Setter
@Entity
@Data
@NoArgsConstructor

public class OrderGame extends DatabaseEntity{
    @ManyToOne
    private Order order;
    @OneToOne()
    private Game game;
    @OneToOne()
    private ShopGame shopGame;
    @Column
    private int count;

    public OrderGame (int count) {
        this.count = count;
    }
}
