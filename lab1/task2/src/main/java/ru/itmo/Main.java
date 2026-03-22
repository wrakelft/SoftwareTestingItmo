package ru.itmo;

import ru.itmo.utils.BucketSort;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] digits = {786, 50, 749, 136, 123, 929, 662, 459, 332, 155, 499, 915, 882, 63, 210, 929, 778, 109, 701, 442, 427, 280, 672, 847, 177, 271, 406, 21, 704, 240};
        int[] sorted = BucketSort.sort(digits);
        System.out.println("Исходный массив: " + Arrays.toString(digits));
        System.out.println("Отсортированный массив: " + Arrays.toString(sorted));
    }
}
