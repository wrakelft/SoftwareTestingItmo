package ru.itmo.function.trig;

import ru.itmo.function.MathFunction;
import ru.itmo.util.MathUtils;

import static ru.itmo.util.MathConstants.EPS;

public class TanFunction implements MathFunction {

    private final MathFunction sin;
    private final MathFunction cos;

    public TanFunction(MathFunction sin, MathFunction cos) {
        this.sin = sin;
        this.cos = cos;
    }

    @Override
    public double apply(double x) {
        double cosValue = cos.apply(x);

        if (MathUtils.abs(cosValue) <  EPS) {
            throw new ArithmeticException("tan(x) is undefine when cos(x) = 0");
        }

        return sin.apply(x) / cosValue;
    }
}
