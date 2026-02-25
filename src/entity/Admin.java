package entity;

import java.io.Serializable;
import java.util.Objects;


public class Admin implements Serializable {

    private String adminId;  
    private String name;
    private String position;

    public Admin(String adminId, String name, String position) {
        this.adminId = adminId;
        this.name = name;
        this.position = position;
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

    public void setName(String name) { 
        this.name = name; 
    }
    
    public void setPosition(String position) {
        this.position = position; 
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
        return String.format("Admin ID: %s | Name: %s | Position: %s", 
                adminId, name, position);
    }
}