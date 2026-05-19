package git.matheusoliveira04.api.store.models.dtos.responses;

import java.util.UUID;

public record PaintHistoryResponse(
        Integer id,
        String result,
        UUID userId,
        String surface,
        String paintingMethod

) {
}
