package f3x.competition.F3XCompetition.controller;

import f3x.competition.F3XCompetition.entity.Event;
import f3x.competition.F3XCompetition.entity.Pilot;
import f3x.competition.F3XCompetition.repository.EventRepository;
import f3x.competition.F3XCompetition.repository.PilotRepository;
import f3x.competition.F3XCompetition.repository.RoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventRepository eventRepository;
    private final RoundRepository roundRepository;
    private final PilotRepository pilotRepository;

    @Autowired
    public EventController(EventRepository eventRepository, RoundRepository roundRepository, PilotRepository pilotRepository) {
        this.eventRepository = eventRepository;
        this.roundRepository = roundRepository;
        this.pilotRepository = pilotRepository;
    }

    @GetMapping("/")
    public List<Event> getAll() {
        return this.eventRepository.findAll();
    }

    @GetMapping("/{eventId}")
    public Optional<Event> getById(@PathVariable Long eventId) {
        return this.eventRepository.findById(eventId);
    }

    @PostMapping("/")
    public void addEvent(@RequestBody Event event) {
        this.eventRepository.save(event);
    }

    @PostMapping("/{eventId}/pilots")
    public void addPilotToEvent(@PathVariable Long eventId,@RequestBody Pilot pilot) {
        Event tmpEvent = this.eventRepository.getOne(eventId);
        tmpEvent.addPilot(pilot);
        this.eventRepository.save(tmpEvent);
    }
}
