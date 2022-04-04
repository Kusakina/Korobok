package dashakys.korob.ok.service;

import javax.transaction.Transactional;

import dashakys.korob.ok.model.GameImage;
import dashakys.korob.ok.repository.GameImageRepository;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class GameImageService extends AbstractEntityService<GameImage, GameImageRepository> {
        public GameImageService(GameImageRepository repository) {
            super(repository);
        }
}
