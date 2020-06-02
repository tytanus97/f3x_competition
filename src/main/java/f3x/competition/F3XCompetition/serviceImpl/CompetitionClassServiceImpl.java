package f3x.competition.F3XCompetition.serviceImpl;

import f3x.competition.F3XCompetition.entity.CompetitionClass;
import f3x.competition.F3XCompetition.repository.CompetitionClassRepository;
import f3x.competition.F3XCompetition.service.CompetitionClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompetitionClassServiceImpl implements CompetitionClassService {

    private final CompetitionClassRepository competitionClassRepository;

    @Autowired
    public CompetitionClassServiceImpl(CompetitionClassRepository competitionClassRepository) {
        this.competitionClassRepository = competitionClassRepository;
    }

    @Override
    @Transactional
    public List<CompetitionClass> getAll() {
        return this.competitionClassRepository.findAll();
    }

    @Override
    @Transactional
    public void saveCompetitionClass(CompetitionClass competitionClass) {
        this.competitionClassRepository.save(competitionClass);
    }

    @Override
    @Transactional
    public void removeCompetitionClass(CompetitionClass competitionClass) {
        this.competitionClassRepository.delete(competitionClass);
    }
}
