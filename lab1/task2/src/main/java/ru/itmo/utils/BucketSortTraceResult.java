package ru.itmo.utils;

import java.util.List;

public class BucketSortTraceResult {
    private final int[] sortedArray;
    private final List<String> trace;

    public BucketSortTraceResult(int[] sortedArray, List<String> trace) {
        this.sortedArray = sortedArray;
        this.trace = trace;
    }

    public int[] getSortedArray() {
        return sortedArray;
    }

    public List<String> getTrace() {
        return trace;
    }
}
