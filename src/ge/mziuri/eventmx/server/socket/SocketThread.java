package ge.mziuri.eventmx.server.socket;

import ge.mziuri.eventmx.model.command.Command;
import ge.mziuri.eventmx.model.command.CommandResult;
import ge.mziuri.eventmx.model.event.Event;
import ge.mziuri.eventmx.model.exception.EventMXException;
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
                }catch (EventMXException ex){
                    out.writeObject(CommandResult.FAILURE);
                    ex.getStackTrace();
                }
                break;
            case REMOVE_EVENT:
                int reid = in.readInt();
                Event eventForRemove = new Event();
                eventForRemove.setId(reid);
                try {
                    eventDAO.removeEvent(eventForRemove);
                    out.writeObject(CommandResult.SUCCESSFUL);
                }catch (EventMXException ex){
                    out.writeObject(CommandResult.FAILURE);
                    ex.getStackTrace();
                }





















        }
















        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}