package ru.itmo.function.trig;

import ru.itmo.function.MathFunction;
import ru.itmo.util.MathUtils;

import static ru.itmo.util.MathConstants.EPS;

public class SecFunction implements MathFunction {

    private final MathFunction cos;

    public SecFunction(MathFunction cos) {
        this.cos = cos;
    }

    @Override
    public double apply(double x) {
        double cosValue = cos.apply(x);

        if (MathUtils.abs(cosValue) < EPS) {
            throw new ArithmeticException("sec(x) is undefined when cos(x) = 0");
        }

        return 1.0 / cosValue;
    }
}