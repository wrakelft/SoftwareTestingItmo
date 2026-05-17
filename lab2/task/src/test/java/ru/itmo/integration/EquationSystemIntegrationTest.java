package ru.itmo.integration;

import org.junit.jupiter.api.Test;
import ru.itmo.function.MathFunction;
import ru.itmo.system.EquationSystemFactory;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.itmo.util.MathConstants.PI;

public class EquationSystemIntegrationTest {

    private final MathFunction system = EquationSystemFactory.create();

    @Test
    void shouldCalculateTrigBranchForNegativeArgument() {
        double result = system.apply(-1.0);

        assertTrue(Double.isFinite(result));
    }

    @Test
    void shouldCalculateLogBranchForPositiveArgument() {
        double result = system.apply(2.0);

        assertTrue(Double.isFinite(result));
    }

    @Test
    void shouldUseTrigBranchAtZero() {
        assertThrows(ArithmeticException.class, () -> system.apply(0.0));
    }

    @Test
    void shouldThrowForUndefinedTrigArgument() {
        assertThrows(ArithmeticException.class, () -> system.apply(-PI));
    }
}
