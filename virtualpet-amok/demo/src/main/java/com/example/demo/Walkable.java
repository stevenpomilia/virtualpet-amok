package com.example.demo;

public abstract class Walkable extends VirtualPet {

    public Walkable(String name, String description, int healthLevel, int happinessLevel) {
        super(name, description, healthLevel, happinessLevel);
    }

    public void walkOrganicDogs() {
        setHappinessLevel(getHappinessLevel() + 25);
    }

}
