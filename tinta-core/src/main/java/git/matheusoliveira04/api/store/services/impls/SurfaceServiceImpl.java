package git.matheusoliveira04.api.store.services.impls;


import git.matheusoliveira04.api.store.models.Surface;
import git.matheusoliveira04.api.store.models.SurfaceType;
import git.matheusoliveira04.api.store.repositories.SurfaceRepository;
import git.matheusoliveira04.api.store.services.SurfaceService;
import git.matheusoliveira04.api.store.services.excepitions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurfaceServiceImpl implements SurfaceService {

    @Autowired
    private SurfaceRepository repository;

    @Override
    public Surface insert(Surface surface) {
        return repository.save(surface);
    }

    @Override
    public List<Surface> findAll() {
        List<Surface> surface = repository.findAll();
        if(surface.isEmpty()) {
            throw new ObjectNotFoundException("Surface not found");
        }
        return surface;
    }

    @Override
    public Surface findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Surface not found"));
    }
}
