package f3x.competition.F3XCompetition.response;

public class AuthenticationResponse {

    private final String jwt;
    private final Long pilotId;

    public AuthenticationResponse(String jwt,Long pilotId) {
        this.jwt = jwt;
        this.pilotId = pilotId;
    }

    public String getJwt() {
        return jwt;
    }

    public Long getPilotId() {
        return pilotId;
    }
}
