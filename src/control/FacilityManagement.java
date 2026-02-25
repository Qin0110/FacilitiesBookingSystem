package control;

import adt.ArrayList;
import adt.ListInterface;
import boundary.FacilityMaintenanceUI;
import dao.FacilityDAO;
import entity.Facility;

/**
 * Controls the logic for managing facilities.
 */
public class FacilityManagement {

    private ListInterface<Facility> facilityList;
    private FacilityDAO facilityDAO = new FacilityDAO();
    private FacilityMaintenanceUI facilityUI = new FacilityMaintenanceUI();

    public FacilityManagement(ListInterface<Facility> sharedList) {
        this.facilityList = sharedList;
    }

    public void runFacilityMaintenance() {
        int choice = 0;
        do {
            choice = facilityUI.getMenuChoice();
            switch (choice) {
                case 1:
                    addNewFacility();
                    break;
                case 2:
                    removeFacility();
                    break;
                case 3:
                    updateFacility();
                    break;
                case 4:
                    displayFacilities();
                    break;
                case 0:
                    System.out.println("Exiting system...");
                    facilityDAO.saveToFile(facilityList);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    private void addNewFacility() {
        Facility newFacility = facilityUI.inputFacilityDetails();
        if (facilityList.contains(newFacility)) {
            System.out.println("Error: A facility with ID " + newFacility.getFacilityId() + " already exists.");
        } else {
            facilityList.add(newFacility);
            System.out.println("Facility added successfully.");
        }
    }

    private void removeFacility() {
        String id = facilityUI.inputFacilityId();
        boolean found = false;

        for (int i = 0; i < facilityList.size(); i++) {
            Facility f = facilityList.get(i);
            if (f.getFacilityId().equals(id)) {
                facilityList.remove(i);
                System.out.println("Facility removed.");
                found = true;
                break; 
            }
        }

        if (!found) {
            System.out.println("Facility not found.");
        }
    }

    private void updateFacility() {
        String id = facilityUI.inputFacilityId();
        boolean found = false;

        for (int i = 0; i < facilityList.size(); i++) {
            Facility f = facilityList.get(i);
            if (f.getFacilityId().equals(id)) {
                String newName = facilityUI.inputName();
                String newType = facilityUI.inputType();
                int newCapacity = facilityUI.inputCapacity();

                f.setName(newName);
                f.setType(newType);
                f.setCapacity(newCapacity);

                System.out.println("Facility updated.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Facility not found.");
        }
    }

    private void displayFacilities() {
        String outputStr = "";
        for (int i = 0; i < facilityList.size(); i++) {
            outputStr += facilityList.get(i).toString() + "\n";
        }
        facilityUI.listAllFacilities(outputStr);
    }
    
    public static void main(String[] args) {
        FacilityDAO dao = new FacilityDAO();
        ListInterface<Facility> sharedList = dao.retrieveFromFile();

        FacilityManagement fm = new FacilityManagement(sharedList);

        fm.runFacilityMaintenance();
    }
}