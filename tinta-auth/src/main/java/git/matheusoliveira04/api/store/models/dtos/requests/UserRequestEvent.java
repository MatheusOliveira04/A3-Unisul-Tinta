package git.matheusoliveira04.api.store.models.dtos.requests;


import java.io.Serializable;

public record UserRequestEvent(
        String email
) implements Serializable {
}
