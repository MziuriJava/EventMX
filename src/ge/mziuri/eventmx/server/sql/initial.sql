CREATE TABLE Event(
ID SERIAL PRIMARY KEY NOT NULL,
Name VARCHAR(30),
Public BOOLEAN,
Capacity INT,
Location VARCHAR(40),
Date DATE



);

CREATE TABLE Person(
ID SERIAL PRIMARY KEY NOT NULL,
Name VARCHAR(20),
LastName VARCHAR(30),
Person_ID INT




);


CREATE TABLE EventPerson(
ID SERIAL PRIMARY KEY NOT NULL,
fk_event INT REFERENCES Event(ID),
fk_person INT REFERENCES Person(ID)





);