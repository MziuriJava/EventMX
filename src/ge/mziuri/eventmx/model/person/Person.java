package ge.mziuri.eventmx.model.person;

public class Person {
    private int ID;

    private String Name;

    private String Lastname;

    private int Person_ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public int getPerson_ID() {
        return Person_ID;
    }

    public void setPerson_ID(int person_ID) {
        Person_ID = person_ID;
    }
}
