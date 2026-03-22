package ru.itmo;

import ru.itmo.enums.*;
import ru.itmo.model.Air;
import ru.itmo.model.Character;
import ru.itmo.model.Flashlight;
import ru.itmo.model.Tunnel;
import ru.itmo.model.Wall;

public class Main {
    public static void main(String[] args) {
        Wall leftWall = new Wall("Левая стена", TemperatureLevel.Холод, TileColor.Темная);
        Wall rightWall = new Wall("Правая стена", TemperatureLevel.Холод, TileColor.Темная);
        Air air = new Air(SmellType.Тления, 8);
        Tunnel tunnel = new Tunnel("Темный тоннель", leftWall, rightWall, air);
        Flashlight flashlight = new Flashlight("Фонарь Зафода", 10);

        Character zaphod = new Character(
                "Зафод",
                EmotionalState.Нервно,
                DisplayedState.Целеустремленный,
                MovementSpeed.Быстро,
                flashlight,
                tunnel
        );

        zaphod.moveForward();
        zaphod.shineFleshLight(Direction.налево);
        zaphod.shineFleshLight(Direction.направо);

        System.out.println("Персонаж: " + zaphod.getName());
        System.out.println("Шагов сделано: " + zaphod.getStepsTaken());
        System.out.println("Эмоциональное состояние: " + zaphod.getEmotionalState());
        System.out.println("Внешнее состояние: " + zaphod.getDisplayedState());
        System.out.println("Температура левой стены: " + zaphod.touchLeftWall());
        System.out.println("Температура правой стены: " + zaphod.touchRightWall());
        System.out.println("Запах в воздухе: " + zaphod.smellAir());
        System.out.println("Фонарь включен: " + zaphod.getFlashlight().isOn());
        System.out.println("Заряд фонаря: " + zaphod.getFlashlight().getBatteryLevel());
    }
}