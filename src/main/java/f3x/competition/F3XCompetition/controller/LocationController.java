package f3x.competition.F3XCompetition.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import f3x.competition.F3XCompetition.dto.LocationDTO;
import f3x.competition.F3XCompetition.entity.Image;
import f3x.competition.F3XCompetition.entity.Location;
import f3x.competition.F3XCompetition.service.ImageService;
import f3x.competition.F3XCompetition.service.LocationService;
import f3x.competition.F3XCompetition.serviceImpl.LocationServiceImpl;
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
@RequestMapping("/api/locations")
public class LocationController {

    private final LocationService locationService;
    private final ImageService imageService;
    private final ObjectMapper objectMapper;

    @Autowired
    public LocationController(LocationService locationService, ImageService imageService, ObjectMapper objectMapper) {
        this.locationService = locationService;
        this.imageService = imageService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/")
    public ResponseEntity<List<LocationDTO>> findAllLocations() {
        List<Location> locations = this.locationService.findAll();

        List<LocationDTO> locationDTOList = locations.stream().map(l -> {
           Optional<List<Image>> imageList = this.imageService.findByEntityIdAndEntityType(l.getLocationId(),"location");
           LocationDTO locationDTO = ((LocationServiceImpl)this.locationService).locationToLocationDTO(l);
           locationDTO.setImageList(imageList.orElse(null));
           return locationDTO;

        }).collect(Collectors.toList());
        return new ResponseEntity<>(locationDTOList,HttpStatus.OK);
    }

    @GetMapping("/{locationId}")
    public ResponseEntity<LocationDTO> findLocationById(@PathVariable Long locationId) {
        Optional<Location> tmpLocation = this.locationService.findById(locationId);

        if(tmpLocation.isPresent()) {
            LocationDTO locationDTO = ((LocationServiceImpl)this.locationService).locationToLocationDTO(tmpLocation.get());
            Optional<List<Image>> imageList = this.imageService.findByEntityIdAndEntityType(locationDTO.getLocationId(),"location");
            locationDTO.setImageList(imageList.orElse(null));
            return new ResponseEntity<>(locationDTO,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/")
    public ResponseEntity addLocation(@RequestParam("location") String locationJSON,
                                      @RequestParam("locationImages") List<MultipartFile> locationImages) {

        LocationDTO locationDTO = null;
        try {
            locationDTO = objectMapper.readValue(locationJSON, LocationDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Location savedLocation = this.locationService.save(((LocationServiceImpl)this.locationService).locationDTOtoLocation(locationDTO));
        if(savedLocation != null) {
            List<Image> imageList = locationImages.stream().map(file -> {
                String fileName = this.imageService.uploadImage(file, Location.class.getSimpleName().toLowerCase() + '/'
                        + savedLocation.getLocationId().toString());
                String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/api/images/location/")
                        .path(savedLocation.getLocationId().toString() + "/")
                        .path(fileName)
                        .toUriString();

                System.out.println(fileName);
                System.out.println(fileDownloadUri);

                Image image = new Image();
                image.setImageId(0L);
                image.setImageName(fileName);
                image.setEntityId(savedLocation.getLocationId());
                image.setEntityType(Location.class.getSimpleName().toLowerCase());
                image.setImageSize(file.getSize());
                image.setImageURI(fileDownloadUri);
                image.setImageType(file.getContentType());
                return this.imageService.save(image);
            }).collect(Collectors.toList());
            LocationDTO resultLocation = ((LocationServiceImpl)this.locationService).locationToLocationDTO(savedLocation);
            resultLocation.setImageList(imageList);

            return new ResponseEntity<>(resultLocation, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/")
    public ResponseEntity updateLocation(@RequestBody Location location) {

        return new ResponseEntity(HttpStatus.OK);
    }
    @DeleteMapping("/{locationId}")
    public ResponseEntity deleteLocation(@PathVariable Long locationId) {
        return new ResponseEntity(HttpStatus.OK);
    }

}
