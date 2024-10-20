package ru.mfti.atp.sem7;

import ru.mfti.atp.sem1.Person;

import java.lang.reflect.Field;

import static ru.mfti.atp.sem7.CacheHandler.cache;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        Person p = new Person("Oleg", 130);

        try {
            validateBounds(p);
        } catch (IllegalStateException ise) {
            System.out.println("validation falied for person " + p + ", reason: " + ise);
        }

        // how to cache the methods?
        // option 1: decorator pattern
//        Calculator calculator = new CalculatorDecorator(new CalculatorImpl());
        // option 2: dynamic proxy
        Calculator calculator = cache(new CalculatorImpl());

        System.out.println(calculator.add(1, 2));
        System.out.println(calculator.add(1, 2));
        System.out.println(calculator.add(1, 2));
        System.out.println(calculator.add(1, 2));
        System.out.println(calculator.add(1, 2));
        System.out.println(calculator.add(2, 3));
        System.out.println(calculator.add(2, 3));
        System.out.println(calculator.add(2, 3));
    }

    private static void validateBounds(Object o) throws IllegalAccessException {
        Class<?> clazz = o.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            boolean accessible = field.canAccess(o);
            if (!accessible) {
                field.setAccessible(true);
            }
            if (field.getAnnotation(Bounds.class) != null) {
                Bounds annotation = field.getAnnotation(Bounds.class);
                int min = annotation.min();
                int max = annotation.max();
                int value = (Integer) field.get(o);

                if (value < min || value > max) {
                    throw new IllegalStateException("validation failed for field " + field.getName());
                }
            }
        }
    }
}
