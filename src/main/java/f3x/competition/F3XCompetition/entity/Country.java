package f3x.competition.F3XCompetition.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.SqlFragmentAlias;

import javax.persistence.*;

@Entity
@Table(name="country")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "country_id_generator")
    @SequenceGenerator(name="country_id_generator",initialValue = 1,sequenceName = "country_id_seq")
    @Column(name="country_id")
    @JsonProperty
    private Long countryId;

    @Column(name="country_name")
    @JsonProperty
    private String countryName;

    @Column(name="country_code")
    @JsonProperty
    private String countryCode;

    public Country() {
    }

    public Country(Long countryId, String countryName, String countryCode) {
        this.countryId = countryId;
        this.countryName = countryName;
        this.countryCode = countryCode;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryId=" + countryId +
                ", countryName='" + countryName + '\'' +
                ", countryCode='" + countryCode + '\'' +
                '}';
    }
}
