package f3x.competition.F3XCompetition.serviceImpl;

import f3x.competition.F3XCompetition.dto.PilotDTO;
import f3x.competition.F3XCompetition.entity.Event;
import f3x.competition.F3XCompetition.entity.Pilot;
import f3x.competition.F3XCompetition.entity.PilotCredential;
import f3x.competition.F3XCompetition.entity.Plane;
import f3x.competition.F3XCompetition.repository.PilotCredentialRepository;
import f3x.competition.F3XCompetition.repository.PilotRepository;
import f3x.competition.F3XCompetition.service.PilotService;
import f3x.competition.F3XCompetition.service.PlaneService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PilotServiceImpl implements PilotService {

    private final PilotRepository pilotRepository;
    private final PlaneService planeService;
    private final PilotCredentialRepository pilotCredentialRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public PilotServiceImpl(PilotRepository pilotRepository, PlaneService planeService, PilotCredentialRepository pilotCredentialRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.pilotRepository = pilotRepository;
        this.planeService = planeService;
        this.pilotCredentialRepository = pilotCredentialRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
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
    public Optional<Pilot> getPilotByEmail(String email) {
        return this.pilotRepository.getByPilotEmail(email);
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
            for(Plane plane: pilot.getPilotPlanes()) {
                this.planeService.delete(plane);
            }
            this.pilotCredentialRepository.deleteByPilot(pilot);
        this.pilotRepository.delete(pilot);
    }

    @Override
    @Transactional
    public Optional<Pilot> findByUsername(String username) {
        PilotCredential credential = this.pilotCredentialRepository.findByUsername(username);
        return credential != null? Optional.of(credential.getPilot()): Optional.empty();
    }

    @Override
    @Transactional
    public PilotCredential savePilotCredential(PilotCredential pilotCredential) {
        pilotCredential.setPassword(passwordEncoder.encode(pilotCredential.getPassword()));
        return this.pilotCredentialRepository.save(pilotCredential);
    }


    public PilotDTO pilotToPilotDTO(Pilot pilot) {
        return modelMapper.map(pilot,PilotDTO.class);
    }

    public Pilot pilotDTOtoPilot(PilotDTO pilotDTO) {
        return modelMapper.map(pilotDTO,Pilot.class);
    }
}
