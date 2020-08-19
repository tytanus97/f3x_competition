package f3x.competition.F3XCompetition.controller;

import f3x.competition.F3XCompetition.dto.LocationDTO;
import f3x.competition.F3XCompetition.entity.Location;
import f3x.competition.F3XCompetition.service.ImageService;
import f3x.competition.F3XCompetition.service.LocationService;
import f3x.competition.F3XCompetition.serviceImpl.LocationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    private final LocationService locationService;
    private final ImageService imageService;

    @Autowired
    public LocationController(LocationService locationService, ImageService imageService) {
        this.locationService = locationService;
        this.imageService = imageService;
    }

    @GetMapping("/")
    public ResponseEntity<List<LocationDTO>> findAllLocations() {
        List<Location> locations = this.locationService.findAll();
        return new ResponseEntity<>(locations.stream().map(((LocationServiceImpl) this.locationService)::locationToLocationDTO)
                .collect(Collectors.toList()),HttpStatus.OK);
    }

    @GetMapping("/{locationId}")
    public ResponseEntity<LocationDTO> findLocationById(@PathVariable Long locationId) {
        Optional<Location> tmpLocation = this.locationService.findById(locationId);
        return tmpLocation.map(location -> new ResponseEntity<>(((LocationServiceImpl) this.locationService)
                .locationToLocationDTO(location), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/")
    public ResponseEntity addLocation(@RequestBody LocationDTO locationDTO) {
        System.out.println(locationDTO.toString());

        this.locationService.save(((LocationServiceImpl)this.locationService).locationDTOtoLocation(locationDTO));
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
