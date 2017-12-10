package ge.mziuri.eventmx;

import ge.mziuri.eventmx.model.event.Event;
import ge.mziuri.eventmx.model.exception.EventMXException;
import ge.mziuri.eventmx.server.dao.EventDAOI;

public class TestRunner {
    public static void main(String[] args) {
        EventDAOI ed=new EventDAOI();
        Event event=new Event();
        event.setName("Wyali");
        event.setDate(null);
        event.setLocation("Tbilisi");
        event.setPub(false);
        event.setCapacity(1);
        try {
            ed.addEvent(event);
        } catch (EventMXException ex) {
            ex.printStackTrace();
        }

    }
}
