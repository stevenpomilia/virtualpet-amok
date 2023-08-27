package com.example.demo;

import java.util.UUID;

abstract class VirtualPet {

    private UUID petId;
    private String name;
    private String description;
    private int healthLevel;
    protected int happinessLevel;

    public VirtualPet(String name, String description, int healthLevel, int happinessLevel) {
        this.petId = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.healthLevel = healthLevel;
        this.happinessLevel = happinessLevel;
    }

    public UUID getPetById() {
        return petId;
    }

    public int ensureWithinRange(int value) {
        return Math.max(1, Math.min(100, value));
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getHealthLevel() {
        return healthLevel;
    }

    public int getHappinessLevel() {
        return happinessLevel;
    }

    public void setHappinessLevel(int happinessLevel) {
        this.happinessLevel = happinessLevel;
    }

    public void setHealthLevel(int healthLevel) {
        this.healthLevel = healthLevel;
    }

    void commandPlay() {
        setHappinessLevel(getHappinessLevel() + 25);
    }

    public void tick() {
    }

    public String getPetType() {
        String className = getClass().getSimpleName();
        return className;
    }
}
