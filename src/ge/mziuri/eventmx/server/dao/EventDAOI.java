package ge.mziuri.eventmx.server.dao;

import ge.mziuri.eventmx.model.event.Event;
import ge.mziuri.eventmx.model.event.SortDirection;
import ge.mziuri.eventmx.model.event.SortType;
import ge.mziuri.eventmx.model.exception.EventMXException;
import ge.mziuri.eventmx.server.util.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDAOI implements EventDAO {

    public void addEvent(Event event) throws EventMXException {
        try {
            Connection con = null;
            PreparedStatement pstmt = null;
            con = DatabaseConnector.getConnection();
            pstmt = con.prepareStatement("INSERT INTO Event (Name, Public, Capacity, Location, date) VALUES (?,?,?,?,?)");

            pstmt.setString(1, event.getName());
            pstmt.setBoolean(2, event.getPub());
            pstmt.setInt(3, event.getCapacity());
            pstmt.setString(4, event.getLocation());
            pstmt.setTimestamp(5, new Timestamp(event.getDate().getTime()));
            pstmt.executeUpdate();
            pstmt.close();
            con.close();

        } catch (Exception ex) {
            throw new EventMXException("Can't add Event", ex);

        }

    }

    @Override
    public void removeEvent(Event event) throws EventMXException {
        try {
            Connection con = null;
            con = DatabaseConnector.getConnection();
            PreparedStatement pstmt = con.prepareStatement("DELETE FROM event WHERE ID=?");
            pstmt.setInt(1, event.getId());
            pstmt.executeUpdate();
            pstmt.close();
            con.close();

        } catch (Exception ex) {
            throw new EventMXException("Can't remove event", ex);
        }
    }

    @Override
    public void makeEventPublic(Event event) throws EventMXException {
        try {
            Connection con = null;
            con = DatabaseConnector.getConnection();
            PreparedStatement pstmt = con.prepareStatement("UPDATE event SET public = true WHERE id=?");
            pstmt.setInt(1, event.getId());
            pstmt.executeUpdate();
            pstmt.close();
            con.close();

        } catch (Exception ex) {
            throw new EventMXException("Can't change to public ", ex);
        }

    }


    @Override
    public void changeEventCapacity(Event event) throws EventMXException {
        try {
            Connection con = null;
            con = DatabaseConnector.getConnection();
            PreparedStatement pstmt = con.prepareStatement("UPDATE event SET Capacity = ? WHERE id=?");
            pstmt.setInt(1, event.getCapacity());
            pstmt.setInt(2, event.getId());
            pstmt.executeUpdate();
            pstmt.close();
            con.close();

        } catch (Exception ex) {
            throw new EventMXException("Can't change Capacity ", ex);
        }

    }

    @Override
    public void changeEventLocation(Event event) throws EventMXException {
        try {
            Connection con = null;
            con = DatabaseConnector.getConnection();
            PreparedStatement pstmt = con.prepareStatement("UPDATE event SET location=? WHERE id=?");
            pstmt.setString(1, event.getLocation());
            pstmt.setInt(2, event.getId());
            pstmt.executeUpdate();
            pstmt.close();
            con.close();

        } catch (Exception ex) {
            throw new EventMXException("Can't change location ", ex);
        }

    }

    @Override
    public List<Event> sortEvent(SortType sortType, SortDirection sortDirection) throws EventMXException {
        try {
            Connection con = null;
            con = DatabaseConnector.getConnection();
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM Event ORDER BY ? " + sortDirection.name());
            pstmt.setString(1, sortType.name());
            ResultSet rs = pstmt.executeQuery();
            List<Event> events = new ArrayList<>();
            while (rs.next()) {
                int id=rs.getInt("id");
                String name=rs.getString("name");
                Boolean pub=rs.getBoolean("public");
                int capacity=rs.getInt("capacity");
                String location=rs.getString("location");
                Date date=rs.getDate("date");
                Event event=new Event();
                event.setId(id);
                event.setName(name);
                event.setPub(pub);
                event.setCapacity(capacity);
                event.setLocation(location);
                event.setDate(date);
            }
            pstmt.close();
            con.close();
            return events;
        } catch (Exception ex) {
            throw new EventMXException("Can't sort events ", ex);
        }


    }
}
