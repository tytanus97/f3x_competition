package f3x.competition.F3XCompetition.repository;

import f3x.competition.F3XCompetition.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {

    Optional<Country> findByCountryId(Long id);
    default String getCos() {
        return "COS";
    }
}
