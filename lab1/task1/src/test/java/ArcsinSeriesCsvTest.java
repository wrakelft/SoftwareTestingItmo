import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.itmo.utils.ArcsinSeries;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArcsinSeriesCsvTest {
    private static final double DELTA = 1e-6;

    @ParameterizedTest(name = "calc({0}, 20) = {1}")
    @DisplayName("Проверка табличных значений arcsin(x) из CSV")
    @CsvFileSource(resources = "/values.csv")
    void matchExpectedValues(double x) {
        assertEquals(Math.asin(x), ArcsinSeries.calc(x, Integer.MAX_VALUE), DELTA);
    }
}
