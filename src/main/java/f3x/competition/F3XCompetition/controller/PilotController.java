package f3x.competition.F3XCompetition.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import f3x.competition.F3XCompetition.dto.PilotDTO;
import f3x.competition.F3XCompetition.dto.PlaneDTO;
import f3x.competition.F3XCompetition.entity.Country;
import f3x.competition.F3XCompetition.entity.Event;
import f3x.competition.F3XCompetition.entity.Pilot;
import f3x.competition.F3XCompetition.entity.Plane;
import f3x.competition.F3XCompetition.entity.Image;
import f3x.competition.F3XCompetition.repository.PlaneRepository;
import f3x.competition.F3XCompetition.service.ImageService;
import f3x.competition.F3XCompetition.service.PilotService;
import f3x.competition.F3XCompetition.service.PlaneService;
import f3x.competition.F3XCompetition.serviceImpl.PilotServiceImpl;
import f3x.competition.F3XCompetition.serviceImpl.PlaneServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pilots")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class PilotController {

    private final PilotService pilotService;
    private final PlaneService planeService;
    private final ImageService imageService;
    private final ObjectMapper objectMapper;

    @Autowired
    public PilotController(PilotService pilotService, PlaneRepository planeRepository,
                           PlaneService planeService, ImageService imageService, ObjectMapper objectMapper) {
        this.pilotService = pilotService;
        this.planeService = planeService;
        this.imageService = imageService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/")
    public ResponseEntity<List<Pilot>> getAll() {
        return new ResponseEntity<>(this.pilotService.getAll(),HttpStatus.OK);
    }

    @GetMapping("/{pilotId}")
    public ResponseEntity<Optional<Pilot>> getById(@PathVariable Long pilotId) {
        return new ResponseEntity<>(this.pilotService.getById(pilotId),HttpStatus.OK);
    }

    @GetMapping("/email")
    public ResponseEntity<List<Pilot>> getAllByEmail(@RequestParam("email") String email) {
        if(!email.isEmpty()) {
            return new ResponseEntity<>(this.pilotService.getPilotsByEmail(email),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{pilotId}/country")
    public ResponseEntity<Optional<Country>> getPilotCountry(@PathVariable Long pilotId) {
        return new ResponseEntity<>(this.pilotService.getById(pilotId).map(Pilot::getCountry),HttpStatus.OK);
    }

    @GetMapping("/{pilotId}/events")
    public ResponseEntity<List<Event>> getPilotEvents(@PathVariable Long pilotId) {
        Optional<Pilot> tmpPilot = this.pilotService.getById(pilotId);
        return new ResponseEntity<>(tmpPilot.map(Pilot::getPilotEvents).orElse(null),HttpStatus.OK);
    }

    @GetMapping("/{pilotId}/planes")
    public ResponseEntity<List<PlaneDTO>> getPilotPlanes(@PathVariable Long pilotId) {
        Optional<Pilot> tmpPilot = this.pilotService.getById(pilotId);
        if(tmpPilot.isPresent()) {
            List<Plane> pilotPlanes = tmpPilot.get().getPilotPlanes();
            List<PlaneDTO> pilotPlanesDTO = pilotPlanes.stream().map(plane -> {
                PlaneDTO planeDTO = ((PlaneServiceImpl)this.planeService).planeToPlaneDTO(plane);

                Optional<List<Image>> imageList = this.imageService.findByEntityIdAndEntityType(plane.getPlaneId(),"plane");
                planeDTO.setImageList(imageList.orElse(null));
                return planeDTO;
            }).collect(Collectors.toList());
            return new ResponseEntity<>(pilotPlanesDTO,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{pilotId}/planes/{planeId}")
    public ResponseEntity<PlaneDTO> getPilotPlane(@PathVariable Long pilotId, @PathVariable Long planeId) {
        Optional<Plane> tmpPlane = this.planeService.getById(planeId);

        if(tmpPlane.isPresent()) {
            Plane plane = tmpPlane.get();
            PlaneDTO planeDTO = ((PlaneServiceImpl)this.planeService).planeToPlaneDTO(plane);
            Optional<List<Image>> imageList = this.imageService.findByEntityIdAndEntityType(plane.getPlaneId(),"plane");
            planeDTO.setImageList(imageList.orElse(null));
            return new ResponseEntity<>(planeDTO,HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{pilotId}")
    public ResponseEntity<Pilot> updatePilot(@RequestBody PilotDTO updatedPilot, @PathVariable Long pilotId) {
        Pilot pilot  = ((PilotServiceImpl)this.pilotService).pilotDTOtoPilot(updatedPilot);
        return new ResponseEntity<>(this.pilotService.savePilot(pilot),HttpStatus.OK);
    }
    @PostMapping("/")
    public ResponseEntity savePilot(@RequestBody PilotDTO pilotDTO) {
        System.out.println(pilotDTO.toString());
        Pilot pilotEntity = ((PilotServiceImpl)this.pilotService).pilotDTOtoPilot(pilotDTO);
        pilotEntity =  this.pilotService.savePilot(pilotEntity);
        return pilotEntity == null? new ResponseEntity<>(HttpStatus.BAD_REQUEST):
                new ResponseEntity<>(pilotEntity,HttpStatus.CREATED);
    }

    @PostMapping(value = "/{pilotId}/planes")
    public ResponseEntity<PlaneDTO> addPilotPlane(@PathVariable Long pilotId, @RequestParam("images") List<MultipartFile> images
                                                ,@RequestParam(value = "planeBody",required = false) String planeJson ) {

        Optional<Pilot> tmpPilot = this.pilotService.getById(pilotId);
        final Plane plane;
        try {
            PlaneDTO planeDTO = objectMapper.readValue(planeJson, PlaneDTO.class);
            plane = ((PlaneServiceImpl)this.planeService).planeDTOtoPlane(planeDTO);
            tmpPilot.ifPresent(plane::setPilot);

        }catch(JsonProcessingException exc) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

         Plane savedPlane = this.planeService.savePlane(plane);

        if(savedPlane != null) {
            List<Image> imageList = images.stream().map(file -> {
                String fileName = this.imageService.uploadImage(file, Plane.class.getSimpleName().toLowerCase() + '/'
                        + savedPlane.getPlaneId().toString());
                String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/api/images/plane/")
                        .path(savedPlane.getPlaneId().toString() + "/")
                        .path(fileName)
                        .toUriString();

                System.out.println(fileName);
                System.out.println(fileDownloadUri);

                Image image = new Image();
                image.setImageId(0L);
                image.setImageName(fileName);
                image.setEntityId(savedPlane.getPlaneId());
                image.setEntityType(Plane.class.getSimpleName().toLowerCase());
                image.setImageSize(file.getSize());
                image.setImageURI(fileDownloadUri);
                image.setImageType(file.getContentType());
                return this.imageService.save(image);
            }).collect(Collectors.toList());
            PlaneDTO resultPlane = ((PlaneServiceImpl) this.planeService).planeToPlaneDTO(savedPlane);
            resultPlane.setImageList(imageList);

            return new ResponseEntity<>(resultPlane, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @DeleteMapping("/planes/{planeId}")
    public ResponseEntity deletePlane(@PathVariable Long planeId) {
        Optional<Plane> plane = this.planeService.getById(planeId);
        if(plane.isPresent()) {
            this.planeService.delete(plane.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{pilotId}")
    public ResponseEntity removePilot(@PathVariable Long pilotId) {
        Optional<Pilot> tmpPilot = this.pilotService.getById(pilotId);
        tmpPilot.ifPresent(pilot -> {

            this.pilotService.delete(pilot);
        });
        return new ResponseEntity(HttpStatus.OK);
    }

}
