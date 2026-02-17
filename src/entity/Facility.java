package entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Entity class representing a Facility in the system.
 * * @author user  
 */
public class Facility implements Serializable { 
    
    private String facilityId;
    private String name;
    private String type;      
    private int capacity;

    // Constructors
    public Facility() {
    }

    public Facility(String facilityId, String name, String type, int capacity) {
        this.facilityId = facilityId;
        this.name = name;
        this.type = type;
        this.capacity = capacity;
    }

    // Getters and Setters
    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.facilityId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Facility other = (Facility) obj;
        // Two facilities are considered equal if they have the same ID
        if (!Objects.equals(this.facilityId, other.facilityId)) {
            return false;
        }
        return true;
    }

    // 5. toString (For printing the object easily in your Boundary class)
    @Override
    public String toString() {
        // Formats the output neatly in columns
        return String.format("%-10s %-20s %-15s %5d", facilityId, name, type, capacity);
    }
}
