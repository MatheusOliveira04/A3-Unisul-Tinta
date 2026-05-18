package git.matheusoliveira04.api.store.services;

import git.matheusoliveira04.api.store.models.Surface;

import java.util.List;

public interface SurfaceService {

    Surface insert(Surface surface);

    List<Surface> findAll();

    Surface findById(Integer id);
}
