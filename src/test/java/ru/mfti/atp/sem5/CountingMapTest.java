package ru.mfti.atp.sem5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountingMapTest {
    @Test
    public void test() {
        CountingMap<Integer> map = new CountingMapImpl<>();

        map.add(1);
        map.add(1);

        assertEquals(2, map.get(1));
        assertEquals(0, map.get(2));
    }

    @Test
    public void testCopyTo() {
        CountingMap<Integer> map1 = new CountingMapImpl<>();
        CountingMap<Integer> map2 = new CountingMapImpl<>();

        map1.add(1);
        map1.add(1);
        map2.add(2);
        map2.add(3);

        map1.copyTo(map2);

        assertEquals(2, map2.get(1));
        assertEquals(1, map2.get(2));
        assertEquals(1, map2.get(3));
        assertEquals(0, map2.get(4));
    }

    @Test
    public void testCopyToNumber() {
        CountingMap<Integer> map1 = new CountingMapImpl<>();
        CountingMap<Number> map2 = new CountingMapImpl<>();

        map1.add(1);
        map1.add(1);
        map2.add(2);
        map2.add(3);

        map1.copyTo(map2);

        assertEquals(2, map2.get(1));
        assertEquals(1, map2.get(2));
        assertEquals(1, map2.get(3));
        assertEquals(0, map2.get(4));
    }
}
