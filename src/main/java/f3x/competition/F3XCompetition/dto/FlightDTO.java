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
    @JsonProperty
    private PilotDTO pilot;
    @JsonProperty
    private float total;

    public FlightDTO() {
    }

    public FlightDTO(Long flightId, int flightDuration, int flightLanding, int flightPenalty, PilotDTO pilot, float total) {
        this.flightId = flightId;
        this.flightDuration = flightDuration;
        this.flightLanding = flightLanding;
        this.flightPenalty = flightPenalty;
        this.pilot = pilot;
        this.total = total;
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

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public PilotDTO getPilot() {
        return pilot;
    }

    public void setPilot(PilotDTO pilot) {
        this.pilot = pilot;
    }

    @Override
    public String toString() {
        return "FlightDTO{" +
                "flightId=" + flightId +
                ", flightDuration=" + flightDuration +
                ", flightLanding=" + flightLanding +
                ", flightPenalty=" + flightPenalty +
                ", pilot=" + pilot +
                ", total=" + total +
                '}';
    }
}
