package f3x.competition.F3XCompetition.repository;

import f3x.competition.F3XCompetition.entity.Pilot;
import f3x.competition.F3XCompetition.entity.PilotCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PilotCredentialRepository extends JpaRepository<PilotCredential,Long> {
    PilotCredential findByUsername(String username);
    void deleteByPilot(Pilot Pilot);

}
