package ru.itmo.integration;

import org.junit.jupiter.api.Test;
import ru.itmo.function.MathFunction;
import ru.itmo.function.trig.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.itmo.util.MathConstants.EPS;
import static ru.itmo.util.MathConstants.PI;

public class DerivedTrigIntegrationTest {

    @Test
    void shouldIntegrateAllDerivedTrigWithCosAndSin() {
        MathFunction cos = new CosFunction();
        MathFunction sin = new SinFunction(cos);

        MathFunction tan = new TanFunction(sin, cos);
        MathFunction cot = new CotFunction(sin, cos);
        MathFunction sec = new SecFunction(cos);
        MathFunction csc = new CscFunction(sin);

        assertEquals(1.0, tan.apply(PI / 4.0), 1e-6);
        assertEquals(1.0, cot.apply(PI / 4.0), 1e-6);
        assertEquals(1.0, sec.apply(0.0), EPS);
        assertEquals(1.0, csc.apply(PI / 2.0), EPS);

        assertThrows(ArithmeticException.class, () -> tan.apply(PI / 2.0));
        assertThrows(ArithmeticException.class, () -> cot.apply(0.0));
        assertThrows(ArithmeticException.class, () -> sec.apply(PI / 2.0));
        assertThrows(ArithmeticException.class, () -> csc.apply(0.0));
    }
}
