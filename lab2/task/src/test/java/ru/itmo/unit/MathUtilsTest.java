package ru.itmo.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MathUtilsTest {

    @Test
    void shouldCalculatePower() {
        assertEquals(1.0, MathUtils.pow(5.0, 0));
        assertEquals(8.0, MathUtils.pow(2.0, 3));
        assertEquals(16.0, MathUtils.pow(-2.0, 4));
        assertEquals(-8.0, MathUtils.pow(-2.0, 3));
    }

    @Test
    void shouldThrowForNegativePower() {
        assertThrows(IllegalArgumentException.class, () -> MathUtils.pow(2.0, -1));
    }

    @Test
    void shouldCalculateAbs() {
        assertEquals(5.0, MathUtils.abs(5.0));
        assertEquals(5.0, MathUtils.abs(-5.0));
        assertEquals(0.0, MathUtils.abs(0.0));
    }
}