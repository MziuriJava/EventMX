package ge.mziuri.eventmx.server.dao;

import ge.mziuri.eventmx.model.event.Event;
import ge.mziuri.eventmx.model.event.SortDirection;
import ge.mziuri.eventmx.model.event.SortType;
import ge.mziuri.eventmx.model.exception.EventMXException;

import java.util.List;

public interface EventDAO {

    void addEvent(Event event) throws EventMXException;

    void removeEvent(Event event)throws EventMXException;

    void makeEventPublic(Event event )throws EventMXException;

    void changeEventCapacity(Event event)throws EventMXException;

    void changeEventLocation(Event event)throws EventMXException;

    List<Event> sortEvent(SortType sortType, SortDirection sortDirection)throws  EventMXException;
}
