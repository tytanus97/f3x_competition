package f3x.competition.F3XCompetition.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="event")
public class Event {

    @Id
    @Column(name="event_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "event_id_generator")
    @SequenceGenerator(name="event_id_generator",initialValue = 1,sequenceName = "event_id_seq",allocationSize = 1)
    private Long eventId;

    @Column(name="event_round_count")
    private byte eventRoundCount;


    @Column(name="event_name")
    private String eventName;

    @ManyToOne
    @JoinColumn(name="location_id")
    private Location location;

    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name="pilot_event",joinColumns = {@JoinColumn(name="event_id")},
    inverseJoinColumns = {@JoinColumn(name="pilot_id")})
    @JsonIgnore
    private List<Pilot> pilotList;

    @OneToMany(mappedBy = "event",fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.REMOVE,CascadeType.PERSIST})
    private List<Round> roundList;

    @Column(name="registration_open")
    private boolean registrationOpen;

    @Column(name="start_date")
    private Timestamp startDate = new Timestamp(System.currentTimeMillis());

    @Column(name="end_date")
    private Timestamp endDate = new Timestamp(System.currentTimeMillis()+36000);

    public Event() {
    }

    public Event(byte eventRoundCount, String eventName, Location location, List<Pilot> pilotList,
                 List<Round> roundList, Timestamp startDate, Timestamp endDate,boolean registrationOpen) {

        this.eventRoundCount = eventRoundCount;
        this.eventName = eventName;
        this.location = location;
        this.pilotList = pilotList;
        this.roundList = roundList;
        this.startDate = startDate;
        this.endDate = endDate;
        this.registrationOpen = registrationOpen;
    }

    public List<Round> getRoundList() {
        return roundList;
    }

    public void setRoundList(List<Round> roundList) {
        this.roundList = roundList;
    }

    public void addRound(Round round) {
        if(this.roundList == null) {
            this.roundList = new ArrayList<>();
        }
        this.roundList.add(round);
        round.setEvent(this);
    }

    public void removeRound(Round round) {
        if(this.roundList != null && !this.roundList.isEmpty()) {
            this.roundList.remove(round);
            round.setEvent(null);
        }

    }

    public List<Pilot> getPilotList() {
        return pilotList;
    }

    public void setPilotList(List<Pilot> pilotList) {
        this.pilotList = pilotList;
    }

    public void addPilot(Pilot pilot) {
        if(this.pilotList == null) {
            this.pilotList = new ArrayList<>();
        }
        this.pilotList.add(pilot);
    }

    public void removePilot(Pilot pilot) {
        if(this.pilotList != null && !this.pilotList.isEmpty()) {
            this.pilotList.remove(pilot);
        }
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

    public boolean isRegistrationOpen() {
        return registrationOpen;
    }

    public void setRegistrationOpen(boolean registrationOpen) {
        this.registrationOpen = registrationOpen;
    }

}
