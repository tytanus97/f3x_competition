package f3x.competition.F3XCompetition.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
    private Boolean roundStatus;


    @OneToMany(mappedBy = "round",fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.REMOVE})
    private List<Flight> flightList;

    public Round() {
    }


    public Round(Event event, short roundNumber, Boolean roundStatus, List<Flight> flightList) {
        this.event = event;
        this.roundNumber = roundNumber;
        this.roundStatus = roundStatus;
        this.flightList = flightList;
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

    public Boolean getRoundStatus() {
        return roundStatus;
    }

    public void setRoundStatus(Boolean roundStatus) {
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

    public List<Flight> getFlightList() {
        return flightList;
    }

    public void setFlightList(List<Flight> flightList) {
        this.flightList = flightList;
    }

    public void addFlight(Flight flight) {
        if(this.flightList == null) {
            this.flightList = new ArrayList<>();
        }
        this.flightList.add(flight);
        flight.setRound(this);
    }

    public void removeFlight(Flight flight) {
        if(this.flightList != null && !this.flightList.isEmpty()) {
            this.flightList.remove(flight);
            flight.setRound(null);
        }
    }

    @Override
    public String toString() {
        return "Round{" +
                "round_id=" + roundId +
                ", roundNumber=" + roundNumber +
                ", roundStatus=" + roundStatus +
                '}';
    }
}
