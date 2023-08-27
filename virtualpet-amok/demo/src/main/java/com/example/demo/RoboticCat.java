package com.example.demo;

public class RoboticCat extends VirtualPet implements Robotic {
    private int maintenanceLevel;

    public RoboticCat(String name, String description, int healthLevel, int happinessLevel, int maintenanceLevel) {
        super(name, description, healthLevel, happinessLevel);
        this.maintenanceLevel = maintenanceLevel;
    }

    public int getMaintenanceLevel() {
        return maintenanceLevel;
    }

    @Override
    public void commandOil() {
        maintenanceLevel = ensureWithinRange(maintenanceLevel - 25);
        happinessLevel = ensureWithinRange(happinessLevel + 20);
    }

    public void tick() {
        maintenanceLevel = ensureWithinRange(maintenanceLevel + 5);
        setHappinessLevel(ensureWithinRange(getHappinessLevel() - 4));

        // Calculate healthLevel based on pet wellness attributes
        int newHealthLevel = 50 - maintenanceLevel + happinessLevel;
        newHealthLevel = ensureWithinRange(newHealthLevel);

        if (maintenanceLevel >= 100 || getHappinessLevel() <= 0) {
            newHealthLevel = ensureWithinRange(newHealthLevel - 10);
        }

        setHealthLevel(newHealthLevel);
    }
}
