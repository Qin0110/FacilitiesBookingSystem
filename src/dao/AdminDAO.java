package dao;

import adt.ArrayList;
import adt.ListInterface;
import entity.Admin;
import UtilitiesClass.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {

    public ArrayList<Admin> getAllAdmin() {
        ArrayList<Admin> adminList = new ArrayList<>();
        String sql = "SELECT * FROM Admin";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement query = conn.prepareStatement(sql);
             ResultSet rs = query.executeQuery()) {

            while (rs.next()) {
                String id = rs.getString("adminId");
                String name = rs.getString("name");
                String position = rs.getString("position");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phoneNumber");

                Admin a = new Admin(id, name, position, email, phoneNumber);
                adminList.add(a);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching admin: " + e.getMessage());
        }

        return adminList;
    }
    
    public String addAdmin(Admin admin) {
        String sql = "INSERT INTO Admin (adminId, name, position, email, phoneNumber) VALUES (?, ?, ?, ?, ?)";
        
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement query = conn.prepareStatement(sql);            
            query.setString(1, admin.getAdminId());
            query.setString(2, admin.getName());
            query.setString(3, admin.getPosition());
            query.setString(4, admin.getEmail());
            query.setString(5, admin.getPhoneNumber());
            
            query.executeUpdate();
            
            return "Admin Successfully Added";
        }
        catch (SQLException e) {
            return ("Error adding admin: " + e.getMessage());
        }
    }
    
    
    public boolean deleteAdmin(String adminId) {
        String sql = "DELETE FROM Admin WHERE adminId = ?";

        try{
            java.sql.Connection conn = UtilitiesClass.DatabaseConnection.getConnection();
            java.sql.PreparedStatement query = conn.prepareStatement(sql);

            query.setString(1, adminId);

            query.executeUpdate();

            return true; 

        } catch (java.sql.SQLException e) {
            System.out.println("Error deleting admin: " + e.getMessage());
            return false;
        }
    }

    public String updateAdmin(entity.Admin admin) {
        String sql = "UPDATE Admin SET name = ?, position = ?, email = ?, phoneNumber = ? WHERE adminId = ?";

        try (java.sql.Connection conn = UtilitiesClass.DatabaseConnection.getConnection();
             java.sql.PreparedStatement query = conn.prepareStatement(sql)) {

            query.setString(1, admin.getName());
            query.setString(2, admin.getPosition());
            query.setString(3, admin.getEmail());
            query.setString(4, admin.getPhoneNumber());
            query.setString(5, admin.getAdminId());

            query.executeUpdate();
            return "Admin Successfully Updated";

        } catch (java.sql.SQLException e) {
            return "Error updating Admin: " + e.getMessage();
        }
    }
    
    
    public ArrayList<Admin> getAdmin(String id) {
        ArrayList<entity.Admin> adminList = new ArrayList<>();
        String sql = "SELECT * FROM Admin WHERE adminId LIKE ?";
        
        try{
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement query = conn.prepareStatement(sql);
            query.setString(1, "%" + id + "%");
            ResultSet rs = query.executeQuery();
            
            while (rs.next()) {
                String adminId = rs.getString("adminId");
                String name = rs.getString("name");
                String position = rs.getString("position");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phoneNumber");
                
                entity.Admin admin = new entity.Admin(adminId, name, position, email, phoneNumber);
                adminList.add(admin);
            }
             
        }catch (SQLException e) {
            System.out.println("Error retrieving admin: " + e.getMessage());
        }
        
        return adminList;
    }
}