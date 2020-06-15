package f3x.competition.F3XCompetition.repository;

import f3x.competition.F3XCompetition.entity.Event;
import f3x.competition.F3XCompetition.entity.Flight;
import f3x.competition.F3XCompetition.entity.Pilot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Long> {

    List<Flight> findAllByEventAndPilot(Event event, Pilot pilot);
    void deleteFlightByFlightId(Long flightId);
    List<Flight> findAllByEvent_EventIdAndPilot_PilotId(Long eventId,Long pilotId);
    List<Flight> findAllByEvent_EventIdAndPilot_PilotIdAndRound_RoundId(Long eventId,Long pilotId,Long roundId);
}
