package f3x.competition.F3XCompetition.service;

import f3x.competition.F3XCompetition.entity.Flight;

import java.util.List;
import java.util.Optional;

public interface FlightService {
    List<Flight> getAll();
    Optional<Flight> getById(Long flightId);

    void saveFlight(Flight flight);
    void deleteFlight(Flight flight);

}
