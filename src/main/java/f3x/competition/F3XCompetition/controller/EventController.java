package f3x.competition.F3XCompetition.controller;

import f3x.competition.F3XCompetition.dto.EventDTO;
import f3x.competition.F3XCompetition.dto.FlightDTO;
import f3x.competition.F3XCompetition.dto.RoundDTO;
import f3x.competition.F3XCompetition.entity.Event;
import f3x.competition.F3XCompetition.entity.Flight;
import f3x.competition.F3XCompetition.entity.Pilot;
import f3x.competition.F3XCompetition.entity.Round;
import f3x.competition.F3XCompetition.service.EventService;
import f3x.competition.F3XCompetition.service.FlightService;
import f3x.competition.F3XCompetition.service.PilotService;
import f3x.competition.F3XCompetition.service.RoundService;
import f3x.competition.F3XCompetition.serviceImpl.EventServiceImpl;
import f3x.competition.F3XCompetition.serviceImpl.FlightServiceImpl;
import f3x.competition.F3XCompetition.serviceImpl.PilotServiceImpl;
import f3x.competition.F3XCompetition.serviceImpl.RoundServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class EventController {

    private final EventService eventService;
    private final RoundService roundService;
    private final PilotService pilotService;
    private final FlightService flightService;

    @Autowired
    public EventController(EventService eventService, RoundService roundService, PilotService pilotService, FlightService flightService) {
        this.eventService = eventService;
        this.roundService = roundService;
        this.pilotService = pilotService;
        this.flightService = flightService;
    }

    @GetMapping("/")
    public ResponseEntity<List<EventDTO>> getAll() {
        return new ResponseEntity<>(this.eventService.findAll().stream().map(((EventServiceImpl)this.eventService)::eventToEventDTO)
                .collect(Collectors.toList()),HttpStatus.OK);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EventDTO> findById(@PathVariable Long eventId)
    {
        Optional<Event> tmpEvent = this.eventService.findById(eventId);
        return tmpEvent.map(event -> new ResponseEntity<>(((EventServiceImpl)this.eventService).eventToEventDTO(event),HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @GetMapping("/{eventId}/rounds")
    public ResponseEntity<List<RoundDTO>> getAllRounds(@PathVariable Long eventId) {

        Optional<Event> tmpEvent = this.eventService.findById(eventId);
        List<RoundDTO> roundList = tmpEvent.map(event -> event.getRoundList().stream()
                .map(round -> {
                    System.out.println(round.getFlightList() + "id rundy: " + round.getRoundId());

                   return ((RoundServiceImpl)this.roundService).roundToRoundDTO(round);
                })
                .collect(Collectors.toList())).orElse(null);
        if(roundList != null) {
            for (RoundDTO round : roundList) {
                System.out.println(round.getFlightList().toString());
            }
        }
        return new ResponseEntity<>(roundList,HttpStatus.OK);
    }

    @GetMapping("/{eventId}/pilots")
    public ResponseEntity findEventPilots(@PathVariable Long eventId) {
        Optional<Event> tmpEvent = this.eventService.findById(eventId);
        return tmpEvent.map(event -> new ResponseEntity<>(event.getPilotList()
                .stream().map(((PilotServiceImpl) this.pilotService)::pilotToPilotDTO)
                .collect(Collectors.toList()),HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{eventId}/rounds/{roundId}/flights")
    public List<Flight> getEventRoundFlights(@PathVariable Long eventId,@PathVariable Long roundId) {
        Optional<Round> tmpRound = this.roundService.findById(roundId);
        return tmpRound.map(Round::getFlightList).orElse(null);
    }

    @PutMapping("/{eventId}")
    public ResponseEntity updateEvent(@RequestBody Event event, @PathVariable Long eventId) {
        Optional<Event> tmpEvent = this.eventService.findById(eventId);

        if(tmpEvent.isPresent()) {
            this.eventService.saveEvent(event);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/")
    public ResponseEntity<EventDTO> addEvent(@RequestBody EventDTO eventDTO) {
        Event event = ((EventServiceImpl)this.eventService).eventDTOtoEvent(eventDTO);
        EventDTO resultEventDTO = ((EventServiceImpl)this.eventService).eventToEventDTO(this.eventService.saveEvent(event));
        System.out.println(resultEventDTO.getPilotDirector().toString());
        return new ResponseEntity<>(resultEventDTO,HttpStatus.OK);
    }

    @PostMapping("/{eventId}/pilots/{pilotId}")
    public ResponseEntity addPilotToEvent(@PathVariable Long eventId,@PathVariable Long pilotId) {
        Optional<Event> tmpEvent = this.eventService.findById(eventId);
        Optional<Pilot> tmpPilot = this.pilotService.getById(pilotId);

        if(tmpEvent.isEmpty() || tmpPilot.isEmpty()) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if(!tmpEvent.get().getPilotList().contains(tmpPilot.get())) {
            Event resultEvent = this.eventService.addPilotToEvent(tmpEvent.get(),tmpPilot.get());
            return new ResponseEntity<>(((EventServiceImpl)this.eventService).eventToEventDTO(resultEvent),HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.ALREADY_REPORTED);
    }

    @PostMapping("/{eventId}/rounds")
    public ResponseEntity<RoundDTO> addRoundToEvent(@RequestBody RoundDTO roundDTO, @PathVariable Long eventId) {
            Optional<Event> tmpEvent = this.eventService.findById(eventId);
            Round round = ((RoundServiceImpl)this.roundService).roundDTOtoRound(roundDTO);
            tmpEvent.ifPresent(round::setEvent);
            round = this.roundService.saveRound(round);
            return new ResponseEntity<>(((RoundServiceImpl)this.roundService)
                    .roundToRoundDTO(round),HttpStatus.OK);
    }

    @PostMapping("/rounds/{roundId}/flights")
    public ResponseEntity addFlightsToRound(@RequestBody FlightDTO flightDTO, @PathVariable Long roundId) {

        Optional<Round> tmpRound = this.roundService.findById(roundId);
        Flight flight = ((FlightServiceImpl)this.flightService).flightDTOtoFlight(flightDTO);

        return tmpRound.map(round -> {
            flight.setRound(round);
            if(this.flightService.saveFlight(flight) == null) {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity(HttpStatus.CREATED);
        }).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PatchMapping("/{eventId}/registrationStatus")
    public ResponseEntity changeEventRegisterStatus(@PathVariable Long eventId,@RequestBody String registrationStatusStr) {
        Boolean registrationStatus = Boolean.valueOf(registrationStatusStr);
        Optional<Event> tmpEvent = this.eventService.findById(eventId);
        return tmpEvent.map(event -> {
        event.setRegistrationStatus(registrationStatus);
        return new ResponseEntity<>(((EventServiceImpl)this.eventService)
                .eventToEventDTO(this.eventService.saveEvent(event)),HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PutMapping("/rounds/{roundId}/finalizeRound")
    public ResponseEntity finalizeRound(@PathVariable Long roundId) {
        Optional<Round> tmpRound = this.roundService.findById(roundId);
        boolean isRoundFinalized = this.roundService.finalizeRound(tmpRound);
        return isRoundFinalized?new ResponseEntity(HttpStatus.OK): new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{eventId}/rounds/{roundId}")
    public void deleteRoundFromEvent(@PathVariable Long eventId,@PathVariable Long roundId) {

        Optional<Event> tmpEvent = this.eventService.findById(eventId);
        Optional<Round> tmpRound = this.roundService.findById(roundId);

        tmpEvent.ifPresent(event->{
            tmpRound.ifPresent(round->{
                round.setEvent(null);
                this.roundService.removeRound(round);
            });
        });
    }

    @DeleteMapping("/{eventId}/pilots/{pilotId}")
    public void removePilotFromEvent(@PathVariable Long eventId,@PathVariable Long pilotId) {
        Optional<Event> tmpEvent = this.eventService.findById(eventId);
        Optional<Pilot> tmpPilot = this.pilotService.getById(pilotId);

        tmpEvent.ifPresent(event-> {
            tmpPilot.ifPresent(pilot -> {
                this.eventService.removePilotFromEvent(event,pilot);
            });
        });
    }
    @DeleteMapping("/{flightId}")
    public void deleteFlightFromRound(@PathVariable Long flightId) {
            this.flightService.deleteFlightByFlightId(flightId);
    }
}
