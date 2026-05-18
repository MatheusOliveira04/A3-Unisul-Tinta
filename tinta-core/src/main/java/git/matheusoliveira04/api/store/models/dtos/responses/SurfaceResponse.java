package git.matheusoliveira04.api.store.models.dtos.responses;

public record SurfaceResponse(
        Integer id,
        String description,
        Integer surfaceTypeId,
        String surfaceType

) {
}
