package com.example.demo;

import java.util.*;

public class VirtualPetShelter {

    private int shelterLitterBoxWasteLevel = 10;

    Map<String, VirtualPet> petsInShelter = new HashMap<>();

    public Collection<VirtualPet> availablePets() {
        return petsInShelter.values();
    }

    public VirtualPet getPet(UUID petId) {
        for (VirtualPet pet : petsInShelter.values()) {
            if (pet.getPetById().equals(petId)) {
                return pet;
            }
        }
        return null;
    }

    public Map<String, VirtualPet> getPetsInShelter() {
        return petsInShelter;
    }

    public boolean arePetsAvailable() {
        return petsInShelter.isEmpty();
    }

    public void addPet(VirtualPet virtualPet) {
        String uniqueKey = virtualPet.getName() + "-" + virtualPet.getPetType();
        petsInShelter.put(uniqueKey, virtualPet);
    }

    public void removePet(UUID petId) {
        VirtualPet removedPet = petsInShelter.remove(petId.toString());
        if (removedPet != null) {
            System.out.println("Adopted " + removedPet.getName() + " from the shelter.");
        } else {
            System.out.println("Pet with the given UUID not found.");
        }
    }

    public void shelterTick() {
        for (VirtualPet availablePets : availablePets()) {
            availablePets.tick();
        }
    }

    public void feedAllOrganics() {
        for (VirtualPet availablePets : availablePets()) {
            if (availablePets instanceof Organic) {
                Organic organicPet = (Organic) availablePets;
                organicPet.commandFeed();
            }
        }
    }

    public void waterAllOrganics() {
        for (VirtualPet availablePets : availablePets()) {
            if (availablePets instanceof Organic) {
                Organic organicPet = (Organic) availablePets;
                organicPet.commandWater();
            }
        }
    }

    public void maintainRoboticPets() {
        for (VirtualPet availablePets : availablePets()) {
            if (availablePets instanceof Robotic) {
                Robotic roboticPet = (Robotic) availablePets;
                roboticPet.commandOil();
            }
        }
    }

    public void cleanAllDogCages() {
        for (VirtualPet availablePets : availablePets()) {
            if (availablePets instanceof OrganicDog) {
                OrganicDog organicDog = (OrganicDog) availablePets;
                organicDog.cleanDogCages();
            }
        }
    }

    public void walkOrganicDogs() {
        for (VirtualPet availablePets : availablePets()) {
            if (availablePets instanceof Walkable) {
                Walkable walkableDog = (Walkable) availablePets;
                walkableDog.walkOrganicDogs();
            }
        }
    }

    public void setLitterBoxWasteLevel(int litterBoxWasteLevel) {
        this.shelterLitterBoxWasteLevel = litterBoxWasteLevel;
    }

    public void cleanTheLitterBox() {
        shelterLitterBoxWasteLevel = Math.max(0, shelterLitterBoxWasteLevel - 70);

        for (VirtualPet availablePets : availablePets()) {
            if (availablePets instanceof OrganicCat) {
                OrganicCat organicCat = (OrganicCat) availablePets;
                organicCat.adjustHappinessAfterCleaningLitterBox();
            }
        }
    }

    public int getLitterBoxWasteLevel() {
        return shelterLitterBoxWasteLevel;
    }

}
