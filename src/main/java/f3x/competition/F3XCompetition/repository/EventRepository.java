package f3x.competition.F3XCompetition.repository;

import f3x.competition.F3XCompetition.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {

    @Query(value = "SELECT e from Event e where e.eventStatus = true")
    List<Event> findAllByStatusTrue();
}
