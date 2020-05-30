package f3x.competition.F3XCompetition.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalTime;

@Entity
@Table(name="stats")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Stats {

    private Long statsId;
    private Flight flight;
    private LocalTime flight_duration;
}
