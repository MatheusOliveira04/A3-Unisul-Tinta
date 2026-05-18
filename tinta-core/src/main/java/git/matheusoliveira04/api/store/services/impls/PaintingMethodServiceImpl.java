package git.matheusoliveira04.api.store.services.impls;

import git.matheusoliveira04.api.store.models.PaintingMethod;
import git.matheusoliveira04.api.store.models.Surface;
import git.matheusoliveira04.api.store.repositories.PaintingMethodRepository;
import git.matheusoliveira04.api.store.services.PaintingMethodService;
import git.matheusoliveira04.api.store.services.excepitions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaintingMethodServiceImpl implements PaintingMethodService {

    @Autowired
    private PaintingMethodRepository repository;

    @Override
    public PaintingMethod insert(PaintingMethod paintingMethod) {
        return repository.save(paintingMethod);
    }

    @Override
    public List<PaintingMethod> findALl() {
        List<PaintingMethod> paintingMethods = repository.findAll();
        if(paintingMethods.isEmpty()) {
            throw new ObjectNotFoundException("Surface not found");
        }
        return paintingMethods;
    }

    @Override
    public PaintingMethod findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("PaintingMethod not found"));
    }
}
