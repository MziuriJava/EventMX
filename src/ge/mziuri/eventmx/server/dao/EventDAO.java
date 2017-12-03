package ge.mziuri.eventmx.server.dao;

import ge.mziuri.eventmx.model.event.Event;

public interface EventDAO {

    void addEvent (Event event);

    void RemoveEvent (Event event);

    void MakeEventPublic (Event event);

    void ChangeEventCapacity (Event event);

    void ChangeEventLocation (Event event);

    void SortEventsByAlphabet();

    void SortEventByCapacity();

    void SortEventsByDate();
}
