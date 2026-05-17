package ru.itmo.system;

import ru.itmo.function.MathFunction;
import ru.itmo.util.MathUtils;

import static ru.itmo.util.MathConstants.EPS;
import static ru.itmo.util.MathUtils.pow;

public class TrigPartFunction implements MathFunction {

    private final MathFunction sin;
    private final MathFunction tan;
    private final MathFunction cot;
    private final MathFunction sec;
    private final MathFunction csc;

    public TrigPartFunction(
            MathFunction sin,
            MathFunction tan,
            MathFunction cot,
            MathFunction sec,
            MathFunction csc
    ) {
        this.sin = sin;
        this.tan = tan;
        this.cot = cot;
        this.sec = sec;
        this.csc = csc;
    }

    @Override
    public double apply(double x) {
        double sinVal = sin.apply(x);
        double tanVal = tan.apply(x);
        double cotVal = cot.apply(x);
        double secVal = sec.apply(x);
        double cscVal = csc.apply(x);

        if (MathUtils.abs(tanVal) < EPS) {
            throw new ArithmeticException("tan(x) / tan (x) is undefined when tan(x) = 0");
        }

        double tanDivTan = tanVal / tanVal;

        return pow(pow(tanVal * secVal, 2) + secVal - (sinVal - cotVal),2) + (secVal / tanDivTan)
                - (tanVal - cscVal) - pow(cscVal, 2);
    }
}
