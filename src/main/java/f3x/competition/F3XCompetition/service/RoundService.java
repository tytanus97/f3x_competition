package f3x.competition.F3XCompetition.service;

import f3x.competition.F3XCompetition.entity.Flight;
import f3x.competition.F3XCompetition.entity.Round;

import java.util.List;
import java.util.Optional;

public interface RoundService {
    List<Round> findAll();
    Optional<Round> findById(Long id);
    Round saveRound(Round round);
    List<Flight> findRoundFlights(Round round);
    Flight addFlightToRound(Round round,Flight flight);
    void removeFlightFromRound(Round round,Flight flight);
    void removeRound(Round round);
}
