package entity;

public class Student extends User {
    private String programme; 

    public Student(String studentId, String name, String programme, String email, String phoneNumber) {
        super(studentId, name, email, phoneNumber); 
        this.programme = programme;
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
    public String toString() {
        return String.format("Student ID: %s | Name: %s | Programme: %s | Email: %s | Phone: %s", 
                getId(), getName(), programme, getEmail(), getPhoneNumber());
    }
}