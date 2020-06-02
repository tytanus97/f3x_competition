package f3x.competition.F3XCompetition.service;

import f3x.competition.F3XCompetition.entity.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> getAll();
    Optional<Country> getById(Long countryId);
    void saveCountry(Country country);
    void removeCountry(Country country);
}
