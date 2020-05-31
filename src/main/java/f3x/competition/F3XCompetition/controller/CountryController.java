package f3x.competition.F3XCompetition.controller;

import f3x.competition.F3XCompetition.entity.Country;
import f3x.competition.F3XCompetition.entity.Location;
import f3x.competition.F3XCompetition.repository.CountryRepository;
import f3x.competition.F3XCompetition.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryController(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @GetMapping("/api/countries/{countryId}")
    public Optional<Country> getById(@PathVariable Long countryId) {
        return countryRepository.findByCountryId(countryId);
    }

    @GetMapping("/")
        public List<Country> getAll() {
       return this.countryRepository.findAll();
    }

    @PostMapping("/")
    public void addCountry(@RequestBody Country country) {
        countryRepository.save(country);
    }



}
