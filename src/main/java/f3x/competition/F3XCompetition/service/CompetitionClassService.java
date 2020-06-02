package f3x.competition.F3XCompetition.service;

import f3x.competition.F3XCompetition.entity.CompetitionClass;

import java.util.List;

public interface CompetitionClassService {
        List<CompetitionClass> getAll();
        void saveCompetitionClass(CompetitionClass competitionClass);
        void removeCompetitionClass(CompetitionClass competitionClass);
}
