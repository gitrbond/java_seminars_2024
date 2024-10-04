//package ru.mfti.atp.sem4;
//
//import ru.mfti.atp.sem1.Person;
//import ru.mfti.atp.sem1.Pet;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.List;
//
//public class Main {
//    public static void main(String[] args) {
//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        list.add(4);
//
//        List<Person> personList = new ArrayList<>();
//        personList.add(new Person("Oleg", 20));
//        personList.add(new Person("Misha", 30));
//        personList.add(new Person("Pasha", 40));
//
//        System.out.println(findMax(personList, Comparator.comparingInt(Person::getAge).thenComparingInt(person -> person.getName().length())));
//        System.out.println(findMax(personList));
////        List<Long> longList = List.of(1L, 2L, 6L, 100L);
////
////        printAll(list);
////        printAll(longList);
////
////        Integer el = list.get(1);
////        System.out.println(el);
//
//
//        List<? extends Number> list0 = new ArrayList<Long>();
//        List<? extends Number> list01 = new ArrayList<Double>();
//        List<? extends Number> list02 = new ArrayList<Short>();
//
//        list0.add(null);
//
//        List<Object> objectList = new ArrayList<>();
//        objectList.add(1);
//        objectList.add(1L);
//        objectList.add("1");
//
//        List<? super Number> list1 = new ArrayList<Number>();
//        List<? super Number> list2 = new ArrayList<Object>();
//        list1.add(0.3);
//        list1.add(1);
//        list1.add(1L);
//
//        System.out.println(list1);
//    }
//
//    // T extends Number
//    // Long
//    // Int
//    private static <T> void printAll(List<T> list) {
//        for (T el : list) {
//            System.out.println(el);
//        }
//    }
//
//    public static <T> T findMax(List<T> list, Comparator<T> comparator) {
//        T max = list.get(0);
//        for (T el : list) {
//            if (comparator.compare(el, max) > 0) {
//                max = el;
//            }
//        }
//        return max;
//    }
//
//    public static <T extends Comparable<T> & Iterable<Pet>> T findMax(List<T> list) {
//        T max = list.get(0);
//        for (T el : list) {
//            if (el.compareTo(max) > 0) {
//                max = el;
//            }
//        }
//        return max;
//    }
//}
