package f3x.competition.F3XCompetition.repository;

import f3x.competition.F3XCompetition.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {
}
