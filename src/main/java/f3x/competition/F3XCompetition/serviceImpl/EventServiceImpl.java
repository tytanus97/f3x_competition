package f3x.competition.F3XCompetition.serviceImpl;

import f3x.competition.F3XCompetition.entity.Event;
import f3x.competition.F3XCompetition.entity.Pilot;
import f3x.competition.F3XCompetition.entity.Round;
import f3x.competition.F3XCompetition.repository.EventRepository;
import f3x.competition.F3XCompetition.repository.PilotRepository;
import f3x.competition.F3XCompetition.repository.RoundRepository;
import f3x.competition.F3XCompetition.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final PilotRepository pilotRepository;
    private final RoundRepository roundRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, PilotRepository pilotRepository, RoundRepository roundRepository) {
        this.eventRepository = eventRepository;
        this.pilotRepository = pilotRepository;
        this.roundRepository = roundRepository;
    }

    @Override
    @Transactional
    public List<Event> getAll() {
        return this.eventRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Event> getById(Long eventId) {
        return this.eventRepository.findById(eventId);
    }

    @Override
    @Transactional
    public void addEvent(Event event) {
        this.eventRepository.save(event);
    }

    @Override
    @Transactional

    public void addPilotToEvent(Event event, Pilot pilot) {
        event.addPilot(pilot);
        this.eventRepository.save(event);
        this.pilotRepository.save(pilot);
    }

    @Override
    @Transactional
    public void removePilotFromEvent(Event event, Pilot pilot) {
        event.removePilot(pilot);
        this.eventRepository.save(event);
        this.pilotRepository.save(pilot);
    }

    @Override
    @Transactional
    public void addRoundToEvent(Event event, Round round) {
        event.addRound(round);
        this.eventRepository.save(event);
        this.roundRepository.save(round);
    }

    @Override
    @Transactional
    public void removeRoundFromEvent(Event event, Round round) {
        event.removeRound(round);
        this.eventRepository.save(event);
        this.roundRepository.delete(round);
    }

    @Override
    @Transactional
    public List<Round> getEventRounds(Event event) {
        return event.getRoundList();
    }

}
