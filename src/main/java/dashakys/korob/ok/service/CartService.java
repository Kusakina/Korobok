package dashakys.korob.ok.service;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.VaadinSessionScope;
import dashakys.korob.ok.model.ShopGame;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@SpringComponent
@VaadinSessionScope
public class CartService {
    private final HashMap<ShopGame, Integer> cart;

    public CartService() {
        this.cart = new HashMap<>();
    }

    public void addToCart(ShopGame shopGame){
        int cartCount = cart.getOrDefault(shopGame, 0);
        if(cartCount == shopGame.getCount()){
            throw new EntityServiceException("Упс, в коробе уже максимальное количество экземпляров этой игры");
        }

        cart.put(shopGame, cartCount + 1);

    }

    public List<ShopGame> getGames() {
        return cart.entrySet().stream()
                .map(e -> {
                    var shopGame = e.getKey();
                    return new ShopGame(shopGame.getGame(), shopGame.getPrice(), e.getValue());
                }).collect(Collectors.toUnmodifiableList());
    }

    public int getTotalCost() {
        return cart.entrySet().stream()
                .mapToInt(e -> e.getKey().getPrice() * e.getValue())
                .sum();
    }
    public  List<Integer> getCount(){
        return cart.entrySet().stream()
                .map(e -> {
                    var count = e.getValue();
                    return count;
                }).collect(Collectors.toUnmodifiableList());
    }

    public int size() {
        return cart.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void remove(ShopGame key) {
        cart.remove(key);
    }
    public void removeAll(){
        cart.clear();
    }

    public void setCount(ShopGame shopGame, int n) {
        cart.put(shopGame, n);
    }
//    public void put(ShopGame key, Integer value) {
//        cart.put(key,value);
//    }

//    public boolean containsKey(ShopGame key) {
//        return cart.containsKey(key);
//    }
//    public void clear() {
//        cart.clear();
//    }
//    public Set<Map.Entry<ShopGame, Integer>> entrySet() {
//        return cart.entrySet();
//    }
}
