package f3x.competition.F3XCompetition.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import f3x.competition.F3XCompetition.entity.Country;
import f3x.competition.F3XCompetition.entity.Image;

import java.util.List;

public class LocationDTO {


    @JsonProperty
    private Long locationId;

    @JsonProperty
    private String locationName;

    @JsonProperty
    private String latitude;

    @JsonProperty
    private String longitude;

    @JsonProperty
    private Country country;

    @JsonProperty
    private List<Image> imageList;

    public LocationDTO() {
    }


    public LocationDTO(Long locationId, String locationName, String latitude, String longitude, Country country, List<Image> imageList) {
        this.locationId = locationId;
        this.locationName = locationName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.imageList = imageList;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getLattitude() {
        return latitude;
    }

    public void setLattitude(String lattitude) {
        this.latitude = lattitude;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }

    @Override
    public String toString() {
        return "LocationDTO{" +
                "locationId=" + locationId +
                ", locationName='" + locationName + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", country=" + country +
                '}';
    }
}
