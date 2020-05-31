package f3x.competition.F3XCompetition.controller;

import f3x.competition.F3XCompetition.entity.Country;
import f3x.competition.F3XCompetition.entity.Location;
import f3x.competition.F3XCompetition.repository.CountryRepository;
import f3x.competition.F3XCompetition.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class TestController {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private LocationRepository locationRepository;

    @PostMapping("/api/countries")
    public void addCountry(@RequestBody Country country) {
        countryRepository.save(country);
    }
    @GetMapping("/api/countries/{countryId}")
    public Optional<Country> getCountryById(@PathVariable Long countryId) {
        return countryRepository.findByCountryId(countryId);
    }

    @GetMapping("/api/cos")
    public String getCos() {
        return countryRepository.getCos();
    }

    @PostMapping("/api/locations")
    public void addLocation(@RequestBody Location location) {

        location.setCountry(countryRepository.findByCountryId((long)1).get());
        locationRepository.save(location);
    }

    @GetMapping("/api/location/{locationId}")
    public Optional<Location> getLocationById(@PathVariable Long locationId) {
        return locationRepository.findById(locationId);
    }

    @GetMapping("/api/location/{locationId}/country")
    public Country getLocationCountry(@PathVariable Long locationId) {
        return locationRepository.findById(locationId).get().getCountry();
    }


}
