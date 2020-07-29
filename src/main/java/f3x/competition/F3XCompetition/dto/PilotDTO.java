package f3x.competition.F3XCompetition.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import f3x.competition.F3XCompetition.entity.Country;

import java.time.LocalDate;

public class PilotDTO {

    @JsonProperty
    private Long pilotId;

    @JsonProperty
    private String pilotFirstName;

    @JsonProperty
    private String pilotLastName;

    @JsonProperty
    private Country country;

    @JsonProperty
    private LocalDate pilotBirthDate;

    @JsonProperty
    private String pilotEmail;

    @JsonProperty(defaultValue = "0")
    private float pilotRating;

    public PilotDTO() {
    }

    public PilotDTO(Long pilotId, String pilotFirstName, String pilotLastName, Country country,
                    LocalDate pilotBirthDate, String pilotEmail, float pilotRating) {
        this.pilotId = pilotId;
        this.pilotFirstName = pilotFirstName;
        this.pilotLastName = pilotLastName;
        this.country = country;
        this.pilotBirthDate = pilotBirthDate;
        this.pilotEmail = pilotEmail;
        this.pilotRating = pilotRating;
    }

    public Long getPilotId() {
        return pilotId;
    }

    public void setPilotId(Long pilotId) {
        this.pilotId = pilotId;
    }

    public String getPilotFirstName() {
        return pilotFirstName;
    }

    public void setPilotFirstName(String pilotFirstName) {
        this.pilotFirstName = pilotFirstName;
    }

    public String getPilotLastName() {
        return pilotLastName;
    }

    public void setPilotLastName(String pilotLastName) {
        this.pilotLastName = pilotLastName;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public LocalDate getPilotBirthDate() {
        return pilotBirthDate;
    }

    public void setPilotBirthDate(LocalDate pilotBirthDate) {
        this.pilotBirthDate = pilotBirthDate;
    }

    public String getPilotEmail() {
        return pilotEmail;
    }

    public void setPilotEmail(String pilotEmail) {
        this.pilotEmail = pilotEmail;
    }

    public float getPilotRating() {
        return pilotRating;
    }

    public void setPilotRating(float pilotRating) {
        this.pilotRating = pilotRating;
    }


}
