package git.matheusoliveira04.api.store.models.dtos.requests;

import jakarta.validation.constraints.NotEmpty;

public record UserRequest(
        @NotEmpty
        String name,
        @NotEmpty
        String email,
        @NotEmpty
        String password,
        @NotEmpty
        String roles
) {
}
