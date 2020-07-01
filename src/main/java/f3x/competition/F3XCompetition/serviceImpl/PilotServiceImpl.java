package f3x.competition.F3XCompetition.serviceImpl;

import f3x.competition.F3XCompetition.entity.Event;
import f3x.competition.F3XCompetition.entity.Pilot;
import f3x.competition.F3XCompetition.entity.Plane;
import f3x.competition.F3XCompetition.repository.PilotRepository;
import f3x.competition.F3XCompetition.service.PilotService;
import f3x.competition.F3XCompetition.service.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PilotServiceImpl implements PilotService {

    private final PilotRepository pilotRepository;
    private final PlaneService planeService;


    @Autowired
    public PilotServiceImpl(PilotRepository pilotRepository, PlaneService planeService) {
        this.pilotRepository = pilotRepository;
        this.planeService = planeService;
    }

    @Override
    @Transactional
    public List<Pilot> getAll() {
        return this.pilotRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Pilot> getById(Long pilotId) {
        return this.pilotRepository.findById(pilotId);
    }

    @Override
    @Transactional
    public Pilot savePilot(Pilot pilot) {

       return this.pilotRepository.save(pilot);
    }

    @Override
    @Transactional
    public void addPlaneToPilot(Pilot pilot, Plane plane) {
            this.planeService.savePlane(plane);
            pilot.addPlane(plane);
            this.pilotRepository.save(pilot);

    }

    @Override
    @Transactional
    public void removePlaneFromPilot(Pilot pilot, Plane plane) {
            pilot.removePlane(plane);
            this.pilotRepository.save(pilot);
            this.planeService.delete(plane);
    }

    @Override
    @Transactional
    public void delete(Pilot pilot) {
            for(Event event: pilot.getPilotEvents()){
                event.removePilot(pilot);
            }
        this.pilotRepository.delete(pilot);
    }
}
