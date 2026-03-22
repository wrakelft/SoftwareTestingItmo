import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.itmo.enums.SmellType;
import ru.itmo.enums.TemperatureLevel;
import ru.itmo.enums.TileColor;
import ru.itmo.model.Air;
import ru.itmo.model.Tunnel;
import ru.itmo.model.Wall;

import static org.junit.jupiter.api.Assertions.*;

public class TunnelTest {

    @Test
    @DisplayName("Должен корректно создавать тоннель")
    void shouldCreateTunnel() {
        Wall leftWall = new Wall("Левая", TemperatureLevel.Холод, TileColor.Темная);
        Wall rightWall = new Wall("Правая", TemperatureLevel.Холод, TileColor.Черная);
        Air air = new Air(SmellType.Тления, 7);

        Tunnel tunnel = new Tunnel("Тоннель", leftWall, rightWall, air);

        assertEquals("Тоннель", tunnel.getName());
        assertEquals(leftWall, tunnel.getLeftWall());
        assertEquals(rightWall, tunnel.getRightWall());
        assertEquals(air, tunnel.getAir());
    }

    @Test
    @DisplayName("Должен определять наличие холодных стен")
    void shouldDetectColdWalls() {
        Wall leftWall = new Wall("Левая", TemperatureLevel.Холод, TileColor.Темная);
        Wall rightWall = new Wall("Правая", TemperatureLevel.Тепло, TileColor.Черная);
        Air air = new Air(SmellType.Обычный, 2);

        Tunnel tunnel = new Tunnel("Тоннель", leftWall, rightWall, air);

        assertTrue(tunnel.hasColdWalls());
    }

    @Test
    @DisplayName("Должен определять наличие холодных стен")
    void shouldDetectColdWallsAnotherSide() {
        Wall leftWall = new Wall("Левая", TemperatureLevel.Тепло, TileColor.Темная);
        Wall rightWall = new Wall("Правая", TemperatureLevel.Холод, TileColor.Черная);
        Air air = new Air(SmellType.Обычный, 2);

        Tunnel tunnel = new Tunnel("Тоннель", leftWall, rightWall, air);

        assertTrue(tunnel.hasColdWalls());
    }

    @Test
    @DisplayName("Не должен определять холодные стены, если обе стены тёплые")
    void shouldNotDetectColdWalls() {
        Wall leftWall = new Wall("Левая", TemperatureLevel.Тепло, TileColor.Серая);
        Wall rightWall = new Wall("Правая", TemperatureLevel.Тепло, TileColor.Черная);
        Air air = new Air(SmellType.Обычный, 1);

        Tunnel tunnel = new Tunnel("Тоннель", leftWall, rightWall, air);

        assertFalse(tunnel.hasColdWalls());
    }

    @Test
    @DisplayName("Должен возвращать true, если обе стены холодные")
    void shouldReturnFalseIfWallsAreCold() {
        Wall leftWall = new Wall("Левая", TemperatureLevel.Холод, TileColor.Серая);
        Wall rightWall = new Wall("Правая", TemperatureLevel.Холод, TileColor.Черная);
        Air air = new Air(SmellType.Обычный, 2);

        Tunnel tunnel = new Tunnel("Тоннель", leftWall, rightWall, air);

        assertTrue(tunnel.hasColdWalls());
    }


}