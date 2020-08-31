package f3x.competition.F3XCompetition.service;

import f3x.competition.F3XCompetition.entity.Flight;

import java.util.List;
import java.util.Optional;

public interface FlightService {
    List<Flight> findAll();
    Optional<Flight> findById(Long flightId);
    Flight saveFlight(Flight flight);
    void deleteFlight(Flight flight);
    void deleteFlightByFlightId(Long flightId);
}
