package ge.mziuri.eventmx.server.dao;

import ge.mziuri.eventmx.model.event.Event;
import ge.mziuri.eventmx.model.exception.EventMXException;
import ge.mziuri.eventmx.model.person.Person;
import ge.mziuri.eventmx.server.util.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class PersonDAOI implements PersonDAO {

    @Override
    public void addPerson(Person person) throws EventMXException{

        try {
            Connection con=null;
            con= DatabaseConnector.getConnection();
            PreparedStatement pstmt=con.prepareStatement("INSERT INTO Person(NAME, LASTNAME, PERSON_ID VALUES(?,?,?)");
            pstmt.setString(1, person.getName());
            pstmt.setString(2, person.getLastname());
            pstmt.setInt(3, person.getPerson_ID());
            pstmt.executeUpdate();
            pstmt.close();
            con.close();
        } catch (Exception ex) {
            throw new EventMXException("Can't add person ", ex);
        }


    }

    @Override
    public void removePerson(Person person) throws EventMXException {
        try {
            Connection con=null;
            con= DatabaseConnector.getConnection();
            PreparedStatement pstmt=con.prepareStatement("DELETE FROM Person WHERE ID=?");
            pstmt.setInt(1, person.getID());
            pstmt.executeUpdate();
            pstmt.close();
            con.close();

        } catch (Exception ex) {
            throw new EventMXException("Can't remove person", ex);
        }

    }
}
