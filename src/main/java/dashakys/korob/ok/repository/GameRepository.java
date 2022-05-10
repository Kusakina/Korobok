package dashakys.korob.ok.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import dashakys.korob.ok.model.Game;
import dashakys.korob.ok.model.Profile;
import dashakys.korob.ok.model.ShopGame;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends EntityRepository<Game>  {
    Optional<Game> findByName(String name);
    List<Game> findAllByCategory(String category);
    List<Game> findAllByMaxPlayers(int maxPlayers);
    List<Game> findAllByMinPlayers(int minPlayers);

    @Query("SELECT sg.game FROM ShopGame sg " +
            "JOIN PurchaseGame pg on sg = pg.shopGame " +
            "JOIN Purchase p on pg.purchase = p " +
            "WHERE p.client = :profile"
    )
    List<Game> findAllPurchasedByProfile(@Param("profile") Profile profile);

    @Query("SELECT DISTINCT sg.category FROM Game sg ")
    List<String> searchCategory ();

    Set<Game> findByCategoryIn(Set<String> categories);
}
