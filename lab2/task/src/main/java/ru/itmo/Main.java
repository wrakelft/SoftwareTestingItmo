package ru.itmo;

import ru.itmo.csv.CsvWriter;
import ru.itmo.function.MathFunction;
import ru.itmo.function.log.BaseLogFunction;
import ru.itmo.function.log.LnFunction;
import ru.itmo.function.trig.CosFunction;
import ru.itmo.function.trig.CotFunction;
import ru.itmo.function.trig.CscFunction;
import ru.itmo.function.trig.SecFunction;
import ru.itmo.function.trig.SinFunction;
import ru.itmo.function.trig.TanFunction;
import ru.itmo.system.EquationSystem;
import ru.itmo.system.LogPartFunction;
import ru.itmo.system.TrigPartFunction;

public class Main {

    public static void main(String[] args) {
        MathFunction cos = new CosFunction();
        MathFunction sin = new SinFunction(cos);

        MathFunction tan = new TanFunction(sin, cos);
        MathFunction cot = new CotFunction(sin, cos);
        MathFunction sec = new SecFunction(cos);
        MathFunction csc = new CscFunction(sin);

        MathFunction ln = new LnFunction();
        MathFunction log2 = new BaseLogFunction(ln, 2.0);
        MathFunction log5 = new BaseLogFunction(ln, 5.0);

        MathFunction trigPart = new TrigPartFunction(sin, tan, cot, sec, csc);
        MathFunction logPart = new LogPartFunction(ln, log2, log5);
        MathFunction system = new EquationSystem(trigPart, logPart);

        CsvWriter csvWriter = new CsvWriter();

        double step = 0.01;

        csvWriter.write("build/csv/cos.csv", cos, -10.0, 10.0, step);
        csvWriter.write("build/csv/sin.csv", sin, -10.0, 10.0, step);
        csvWriter.write("build/csv/tan.csv", tan, -10.0, 10.0, step);
        csvWriter.write("build/csv/cot.csv", cot, -10.0, 10.0, step);
        csvWriter.write("build/csv/sec.csv", sec, -10.0, 10.0, step);
        csvWriter.write("build/csv/csc.csv", csc, -10.0, 10.0, step);

        csvWriter.write("build/csv/ln.csv", ln, 0.1, 10.0, step);
        csvWriter.write("build/csv/log2.csv", log2, 0.1, 10.0, step);
        csvWriter.write("build/csv/log5.csv", log5, 0.1, 10.0, step);

        csvWriter.write("build/csv/trig_part.csv", trigPart, -10.0, -0.1, step);
        csvWriter.write("build/csv/log_part.csv", logPart, 0.1, 10.0, step);
        csvWriter.write("build/csv/equation_system.csv", system, -10.0, 10.0, step);
    }
}