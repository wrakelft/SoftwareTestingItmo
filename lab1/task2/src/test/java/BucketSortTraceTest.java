import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.itmo.utils.BucketSort;
import ru.itmo.utils.BucketSortTraceResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BucketSortTraceTest {

    @Test
    @DisplayName("Должен давать корректную трассу для пустого массива")
    void shouldTraceEmptyArray() {
        BucketSortTraceResult result = BucketSort.sortWithTrace(new int[]{});

        List<String> expected = List.of(
                "T1:START",
                "T2:NOT_NULL",
                "T3:EMPTY"
        );

        assertEquals(expected, result.getTrace());
    }

    @Test
    @DisplayName("Должен давать корректную трассу для массива из одинаковых элементов")
    void shouldTraceAllEqualArray() {
        BucketSortTraceResult result = BucketSort.sortWithTrace(new int[]{7, 7, 7});

        List<String> expected = List.of(
                "T1:START",
                "T2:NOT_NULL",
                "T3:NOT_EMPTY",
                "T4:CREATE_BUCKETS",
                "T5:FIND_MIN_MAX",
                "T6:NO_NEGATIVE",
                "T7:ALL_EQUAL"
        );

        assertEquals(expected, result.getTrace());
    }

    @Test
    @DisplayName("Должен давать корректную трассу для обычного массива")
    void shouldTraceNormalArray() {
        BucketSortTraceResult result = BucketSort.sortWithTrace(new int[]{5, 1, 9, 3});

        List<String> trace = result.getTrace();

        assertEquals("T1:START", trace.get(0));
        assertEquals("T2:NOT_NULL", trace.get(1));
        assertEquals("T3:NOT_EMPTY", trace.get(2));
        assertEquals("T4:CREATE_BUCKETS", trace.get(3));
        assertEquals("T5:FIND_MIN_MAX", trace.get(4));
        assertEquals("T6:NO_NEGATIVE", trace.get(5));
        assertEquals("T7:NOT_ALL_EQUAL", trace.get(6));
        assertEquals("T8:DISTRIBUTE", trace.get(7));
        assertEquals("T10:SORT_BUCKETS", trace.get(trace.size() - 3));
        assertEquals("T11:MERGE", trace.get(trace.size() - 2));
        assertEquals("T12:END", trace.get(trace.size() - 1));
    }
}