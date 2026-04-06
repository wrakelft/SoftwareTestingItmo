package ru.itmo.model;

public class Air {
    private final boolean decaySmell;
    private final int intensity;

    public Air(boolean decaySmell, int intensity) {
        if (intensity < 0) {
            throw new IllegalArgumentException("интенсивность запаха не может быть отрицательной");
        }
        this.decaySmell = decaySmell;
        this.intensity = intensity;
    }

    public boolean hasDecaySmell() {
        return decaySmell;
    }

    public int getIntensity() {
        return intensity;
    }

    public String getSmellDescription() {
        return decaySmell ? "тление" : "обычный";
    }

}
