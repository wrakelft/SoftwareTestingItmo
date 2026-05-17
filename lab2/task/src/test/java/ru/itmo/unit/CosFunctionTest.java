package ru.itmo.unit;

import org.junit.jupiter.api.Test;
import ru.itmo.function.trig.CosFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.itmo.util.MathConstants.EPS;
import static ru.itmo.util.MathConstants.PI;
import static ru.itmo.util.MathConstants.TWO_PI;

class CosFunctionTest {

    private final CosFunction cos = new CosFunction();

    @Test
    void shouldReturnOneAtZero() {
        assertEquals(1.0, cos.apply(0.0), EPS);
    }

    @Test
    void shouldReturnMinusOneAtPi() {
        assertEquals(-1.0, cos.apply(PI), EPS);
    }

    @Test
    void shouldReturnOneAtTwoPi() {
        assertEquals(1.0, cos.apply(TWO_PI), EPS);
    }

    @Test
    void shouldReturnZeroAtPiDividedByTwo() {
        assertEquals(0.0, cos.apply(PI / 2.0), EPS);
    }

    @Test
    void shouldBeEvenFunction() {
        double x = 1.234;

        assertEquals(cos.apply(x), cos.apply(-x), EPS);
    }

    @Test
    void shouldBePeriodicWithTwoPiPeriod() {
        double x = 0.75;

        assertEquals(cos.apply(x), cos.apply(x + TWO_PI), EPS);
        assertEquals(cos.apply(x), cos.apply(x - TWO_PI), EPS);
    }

    @Test
    void shouldCheckExtremumPointAtZero() {
        double center = cos.apply(0.0);
        double left = cos.apply(-0.01);
        double right = cos.apply(0.01);

        assertTrue(center > left);
        assertTrue(center > right);
    }

    @Test
    void shouldCheckExtremumPointAtPi() {
        double center = cos.apply(PI);
        double left = cos.apply(PI - 0.01);
        double right = cos.apply(PI + 0.01);

        assertTrue(center < left);
        assertTrue(center < right);
    }
}