package ru.itmo.csv;

import ru.itmo.function.MathFunction;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CsvWriter {

    public void write(
            String filePath,
            MathFunction function,
            double from,
            double to,
            double step
    ) {
        if (step <= 0) {
            throw new IllegalArgumentException("step must be positive");
        }

        File file = new File(filePath);
        File parent = file.getParentFile();

        if (parent != null && !parent.exists()) {
            boolean created = parent.mkdirs();

            if (!created) {
                throw new RuntimeException("Failed to create directory: " + parent);
            }
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            writer.println("X,Result");

            for (double x = from; x <= to; x += step) {
                try {
                    double result = function.apply(x);

                    if (Double.isFinite(result)) {
                        writer.println(x + "," + result);
                    } else {
                        writer.println(x + ",undefined");
                    }
                } catch (ArithmeticException | IllegalArgumentException exception) {
                    writer.println(x + ",undefined");
                }
            }
        } catch (IOException exception) {
            throw new RuntimeException("Failed to write CSV file: " + filePath, exception);
        }
    }
}