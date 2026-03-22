package ru.itmo.model;

import java.util.Objects;

public class Tunnel extends SceneObject {
    private final Wall leftWall;
    private final Wall rightWall;
    private final Air air;

    public Tunnel(String name, Wall leftWall, Wall rightWall, Air air) {
        super(name);
        this.leftWall = Objects.requireNonNull(leftWall, "Левая стена не должна быть null");
        this.rightWall = Objects.requireNonNull(rightWall, "Правая стена не должна быть null");
        this.air = Objects.requireNonNull(air, "Воздух не должен быть null");
    }

    public Wall getLeftWall() {
        return leftWall;
    }

    public Wall getRightWall() {
        return rightWall;
    }

    public Air getAir() {
        return air;
    }

    public boolean hasColdWalls() {
        return leftWall.isCold() || rightWall.isCold();
    }
}