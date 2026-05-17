package ru.itmo.system;

import ru.itmo.function.MathFunction;

public class EquationSystem implements MathFunction {

    private final MathFunction trigPart;
    private final MathFunction logPart;

    public EquationSystem(MathFunction trigPart, MathFunction logPart) {
        this.trigPart = trigPart;
        this.logPart = logPart;
    }

    @Override
    public double apply(double x) {
        if (x <= 0.0) {
            return trigPart.apply(x);
        }
        return logPart.apply(x);
    }
}
