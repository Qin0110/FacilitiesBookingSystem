package control;

import adt.ArrayList;
import adt.ListInterface;
import dao.StudentDAO;
import entity.Student;

/**
 * Controls the logic for managing students.
 */
public class StudentManagement {

    private ListInterface<Student> studentList;
    private StudentDAO studentDAO = new StudentDAO();

    public StudentManagement() {
        this.studentList = new ArrayList<>();
    }

    public StudentManagement(ListInterface<Student> sharedList) {
        this.studentList = sharedList;
    }

    // ===============================
    // Add New Student
    // ===============================
    public String addNewStudent(String id, String name, String programme, String email, String phone) {

        try {
            Student newStudent = new Student(id, name, programme, email, phone);
            return studentDAO.addStudent(newStudent);
        } catch (IllegalArgumentException e) {
            return "INVALID_INPUT: " + e.getMessage();
        }
    }

    // ===============================
    // Get All Students
    // ===============================
    public ArrayList<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }

    // ===============================
    // Remove Student
    // ===============================
    public boolean removeStudent(String studentId) {
        return studentDAO.deleteStudent(studentId);
    }

    // ===============================
    // Update Student
    // ===============================
    public String updateStudent(String id, String name, String programme, String email, String phone) {

        Student updatedStudent;
        try {
            updatedStudent = new Student(id, name, programme, email, phone);
        } catch (IllegalArgumentException e) {
            return "INVALID_INPUT: " + e.getMessage();
        }

        return studentDAO.updateStudent(updatedStudent);
    }

    // ===============================
    // Search Student By ID
    // ===============================
    public ArrayList<Student> searchStudent(String id) {
        return studentDAO.getStudent(id);
    }
    
    public Student getStudentById(String studentId) {
        for (int i = 0; i < studentList.size(); i++) {
            Student s = studentList.get(i);
            if (s.getStudentId().equals(studentId)) {
                return s; 
            }
        }
        return null; 
    }
}