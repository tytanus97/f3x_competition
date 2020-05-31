package f3x.competition.F3XCompetition.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name="flight")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "flight_id_generator")
    @SequenceGenerator(name="flight_id_generator",initialValue = 1,sequenceName = "flight_id_seq")
    @Column(name="flight_id")
    @JsonProperty
    private Long flightId;

    @ManyToOne
    @JoinColumn(name="pilot_id")
    @JsonIgnore
    private Pilot pilot;

    @ManyToOne
    @JoinColumn(name="event_id")
    @JsonIgnore
    private Event event;

    @ManyToOne
    @JoinColumn(name="round_id")
    @JsonIgnore
    private Round round;

    @OneToOne
    @JoinColumn(name="competition_class_id")
    @JsonProperty
    private CompetitionClass competitionClass;

    @OneToOne
    @JoinColumn(name="stats_id")
    @JsonProperty
    private Stats stats;

    public Flight() {
    }

    public Flight(Pilot pilot, Event event, Round round, CompetitionClass competitionClass, Stats stats) {
        this.pilot = pilot;
        this.event = event;
        this.round = round;
        this.competitionClass = competitionClass;
        this.stats = stats;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public Pilot getPilot() {
        return pilot;
    }

    public void setPilot(Pilot pilot) {
        this.pilot = pilot;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    public CompetitionClass getCompetitionClass() {
        return competitionClass;
    }

    public void setCompetitionClass(CompetitionClass competitionClass) {
        this.competitionClass = competitionClass;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightId=" + flightId +
                ", competitionClass=" + competitionClass +
                '}';
    }
}