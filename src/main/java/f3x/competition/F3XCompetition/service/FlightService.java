package f3x.competition.F3XCompetition.service;

import f3x.competition.F3XCompetition.entity.Event;
import f3x.competition.F3XCompetition.entity.Flight;
import f3x.competition.F3XCompetition.entity.Pilot;
import f3x.competition.F3XCompetition.entity.Round;

import java.util.List;
import java.util.Optional;

public interface FlightService {
    List<Flight> getAll();
    Optional<Flight> getById(Long flightId);
    List<Flight> getByEventAndPilot(Event event, Pilot pilot);
    List<Flight> getByEventAndPilot(Long eventId, Long pilotId);
    void saveFlight(Flight flight);
    void deleteFlight(Flight flight);
    List<Flight> getByEventAndPilotAndRound(Long eventId, Long pilotId, Long roundId);
}
