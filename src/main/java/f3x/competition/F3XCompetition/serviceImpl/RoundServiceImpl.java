package f3x.competition.F3XCompetition.serviceImpl;

import f3x.competition.F3XCompetition.dto.RoundDTO;
import f3x.competition.F3XCompetition.entity.Flight;
import f3x.competition.F3XCompetition.entity.Round;
import f3x.competition.F3XCompetition.repository.FlightRepository;
import f3x.competition.F3XCompetition.repository.RoundRepository;
import f3x.competition.F3XCompetition.service.RoundService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RoundServiceImpl  implements RoundService {

    private final RoundRepository roundRepository;
    private final FlightRepository flightRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RoundServiceImpl(RoundRepository roundRepository, FlightRepository flightRepository, ModelMapper modelMapper) {
        this.roundRepository = roundRepository;
        this.flightRepository = flightRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public List<Round> findAll() {
        return this.roundRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Round> findById(Long roundId) {
        return this.roundRepository.findById(roundId);
    }

    @Override
    @Transactional
    public List<Flight> findRoundFlights(Round round) {
        return round.getRoundFlights();
    }

    @Override
    @Transactional
    public Flight addFlightToRound(Round round, Flight flight) {
        flight.setRound(round);
        return this.flightRepository.save(flight);
    }

    @Override
    @Transactional
    public Round saveRound(Round round) {
        return this.roundRepository.save(round);
    }

    @Override
    @Transactional
    public void removeRound(Round round) {
        this.roundRepository.delete(round);
    }

    @Override
    @Transactional
    public void removeFlightFromRound(Round round, Flight flight) {
            flight.setRound(null);
            this.flightRepository.delete(flight);
    }


    public Round roundDTOtoRound(RoundDTO roundDTO) {
            return this.modelMapper.map(roundDTO,Round.class);
    }

    public RoundDTO roundToRoundDTO(Round round) {
        return this.modelMapper.map(round,RoundDTO.class);
    }
}
