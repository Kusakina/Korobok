package model;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Data
@NoArgsConstructor
public class Order extends DatabaseEntity {
    @ManyToOne()
    private User user;
    @ManyToOne()
    private User admin;
    @Column
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column
    private int cost;

    public Order (int cost) {
        this.cost = cost;
    }
}
