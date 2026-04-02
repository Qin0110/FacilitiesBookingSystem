/**
 * @author Ng Wei Hao
 */


package entity;
import java.io.Serializable;

public class ScheduleSession implements Serializable{
    public static final String STATUS_AVAILABLE = "Available";
    public static final String STATUS_BOOKED = "Booked";
    public static final String STATUS_MAINTENANCE = "Maintenance";
    public static final String STATUS_NOT_AVAILABLE = "Not Available";
    
    private String session;
    private String availability;
    
    public ScheduleSession(String session){
        this.session = session;
        this.availability = STATUS_AVAILABLE;
    }
    
    public ScheduleSession(String session, String availability){
        this.session = session;
        this.availability = availability;
    }
    
    public void setSession(String session){
        this.session = session;
    }
    
    public void setAvailability(String availability) {
        this.availability = availability;
    }
    
    public String getSession() {
        return this.session;
    }

    public String getAvailability() {
        return this.availability;
    }
    
    public String toString() {
        return "[" + session + " : " + availability + "]";
    }
}
