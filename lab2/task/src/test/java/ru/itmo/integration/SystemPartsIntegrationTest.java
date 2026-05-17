package ru.itmo.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.itmo.function.MathFunction;
import ru.itmo.function.log.BaseLogFunction;
import ru.itmo.function.log.LnFunction;
import ru.itmo.function.trig.CosFunction;
import ru.itmo.function.trig.CotFunction;
import ru.itmo.function.trig.CscFunction;
import ru.itmo.function.trig.SecFunction;
import ru.itmo.function.trig.SinFunction;
import ru.itmo.function.trig.TanFunction;
import ru.itmo.system.LogPartFunction;
import ru.itmo.system.TrigPartFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.itmo.util.MathConstants.TWO_PI;

class SystemPartsIntegrationTest {

    private MathFunction trigPart;
    private MathFunction logPart;

    @BeforeEach
    void setUp() {
        MathFunction cos = new CosFunction();
        MathFunction sin = new SinFunction(cos);

        MathFunction tan = new TanFunction(sin, cos);
        MathFunction cot = new CotFunction(sin, cos);
        MathFunction sec = new SecFunction(cos);
        MathFunction csc = new CscFunction(sin);

        trigPart = new TrigPartFunction(sin, tan, cot, sec, csc);

        MathFunction ln = new LnFunction();
        MathFunction log2 = new BaseLogFunction(ln, 2.0);
        MathFunction log5 = new BaseLogFunction(ln, 5.0);

        logPart = new LogPartFunction(ln, log2, log5);
    }

    @Test
    void shouldCalculateTrigPartAtRegularPoint() {
        double result = trigPart.apply(-1.0);

        assertTrue(Double.isFinite(result));
    }

    @Test
    void shouldThrowForTrigPartAtZero() {
        assertThrows(ArithmeticException.class, () -> trigPart.apply(0.0));
    }

    @Test
    void shouldCheckTrigPartPeriodicity() {
        double x = -1.0;

        assertEquals(trigPart.apply(x), trigPart.apply(x - TWO_PI), 1e-4);
        assertEquals(trigPart.apply(x), trigPart.apply(x + TWO_PI), 1e-4);
    }

    @Test
    void shouldCalculateLogPartAtOne() {
        assertEquals(0.0, logPart.apply(1.0), 1e-7);
    }

    @Test
    void shouldCalculateLogPartAtRegularPoint() {
        double result = logPart.apply(2.0);

        assertTrue(Double.isFinite(result));
    }

    @Test
    void shouldThrowForLogPartAtZero() {
        assertThrows(ArithmeticException.class, () -> logPart.apply(0.0));
    }

    @Test
    void shouldThrowForLogPartAtNegativeArgument() {
        assertThrows(ArithmeticException.class, () -> logPart.apply(-1.0));
    }
}