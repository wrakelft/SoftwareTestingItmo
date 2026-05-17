package ru.itmo.integration;

import org.junit.jupiter.api.Test;
import ru.itmo.function.MathFunction;
import ru.itmo.function.trig.CosFunction;
import ru.itmo.function.trig.SinFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.itmo.util.MathConstants.EPS;
import static ru.itmo.util.MathConstants.PI;

public class SinCosIntegrationTest {

    @Test
    void shouldCalcSinUsingCos() {
        MathFunction cos = new CosFunction();
        MathFunction sin = new SinFunction(cos);

        assertEquals(0.0, sin.apply(0.0), EPS);
        assertEquals(1.0, sin.apply(PI / 2.0), EPS);
        assertEquals(0.0, sin.apply(PI), EPS);
        assertEquals(-1.0, sin.apply(-PI / 2.0), EPS);
    }
}
