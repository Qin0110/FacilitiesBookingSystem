package UtilitiesClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String DATABASE = "tarumt_facility_booking_db";
    private static final String PASSWORD = "123456"; 

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            connection = DriverManager.getConnection(URL + DATABASE, USER, PASSWORD);
            System.out.println("Database Connection Successful!");
            
        } catch (ClassNotFoundException e) {
            System.out.println("Error! MySQL JDBC Driver not found. Did you add the .jar file to NetBeans Libraries?");
            e.printStackTrace();
        } catch (SQLException e) {
                        
            try{
                
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement query = connection.createStatement();
                
                StringBuilder facilities = new StringBuilder();
                
                facilities.append("INSERT INTO Facility (facilityId, name, type, location, capacity) VALUES \n");
                
                for (int i = 1; i <= 20; i++) {
                    facilities.append(String.format("('GYM-%02d', 'Gymnasium %d', 'Sports', 'Sports Complex', 50), \n", i, i));
                }

                for (int i = 1; i <= 10; i++) {
                    facilities.append(String.format("('SP-FOYER-%d', 'S.P. Foyer %d', 'Event Space', 'Sports Pavilion', 100), \n", i, i));
                }

                for (int i = 1; i <= 20; i++) {
                    facilities.append(String.format("('POOL-%02d', 'Swimming Pool %d', 'Sports', 'Sports Complex', 100), \n", i, i));
                }

                for (int i = 1; i <= 9; i++) {
                    facilities.append(String.format("('BADM-%02d', 'Badminton Court %d', 'Sports', 'Sports Complex', 4), \n", i, i));
                }

                for (int i = 1; i <= 2; i++) {
                    facilities.append(String.format("('TENNIS-%02d', 'Tennis Court %d', 'Sports', 'Sports Complex', 4), \n", i, i));
                }

                for (int i = 1; i <= 4; i++) {
                    facilities.append(String.format("('PICKLE-%02d', 'Pickleball Court %d', 'Sports', 'Sports Complex', 4), \n", i, i));
                }

                for (int i = 1; i <= 4; i++) {
                    facilities.append(String.format("('ARENA-PB%d', 'Arena Pickleball Court %d', 'Sports', 'TAR UMT Arena', 4), \n", i, i));
                }

                for (int i = 1; i <= 2; i++) {
                    facilities.append(String.format("('SQUASH-%02d', 'Squash Court %d', 'Sports', 'Sports Complex', 4), \n", i, i));
                } 
                
                facilities.append("('KARAOKE-01', 'Guest/Karaoke Room', 'Recreation', 'Student Centre', 10), \n");
                facilities.append("('SNOOKER-01', 'Snooker Table 1', 'Recreation', 'Clubhouse', 4), \n");
                facilities.append("('ARENA-FUT', 'Futsal Court', 'Sports', 'TAR UMT Arena', 14), \n");
                facilities.append("('ARENA-HND', 'Handball Court', 'Sports', 'TAR UMT Arena', 14)");
                
                String insertFacilitiesSQL = facilities.toString();

                query.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DATABASE);
                query.executeUpdate("USE " + DATABASE);
                
                query.executeUpdate("CREATE TABLE IF NOT EXISTS Facility (" +
                    "facilityId VARCHAR(45) PRIMARY KEY, " +
                    "name VARCHAR(150) NOT NULL, " +
                    "type VARCHAR(100), " +
                    "location VARCHAR(300), " +
                    "capacity INT)");
                
                query.executeUpdate("CREATE TABLE Student (" +
                    "studentId VARCHAR(45) PRIMARY KEY, " +
                    "name VARCHAR(50) NOT NULL, " +
                    "programme VARCHAR(50))");
                
                query.executeUpdate("CREATE TABLE  Admin (" +
                    "adminId VARCHAR(45) PRIMARY KEY, " +
                    "name VARCHAR(100) NOT NULL, " +
                    "position VARCHAR(100))");
                
                query.executeUpdate("CREATE TABLE Schedule (" +
                    "scheduleId VARCHAR(45) PRIMARY KEY, " +
                    "facilityId VARCHAR(45), " +
                    "startingTime DATETIME, " +
                    "endingTime DATETIME, " +
                    "availability VARCHAR(20) DEFAULT 'Available', " +
                    "FOREIGN KEY (facilityId) REFERENCES Facility(facilityId))");
                
                query.executeUpdate("CREATE TABLE Booking (" +
                    "bookingId VARCHAR(10) PRIMARY KEY, " +
                    "studentId VARCHAR(45), " +
                    "scheduleId VARCHAR(45), " +
                    "adminId VARCHAR(45), " +
                    "arrivalTime DATETIME, " +
                    "leavingTime DATETIME, " +
                    "status VARCHAR(20) DEFAULT 'Pending', " +
                    "FOREIGN KEY (studentId) REFERENCES Student(studentId), " +
                    "FOREIGN KEY (scheduleId) REFERENCES Schedule(scheduleId), " +
                    "FOREIGN KEY (adminId) REFERENCES Admin(adminId))");
                
                query.executeUpdate("CREATE TABLE IF NOT EXISTS Maintenance(" +
                    "maintenanceId INT PRIMARY KEY AUTO_INCREMENT, " +
                    "facilityId VARCHAR(45), " +
                    "approveBy VARCHAR(45), " +
                    "startingTime DATETIME, " +
                    "endingTime DATETIME, " +
                    "description VARCHAR(500), " +
                    "FOREIGN KEY (facilityId) REFERENCES Facility(facilityId), " +
                    "FOREIGN KEY (approveBy) REFERENCES Admin(adminId))");
                
                System.out.println("Successfully Create Database!");
                
               query.executeUpdate(insertFacilitiesSQL);
                
            }catch (SQLException ex){
                System.out.println("Fail to Create Database.");
                System.out.println("Error! " + ex.getMessage());
            }
            
        }
        return connection;
    }
}