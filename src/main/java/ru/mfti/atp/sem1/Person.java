package ru.mfti.atp.sem1;

import lombok.*;
import ru.mfti.atp.sem6.NotNull;
import ru.mfti.atp.sem7.Bounds;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@ToString
@RequiredArgsConstructor
public class Person implements Comparable<Person>, Iterable<Pet> {
    @NotNull
    private String name;

    @Bounds(min=0, max=120)
    private int age;

    private final List<Pet> pets = List.of();

    public final String somePublicField = "whatever";

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
    public int compareTo(Person o) {
        return Comparator.comparingInt(Person::getAge).compare(this, o);
    }

    @Override
    public Iterator<Pet> iterator() {
        return pets.iterator();
    }
}
