package f3x.competition.F3XCompetition.controller;

import f3x.competition.F3XCompetition.entity.Flight;
import f3x.competition.F3XCompetition.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightRepository flightRepository;

    @Autowired
    public FlightController(FlightRepository flightRepository) {
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

    public void addFlight(@RequestBody Flight flight) {
        this.flightRepository.save(flight);
    }
}
