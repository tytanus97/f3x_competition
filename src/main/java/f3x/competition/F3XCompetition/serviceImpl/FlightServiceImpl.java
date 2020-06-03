package f3x.competition.F3XCompetition.serviceImpl;

import f3x.competition.F3XCompetition.entity.Event;
import f3x.competition.F3XCompetition.entity.Flight;
import f3x.competition.F3XCompetition.entity.Pilot;
import f3x.competition.F3XCompetition.entity.Round;
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
    public List<Flight> getByEventAndPilot(Event event, Pilot pilot) {
        return this.flightRepository.findAllByEventAndPilot(event,pilot);
    }

    @Override
    @Transactional
    public List<Flight> getByEventAndPilot(Long eventId, Long pilotId) {
        return this.flightRepository.findAllByEvent_EventIdAndPilot_PilotId(eventId,pilotId);
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

    @Override
    public List<Flight> getByEventAndPilotAndRound(Long eventId, Long pilotId, Long roundId) {
        return this.flightRepository.findAllByEvent_EventIdAndPilot_PilotIdAndRound_Round_id(eventId,pilotId,roundId);
    }
}
