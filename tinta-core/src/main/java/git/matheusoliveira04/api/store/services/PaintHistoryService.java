package git.matheusoliveira04.api.store.services;

import git.matheusoliveira04.api.store.models.PaintHistory;

import java.util.List;
import java.util.UUID;

public interface PaintHistoryService {

    PaintHistory insert(PaintHistory paintHistory);

    List<PaintHistory> findAll();

    PaintHistory findById(Integer id);

    PaintHistory findByUserId(UUID id);
}