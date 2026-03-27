package entity;

public class Student extends User {
    private String programme; 

    public Student(String studentId, String name, String programme, String email, String phoneNumber) {
        super(name, phoneNumber);
        this.setId(studentId);
        this.programme = programme;
        this.setEmail(email); 
    }

    public String getProgramme() { 
        return programme; 
    }
    
    public void setProgramme(String programme) {
        this.programme = programme; 
    }
    
    public String getStudentId() {
        return super.getId(); 
    }
    
    @Override
    public void setId(String id) {
        // 1. Check the strict Student ID format
        if (id == null || !id.matches("^[0-9]{2}WMR[0-9]{5}$")) {
            throw new IllegalArgumentException("Invalid Student ID. Example: XXWMRXXXXX");
        }
        // 2. Hand it up to the User class to save
        super.setId(id);
    }
    
    @Override
    public void setEmail(String email) {
        if (email == null || !email.matches("^[A-Za-z0-9._]+@student\\.tarc\\.edu\\.my$")) {
            throw new IllegalArgumentException("Invalid email format. Must end with @student.tarc.edu.my");
        }
        
        // after passes, let the User class save it
        super.setEmail(email); 
    }

    @Override
    public String toString() {
        return String.format("Student ID: %s | Name: %s | Programme: %s | Email: %s | Phone: %s", 
                getId(), getName(), programme, getEmail(), getPhoneNumber());
    }
}