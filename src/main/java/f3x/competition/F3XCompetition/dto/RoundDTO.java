package f3x.competition.F3XCompetition.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RoundDTO {

    @JsonProperty
    private Long roundId;
    @JsonProperty
    private short roundNumber;
    @JsonProperty
    private Boolean roundStatus;
    @JsonProperty
    private List<FlightDTO> flightList;

    public RoundDTO() {
    }

    public RoundDTO(Long roundId, short roundNumber, Boolean roundStatus, List<FlightDTO> flightList) {
        this.roundId = roundId;
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

    public List<FlightDTO> getFlightList() {
        return flightList;
    }

    public void setFlightList(List<FlightDTO> flightList) {
        this.flightList = flightList;
    }

    @Override
    public String toString() {
        return "RoundDTO{" +
                "roundId=" + roundId +
                ", roundNumber=" + roundNumber +
                ", roundStatus=" + roundStatus +
                '}';
    }
}
