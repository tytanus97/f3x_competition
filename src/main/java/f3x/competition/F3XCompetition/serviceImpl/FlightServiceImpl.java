package f3x.competition.F3XCompetition.serviceImpl;

import f3x.competition.F3XCompetition.dto.FlightDTO;
import f3x.competition.F3XCompetition.entity.Event;
import f3x.competition.F3XCompetition.entity.Flight;
import f3x.competition.F3XCompetition.entity.Pilot;
import f3x.competition.F3XCompetition.repository.FlightRepository;
import f3x.competition.F3XCompetition.service.FlightService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public FlightServiceImpl(FlightRepository flightRepository, ModelMapper modelMapper) {
        this.flightRepository = flightRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public List<Flight> findAll() {
        return this.flightRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Flight> findById(Long flightId) {
        return this.flightRepository.findById(flightId);
    }

    @Override
    @Transactional
    public List<Flight> findByEventAndPilot(Event event, Pilot pilot) {
        return this.flightRepository.findAllByEventAndPilot(event,pilot);
    }

    @Override
    @Transactional
    public List<Flight> findByEventAndPilot(Long eventId, Long pilotId) {
        return this.flightRepository.findAllByEvent_EventIdAndPilot_PilotId(eventId,pilotId);
    }

    @Override
    @Transactional
    public Flight saveFlight(Flight flight) {
        return this.flightRepository.save(flight);
    }

    @Override
    @Transactional
    public void deleteFlight(Flight flight) {
        this.flightRepository.delete(flight);
    }

    @Override
    @Transactional
    public void deleteFlightByFlightId(Long flightId) {
        this.flightRepository.deleteFlightByFlightId(flightId);
    }

    @Override
    @Transactional
    public List<Flight> findByEventAndPilotAndRound(Long eventId, Long pilotId, Long roundId) {
        return this.flightRepository.findAllByEvent_EventIdAndPilot_PilotIdAndRound_RoundId(eventId,pilotId,roundId);
    }


    public Flight flightDTOtoFlight(FlightDTO flightDTO) {
        return this.modelMapper.map(flightDTO,Flight.class);
    }

    public FlightDTO flightToFlightDTO(Flight flight) {
        return this.modelMapper.map(flight,FlightDTO.class);
    }




}
