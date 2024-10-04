package ru.mfti.atp.sem5;

import ru.mfti.atp.sem1.Person;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        // comparator for Persons
        Comparator<Person> personComparator = new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge() - o2.getAge();
            }
        };

        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Oleg", 20));
        personList.add(new Person("Misha", 30));
        personList.add(new Person("Pasha", 40));

        List<SuperPerson> superPersonList = new ArrayList<>();
        superPersonList.add(new SuperPerson("Jack", 20));
        superPersonList.add(new SuperPerson("Joe", 30));
        superPersonList.add(new SuperPerson("Michael", 40));

        System.out.println(findMax(superPersonList, personComparator));
        System.out.println(findMax(superPersonList));
    }

    // PECS
    // Producer extends, consumer super
    // find max with comparator
    public static <T> T findMax(List<? extends T> list, Comparator<? super T> comparator) {
        T max = list.get(0);
        for (T el : list) {
            if (comparator.compare(el, max) > 0) {
                max = el;
            }
        }
        return max;
    }

    // find max for comparable
    public static <T extends Comparable<? super T>>T findMax(List<? extends T> list) {
        T max = list.get(0);
        for (T el : list) {
            if (el.compareTo(max) > 0) {
                max = el;
            }
        }
        return max;
    }
}
