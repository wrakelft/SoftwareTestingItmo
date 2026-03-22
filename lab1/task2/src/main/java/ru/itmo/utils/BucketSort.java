package ru.itmo.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BucketSort {

    public static int[] sort(int[] digits) {
        if (digits == null) {
            throw new IllegalArgumentException("Массив не должен быть null");
        }
        if (digits.length == 0) {
            return new int[0];
        }

        int bucketCount = digits.length;

        @SuppressWarnings("unchecked")
        LinkedList<Integer>[] buckets = new LinkedList[bucketCount];
        for (int i = 0; i < bucketCount; i++) {
            buckets[i] = new LinkedList<>();
        }
        int maxD = Arrays.stream(digits).max().orElse(0);
        int minD = Arrays.stream(digits).min().orElse(0);

        if (minD < 0) {
            throw new IllegalArgumentException("Отрицательные числа не поддерживаются");
        }

        if (maxD == minD) {
            return Arrays.copyOf(digits, digits.length);
        }

        for (int digit : digits) {
            int index = (digit * bucketCount) / (maxD + 1);
            buckets[index].add(digit);
        }

        for (int i = 0; i < bucketCount; i++) {
            Collections.sort(buckets[i]);
        }
        return Arrays.stream(buckets)
                .flatMap(List::stream)
                .mapToInt(Integer::intValue)
                .toArray();
    }
}
