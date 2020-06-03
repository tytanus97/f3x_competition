package f3x.competition.F3XCompetition.controller;

import f3x.competition.F3XCompetition.entity.Event;
import f3x.competition.F3XCompetition.entity.Flight;
import f3x.competition.F3XCompetition.entity.Pilot;
import f3x.competition.F3XCompetition.entity.Round;
import f3x.competition.F3XCompetition.service.EventService;
import f3x.competition.F3XCompetition.service.PilotService;
import f3x.competition.F3XCompetition.service.RoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;
    private final RoundService roundService;
    private final PilotService pilotService;

    @Autowired
    public EventController(EventService eventService, RoundService roundService, PilotService pilotService) {
        this.eventService = eventService;
        this.roundService = roundService;
        this.pilotService = pilotService;
    }

    @GetMapping("/")
    public List<Event> getAll() {
        return this.eventService.getAll();
    }

    @GetMapping("/{eventId}")
    public Optional<Event> getById(@PathVariable Long eventId) {
        return this.eventService.getById(eventId);
    }

    @GetMapping("/{eventId}/rounds")
    public List<Round> getAllRounds(@PathVariable Long eventId) {
        Optional<Event> tmpEvent = this.eventService.getById(eventId);
        return tmpEvent.map(Event::getRoundList).orElse(null);
    }

    @GetMapping("/{eventId}/pilots")
    public List<Pilot> getEventPilots(@PathVariable Long eventId) {
        Optional<Event> tmpEvent = this.eventService.getById(eventId);
        return tmpEvent.map(Event::getPilotList).orElse(null);
    }
    @GetMapping("/{eventId}/rounds/{roundId}/flights")
    public List<Flight> getEventRoundFlights(@PathVariable Long eventId,@PathVariable Long roundId) {
        Optional<Round> tmpRound = this.roundService.getById(roundId);
        return tmpRound.map(Round::getRoundFlights).orElse(null);
    }



    @PostMapping("/")
    public void addEvent(@RequestBody Event event) {
        this.eventService.saveEvent(event);
    }

    @PostMapping("/{eventId}/pilots")
    public void addPilotToEvent(@PathVariable Long eventId,@RequestParam("pilotId") Long pilotId) {
        Optional<Event> tmpEvent = this.eventService.getById(eventId);
        Optional<Pilot> tmpPilot = this.pilotService.getById(pilotId);

        tmpEvent.ifPresent(event-> {
                tmpPilot.ifPresent(pilot->{
                    pilot.addEvent(event);
                    this.pilotService.savePilot(pilot);
                });
        });
    }

    @PostMapping("/{eventId}/rounds")
    public void addRoundToEvent(@RequestBody Round round,@PathVariable Long eventId) {
        Optional<Event> tmpEvent = this.eventService.getById(eventId);
        tmpEvent.ifPresent(event-> {
            round.setEvent(event);
            this.roundService.saveRound(round);
        });
    }

    @DeleteMapping("/{eventId}/rounds/{roundId}")
    public void deleteRoundFromEvent(@PathVariable Long eventId,@PathVariable Long roundId) {

        Optional<Event> tmpEvent = this.eventService.getById(eventId);
        Optional<Round> tmpRound = this.roundService.getById(roundId);

        tmpEvent.ifPresent(event->{
            tmpRound.ifPresent(round->{
                round.setEvent(null);
                this.roundService.removeRound(round);
            });
        });
    }

    @DeleteMapping("/{eventId}/pilots/{pilotId}")
    public void removePilotFromEvent(@PathVariable Long eventId,@PathVariable Long pilotId) {
        Optional<Event> tmpEvent = this.eventService.getById(eventId);
        Optional<Pilot> tmpPilot = this.pilotService.getById(pilotId);

        tmpEvent.ifPresent(event-> {
            tmpPilot.ifPresent(pilot->{
                pilot.removeEvent(event);
                this.pilotService.savePilot(pilot);
                });
        });
    }
}
