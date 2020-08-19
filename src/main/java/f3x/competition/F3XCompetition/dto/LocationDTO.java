package f3x.competition.F3XCompetition.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import f3x.competition.F3XCompetition.entity.Country;

public class LocationDTO {


    @JsonProperty
    private Long locationId;

    @JsonProperty
    private String locationName;

    @JsonProperty
    private String lattitude;

    @JsonProperty
    private String longitude;

    @JsonProperty
    private Country country;

    public LocationDTO() {
    }

    public LocationDTO(Long locationId, String locationName, String latitude, String longitude, Country country) {
        this.locationId = locationId;
        this.locationName = locationName;
        this.lattitude = latitude;
        this.longitude = longitude;
        this.country = country;
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
        return lattitude;
    }

    public void setLatitude(String latitude) {
        this.lattitude = latitude;
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

    @Override
    public String toString() {
        return "LocationDTO{" +
                "locationId=" + locationId +
                ", locationName='" + locationName + '\'' +
                ", latitude='" + lattitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", country=" + country +
                '}';
    }
}
