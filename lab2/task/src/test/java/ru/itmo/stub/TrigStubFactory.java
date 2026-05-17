package ru.itmo.stub;

import ru.itmo.function.MathFunction;

import java.util.Map;

public final class TrigStubFactory {

    public static final double X1 = -1.0;
    public static final double X2 = -2.0;
    public static final double X3 = -4.5;

    private TrigStubFactory() {
    }

    public static MathFunction cosStub() {
        return new TableFunctionStub(Map.of(
                X1, 0.5403023059,
                X2, -0.4161468365,
                X3, -0.2107957994
        ));
    }

    public static MathFunction sinStub() {
        return new TableFunctionStub(Map.of(
                X1, -0.8414709848,
                X2, -0.9092974268,
                X3, 0.9775301177
        ));
    }

    public static MathFunction tanStub() {
        return new TableFunctionStub(Map.of(
                X1, -1.5574077247,
                X2, 2.1850398633,
                X3, -4.6373320546
        ));
    }

    public static MathFunction cotStub() {
        return new TableFunctionStub(Map.of(
                X1, -0.6420926159,
                X2, 0.4576575544,
                X3, -0.2156415029
        ));
    }

    public static MathFunction secStub() {
        return new TableFunctionStub(Map.of(
                X1, 1.8508157177,
                X2, -2.4029979617,
                X3, -4.7439275484
        ));
    }

    public static MathFunction cscStub() {
        return new TableFunctionStub(Map.of(
                X1, -1.1883951058,
                X2, -1.0997501703,
                X3, 1.0229879368
        ));
    }
}