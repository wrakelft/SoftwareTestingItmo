package ru.itmo.util;

public class MathUtils {

    private MathUtils() {

    }

    public static double pow(double value, int power) {
        if (power < 0) {
            throw new IllegalArgumentException("power must be non-negative");
        }

        double result = 1.0;

        for (int i = 0; i < power; i++) {
            result *= value;
        }
        return result;
    }

    public static double abs(double value) {
        return value < 0 ? -value : value;
    }
}
