package f3x.competition.F3XCompetition.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name="pilot_credential")
public class PilotCredential {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "pilot_credential_id_generator")
    @SequenceGenerator(name="pilot_credential_id_generator",initialValue = 1,sequenceName = "pilot_credential_id_seq")
    private Long credentialId;

    @Column(name="password")
    @JsonProperty
    private String password;

    @Column(name="username")
    @JsonProperty
    private String username;

    @OneToOne
    @JoinColumn(name="pilot_id")
    private Pilot pilot;


    public PilotCredential() {
    }

    public PilotCredential(String password, String username ) {
        this.password = password;
        this.username = username;

    }

    public PilotCredential(String password, String username, Pilot pilot ) {
        this.password = password;
        this.username = username;
        this.pilot = pilot;

    }



    public Long getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Long credentialId) {
        this.credentialId = credentialId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Pilot getPilot() {
        return pilot;
    }

    public void setPilot(Pilot pilot) {
        this.pilot = pilot;
    }


}
