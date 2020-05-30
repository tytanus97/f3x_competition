package f3x.competition.F3XCompetition.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import f3x.competition.F3XCompetition.enumerate.RoundStatus;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="round")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Round {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "round_id_generator")
    @SequenceGenerator(name="round_id_generator",initialValue = 1,sequenceName = "round_id_seq")
    @Column(name="round_id")
    @JsonProperty
    private Long round_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="event_id")
    @JsonProperty
    private Event event;

    @Column(name="round_number")
    @JsonProperty
    private short roundNumber;

    @Column(name="round_status")
    @JsonProperty
    private RoundStatus roundStatus;

    @Column(name="round_begin_date")
    @JsonProperty
    private Timestamp roundBeginDate;

    @Column(name="round_finish_date")
    @JsonProperty
    private Timestamp roundFinishDate;

    @OneToMany(mappedBy = "round",fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.REMOVE})
    @JsonProperty
    private List<Flight> roundFlights;

    public Round() {
    }

    public Round(Event event, short roundNumber, RoundStatus roundStatus, Timestamp roundBeginDate, Timestamp roundFinishDate, List<Flight> roundFlights) {
        this.event = event;
        this.roundNumber = roundNumber;
        this.roundStatus = roundStatus;
        this.roundBeginDate = roundBeginDate;
        this.roundFinishDate = roundFinishDate;
        this.roundFlights = roundFlights;
    }

    public Long getRound_id() {
        return round_id;
    }

    public void setRound_id(Long round_id) {
        this.round_id = round_id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public short getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(short roundNumber) {
        this.roundNumber = roundNumber;
    }

    public RoundStatus getRoundStatus() {
        return roundStatus;
    }

    public void setRoundStatus(RoundStatus roundStatus) {
        this.roundStatus = roundStatus;
    }

    public Timestamp getRoundBeginDate() {
        return roundBeginDate;
    }

    public void setRoundBeginDate(Timestamp roundBeginDate) {
        this.roundBeginDate = roundBeginDate;
    }

    public Timestamp getRoundFinishDate() {
        return roundFinishDate;
    }

    public void setRoundFinishDate(Timestamp roundFinishDate) {
        this.roundFinishDate = roundFinishDate;
    }

    public List<Flight> getRoundFlights() {
        return roundFlights;
    }

    public void setRoundFlights(List<Flight> roundFlights) {
        this.roundFlights = roundFlights;
    }

    public void addFlight(Flight flight) {
        if(this.roundFlights == null) {
            this.roundFlights = new ArrayList<>();
        }
        this.roundFlights.add(flight);
    }

    public void removeFlight(Flight flight) {
        if(this.roundFlights != null && !this.roundFlights.isEmpty()) {
            this.roundFlights.remove(flight);
        }
    }

    @Override
    public String toString() {
        return "Round{" +
                "round_id=" + round_id +
                ", event=" + event +
                ", roundNumber=" + roundNumber +
                ", roundStatus=" + roundStatus +
                ", roundBeginDate=" + roundBeginDate +
                ", roundFinishDate=" + roundFinishDate +
                ", roundFlights=" + roundFlights +
                '}';
    }
}
