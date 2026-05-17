package ru.itmo.function.trig;

import ru.itmo.function.MathFunction;
import ru.itmo.util.MathConstants;
import ru.itmo.util.MathUtils;

public class CosFunction implements MathFunction {

    private final double eps;

    public CosFunction() {
        this(MathConstants.EPS);
    }

    public CosFunction(double eps) {
        if (eps <= 0) {
            throw new IllegalArgumentException("eps must be positive");
        }
        this.eps = eps;
    }

    @Override
    public double apply(double x) {
        double normalizedX = normalize(x);

        double result = 1.0;
        double term = 1.0;

        int n = 1;

        while (MathUtils.abs(term) > eps) {
            term *= -normalizedX * normalizedX / ((2.0 * n - 1.0) * (2.0 * n));
            result += term;
            n++;

            if (n > 1000) {
                throw new ArithmeticException("Cos series didn't converge");
            }
        }
        return result;
    }

    private double normalize(double x) {
        while (x > MathConstants.PI) {
            x -= MathConstants.TWO_PI;
        }

        while (x < -MathConstants.PI) {
            x += MathConstants.TWO_PI;
        }
        return x;
    }
}
