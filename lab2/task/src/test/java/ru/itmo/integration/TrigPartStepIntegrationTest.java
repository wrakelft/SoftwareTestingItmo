package ru.itmo.integration;

import org.junit.jupiter.api.Test;
import ru.itmo.function.MathFunction;
import ru.itmo.function.trig.CosFunction;
import ru.itmo.function.trig.CotFunction;
import ru.itmo.function.trig.CscFunction;
import ru.itmo.function.trig.SecFunction;
import ru.itmo.function.trig.SinFunction;
import ru.itmo.function.trig.TanFunction;
import ru.itmo.stub.TrigStubFactory;
import ru.itmo.system.TrigPartFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.itmo.stub.TrigStubFactory.X1;

class TrigPartStepIntegrationTest {

    private static final double EPS = 1e-4;

    /*
     * Ожидаемое значение тригонометрической части в точке x = -1.0.
     * Оно рассчитано по табличным значениям:
     * sin(-1), tan(-1), cot(-1), sec(-1), csc(-1).
     */
    private static final double EXPECTED_AT_X1 = 108.1134110807;

    @Test
    void step1ShouldCalculateTrigPartUsingOnlyStubs() {
        MathFunction sin = TrigStubFactory.sinStub();
        MathFunction tan = TrigStubFactory.tanStub();
        MathFunction cot = TrigStubFactory.cotStub();
        MathFunction sec = TrigStubFactory.secStub();
        MathFunction csc = TrigStubFactory.cscStub();

        MathFunction trigPart = new TrigPartFunction(sin, tan, cot, sec, csc);

        assertEquals(EXPECTED_AT_X1, trigPart.apply(X1), EPS);
    }

    @Test
    void step2ShouldIntegrateRealSecFunctionWithCosStub() {
        MathFunction sin = TrigStubFactory.sinStub();
        MathFunction tan = TrigStubFactory.tanStub();
        MathFunction cot = TrigStubFactory.cotStub();

        MathFunction cos = TrigStubFactory.cosStub();
        MathFunction sec = new SecFunction(cos);

        MathFunction csc = TrigStubFactory.cscStub();

        MathFunction trigPart = new TrigPartFunction(sin, tan, cot, sec, csc);

        assertEquals(EXPECTED_AT_X1, trigPart.apply(X1), EPS);
    }

    @Test
    void step3ShouldIntegrateRealCscFunctionWithSinStub() {
        MathFunction sin = TrigStubFactory.sinStub();
        MathFunction tan = TrigStubFactory.tanStub();
        MathFunction cot = TrigStubFactory.cotStub();

        MathFunction cos = TrigStubFactory.cosStub();
        MathFunction sec = new SecFunction(cos);
        MathFunction csc = new CscFunction(sin);

        MathFunction trigPart = new TrigPartFunction(sin, tan, cot, sec, csc);

        assertEquals(EXPECTED_AT_X1, trigPart.apply(X1), EPS);
    }

    @Test
    void step4ShouldIntegrateRealTanAndCotFunctionsWithSinAndCosStubs() {
        MathFunction sin = TrigStubFactory.sinStub();
        MathFunction cos = TrigStubFactory.cosStub();

        MathFunction tan = new TanFunction(sin, cos);
        MathFunction cot = new CotFunction(sin, cos);
        MathFunction sec = new SecFunction(cos);
        MathFunction csc = new CscFunction(sin);

        MathFunction trigPart = new TrigPartFunction(sin, tan, cot, sec, csc);

        assertEquals(EXPECTED_AT_X1, trigPart.apply(X1), EPS);
    }

    @Test
    void step5ShouldIntegrateRealSinAndCosFunctions() {
        MathFunction cos = new CosFunction();
        MathFunction sin = new SinFunction(cos);

        MathFunction tan = new TanFunction(sin, cos);
        MathFunction cot = new CotFunction(sin, cos);
        MathFunction sec = new SecFunction(cos);
        MathFunction csc = new CscFunction(sin);

        MathFunction trigPart = new TrigPartFunction(sin, tan, cot, sec, csc);

        assertEquals(EXPECTED_AT_X1, trigPart.apply(X1), 1e-3);
    }
}