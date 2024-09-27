package ru.mfti.atp.sem3;

import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) throws Exception {
        Garage garage = new Garage();

//        System.out.println(garage);

        Predicate<String> predicate = o -> o.equals("green");

        System.out.println(predicate.test("green"));
        System.out.println(predicate.test("red"));

        try {
            garage.add(new Car("green"));
            garage.add(new Car("red"));
            garage.add(new Car("blue"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(garage);
        garage.undo();
        System.out.println(garage);
        garage.add(new Car("green"));
        System.out.println(garage);
        garage.undo();
        System.out.println(garage);
    }
}
