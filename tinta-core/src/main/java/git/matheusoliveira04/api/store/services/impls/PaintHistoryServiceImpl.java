package git.matheusoliveira04.api.store.services.impls;

import git.matheusoliveira04.api.store.models.PaintHistory;
import git.matheusoliveira04.api.store.models.PaintingMethod;
import git.matheusoliveira04.api.store.repositories.PaintHistoryRepository;
import git.matheusoliveira04.api.store.services.PaintHistoryService;
import git.matheusoliveira04.api.store.services.excepitions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaintHistoryServiceImpl implements PaintHistoryService {

    @Autowired
    private PaintHistoryRepository repository;

    @Override
    public PaintHistory insert(PaintHistory paintHistory) {
        return repository.save(paintHistory);
    }

    @Override
    public List<PaintHistory> findAll() {
        List<PaintHistory> paintHistory = repository.findAll();
        if(paintHistory.isEmpty()) {
            throw new ObjectNotFoundException("History not found");
        }
        return paintHistory;
    }

    @Override
    public PaintHistory findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("History not found"));
    }
}
