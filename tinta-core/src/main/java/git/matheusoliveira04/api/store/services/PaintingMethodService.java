package git.matheusoliveira04.api.store.services;

import git.matheusoliveira04.api.store.models.PaintingMethod;

import java.util.List;

public interface PaintingMethodService {

    PaintingMethod insert(PaintingMethod paintingMethod);

    List<PaintingMethod> findALl();

    PaintingMethod findById(Integer id);
}
