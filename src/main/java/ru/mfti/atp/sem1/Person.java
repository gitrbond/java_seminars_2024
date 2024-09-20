package ru.mfti.atp.sem1;

import lombok.*;

import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
public class Person {
    private String name;

    private int age;

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
}
