package ru.itmo.function.trig;

import ru.itmo.function.MathFunction;

import static ru.itmo.util.MathConstants.PI;

public class SinFunction implements MathFunction {

    private final  MathFunction cos;

    public SinFunction(MathFunction cos) {
        this.cos = cos;
    }

    @Override
    public double apply(double x) {
        return cos.apply(PI / 2.0 - x);
    }
}
