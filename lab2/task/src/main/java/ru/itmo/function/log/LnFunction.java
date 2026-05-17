package ru.itmo.function.log;

import ru.itmo.function.MathFunction;
import ru.itmo.util.MathConstants;
import ru.itmo.util.MathUtils;

public class LnFunction implements MathFunction {

    private final double eps;

    public LnFunction() {
        this(MathConstants.EPS);
    }

    public LnFunction(double eps) {
        if (eps < 0) {
            throw new IllegalArgumentException("eps must be a positive number");
        }
        this.eps = eps;
    }


    @Override
    public double apply(double x) {
        if (x <= 0) {
            throw new ArithmeticException("ln(x) is undefined for x <= 0");
        }

        double z = (x - 1.0) / (x + 1.0);
        double zPower = z;
        double result = 0.0;

        int denominator = 1;

        while (MathUtils.abs(zPower / denominator) > eps) {
            result += zPower / denominator;

            zPower *= z * z;
            denominator += 2;

            if (denominator > 100_000) {
                throw new ArithmeticException("ln series didn't converge");
            }
        }
        return 2.0 * result;
    }
}
