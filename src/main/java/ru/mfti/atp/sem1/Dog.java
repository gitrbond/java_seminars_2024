package ru.mfti.atp.sem1;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
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
