package ru.itmo.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.itmo.function.MathFunction;
import ru.itmo.function.trig.CosFunction;
import ru.itmo.function.trig.CotFunction;
import ru.itmo.function.trig.CscFunction;
import ru.itmo.function.trig.SecFunction;
import ru.itmo.function.trig.SinFunction;
import ru.itmo.function.trig.TanFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.itmo.util.MathConstants.EPS;
import static ru.itmo.util.MathConstants.PI;
import static ru.itmo.util.MathConstants.TWO_PI;

class DerivedTrigFunctionTest {

    private MathFunction sin;
    private MathFunction cos;
    private MathFunction tan;
    private MathFunction cot;
    private MathFunction sec;
    private MathFunction csc;

    @BeforeEach
    void setUp() {
        cos = new CosFunction();
        sin = new SinFunction(cos);

        tan = new TanFunction(sin, cos);
        cot = new CotFunction(sin, cos);
        sec = new SecFunction(cos);
        csc = new CscFunction(sin);
    }

    @Test
    void shouldCalculateTanAtRegularPoints() {
        assertEquals(0.0, tan.apply(0.0), EPS);
        assertEquals(1.0, tan.apply(PI / 4.0), 1e-6);
        assertEquals(-1.0, tan.apply(-PI / 4.0), 1e-6);
    }

    @Test
    void shouldThrowForTanAtPiDividedByTwo() {
        assertThrows(ArithmeticException.class, () -> tan.apply(PI / 2.0));
    }

    @Test
    void shouldCalculateCotAtRegularPoints() {
        assertEquals(1.0, cot.apply(PI / 4.0), 1e-6);
        assertEquals(-1.0, cot.apply(-PI / 4.0), 1e-6);
    }

    @Test
    void shouldThrowForCotAtZero() {
        assertThrows(ArithmeticException.class, () -> cot.apply(0.0));
    }

    @Test
    void shouldCalculateSecAtRegularPoints() {
        assertEquals(1.0, sec.apply(0.0), EPS);
        assertEquals(-1.0, sec.apply(PI), EPS);
    }

    @Test
    void shouldThrowForSecAtPiDividedByTwo() {
        assertThrows(ArithmeticException.class, () -> sec.apply(PI / 2.0));
    }

    @Test
    void shouldCalculateCscAtRegularPoints() {
        assertEquals(1.0, csc.apply(PI / 2.0), EPS);
        assertEquals(-1.0, csc.apply(-PI / 2.0), EPS);
    }

    @Test
    void shouldThrowForCscAtZero() {
        assertThrows(ArithmeticException.class, () -> csc.apply(0.0));
    }

    @Test
    void shouldCheckTanPeriodicity() {
        double x = 0.75;

        assertEquals(tan.apply(x), tan.apply(x + PI), 1e-6);
        assertEquals(tan.apply(x), tan.apply(x - PI), 1e-6);
    }

    @Test
    void shouldCheckCotPeriodicity() {
        double x = 0.75;

        assertEquals(cot.apply(x), cot.apply(x + PI), 1e-6);
        assertEquals(cot.apply(x), cot.apply(x - PI), 1e-6);
    }

    @Test
    void shouldCheckSecPeriodicity() {
        double x = 0.75;

        assertEquals(sec.apply(x), sec.apply(x + TWO_PI), 1e-6);
        assertEquals(sec.apply(x), sec.apply(x - TWO_PI), 1e-6);
    }

    @Test
    void shouldCheckCscPeriodicity() {
        double x = 0.75;

        assertEquals(csc.apply(x), csc.apply(x + TWO_PI), 1e-6);
        assertEquals(csc.apply(x), csc.apply(x - TWO_PI), 1e-6);
    }
}