package ru.itmo.unit;

import org.junit.jupiter.api.Test;
import ru.itmo.function.log.LnFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.itmo.util.MathConstants.E;
import static ru.itmo.util.MathConstants.EPS;

class LnFunctionTest {

    private final LnFunction ln = new LnFunction();

    @Test
    void shouldReturnZeroAtOne() {
        assertEquals(0.0, ln.apply(1.0), EPS);
    }

    @Test
    void shouldReturnOneAtE() {
        assertEquals(1.0, ln.apply(E), 1e-6);
    }

    @Test
    void shouldReturnMinusOneAtOneDividedByE() {
        assertEquals(-1.0, ln.apply(1.0 / E), 1e-6);
    }

    @Test
    void shouldThrowForZero() {
        assertThrows(ArithmeticException.class, () -> ln.apply(0.0));
    }

    @Test
    void shouldThrowForNegativeArgument() {
        assertThrows(ArithmeticException.class, () -> ln.apply(-1.0));
    }

    @Test
    void shouldBeIncreasingFunction() {
        double left = ln.apply(2.0);
        double right = ln.apply(3.0);

        assertTrue(right > left);
    }
}