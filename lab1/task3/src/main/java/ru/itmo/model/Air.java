package ru.itmo.model;

import ru.itmo.enums.SmellType;

public class Air {
    private final SmellType smellType;
    private final int intensity;

    public Air(SmellType smellType, int intensity) {
        if (intensity < 0) {
            throw new IllegalArgumentException("интенсивность запаха не может быть отрицательной");
        }
        this.smellType = smellType;
        this.intensity = intensity;
    }

    public SmellType getSmellType() {
        return smellType;
    }

    public int getIntensity() {
        return intensity;
    }

    public boolean hasDecaySmell() {
        return smellType == SmellType.Тления;
    }
}
