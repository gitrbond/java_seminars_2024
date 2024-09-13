package ru.mfti.atp.sem1;

public class Dog implements Pet {
    String name;
    Person owner;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Person getOwner() {
        return owner;
    }
}
