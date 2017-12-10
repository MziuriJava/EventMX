package ge.mziuri.eventmx.server.dao;

import ge.mziuri.eventmx.model.event.Event;
import ge.mziuri.eventmx.model.exception.EventMXException;
import ge.mziuri.eventmx.model.person.Person;

public interface PersonDAO {

    void addPerson(Person person) throws EventMXException;


    void removePerson(Person person)throws EventMXException;


    }

