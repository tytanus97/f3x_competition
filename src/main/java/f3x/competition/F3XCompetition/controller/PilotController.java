package f3x.competition.F3XCompetition.controller;

import f3x.competition.F3XCompetition.entity.Event;
import f3x.competition.F3XCompetition.entity.Pilot;
import f3x.competition.F3XCompetition.entity.Plane;
import f3x.competition.F3XCompetition.repository.PlaneRepository;
import f3x.competition.F3XCompetition.service.PilotService;
import f3x.competition.F3XCompetition.service.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pilots")
public class PilotController {

    private final PilotService pilotService;
    private final PlaneService planeService;

    @Autowired
    public PilotController(PilotService pilotService, PlaneRepository planeRepository, PlaneService planeService) {
        this.pilotService = pilotService;
        this.planeService = planeService;
    }

    @GetMapping("/")
    public List<Pilot> getAll() {
        return this.pilotService.getAll();
    }

    @GetMapping("/{pilotId}")
    public Optional<Pilot> getById(@PathVariable Long pilotId) {
        return this.pilotService.getById(pilotId);
    }

    @GetMapping("/{pilotId}/events")
    public List<Event> getPilotEvents(@PathVariable Long pilotId) {
        Optional<Pilot> tmpPilot = this.pilotService.getById(pilotId);
        return tmpPilot.map(Pilot::getPilotEvents).orElse(null);
    }

    @GetMapping("/{pilotId}/planes")
    public List<Plane> getPilotPlanes(@PathVariable Long pilotId) {
        Optional<Pilot> tmpPilot = this.pilotService.getById(pilotId);
        return tmpPilot.map(Pilot::getPilotPlanes).orElse(null);
    }

    @GetMapping("/{pilotId}/planes/{planeId}")
    public Plane getPilotPlane(@PathVariable Long pilotId,@PathVariable Long planeId) {
        return this.planeService.

    }

    @PostMapping("/")
    public void savePilot(@RequestBody Pilot pilot) {
        this.pilotService.savePilot(pilot);
    }

    @DeleteMapping("/{pilotId}")
    public void removePilot(@PathVariable Long pilotId) {
        Optional<Pilot> tmpPilot = this.pilotService.getById(pilotId);
        tmpPilot.ifPresent(this.pilotService::delete);
    }



}
