package f3x.competition.F3XCompetition.service;

import f3x.competition.F3XCompetition.entity.Plane;

import java.util.Optional;

public interface PlaneService {
    void savePlane(Plane plane);
    void delete(Plane plane);
    void deleteById(Long planeId);
    Optional<Plane> getById(Long planeId);
}
