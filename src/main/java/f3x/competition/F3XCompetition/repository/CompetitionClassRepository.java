package f3x.competition.F3XCompetition.repository;

import f3x.competition.F3XCompetition.entity.CompetitionClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionClassRepository extends JpaRepository<CompetitionClass,Long> {

}
