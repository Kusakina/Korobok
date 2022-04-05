package dashakys.korob.ok.service;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EntityServiceException extends RuntimeException {

    public EntityServiceException(String message) {
        super(message);
    }
}
