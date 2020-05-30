package f3x.competition.F3XCompetition.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name="event")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {
    @Id
    @Column(name="event_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "event_id_generator")
    @SequenceGenerator(name="event_id_generator",initialValue = 1,sequenceName = "event_id_seq")
    @JsonProperty
    private Long eventId;

    @Column(name="event_round_count")
    @JsonProperty
    private byte eventRoundCount;

    @Column(name="event_name")
    @JsonProperty
    private String eventName;

    @ManyToOne
    @JoinColumn(name="location_id")
    @JsonProperty
    private Location location;

    @ManyToOne
    @JoinColumn(name="competition_class_id")
    @JsonProperty
    private CompetitionClass competitionClass;

    @Column(name="start_date")
    @JsonProperty
    private Timestamp startDate;

    @Column(name="end_date")
    @JsonProperty
    private Timestamp endDate;


    public Event(byte eventRoundCount, String eventName, Location location, CompetitionClass competitionClass, Timestamp startDate, Timestamp endDate) {
        this.eventRoundCount = eventRoundCount;
        this.eventName = eventName;
        this.location = location;
        this.competitionClass = competitionClass;
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public CompetitionClass getCompetitionClass() {
        return competitionClass;
    }

    public void setCompetitionClass(CompetitionClass competitionClass) {
        this.competitionClass = competitionClass;
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
        return "Event{" +
                "eventId=" + eventId +
                ", eventRoundCount=" + eventRoundCount +
                ", eventName='" + eventName + '\'' +
                ", location=" + location +
                ", competitionClass=" + competitionClass +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
