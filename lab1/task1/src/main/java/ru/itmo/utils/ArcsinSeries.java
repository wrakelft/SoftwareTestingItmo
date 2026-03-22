package ru.itmo.utils;

public class ArcsinSeries {

    private ArcsinSeries() {}

    public static double calc(double x, int n) {
        validateInput(x, n);

        if (Double.doubleToLongBits(x) == Double.doubleToLongBits(-0.0)) {
            return -0.0;
        }

        if (x == 1.0) {
            return Math.PI / 2.0;
        }
        if (x == -1.0) {
            return -Math.PI / 2.0;
        }

        if (x == 0.0) {
            return 0.0;
        }

        double term = x;
        double sum = term;

        for (int i = 0; i < n - 1; i++) {
            double prevSum = sum;
            term = term * x * x * (2.0 * i + 1.0) * (2.0 * i + 1.0) / ((2.0 * i + 2.0) * (2.0 * i + 3.0));
            sum += term;

            if (sum == prevSum) {
                break;
            }
        }
        return sum;
    }

    private static void validateInput(double x, int n) {
        if (Double.isNaN(x) || Double.isInfinite(x)) {
            throw new IllegalArgumentException("x должен быть конечным");
        }
        if (x < -1.0 || x > 1.0) {
            throw new IllegalArgumentException("x должен быть в [-1, 1]");
        }
        if (n <= 0) {
            throw new IllegalArgumentException("n должно быть больше 0");
        }
    }
}
