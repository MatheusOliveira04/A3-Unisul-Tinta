package git.matheusoliveira04.api.store.models.dtos.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PaintHistoryRequest(

        @NotNull
        String email,

        @NotNull
        Integer surfaceId,

        @NotNull
        Integer paintingMethodId,

        @NotEmpty
        String result

) {
}
