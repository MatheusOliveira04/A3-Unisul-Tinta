package git.matheusoliveira04.api.store.services;

import git.matheusoliveira04.api.store.models.SurfaceType;

import java.util.List;

public interface SurfaceTypeService {

    SurfaceType insert(SurfaceType surfaceType);

    List<SurfaceType> findAll();

    SurfaceType findById(Integer id);
}
