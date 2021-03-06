package f3x.competition.F3XCompetition.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "plane")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Plane {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "plane_id_generator")
    @SequenceGenerator(name="plane_id_generator",initialValue = 1,sequenceName = "plane_id_seq",allocationSize = 1)
    @Column(name="plane_id")
    private Long planeId;

    @Column(name="plane_wing_span")
    private float planeWingSpan;

    @Column(name="plane_color")
    private String planeColor;

    @Column(name="plane_weight")
    private float planeWeight;

    @Column(name="plane_name")
    private String planeName;

    @ManyToOne
    @JoinColumn(name="pilot_id")
    private Pilot pilot;

    public Plane() {
    }

    public Plane(float planeWingSpan, String planeColor, String planeName, Pilot pilot,float planeWeight) {
        this.planeWingSpan = planeWingSpan;
        this.planeColor = planeColor;
        this.planeName = planeName;
        this.pilot = pilot;
        this.planeWeight = planeWeight;
    }

    public Long getPlaneId() {
        return planeId;
    }

    public void setPlaneId(Long planeId) {
        this.planeId = planeId;
    }

    public float getPlaneWingSpan() {
        return planeWingSpan;
    }

    public void setPlaneWingSpan(float planeWingSpan) {
        this.planeWingSpan = planeWingSpan;
    }

    public String getPlaneColor() {
        return planeColor;
    }

    public void setPlaneColor(String planeColor) {
        this.planeColor = planeColor;
    }

    public String getPlaneName() {
        return planeName;
    }

    public void setPlaneName(String planeName) {
        this.planeName = planeName;
    }

    public Pilot getPilot() {
        return pilot;
    }

    public void setPilot(Pilot pilot) {
        this.pilot = pilot;
    }

    public float getPlaneWeight() {
        return planeWeight;
    }

    public void setPlaneWeight(float planeWeight) {
        this.planeWeight = planeWeight;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "planeId=" + planeId +
                ", planeWingSpan=" + planeWingSpan +
                ", planeColor='" + planeColor + '\'' +
                ", planeWeight=" + planeWeight +
                ", planeName='" + planeName + '\''+
                '}';
    }
}
