package f3x.competition.F3XCompetition.service;

import f3x.competition.F3XCompetition.entity.Location;

import java.util.List;

public interface LocationService {
    void save(Location location);
    List<Location> findAll();
}
