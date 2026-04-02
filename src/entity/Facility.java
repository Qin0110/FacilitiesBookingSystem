/**
 * @author Lee Guan Wei
 */


package entity;

import java.io.Serializable;
import java.util.Objects;


public class Facility implements Serializable { 
    
    private String facilityId;
    private String name;
    private String type;      
    private String location;
    private int minCapacity;
    private int maxCapacity;

    public Facility() {
    }

    public Facility(String facilityId, String name, String type, String location, int minCapacity, int maxCapacity) {
        this.facilityId = facilityId;
        this.name = name;
        this.type = type;
        this.location = location;
        this.minCapacity = minCapacity;
        this.maxCapacity = maxCapacity; 
    }

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
    
    public String getLocation(){
        return location;
    }
    
    public void setLocation(String location){
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMinCapacity() {
        return minCapacity;
    }

    public void setMinCapacity(int minCapacity) {
        this.minCapacity = minCapacity;
    }
    
    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
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
        if(!Objects.equals(this.facilityId, other.facilityId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-20s %-15s %5d", facilityId, name, type, minCapacity, maxCapacity);
    }
}
