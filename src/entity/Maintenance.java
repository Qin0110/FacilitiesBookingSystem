package entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Maintenance implements Serializable {
    private String maintenanceId; 
    private String facilityId;   
    private String adminId;      
    private String description;   
    private LocalDateTime startingTime;
    private LocalDateTime endingTime;

    public Maintenance(String maintenanceId, String facilityId, String adminId, String description, LocalDateTime startingTime, LocalDateTime endingTime) {
        this.maintenanceId = maintenanceId;
        this.facilityId = facilityId;
        this.adminId = adminId;
        this.description = description;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
    }

    public String getMaintenanceId() { 
        return maintenanceId; 
    }
    
    public String getFacilityId() { 
        return facilityId; 
    }
    
    public String getAdminId() { 
        return adminId; 
    }
    
    public String getDescription() {
        return description; 
    }
    
    public LocalDateTime getStartingTime() {
        return startingTime; 
    }
    
    public LocalDateTime getEndingTime() {
        return endingTime; 
    }

    public void setDescription(String description) {
        this.description = description; }
    
    public void setStartingTime(LocalDateTime startingTime) {
        this.startingTime = startingTime; 
    }
    
    public void setEndingTime(LocalDateTime endingTime) {
        this.endingTime = endingTime; 
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Maintenance that = (Maintenance) obj;
        return maintenanceId.equals(that.maintenanceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maintenanceId);
    }
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format("Maintenance ID: %s | Facility ID: %s | Approve By: %s | Description: %s | Starting Time: %s | Ending Time: %s", 
                maintenanceId, 
                facilityId, 
                adminId,
                description,
                startingTime.format(formatter), 
                endingTime.format(formatter));
    }
}