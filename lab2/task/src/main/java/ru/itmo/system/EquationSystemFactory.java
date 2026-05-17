package ru.itmo.system;

import ru.itmo.function.MathFunction;
import ru.itmo.function.log.BaseLogFunction;
import ru.itmo.function.log.LnFunction;
import ru.itmo.function.trig.*;

public final class EquationSystemFactory {

    private EquationSystemFactory() {

    }

    public static MathFunction create() {
        MathFunction cos = new CosFunction();
        MathFunction sin = new SinFunction(cos);

        MathFunction tan = new TanFunction(sin, cos);
        MathFunction cot = new CotFunction(sin, cos);
        MathFunction sec = new SecFunction(cos);
        MathFunction csc = new CscFunction(sin);

        MathFunction trigPart = new TrigPartFunction(sin, tan, cot, sec, csc);

        MathFunction ln = new LnFunction();
        MathFunction log2 = new BaseLogFunction(ln, 2.0);
        MathFunction log5 = new BaseLogFunction(ln, 5.0);

        MathFunction logPart = new LogPartFunction(ln, log2, log5);

        return new EquationSystem(trigPart, logPart);
    }
}
