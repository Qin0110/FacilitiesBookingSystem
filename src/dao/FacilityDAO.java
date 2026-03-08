package dao;

import entity.Facility;
import UtilitiesClass.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import adt.ArrayList;

public class FacilityDAO {

    public boolean addFacility(Facility facility) {
        String sql = "INSERT INTO Facility (facilityId, name, type, location, minCapacity, maxCapacity) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement query = conn.prepareStatement(sql)) {
             
            query.setString(1, facility.getFacilityId());
            query.setString(2, facility.getName());
            query.setString(3, facility.getType());
            query.setString(4, facility.getLocation());
            query.setInt(5, facility.getMinCapacity());
            query.setInt(6, facility.getMaxCapacity());
            
            return query.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.out.println("Error adding facility to DB: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<Facility> getAllFacilities() {
        ArrayList<Facility> facilityList = new ArrayList<>();
        String sql = "SELECT * FROM Facility";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement query = conn.prepareStatement(sql);
             ResultSet rs = query.executeQuery()) {
             
            while (rs.next()) {
                Facility facility = new Facility(
                    rs.getString("facilityId"),
                    rs.getString("name"),
                    rs.getString("type"),
                    rs.getString("location"),
                    rs.getInt("minCapacity"),
                    rs.getInt("maxCapacity")
                );
                facilityList.add(facility);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving facilities: " + e.getMessage());
        }
        
        return facilityList;
    }
    
    public boolean isFacilityExists(String facilityId) {
        String sql = "SELECT COUNT(*) FROM Facility WHERE facilityId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement query = conn.prepareStatement(sql)) {

            query.setString(1, facilityId);

            try (ResultSet rs = query.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; 
                }
            }
        } catch (SQLException e) {
            System.out.println("Error checking if facility exists: " + e.getMessage());
        }
        return false;
    }
    
    public boolean deleteFacility(String facilityId) {
        String sql = "DELETE FROM Facility WHERE facilityId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement query = conn.prepareStatement(sql)) {

            query.setString(1, facilityId);
            return query.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error deleting facility: " + e.getMessage());
            return false;
        }
    }
    
    public boolean updateFacility(Facility facility) {
        String sql = "UPDATE Facility SET name = ?, type = ?, location = ?, minCapacity = ?, maxCapacity = ? WHERE facilityId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement query = conn.prepareStatement(sql)) {

            query.setString(1, facility.getName());
            query.setString(2, facility.getType());
            query.setString(3, facility.getLocation());
            query.setInt(4, facility.getMinCapacity());
            query.setInt(5, facility.getMaxCapacity());
            query.setString(6, facility.getFacilityId());

            return query.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error updating facility: " + e.getMessage());
            return false;
        }
    }
}