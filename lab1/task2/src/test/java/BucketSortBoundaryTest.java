import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.itmo.utils.BucketSort;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BucketSortBoundaryTest {

    @Test
    @DisplayName("Должен выбрасывать исключение для null")
    void throwForNullArray() {
        assertThrows(IllegalArgumentException.class, () -> BucketSort.sort(null));
    }

    @Test
    @DisplayName("Должен корректно обрабатывать пустой массив")
    void shouldHandleEmptyArray() {
        int[] input = {};
        int[] expected = {};

        assertArrayEquals(expected, BucketSort.sort(input));
    }

    @Test
    @DisplayName("Должен корректно обрабатывать массив из 1 элемента")
    void handleSingleArray() {
        int[] input = {42};
        int[] expected = {42};

        assertArrayEquals(expected, BucketSort.sort(input));
    }

    @Test
    @DisplayName("Должен корректно обрабатывать массив из одинаковых элементов")
    void handleEqualArray() {
        int[] input = {7, 7, 7, 7};
        int[] expected = {7, 7, 7, 7};

        assertArrayEquals(expected, BucketSort.sort(input));
    }
}
