package dao;

import adt.ArrayList;
import adt.ListInterface;
import entity.Student;
import UtilitiesClass.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO {

    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> studentList = new ArrayList<>();
        String sql = "SELECT * FROM Student";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement query = conn.prepareStatement(sql);
             ResultSet rs = query.executeQuery()) {

            while (rs.next()) {
                String id = rs.getString("studentId");
                String name = rs.getString("name");
                String prog = rs.getString("programme");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phoneNumber");

                Student s = new Student(id, name, prog, email, phoneNumber);
                studentList.add(s);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching students: " + e.getMessage());
        }

        return studentList;
    }
    
    public String addStudent(Student student) {
        String sql = "INSERT INTO Student (studentId, name, programme, email, phoneNumber) VALUES (?, ?, ?, ?, ?)";
        
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement query = conn.prepareStatement(sql);            
            query.setString(1, student.getStudentId());
            query.setString(2, student.getName());
            query.setString(3, student.getProgramme());
            query.setString(4, student.getEmail());
            query.setString(5, student.getPhoneNumber());
            
            query.executeUpdate();
            
            return "Student Successfully Added";
        }
        catch (SQLException e) {
            return ("Error adding student: " + e.getMessage());
        }
    }
    
    
    public boolean deleteStudent(String studentId) {
        String sql = "DELETE FROM Student WHERE studentId = ?";

        try{
            java.sql.Connection conn = UtilitiesClass.DatabaseConnection.getConnection();
            java.sql.PreparedStatement query = conn.prepareStatement(sql);

            query.setString(1, studentId);

            query.executeUpdate();

            return true; 

        } catch (java.sql.SQLException e) {
            System.out.println("Error deleting student: " + e.getMessage());
            return false;
        }
    }

    public String updateStudent(entity.Student student) {
        String sql = "UPDATE Student SET name = ?, programme = ?, email = ?, phoneNumber = ? WHERE studentId = ?";

        try (java.sql.Connection conn = UtilitiesClass.DatabaseConnection.getConnection();
             java.sql.PreparedStatement query = conn.prepareStatement(sql)) {

            query.setString(1, student.getName());
            query.setString(2, student.getProgramme());
            query.setString(3, student.getEmail());
            query.setString(4, student.getPhoneNumber());
            query.setString(5, student.getStudentId());

            query.executeUpdate();
            return "Student Successfully Updated";

        } catch (java.sql.SQLException e) {
            return "Error updating student: " + e.getMessage();
        }
    }
    
    
    public ArrayList<Student> getStudent(String id) {
        ArrayList<entity.Student> studentList = new ArrayList<>();
        String sql = "SELECT * FROM Student WHERE studentId LIKE ?";
        
        try{
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement query = conn.prepareStatement(sql);
            query.setString(1, "%" + id + "%");
            ResultSet rs = query.executeQuery();
            
            while (rs.next()) {
                String studentId = rs.getString("studentId");
                String name = rs.getString("name");
                String programme = rs.getString("programme");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phoneNumber");
                
                entity.Student student = new entity.Student(studentId, name, programme, email, phoneNumber);
                studentList.add(student);
            }
             
        }catch (SQLException e) {
            System.out.println("Error retrieving student: " + e.getMessage());
        }
        
        return studentList;
    }
}