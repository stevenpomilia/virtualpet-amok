package com.example.demo;

public class OrganicCat extends VirtualPet implements Organic {

    private int hungerLevel;
    private int thirstLevel;
    private VirtualPetShelter shelter;

    public OrganicCat(String name, String description, int healthLevel, int happinessLevel, int hungerLevel,
            int thirstLevel, VirtualPetShelter shelter) {
        super(name, description, healthLevel, happinessLevel);
        this.hungerLevel = hungerLevel;
        this.thirstLevel = thirstLevel;
        this.shelter = shelter;
    }

    public int getHungerLevel() {
        return hungerLevel;
    }

    public int getThirstLevel() {
        return thirstLevel;
    }

    public void commandFeed() {
        hungerLevel = ensureWithinRange(hungerLevel - 25);
        thirstLevel = ensureWithinRange(thirstLevel + 5);
        happinessLevel = ensureWithinRange(happinessLevel + 15);
    }

    public void commandWater() {
        thirstLevel -= 25;
        hungerLevel -= 5;
        happinessLevel += 10;
    }

    public void tick() {
        System.out.println("Tick() called for: " + getName());

        int wasteProduced = calculateWasteProduced();
        System.out.println("Waste produced: " + wasteProduced);
        applyWaste(wasteProduced);

        hungerLevel = ensureWithinRange(hungerLevel + 5);
        System.out.println("Hunger level increased to: " + hungerLevel);
        thirstLevel = ensureWithinRange(thirstLevel + 5);
        System.out.println("Thirst level increased to: " + thirstLevel);
        setHappinessLevel(ensureWithinRange(getHappinessLevel() - 5));
        System.out.println("Happiness level decreased to: " + getHappinessLevel());

        int beneficialProperties = getHappinessLevel();
        int nonBeneficialProperties = hungerLevel + thirstLevel + shelter.getLitterBoxWasteLevel();
        System.out.println("Beneficial properties: " + beneficialProperties);
        System.out.println("Non-beneficial properties: " + nonBeneficialProperties);

        int newHealthLevel = beneficialProperties - (nonBeneficialProperties / 2 + 10);
        System.out.println("Calculated new health level: " + newHealthLevel);
        newHealthLevel = ensureWithinRange(newHealthLevel);

        if (hungerLevel >= 80 || thirstLevel >= 80 || getHappinessLevel() <= 20) {
            newHealthLevel -= 10;
        }

        if (shelter.getLitterBoxWasteLevel() >= 80) {
            newHealthLevel -= 10;
        }

        setHealthLevel(newHealthLevel);
        System.out.println("Final health level set to: " + getHealthLevel());
    }

    private int calculateWasteProduced() {
        // Calculate the waste produced based on hunger and thirst levels
        int wasteProduced = hungerLevel / 15 + thirstLevel / 20;
        return wasteProduced;
    }

    private void applyWaste(int wasteProduced) {
        shelter.setLitterBoxWasteLevel(shelter.getLitterBoxWasteLevel() + wasteProduced);
    }

    public void adjustHappinessAfterCleaningLitterBox() {
        happinessLevel += 20;
        // Ensure happiness level stays within the range
        happinessLevel = ensureWithinRange(happinessLevel);
    }

}