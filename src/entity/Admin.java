package entity;

public class Admin extends User {
    private String position;

    public Admin(String adminId, String name, String position, String email, String phoneNumber) {
        super(adminId, name, email, phoneNumber);
        this.position = position;
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
    public String toString() {
        return String.format("Admin ID: %s | Name: %s | Position: %s | Email: %s | Phone: %s", getId(), getName(), position, getEmail(), getPhoneNumber());
    }
}