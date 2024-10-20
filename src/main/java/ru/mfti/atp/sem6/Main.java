package ru.mfti.atp.sem6;

import ru.mfti.atp.sem1.Dog;
import ru.mfti.atp.sem1.Person;
import ru.mfti.atp.sem1.Pet;
import ru.mfti.atp.sem5.SuperPerson;

import java.lang.reflect.Field;
import java.lang.reflect.InaccessibleObjectException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Class<?> personClass = Person.class;

        System.out.println("Person's declared fields (all fields in this class):" +
                Arrays.toString(personClass.getDeclaredFields()));
        System.out.println("SuperPerson's declared fields:" +
                Arrays.toString(SuperPerson.class.getDeclaredFields()));
        System.out.println("SuperPerson's fields (only public and in all hierarchy):" +
                Arrays.toString(SuperPerson.class.getFields()));

//        for (Method method : Person.class.getMethods()) {
//            System.out.println(method);
//        }

//        printParents(new HashMap<>());
//        printParents(new HashSet<>());

//        printFields(new Person("name", 12));
//        printFields(new HashMap<>());

        Person person = new Person(null, 12);
        Field age = Person.class.getDeclaredField("pets");
        age.setAccessible(true);
        age.set(person, List.of(new Dog("foo", null)));

        // we changed private field!
        System.out.println(person);

//        validateNotNull(person);
    }

    public static void printParents(Object o) {
        Class<?> clazz = o.getClass();
        while (clazz != null) {
            System.out.println(clazz);
            clazz = clazz.getSuperclass();
        }
    }

    public static void printFields(Object o) throws IllegalAccessException {
        Class<?> clazz = o.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            boolean accessible = field.canAccess(o);
            if (!accessible) {
                field.setAccessible(true);
                System.out.println(field.getName() + "=" + field.get(o));
            }
        }
    }

    public static void validateNotNull(Object o) throws IllegalAccessException {
        Class<?> clazz = o.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            boolean accessible = field.canAccess(o);
            if (!accessible) {
                field.setAccessible(true);
            }
            if (field.getAnnotation(NotNull.class) != null && field.get(o) == null) {
                throw new IllegalStateException("validation failed for field " + field.getName());
            }
        }
    }
}
