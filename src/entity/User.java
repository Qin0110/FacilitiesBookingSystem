/**
 * @author Ng Wei Hao
 */


package entity;

import java.io.Serializable;
import java.util.Objects;


public abstract class User implements Serializable {
    private String id; 
    private String name;
    private String email;
    private String phoneNumber;

    public User(String name, String phoneNumber) {
        this.setName(name);
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

    public void setId(String id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be empty.");
        }
        this.id = id; // Actually saves the data into memory
    }
    
    public void setName(String name) {
        // Check if it's completely empty first
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }

        // Check for numbers and invalid symbols
        if (!name.matches("^[a-zA-Z\\s\\.\\'\\-\\/]+$")) {
            throw new IllegalArgumentException("Name can only contain letters, spaces, and valid punctuation (., -, ', /). No numbers allowed.");
        }

        // Save the name (using .trim() to clean up extra spaces at the ends)
        this.name = name.trim(); 
    }
    
    public void setEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Email cannot be empty.");
        }
        this.email = email; // Saves the data
    }
    
    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || !phoneNumber.matches("^01[0-9]-[0-9]{7,8}$")) {
            throw new IllegalArgumentException("Invalid phone number format. Example: 012-3456789 or 011-23456789");
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