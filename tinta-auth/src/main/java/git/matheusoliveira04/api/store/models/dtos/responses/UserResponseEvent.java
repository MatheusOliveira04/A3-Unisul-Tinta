package git.matheusoliveira04.api.store.models.dtos.responses;

import java.io.Serializable;
import java.util.UUID;

public record UserResponseEvent(
        UUID id,
        String name,
        String email
) implements Serializable {
}
