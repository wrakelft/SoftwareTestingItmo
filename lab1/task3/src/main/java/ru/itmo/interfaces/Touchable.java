package ru.itmo.interfaces;

import ru.itmo.enums.TemperatureLevel;

public interface Touchable {
    TemperatureLevel feelTemperature();
    boolean isTouched();
}
