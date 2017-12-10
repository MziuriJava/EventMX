package ge.mziuri.eventmx.server.dao;

import ge.mziuri.eventmx.model.event.Event;
import ge.mziuri.eventmx.model.event.SortDirection;
import ge.mziuri.eventmx.model.event.SortType;

public interface EventDAO {

    void addEvent (Event event);

    void removeEvent (Event event);

    void makeEventPublic (Event event);

    void changeEventCapacity (Event event);

    void changeEventLocation (Event event);

    void sortEvent(SortType sortType, SortDirection sortDirection);
}
