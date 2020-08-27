package f3x.competition.F3XCompetition.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import f3x.competition.F3XCompetition.enumerate.RoundStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="round")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Round {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "round_id_generator")
    @SequenceGenerator(name="round_id_generator",initialValue = 1,sequenceName = "round_id_seq",allocationSize = 1)
    @Column(name="round_id")
    private Long roundId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="event_id")
    private Event event;

    @Column(name="round_number")
    private short roundNumber;

    @Column(name="round_status")
    private RoundStatus roundStatus;


    @OneToMany(mappedBy = "round",fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.REMOVE})
    private List<Flight> roundFlights;

    public Round() {
    }

    /*public Round(Event event, short roundNumber, RoundStatus roundStatus, Timestamp roundBeginDate, Timestamp roundFinishDate, List<Flight> roundFlights) {
        this.event = event;
        this.roundNumber = roundNumber;
        this.roundStatus = roundStatus;
        this.roundBeginDate = roundBeginDate;
        this.roundFinishDate = roundFinishDate;
        this.roundFlights = roundFlights;
    }*/

    public Round(Event event, short roundNumber, RoundStatus roundStatus, List<Flight> roundFlights) {
        this.event = event;
        this.roundNumber = roundNumber;
        this.roundStatus = roundStatus;
        this.roundFlights = roundFlights;
    }

    public Long getRoundId() {
        return roundId;
    }

    public void setRoundId(Long roundId) {
        this.roundId = roundId;
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
/*
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
    }*/

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
        flight.setRound(this);
    }

    public void removeFlight(Flight flight) {
        if(this.roundFlights != null && !this.roundFlights.isEmpty()) {
            this.roundFlights.remove(flight);
            flight.setRound(null);
        }
    }

    @Override
    public String toString() {
        return "Round{" +
                "round_id=" + roundId +
                ", roundNumber=" + roundNumber +
                ", roundStatus=" + roundStatus +
              /*  ", roundBeginDate=" + roundBeginDate +
                ", roundFinishDate=" + roundFinishDate +*/
                '}';
    }
}
