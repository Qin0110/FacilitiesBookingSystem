
import boundary.MainMenu;
import control.StudentManagement;
import control.ScheduleManagement;
import control.BookingManagement;


public class TARUMTBookingSystem {

    public static void main(String[] args) {
        
        System.out.println("System Initializing... Please wait.");

        StudentManagement studentManager = new StudentManagement();
        ScheduleManagement scheduleManager = new ScheduleManagement();
        
        BookingManagement bookingManager = new BookingManagement(studentManager, scheduleManager);
        
        MainMenu mainMenu = new MainMenu(studentManager, bookingManager);
        
        mainMenu.start();
        
    }
}