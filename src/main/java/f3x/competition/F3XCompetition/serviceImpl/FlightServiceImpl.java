package f3x.competition.F3XCompetition.serviceImpl;

import f3x.competition.F3XCompetition.entity.Flight;
import f3x.competition.F3XCompetition.repository.FlightRepository;
import f3x.competition.F3XCompetition.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    @Autowired
    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    @Transactional
    public List<Flight> getAll() {
        return this.flightRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Flight> getById(Long flightId) {
        return this.flightRepository.findById(flightId);
    }

    @Override
    @Transactional
    public void saveFlight(Flight flight) {
        this.flightRepository.save(flight);
    }

    @Override
    @Transactional
    public void deleteFlight(Flight flight) {
        this.flightRepository.delete(flight);
    }
}
