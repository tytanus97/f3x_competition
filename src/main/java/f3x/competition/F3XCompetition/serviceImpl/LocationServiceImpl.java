package f3x.competition.F3XCompetition.serviceImpl;

import f3x.competition.F3XCompetition.dto.LocationDTO;
import f3x.competition.F3XCompetition.entity.Location;
import f3x.competition.F3XCompetition.repository.LocationRepository;
import f3x.competition.F3XCompetition.service.LocationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository, ModelMapper modelMapper) {
        this.locationRepository = locationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public void save(Location location) {
            this.locationRepository.save(location);
    }

    @Override
    @Transactional
    public List<Location> findAll() {
        return this.locationRepository.findAll();
    }


    public LocationDTO locationToLocationDTO(Location location) {
        return this.modelMapper.map(location,LocationDTO.class);
    }

    public Location locationDTOtoLocation(LocationDTO locationDTO) {
        return this.modelMapper.map(locationDTO,Location.class);
    }
}
