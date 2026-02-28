
import boundary.MainMenu;
import control.StudentManagement;
import control.ScheduleManagement;
import control.BookingManagement;
import control.FacilityManagement;


public class TARUMTBookingSystem {

    public static void main(String[] args) {
        
        System.out.println("System Initializing... Please wait.");
        UtilitiesClass.DatabaseConnection.getConnection();
        StudentManagement studentManager = new StudentManagement();
        ScheduleManagement scheduleManager = new ScheduleManagement();
        FacilityManagement facilityManager = new FacilityManagement();
        BookingManagement bookingManager = new BookingManagement(studentManager, scheduleManager);
        
        MainMenu mainMenu = new MainMenu(studentManager, bookingManager, facilityManager, scheduleManager);
        
        mainMenu.start();
        
    }
}