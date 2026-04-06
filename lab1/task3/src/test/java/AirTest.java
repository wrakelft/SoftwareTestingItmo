import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import ru.itmo.model.Air;

import static org.junit.jupiter.api.Assertions.*;

public class AirTest {

    @Timeout(1)
    @Test
    @DisplayName("Должен корректно создавать объект Air")
    void shouldCreateAir() {
        Air air = new Air(true, 8);

        assertTrue(air.hasDecaySmell());
        assertEquals(8, air.getIntensity());
        assertEquals("тление", air.getSmellDescription());
    }

    @Test
    @DisplayName("Должен определять запах тления")
    void shouldDetectDecaySmell() {
        Air air = new Air(true, 5);

        assertTrue(air.hasDecaySmell());
    }

    @Test
    @DisplayName("Не должен определять обычный запах как тление")
    void shouldNotDetectDecaySmellForNormalAir() {
        Air air = new Air(false, 3);

        assertFalse(air.hasDecaySmell());
        assertEquals(3, air.getIntensity());
        assertEquals("обычный", air.getSmellDescription());
    }

    @Test
    @DisplayName("Должен выбрасывать исключение при отрицательной интенсивности")
    void shouldThrowForNegativeIntensity() {
        assertThrows(IllegalArgumentException.class, () -> new Air(true, -1));

    }
}