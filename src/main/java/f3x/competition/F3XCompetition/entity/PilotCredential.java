package f3x.competition.F3XCompetition.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="pilot_credential")
public class PilotCredential {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "pilot_credential_id_generator")
    @SequenceGenerator(name="pilot_credential_id_generator",initialValue = 1,sequenceName = "pilot_credential_id_seq")
    private Long credentialId;

    @Column(name="password")
    private String password;

    @Column(name="username")
    private String username;

   /* @OneToOne
    @JoinColumn(name="pilot_id")
    private Pilot pilot;*/

   // private List<String> roles;

    public PilotCredential() {
    }

    public PilotCredential(String password, String username/*, Pilot pilot,  List<String> roles*/) {
        this.password = password;
        this.username = username;
       // this.pilot = pilot;
        //this.roles = roles;
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

   /* public Pilot getPilot() {
        return pilot;
    }

    public void setPilot(Pilot pilot) {
        this.pilot = pilot;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    } */
}
