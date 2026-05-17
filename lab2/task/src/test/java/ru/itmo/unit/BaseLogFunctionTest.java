package ru.itmo.unit;

import org.junit.jupiter.api.Test;
import ru.itmo.function.MathFunction;
import ru.itmo.function.log.BaseLogFunction;
import ru.itmo.function.log.LnFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.itmo.util.MathConstants.EPS;

class BaseLogFunctionTest {

    private final MathFunction ln = new LnFunction();

    @Test
    void shouldCalculateLog2() {
        MathFunction log2 = new BaseLogFunction(ln, 2.0);

        assertEquals(0.0, log2.apply(1.0), EPS);
        assertEquals(1.0, log2.apply(2.0), 1e-6);
        assertEquals(2.0, log2.apply(4.0), 1e-6);
        assertEquals(3.0, log2.apply(8.0), 1e-6);
    }

    @Test
    void shouldCalculateLog5() {
        MathFunction log5 = new BaseLogFunction(ln, 5.0);

        assertEquals(0.0, log5.apply(1.0), EPS);
        assertEquals(1.0, log5.apply(5.0), 1e-6);
        assertEquals(2.0, log5.apply(25.0), 1e-6);
    }

    @Test
    void shouldThrowForInvalidArgument() {
        MathFunction log2 = new BaseLogFunction(ln, 2.0);

        assertThrows(ArithmeticException.class, () -> log2.apply(0.0));
        assertThrows(ArithmeticException.class, () -> log2.apply(-1.0));
    }

    @Test
    void shouldThrowForInvalidBase() {
        assertThrows(IllegalArgumentException.class, () -> new BaseLogFunction(ln, 0.0));
        assertThrows(IllegalArgumentException.class, () -> new BaseLogFunction(ln, -2.0));
        assertThrows(IllegalArgumentException.class, () -> new BaseLogFunction(ln, 1.0));
    }
}