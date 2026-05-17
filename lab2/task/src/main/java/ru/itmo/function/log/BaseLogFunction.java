package ru.itmo.function.log;

import ru.itmo.function.MathFunction;

public class BaseLogFunction implements MathFunction {

    private final MathFunction ln;
    private final double base;

    public BaseLogFunction(MathFunction ln, double base) {
        if (base <= 0 || base == 1.0) {
            throw new IllegalArgumentException("log base must be positive and not equal to 1");
        }

        this.ln = ln;
        this.base = base;
    }

    @Override
    public double apply(double x) {
        return ln.apply(x) / ln.apply(base);
    }
}
