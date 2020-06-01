package f3x.competition.F3XCompetition.service;

import f3x.competition.F3XCompetition.entity.Pilot;
import f3x.competition.F3XCompetition.entity.Plane;

import java.util.List;
import java.util.Optional;

public interface PilotService {
    List<Pilot> getAll();
    Optional<Pilot> getById(Long pilotId);
    void addPilot(Pilot pilot);
    void addPlaneToPilot(Pilot pilot, Plane plane);
    void removePlaneFromPilot(Pilot pilot,Plane plane);
    void delete(Pilot pilot);

}
