package control;
import adt.SingleLinkedList;
import adt.ListInterface;
import entity.Admin;

import dao.AdminCollection;


public class AdminManagement {
    
    private ListInterface<Admin> adminList;
    AdminCollection adminCollection = new AdminCollection();
    public AdminManagement() {
        
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
    
        
    public ListInterface<Admin> searchAdminForTable(String id,String position) {
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
    
}
