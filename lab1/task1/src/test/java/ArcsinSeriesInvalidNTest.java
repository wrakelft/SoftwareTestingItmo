import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.itmo.utils.ArcsinSeries;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArcsinSeriesInvalidNTest {
    @ParameterizedTest(name = "calc(0.5, {0}) should throw")
    @DisplayName("Должен выбрасывать исключение для некорректного числа членов ряда")
    @ValueSource(ints = {0, -1, -10})
    void throwForInvalidN(int n) {
        assertThrows(IllegalArgumentException.class, () -> ArcsinSeries.calc(0.5, n));
    }
}
