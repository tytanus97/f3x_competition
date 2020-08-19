package f3x.competition.F3XCompetition.dto;

import java.sql.Timestamp;

public class EventDTO {

    private Long eventId;
    private byte eventRoundCount;
    private String eventName;
    private Timestamp startDate;
    private Timestamp endDate;

    public EventDTO() {

    }

    public EventDTO(Long eventId, byte eventRoundCount, String eventName, Timestamp startDate, Timestamp endDate) {
        this.eventId = eventId;
        this.eventRoundCount = eventRoundCount;
        this.eventName = eventName;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
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
