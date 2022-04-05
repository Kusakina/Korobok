package dashakys.korob.ok.service;

import lombok.NoArgsConstructor;

public class EntityServiceException extends RuntimeException {

    public EntityServiceException(String message) {
        super(message);
    }

    public EntityServiceException(Throwable cause) {
        super(cause);
    }
}
