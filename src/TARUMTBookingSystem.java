
import boundary.MainMenu;
import control.AdminManagement;
import control.StudentManagement;
import control.ScheduleControl;
import control.BookingManagement;
import control.FacilityManagement;
import java.sql.SQLException;


public class TARUMTBookingSystem {
    public static void main(String[] args) throws SQLException {
        
        System.out.println("System Initializing... Please wait.");
        UtilitiesClass.DatabaseConnection.getConnection();
        AdminManagement adminManager = new AdminManagement();
        StudentManagement studentManager = new StudentManagement();
        ScheduleControl scheduleManager = new ScheduleControl();
        FacilityManagement facilityManager = new FacilityManagement();
        BookingManagement bookingManager = new BookingManagement(studentManager, scheduleManager);

        MainMenu mainMenu = new MainMenu(adminManager, studentManager, bookingManager, facilityManager, scheduleManager);
        mainMenu.setVisible(true);
    
        
    }
}