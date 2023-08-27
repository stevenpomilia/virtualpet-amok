package com.example.demo;

import java.util.Scanner;
import java.util.UUID;

public class VirtualPetAmokApp {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		VirtualPetShelter shelter = new VirtualPetShelter();

		VirtualPet scout = new OrganicDog("Scout", "Mini Australian Shepherd", 50, 100, 50, 30, 40);
		shelter.addPet(scout);
		VirtualPet sparky = new RoboticDog("Sparky", "Supersonic Pup", 50, 50, 25);
		shelter.addPet(sparky);
		VirtualPet gizmo = new OrganicCat("Gizmo", "Organic Grey Furrball", 50, 100, 40, 30, shelter);
		shelter.addPet(gizmo);
		VirtualPet felix = new RoboticCat("Felix", "Metallic twitchy kitty", 50, 50, 25);
		shelter.addPet(felix);

		startupGreeting();

		// Console UI
		do {

			System.out.println("All admitted organic dogs and wellness levels:\n");
			System.out.println("Name \t|Overall|Happiness |Hunger |Thirst |Cage Filth %");
			for (VirtualPet availablePets : shelter.availablePets()) {
				if (availablePets instanceof OrganicDog) {
					System.out.println(availablePets.getName() + "\t|" + availablePets.getHealthLevel() + "\t|"
							+ availablePets.getHappinessLevel() + "\t   |"
							+ ((OrganicDog) availablePets).getHungerLevel() + "\t   |"
							+ ((OrganicDog) availablePets).getThirstLevel() + "\t   |"
							+ ((OrganicDog) availablePets).getWasteLevel());
				}
			}

			System.out.println("\nAll admitted organic cats and wellness levels:\n");
			System.out.println("Name \t|Overall|Happiness |Hunger |Thirst |Litter Box Capacity %"); // Box Can overfill
			for (VirtualPet availablePets : shelter.availablePets()) {
				if (availablePets instanceof OrganicCat) {
					System.out.println(availablePets.getName() + "\t|" + availablePets.getHealthLevel() + "\t|"
							+ availablePets.getHappinessLevel() + "\t   |"
							+ ((OrganicCat) availablePets).getHungerLevel() + "\t   |"
							+ ((OrganicCat) availablePets).getThirstLevel() + "\t   |"
							+ shelter.getLitterBoxWasteLevel());
				}
			}

			System.out.println("\nAll admitted robotic dogs and their wellness levels:\n");
			System.out.println("Name \t|Overall|Happiness |Maintenance");
			for (VirtualPet availablePets : shelter.availablePets()) {
				if (availablePets instanceof RoboticDog) {
					System.out.println(availablePets.getName() + "\t|" + availablePets.getHealthLevel() + "\t|"
							+ availablePets.getHappinessLevel() + "\t   |"
							+ ((RoboticDog) availablePets).getMaintenanceLevel());
				}
			}

			System.out.println("\nAll admitted robotic cats and their wellness levels:\n");
			System.out.println("Name \t|Overall|Happiness |Maintenance");
			for (VirtualPet availablePets : shelter.availablePets()) {
				if (availablePets instanceof RoboticCat) {
					System.out.println(availablePets.getName() + "\t|" + availablePets.getHealthLevel() + "\t|"
							+ availablePets.getHappinessLevel() + "\t   |"
							+ ((RoboticCat) availablePets).getMaintenanceLevel());
				}
			}

			System.out.println(petStatusMenu());

			String action = input.next();

			switch (action) {

				case "1":
					shelter.feedAllOrganics();
					shelter.shelterTick();
					System.out.println("All organic pets have been fed.\n");
					break;

				case "2":
					shelter.waterAllOrganics();
					shelter.shelterTick();
					System.out.println("All organic pets have been given some water.\n");
					break;

				case "3":
					int UUIDInputCountCase3 = 0;
					boolean maxUUIDEnteredCase3 = false;
					VirtualPet petToPlayWith = null;

					while (!maxUUIDEnteredCase3) {
						if (UUIDInputCountCase3 >= 3) {
							System.out.println("You've entered an invalid UUID excessively.");
							System.out.println("Enter 'M' to return to the main menu or enter a valid UUID:");
							String userInput = input.next().toUpperCase();
							if (userInput.equals("M")) {
								break; // Return to main menu
							}
						}

						System.out.println("Select a pet to play with:");
						for (VirtualPet availablePet : shelter.availablePets()) {
							System.out.println(availablePet.getName() + " - UUID: " + availablePet.getPetById());
						}

						String selectedUUID = input.next();
						try {
							petToPlayWith = shelter.getPet(UUID.fromString(selectedUUID));
							if (petToPlayWith != null) {
								maxUUIDEnteredCase3 = true;
							} else {
								System.out.println("Pet with the given UUID not found. Enter a valid UUID:");
								UUIDInputCountCase3++;

							}
						} catch (IllegalArgumentException e) {
							System.out.println("Invalid UUID format. Enter a valid UUID:");
							UUIDInputCountCase3++;
						}
					}

					if (petToPlayWith != null) {
						petToPlayWith.commandPlay();
						shelter.shelterTick();
						System.out.println("\n" + petToPlayWith.getName() +
								" drools from excitement after a nice play session.\n");
					}
					break;

				case "4":
					shelter.walkOrganicDogs();
					shelter.shelterTick();
					System.out.println("The organic dogs loved their walk through the park!\n");
					break;
				case "5":
					shelter.cleanAllDogCages();
					shelter.shelterTick();
					System.out.println("The dog cages have been scrubbed with Simple Green.");
					break;
				case "6":
					shelter.cleanTheLitterBox();
					shelter.shelterTick();
					System.out
							.println(
									"The litter box has been cleaned.\n");
					break;
				case "7":
					shelter.maintainRoboticPets();
					shelter.shelterTick();
					System.out.println("The robotic pets have been oiled.\n");
					break;
				case "8":
					shelter.addPet(newStrayOrganicDog(input, shelter));
					shelter.shelterTick();
					break;
				case "9":
					shelter.addPet(newStrayOrganicCat(input, shelter));
					shelter.shelterTick();
					break;
				case "10":
					shelter.addPet(newStrayRoboticDog(input));
					shelter.shelterTick();
					break;
				case "11":
					shelter.addPet(newStrayRoboticCat(input));
					shelter.shelterTick();
					break;
				case "12":
					int UUIDInputCountCase12 = 0;
					boolean maxUUIDEnteredCase12 = false;

					while (!maxUUIDEnteredCase12) {
						if (UUIDInputCountCase12 >= 3) {
							System.out.println("You've entered an invalid UUID excessively.");
							System.out.println("Enter 'M' to return to the main menu or enter a valid UUID:");
							String userInput = input.next().toUpperCase();
							if (userInput.equals("M")) {
								break; // Return to the main menu
							}
						}
						System.out.println("Enter the UUID of the pet you would like to adopt:");

						for (VirtualPet availablePet : shelter.availablePets()) {
							System.out.println("Name: " + availablePet.getName());
							System.out.println("Description: " + availablePet.getDescription());
							System.out.println("UUID: " + availablePet.getPetById() + "\n");
						}
						String adoptionUUIDInput = input.next();

						try {
							UUID adoptionUUID = UUID.fromString(adoptionUUIDInput);
							VirtualPet petToAdopt = shelter.getPet(adoptionUUID);
							if (petToAdopt != null) {
								System.out.println("Pet Information:\n");
								System.out.println("Name: " + petToAdopt.getName());
								System.out.println("Description: " + petToAdopt.getDescription());
								System.out.println("UUID: " + petToAdopt.getPetById() + "\n");

								shelter.removePet(adoptionUUID);
								shelter.shelterTick();
								System.out.println("You've adopted " + petToAdopt.getName() + " from the shelter.\n");
								maxUUIDEnteredCase12 = true;
							} else {
								System.out.println("Pet with the given UUID not found. Enter a valid UUID:");
								UUIDInputCountCase12++;
							}
						} catch (IllegalArgumentException e) {
							System.out.println("Invalid UUID format. Enter a valid UUID:");
							UUIDInputCountCase12++;
						}
					}
					break;
				case "13":
					System.out.println("Thank you for visiting The Pontiac Pet Shelter! Come back to visit soon.");
					System.exit(0);
					break;
				default:
					System.out.println("Enter a valid action ID #.");
			}
			// shelter.shelterTick();
		} while (shelter.arePetsAvailable() == false);

	}

	private static String startupGreeting() {
		return "Welcome to the Pontiac Dog Shelter AMOK!";

	}

	private static String petStatusMenu() {
		return " _________________________________\n" +
				"|                                 |\n" +
				"|  1. Feed All Organic Pets       |\n" +
				"|  2. Hydrate All Organic Pets    |\n" +
				"|  3. Play with a pet             |\n" +
				"|  4. Walk All Dogs               |\n" +
				"|  5. Clean all dog cages         |\n" +
				"|  6. Clean communal litter box   |\n" +
				"|  7. Oil all robots              |\n" +
				"|  8. Admit an organic dog    	  |\n" +
				"|  9. Admit an organic cat    	  |\n" +
				"| 10. Admit a robotic dog         |\n" +
				"| 11. Admit a robotic cat         |\n" +
				"| 12. Adopt a pet                 |\n" +
				"| 13. Quit                        |\n" +
				"|_________________________________|\n" +
				"Input an action using the corresponding # ID above: ";
	}

	private static VirtualPet newStrayOrganicDog(Scanner input, VirtualPetShelter shelter) {
		System.out.println("Enter the name of the dog:");
		String name = input.next();
		System.out.println("Enter a description of the dog:");
		String description = input.next();
		return new OrganicDog(name, description, 10, 10, 6, 6, 5);
	}

	private static VirtualPet newStrayOrganicCat(Scanner input, VirtualPetShelter shelter) {
		System.out.println("Enter the name of the cat:");
		String name = input.next();
		System.out.println("Enter a description of the cat:");
		String description = input.next();
		return new OrganicCat(name, description, 10, 10, 5, 7, shelter);
	}

	private static VirtualPet newStrayRoboticDog(Scanner input) {
		System.out.println("Enter the name of the dog:");
		String name = input.next();
		System.out.println("Enter a description of the dog:");
		String description = input.next();
		return new RoboticDog(name, description, 10, 10, 5);
	}

	private static VirtualPet newStrayRoboticCat(Scanner input) {
		System.out.println("Enter the name of the cat:");
		String name = input.next();
		System.out.println("Enter a description of the cat:");
		String description = input.next();
		return new RoboticCat(name, description, 10, 10, 6);

	}

}