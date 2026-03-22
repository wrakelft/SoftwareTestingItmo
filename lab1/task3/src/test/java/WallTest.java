import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.itmo.enums.TemperatureLevel;
import ru.itmo.enums.TileColor;
import ru.itmo.model.Wall;

import static org.junit.jupiter.api.Assertions.*;

public class WallTest {

    @Test
    @DisplayName("Должен корректно создавать стену")
    void shouldCreateWall() {
        Wall wall = new Wall("Левая стена", TemperatureLevel.Холод, TileColor.Темная);

        assertEquals("Левая стена", wall.getName());
        assertEquals(TemperatureLevel.Холод, wall.feelTemperature());
        assertEquals(TileColor.Темная, wall.getTileColor());
    }

    @Test
    @DisplayName("Должен определять холодную стену")
    void shouldDetectColdWall() {
        Wall wall = new Wall("Стена", TemperatureLevel.Холод, TileColor.Черная);

        assertTrue(wall.isCold());
    }

    @Test
    @DisplayName("Не должен считать тёплую стену холодной")
    void shouldNotDetectWarmWallAsCold() {
        Wall wall = new Wall("Стена", TemperatureLevel.Тепло, TileColor.Серая);

        assertFalse(wall.isCold());
    }

    @Test
    @DisplayName("После прикосновения стена должна считаться тронутой")
    void shouldBecomeTouchedAfterFeelingTemperature() {
        Wall wall = new Wall("Стена", TemperatureLevel.Холод, TileColor.Темная);

        wall.feelTemperature();

        assertTrue(wall.isTouched());
    }
}