package dashakys.korob.ok.service;

import javax.transaction.Transactional;

import dashakys.korob.ok.model.*;
import dashakys.korob.ok.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class PurchaseService extends AbstractEntityService<Purchase, PurchaseRepository> {

    private final ProfileService profileService;
    private final PurchaseGameService purchaseGameService;

    public PurchaseService(PurchaseRepository repository,
                           ProfileService profileService,
                           PurchaseGameService purchaseGameService) {
        super(repository);
        this.profileService = profileService;
        this.purchaseGameService = purchaseGameService;
    }

    public List <Purchase> findAllByClient(Profile client) {
        try {
            return repository.findAllByClient(client);
        } catch (Exception e) {
            throw new EntityServiceException(e);
        }
    }
    public List <Purchase> findAllByManager(Profile admin) {
        try {
            return repository.findAllByManager(admin);
        } catch (Exception e) {
            throw new EntityServiceException(e);
        }
    }

    public void update(Status status, Purchase purchase){

        purchase.setStatus(status);
        this.save(purchase);

    }

    public void createOrder(
            List<OrderedGame> games,
            Profile profile
    ) {
        if (games.isEmpty()){
            throw new EntityServiceException("Короб пуст :(");
        }

        purchaseGameService.checkGamesCount(games);

        Purchase purchase = new Purchase(
                profile,
                profileService.findByLogin("admin").get()
        );

        save(purchase);

        purchaseGameService.addPurchasedGames(purchase, games);
    }

    public void createOrder(
            List<OrderedGame> games,
            Profile profile,
            int cost
    ) {
        if (games.isEmpty()){
            throw new EntityServiceException("Короб пуст :(");
        }

        purchaseGameService.checkGamesCount(games);

        Purchase purchase = new Purchase(
                profile,
                profileService.findByLogin("admin").get(),
                cost
        );

        save(purchase);

        purchaseGameService.addPurchasedGames(purchase, games);
    }
    public Optional<Purchase> findById(long id){
        return repository.findById(id);
    }

}
