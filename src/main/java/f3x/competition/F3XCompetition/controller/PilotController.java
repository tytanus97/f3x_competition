package f3x.competition.F3XCompetition.controller;

import f3x.competition.F3XCompetition.entity.Country;
import f3x.competition.F3XCompetition.entity.Event;
import f3x.competition.F3XCompetition.entity.Pilot;
import f3x.competition.F3XCompetition.entity.Plane;
import f3x.competition.F3XCompetition.repository.PlaneRepository;
import f3x.competition.F3XCompetition.service.PilotService;
import f3x.competition.F3XCompetition.service.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pilots")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class PilotController {

    private final PilotService pilotService;
    private final PlaneService planeService;

    @Autowired
    public PilotController(PilotService pilotService, PlaneRepository planeRepository, PlaneService planeService) {
        this.pilotService = pilotService;
        this.planeService = planeService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Pilot>> getAll() {
        return new ResponseEntity<>(this.pilotService.getAll(),HttpStatus.OK);
    }

    @GetMapping("/{pilotId}")
    public ResponseEntity<Optional<Pilot>> getById(@PathVariable Long pilotId) {
        return new ResponseEntity<>(this.pilotService.getById(pilotId),HttpStatus.OK);
    }

    @GetMapping("/email")
    public ResponseEntity<List<Pilot>> getAllByEmail(@RequestParam("email") String email) {
        if(!email.isEmpty()) {
            return new ResponseEntity<>(this.pilotService.getPilotsByEmail(email),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{pilotId}/country")
    public ResponseEntity<Optional<Country>> getPilotCountry(@PathVariable Long pilotId) {
        return new ResponseEntity<>(this.pilotService.getById(pilotId).map(Pilot::getCountry),HttpStatus.OK);
    }

    @GetMapping("/{pilotId}/events")
    public ResponseEntity<List<Event>> getPilotEvents(@PathVariable Long pilotId) {
        Optional<Pilot> tmpPilot = this.pilotService.getById(pilotId);
        return new ResponseEntity<>(tmpPilot.map(Pilot::getPilotEvents).orElse(null),HttpStatus.OK);
    }

    @GetMapping("/{pilotId}/planes")
    public ResponseEntity<List<Plane>> getPilotPlanes(@PathVariable Long pilotId) {
        Optional<Pilot> tmpPilot = this.pilotService.getById(pilotId);
        return new ResponseEntity<>(tmpPilot.map(Pilot::getPilotPlanes).orElse(null),HttpStatus.OK);
    }

    @GetMapping("/{pilotId}/planes/{planeId}")
    public ResponseEntity<Plane> getPilotPlane(@PathVariable Long pilotId, @PathVariable Long planeId) {
        Optional<Plane> tmpPlane = this.planeService.getById(planeId);
        return new ResponseEntity<>(tmpPlane.orElse(null),HttpStatus.OK);
    }

    @PutMapping("/{pilotId}")
    public ResponseEntity<Pilot> updatePilot(@RequestBody Pilot updatedPilot, @PathVariable Long pilotId) {
        return new ResponseEntity<>(this.pilotService.savePilot(updatedPilot),HttpStatus.OK);
    }
    @PostMapping("/")
    public ResponseEntity savePilot(@RequestBody Pilot pilot) {
        System.out.println(pilot.toString());
       Pilot result =  this.pilotService.savePilot(pilot);
        return result == null? new ResponseEntity<>(HttpStatus.BAD_REQUEST):
                new ResponseEntity<>(pilot,HttpStatus.CREATED);
    }

    @PostMapping("/{pilotId}/planes")
    public ResponseEntity<Plane> addPilotPlane(@PathVariable Long pilotId,@RequestBody Plane plane) {
        Optional<Pilot> tmpPilot = this.pilotService.getById(pilotId);
        tmpPilot.ifPresent(plane::setPilot);
        return new ResponseEntity<>(this.planeService.savePlane(plane),HttpStatus.OK);
    }

    @DeleteMapping("/{pilotId}/planes/{planeId}")
    public ResponseEntity deletePlane(@PathVariable Long pilotId,@PathVariable Long planeId) {
        this.planeService.deleteById(planeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{pilotId}")
    public ResponseEntity removePilot(@PathVariable Long pilotId) {
        Optional<Pilot> tmpPilot = this.pilotService.getById(pilotId);
        tmpPilot.ifPresent(this.pilotService::delete);
        return new ResponseEntity(HttpStatus.OK);
    }
}
