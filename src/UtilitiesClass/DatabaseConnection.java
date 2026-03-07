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

    public static Connection getConnection()  {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            connection = DriverManager.getConnection(URL + DATABASE, USER, PASSWORD);
            
        } catch (ClassNotFoundException e) {
            System.out.println("Error! MySQL JDBC Driver not found. Did you add the .jar file to NetBeans Libraries?");
            e.printStackTrace();
        } catch (SQLException e) {
                        
            try{
                
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement query = connection.createStatement();

                query.executeUpdate("CREATE DATABASE " + DATABASE);
                query.executeUpdate("USE " + DATABASE);
                
                query.executeUpdate("CREATE TABLE IF NOT EXISTS Facility (" +
                    "facilityId VARCHAR(15) PRIMARY KEY, " +
                    "name VARCHAR(150) NOT NULL, " +
                    "type VARCHAR(100), " +
                    "location VARCHAR(300), " +
                    "minCapacity INT, "+
                    "maxCapacity INT)");
                
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
                    "facilityId VARCHAR(15), " +
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
                    "facilityId VARCHAR(15), " +
                    "approveBy VARCHAR(45), " +
                    "startingTime DATETIME, " +
                    "endingTime DATETIME, " +
                    "description VARCHAR(500), " +
                    "FOREIGN KEY (facilityId) REFERENCES Facility(facilityId), " +
                    "FOREIGN KEY (approveBy) REFERENCES Admin(adminId))");
                
                
                //Facility
                
                StringBuilder facilities = new StringBuilder();
                
                facilities.append("INSERT INTO Facility (facilityId, name, type, location, minCapacity, maxCapacity) VALUES \n");
                
                for (int i = 1; i <= 20; i++) {
                    facilities.append(String.format("('GYM-%02d', 'Gymnasium %d', 'Sports', 'Sports Complex', 1, 1), \n", i, i));
                    facilities.append(String.format("('POOL-%02d', 'Swimming Pool %d', 'Sports', 'Sports Complex', 1, 1), \n", i, i));        
                    facilities.append(String.format("('Q-%02d', 'Individual Study Room Q%d', 'Library Study Room', 'Library Level 3', 1, 1), \n", i, i));        
                }

                for (int i = 1; i <= 10; i++) {
                    facilities.append(String.format("('SP-FOYER-%d', 'S.P. Foyer %d', 'Event Space', 'Sports Pavilion', 1, 50), \n", i, i));
                    facilities.append(String.format("('BADM-%02d', 'Badminton Court %d', 'Sports', 'Sports Complex', 1, 8), \n", i, i));

                }  
        
                for (int i = 1; i <= 2; i++) {
                    facilities.append(String.format("('TENNIS-%02d', 'Tennis Court %d', 'Sports', 'Sports Complex', 1, 8), \n", i, i));
                    facilities.append(String.format("('SQUASH-%02d', 'Squash Court %d', 'Sports', 'Sports Complex', 1, 6), \n", i, i));
             

                }

                for (int i = 1; i <= 4; i++) {
                    facilities.append(String.format("('PICKLE-%02d', 'Pickleball Court %d', 'Sports', 'Sports Complex', 1, 8), \n", i, i));
                    facilities.append(String.format("('PICKLE-ARENA-%02d', 'Arena Pickleball Court %d', 'Sports', 'TAR UMT Arena', 1, 8), \n", i, i));
                    facilities.append(String.format("('CC-%02d', 'Discussion Room (1PC)', 'Cyber Centre Discussion Room', 'Cyber Centre', 2,4), \n", i));
                    facilities.append(String.format("('CC-%02d', 'Discussion Room (2PC)', 'Cyber Centre Discussion Room', 'Cyber Centre', 5,8), \n", i+4));
                    facilities.append(String.format("('CC-%02d', 'Discussion Room with Projector (2PC)', 'Cyber Centre Discussion Room', 'Cyber Centre', 5,8), \n", i+8));

                }
                
                for (int i = 1; i <= 10; i++){
                    facilities.append(String.format("('A-%02d', 'Discussion Room', 'Library Study Room', 'Library Level 1', 2, 4), \n", i));
                    facilities.append(String.format("('B-%02d', 'Discussion Room with PC', 'Library Study Room', 'Library Level 2', 2, 4), \n", i));
                }
                
                for (int i = 11; i<= 15; i++){
                    facilities.append(String.format("('A-%02d', 'Discussion Room', 'Library Study Room', 'Library Level 1', 5, 6), \n", i));
                    facilities.append(String.format("('B-%02d', 'Discussion Room with PC', 'Library Study Room', 'Library Level 2', 5,6), \n", i));
                    facilities.append(String.format("('A-%02d', 'Discussion Room', 'Library Study Room', 'Library Level 1', 6, 8), \n", i+5));
                    facilities.append(String.format("('B-%02d', 'Discussion Room with PC', 'Library Study Room', 'Library Level 2', 6,8), \n", i+5));
                }
                
                
                facilities.append("('KARAOKE-01', 'Guest/Karaoke Room', 'Recreation', 'Student Centre', 1, 10), \n");
                facilities.append("('SNOOKER-01', 'Snooker Table 1', 'Recreation', 'Clubhouse', 1, 6), \n");
                facilities.append("('ARENA-FUT', 'Futsal Court', 'Sports', 'TAR UMT Arena', 1, 16), \n");
                facilities.append("('ARENA-HND', 'Handball Court', 'Sports', 'TAR UMT Arena', 1, 16)");
                
  
                
                    
                
                String insertFacilitiesSQL = facilities.toString();
                
                query.executeUpdate(insertFacilitiesSQL);

                // STUDENTS

                StringBuilder students = new StringBuilder();
                students.append("INSERT INTO Student (studentId, name, programme) VALUES \n");
                for (int i = 1; i <= 19; i++) {
                    students.append(String.format("('23WMR%05d', 'Dummy Student %d', 'Data Science'), \n", i, i));
                }
                students.append("('23WMR00020', 'Ali bin Abu', 'Software Engineering')"); 
                
                query.executeUpdate(students.toString());

                
                // ADMINS

                StringBuilder admins = new StringBuilder();
                admins.append("INSERT INTO Admin (adminId, name, position) VALUES \n");
                for (int i = 1; i <= 4; i++) {
                    admins.append(String.format("('A%03d', 'Admin Staff %d', 'Facility Assistant'), \n", i, i));
                }
                admins.append("('A005', 'Mr. Tan', 'Facility Manager')"); 
                
                query.executeUpdate(admins.toString());

                
                // SCHEDULE
                
                
                StringBuilder schedules = new StringBuilder();
                schedules.append("INSERT INTO Schedule (scheduleId, facilityId, startingTime, endingTime, availability) VALUES \n");
                

                for (int i = 1; i <= 10; i++) {
                    schedules.append(String.format("('SCH-G%03d', 'GYM-%02d', '2026-03-05 10:00:00', '2026-03-05 12:00:00', 'Available'), \n", i, i));
                }
                
                for (int i = 1; i <= 8; i++) {
                    schedules.append(String.format("('SCH-B%03d', 'BADM-%02d', '2026-03-06 14:00:00', '2026-03-06 16:00:00', 'Available'), \n", i, i));
                }
                
                for (int i = 1; i <= 5; i++) {
                    schedules.append(String.format("('SCH-CC%03d', 'CC-%02d', '2026-03-10 10:00:00', '2026-03-10 12:00:00', 'Available'), \n", i, i));
                }
                
                for (int i = 1; i <= 5; i++) {
                    schedules.append(String.format("('SCH-Q%03d', 'Q-%02d', '2026-03-10 10:00:00', '2026-03-10 12:00:00', 'Available'), \n", i, i));
                }

                for (int i = 1; i <= 5; i++) {
                    schedules.append(String.format("('SCH-LA%03d', 'A-%02d', '2026-03-11 14:00:00', '2026-03-11 16:00:00', 'Available'), \n", i, i));
                }

                for (int i = 1; i <= 4; i++) {
                    schedules.append(String.format("('SCH-LB%03d', 'B-%02d', '2026-03-12 09:00:00', '2026-03-12 11:00:00', 'Available'), \n", i, i));
                }
                schedules.append("('SCH-LB005', 'B-05', '2026-03-12 09:00:00', '2026-03-12 11:00:00', 'Available')");
                
                query.executeUpdate(schedules.toString());
                                
                

                
                
            }catch (SQLException ex){

                try {
                    Connection cleanConnection = DriverManager.getConnection(URL, USER, PASSWORD);
                    Statement cleanQuery = cleanConnection.createStatement();

                    cleanQuery.executeUpdate("DROP DATABASE " + DATABASE);

                } catch (SQLException e2) {
                    e2.printStackTrace();
                }
                System.out.println("Error: " + ex);
                System.exit(0);
            }
            
        }
        return connection;
    }
}