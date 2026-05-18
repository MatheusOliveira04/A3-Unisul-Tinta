package git.matheusoliveira04.api.store.models.dtos.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record SurfaceRequest(

        @NotEmpty
        String description,

        @NotNull
        Integer surfaceTypeId

) {
}
