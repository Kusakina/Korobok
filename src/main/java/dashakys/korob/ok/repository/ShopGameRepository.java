package dashakys.korob.ok.repository;

import java.util.List;
import java.util.Optional;

import dashakys.korob.ok.model.Game;
import dashakys.korob.ok.model.ShopGame;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopGameRepository extends EntityRepository<ShopGame> {

    Optional<ShopGame> findByGame(Game game);
    @Query("SELECT * FROM ShopGame sg " +
            "JOIN Game g on sg.game = g" +
            "WHERE LOWER(g.Name) like LOWER(concat('%', :searchTerm, '%'))"
    )

    List<ShopGame> search(@Param("searchTerm") String searchTerm);
    List<ShopGame> findAll();
}
