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


    // ===============================
    // Add New Admin
    // ===============================
    public String addNewAdmin(String id, String name, String position, String email, String phoneNumber) {
        try {
            Admin newAdmin = new Admin(id, name, position, email, phoneNumber);
            
            boolean isAdded = adminCollection.add(newAdmin); 
            
            if (!isAdded) {
                return "ERROR: Admin ID '" + id + "' already exists! Please use a unique ID.";
            }
            
        } catch (IllegalArgumentException e) {
            return "ERROR: " + e.getMessage();
        }
        return "TRUE";
    }
    
    

    // ===============================
    // Get All Admins
    // ===============================
    public ListInterface<Admin> getAllAdmins() {
        return adminCollection.getAllAdmin();
    }

    // ===============================
    // Remove Admin
    // ===============================
    public boolean removeAdmin(String adminId) {
        return adminCollection.remove(adminId);
    }

    // ===============================
    // Update Admin
    // ===============================
    public String updateAdmin(String id, String name, String programme, String email, String phone) {

        Admin updatedAdmin;
        try {
            updatedAdmin = new Admin(id, name, programme, email, phone);
            adminCollection.set(updatedAdmin);
        } catch (IllegalArgumentException e) {
            return "ERROR: " + e.getMessage();
        }

        return "TRUE";
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
