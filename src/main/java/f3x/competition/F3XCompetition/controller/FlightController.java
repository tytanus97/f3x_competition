package f3x.competition.F3XCompetition.controller;

import f3x.competition.F3XCompetition.entity.Flight;
import f3x.competition.F3XCompetition.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/flights")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @DeleteMapping("/{flightId}/delete")
    public ResponseEntity deleteFlightById(@PathVariable Long flightId) {
        this.flightService.deleteFlightByFlightId(flightId);
        return new ResponseEntity(HttpStatus.OK);
    }

}
