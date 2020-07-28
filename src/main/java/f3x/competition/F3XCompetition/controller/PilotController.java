package f3x.competition.F3XCompetition.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import f3x.competition.F3XCompetition.dto.PlaneDTO;
import f3x.competition.F3XCompetition.entity.Country;
import f3x.competition.F3XCompetition.entity.Event;
import f3x.competition.F3XCompetition.entity.Pilot;
import f3x.competition.F3XCompetition.entity.Plane;
import f3x.competition.F3XCompetition.entity.images.Image;
import f3x.competition.F3XCompetition.repository.PlaneRepository;
import f3x.competition.F3XCompetition.service.ImageService;
import f3x.competition.F3XCompetition.service.PilotService;
import f3x.competition.F3XCompetition.service.PlaneService;
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
    public PilotController(PilotService pilotService, PlaneRepository planeRepository, PlaneService planeService, ImageService imageService, ObjectMapper objectMapper) {
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
                PlaneDTO planeDTO = new PlaneDTO();
                planeDTO.setPlaneId(plane.getPlaneId());
                planeDTO.setPlaneColor(plane.getPlaneColor());
                planeDTO.setPlaneName(plane.getPlaneName());
                planeDTO.setPlaneWeight(plane.getPlaneWeight());
                planeDTO.setPlaneWingSpan(plane.getPlaneWingSpan());
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
            PlaneDTO planeDTO = new PlaneDTO();

            planeDTO.setPlaneId(plane.getPlaneId());
            planeDTO.setPlaneColor(plane.getPlaneColor());
            planeDTO.setPlaneName(plane.getPlaneName());
            planeDTO.setPlaneWeight(plane.getPlaneWeight());
            planeDTO.setPlaneWingSpan(plane.getPlaneWingSpan());
            Optional<List<Image>> imageList = this.imageService.findByEntityIdAndEntityType(plane.getPlaneId(),"plane");
            planeDTO.setImageList(imageList.orElse(null));
            return new ResponseEntity<>(planeDTO,HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{pilotId}")
    public ResponseEntity<Pilot> updatePilot(@RequestBody Pilot updatedPilot, @PathVariable Long pilotId) {
        return new ResponseEntity<>(this.pilotService.savePilot(updatedPilot),HttpStatus.OK);
    }
    @PostMapping("/")
    public ResponseEntity savePilot(@RequestBody Pilot pilot) {
        System.out.println(pilot.toString());
       Pilot result =  this.pilotService.savePilot(pilot);
        return result == null? new ResponseEntity<>(HttpStatus.BAD_REQUEST):
                new ResponseEntity<>(pilot,HttpStatus.CREATED);
    }

    @PostMapping(value = "/{pilotId}/planes")
    public ResponseEntity<Plane> addPilotPlane(@PathVariable Long pilotId, @RequestParam("images") List<MultipartFile> images
                                                ,@RequestParam(value = "planeBody",required = false) String planeJson ) {

        Optional<Pilot> tmpPilot = this.pilotService.getById(pilotId);
        final Plane plane = new Plane();
        try {
            PlaneDTO receivedPlane = objectMapper.readValue(planeJson, PlaneDTO.class);
            tmpPilot.ifPresent(plane::setPilot);
            plane.setPlaneColor(receivedPlane.getPlaneColor());
            plane.setPlaneId(receivedPlane.getPlaneId());
            plane.setPlaneName(receivedPlane.getPlaneName());
            plane.setPlaneWeight(receivedPlane.getPlaneWeight());
            plane.setPlaneWingSpan(receivedPlane.getPlaneWingSpan());

        }catch(JsonProcessingException exc) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

         Plane savedPlane = this.planeService.savePlane(plane);

        if(savedPlane != null) {
            images.forEach(file -> {
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
                this.imageService.save(image);
            });

            return new ResponseEntity<>(savedPlane, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @DeleteMapping("/planes/{planeId}")
    public ResponseEntity deletePlane(@PathVariable Long planeId) {
        this.planeService.deleteById(planeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{pilotId}")
    public ResponseEntity removePilot(@PathVariable Long pilotId) {
        Optional<Pilot> tmpPilot = this.pilotService.getById(pilotId);
        tmpPilot.ifPresent(this.pilotService::delete);
        return new ResponseEntity(HttpStatus.OK);
    }
}
