
package entity;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Booking implements Serializable {
    
    private String bookingId;
    private String studentId;    
//    private String scheduleId;
    private String approvedBy;   
    private Schedule schedule;
    
    private LocalDateTime arrivalTime;
    private LocalDateTime leavingTime;
    private String status;
    
    private String bookingDate;
    private String bookingSession;

    public Booking(String bookingId, String studentId, Schedule schedule, int sessionIndex, LocalDateTime arrivalTime, LocalDateTime leavingTime) {
        this.bookingId = bookingId;
        this.studentId = studentId;
        this.schedule = schedule;
        this.arrivalTime = arrivalTime;
        this.leavingTime = leavingTime;
        this.status = "Pending";
        this.approvedBy = "N/A"; 
        this.bookingDate = schedule.getDate();
        this.bookingSession = schedule.getSessions().get(sessionIndex).getSession();
    }

    public String getBookingId() { 
        return bookingId; 
    }
    
    public String getStudentId() {
        return studentId; 
    }
    
    public String getScheduleSession() {
        return this.bookingDate  + " " + this.bookingSession; 
    }
    
    public Schedule getSchedule(){
        return this.schedule;
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
        return this.bookingId.equals(booking.bookingId) &&
               this.studentId.equals(booking.studentId) &&
               this.bookingSession.equals(booking.bookingSession) && // Schedule ID to booking session + booking Date
               this.bookingDate.equals(booking.bookingDate); // Facility name no need (?) since passed in Schedule object contains facility
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId, studentId, this.bookingDate + " " + this.bookingSession);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        
        String arrivalStr = (arrivalTime != null) ? arrivalTime.format(formatter) : "Not Arrived";
        String leavingStr = (leavingTime != null) ? leavingTime.format(formatter) : "Not Left";

        return String.format("Booking ID: %s | Student: %s | Schedule: %s | Arrival: %s | Leaving: %s | Status: %s | Approved By: %s", 
                bookingId, 
                studentId, 
                this.bookingDate + " " + this.bookingSession,
                arrivalStr, 
                leavingStr, 
                status, 
                approvedBy == null ? "N/A" : approvedBy);
    }
}