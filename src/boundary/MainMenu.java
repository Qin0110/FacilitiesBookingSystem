package boundary;

import UtilitiesClass.Input;
import control.BookingManagement;
import control.AdminManagement;
import control.StudentManagement;
import control.ScheduleManagement;
import control.FacilityManagement;
import java.util.HashMap;
import java.util.Map;

public class MainMenu {

    private Map<Integer, Runnable> menuRouter;
    
    private FacilityManagement facilityManager;
    private StudentManagement studentManager;
    private AdminManagement adminManager;
    private BookingManagement bookingManager;
    private ScheduleManagement scheduleManager;
    
    private StudentUI studentUI;
    private AdminUI adminUI;

    public MainMenu(AdminManagement adminManager, StudentManagement studentManager, BookingManagement bookingManager, FacilityManagement facilityManager, ScheduleManagement scheduleManager) {
        this.studentUI = new StudentUI(bookingManager, scheduleManager, facilityManager);
        this.adminUI = new AdminUI(adminManager, studentManager, bookingManager, facilityManager, scheduleManager);
        
        this.studentManager = studentManager;
        this.adminManager = adminManager;
        this.bookingManager = bookingManager;
        this.facilityManager = facilityManager;
        this.scheduleManager = scheduleManager;
        
        menuRouter = new HashMap<>();
        menuRouter.put(1, this::launchStudentMenu);
        menuRouter.put(2, this::launchAdminMenu);
    }

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\n==========================================");
            System.out.println("   TARUMT FACILITIES BOOKING SYSTEM       ");
            System.out.println("==========================================");
            System.out.println("1. Student Access");
            System.out.println("2. Administrator Access");
            System.out.println("0. Exit System");
            System.out.println("==========================================");
            
            int choice = Input.getInt("Select your role: ");

            if (choice == 0) {
                running = false;
                System.out.println("Ending System... ");
                
            } else {
                Runnable action = menuRouter.get(choice);
                if (action != null) {
                    action.run(); 
                } else {
                    System.out.println("Error! Invalid choice. Try again.");
                }
            }
        }
    }

    private void launchStudentMenu() {
        studentUI.start();
    }

    private void launchAdminMenu() {
        AdminUI adminUI = new AdminUI(adminManager, studentManager, bookingManager, facilityManager, scheduleManager);  
        adminUI.setLocationRelativeTo(null);
        adminUI.setVisible(true);
    }
}