
package control;

import entity.Student;
import adt.ArrayList;
import adt.ListInterface;

public class StudentMaintenance {

    private ListInterface<Student> studentList;

    public StudentMaintenance() {
        this.studentList = new ArrayList<>();
       
    }

    public boolean addStudent(Student newStudent) {
        if (getStudentById(newStudent.getStudentId()) != null) {
            return false; 
        }
        
        studentList.add(newStudent);
        return true; 
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

    public ListInterface<Student> getAllStudents() {
        return studentList;
    }
    
 
    public boolean updateStudentProgramme(String studentId, String newProgramme) {
        Student s = getStudentById(studentId);
        if (s != null) {
            s.setProgramme(newProgramme);
            return true; 
        }
        return false; 
    }

    public boolean deleteStudent(String studentId) {
        for (int i = 0; i < studentList.size(); i++) {
            Student s = studentList.get(i);
            if (s.getStudentId().equals(studentId)) {
                return studentList.remove(i); 
            }
        }
        return false; 
    }
}