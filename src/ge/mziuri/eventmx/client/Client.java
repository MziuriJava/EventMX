package ge.mziuri.eventmx.client;

import ge.mziuri.eventmx.model.command.Command;
import ge.mziuri.eventmx.model.command.CommandResult;
import ge.mziuri.eventmx.model.event.Event;
import ge.mziuri.eventmx.model.event.SortDirection;
import ge.mziuri.eventmx.model.event.SortType;
import ge.mziuri.eventmx.model.person.Person;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Client {
    public void start() {


        try {
            Socket socket = new Socket("localhost", 8080);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            Scanner scanner = new Scanner(System.in);
            System.out.println(Messages.getMessage("chooseCommand"));
            Command commands[] = Command.values();
            for (int i = 0; i < commands.length; i++) {
                System.out.println(i + 1 + ". " + Messages.getMessage(commands[i].name()));
            }
            int num = scanner.nextInt();
            Command command = commands[num - 1];
            out.writeObject(command);
            switch (command) {
                case ADD_EVENT:
                    scanner.nextLine();

                    System.out.println("Name");
                    String name = scanner.nextLine();

                    System.out.println("Public");
                    Boolean pub = scanner.nextBoolean();

                    System.out.println("Capacity");
                    int acapacity = scanner.nextInt();

                    System.out.println("Location");
                    scanner.nextLine();
                    String aLocation = scanner.nextLine();

                    System.out.println("Date (MM-dd-yyyy)");
                    String datest = scanner.nextLine();
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
                        Date date = sdf.parse(datest);

                        Event event = new Event();
                        event.setName(name);
                        event.setPub(pub);
                        event.setCapacity(acapacity);
                        event.setLocation(aLocation);
                        event.setDate(date);

                        out.writeObject(event);
                        CommandResult addEventResult = (CommandResult) in.readObject();
                        System.out.println(Messages.getMessage(addEventResult.name()));
                        break;
                    } catch (ParseException ex) {
                        System.out.println("Date format is not valid");
                        break;
                    }

                case REMOVE_EVENT:
                    scanner.nextLine();

                    System.out.println("ID");
                    int reid = scanner.nextInt();
                    out.writeObject(reid);
                    CommandResult removeEventResult = (CommandResult) in.readObject();
                    System.out.println(Messages.getMessage(removeEventResult.name()));
                    break;

                case MAKE_EVENT_PUBLIC:
                    scanner.nextLine();

                    System.out.println("ID");
                    int mepid = scanner.nextInt();


                    out.writeObject(mepid);
                    CommandResult makeEventPublicResult = (CommandResult) in.readObject();
                    System.out.println(Messages.getMessage(makeEventPublicResult.name()));
                    break;

                case CHANGE_EVENT_CAPACITY:
                    scanner.nextLine();

                    System.out.println("ID");
                    int cecid = scanner.nextInt();

                    System.out.println("Capacity");
                    int ccapacity = scanner.nextInt();

                    out.writeObject(cecid);
                    out.writeObject(ccapacity);
                    CommandResult changeEventCapacityResult = (CommandResult) in.readObject();
                    System.out.println(Messages.getMessage(changeEventCapacityResult.name()));
                    break;

                case CHANGE_EVENT_LOCATION:
                    scanner.nextLine();

                    System.out.println("ID");
                    int celid=scanner.nextInt();

                    System.out.println("Location");
                    String clocation=scanner.nextLine();

                    out.writeObject(celid);
                    out.writeChars(clocation);

                    CommandResult changeEventLocationResult = (CommandResult) in.readObject();
                    System.out.println(Messages.getMessage(changeEventLocationResult.name()));
                    break;

                case SORT_EVENTS:
                    scanner.nextLine();

                    System.out.println("Sort By (NAME, CAPACITY, DATE)");
                    String sortType = scanner.nextLine();

                    System.out.println("Sort Direction (ASC, DESC)");
                    String sortDirection=scanner.nextLine();

                    try {
                        SortType type = SortType.valueOf(sortType);
                        SortDirection direction = SortDirection.valueOf(sortDirection);

                        out.writeObject(type);
                        out.writeObject(direction);
                        CommandResult sortEventsResult = (CommandResult) in.readObject();
                        System.out.println(Messages.getMessage(sortEventsResult.name()));
                        break;
                    } catch (IllegalArgumentException ex) {
                        System.out.println("Sort By or Sort Direction is not valid");
                    }

                case ADD_PERSON:
                    scanner.nextLine();

                    System.out.println("Name");
                    String pname=scanner.nextLine();

                    System.out.println("Last Name");
                    String lastname=scanner.nextLine();

                    System.out.println("Person's ID");
                    int person_id=scanner.nextInt();

                    Person person=new Person();
                    person.setName(pname);
                    person.setLastname(lastname);
                    person.setPerson_ID(person_id);

                    out.writeObject(person);
                    CommandResult addPersonResult = (CommandResult) in.readObject();
                    System.out.println(Messages.getMessage(addPersonResult.name()));
                    break;

                case REMOVE_PERSON:
                    scanner.nextLine();

                    System.out.println("ID");
                    int pid=scanner.nextInt();

                    out.writeObject(pid);
                    CommandResult removePersonResult = (CommandResult) in.readObject();
                    System.out.println(Messages.getMessage(removePersonResult.name()));
                    break;
            }
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }


    }
}
