package ru.itmo.model;

import ru.itmo.enums.TemperatureLevel;
import ru.itmo.enums.TileColor;
import ru.itmo.interfaces.Touchable;

public class Wall extends SceneObject implements Touchable {
    private final TemperatureLevel temperatureLevel;
    private final TileColor tileColor;
    private boolean isTouched;

    public Wall(String name, TemperatureLevel temperatureLevel, TileColor tileColor) {
        super(name);
        this.temperatureLevel = temperatureLevel;
        this.tileColor = tileColor;
    }

    @Override
    public TemperatureLevel feelTemperature() {
        isTouched = true;
        return temperatureLevel;
    }

    public TileColor getTileColor() {
        return tileColor;
    }

    public boolean isCold() {
        return temperatureLevel == TemperatureLevel.Холод;
    }

    @Override
    public boolean isTouched() {
        return isTouched;
    }
}
