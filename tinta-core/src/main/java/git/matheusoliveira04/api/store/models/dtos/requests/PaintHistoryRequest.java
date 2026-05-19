package git.matheusoliveira04.api.store.models.dtos.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PaintHistoryRequest(

        @NotNull
        UUID userId,

        @NotNull
        Integer surfaceId,

        @NotNull
        Integer paintingMethodId,

        @NotEmpty
        String result

) {
}
