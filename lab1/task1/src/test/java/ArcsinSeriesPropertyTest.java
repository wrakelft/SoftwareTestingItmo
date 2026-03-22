import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.itmo.utils.ArcsinSeries;

import static org.junit.jupiter.api.Assertions.*;

public class ArcsinSeriesPropertyTest {
    private static final double DELTA = 1e-6;
    private static final int DEFAULT_N = 20;

    @Test
    @DisplayName("Для x = 0 должно возвращаться 0")
    void returnZero() {
        assertEquals(Math.asin(0), ArcsinSeries.calc(0.0, DEFAULT_N), 0.0);
    }

    @Test
    @DisplayName("Для x = -0 должно возвращаться -0")
    void returnZeroNegative() {
        assertEquals(
                Double.doubleToLongBits(Math.asin(-0.0)),
                Double.doubleToLongBits(ArcsinSeries.calc(-0.0, DEFAULT_N))
        );
    }

    @Test
    @DisplayName("Для очень малого +x результат не должен быть 0")
    void notReturnZeroForPositive() {
        double result = ArcsinSeries.calc(0.000001, DEFAULT_N);
        assertNotEquals(0.0, result, 0.0);
    }

    @Test
    @DisplayName("Для очень малого -x результат не должен быть 0")
    void notReturnZeroForNegative() {
        double result = ArcsinSeries.calc(-0.000001, DEFAULT_N);
        assertNotEquals(0.0, result, 0.0);
    }

    @Test
    @DisplayName("Функция должна быть нечётной")
    void shouldBeOddFunction() {
        double x = 0.5;
        double positive = ArcsinSeries.calc(x, DEFAULT_N);
        double negative = ArcsinSeries.calc(-x, DEFAULT_N);

        assertEquals(-positive, negative, DELTA);
    }

    @Test
    @DisplayName("С увеличением числа членов ряда точность должна улучшаться")
    void shouldImproveAccuracyWhenTermsCountIncreases() {
        double x = 0.99;
        double expected = Math.asin(x);

        double error5 = Math.abs(expected - ArcsinSeries.calc(x, 5));
        double error10 = Math.abs(expected - ArcsinSeries.calc(x, 10));
        double error20 = Math.abs(expected - ArcsinSeries.calc(x, DEFAULT_N));

        assertTrue(error10 <= error5);
        assertTrue(error20 <= error10);
    }
}
