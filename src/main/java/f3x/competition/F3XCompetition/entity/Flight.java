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
    @SequenceGenerator(name="flight_id_generator",initialValue = 1,sequenceName = "flight_id_seq",allocationSize = 1)
    @Column(name="flight_id")
    private Long flightId;

    @ManyToOne
    @JoinColumn(name="pilot_id")
    @JsonIgnore
    private Pilot pilot;


    @ManyToOne
    @JoinColumn(name="round_id")
    private Round round;

    @Column(name="flight_duration")
    private int flightDuration;

    @Column(name="flight_landing")
    private int flightLanding;

    @Column(name="flight_penalty")
    private int flightPenalty;


    public Flight() {
    }

    public Flight(Pilot pilot, Round round, int flightDuration, int flightLanding, int flightPenalty) {
        this.pilot = pilot;
        this.round = round;
        this.flightDuration = flightDuration;
        this.flightLanding = flightLanding;
        this.flightPenalty = flightPenalty;
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

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    public int getFlightDuration() {
        return flightDuration;
    }

    public void setFlightDuration(int flightDuration) {
        this.flightDuration = flightDuration;
    }

    public int getFlightLanding() {
        return flightLanding;
    }

    public void setFlightLanding(int flightLanding) {
        this.flightLanding = flightLanding;
    }

    public int getFlightPenalty() {
        return flightPenalty;
    }

    public void setFlightPenalty(int flightPenalty) {
        this.flightPenalty = flightPenalty;
    }
}
