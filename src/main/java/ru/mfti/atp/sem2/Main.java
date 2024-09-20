package ru.mfti.atp.sem2;

import ru.mfti.atp.sem1.Person;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Person alex = new Person("Alex", 26);
        Person alex1 = new Person("alex", 20);
//        Person bob = new Person("Bob", 20);

        // rename - Shift+F6
        System.out.println(alex.equals(alex1));
        System.out.println(alex.hashCode());
        System.out.println(alex1.hashCode());

        List<Person> list = new ArrayList<>();
        Map<Person, String> map = new LinkedHashMap<>();

//        map.put(alex1, "alex1");
//        map.put(alex, "alex");
//        System.out.println(map);
//
//        Set<Person> set = new HashSet<>();
//        set.add(alex);
//        set.add(alex1);
//        System.out.println(set);

        Comparator<Person> comparator = Comparator.comparingInt(Person::getAge);

        SortedSet<Person> treeSet= new TreeSet<>(comparator);
        treeSet.add(alex);
        treeSet.add(alex1);
        System.out.println(treeSet.first());
    }
}
