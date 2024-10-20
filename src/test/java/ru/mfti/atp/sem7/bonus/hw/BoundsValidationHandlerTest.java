package ru.mfti.atp.sem7.bonus.hw;

import org.junit.jupiter.api.Test;
import ru.mfti.atp.sem7.Calculator;
import ru.mfti.atp.sem7.CalculatorImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.mfti.atp.sem7.bonus.hw.BoundsValidationHandler.validateBounds;

public class BoundsValidationHandlerTest {
    @Test
    public void testFirstArgBounds() {
        Calculator calculator = validateBounds(new CalculatorImpl());

        assertEquals(1, calculator.add(0, 1));
        assertEquals(12, calculator.add(10, 2));

        assertThrows(IllegalStateException.class, () -> calculator.add(-1, 3));
        assertThrows(IllegalStateException.class, () -> calculator.add(11, 4));
    }

    @Test
    public void testSecondArgBounds() {
        Calculator calculator = validateBounds(new CalculatorImpl());

        assertEquals(1, calculator.add(1, 0));
        assertEquals(7, calculator.add(2, 5));

        assertThrows(IllegalStateException.class, () -> calculator.add(3, -1));
        assertThrows(IllegalStateException.class, () -> calculator.add(4, 6));
    }
}
