package ru.itmo.unit;

import org.junit.jupiter.api.Test;
import ru.itmo.function.MathFunction;
import ru.itmo.system.EquationSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EquationSystemBranchTest {

    @Test
    void shouldUseTrigPartWhenXIsNegative() {
        MathFunction trigPart = x -> 100.0;
        MathFunction logPart = x -> 200.0;

        EquationSystem system = new EquationSystem(trigPart, logPart);

        assertEquals(100.0, system.apply(-1.0));
    }

    @Test
    void shouldUseTrigPartWhenXIsZero() {
        MathFunction trigPart = x -> 100.0;
        MathFunction logPart = x -> 200.0;

        EquationSystem system = new EquationSystem(trigPart, logPart);

        assertEquals(100.0, system.apply(0.0));
    }

    @Test
    void shouldUseLogPartWhenXIsPositive() {
        MathFunction trigPart = x -> 100.0;
        MathFunction logPart = x -> 200.0;

        EquationSystem system = new EquationSystem(trigPart, logPart);

        assertEquals(200.0, system.apply(1.0));
    }
}