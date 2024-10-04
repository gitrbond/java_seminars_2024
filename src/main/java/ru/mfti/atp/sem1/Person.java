package ru.mfti.atp.sem1;

import lombok.*;

import java.util.*;
import java.util.function.Consumer;

@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class Person implements Comparable<Person>, Iterable<Pet> {
    private String name;

    private int age;

    private final List<Pet> pets = List.of();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && name.equalsIgnoreCase(person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase(), age);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Person o) {
        return Comparator.comparingInt(Person::getAge).compare(this, o);
    }

    @Override
    public Iterator<Pet> iterator() {
        return pets.iterator();
    }
}
