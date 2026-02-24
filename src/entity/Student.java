
package entity;

import java.io.Serializable;
import java.util.Objects;

public class Student implements Serializable {

    private String studentId;  
    private String name;
    private String programme; 

    public Student(String studentId, String name, String programme) {
        this.studentId = studentId;
        this.name = name;
        this.programme = programme;
    }

    public String getStudentId() { return studentId; }
    public String getName() { return name; }
    public String getProgramme() { return programme; }

    public void setName(String name) { this.name = name; }
    public void setProgramme(String programme) { this.programme = programme; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return studentId.equals(student.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }
    
    @Override
    public String toString() {
        return String.format("Student ID: %s | Name: %s | Programme: %s", 
                studentId, name, programme);
    }
}