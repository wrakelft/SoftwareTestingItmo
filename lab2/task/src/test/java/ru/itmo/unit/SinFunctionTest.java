package ru.itmo.unit;

import org.junit.jupiter.api.Test;
import ru.itmo.function.trig.CosFunction;
import ru.itmo.function.trig.SinFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.itmo.util.MathConstants.EPS;
import static ru.itmo.util.MathConstants.PI;
import static ru.itmo.util.MathConstants.TWO_PI;

class SinFunctionTest {

    private final SinFunction sin = new SinFunction(new CosFunction());

    @Test
    void shouldReturnZeroAtZero() {
        assertEquals(0.0, sin.apply(0.0), EPS);
    }

    @Test
    void shouldReturnOneAtPiDividedByTwo() {
        assertEquals(1.0, sin.apply(PI / 2.0), EPS);
    }

    @Test
    void shouldReturnZeroAtPi() {
        assertEquals(0.0, sin.apply(PI), EPS);
    }

    @Test
    void shouldReturnMinusOneAtMinusPiDividedByTwo() {
        assertEquals(-1.0, sin.apply(-PI / 2.0), EPS);
    }

    @Test
    void shouldBeOddFunction() {
        double x = 1.234;

        assertEquals(-sin.apply(x), sin.apply(-x), EPS);
    }

    @Test
    void shouldBePeriodicWithTwoPiPeriod() {
        double x = 0.75;

        assertEquals(sin.apply(x), sin.apply(x + TWO_PI), EPS);
        assertEquals(sin.apply(x), sin.apply(x - TWO_PI), EPS);
    }

    @Test
    void shouldCheckExtremumPointAtPiDividedByTwo() {
        double center = sin.apply(PI / 2.0);
        double left = sin.apply(PI / 2.0 - 0.01);
        double right = sin.apply(PI / 2.0 + 0.01);

        assertTrue(center > left);
        assertTrue(center > right);
    }

    @Test
    void shouldCheckExtremumPointAtMinusPiDividedByTwo() {
        double center = sin.apply(-PI / 2.0);
        double left = sin.apply(-PI / 2.0 - 0.01);
        double right = sin.apply(-PI / 2.0 + 0.01);

        assertTrue(center < left);
        assertTrue(center < right);
    }
}