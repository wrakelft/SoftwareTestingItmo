import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.itmo.utils.BucketSort;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class BucketSortInvalidInputTest {

    @Test
    @DisplayName("Должен выбрасывать исключение для массива с отрицательными числами")
    void throwForNegativeNum() {
        int[] input = {5, -1, 3, 7};

        assertThrows(IllegalArgumentException.class, () -> BucketSort.sort(input));
    }
}
