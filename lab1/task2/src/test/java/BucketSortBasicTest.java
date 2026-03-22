import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.itmo.utils.BucketSort;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class BucketSortBasicTest {
    @Test
    @DisplayName("Сортировка обычного неупорядоченного массива")
    void sortUnorderedArray() {
        int[] input = {5, 1, 9, 3, 7};
        int[] expected = {1, 3, 5, 7, 9};

        assertArrayEquals(expected, BucketSort.sort(input));
    }

    @Test
    @DisplayName("Обработка уже отсортированного массива")
    void handleAlreadySortedArray() {
        int[] input = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};

        assertArrayEquals(expected, BucketSort.sort(input));
    }

    @Test
    @DisplayName("Сортировка массива, упорядоченного по убыванию")
    void sortReverseOrderedArray() {
        int[] input = {9, 7, 5, 3, 1};
        int[] expected = {1, 3, 5, 7, 9};

        assertArrayEquals(expected, BucketSort.sort(input));
    }

    @Test
    @DisplayName("Сортировка массива с повторяющимися значениями")
    void sortArrayWithDuplicates() {
        int[] input = {5, 1, 5, 3, 1, 7};
        int[] expected = {1, 1, 3, 5, 5, 7};

        assertArrayEquals(expected, BucketSort.sort(input));
    }
}
