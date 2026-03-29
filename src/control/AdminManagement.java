package control;
import adt.SingleLinkedList;
import adt.ListInterface;
import entity.Admin;

import dao.AdminCollection;


public class AdminManagement {
    
    private ListInterface<Admin> adminList;
    AdminCollection adminCollection = new AdminCollection();
    
    public AdminManagement() {
        this.adminList = adminCollection.getAllAdmin();
    }

    public String addNewAdmin(String id, String name, String position, String email, String phoneNumber) {

        ListInterface<Admin> adminList = this.getAllAdmins(); 

        // Loop to check for duplicates
        for (int i = 0; i < adminList.size(); i++) { 
        
            Admin existingAdmin = adminList.get(i);

            if (existingAdmin.getId().equalsIgnoreCase(id)) {
                return "Error: This Admin ID already exists!"; 
            }
            if (existingAdmin.getEmail().equalsIgnoreCase(email)) {
                return "Error: This Email is already registered to another Admin!"; 
            }
            if (existingAdmin.getPhoneNumber().equals(phoneNumber)) {
                return "Error: This Phone Number is already registered to another Admin!"; 
            }
        }

        // If no duplicates are found, create and save the admin
        try {
            Admin newAdmin = new Admin(id, name, position, email, phoneNumber);
            adminCollection.add(newAdmin);
             
            return "Admin added successfully!"; 
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage(); 
        }
    }

    public ListInterface<Admin> getAllAdmins() {
        return adminCollection.getAllAdmin();
    }

    public boolean removeAdmin(String adminId) {
        return adminCollection.remove(adminId);
    }

    public String updateAdmin(String id, String name, String position, String email, String phone) {

        ListInterface<Admin> adminList = this.getAllAdmins(); 
        
        // Loop through the list to check for duplicates
        for (int i = 0; i < adminList.size(); i++) {
            Admin existingAdmin = adminList.get(i);

            // Skip the admin info currently updating
            if (existingAdmin.getId().equalsIgnoreCase(id)) {
                continue; 
            }

            // Checking OTHER admins' records (ID does not need checking since it cannot be edited)
            if (existingAdmin.getEmail().equalsIgnoreCase(email)) {
                return "Error: This Email is already registered to another Admin!"; 
            }
            if (existingAdmin.getPhoneNumber().equals(phone)) {
                return "Error: This Phone Number is already registered to another Admin!"; 
            }
        }

        // Update the admin
        try {
            Admin updatedAdmin = new Admin(id, name, position, email, phone);
            adminCollection.set(updatedAdmin); 
            
            return updatedAdmin.getName() + " with Admin ID " + 
                   updatedAdmin.getId() + " has been successfully updated!";
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        }
    }

    // ===============================
    // Search Admin By ID
    // ===============================
//    public ListInterface<Admin> searchAdmin(String id) {
//        return adminCollection.getAdminById(id);
//    }
    
        
    public ListInterface<Admin> searchAdminForTable(String id,String position, String sortBy) {
        ListInterface<Admin> searchResults = new SingleLinkedList<>();
        ListInterface<Admin> allAdmin = adminCollection.getAllAdmin();
 
        for (int i = 0; i < allAdmin.size(); i++) {
            Admin admin = allAdmin.get(i);
 
            boolean matchesId   = admin.getAdminId().toLowerCase().contains(id.toLowerCase())
                                || admin.getName().toLowerCase().contains(id.toLowerCase());
            boolean matchesType = (position == null || position.equals("ALL") || admin.getPosition().equals(position));
 
            if (matchesId && matchesType) {
                searchResults.add(admin);
            }
        }
        
        // Sort the filtered list based on the combo box selection
        if (sortBy != null && !sortBy.trim().isEmpty()) {
            searchResults.sort(new adt.Comparator<entity.Admin>() {
                @Override
                public int compare(entity.Admin a1, entity.Admin a2) {
                    switch (sortBy) {
                        case "Admin ID":
                            return a1.getAdminId().compareToIgnoreCase(a2.getAdminId());
                        case "Name":
                            return a1.getName().compareToIgnoreCase(a2.getName());
                        case "Position":
                            return a1.getPosition().compareToIgnoreCase(a2.getPosition());
                        case "Email":
                            return a1.getEmail().compareToIgnoreCase(a2.getEmail());
                        case "Phone Number":
                            return a1.getPhoneNumber().compareToIgnoreCase(a2.getPhoneNumber());
                        default:
                            return 0; // No sorting
                    }
                }
            });
        }
        
        return searchResults;
    }
    
    public Admin getAdminById(String adminId) {
        for (int i = 0; i < adminList.size(); i++) {
            Admin admin = adminList.get(i);
            if (admin.getAdminId().equals(adminId)) {
                return admin; 
            }
        }
        return null; 
    }
    
    public String generateAdminReport() {
        adt.ListInterface<entity.Admin> allAdmins = adminCollection.getAllAdmin();

        int totalAdmins = allAdmins.size();

        int managerCount = 0;
        int assistantCount = 0;
        int systemAdminCount = 0; 

        // Count the positions
        for (int i = 0; i < totalAdmins; i++) {
            String pos = allAdmins.get(i).getPosition();

            if (pos.equalsIgnoreCase("Facility Manager")) {
                managerCount++;
            } else if (pos.equalsIgnoreCase("Facility Assistant")) {
                assistantCount++;
            } else if (pos.equalsIgnoreCase("System Admin")) {
                systemAdminCount++;
            }
        }

        // Build the report text
        StringBuilder report = new StringBuilder();
        report.append("==================================================\n");
        report.append("          FACILITY STAFF SUMMARY REPORT           \n");
        report.append("==================================================\n\n");

        report.append("--- OVERALL STATISTICS ---\n");
        report.append("Total Staff in System     : ").append(totalAdmins).append("\n\n");

        report.append("--- POSITION BREAKDOWN ---\n");
        report.append(String.format("%-25s : %d\n", "Facility Manager", managerCount));
        report.append(String.format("%-25s : %d\n", "Facility Assistant", assistantCount));
        report.append(String.format("%-25s : %d\n\n", "System Admin", systemAdminCount)); // Optional

        report.append("--- DETAILED STAFF LIST ---\n");
        report.append(String.format("%-15s %-25s %-20s\n", "STAFF ID", "NAME", "POSITION"));
        report.append("-----------------------------------------------------------------\n");

        for (int i = 0; i < totalAdmins; i++) {
            entity.Admin a = allAdmins.get(i);
            report.append(String.format("%-15s %-25s %-20s\n", 
                a.getAdminId(), a.getName(), a.getPosition()));
        }

        report.append("\n=================== END OF REPORT ===================");

        return report.toString();
    }
    
}
