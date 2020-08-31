package f3x.competition.F3XCompetition.entity;

import javax.persistence.*;
import java.time.LocalDate;
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

    @Column(name="event_type")
    private String eventType;

    @Column(name="registration_status")
    private Boolean registrationStatus;

    @Column(name="event_status")
    private Boolean eventStatus;

    @Column(name="event_name")
    private String eventName;

    @ManyToOne
    @JoinColumn(name="location_id")
    private Location location;

    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name="pilot_event",joinColumns = {@JoinColumn(name="event_id")},
    inverseJoinColumns = {@JoinColumn(name="pilot_id")})
    private List<Pilot> pilotList;

    @OneToMany(mappedBy = "event",fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.REMOVE,CascadeType.PERSIST})
    private List<Round> roundList;


    @ManyToOne
    @JoinColumn(name="pilot_director_id")
    private Pilot pilotDirector;

    @Column(name="start_date")
    private LocalDate startDate;

    @Column(name="end_date")
    private LocalDate endDate;

    public Event() {
    }

    public Event(String eventType, Boolean registrationStatus, Boolean eventStatus, String eventName, Location location,
                 List<Pilot> pilotList, List<Round> roundList, Pilot pilotDirector, LocalDate startDate, LocalDate endDate) {
        this.eventType = eventType;
        this.registrationStatus = registrationStatus;
        this.eventStatus = eventStatus;
        this.eventName = eventName;
        this.location = location;
        this.pilotList = pilotList;
        this.roundList = roundList;
        this.pilotDirector = pilotDirector;
        this.startDate = startDate;
        this.endDate = endDate;
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
        return this.pilotList;
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

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Boolean isRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(Boolean registrationStatus) {
        this.registrationStatus = registrationStatus;
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


    public Pilot getPilotDirector() {
        return pilotDirector;
    }

    public void setPilotDirector(Pilot pilotDirector) {
        this.pilotDirector = pilotDirector;
    }

    public Boolean getRegistrationStatus() {
        return registrationStatus;
    }

    public Boolean getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(Boolean eventStatus) {
        this.eventStatus = eventStatus;
    }
}
