package f3x.competition.F3XCompetition.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import f3x.competition.F3XCompetition.entity.Location;

import java.time.LocalDate;

public class EventDTO {
    @JsonProperty
    private Long eventId;
    @JsonProperty
    private Boolean registrationStatus;
    @JsonProperty
    private String eventType;
    @JsonProperty
    private String eventName;
    @JsonProperty
    private LocalDate startDate;
    @JsonProperty
    private LocalDate endDate;
    @JsonProperty
    private Location location;
    @JsonProperty
    private PilotDTO pilotDirector;

    public EventDTO() {

    }

    public EventDTO(Long eventId, Boolean registrationStatus, String eventType, String eventName, LocalDate startDate, LocalDate endDate, Location location, PilotDTO pilotDirector) {
        this.eventId = eventId;
        this.registrationStatus = registrationStatus;
        this.eventType = eventType;
        this.eventName = eventName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.pilotDirector = pilotDirector;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Boolean isRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(Boolean registrationStatus) {
        this.registrationStatus = registrationStatus;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public PilotDTO getPilotDirector() {
        return pilotDirector;
    }

    public void setPilotDirector(PilotDTO pilotDirector) {
        this.pilotDirector = pilotDirector;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "EventDTO{" +
                "eventId=" + eventId +
                ", registrationStatus=" + registrationStatus +
                ", eventType='" + eventType + '\'' +
                ", eventName='" + eventName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", location=" + location +
                ", pilotDirector=" + pilotDirector +
                '}';
    }
}
