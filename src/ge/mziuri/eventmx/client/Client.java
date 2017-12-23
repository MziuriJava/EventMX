package ge.mziuri.eventmx.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public void start() {


        try{ Socket socket = new Socket("localhost",8080);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            Scanner scanner = new Scanner(System.in);
            System.out.println(Messages.getMessage("chooseCommand"));



        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }
}
