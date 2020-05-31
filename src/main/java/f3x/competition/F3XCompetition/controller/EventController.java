package f3x.competition.F3XCompetition.controller;

import f3x.competition.F3XCompetition.entity.Event;
import f3x.competition.F3XCompetition.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventRepository eventRepository;

    @Autowired
    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
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
}
