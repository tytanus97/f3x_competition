package f3x.competition.F3XCompetition.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name="task")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Task {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "task_id_generator")
    @SequenceGenerator(name="task_id_generator",initialValue = 1,sequenceName = "task_id_seq")
    @Column(name="task_id")
    @JsonProperty
    private Long taskId;

    @Column(name="task_name")
    @JsonProperty
    private String taskName;

    @ManyToOne
    @JoinColumn(name="competition_class_id")
    private CompetitionClass competitionClass;

    public Task() {
    }

    public Task(String taskName, CompetitionClass competitionClass) {
        this.taskName = taskName;
        this.competitionClass = competitionClass;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public CompetitionClass getCompetitionClass() {
        return competitionClass;
    }

    public void setCompetitionClass(CompetitionClass competitionClass) {
        this.competitionClass = competitionClass;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                '}';
    }
}
