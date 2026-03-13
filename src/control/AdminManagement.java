/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;
import adt.ArrayList;
import adt.ListInterface;
import dao.AdminDAO;
import entity.Admin;

/**
 *
 * @author Lenovo
 */
public class AdminManagement {
    
    private ListInterface<Admin> adminList;
    private AdminDAO adminDAO = new AdminDAO();

    public AdminManagement() {
        this.adminList = new ArrayList<>();
    }

    public AdminManagement(ListInterface<Admin> sharedList) {
        this.adminList = sharedList;
    }

    // ===============================
    // Add New Admin
    // ===============================
    public String addNewAdmin(String id, String name, String position, String email, String phoneNumber) {

        try {
            Admin newAdmin = new Admin(id, name, position, email, phoneNumber);
            return adminDAO.addAdmin(newAdmin);
        } catch (IllegalArgumentException e) {
            return "INVALID_INPUT: " + e.getMessage();
        }
    }

    // ===============================
    // Get All Admins
    // ===============================
    public ArrayList<Admin> getAllAdmins() {
        return adminDAO.getAllAdmin();
    }

    // ===============================
    // Remove Admin
    // ===============================
    public boolean removeAdmin(String adminId) {
        return adminDAO.deleteAdmin(adminId);
    }

    // ===============================
    // Update Admin
    // ===============================
    public String updateAdmin(String id, String name, String programme, String email, String phone) {

        Admin updatedAdmin;
        try {
            updatedAdmin = new Admin(id, name, programme, email, phone);
        } catch (IllegalArgumentException e) {
            return "INVALID_INPUT: " + e.getMessage();
        }

        return adminDAO.updateAdmin(updatedAdmin);
    }

    // ===============================
    // Search Admin By ID
    // ===============================
    public ArrayList<Admin> searchAdmin(String id) {
        return adminDAO.getAdmin(id);
    }
    
    public Admin getAdminById(String adminId) {
        for (int i = 0; i < adminList.size(); i++) {
            Admin s = adminList.get(i);
            if (s.getAdminId().equals(adminId)) {
                return s; 
            }
        }
        return null; 
    }
    
}
