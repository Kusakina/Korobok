package dashakys.korob.ok.service;

import dashakys.korob.ok.model.GameImage;
import org.springframework.data.jpa.repository.JpaRepository;

public class GameImageService extends AbstractEntityService<GameImage, JpaRepository<GameImage, Long>> {
        public GameImageService(JpaRepository<GameImage, Long> repository) {
            super(repository);
        }
}