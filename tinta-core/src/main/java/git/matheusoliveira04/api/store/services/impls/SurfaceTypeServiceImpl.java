package git.matheusoliveira04.api.store.services.impls;

import git.matheusoliveira04.api.store.models.SurfaceType;
import git.matheusoliveira04.api.store.repositories.SurfaceTypeRepository;
import git.matheusoliveira04.api.store.services.SurfaceTypeService;
import git.matheusoliveira04.api.store.services.excepitions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurfaceTypeServiceImpl implements SurfaceTypeService {

    @Autowired
    private SurfaceTypeRepository repository;

    @Override
    public SurfaceType insert(SurfaceType surfaceType) {
        return repository.save(surfaceType);
    }

    @Override
    public List<SurfaceType> findAll() {
        List<SurfaceType> surfaceTypes = repository.findAll();
        if(surfaceTypes.isEmpty()) {
            throw new ObjectNotFoundException("SurfaceType not found");
        }
        return surfaceTypes;
    }

    @Override
    public SurfaceType findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("SurfaceType not found"));
    }
}
