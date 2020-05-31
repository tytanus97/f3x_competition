package f3x.competition.F3XCompetition.repository;

import f3x.competition.F3XCompetition.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Long> {
}
