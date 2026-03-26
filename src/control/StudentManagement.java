package control;
import adt.SingleLinkedList;
import adt.ListInterface;
import entity.Student;

import dao.StudentCollection;


public class StudentManagement {
    
    private ListInterface<Student> studentList;
    StudentCollection studentCollection = new StudentCollection();
    public StudentManagement() {
        
    }
    

    // ===============================
    // Add New Student
    // ===============================
    public String addNewStudent(String id, String name, String programme, String email, String phoneNumber) {

        try {
            Student newStudent = new Student(id, name, programme, email, phoneNumber);
            studentCollection.add(newStudent);
        } catch (IllegalArgumentException e) {
            return "ERROR: " + e.getMessage();
        }
        return "TRUE";
    }

    // ===============================
    // Get All Students
    // ===============================
    public ListInterface<Student> getAllStudents() {
        return studentCollection.getAllStudent();
    }

    // ===============================
    // Remove Student
    // ===============================
    public boolean removeStudent(String studentId) {
        return studentCollection.remove(studentId);
    }

    // ===============================
    // Update Student
    // ===============================
    public String updateStudent(String id, String name, String programme, String email, String phone) {

        Student updatedStudent;
        try {
            updatedStudent = new Student(id, name, programme, email, phone);
            studentCollection.set(updatedStudent);
        } catch (IllegalArgumentException e) {
            return "ERROR: " + e.getMessage();
        }

        return "TRUE";
    }

    // ===============================
    // Search Student By ID
    // ===============================
//    public ListInterface<Student> searchStudent(String id) {
//        return studentCollection.getStudentById(id);
//    }
    
        
    public ListInterface<Student> searchStudentForTable(String id,String programme) {
        ListInterface<Student> searchResults = new SingleLinkedList<>();
        ListInterface<Student> allStudent = studentCollection.getAllStudent();
 
        for (int i = 0; i < allStudent.size(); i++) {
            Student student = allStudent.get(i);
 
            boolean matchesId   = student.getStudentId().toLowerCase().contains(id.toLowerCase())
                                || student.getName().toLowerCase().contains(id.toLowerCase());
            boolean matchesType = (programme == null || programme.equals("ALL") || student.getProgramme().equals(programme));
 
            if (matchesId && matchesType) {
                searchResults.add(student);
            }
        }
        return searchResults;
    }
    
    public Student getStudentById(String studentId) {
            return studentCollection.getStudentById(studentId);
    }
    
}
