package ru.itmo.integration;

import org.junit.jupiter.api.Test;
import ru.itmo.function.MathFunction;
import ru.itmo.function.log.BaseLogFunction;
import ru.itmo.function.log.LnFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LogIntegrationTest {

    @Test
    void shouldIntegrateBaseLogsWithNaturalLogarithm() {
        MathFunction ln = new LnFunction();
        MathFunction log2 = new BaseLogFunction(ln, 2.0);
        MathFunction log5 = new BaseLogFunction(ln, 5.0);

        assertEquals(1.0, log2.apply(2.0), 1e-6);
        assertEquals(2.0, log2.apply(4.0), 1e-6);

        assertEquals(1.0, log5.apply(5.0), 1e-6);
        assertEquals(2.0, log5.apply(25.0), 1e-6);

        assertThrows(ArithmeticException.class, () -> log2.apply(0.0));
        assertThrows(ArithmeticException.class, () -> log5.apply(-1.0));
    }
}