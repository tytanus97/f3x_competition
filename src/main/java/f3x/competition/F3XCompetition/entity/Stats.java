package f3x.competition.F3XCompetition.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name="stats")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Stats {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "stats_id_generator")
    @SequenceGenerator(name="stats_id_generator",initialValue = 1,sequenceName = "stats_id_seq")
    @Column(name="stats_id")
    @JsonProperty
    private Long statsId;

    @OneToOne
    @JoinColumn(name="flight_id")
    @JsonProperty
    private Flight flight;

    @Column(name="flight_duration")
    @JsonProperty
    private LocalTime flightDuration;

    @Column(name="laps")
    @JsonProperty
    private int laps;

    @Column(name="landing")
    @JsonProperty
    private int landing;

    @Column(name="score")
    @JsonProperty
    private float score;


    public Stats() {
    }

    public Stats(Flight flight, LocalTime flightDuration, int laps, int landing, float score) {
        this.flight = flight;
        this.flightDuration = flightDuration;
        this.laps = laps;
        this.landing = landing;
        this.score = score;
    }

    public Long getStatsId() {
        return statsId;
    }

    public void setStatsId(Long statsId) {
        this.statsId = statsId;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public LocalTime getFlightDuration() {
        return flightDuration;
    }

    public void setFlightDuration(LocalTime flightDuration) {
        this.flightDuration = flightDuration;
    }

    public int getLaps() {
        return laps;
    }

    public void setLaps(int laps) {
        this.laps = laps;
    }

    public int getLanding() {
        return landing;
    }

    public void setLanding(int landing) {
        this.landing = landing;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Stats{" +
                "statsId=" + statsId +
                ", flightDuration=" + flightDuration +
                ", laps=" + laps +
                ", landing=" + landing +
                ", score=" + score +
                '}';
    }
}
