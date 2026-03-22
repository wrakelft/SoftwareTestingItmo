import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.itmo.enums.SmellType;
import ru.itmo.model.Air;

import static org.junit.jupiter.api.Assertions.*;

public class AirTest {

    @Test
    @DisplayName("Должен корректно создавать объект Air")
    void shouldCreateAir() {
        Air air = new Air(SmellType.Тления, 8);

        assertEquals(SmellType.Тления, air.getSmellType());
        assertEquals(8, air.getIntensity());
    }

    @Test
    @DisplayName("Должен определять запах тления")
    void shouldDetectDecaySmell() {
        Air air = new Air(SmellType.Тления, 5);

        assertTrue(air.hasDecaySmell());
    }

    @Test
    @DisplayName("Не должен определять обычный запах как тление")
    void shouldNotDetectDecaySmellForNormalAir() {
        Air air = new Air(SmellType.Обычный, 3);

        assertFalse(air.hasDecaySmell());
    }

    @Test
    @DisplayName("Должен выбрасывать исключение при отрицательной интенсивности")
    void shouldThrowForNegativeIntensity() {
        assertThrows(IllegalArgumentException.class, () -> new Air(SmellType.Обычный, -1));
    }
}