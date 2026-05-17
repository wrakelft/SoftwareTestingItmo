package ru.itmo.stub;

import ru.itmo.function.MathFunction;

import java.util.Map;

public class TableFunctionStub implements MathFunction {

    private final Map<Double, Double> values;

    public TableFunctionStub(Map<Double, Double> values) {
        this.values = values;
    }

    @Override
    public double apply(double x) {
        Double value = values.get(x);

        if (value == null) {
            throw new IllegalArgumentException("No stub value for x = " + x);
        }
        return value;
    }
}
