package dashakys.korob.ok.service;

import javax.transaction.Transactional;

import dashakys.korob.ok.model.Purchase;
import dashakys.korob.ok.model.PurchaseGame;
import dashakys.korob.ok.model.ShopGame;
import dashakys.korob.ok.repository.PurchaseGameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class PurchaseGameService extends AbstractEntityService<PurchaseGame, PurchaseGameRepository> {

    public PurchaseGameService(PurchaseGameRepository repository) {
        super(repository);
    }

    public List<PurchaseGame> findAllByPurchase(Purchase purchase) {
        try {
            return repository.findAllByPurchase(purchase);
        } catch (Exception e) {
            throw new EntityServiceException(e);
        }
    }

    public void addPurchaseGame(Purchase purchase, ShopGame shopGame, int count) {
        if (count > shopGame.getCount()) {
            throw new EntityServiceException(String.format("Запрошенное количество %d больше имеющегося %d", count, shopGame.getCount()));
        }

        if (count > 0) {
            PurchaseGame purchaseGame = new PurchaseGame(purchase, shopGame, count);
            save(purchaseGame);
        }
    }
    public void addPurchaseGame(PurchaseService purchaseService, Purchase purchase, List <ShopGame> list, ShopGameService shopGameService) {
        if (list.size() == 0)throw new EntityServiceException("В коробе пусто :(");
        String error ="";
        for (int i=0; i<list.size();++i){
            if (list.get(i).getCount() > shopGameService.findAllByGame(list.get(i).getGame().getName()).get(0).getCount())
            {
                error+= String.format("Количество выбранного вами товара %s = %d, но на складе имеется только %d \n",
                        list.get(i).getGameName(),
                        list.get(i).getCount(),
                        shopGameService.findAllByGame(list.get(i).getGame().getName()).get(0).getCount());
                purchaseService.remove(purchase);
            }
        }
        if (error == "") throw new EntityServiceException(error);



        for(int i =0; i< list.size();++i){
            PurchaseGame purchaseGame = new PurchaseGame(purchase, list.get(i), list.get(i).getCount());
            save(purchaseGame);
        }

    }
}
