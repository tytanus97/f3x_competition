package f3x.competition.F3XCompetition;

import f3x.competition.F3XCompetition.entity.Country;
import f3x.competition.F3XCompetition.entity.PilotCredential;
import f3x.competition.F3XCompetition.repository.CountryRepository;
import f3x.competition.F3XCompetition.repository.EventRepository;
import f3x.competition.F3XCompetition.repository.LocationRepository;
import f3x.competition.F3XCompetition.repository.PilotCredentialRepository;
import f3x.competition.F3XCompetition.service.EventService;
import f3x.competition.F3XCompetition.utils.ImagesUploadLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@EnableSwagger2
@EnableConfigurationProperties({ImagesUploadLocation.class})
@EnableScheduling
public class F3XCompetitionApplication {

	@Autowired
	private PilotCredentialRepository pilotCredentialRepository;
	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private EventService eventService;

	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(F3XCompetitionApplication.class, args);

	}

	@Scheduled(cron = "0 0 * * * *")
	public void checkEventStatus() throws InterruptedException {
		this.eventService.checkEventStatusTrue();
	}

	@PostConstruct
	public void addDefaultUserCredits() {
		List<PilotCredential> pilotCredentialsList = Stream.of(
				new PilotCredential(this.passwordEncoder.encode("abc"),"abc"),
				new PilotCredential(this.passwordEncoder.encode("test"),"test")

		).collect(Collectors.toList());
		List<Country> countryList = Stream.of(
				new Country(0L,"Polska","POL"),
				new Country(0L,"Niemcy","GER"),
				new Country(0L,"Czechy","CZE")
		).collect(Collectors.toList());

		//this.pilotCredentialRepository.deleteAll();
		this.eventRepository.deleteAll();
		//this.locationRepository.deleteAll();
		//this.countryRepository.deleteAll();
		//this.countryRepository.saveAll(countryList);
		//this.pilotCredentialRepository.saveAll(pilotCredentialsList);
	}

}
