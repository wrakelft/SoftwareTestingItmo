package ru.itmo.function.trig;

import ru.itmo.function.MathFunction;
import ru.itmo.util.MathUtils;

import static ru.itmo.util.MathConstants.EPS;

public class CscFunction implements MathFunction {

    private final MathFunction sin;

    public CscFunction(MathFunction sin) {
        this.sin = sin;
    }

    @Override
    public double apply(double x) {
        double sinValue = sin.apply(x);

        if (MathUtils.abs(sinValue) < EPS) {
            throw new ArithmeticException("csc(x) is undefined when sin(x) = 0");
        }

        return 1.0 / sinValue;
    }
}