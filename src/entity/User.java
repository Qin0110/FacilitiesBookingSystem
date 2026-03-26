package entity;

import java.io.Serializable;
import java.util.Objects;


public abstract class User implements Serializable {
    private String id; 
    private String name;
    private String email;
    private String phoneNumber;

    public User(String id, String name, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        setEmail(email); 
        setPhoneNumber(phoneNumber);
    }

    public String getId() { 
        return id; 
    }
    
    public String getName() { 
        return name; 
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
    
    public void setEmail(String email) {
       if (email == null || !email.matches("^[A-Za-z0-9]+@[A-Za-z0-9]+\\.[A-Za-z0-9.]+$")) {
            throw new IllegalArgumentException("Invalid email format. Example: user@example.com");
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
        User user = (User) obj;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}