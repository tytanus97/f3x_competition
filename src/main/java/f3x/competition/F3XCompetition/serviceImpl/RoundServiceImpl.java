package f3x.competition.F3XCompetition.serviceImpl;

import f3x.competition.F3XCompetition.entity.Flight;
import f3x.competition.F3XCompetition.entity.Round;
import f3x.competition.F3XCompetition.repository.FlightRepository;
import f3x.competition.F3XCompetition.repository.RoundRepository;
import f3x.competition.F3XCompetition.service.RoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RoundServiceImpl  implements RoundService {

    private final RoundRepository roundRepository;
    private final FlightRepository flightRepository;

    @Autowired
    public RoundServiceImpl(RoundRepository roundRepository, FlightRepository flightRepository) {
        this.roundRepository = roundRepository;
        this.flightRepository = flightRepository;
    }

    @Override
    @Transactional
    public List<Round> getAll() {
        return this.roundRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Round> getById(Long roundId) {
        return this.roundRepository.findById(roundId);
    }

    @Override
    @Transactional
    public List<Flight> getRoundFlights(Round round) {
        return round.getRoundFlights();
    }

    @Override
    @Transactional
    public void addFlightToRound(Round round, Flight flight) {
        round.addFlight(flight);
        this.roundRepository.save(round);
        this.flightRepository.save(flight);
    }

    @Override
    @Transactional
    public void removeFlightFromRound(Round round, Flight flight) {
            round.removeFlight(flight);
        this.roundRepository.save(round);
        this.flightRepository.save(flight);
    }
}
