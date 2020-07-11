package f3x.competition.F3XCompetition.controller;

import f3x.competition.F3XCompetition.entity.Location;
import f3x.competition.F3XCompetition.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Location>> findAllLocations(@RequestParam(value = "country",required = false)String countryName) {


        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{locationId}")
    public ResponseEntity findLocationById(@PathVariable Long locationId) {

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity addLocation(@RequestBody Location location) {

        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity updateLocation(@RequestBody Location location) {


        return new ResponseEntity(HttpStatus.OK);
    }
    @DeleteMapping("/{locationId}")
    public ResponseEntity deleteLocation(@PathVariable Long locationId) {
        return new ResponseEntity(HttpStatus.OK);
    }

}
