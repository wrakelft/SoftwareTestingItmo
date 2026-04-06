package ru.itmo.utils;

import java.util.*;

public class BucketSort {

    public static int[] sort(int[] digits) {
        return sortWithTrace(digits).getSortedArray();
    }
    public static BucketSortTraceResult sortWithTrace(int[] digits) {
        List<String> trace = new ArrayList<>();
        trace.add("T1:START");

        if (digits == null) {
            trace.add("T2:NULL");
            throw new IllegalArgumentException("Массив не должен быть null");
        }
        trace.add("T2:NOT_NULL");
        if (digits.length == 0) {
            trace.add("T3:EMPTY");
            return new BucketSortTraceResult(new int[0], trace);
        }
        trace.add("T3:NOT_EMPTY");

        int bucketCount = digits.length;
        trace.add("T4:CREATE_BUCKETS");

        @SuppressWarnings("unchecked")
        LinkedList<Integer>[] buckets = new LinkedList[bucketCount];
        for (int i = 0; i < bucketCount; i++) {
            buckets[i] = new LinkedList<>();
        }
        trace.add("T5:FIND_MIN_MAX");
        int maxD = Arrays.stream(digits).max().orElse(0);
        int minD = Arrays.stream(digits).min().orElse(0);

        if (minD < 0) {
            trace.add("T6:NEGATIVE_FOUND");
            throw new IllegalArgumentException("Отрицательные числа не поддерживаются");
        }
        trace.add("T6:NO_NEGATIVE");

        if (maxD == minD) {
            trace.add("T7:ALL_EQUAL");
            return new BucketSortTraceResult(Arrays.copyOf(digits, digits.length), trace);
        }
        trace.add("T7:NOT_ALL_EQUAL");

        trace.add("T8:DISTRIBUTE");
        for (int digit : digits) {
            int index = (digit * bucketCount) / (maxD + 1);
            trace.add("T9:PUT_" + digit + "_TO_" + index);
            buckets[index].add(digit);
        }

        trace.add("T10:SORT_BUCKETS");
        for (int i = 0; i < bucketCount; i++) {
            Collections.sort(buckets[i]);
        }

        trace.add("T11:MERGE");
        int[] result = Arrays.stream(buckets)
                .flatMap(List::stream)
                .mapToInt(Integer::intValue)
                .toArray();
        trace.add("T12:END");
        return new BucketSortTraceResult(result, trace);
    }
}
