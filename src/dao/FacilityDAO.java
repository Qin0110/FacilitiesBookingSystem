package dao;

import entity.Facility;
import UtilitiesClass.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import adt.ArrayList;

public class FacilityDAO {

    public String addFacility(Facility facility) {
        String sql = "INSERT INTO Facility (facilityId, name, type, location, capacity) VALUES (?, ?, ?, ?, ?)";
        
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement query = conn.prepareStatement(sql);            
            query.setString(1, facility.getFacilityId());
            query.setString(2, facility.getName());
            query.setString(3, facility.getType());
            query.setString(4, facility.getLocation());
            query.setInt(5, facility.getCapacity());
            
            query.executeUpdate();
            
            return "Facility Successfully Added";
        }
        catch (SQLException e) {
            return ("Error adding facility: " + e.getMessage());
        }
    }

    public ArrayList<Facility> getAllFacilities() {
        ArrayList<entity.Facility> facilityList = new ArrayList<>();
        String sql = "SELECT * FROM Facility";

        try{
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement query = conn.prepareStatement(sql);
            ResultSet rs = query.executeQuery();
             
            while (rs.next()) {
                String id = rs.getString("facilityId");
                String name = rs.getString("name");
                String type = rs.getString("type");
                String location = rs.getString("location");
                int capacity = rs.getInt("capacity");
                
                Facility facility = new Facility(id, name, type, location, capacity);
                facilityList.add(facility);
            }
        }catch (SQLException e) {
            System.out.println("Error retrieving facilities: " + e.getMessage());
        }
        
        return facilityList;
    }
    
    public boolean isFacilityExists(String facilityId) {
        String sql = "SELECT COUNT(*) FROM Facility WHERE facilityId = ?";

        try{ 
            Connection conn = DatabaseConnection.getConnection();
             PreparedStatement query = conn.prepareStatement(sql);

            query.setString(1, facilityId);

            ResultSet rs = query.executeQuery();
            
            if (rs.next()) {
                int count = rs.getInt(1); 
                
                return count == 0; 
            }
                        
            return true;
           

        } catch (SQLException e) {
            System.out.println("Error checking if facility exists: " + e.getMessage());
            return false; 
        }

    }
    
    public boolean deleteFacility(String facilityId) {
        String sql = "DELETE FROM Facility WHERE facilityId = ?";

        try{
            java.sql.Connection conn = UtilitiesClass.DatabaseConnection.getConnection();
            java.sql.PreparedStatement query = conn.prepareStatement(sql);

            query.setString(1, facilityId);

            query.executeUpdate();

            return true; 

        } catch (java.sql.SQLException e) {
            System.out.println("Error deleting facility: " + e.getMessage());
            return false;
        }
    }
    
    public String updateFacility(entity.Facility facility) {
        String sql = "UPDATE Facility SET name = ?, type = ?, location = ?, capacity = ? WHERE facilityId = ?";

        try (java.sql.Connection conn = UtilitiesClass.DatabaseConnection.getConnection();
             java.sql.PreparedStatement query = conn.prepareStatement(sql)) {

            query.setString(1, facility.getName());
            query.setString(2, facility.getType());
            query.setString(3, facility.getLocation());
            query.setInt(4, facility.getCapacity());
            query.setString(5, facility.getFacilityId());

            query.executeUpdate();
            return "Facility Successfully Updated";

        } catch (java.sql.SQLException e) {
            return "Error updating facility: " + e.getMessage();
        }
    }
    
    public ArrayList<Facility> getFacility(String id) {
        ArrayList<entity.Facility> facilityList = new ArrayList<>();
        String sql = "SELECT * FROM Facility WHERE facilityId LIKE ?";
        
        try{
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement query = conn.prepareStatement(sql);
            query.setString(1, "%" + id + "%");
            ResultSet rs = query.executeQuery();
            
            while (rs.next()) {
                String retrievedId = rs.getString("facilityId");
                String name = rs.getString("name");
                String type = rs.getString("type");
                String location = rs.getString("location");
                int capacity = rs.getInt("capacity");
                
                entity.Facility facility = new entity.Facility(retrievedId, name, type, location, capacity);
                facilityList.add(facility);
            }
             
        }catch (SQLException e) {
            System.out.println("Error retrieving facilities: " + e.getMessage());
        }
        
        return facilityList;
    }
}