package f3x.competition.F3XCompetition.serviceImpl;

import f3x.competition.F3XCompetition.entity.Country;
import f3x.competition.F3XCompetition.repository.CountryRepository;
import f3x.competition.F3XCompetition.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    @Transactional
    public List<Country> getAll() {
        return this.countryRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Country> getById(Long countryId) {
        return this.countryRepository.findById(countryId);
    }

    @Override
    @Transactional
    public void saveCountry(Country country) {
            this.countryRepository.save(country);
    }

    @Override
    @Transactional
    public void removeCountry(Country country) {
        this.countryRepository.delete(country);
    }
}
