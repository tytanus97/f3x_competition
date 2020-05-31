package f3x.competition.F3XCompetition;

import f3x.competition.F3XCompetition.entity.Country;
import f3x.competition.F3XCompetition.entity.Location;
import f3x.competition.F3XCompetition.repository.CountryRepository;
import f3x.competition.F3XCompetition.repository.TestRepositoryLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Optional;

@SpringBootApplication
@EnableSwagger2
public class F3XCompetitionApplication {

	@Autowired
	private static TestRepositoryLocation testRepositoryLocation;

	@Autowired
	private static CountryRepository countryRepository;

	public static void main(String[] args) {
		SpringApplication.run(F3XCompetitionApplication.class, args);

		/*Optional<Country> c1 = countryRepository.findByCountryId((long)1);
		System.out.println(c1.isPresent()?c1.get().toString():"nic");*/

		
	}

}
