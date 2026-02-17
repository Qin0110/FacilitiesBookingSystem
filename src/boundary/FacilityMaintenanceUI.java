package boundary;

import entity.Facility;
import java.util.Scanner;

public class FacilityMaintenanceUI {
    
    Scanner scanner = new Scanner(System.in);

    public int getMenuChoice() {
        System.out.println("\nFACILITY MAINTENANCE MENU");
        System.out.println("1. Add New Facility");
        System.out.println("2. Remove Facility");
        System.out.println("3. Update Facility Details");
        System.out.println("4. Display All Facilities");
        System.out.println("0. Quit");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public Facility inputFacilityDetails() {
        System.out.print("Enter Facility ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Facility Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Facility Type (e.g. Badminton Court, Gym Room, Discussion Room, Individual Study Room): ");
        String type = scanner.nextLine();
        System.out.print("Enter Capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine(); 
        return new Facility(id, name, type, capacity);
    }

    public String inputFacilityId() {
        System.out.print("Enter Facility ID: ");
        return scanner.nextLine();
    }

    public String inputName() {
        System.out.print("Enter New Name: ");
        return scanner.nextLine();
    }

    public String inputType() {
        System.out.print("Enter New Type (e.g. Badminton Court, Gym Room, Discussion Room, Individual Study Room): ");
        return scanner.nextLine();
    }
    
    public int inputCapacity() {
        System.out.print("Enter New Capacity: ");
        int c = scanner.nextInt();
        scanner.nextLine(); 
        return c;
    }

    public void listAllFacilities(String output) {
        System.out.println("\nList of Facilities:");
        System.out.printf("%-10s %-20s %-15s %5s\n", "ID", "Name", "Type", "Capacity");
        System.out.println(output);
    }
}