package dashakys.korob.ok.service;

import dashakys.korob.ok.model.Profile;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CartMapService {
    private Map<Profile, CartService> cartGameService;
    CartMapService(){
        this.cartGameService = new HashMap<>();
    }
//    public Map<Profile, Cart> get(){
//        return this.cartGameService;
//    }
   /* @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public Cart get(Object key) {
        return null;
    }

    @Override
    public Cart put(Profile key, Cart value) {
        return null;
    }

    @Override
    public Cart remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends Profile, ? extends Cart> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<Profile> keySet() {
        return null;
    }

    @Override
    public Collection<Cart> values() {
        return null;
    }

    @Override
    public Set<Map.Entry<Profile, Cart>> entrySet() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }*/
}
