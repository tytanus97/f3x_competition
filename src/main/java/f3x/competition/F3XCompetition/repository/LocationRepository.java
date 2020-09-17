package f3x.competition.F3XCompetition.repository;

import f3x.competition.F3XCompetition.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location,Long> {
    List<Location> findAllByCountry_CountryName(String countryName);
}
