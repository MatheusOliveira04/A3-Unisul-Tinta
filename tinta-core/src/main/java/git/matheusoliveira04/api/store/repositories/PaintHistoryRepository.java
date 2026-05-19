package git.matheusoliveira04.api.store.repositories;

import git.matheusoliveira04.api.store.models.PaintHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaintHistoryRepository extends JpaRepository<PaintHistory, Integer> {

}
