package ru.itmo.integration;

import org.junit.jupiter.api.Test;
import ru.itmo.function.MathFunction;
import ru.itmo.function.log.BaseLogFunction;
import ru.itmo.function.log.LnFunction;
import ru.itmo.stub.LogStubFactory;
import ru.itmo.system.LogPartFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.itmo.stub.LogStubFactory.X1;

class LogPartStepIntegrationTest {

    private static final double EPS = 1e-5;

    /*
     * Ожидаемое значение логарифмической части в точке x = 2.0.
     *
     * ln(2)   = 0.6931471806
     * log2(2) = 1.0
     * log5(2) = 0.4306765581
     *
     * Формула:
     * (((ln(x) * log5(x))^3)^2)^3 - log2(x) * log5(x)
     */
    private static final double EXPECTED_AT_X1 = -0.4306765550;

    @Test
    void step1ShouldCalculateLogPartUsingOnlyStubs() {
        MathFunction ln = LogStubFactory.lnStub();
        MathFunction log2 = LogStubFactory.log2Stub();
        MathFunction log5 = LogStubFactory.log5Stub();

        MathFunction logPart = new LogPartFunction(ln, log2, log5);

        assertEquals(EXPECTED_AT_X1, logPart.apply(X1), EPS);
    }

    @Test
    void step2ShouldIntegrateRealLog2FunctionWithLnStub() {
        MathFunction ln = LogStubFactory.lnStub();

        MathFunction log2 = new BaseLogFunction(ln, 2.0);
        MathFunction log5 = LogStubFactory.log5Stub();

        MathFunction logPart = new LogPartFunction(ln, log2, log5);

        assertEquals(EXPECTED_AT_X1, logPart.apply(X1), EPS);
    }

    @Test
    void step3ShouldIntegrateRealLog5FunctionWithLnStub() {
        MathFunction ln = LogStubFactory.lnStub();

        MathFunction log2 = new BaseLogFunction(ln, 2.0);
        MathFunction log5 = new BaseLogFunction(ln, 5.0);

        MathFunction logPart = new LogPartFunction(ln, log2, log5);

        assertEquals(EXPECTED_AT_X1, logPart.apply(X1), EPS);
    }

    @Test
    void step4ShouldIntegrateRealLnFunction() {
        MathFunction ln = new LnFunction();

        MathFunction log2 = new BaseLogFunction(ln, 2.0);
        MathFunction log5 = new BaseLogFunction(ln, 5.0);

        MathFunction logPart = new LogPartFunction(ln, log2, log5);

        assertEquals(EXPECTED_AT_X1, logPart.apply(X1), 1e-4);
    }
}