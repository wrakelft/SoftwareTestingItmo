package ru.itmo.model;

import ru.itmo.enums.Direction;
import ru.itmo.interfaces.Illuminable;

public class Flashlight extends SceneObject implements Illuminable {
    private boolean isOn;
    private int batteryLevel;
    private Direction beamDirection;

    public Flashlight(String name, int batteryLevel) {
        super(name);
        if (batteryLevel < 0 || batteryLevel > 100) {
            throw new IllegalArgumentException("Уровень батаери должен быть от 0 до 100");
        }
        this.batteryLevel = batteryLevel;
        this.isOn = false;
        this.beamDirection = Direction.Прямо;
    }

    @Override
    public void turnOn() {
        if (batteryLevel > 0) {
            isOn = true;
        }
    }

    @Override
    public void turnOff() {
        isOn = false;
    }

    @Override
    public void shineTo(Direction direction) {
        if (!canIlluminate()) {
            throw new IllegalStateException("Фонар не может светить");
        }
        this.beamDirection = direction;
        batteryLevel--;
        if (batteryLevel == 0) {
            isOn = false;
        }
    }

    @Override
    public boolean canIlluminate() {
        return isOn && batteryLevel > 0;
    }

    public boolean isOn() {
        return isOn;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public Direction getBeamDirection() {
        return beamDirection;
    }
}
