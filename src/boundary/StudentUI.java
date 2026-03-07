package boundary;

import UtilitiesClass.Input;
import control.BookingManagement;
import control.ScheduleManagement;
import control.FacilityManagement;
import java.util.HashMap;
import java.util.Map;

public class StudentUI {

    private BookingManagement bookingManager;
    private ScheduleManagement scheduleManager;
    private FacilityManagement facilityManager;
    private Map<Integer, Runnable> actions;

    public StudentUI(BookingManagement bookingControl, ScheduleManagement scheduleManager, FacilityManagement facilityManager) {
        this.bookingManager = bookingControl;
        this.scheduleManager = scheduleManager;
        this.facilityManager = facilityManager;
        
        actions = new HashMap<>();
        actions.put(1, this::uiBrowseFacilities);
        actions.put(2   , this::uiMakeBooking);
        actions.put(3, this::uiViewHistory);
        actions.put(4, this::uiCancelBooking);
    }

    public void start() {
        boolean inStudentMenu = true;
        while (inStudentMenu) {
            System.out.println("\n==========================================");
            System.out.println("             Student Menu            ");
            System.out.println("==========================================");
            System.out.println("1. Browse Available Facilities & Schedules");
            System.out.println("2. Make a New Booking");
            System.out.println("3. View My Booking History");
            System.out.println("4. Cancel a Booking");
            System.out.println("0. Logout (Return to Main Menu)");
            System.out.println("==========================================");
            
            int choice = Input.getInt("Enter your choice: ");

            if (choice == 0) {
                inStudentMenu = false; 
            } else {
                Runnable action = actions.get(choice);
                if (action != null) {
                    action.run(); 
                    Input.pause();
                } else {
                    System.out.println("ERROR: Invalid selection.");
                }
            }
        }
    }

    private void uiBrowseFacilities() {
        System.out.println("\n--- Available Facilities ---");
        
        //havnet complete
    }

    private void uiMakeBooking() {
        new boundary.BookingUI(bookingManager, scheduleManager, facilityManager).setVisible(true);
    }

    private void uiViewHistory() {
        new boundary.BookingHistoryAndCancel(bookingManager, scheduleManager).setVisible(true);
    }

    private void uiCancelBooking() {
        new boundary.BookingHistoryAndCancel(bookingManager, scheduleManager).setVisible(true);
    }
}