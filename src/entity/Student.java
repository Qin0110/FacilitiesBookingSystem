
package entity;

import java.io.Serializable;
import java.util.Objects;

public class Student implements Serializable {

    private String studentId;  
    private String name;
    private String programme; 
    private String email;
    private String phoneNumber;

    public Student(String studentId, String name, String programme, String email, String phoneNumber) {
        this.studentId = studentId;
        this.name = name;
        this.programme = programme;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getStudentId() { 
        return studentId; 
    }
    
    public String getName() { 
        return name; 
    }
    
    public String getProgramme() { 
        return programme; 
    }
    
    public String getEmail() { 
        return email; 
    }

    public String getPhoneNumber() { 
        return phoneNumber; 
    }


    public void setName(String name) { 
        this.name = name; 
    }
    
    public void setProgramme(String programme) { 
        this.programme = programme; 
    }
    
    public void setEmail(String email) {
        if (email == null || !email.matches("^[A-Za-z0-9]+@[A-Za-z0-9.]+$")) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        this.email = email;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || !phoneNumber.matches("^01[0-9]-[0-9]{7}$")) {
            throw new IllegalArgumentException("Invalid phone number format. Example: 019-1234567");
        }
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        //here sure the object is a student
        Student student = (Student) obj;
        return studentId.equals(student.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }
    
    @Override
    public String toString() {
        return String.format("Student ID: %s | Name: %s | Programme: %s| Email: %s| Phone Number: %s", 
                studentId, name, programme, email, phoneNumber);
    }
}