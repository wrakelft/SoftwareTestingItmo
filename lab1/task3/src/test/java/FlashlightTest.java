import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.itmo.enums.Direction;
import ru.itmo.model.Flashlight;

import static org.junit.jupiter.api.Assertions.*;

public class FlashlightTest {

    @Test
    @DisplayName("Должен корректно создавать фонарь")
    void shouldCreateFlashlight() {
        Flashlight flashlight = new Flashlight("Фонарь", 10);

        assertEquals("Фонарь", flashlight.getName());
        assertEquals(10, flashlight.getBatteryLevel());
        assertFalse(flashlight.isOn());
    }

    @Test
    @DisplayName("Должен включаться при положительном заряде")
    void shouldTurnOn() {
        Flashlight flashlight = new Flashlight("Фонарь", 10);

        flashlight.turnOn();

        assertTrue(flashlight.isOn());
    }

    @Test
    @DisplayName("Не должен светить при выключенном состоянии")
    void shouldNotIlluminateWhenTurnedOff() {
        Flashlight flashlight = new Flashlight("Фонарь", 10);

        assertFalse(flashlight.canIlluminate());
    }

    @Test
    @DisplayName("Должен светить при включенном состоянии")
    void shouldIlluminateWhenTurnedOn() {
        Flashlight flashlight = new Flashlight("Фонарь", 10);
        flashlight.turnOn();

        assertTrue(flashlight.canIlluminate());
    }

    @Test
    @DisplayName("Не должен светить при нулевом заряде даже после попытки включения")
    void shouldNotIlluminateWithZeroBattery() {
        Flashlight flashlight = new Flashlight("Фонарь", 0);

        flashlight.turnOn();

        assertFalse(flashlight.canIlluminate());
    }

    @Test
    @DisplayName("Должен менять направление луча и тратить заряд")
    void shouldShineToDirectionAndConsumeBattery() {
        Flashlight flashlight = new Flashlight("Фонарь", 10);
        flashlight.turnOn();

        flashlight.shineTo(Direction.налево);

        assertEquals(Direction.налево, flashlight.getBeamDirection());
        assertEquals(9, flashlight.getBatteryLevel());
    }

    @Test
    @DisplayName("Должен выбрасывать исключение при попытке светить выключенным фонарём")
    void shouldThrowWhenShiningWhileOff() {
        Flashlight flashlight = new Flashlight("Фонарь", 10);

        assertThrows(IllegalStateException.class, () -> flashlight.shineTo(Direction.Прямо));
    }

    @Test
    @DisplayName("Должен выбрасывать исключение, если заряд батареи меньше 0")
    void shouldThrowWhenBatteryLevelIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Flashlight("Фонарь", -1));
    }

    @Test
    @DisplayName("Должен выбрасывать исключение, если заряд батареи больше 100")
    void shouldThrowWhenBatteryLevelIsGreaterThanHundred() {
        assertThrows(IllegalArgumentException.class, () -> new Flashlight("Фонарь", 101));
    }

    @Test
    @DisplayName("Должен автоматически выключаться при полном разряде")
    void shouldTurnOffAutomaticallyWhenBatteryBecomesZero() {
        Flashlight flashlight = new Flashlight("Фонарь", 1);
        flashlight.turnOn();

        flashlight.shineTo(Direction.Прямо);

        assertEquals(0, flashlight.getBatteryLevel());
        assertFalse(flashlight.isOn());
    }

    @Test
    @DisplayName("Должен выключаться при вызове turnOff")
    void shouldTurnOffManually() {
        Flashlight flashlight = new Flashlight("Фонарь", 10);
        flashlight.turnOn();

        flashlight.turnOff();

        assertFalse(flashlight.isOn());
    }


}