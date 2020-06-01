package f3x.competition.F3XCompetition.controller;

import f3x.competition.F3XCompetition.entity.Pilot;
import f3x.competition.F3XCompetition.repository.PilotRepository;
import f3x.competition.F3XCompetition.repository.PlaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pilots")
public class PilotController {

    private final PilotRepository pilotRepository;
    private final PlaneRepository planeRepository;

    @Autowired
    public PilotController(PilotRepository pilotRepository,PlaneRepository planeRepository) {
        this.pilotRepository = pilotRepository;
        this.planeRepository = planeRepository;
    }

    @GetMapping("/")
    public List<Pilot> getAll() {
        return this.pilotRepository.findAll();
    }

    @GetMapping("/{pilotId}")
    public Optional<Pilot> getById(@PathVariable Long pilotId) {
        return this.pilotRepository.findById(pilotId);
    }

    @PostMapping("/")
    public void addPilot(@RequestBody Pilot pilot) {
        this.pilotRepository.save(pilot);
    }

}
