package f3x.competition.F3XCompetition.controller;

import f3x.competition.F3XCompetition.entity.Event;
import f3x.competition.F3XCompetition.entity.Flight;
import f3x.competition.F3XCompetition.repository.EventRepository;
import f3x.competition.F3XCompetition.repository.FlightRepository;
import f3x.competition.F3XCompetition.repository.RoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/flights")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class FlightController {

    private final FlightRepository flightRepository;

    @Autowired
    public FlightController(FlightRepository flightRepository, EventRepository eventRepository, RoundRepository roundRepository) {
        this.flightRepository = flightRepository;

    }

    @GetMapping("/")
    public List<Flight> getAll() {
       return this.flightRepository.findAll();
    }

    @GetMapping("/{flightId}")
    public Optional<Flight> getById(@PathVariable Long flightId) {
        return this.flightRepository.findById(flightId);
    }

}
