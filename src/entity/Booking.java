
package entity;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Booking implements Serializable {
    
    private String facilityId;   
    private String studentId;    
    private String scheduleId;   
    
    private String approvedBy;   
    private LocalDateTime arrivalTime;
    private LocalDateTime leavingTime;
    private String status;     

    public Booking(String facilityId, String studentId, String scheduleId, LocalDateTime arrivalTime, LocalDateTime leavingTime) {
        this.facilityId = facilityId;
        this.studentId = studentId;
        this.scheduleId = scheduleId;
        this.arrivalTime = arrivalTime;
        this.leavingTime = leavingTime;
        this.status = "Pending";
        this.approvedBy = "N/A"; 
    }

    public String getFacilityId() { 
        return facilityId; 
    }
    
    public String getStudentId() {
        return studentId; 
    }
    
    public String getScheduleId() {
        return scheduleId; 
    }
    
    public LocalDateTime getArrivalTime() {
        return arrivalTime; 
    }
    
    public LocalDateTime getLeavingTime() { 
        return leavingTime; 
    }
    
    public String getStatus() { 
        return status; 
    }
    
    public String getApprovedBy() { 
        return approvedBy; 
    }
   
    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime; 
    }
    
    public void setLeavingTime(LocalDateTime leavingTime) {
        this.leavingTime = leavingTime; 
    }
    
    public void setStatus(String status) { 
        this.status = status; 
    }
    
    public void setApprovedBy(String adminId) { 
        this.approvedBy = adminId; 
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Booking booking = (Booking) obj;
        // The booking is only considered a duplicate if ALL THREE keys match exactly
        return facilityId.equals(booking.facilityId) &&
               studentId.equals(booking.studentId) &&
               scheduleId.equals(booking.scheduleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(facilityId, studentId, scheduleId);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format("Facility: %s | Student: %s | Schedule: %s | Arrival: %s | Leaving: %s | Status: %s | Approved By: %s", 
                facilityId, 
                studentId, 
                scheduleId,
                arrivalTime.format(formatter), 
                leavingTime.format(formatter), 
                status, 
                approvedBy);
    }
}