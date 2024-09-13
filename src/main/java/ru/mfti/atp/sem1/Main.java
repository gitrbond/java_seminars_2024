// Alt + Shift = таскать строки
package ru.mfti.atp.sem1;

// Ctrl + Alt + O = optimize imports
// Ctrl + Alt + L = format code

import java.util.*;

public class Main {
    //psvm
    public static void main(String[] args) {
        Person p1 = new Person("Oleg", 20);
        Person p2 = new Person("Masha", 20);
        Person p3 = new Person("Dima", 20);

        List<Person> personList = new ArrayList<>();
        personList.add(p1);
        personList.add(p2);
        personList.add(p3);

        p3.setAge(21);

        //sout
        System.out.println(personList);
    }

    Object func (Pet p, Person person) {
        return p.getName();
    }
}
