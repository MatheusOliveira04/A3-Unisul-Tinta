package git.matheusoliveira04.api.store.models.dtos.responses;

import java.io.Serializable;
import java.util.UUID;

public record LoginResponse(
        UUID userId,
        String token
) implements Serializable {
}
