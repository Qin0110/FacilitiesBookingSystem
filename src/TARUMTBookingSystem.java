
import boundary.MainMenu;
import control.AdminManagement;
import control.StudentManagement;
import control.ScheduleControl;
import control.BookingManagement;
import control.FacilityManagement;
import java.sql.SQLException;


public class TARUMTBookingSystem {
    public static void main(String[] args) throws SQLException {
        
        AdminManagement adminManager = new AdminManagement();
        StudentManagement studentManager = new StudentManagement();
        FacilityManagement facilityManager = new FacilityManagement();

        ScheduleControl scheduleManager = new ScheduleControl();
        BookingManagement bookingManager = new BookingManagement(studentManager, scheduleManager, adminManager);

        MainMenu mainMenu = new MainMenu(adminManager, studentManager, bookingManager, facilityManager, scheduleManager);
        mainMenu.setVisible(true);
    
        
    }
}