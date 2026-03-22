package ru.itmo;

import ru.itmo.utils.ArcsinSeries;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            printUsage();
            return;
        }

        try {
            double x = Double.parseDouble(args[0]);
            int n = args.length >= 2 ? Integer.parseInt(args[1]) : Integer.MAX_VALUE;

            double result = ArcsinSeries.calc(x, n);
            System.out.printf("arcsin(%.6f) = %.12f (n = %d)%n", x, result, n);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка ввода: x должно быть вещественным числом, n - целым числом");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void printUsage() {
        System.out.println("Нужно ввести <x> [n]");
        System.out.println("<x> — аргумент функции arcsin, должен быть в диапазоне [-1, 1]");
        System.out.println("[n] — количество членов ряда, необязательный параметр");
    }
}