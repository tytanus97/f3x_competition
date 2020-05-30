package f3x.competition.F3XCompetition.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name="competition_class")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompetitionClass {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "competition_class_id_generator")
    @SequenceGenerator(name="competition_class_id_generator",initialValue = 1,sequenceName = "competition_class_id_seq")
    @Column(name="competition_class_id")
    @JsonProperty
    private Long competitionClassId;

    @Column(name="competition_class_name")
    private String competitionClassName;

    public CompetitionClass() {
    }

    public CompetitionClass(Long competitionClassId, String competitionClassName) {
        this.competitionClassId = competitionClassId;
        this.competitionClassName = competitionClassName;
    }

    public Long getCompetitionClassId() {
        return competitionClassId;
    }

    public void setCompetitionClassId(Long competitionClassId) {
        this.competitionClassId = competitionClassId;
    }

    public String getCompetitionClassName() {
        return competitionClassName;
    }

    public void setCompetitionClassName(String competitionClassName) {
        this.competitionClassName = competitionClassName;
    }

    @Override
    public String toString() {
        return "CompetitionClass{" +
                "competitionClassId=" + competitionClassId +
                ", competitionClassName='" + competitionClassName + '\'' +
                '}';
    }
}
