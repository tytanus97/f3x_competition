package f3x.competition.F3XCompetition.repository;

import f3x.competition.F3XCompetition.entity.Pilot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PilotRepository extends JpaRepository<Pilot,Long> {
    List<Pilot> getAllByPilotEmail(String pilotEmail);
}
