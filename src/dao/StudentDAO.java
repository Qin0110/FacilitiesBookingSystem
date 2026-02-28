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

    public ListInterface<Student> getAllStudents() {
        ListInterface<Student> studentList = new ArrayList<>();
        String sql = "SELECT * FROM Student";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement query = conn.prepareStatement(sql);
             ResultSet rs = query.executeQuery()) {

            while (rs.next()) {
                String id = rs.getString("studentId");
                String name = rs.getString("name");
                String prog = rs.getString("programme");

                Student s = new Student(id, name, prog);
                studentList.add(s);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching students: " + e.getMessage());
        }

        return studentList;
    }
}