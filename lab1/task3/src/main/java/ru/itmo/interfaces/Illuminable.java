package ru.itmo.interfaces;

import ru.itmo.enums.Direction;

public interface Illuminable {
    void turnOn();
    void turnOff();
    void shineTo(Direction direction);
    boolean canIlluminate();
}
