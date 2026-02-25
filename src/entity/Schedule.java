
package entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;


public class Schedule {
    private String scheduleId;   
    private String facilityId;   
    
    private LocalDateTime startingTime;
    private LocalDateTime endingTime;
    private String availability;  

    public Schedule(String scheduleId, String facilityId, LocalDateTime startingTime, LocalDateTime endingTime) {
        this.scheduleId = scheduleId;
        this.facilityId = facilityId;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        this.availability = "Available"; 
    }

    public String getScheduleId() { 
        return scheduleId; 
    }
    
    public String getFacilityId() { 
        return facilityId; 
    }
    
    public LocalDateTime getStartingTime() { 
        return startingTime; 
    }
    
    public LocalDateTime getEndingTime() { 
        return endingTime; 
    }
    
    public String getAvailability() { 
        return availability; 
    }

    public void setStartingTime(LocalDateTime startingTime) { 
        this.startingTime = startingTime; 
    }
    
    public void setEndingTime(LocalDateTime endingTime) {
        this.endingTime = endingTime; 
    }
    
    public void setAvailability(String availability) {
        this.availability = availability; 
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Schedule schedule = (Schedule) obj;
        return scheduleId.equals(schedule.scheduleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scheduleId);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format("Schedule ID: %s | Facility ID: %s | Start: %s | End: %s | Status: %s", 
                scheduleId, 
                facilityId, 
                startingTime.format(formatter), 
                endingTime.format(formatter), 
                availability);
    }
}
