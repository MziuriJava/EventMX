package ge.mziuri.eventmx.server.dao;

import ge.mziuri.eventmx.model.event.Event;
import ge.mziuri.eventmx.model.event.SortDirection;
import ge.mziuri.eventmx.model.event.SortType;
import ge.mziuri.eventmx.model.exception.EventMXException;
import ge.mziuri.eventmx.server.util.DatabaseConnector;

import java.sql.*;

public class EventDAOI implements EventDAO {

    public void addEvent(Event event) throws EventMXException {
        try {
            Connection con = null;
            PreparedStatement pstmt = null;
            con = DatabaseConnector.getConnection();
            pstmt = con.prepareStatement("INSERT INTO Event (Name, Public, Capacity, Location, date) VALUES (?,?,?,?,?)");

            pstmt.setString(1, event.getName());
            pstmt.setBoolean(2, event.getPublic());
            pstmt.setInt(3, event.getCapacity());
            pstmt.setString(4, event.getLocation());
            pstmt.setTimestamp(5, new Timestamp(event.getDate().getTime()));
        } catch (Exception ex) {
            throw new EventMXException("Can't add Event", ex);
        }
    }

    @Override
    public void removeEvent(Event event) {

    }

    @Override
    public void makeEventPublic(Event event) {

    }

    @Override
    public void changeEventCapacity(Event event) {

    }

    @Override
    public void changeEventLocation(Event event) {

    }

    @Override
    public void sortEvent(SortType sortType, SortDirection sortDirection) {

    }
}
