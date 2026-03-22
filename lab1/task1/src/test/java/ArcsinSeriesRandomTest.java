import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.itmo.utils.ArcsinSeries;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArcsinSeriesRandomTest {
    private static final double DELTA = 1e-6;

    @Test
    @DisplayName("Случайное тестирование сравнением с Math.asin")
    void shouldMatchMathAsinForRandomValues() {
        for (int i = 0; i < 5000; i++) {
            double x = ThreadLocalRandom.current().nextDouble(-0.99, 0.99);
            assertEquals(Math.asin(x), ArcsinSeries.calc(x, Integer.MAX_VALUE), DELTA);
        }
    }
}
