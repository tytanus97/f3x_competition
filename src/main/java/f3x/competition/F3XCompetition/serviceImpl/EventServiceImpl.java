package f3x.competition.F3XCompetition.serviceImpl;

import f3x.competition.F3XCompetition.dto.EventDTO;
import f3x.competition.F3XCompetition.dto.PilotDTO;
import f3x.competition.F3XCompetition.entity.Event;
import f3x.competition.F3XCompetition.entity.Pilot;
import f3x.competition.F3XCompetition.entity.Round;
import f3x.competition.F3XCompetition.repository.EventRepository;
import f3x.competition.F3XCompetition.service.EventService;
import f3x.competition.F3XCompetition.service.PilotService;
import f3x.competition.F3XCompetition.service.RoundService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final PilotService pilotService;
    private final RoundService roundService;
    private final ModelMapper modelMapper;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, PilotService pilotService, RoundService roundService, ModelMapper modelMapper) {
        this.eventRepository = eventRepository;
        this.pilotService = pilotService;
        this.roundService = roundService;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public List<Event> findAll() {
        return this.eventRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Event> findById(Long eventId) {
        return this.eventRepository.findById(eventId);
    }

    @Override
    @Transactional
    public Event saveEvent(Event event)
    {
        return this.eventRepository.save(event);
    }

    @Override
    @Transactional
    public Event addPilotToEvent(Event event, Pilot pilot) {
        event.addPilot(pilot);
        return this.eventRepository.save(event);
    }

    @Override
    @Transactional
    public void removePilotFromEvent(Event event, Pilot pilot) {
        event.removePilot(pilot);
        this.eventRepository.save(event);
        this.pilotService.savePilot(pilot);
    }

    @Override
    @Transactional
    public Round addRoundToEvent(Event event, Round round) {
        round.setEvent(event);
        return this.roundService.saveRound(round);
    }

    @Override
    @Transactional
    public void removeRoundFromEvent(Event event, Round round) {
        event.removeRound(round);
        this.eventRepository.save(event);
        this.roundService.removeRound(round);
    }

    @Override
    @Transactional
    public List<Round> findEventRounds(Event event) {
        return event.getRoundList();
    }

    @Override
    @Transactional
    public void removeEvent(Event event) {
        event.getPilotList().forEach(p-> {
            p.removeEvent(event);
            this.pilotService.savePilot(p);
        });
        this.eventRepository.delete(event);
    }

    @PostConstruct
    private void config() {
        this.modelMapper.typeMap(EventDTO.class,Event.class)
                .addMapping(EventDTO::getPilotDirector,Event::setPilotDirector);

        this.modelMapper.typeMap(Event.class,EventDTO.class)
                .addMapping(Event::getPilotDirector,EventDTO::setPilotDirector);
    }

    public Event eventDTOtoEvent(EventDTO eventDTO) {
        return this.modelMapper.map(eventDTO,Event.class);
    }

    public EventDTO eventToEventDTO(Event event) {
        return this.modelMapper.map(event,EventDTO.class);
    }

    public void checkEventStatusTrue() {
        List<Event> eventList = this.eventRepository.findAllByStatusTrue();
        LocalDate currentDate = LocalDate.now();
        eventList.forEach(event -> {
            if(event.getEndDate().compareTo(currentDate) < 0) {
                event.setEventStatus(false);
                event.setRegistrationStatus(false);
                this.eventRepository.save(event);
            }
        });
    }
}
