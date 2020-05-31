package f3x.competition.F3XCompetition.repository;

import f3x.competition.F3XCompetition.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
}
