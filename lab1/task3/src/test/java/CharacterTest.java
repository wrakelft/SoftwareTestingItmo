import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.itmo.enums.Direction;
import ru.itmo.enums.DisplayedState;
import ru.itmo.enums.EmotionalState;
import ru.itmo.enums.MovementSpeed;
import ru.itmo.enums.SmellType;
import ru.itmo.enums.TemperatureLevel;
import ru.itmo.enums.TileColor;
import ru.itmo.model.Air;
import ru.itmo.model.Character;
import ru.itmo.model.Flashlight;
import ru.itmo.model.Tunnel;
import ru.itmo.model.Wall;

import static org.junit.jupiter.api.Assertions.*;

public class CharacterTest {

    private Character createCharacter() {
        Wall leftWall = new Wall("Левая стена", TemperatureLevel.Холод, TileColor.Темная);
        Wall rightWall = new Wall("Правая стена", TemperatureLevel.Холод, TileColor.Черная);
        Air air = new Air(SmellType.Тления, 8);
        Tunnel tunnel = new Tunnel("Темный тоннель", leftWall, rightWall, air);
        Flashlight flashlight = new Flashlight("Фонарь", 10);

        return new Character(
                "Зафод",
                EmotionalState.Нервно,
                DisplayedState.Целеустремленный,
                MovementSpeed.Быстро,
                flashlight,
                tunnel
        );
    }

    @Test
    @DisplayName("Должен корректно создавать персонажа")
    void shouldCreateCharacter() {
        Character character = createCharacter();

        assertEquals("Зафод", character.getName());
        assertEquals(EmotionalState.Нервно, character.getEmotionalState());
        assertEquals(DisplayedState.Целеустремленный, character.getDisplayedState());
        assertEquals(MovementSpeed.Быстро, character.getMovementSpeed());
        assertEquals(0, character.getStepsTaken());
    }

    @Test
    @DisplayName("Должен увеличивать число шагов при движении вперёд")
    void shouldMoveForward() {
        Character character = createCharacter();

        character.moveForward();

        assertEquals(1, character.getStepsTaken());
        assertEquals(Direction.Прямо, character.getCurrentDirection());
    }

    @Test
    @DisplayName("Должен менять отображаемое состояние при маскировке эмоции")
    void shouldMaskEmotion() {
        Character character = createCharacter();

        character.maskEmotional(DisplayedState.Уверенный);

        assertEquals(DisplayedState.Уверенный, character.getDisplayedState());
    }

    @Test
    @DisplayName("Должен определять, что персонаж нервничает")
    void shouldDetectNervousState() {
        Character character = createCharacter();

        assertTrue(character.isNervous());
    }

    @Test
    @DisplayName("Должен ощущать холод левой стены")
    void shouldTouchLeftWall() {
        Character character = createCharacter();

        TemperatureLevel result = character.touchLeftWall();

        assertEquals(TemperatureLevel.Холод, result);
    }

    @Test
    @DisplayName("Должен ощущать холод правой стены")
    void shouldTouchRightWall() {
        Character character = createCharacter();

        TemperatureLevel result = character.touchRightWall();

        assertEquals(TemperatureLevel.Холод, result);
    }

    @Test
    @DisplayName("Должен ощущать запах тления в воздухе")
    void shouldSmellAir() {
        Character character = createCharacter();

        SmellType smell = character.smellAir();

        assertEquals(SmellType.Тления, smell);
    }

    @Test
    @DisplayName("Должен использовать фонарь для освещения направления")
    void shouldShineFlashlight() {
        Character character = createCharacter();

        character.shineFleshLight(Direction.направо);

        assertTrue(character.getFlashlight().isOn());
        assertEquals(Direction.направо, character.getFlashlight().getBeamDirection());
        assertEquals(9, character.getFlashlight().getBatteryLevel());
    }

    @Test
    @DisplayName("Должен менять эмоциональное состояние персонажа")
    void shouldChangeEmotionalState() {
        Character character = createCharacter();

        character.setEmotionalState(EmotionalState.Спокойно);

        assertEquals(EmotionalState.Спокойно, character.getEmotionalState());
    }

    @Test
    @DisplayName("Должен выбрасывать исключение при попытке установить null в эмоциональное состояние")
    void shouldThrowWhenSettingNullEmotionalState() {
        Character character = createCharacter();

        assertThrows(NullPointerException.class, () -> character.setEmotionalState(null));
    }

    @Test
    @DisplayName("Должен возвращать true, если персонаж нервничает")
    void shouldReturnTrueWhenCharacterIsNervous() {
        Character character = createCharacter();

        assertTrue(character.isNervous());
    }

    @Test
    @DisplayName("Должен возвращать false, если персонаж не нервничает")
    void shouldReturnFalseWhenCharacterIsNotNervous() {
        Character character = createCharacter();
        character.setEmotionalState(EmotionalState.Спокойно);

        assertFalse(character.isNervous());
    }

    @Test
    @DisplayName("Должен возвращать текущий тоннель")
    void shouldReturnCurrentTunnel() {
        Wall leftWall = new Wall("Левая стена", TemperatureLevel.Холод, TileColor.Темная);
        Wall rightWall = new Wall("Правая стена", TemperatureLevel.Холод, TileColor.Черная);
        Air air = new Air(SmellType.Тления, 8);
        Tunnel tunnel = new Tunnel("Темный тоннель", leftWall, rightWall, air);
        Flashlight flashlight = new Flashlight("Фонарь", 10);

        Character character = new Character(
                "Зафод",
                EmotionalState.Нервно,
                DisplayedState.Целеустремленный,
                MovementSpeed.Быстро,
                flashlight,
                tunnel
        );
        assertEquals(tunnel, character.getCurrentTunnel());
    }
}