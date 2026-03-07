package dao;

import entity.Booking;
import UtilitiesClass.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class BookingDAO {

    public String addBooking(Booking booking) {
        String sql = "INSERT INTO Booking (bookingId, studentId, scheduleId, adminId, arrivalTime, leavingTime, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement query = conn.prepareStatement(sql)) {
            
            query.setString(1, booking.getBookingId());
            query.setString(2, booking.getStudentId());
            query.setString(3, booking.getScheduleId());           
            
            if (booking.getApprovedBy().equals("N/A")) {
                query.setNull(4, java.sql.Types.VARCHAR); 
            } else {
                query.setString(4, booking.getApprovedBy());
            }
            
            // Convert LocalDateTime to SQL Timestamp
            query.setTimestamp(5, Timestamp.valueOf(booking.getArrivalTime()));
            query.setTimestamp(6, Timestamp.valueOf(booking.getLeavingTime()));
            
            query.setString(7, booking.getStatus());
            
            query.executeUpdate();
            
            return "SUCCESS";
            
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
            return "ERROR";
        }
    }
    

    public adt.ListInterface<entity.Booking> getAllBookings() {
        adt.ListInterface<entity.Booking> bookingList = new adt.ArrayList<>();
        String sql = "SELECT * FROM Booking";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement query = conn.prepareStatement(sql);
             java.sql.ResultSet rs = query.executeQuery()) {

            while (rs.next()) {
                String id = rs.getString("bookingId");
                String studentId = rs.getString("studentId");
                String scheduleId = rs.getString("scheduleId");
                String adminId = rs.getString("adminId");
                
                java.sql.Timestamp arrivalTs = rs.getTimestamp("arrivalTime");
                java.sql.Timestamp leavingTs = rs.getTimestamp("leavingTime");

                java.time.LocalDateTime arrival = null;
                if (arrivalTs != null) {
                    arrival = arrivalTs.toLocalDateTime();
                }

                java.time.LocalDateTime leaving = null;
                if (leavingTs != null) {
                    leaving = leavingTs.toLocalDateTime();
                }
                
                String status = rs.getString("status");

                entity.Booking b = new entity.Booking(id, studentId, scheduleId, arrival, leaving);
                b.setStatus(status);
                
                if (adminId == null) {
                    b.setApprovedBy("N/A");
                } else {
                    b.setApprovedBy(adminId);
                }

                bookingList.add(b);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching bookings: " + e.getMessage());
        }

        return bookingList;
    }
    
    public boolean updateBookingStatus(String bookingId, String newStatus) {
        String sql = "UPDATE Booking SET status = ? WHERE bookingId = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement query = conn.prepareStatement(sql)) {
            
            query.setString(1, newStatus);
            query.setString(2, bookingId);
            
            int rowsAffected = query.executeUpdate();
            return rowsAffected > 0; 
            
        } catch (SQLException e) {
            System.out.println("Error updating booking status: " + e.getMessage());
            return false;
        }
    }
    
    //for admin kot
    public boolean deleteBooking(String bookingId) {
        String sql = "DELETE FROM Booking WHERE bookingId = ?";
        
        try (Connection conn = UtilitiesClass.DatabaseConnection.getConnection();
             PreparedStatement query = conn.prepareStatement(sql)) {
            
            query.setString(1, bookingId);
            
            int rowsAffected = query.executeUpdate();
            
            return rowsAffected > 0; 
            
        } catch (SQLException e) {
            System.out.println("Error deleting booking: " + e.getMessage());
            return false;
        }
    }
    
    public String getLatestBookingId() {
        String sql = "SELECT bookingId FROM Booking ORDER BY bookingId DESC LIMIT 1";
        String nextId = "B100"; // Default starting ID if the table is completely empty
        
        try (java.sql.Connection conn = UtilitiesClass.DatabaseConnection.getConnection();
             java.sql.PreparedStatement query = conn.prepareStatement(sql);
             java.sql.ResultSet rs = query.executeQuery()) {
            
            if (rs.next()) {
                String lastId = rs.getString("bookingId"); 
                // Extract the number part, convert to int, and add 1
                int idNum = Integer.parseInt(lastId.substring(1)); 
                nextId = "B" + (idNum + 1); 
            }
            
        } catch (java.sql.SQLException e) {
            System.out.println("Error generating new Booking ID: " + e.getMessage());
        }
        
        return nextId;
    }
}