import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.itmo.utils.ArcsinSeries;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArcsinSeriesInvalidTest {

    private static final int DEFAULT_N = 20;

    @ParameterizedTest(name = "calc({0}, 20) should throw")
    @DisplayName("Должен выбрасывать исключение для x вне области определения")
    @ValueSource(doubles = {
            -999.0,
            -1.0000001,
            1.0000001,
            999.0,
            Double.NaN,
            Double.POSITIVE_INFINITY,
            Double.NEGATIVE_INFINITY
    })
    void throwForInvalidX(double x) {
        assertThrows(IllegalArgumentException.class, () -> ArcsinSeries.calc(x, DEFAULT_N));
    }
}
