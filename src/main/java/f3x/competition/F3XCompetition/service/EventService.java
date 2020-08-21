package f3x.competition.F3XCompetition.service;

import f3x.competition.F3XCompetition.entity.Event;
import f3x.competition.F3XCompetition.entity.Pilot;
import f3x.competition.F3XCompetition.entity.Round;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> getAll();
    Optional<Event> getById(Long eventId);
    Event saveEvent(Event event);
    void removeEvent(Event event);
    void addPilotToEvent(Event event, Pilot pilot);
    void removePilotFromEvent(Event event,Pilot pilot);
    void addRoundToEvent(Event event, Round round);
    void removeRoundFromEvent(Event event,Round round);
    List<Round> getEventRounds(Event event);



}
