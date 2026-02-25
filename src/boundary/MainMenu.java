package boundary;

import UtilitiesClass.Input;
import control.BookingManagement;
import control.StudentManagement;
import control.ScheduleManagement;
import java.util.HashMap;
import java.util.Map;

public class MainMenu {

    private Map<Integer, Runnable> menuRouter;
    
    private StudentUI studentUI;
    private AdminUI adminUI;

    public MainMenu(StudentManagement studentControl, BookingManagement bookingControl) {
        this.studentUI = new StudentUI(bookingControl);
        this.adminUI = new AdminUI(studentControl, bookingControl);
        
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
        adminUI.start();   
    }
}