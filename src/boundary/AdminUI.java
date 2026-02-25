package boundary;

import UtilitiesClass.Input;
import control.BookingManagement;
import control.StudentManagement; 

import java.util.HashMap;
import java.util.Map;


public class AdminUI {

    // Dependencies (The Control classes)
    private StudentManagement studentControl;
    private BookingManagement bookingControl;

    // Advanced Routers
    private Map<Integer, Runnable> mainActions;
    private Map<Integer, Runnable> userMenuActions;

    public AdminUI(StudentManagement studentControl, BookingManagement bookingControl) {
        this.studentControl = studentControl;
        this.bookingControl = bookingControl;
        
        mainActions = new HashMap<>();
        mainActions.put(1, this::uiUserManagement);
        mainActions.put(2, this::uiFacilityManagement);
        mainActions.put(3, this::uiScheduleManagement);
        mainActions.put(4, this::uiMaintenanceManagement);
        mainActions.put(5, this::uiBookingManagement);
        mainActions.put(6, this::uiGenerateReports);

        userMenuActions = new HashMap<>();
        userMenuActions.put(1, this::actionRegisterStudent);
        userMenuActions.put(2, this::actionUpdateProgramme);
        userMenuActions.put(3, this::actionDeleteStudent);
        userMenuActions.put(4, this::actionViewAllStudents);
    }

    public void start() {
        boolean inAdminMenu = true;
        while (inAdminMenu) {
            System.out.println("\n==========================================");
            System.out.println("        Administrator Menu           ");
            System.out.println("==========================================");
            System.out.println("1. User Management (Students & Staff)");
            System.out.println("2. Facility Management");
            System.out.println("3. Schedule Management");
            System.out.println("4. Maintenance Management");
            System.out.println("5. Booking & Waitlist Override");
            System.out.println("6. Generate System Reports");
            System.out.println("0. Logout (Return to Main Menu)");
            System.out.println("==========================================");
            
            int choice = Input.getInt("Enter your choice: ");

            if (choice == 0) {
                inAdminMenu = false; 
            } else {
                Runnable action = mainActions.get(choice);
                if (action != null) {
                    action.run(); 
                } else {
                    System.out.println("Error! Invalid selection. Try again.");
                }
            }
        }
    }

    
    private void uiUserManagement() {
        boolean inUserMenu = true;
        while (inUserMenu) {
            System.out.println("\n--- USER MANAGEMENT ---");
            System.out.println("1. Register a New Student");
            System.out.println("2. Update Student Programme");
            System.out.println("3. Delete a Student Record");
            System.out.println("4. View All Students");
            System.out.println("0. Back to Admin Dashboard");
            
            int choice = Input.getInt("Select an action: ");
            
            if (choice == 0) {
                inUserMenu = false; 
            } else {
                Runnable action = userMenuActions.get(choice);
                if (action != null) {
                    action.run();
                    Input.pause();
                } else {
                    System.out.println("Error! Invalid selection.");
                }
            }
        }
    }

    private void uiFacilityManagement() {
        //havent complete
    }

    private void uiScheduleManagement() {
        //havent complete

    }

    private void uiMaintenanceManagement() {
        //havent complete

    }

    private void uiBookingManagement() {
        //havent complete

    }

    private void uiGenerateReports() {
        //havent complete

    }

    private void actionRegisterStudent() {
        //havent complete

    }

    private void actionUpdateProgramme() {
        //havent complete

    }

    private void actionDeleteStudent() {
        //havent complete

    }

    private void actionViewAllStudents() {
        //havent complete

    }
}