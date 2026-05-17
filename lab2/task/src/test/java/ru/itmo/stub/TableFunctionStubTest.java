package ru.itmo.stub;

import org.junit.jupiter.api.Test;
import ru.itmo.function.MathFunction;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TableFunctionStubTest {

    @Test
    void shouldReturnValueFromTable() {
        MathFunction stub = new TableFunctionStub(Map.of(
                -1.0, 10.5,
                2.0, -3.25
        ));

        assertEquals(10.5, stub.apply(-1.0));
        assertEquals(-3.25, stub.apply(2.0));
    }

    @Test
    void shouldThrowWhenValueIsMissing() {
        MathFunction stub = new TableFunctionStub(Map.of(
                -1.0, 10.5
        ));

        assertThrows(IllegalArgumentException.class, () -> stub.apply(2.0));
    }
}