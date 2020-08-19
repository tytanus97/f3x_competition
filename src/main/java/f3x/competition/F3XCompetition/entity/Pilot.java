package f3x.competition.F3XCompetition.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="pilot")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pilot {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "pilot_id_generator")
    @SequenceGenerator(name="pilot_id_generator",initialValue = 1,sequenceName = "pilot_id_seq",allocationSize = 1)
    @Column(name="pilot_id")
    @JsonProperty
    private Long pilotId;

    @Column(name="pilot_first_name")
    @JsonProperty
    private String pilotFirstName;

    @Column(name="pilot_last_name")
    @JsonProperty
    private String pilotLastName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    @JsonProperty
    private Country country;

    @Column(name="pilot_birth_date")
    @JsonProperty
    private LocalDate pilotBirthDate;

    @Column(name="pilot_email")
    @JsonProperty
    private String pilotEmail;

    @Column(name="pilot_rating")
    @JsonProperty(defaultValue = "0")
    private float pilotRating;

    @OneToMany(mappedBy = "pilot",fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.REMOVE})
    @JsonIgnore
    private List<Plane> pilotPlanes;

    @ManyToMany(mappedBy = "pilotList",fetch = FetchType.LAZY,cascade = {CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH})
    @JsonIgnore
    private List<Event> pilotEvents;

    public Pilot() {
    }

    public Pilot(String pilotFirstName, String pilotLastName, Country country, LocalDate pilotBirthDate,
                 float pilotRating, List<Plane> pilotPlanes, List<Event> pilotEvents) {
        this.pilotFirstName = pilotFirstName;
        this.pilotLastName = pilotLastName;
        this.country = country;
        this.pilotBirthDate = pilotBirthDate;
        this.pilotRating = pilotRating;
        this.pilotPlanes = pilotPlanes;
        this.pilotEvents = pilotEvents;
    }


    public List<Plane> getPilotPlanes() {
        return pilotPlanes;
    }

    public void setPilotPlanes(List<Plane> pilotPlanes) {
        this.pilotPlanes = pilotPlanes;
    }

    public void addPlane(Plane plane) {
        if(this.pilotPlanes == null) {
            this.pilotPlanes = new ArrayList<>();
        }
        this.pilotPlanes.add(plane);
        plane.setPilot(this);
    }

    public void removePlane(Plane plane) {
        if(this.pilotPlanes != null && !this.pilotPlanes.isEmpty()) {
            this.pilotPlanes.remove(plane);
            plane.setPilot(null);
        }
    }
    public List<Event> getPilotEvents() {
        return pilotEvents;
    }
    public void setPilotEvents(List<Event> pilotEvents) {
        this.pilotEvents = pilotEvents;
    }


    public Long getPilotId() {
        return pilotId;
    }

    public void setPilotId(Long pilotId) {
        this.pilotId = pilotId;
    }

    public String getPilotFirstName() {
        return pilotFirstName;
    }

    public void setPilotFirstName(String pilotFirstName) {
        this.pilotFirstName = pilotFirstName;
    }

    public String getPilotLastName() {
        return pilotLastName;
    }

    public void setPilotLastName(String pilotLastName) {
        this.pilotLastName = pilotLastName;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public LocalDate getPilotBirthDate() {
        return pilotBirthDate;
    }

    public void setPilotBirthDate(LocalDate pilotBirthDate) {
        this.pilotBirthDate = pilotBirthDate;
    }

    public float getPilotRating() {
        return pilotRating;
    }

    public void setPilotRating(float pilotRating) {
        this.pilotRating = pilotRating;
    }

    public void addEvent(Event event) {
        if(this.pilotEvents == null) {
            this.pilotEvents = new ArrayList<>();
        }
        this.pilotEvents.add(event);
    }

    public void removeEvent(Event event) {
        if(this.pilotEvents != null && !this.pilotEvents.isEmpty()) {
            this.pilotEvents.remove(event);
        }
    }

    public String getPilotEmail() {
        return pilotEmail;
    }

    public void setPilotEmail(String pilotEmail) {
        this.pilotEmail = pilotEmail;
    }

    @Override
    public String toString() {
        return "Pilot{" +
                "pilotId=" + pilotId +
                ", pilotFirstName='" + pilotFirstName + '\'' +
                ", pilotLastName='" + pilotLastName + '\'' +
                ", country=" + country +
                ", pilotBirthDate=" + pilotBirthDate +
                ", pilotEmail='" + pilotEmail + '\'' +
                ", pilotRating=" + pilotRating +
                '}';
    }
}
