package entity;

public class Admin extends User {
    private String position;

    public Admin(String adminId, String name, String position, String email, String phoneNumber) {
        super(name, phoneNumber);
        this.setId(adminId);
        this.position = position;
        this.setEmail(email);
    }

    public String getPosition() {
        return position; 
    }
    
    public void setPosition(String position) {
        this.position = position; 
    }
    
    public String getAdminId() { 
        return super.getId(); 
    }
    
    @Override
    public void setId(String id) {
        if (id == null || !id.matches("^A[0-9]{4}$")) {
            throw new IllegalArgumentException("Invalid Admin ID. Example: A1001");
        }
        super.setId(id);
    }

    @Override
    public void setEmail(String email) {
        if (email == null || !email.matches("^[A-Za-z0-9._]+@tarc\\.edu\\.my$")) {
            throw new IllegalArgumentException("Invalid admin email. Must use official @tarc.edu.my staff email.");
        }
        
        // after passes, let the User class save it
        super.setEmail(email); 
    }

    @Override
    public String toString() {
        return String.format("Admin ID: %s | Name: %s | Position: %s | Email: %s | Phone: %s", getId(), getName(), position, getEmail(), getPhoneNumber());
    }
}