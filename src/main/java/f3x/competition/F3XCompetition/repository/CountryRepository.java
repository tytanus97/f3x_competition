package f3x.competition.F3XCompetition.repository;

import f3x.competition.F3XCompetition.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {

    Optional<Country> findByCountryId(Long id);

    @Query("SELECT p from Country p")
    List<Country> findAll();

    default String getCos() {
        return "COS";
    }
}
