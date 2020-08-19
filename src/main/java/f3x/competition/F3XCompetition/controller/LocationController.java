package f3x.competition.F3XCompetition.controller;

import f3x.competition.F3XCompetition.dto.LocationDTO;
import f3x.competition.F3XCompetition.entity.Image;
import f3x.competition.F3XCompetition.entity.Location;
import f3x.competition.F3XCompetition.entity.Plane;
import f3x.competition.F3XCompetition.service.ImageService;
import f3x.competition.F3XCompetition.service.LocationService;
import f3x.competition.F3XCompetition.serviceImpl.LocationServiceImpl;
import io.swagger.models.Response;
import org.aspectj.weaver.patterns.HasThisTypePatternTriedToSneakInSomeGenericOrParameterizedTypePatternMatchingStuffAnywhereVisitor;
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

    @Autowired
    public LocationController(LocationService locationService, ImageService imageService) {
        this.locationService = locationService;
        this.imageService = imageService;
    }

    @GetMapping("/")
    public ResponseEntity<List<LocationDTO>> findAllLocations() {
        List<Location> locations = this.locationService.findAll();

        return new ResponseEntity<>(locations.stream().map(((LocationServiceImpl) this.locationService)::locationToLocationDTO)
                .collect(Collectors.toList()),HttpStatus.OK);
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
    public ResponseEntity addLocation(@RequestBody LocationDTO locationDTO,
                                      @RequestParam("locationImages") List<MultipartFile> locationImages) {

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
