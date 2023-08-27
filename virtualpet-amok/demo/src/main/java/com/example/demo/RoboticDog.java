package com.example.demo;

public class RoboticDog extends Walkable implements Robotic {

    private int maintenanceLevel;

    public RoboticDog(String name, String description, int healthLevel, int happinessLevel, int maintenanceLevel) {
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
        // Debug Testing
        // System.out.println("Tick() called for: " + getName());

        maintenanceLevel = ensureWithinRange(maintenanceLevel + 5);
        // System.out.println("Maintenance level increased to: " + maintenanceLevel);

        setHappinessLevel(ensureWithinRange(getHappinessLevel() - 2));
        // System.out.println("Happiness level decreased to: " + getHappinessLevel());

        // Calculate healthLevel based on pet wellness attributes
        int newHealthLevel = 50 - maintenanceLevel + happinessLevel;
        // System.out.println("Calculated new health level: " + newHealthLevel);
        newHealthLevel = ensureWithinRange(newHealthLevel);

        if (maintenanceLevel >= 100 || getHappinessLevel() <= 0) {
            newHealthLevel = ensureWithinRange(newHealthLevel - 10);
        }

        setHealthLevel(newHealthLevel);
        // System.out.println("Final health level set to: " + getHealthLevel());
    }
}