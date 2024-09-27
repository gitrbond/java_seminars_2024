package ru.mfti.atp.sem3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GarageTest {
    @Test
    void testAdd() throws Exception {
        Garage garage = new Garage();

        Car car1 = new Car("white");
        garage.add(car1);

        assertTrue(garage.getCars().contains(car1));
        assertEquals(1, garage.getCars().size());

        Car car2 = new Car("black");
        assertThrows(GarageException.class, () -> garage.add(car2), "access denied!");

        assertFalse(garage.getCars().contains(car2));
        assertEquals(1, garage.getCars().size());
    }
}
