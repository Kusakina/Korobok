package dashakys.korob.ok.model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class Purchase extends DatabaseEntity {

    @ManyToOne
    private Profile client;

    @ManyToOne
    private Profile manager;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column
    private int cost;

    public Purchase(Profile client) {
        this(client, null);
    }

    public Purchase(Profile client, Profile manager) {
        this.client = client;
        this.manager = manager;
        this.status = Status.OPEN;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "client=" + client.getName() +
                ", manager=" + manager.getName() +
                ", status=" + status +
                ", cost=" + cost +
                '}';
    }
}
