package f3x.competition.F3XCompetition.serviceImpl;

import f3x.competition.F3XCompetition.entity.Pilot;
import f3x.competition.F3XCompetition.entity.Plane;
import f3x.competition.F3XCompetition.repository.PilotRepository;
import f3x.competition.F3XCompetition.repository.PlaneRepository;
import f3x.competition.F3XCompetition.service.PilotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PilotServiceImpl implements PilotService {

    private final PilotRepository pilotRepository;
    private final PlaneRepository planeRepository;


    @Autowired
    public PilotServiceImpl(PilotRepository pilotRepository, PlaneRepository planeRepository) {
        this.pilotRepository = pilotRepository;
        this.planeRepository = planeRepository;
    }

    @Override
    public List<Pilot> getAll() {
        return this.pilotRepository.findAll();
    }

    @Override
    public Optional<Pilot> getById(Long pilotId) {
        return this.pilotRepository.findById(pilotId);
    }

    @Override
    public void addPilot(Pilot pilot) {
            this.pilotRepository.save(pilot);
    }

    @Override
    public void addPlaneToPilot(Pilot pilot, Plane plane) {
            pilot.addPlane(plane);
            this.pilotRepository.save(pilot);
            this.planeRepository.save(plane);
    }

    @Override
    public void removePlaneFromPilot(Pilot pilot, Plane plane) {
            pilot.removePlane(plane);
            this.pilotRepository.save(pilot);
            this.planeRepository.delete(plane);
    }

    @Override
    public void delete(Pilot pilot) {
            this.pilotRepository.delete(pilot);
    }
}
