package com.example.demo;

public class OrganicDog extends Walkable implements Organic {

    private int hungerLevel;
    private int thirstLevel;
    private int wasteLevel;

    public OrganicDog(String name, String description, int healthLevel, int happinessLevel, int hungerLevel,
            int thirstLevel, int wasteLevel) {
        super(name, description, healthLevel, happinessLevel);
        this.hungerLevel = hungerLevel;
        this.thirstLevel = thirstLevel;
        this.wasteLevel = wasteLevel;
    }

    public int getHungerLevel() {
        return hungerLevel;
    }

    public int getThirstLevel() {
        return thirstLevel;
    }

    public int getWasteLevel() {
        return wasteLevel;
    }

    public void commandFeed() {
        hungerLevel -= 25;
        thirstLevel += 10;
        happinessLevel += 15;
    }

    public void commandWater() {
        thirstLevel -= 25;
        hungerLevel -= 5;
        happinessLevel += 10;
    }

    public void cleanDogCages() {
        wasteLevel = ensureWithinRange(wasteLevel - 50);
        happinessLevel = ensureWithinRange(happinessLevel + 5);
    }

    public void tick() {
        int wasteProduced = calculateWasteProduced();
        applyWaste(wasteProduced);

        hungerLevel = ensureWithinRange(hungerLevel + 5);
        thirstLevel = ensureWithinRange(thirstLevel + 5);
        setHappinessLevel(ensureWithinRange(getHappinessLevel() - 5));

        int beneficialProperties = happinessLevel;
        int nonBeneficialProperties = hungerLevel + thirstLevel + wasteLevel;
        int newHealthLevel = beneficialProperties - (nonBeneficialProperties / 2 + 10);
        setHealthLevel(ensureWithinRange(newHealthLevel));

        if (hungerLevel >= 80 || thirstLevel >= 80 || wasteLevel >= 80 || getHappinessLevel() <= 20) {
            setHealthLevel(ensureWithinRange(getHealthLevel() - 10));
        }
    }

    private int calculateWasteProduced() {
        // Calculate the waste produced based on hunger and thirst levels
        int wasteProduced = hungerLevel / 10 + thirstLevel / 15;
        return wasteProduced;
    }

    private void applyWaste(int wasteProduced) {
        int newWasteLevel = wasteLevel + wasteProduced;
        wasteLevel = Math.max(0, Math.min(100, newWasteLevel));
    }

    public void walkOrganicDogs() {
        super.walkOrganicDogs();
        wasteLevel -= 25;
    }
}