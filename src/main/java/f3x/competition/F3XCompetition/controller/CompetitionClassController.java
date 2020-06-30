package f3x.competition.F3XCompetition.controller;

import f3x.competition.F3XCompetition.entity.CompetitionClass;
import f3x.competition.F3XCompetition.repository.CompetitionClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/classes")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class CompetitionClassController {

    private final CompetitionClassRepository competitionClassRepository;

    @Autowired
    public CompetitionClassController(CompetitionClassRepository competitionClassRepository) {
        this.competitionClassRepository = competitionClassRepository;
    }

    @GetMapping("/")
    public List<CompetitionClass> getAll() {
        return this.competitionClassRepository.findAll();
    }

    @GetMapping("/{competitionClassId}")
    public Optional<CompetitionClass> getById(@PathVariable Long competitionClassId) {
        return this.competitionClassRepository.findById(competitionClassId);
    }

    @PostMapping("/")
    public void addCompetitionClass(@RequestBody CompetitionClass competitionClass) {
        this.competitionClassRepository.save(competitionClass);
    }

}
