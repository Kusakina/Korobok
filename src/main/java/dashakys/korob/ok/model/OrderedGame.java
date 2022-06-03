package dashakys.korob.ok.model;

import lombok.AllArgsConstructor;
import lombok.Data;


// NOT DATABASE TABLE
@Data
@AllArgsConstructor
public class OrderedGame {

    private final ShopGame shopGame;
    private int count;

    public String getGameName() {
        return shopGame.getGameName();
    }

    public int getPrice() {
        return shopGame.getPrice();
    }
}
