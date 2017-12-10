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
            pstmt.setInt(1, event.getID());
            pstmt.close();
            con.close();

        } catch (Exception ex) {
            throw new EventMXException("Can't remove event", ex);
        }
    }

    @Override
    public void makeEventPublic(Event event) throws EventMXException  {
        try {
            Connection con = null;
            con = DatabaseConnector.getConnection();
            PreparedStatement pstmt = con.prepareStatement("UPDATE event SET public = ? WHERE id=?");
            pstmt.setBoolean(1, true);
            pstmt.setInt(2, event.getID());
            pstmt.close();
            con.close();

        } catch (Exception ex) {
            throw new EventMXException("Can't change to public ", ex);
        }

    }


    @Override
    public void setEventCapacity(Event event) throws EventMXException{
     try {
         Connection con=null;
         con= DatabaseConnector.getConnection();
         PreparedStatement pstmt=con.prepareStatement("SET event capacity=? WHERE ID = ?");
         pstmt.setInt(1,event.getCapacity());
         pstmt.setInt(2,event.getID());

         pstmt.close();
         con.close();

     }catch(Exception ex) {
         throw new EventMXException("Can't set capacity", ex);
        }


    }

        @Override
    public void changeEventCapacity(Event event) throws EventMXException {
        try {
            Connection con = null;
            con = DatabaseConnector.getConnection();
            PreparedStatement pstmt = con.prepareStatement("UPDATE event SET Capacity = ? WHERE id=?");
            pstmt.setInt(1, event.getCapacity());
            pstmt.setInt(2, event.getID());
            pstmt.close();
            con.close();

        } catch (Exception ex) {
            throw new EventMXException("Can't change Capacity ", ex);
        }

    }

    @Override
    public void changeEventLocation(Event event)throws EventMXException {
        try {
            Connection con = null;
            con = DatabaseConnector.getConnection();
            PreparedStatement pstmt = con.prepareStatement("UPDATE event SET location=? WHERE id=?");
            pstmt.setString(1, event.getLocation());
            pstmt.setInt(2, event.getID());
            pstmt.close();
            con.close();

        } catch (Exception ex) {
            throw new EventMXException("Can't change location ", ex);
        }

    }

    @Override
    public void sortEvent(SortType sortType, SortDirection sortDirection) {

    }
}
