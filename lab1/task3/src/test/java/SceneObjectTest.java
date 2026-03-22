import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.itmo.model.SceneObject;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SceneObjectTest {

    private static class TestSceneObject extends SceneObject {
        public TestSceneObject(String name) {
            super(name);
        }
    }

    @Test
    @DisplayName("Должен корректно хранить имя объекта сцены")
    void shouldStoreName() {
        SceneObject object = new TestSceneObject("Объект сцены");

        assertEquals("Объект сцены", object.getName());
    }
}