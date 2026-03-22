import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.itmo.utils.BucketSort;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class BucketSortScenarioTest {

    @Test
    @DisplayName("Сортировка массива, где элементы попадают в разные ведра")
    void distributeDifferentBuckets() {
        int[] input = {1, 100, 200, 300, 400};
        int[] expected = {1, 100, 200, 300, 400};

        assertArrayEquals(expected, BucketSort.sort(input));
    }

    @Test
    @DisplayName("Сортировка массива, где часть ведер остаётся пустой")
    void handleEmptyBuckets() {
        int[] input = {10, 11, 12, 1000};
        int[] expected = {10, 11, 12, 1000};

        assertArrayEquals(expected, BucketSort.sort(input));
    }

    @Test
    @DisplayName("Сортировка массива с элементами на границах диапазона")
    void handleBoundaryValues() {
        int[] input = {0, 999, 500, 250, 750};
        int[] expected = {0, 250, 500, 750, 999};

        assertArrayEquals(expected, BucketSort.sort(input));
    }

    @Test
    @DisplayName("Сортировка массива, где несколько элементов попадают в одно ведро")
    void handleElementsInOneBucket() {
        int[] input = {21, 22, 23, 24, 25};
        int[] expected = {21, 22, 23, 24, 25};

        assertArrayEquals(expected, BucketSort.sort(input));
    }
}