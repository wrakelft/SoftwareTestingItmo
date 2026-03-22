package ru.itmo.model;

import ru.itmo.enums.*;

import java.util.Objects;

public class Character extends SceneObject {
    private EmotionalState emotionalState;
    private DisplayedState displayedState;
    private MovementSpeed movementSpeed;
    private Direction currentDirection;
    private final Flashlight flashlight;
    private final Tunnel currentTunnel;
    private int stepsTaken;

    public Character(
            String name,
            EmotionalState emotionalState,
            DisplayedState displayedState,
            MovementSpeed movementSpeed,
            Flashlight flashlight,
            Tunnel currentTunnel
    ) {
        super(name);
        this.emotionalState = Objects.requireNonNull(emotionalState, "Эмоциональное состояние не должно быть null");
        this.displayedState = Objects.requireNonNull(displayedState, "Внешнее состояние не должно быть null");
        this.movementSpeed = Objects.requireNonNull(movementSpeed, "Скорость движения не должна быть null");
        this.flashlight = Objects.requireNonNull(flashlight, "Фонарь не должен быть null");
        this.currentTunnel = Objects.requireNonNull(currentTunnel, "Тоннель не должен быть null");
        this.currentDirection = Direction.Прямо;
        this.stepsTaken = 0;
    }

    public void moveForward() {
        currentDirection = Direction.Прямо;
        stepsTaken++;
    }

    public void setEmotionalState(EmotionalState emotionalState) {
        this.emotionalState = Objects.requireNonNull(emotionalState, "Эмоциональное состояние не должно быть null");
    }

    public void maskEmotional(DisplayedState displayedState) {
        this.displayedState = Objects.requireNonNull(displayedState, "Внешнее состояние не должно быть null");
    }

    public void shineFleshLight(Direction direction) {
        flashlight.turnOn();
        flashlight.shineTo(direction);
    }

    public TemperatureLevel touchLeftWall() {
        return currentTunnel.getLeftWall().feelTemperature();
    }

    public TemperatureLevel touchRightWall() {
        return currentTunnel.getRightWall().feelTemperature();
    }

    public SmellType smellAir() {
        return currentTunnel.getAir().getSmellType();
    }

    public boolean isNervous() {
        return emotionalState == EmotionalState.Нервно;
    }

    public EmotionalState getEmotionalState() {
        return emotionalState;
    }

    public DisplayedState getDisplayedState() {
        return displayedState;
    }

    public MovementSpeed getMovementSpeed() {
        return movementSpeed;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public Flashlight getFlashlight() {
        return flashlight;
    }

    public Tunnel getCurrentTunnel() {
        return currentTunnel;
    }

    public int getStepsTaken() {
        return stepsTaken;
    }
}