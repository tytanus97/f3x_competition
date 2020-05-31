package f3x.competition.F3XCompetition.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import f3x.competition.F3XCompetition.enumerate.LocationType;

import javax.persistence.*;

@Entity
@Table(name="location")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "location_id_generator")
    @SequenceGenerator(name="location_id_generator",initialValue = 1,sequenceName = "location_id_seq")
    @Column(name="location_id")
    @JsonProperty
    private Long locationId;

    @Column(name="location_name")
    @JsonProperty
    private String locationName;

    @Column(name = "latitude")
    @JsonProperty
    private String latitude;

    @Column(name = "longitude")
    @JsonProperty
    private String longitude;

    @Column(name="location_type")
    @JsonProperty
    private LocationType locationType;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    public Location() {
    }

    public Location(String locationName, String latitude, String longitude, LocationType locationType, Country country) {
        this.locationName = locationName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.locationType = locationType;
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

    public LocationType getLocationType() {
        return locationType;
    }

    public void setLocationType(LocationType locationType) {
        this.locationType = locationType;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Location{" +
                "locationId=" + locationId +
                ", locationName='" + locationName + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", locationType=" + locationType +
                '}';
    }
}
