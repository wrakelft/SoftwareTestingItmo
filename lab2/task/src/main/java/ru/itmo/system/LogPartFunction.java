package ru.itmo.system;

import ru.itmo.function.MathFunction;

import static ru.itmo.util.MathUtils.pow;

public class LogPartFunction implements MathFunction {

    private final MathFunction ln;
    private final MathFunction log2;
    private final MathFunction log5;

    public LogPartFunction(MathFunction ln, MathFunction log2, MathFunction log5) {
        this.ln = ln;
        this.log2 = log2;
        this.log5 = log5;
    }

    @Override
    public double apply(double x) {
        double lnVal = ln.apply(x);
        double log2Val = log2.apply(x);
        double log5Val = log5.apply(x);

        return pow(pow(pow(lnVal * log5Val, 3), 2), 3) - (log2Val * log5Val);
    }
}
