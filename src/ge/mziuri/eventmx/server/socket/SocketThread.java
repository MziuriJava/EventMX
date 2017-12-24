package ge.mziuri.eventmx.server.socket;

import ge.mziuri.eventmx.model.command.Command;
import ge.mziuri.eventmx.model.command.CommandResult;
import ge.mziuri.eventmx.model.event.Event;
import ge.mziuri.eventmx.model.event.SortDirection;
import ge.mziuri.eventmx.model.event.SortType;
import ge.mziuri.eventmx.model.exception.EventMXException;
import ge.mziuri.eventmx.model.person.Person;
import ge.mziuri.eventmx.server.dao.EventDAO;
import ge.mziuri.eventmx.server.dao.EventDAOI;
import ge.mziuri.eventmx.server.dao.PersonDAO;
import ge.mziuri.eventmx.server.dao.PersonDAOI;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketThread implements Runnable {
    private final PersonDAO personDAO = new PersonDAOI();
    private final EventDAO eventDAO = new EventDAOI();
    private Socket socket;

    public SocketThread(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {
        try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {

        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        Command command = (Command) in.readObject();
        switch (command) {
            case ADD_EVENT:
                Event event = (Event)in.readObject();
                try {
                    eventDAO.addEvent(event);
                    out.writeObject(CommandResult.SUCCESSFUL);
                } catch (EventMXException ex){
                    out.writeObject(CommandResult.FAILURE);
                    ex.printStackTrace();
                }
                break;
            case REMOVE_EVENT:
                int reid = (Integer)in.readObject();
                Event eventForRemove = new Event();
                eventForRemove.setId(reid);
                try {
                    eventDAO.removeEvent(eventForRemove);
                    out.writeObject(CommandResult.SUCCESSFUL);
                }catch (EventMXException ex){
                    out.writeObject(CommandResult.FAILURE);
                    ex.printStackTrace();
                }
            case MAKE_EVENT_PUBLIC:
                Integer mepid = (Integer)in.readObject();
                Event eventToMakePublic = new Event();
                eventToMakePublic.setId(mepid);
                try{
                    eventDAO.makeEventPublic(eventToMakePublic);
                    out.writeObject(CommandResult.SUCCESSFUL);
                }catch (EventMXException ex){
                    out.writeObject(CommandResult.FAILURE);
                    ex.printStackTrace();
                }
            case CHANGE_EVENT_CAPACITY:
                int cecid = (Integer)in.readObject();
                int ccapacity = (Integer)in.readObject();
                Event eventToChangeCapacity = new Event();
                eventToChangeCapacity.setId(cecid);
                eventToChangeCapacity.setCapacity(ccapacity);
                try {
                    eventDAO.changeEventCapacity(eventToChangeCapacity);
                    out.writeObject(CommandResult.SUCCESSFUL);
                }catch (EventMXException ex) {
                    out.writeObject(CommandResult.FAILURE);
                    ex.printStackTrace();
                }
            case CHANGE_EVENT_LOCATION:
                int celid = (Integer)in.readObject();
                String clocation = (String)in.readObject();
                Event eventToChangeLocation = new Event();
                eventToChangeLocation.setId(celid);
                eventToChangeLocation.setLocation(clocation);
                try{
                    eventDAO.changeEventLocation(eventToChangeLocation);
                    out.writeObject(CommandResult.SUCCESSFUL);
                }catch (EventMXException ex){
                    out.writeObject(CommandResult.FAILURE);
                    ex.printStackTrace();
                }
            case SORT_EVENTS:
                SortType sortType = (SortType)in.readObject();
                SortDirection sortDirection = (SortDirection)in.readObject();
                try{
                    eventDAO.sortEvent(sortType, sortDirection);
                    out.writeObject(CommandResult.SUCCESSFUL);
                }catch (EventMXException ex){
                    out.writeObject(CommandResult.FAILURE);
                    ex.printStackTrace();
                }






            case ADD_PERSON:
                Person person = (Person) in.readObject();
                try {
                    personDAO.addPerson (person);
                    out.writeObject(CommandResult.SUCCESSFUL);
                }catch (EventMXException ex){
                    out.writeObject(CommandResult.FAILURE);
                    ex.printStackTrace();
                }

            case REMOVE_PERSON:
                 int personIdForRemove = (Integer)in.readObject();
                Person personForRemove = new Person();
                personForRemove.setID(personIdForRemove);
                try {
                   personDAO.removePerson(personForRemove);
                    out.writeObject(CommandResult.SUCCESSFUL);
                }catch (EventMXException ex){
                    out.writeObject(CommandResult.FAILURE);
                    ex.printStackTrace();
                }

















        }
















        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}