package ru.mfti.atp.sem1;

public class Cat implements Pet {
    // Ctrl + D = dulicate line
    String firstName; // camelCase
    String lastName;
    Person owner;

    @Override
    public String getName() {
        return firstName + " " + lastName;
    }

    @Override
    public Person getOwner() {
        return owner;
    }
}