package f3x.competition.F3XCompetition.serviceImpl;

import f3x.competition.F3XCompetition.entity.Plane;
import f3x.competition.F3XCompetition.repository.PlaneRepository;
import f3x.competition.F3XCompetition.service.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PlaneServiceImpl implements PlaneService {

    private final PlaneRepository planeRepository;

    @Autowired
    public PlaneServiceImpl(PlaneRepository planeRepository) {
        this.planeRepository = planeRepository;
    }

    @Override
    @Transactional
    public void savePlane(Plane plane) {
        this.planeRepository.save(plane);
    }

    @Override
    @Transactional
    public void delete(Plane plane) {
        this.planeRepository.delete(plane);
    }

    @Override
    @Transactional
    public void deleteById(Long planeId) {
        this.planeRepository.deleteById(planeId);
    }

    @Override
    @Transactional
    public Optional<Plane> getById(Long planeId) {
        return this.planeRepository.findById(planeId);
    }
}
