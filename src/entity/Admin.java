package entity;

import java.io.Serializable;
import java.util.Objects;


public class Admin implements Serializable {

    private String adminId;  
    private String name;
    private String position;
    private String email;
    private String phoneNumber;

    public Admin(String adminId, String name, String position, String email,String phoneNumber) {
        this.adminId = adminId;
        this.name = name;
        this.position = position;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getAdminId() { 
        return adminId; 
    }
    
    public String getName() { 
        return name; 
    }
    
    public String getPosition() { 
        return position; 
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
    
    public void setPosition(String position) {
        this.position = position; 
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
        Admin admin = (Admin) obj;
        return adminId.equals(admin.adminId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adminId);
    }

    @Override
    public String toString() {
        return String.format("Admin ID: %s | Name: %s | Position: %s| Email: %s| Phone Number: %s", 
                adminId, name, position, email, phoneNumber);
    }
}