package f3x.competition.F3XCompetition.serviceImpl;

import f3x.competition.F3XCompetition.dto.PlaneDTO;
import f3x.competition.F3XCompetition.entity.Plane;
import f3x.competition.F3XCompetition.repository.PlaneRepository;
import f3x.competition.F3XCompetition.service.PlaneService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PlaneServiceImpl implements PlaneService {

    private final PlaneRepository planeRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PlaneServiceImpl(PlaneRepository planeRepository, ModelMapper modelMapper) {
        this.planeRepository = planeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public Plane savePlane(Plane plane) {
        return this.planeRepository.save(plane);
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

    public Plane planeDTOtoPlane(PlaneDTO planeDTO) {
        return this.modelMapper.map(planeDTO,Plane.class);
    }

    public PlaneDTO planeToPlaneDTO(Plane plane) {
        return this.modelMapper.map(plane,PlaneDTO.class);
    }
}
