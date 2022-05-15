package dashakys.korob.ok.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Cart {
    private HashMap<ShopGame, Integer> cart;

    public Cart() {
        this.cart = new HashMap<>();
    }

    public HashMap get(){
        return this.cart;
    }

    public Integer get(Object key) {
        return cart.get(key);
    }
    public Integer remove(Object key) {
        return remove(key);
    }
    public void put(ShopGame key, Integer value) {
        cart.put(key,value);
    }
    public boolean isEmpty() {
        return cart.isEmpty();
    }
    public boolean containsKey(Object key) {
        return cart.containsKey(key);
    }
    public void clear() {
        cart.clear();
    }
    public Set<Map.Entry<ShopGame, Integer>> entrySet() {
        return cart.entrySet();
    }
}
