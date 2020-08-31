package f3x.competition.F3XCompetition.serviceImpl;

import f3x.competition.F3XCompetition.repository.StatsRepository;
import f3x.competition.F3XCompetition.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StatsServiceImpl implements StatsService {

    private final StatsRepository statsRepository;

    @Autowired
    public StatsServiceImpl(StatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    @Override
    @Transactional
    public void saveStats(Stats stats) {
        this.statsRepository.save(stats);
    }

    @Override
    @Transactional
    public Optional<Stats> getById(Long statsId) {
        return this.statsRepository.findById(statsId);
    }

    @Override
    @Transactional
    public List<Stats> getAll() {
        return this.statsRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteStats(Stats stats) {
        this.statsRepository.delete(stats);
    }
}
