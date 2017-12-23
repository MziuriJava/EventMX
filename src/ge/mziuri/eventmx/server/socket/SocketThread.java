package ge.mziuri.eventmx.server.socket;

import ge.mziuri.eventmx.server.dao.PersonDAO;
import ge.mziuri.eventmx.server.dao.PersonDAOI;

import java.net.Socket;

public class SocketThread implements Runnable{
    private final PersonDAO personDAO=new PersonDAOI();
    private Socket socket;
    public SocketThread(Socket socket){
        this.socket=socket;
    }


    @Override
    public void run() {

    }
}
