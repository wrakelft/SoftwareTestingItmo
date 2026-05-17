package ru.itmo.unit;

import org.junit.jupiter.api.Test;
import ru.itmo.csv.CsvWriter;
import ru.itmo.function.MathFunction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CsvWriterTest {

    private final CsvWriter csvWriter = new CsvWriter();

    @Test
    void shouldWriteFunctionValuesToCsv() throws IOException {
        Path filePath = Path.of("build/test-csv/simple.csv");

        MathFunction function = x -> x * x;

        csvWriter.write(filePath.toString(), function, 0.0, 0.2, 0.1);

        List<String> lines = Files.readAllLines(filePath);

        assertEquals("X,Result", lines.get(0));
        assertEquals("0.0,0.0", lines.get(1));
        assertEquals("0.1,0.010000000000000002", lines.get(2));
        assertEquals("0.2,0.04000000000000001", lines.get(3));
    }

    @Test
    void shouldWriteUndefinedWhenFunctionThrowsException() throws IOException {
        Path filePath = Path.of("build/test-csv/undefined.csv");

        MathFunction function = x -> {
            if (x == 0.0) {
                throw new ArithmeticException("undefined");
            }

            return x;
        };

        csvWriter.write(filePath.toString(), function, -0.1, 0.1, 0.1);

        List<String> lines = Files.readAllLines(filePath);

        assertTrue(lines.contains("0.0,undefined"));
    }

    @Test
    void shouldThrowWhenStepIsNotPositive() {
        MathFunction function = x -> x;

        org.junit.jupiter.api.Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> csvWriter.write("build/test-csv/bad.csv", function, 0.0, 1.0, 0.0)
        );
    }

    @Test
    void shouldWriteUndefinedWhenFunctionReturnsNaNOrInfinity() throws IOException {
        Path filePath = Path.of("build/test-csv/not-finite.csv");

        MathFunction function = x -> {
            if (x == 0.0) {
                return Double.NaN;
            }

            return Double.POSITIVE_INFINITY;
        };

        csvWriter.write(filePath.toString(), function, 0.0, 0.1, 0.1);

        List<String> lines = Files.readAllLines(filePath);

        assertEquals("X,Result", lines.get(0));
        assertEquals("0.0,undefined", lines.get(1));
        assertEquals("0.1,undefined", lines.get(2));
    }

    @Test
    void shouldWriteCsvWhenFileHasNoParentDirectory() throws IOException {
        Path filePath = Path.of("simple-test.csv");

        MathFunction function = x -> x;

        csvWriter.write(filePath.toString(), function, 1.0, 1.0, 0.1);

        List<String> lines = Files.readAllLines(filePath);

        assertEquals("X,Result", lines.get(0));
        assertEquals("1.0,1.0", lines.get(1));

        Files.deleteIfExists(filePath);
    }

    @Test
    void shouldThrowRuntimeExceptionWhenFileCannotBeWritten() throws IOException {
        Path directoryPath = Path.of("build/test-csv-directory");

        Files.createDirectories(directoryPath);

        MathFunction function = x -> x;

        RuntimeException exception = org.junit.jupiter.api.Assertions.assertThrows(
                RuntimeException.class,
                () -> csvWriter.write(directoryPath.toString(), function, 0.0, 1.0, 0.1)
        );

        assertTrue(exception.getMessage().contains("Failed to write CSV file"));
    }
}