package f3x.competition.F3XCompetition.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import f3x.competition.F3XCompetition.entity.Event;
import f3x.competition.F3XCompetition.enumerate.RoundStatus;

public class RoundDTO {
    @JsonProperty
    private Long roundId;
    @JsonProperty
    private Event event;
    @JsonProperty
    private short roundNumber;
    @JsonProperty
    private RoundStatus roundStatus;

    public RoundDTO() {
    }

    public RoundDTO(Long roundId, Event event, short roundNumber, RoundStatus roundStatus) {
        this.roundId = roundId;
        this.event = event;
        this.roundNumber = roundNumber;
        this.roundStatus = roundStatus;
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

    @Override
    public String toString() {
        return "RoundDTO{" +
                "roundId=" + roundId +
                ", event=" + event +
                ", roundNumber=" + roundNumber +
                ", roundStatus=" + roundStatus +
                '}';
    }
}
