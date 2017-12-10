package ge.mziuri.eventmx.server.dao;

import ge.mziuri.eventmx.model.event.Event;

public interface EventDAO {

    void addEvent (Event event);

    void removeEvent (Event event);

    void makeEventPublic (Event event);

    void changeEventCapacity (Event event);

    void changeEventLocation (Event event);

    void sortEventsByAlphabet();

    void sortEventByCapacity();

    void sortEventsByDate();
}
