package f3x.competition.F3XCompetition.service;

import f3x.competition.F3XCompetition.entity.Location;

import java.util.List;
import java.util.Optional;

public interface LocationService {
    void save(Location location);
    List<Location> findAll();
    Optional<Location> findById(Long locationId);
}
