package ru.itmo.function.trig;

import ru.itmo.function.MathFunction;
import ru.itmo.util.MathUtils;

import static ru.itmo.util.MathConstants.EPS;

public class CotFunction implements MathFunction {

    private final MathFunction sin;
    private final MathFunction cos;

    public CotFunction(MathFunction sin, MathFunction cos) {
        this.sin = sin;
        this.cos = cos;
    }

    @Override
    public double apply(double x) {
        double sinValue = sin.apply(x);

        if (MathUtils.abs(sinValue) < EPS) {
            throw new ArithmeticException("cot(x) is undefined when sin(x) = 0");
        }

        return cos.apply(x) / sinValue;
    }
}
