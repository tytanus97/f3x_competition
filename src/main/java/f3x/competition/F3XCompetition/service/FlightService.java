package f3x.competition.F3XCompetition.service;

import f3x.competition.F3XCompetition.entity.Event;
import f3x.competition.F3XCompetition.entity.Flight;
import f3x.competition.F3XCompetition.entity.Pilot;
import f3x.competition.F3XCompetition.entity.Round;

import java.util.List;
import java.util.Optional;

public interface FlightService {
    List<Flight> findAll();
    Optional<Flight> findById(Long flightId);
    List<Flight> findByEventAndPilot(Event event, Pilot pilot);
    List<Flight> findByEventAndPilot(Long eventId, Long pilotId);
    Flight saveFlight(Flight flight);
    void deleteFlight(Flight flight);
    void deleteFlightByFlightId(Long flightId);
    List<Flight> findByEventAndPilotAndRound(Long eventId, Long pilotId, Long roundId);
}
