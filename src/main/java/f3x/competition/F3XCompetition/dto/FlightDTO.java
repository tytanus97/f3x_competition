package f3x.competition.F3XCompetition.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FlightDTO {


    @JsonProperty
    private Long flightId;
    @JsonProperty
    private int flightDuration;
    @JsonProperty
    private int flightLanding;
    @JsonProperty
    private int flightPenalty;

    public FlightDTO() {
    }

    public FlightDTO(Long flightId, PilotDTO pilotDTO, RoundDTO roundDTO, int flightDuration, int flightLanding, int flightPenalty) {
        this.flightId = flightId;
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

    @Override
    public String toString() {
        return "FlightDTO{" +
                "flightId=" + flightId +
                ", flightDuration=" + flightDuration +
                ", flightLanding=" + flightLanding +
                ", flightPenalty=" + flightPenalty +
                '}';
    }
}
