package Project;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
class ManagementSystem {

    private Scanner scanner;

    private List<String> inventory;
    private static final String FILENAME = "information.txt";
    public ManagementSystem() {
        scanner = new Scanner(System.in);
        inventory = new ArrayList<>();
        loadInformation();
    }


    private void displayMenu() {
        System.out.println("==============================");
        System.out.println("Welcome to College MIS");
        System.out.println("==============================");
        System.out.println("1. Add Information");
        System.out.println("2. Update Information");
        System.out.println("3. View Inventory");
        System.out.println("4. Delete Information");

        System.out.println("6. Exit");
        System.out.println("==============================");
        System.out.print("Please enter your choice (1-6): ");
    }

    private int getUserChoice() {
        int choice = 0;
        boolean validChoice = false;
        while (!validChoice) {
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                validChoice = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid choice.");
                scanner.nextLine();
            }
        }
        return choice;
    }

    private void performOperation(int choice) {
        switch (choice) {
            case 1:
                addInformation();
                break;
            case 2:
                updateInformation();
                break;
            case 3:
                viewInventory();
                break;
            case 4:
                deleteInformation();
                break;
            case 5:
                performTransaction();
                break;
            case 6:
                System.out.println("Exiting the program...");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    private void addInformation() {
        System.out.print("Enter the name: ");
        String name = scanner.nextLine();
        inventory.add(name);
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILENAME, true))) {
            writer.println(name);
            System.out.println("Information added successfully.");
        } catch (IOException e) {
            System.out.println("Error occurred while saving information: " + e.getMessage());
        }
    }

    private void updateInformation() {
        System.out.print("Enter the index of the record to update: ");
        int index = scanner.nextInt();
        scanner.nextLine();
        if (index >= 0 && index < inventory.size()) {
            System.out.print("Enter the updated name: ");
            String updatedName = scanner.nextLine();
            inventory.set(index, updatedName);
            System.out.println("Information updated successfully.");
        } else {
            System.out.println("Invalid index. Please try again.");
        }
    }

    private void viewInventory() {
        System.out.println("Inventory records:");
        if (inventory.isEmpty()) {
            System.out.println("No records found.");
        } else {
            for (int i = 0; i < inventory.size(); i++) {
                System.out.println(i + ". " + inventory.get(i));
            }
        }
    }

    private void deleteInformation() {
        System.out.print("Enter the index of the record to delete: ");
        int index = scanner.nextInt();
        scanner.nextLine();
        if (index >= 0 && index < inventory.size()) {
            String deletedItem = inventory.remove(index);
            System.out.println("Information deleted successfully: " + deletedItem);
        } else {
            System.out.println("Invalid index. Please try again.");
        }
    }

    private void performTransaction() {
        System.out.print("Enter transaction details: ");
        String details = scanner.nextLine();
        System.out.println("Transaction completed successfully.");
    }

    private void generateReport() {
        System.out.println("Generating report...");
        // Implement report generation logic here
        System.out.println("Report generated successfully.");
    }

    private void saveInventory() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILENAME))) {
            for (String item : inventory) {
                writer.println(item);
            }
            System.out.println("Inventory saved successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the inventory.");
        }
    }

    private void loadInformation() {
        try (Scanner fileScanner = new Scanner(new File(FILENAME))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                inventory.add(line);
            }
            System.out.println("Inventory loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("No stored information found.");
        }
    }

    public void run() {
        int choice;
        do {
            displayMenu();
            choice = getUserChoice();
            performOperation(choice);
        } while (choice != 6);
    }
}