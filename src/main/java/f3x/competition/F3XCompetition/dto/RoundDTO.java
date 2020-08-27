package f3x.competition.F3XCompetition.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import f3x.competition.F3XCompetition.entity.Event;
import f3x.competition.F3XCompetition.entity.Flight;

import java.util.List;

public class RoundDTO {
    @JsonProperty
    private Long roundId;
    @JsonProperty
    private EventDTO eventDTO;
    @JsonProperty
    private short roundNumber;
    @JsonProperty
    private Boolean roundOpened;
    @JsonProperty
    private List<Flight> flightList;


    public RoundDTO() {
    }

    public RoundDTO(Long roundId, EventDTO eventDTO, short roundNumber, Boolean roundOpened, List<Flight> flightList) {
        this.roundId = roundId;
        this.eventDTO = eventDTO;
        this.roundNumber = roundNumber;
        this.roundOpened = roundOpened;
        this.flightList = flightList;
    }

    public Long getRoundId() {
        return roundId;
    }

    public void setRoundId(Long roundId) {
        this.roundId = roundId;
    }

    public EventDTO getEventDTO() {
        return eventDTO;
    }

    public void setEventDTO(EventDTO eventDTO) {
        this.eventDTO = eventDTO;
    }

    public short getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(short roundNumber) {
        this.roundNumber = roundNumber;
    }

    public Boolean getRoundOpened() {
        return roundOpened;
    }

    public void setRoundOpened(Boolean roundOpened) {
        this.roundOpened = roundOpened;
    }

    public List<Flight> getFlightList() {
        return flightList;
    }

    public void setFlightList(List<Flight> flightList) {
        this.flightList = flightList;
    }

    @Override
    public String toString() {
        return "RoundDTO{" +
                "roundId=" + roundId +
                ", event=" + eventDTO +
                ", roundNumber=" + roundNumber +
                ", roundStatus=" + roundOpened +
                '}';
    }
}
