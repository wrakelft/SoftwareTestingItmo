package ru.itmo.stub;

import ru.itmo.function.MathFunction;

import java.util.HashMap;
import java.util.Map;

public final class LogStubFactory {

    public static final double X1 = 2.0;
    public static final double X2 = 5.0;
    public static final double X3 = 25.0;

    private LogStubFactory() {
    }

    public static MathFunction lnStub() {
        Map<Double, Double> values = new HashMap<>();

        values.put(X1, 0.6931471806);
        values.put(X2, 1.6094379124);
        values.put(X3, 3.2188758249);

        // Важные взаимозависимые точки:
        // BaseLogFunction вызывает ln.apply(base),
        // поэтому ln(2) и ln(5) должны быть в таблице.
        values.put(2.0, 0.6931471806);
        values.put(5.0, 1.6094379124);

        return new TableFunctionStub(values);
    }

    public static MathFunction log2Stub() {
        return new TableFunctionStub(Map.of(
                X1, 1.0,
                X2, 2.3219280949,
                X3, 4.6438561898
        ));
    }

    public static MathFunction log5Stub() {
        return new TableFunctionStub(Map.of(
                X1, 0.4306765581,
                X2, 1.0,
                X3, 2.0
        ));
    }
}