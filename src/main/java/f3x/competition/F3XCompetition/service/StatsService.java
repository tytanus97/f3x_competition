package f3x.competition.F3XCompetition.service;

import f3x.competition.F3XCompetition.entity.Stats;

import java.util.List;
import java.util.Optional;

public interface StatsService {

    void saveStats(Stats stats);
    Optional<Stats> getById(Long id);
    List<Stats> getAll();
    void deleteStats(Stats stats);
}
