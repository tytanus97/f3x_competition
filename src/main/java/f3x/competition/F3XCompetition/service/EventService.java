package f3x.competition.F3XCompetition.service;

import f3x.competition.F3XCompetition.entity.Event;
import f3x.competition.F3XCompetition.entity.Pilot;
import f3x.competition.F3XCompetition.entity.Round;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> findAll();
    Optional<Event> findById(Long eventId);
    Event saveEvent(Event event);
    void removeEvent(Event event);
    Event addPilotToEvent(Event event, Pilot pilot);
    void removePilotFromEvent(Event event,Pilot pilot);
    Round addRoundToEvent(Event event, Round round);
    void removeRoundFromEvent(Event event,Round round);
    List<Round> findEventRounds(Event event);
    Event finalizeEvent(Event event);

    void checkEventStatusTrue();
}
