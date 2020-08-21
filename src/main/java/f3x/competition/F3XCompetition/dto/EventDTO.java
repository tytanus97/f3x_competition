package f3x.competition.F3XCompetition.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import f3x.competition.F3XCompetition.entity.Pilot;

import java.time.LocalDate;

public class EventDTO {
    @JsonProperty
    private Long eventId;
    @JsonProperty
    private byte eventRoundCount;
    @JsonProperty
    private String eventName;
    @JsonProperty
    private LocalDate startDate;
    @JsonProperty
    private LocalDate endDate;
    @JsonProperty
    private PilotDTO pilotDirector;

    public EventDTO() {

    }

    public EventDTO(Long eventId, byte eventRoundCount, String eventName, LocalDate startDate, LocalDate endDate,PilotDTO pilotDirector) {
        this.eventId = eventId;
        this.eventRoundCount = eventRoundCount;
        this.eventName = eventName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.pilotDirector = pilotDirector;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public byte getEventRoundCount() {
        return eventRoundCount;
    }

    public void setEventRoundCount(byte eventRoundCount) {
        this.eventRoundCount = eventRoundCount;
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

    @Override
    public String toString() {
        return "EventDTO{" +
                "eventId=" + eventId +
                ", eventRoundCount=" + eventRoundCount +
                ", eventName='" + eventName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
