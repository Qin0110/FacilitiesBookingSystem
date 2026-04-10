/**
 * @author Ng Wei Hao
 */

package control;
import adt.SingleLinkedList;
import adt.ListInterface;
import entity.Admin;

import dao.AdminCollection;
import dao.BookingCollection;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AdminManagement {
    
    AdminCollection adminCollection = new AdminCollection();
    BookingCollection bookingCollection = new BookingCollection(); 
    
    public AdminManagement() {
    }

    public String addNewAdmin(String name, String position, String email, String phoneNumber) {
        String id = generateNextId();
        // Check if system is full
        if (id == null) {
            return "Error: Maximum staff limit (99) reached!";
        }
        
        ListInterface<Admin> adminList = this.getAllAdmins(); 
        
        // Loop to check for duplicates
        for (int i = 0; i < adminList.size(); i++) { 
        
            Admin existingAdmin = adminList.get(i);

            if (existingAdmin.getEmail().equalsIgnoreCase(email)) {
                return "Error: This Email is already registered to another Admin!"; 
            }
            if (existingAdmin.getPhoneNumber().equals(phoneNumber)) {
                return "Error: This Phone Number is already registered to another Admin!"; 
            }
        }

        // If no duplicates are found, create and save the admin
        try {
            Admin newAdmin = new Admin(id, name, position, email, phoneNumber);
            adminCollection.addAdmin(newAdmin);
             
            return "Admin added successfully! ID: " + id;
        } catch (IllegalArgumentException e) {
            // Catches format errors from Entity layer
            return "Error: " + e.getMessage();  
        }
    }
    
    public String generateNextId() {
        ListInterface<Admin> adminList = adminCollection.getAllAdmin();
        
        if (adminList.size() >= 99) { return null; } // Limit to 99

        if (adminList.isEmpty()) { return "A1001"; }

        int highestNum = 1000;
        for (int i = 0; i < adminList.size(); i++) {
            String currentId = adminList.get(i).getAdminId();
            try {
                int currentNum = Integer.parseInt(currentId.substring(1));
                if (currentNum > highestNum) { highestNum = currentNum; }
            } catch (Exception e) { /* Skip invalid formats */ }
        }

        return (highestNum >= 1099) ? null : "A" + (highestNum + 1);
    }

    public ListInterface<Admin> getAllAdmins() {
        return adminCollection.getAllAdmin();
    }

    public boolean removeAdmin(String adminId) {
        return adminCollection.removeAdmin(adminId);
    }

    public String updateAdmin(String id, String name, String position, String email, String phone) {

        ListInterface<Admin> adminList = this.getAllAdmins(); 
        
        // Loop through the list to check for duplicates
        for (int i = 0; i < adminList.size(); i++) {
            Admin existingAdmin = adminList.get(i);

            // Skip the admin info currently updating
            if (existingAdmin.getAdminId().equalsIgnoreCase(id)) {
                continue; 
            }

            // Checking other admin records (ID does not need checking since it cannot be edited)
            if (existingAdmin.getEmail().equalsIgnoreCase(email)) {
                return "Error: This Email is already registered to another Admin!"; 
            }
            if (existingAdmin.getPhoneNumber().equals(phone)) {
                return "Error: This Phone Number is already registered to another Admin!"; 
            }
        }

        // Update the admin
        try {
            Admin updatedAdmin = new Admin(id, name, position, email, phone);
            adminCollection.updateAdminInfo(updatedAdmin); 
            
            return updatedAdmin.getName() + " with Admin ID " + 
                   updatedAdmin.getAdminId  () + " has been successfully updated!";
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        }
    }
        
    public ListInterface<Admin> searchAdminForTable(String idOrName,String position, String sortBy, String sortDirection) {
        ListInterface<Admin> searchResults = new SingleLinkedList<>();
        ListInterface<Admin> allAdmin = adminCollection.getAllAdmin();
 
        for (int i = 0; i < allAdmin.size(); i++) {
            Admin admin = allAdmin.get(i);
 
            boolean matchesId   = admin.getAdminId().toLowerCase().contains(idOrName.toLowerCase())
                                || admin.getName().toLowerCase().contains(idOrName.toLowerCase());
            boolean matchesType = (position == null || position.equalsIgnoreCase("ALL") || admin.getPosition().equalsIgnoreCase(position));
 
            if (matchesId && matchesType) {
                searchResults.add(admin);
            }
        }
        
        // Sort the filtered list based on the combo box selection
        if (sortBy != null && !sortBy.trim().isEmpty()) {
            
            // Check want ascending or descending order
            boolean isAscending = sortDirection.equals("Ascending");
            
            searchResults.sort(new adt.Comparator<entity.Admin>() {
                @Override
                public int compare(entity.Admin a1, entity.Admin a2) {
                    int result = 0; 
                    switch (sortBy) {
                        case "Admin ID":
                            result = a1.getAdminId().compareToIgnoreCase(a2.getAdminId());
                            break;
                        case "Name":
                            result = a1.getName().compareToIgnoreCase(a2.getName());
                            break;
                        case "Position":
                            result = a1.getPosition().compareToIgnoreCase(a2.getPosition());
                            break;
                        case "Email":
                            result = a1.getEmail().compareToIgnoreCase(a2.getEmail());
                            break;
                        case "Phone Number":
                            result = a1.getPhoneNumber().compareToIgnoreCase(a2.getPhoneNumber());
                            break;
                        default:
                            result = 0; // Fallback just in case
                            break;
                    }
                    // If descending, just reverse result (multiply by -1)
                    return isAscending ? result : (result * -1);
                }
            });
        }
        
        return searchResults;
    }
    
    public Admin getAdminById(String adminId) {
        return adminCollection.getAdminById(adminId);
    }
    
    public String generateAdminReport() {
        adt.ListInterface<entity.Admin> allAdmins = adminCollection.getAllAdmin();

        int totalAdmins = allAdmins.size();

        int managerCount = 0;
        int assistantCount = 0;

        // Count the positions
        for (int i = 0; i < totalAdmins; i++) {
            String pos = allAdmins.get(i).getPosition();

            if (pos.equalsIgnoreCase("Facility Manager")) {
                managerCount++;
            } else if (pos.equalsIgnoreCase("Facility Assistant")) {
                assistantCount++;
            }
        }

        // Build the report text
        StringBuilder report = new StringBuilder();
        report.append("==================================================\n");
        report.append("          FACILITY STAFF SUMMARY REPORT           \n");
        report.append("==================================================\n\n");

        report.append("--- OVERALL STATISTICS ---\n");
        report.append("Total Staff in System     : ").append(totalAdmins).append("\n\n");

        report.append("--- POSITION BREAKDOWN ---\n");
        report.append(String.format("%-25s : %d\n", "Facility Manager", managerCount));
        report.append(String.format("%-25s : %d\n", "Facility Assistant", assistantCount));

        report.append("--- DETAILED STAFF LIST ---\n");
        report.append(String.format("%-15s %-25s %-20s\n", "STAFF ID", "NAME", "POSITION"));
        report.append("-----------------------------------------------------------------\n");

        for (int i = 0; i < totalAdmins; i++) {
            entity.Admin a = allAdmins.get(i);
            report.append(String.format("%-15s %-25s %-20s\n", 
                a.getAdminId(), a.getName(), a.getPosition()));
        }

        report.append("\n=================== END OF REPORT ===================");

        return report.toString();
    }
    
    public String generateDetailedReport(String adminId, LocalDate startDate, LocalDate endDate) {
        adt.ListInterface<entity.Booking> allBookings = bookingCollection.getAllBookings();
        
        entity.Admin admin = adminCollection.getAdminById(adminId); 

        if (admin == null) {
            return "Error: Admin ID " + adminId + " not found.";
        }

        StringBuilder report = new StringBuilder();
        report.append("=========================================================================\n");
        report.append("                     ADMIN PRODUCTIVITY LEDGER                           \n");
        report.append("=========================================================================\n");
        report.append("Admin Name   : ").append(admin.getName()).append("\n");
        report.append("Admin ID     : ").append(adminId).append("\n");
        report.append("Period       : ").append(startDate).append(" to ").append(endDate).append("\n\n");

        report.append(String.format("%-12s %-25s %-15s %-20s\n", "DATE", "FACILITY", "SLOT", "STUDENT ID"));
        report.append("-------------------------------------------------------------------------\n");

        boolean foundRecords = false;

        for (int i = 0; i < allBookings.size(); i++) {
            entity.Booking b = allBookings.get(i);

            if (b.getApprovedBy() != null && b.getApprovedBy().equalsIgnoreCase(adminId)) {
                entity.Schedule s = b.getSchedule();
                
                String realDate = b.getScheduleSession().substring(0, 10);
                LocalDate bDate = LocalDate.parse(realDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

                if (!bDate.isBefore(startDate) && !bDate.isAfter(endDate)) {

                    foundRecords = true;
                    
                    String[] timeStrings = s.getTimeString();
                    String slot = timeStrings[0] + " - " + timeStrings[1]; 

                    report.append(String.format("%-12s %-25s %-15s %-20s\n", 
                        realDate, s.getFacility().getName(), slot, b.getStudentId()));
                }
            }
        }

        if (!foundRecords) report.append("No bookings managed by this admin in the selected period.\n");
        report.append("\n=========================== END OF REPORT ===========================");

        return report.toString();
    }
    
    public String generateCrossPositionReport(LocalDate startDate, LocalDate endDate) {
        adt.ListInterface<entity.Booking> allBookings = bookingCollection.getAllBookings();

        int managerCount = 0; 
        int assisstantCount = 0; 
        
        for (int i = 0; i < allBookings.size(); i++) {
            entity.Booking b = allBookings.get(i);
            entity.Schedule s = b.getSchedule();
            String realDate = b.getScheduleSession().substring(0, 10);
            LocalDate bDate = LocalDate.parse(realDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            
            String status = b.getStatus(); 
            if (!status.equalsIgnoreCase("Completed") && !status.equalsIgnoreCase("In Progress")) {
                continue; 
            }
            
            if (!bDate.isBefore(startDate) && !bDate.isAfter(endDate)) {                
                
                if (b.getApprovedBy() != null && !b.getApprovedBy().equalsIgnoreCase("N/A")) {
                    entity.Admin admin = adminCollection.getAdminById(b.getApprovedBy());
                    
                    if (admin != null) {
                        String position = admin.getPosition();
                        
                        if (position.equalsIgnoreCase("Facility Manager")) managerCount++;
                        else if (position.equalsIgnoreCase("Facility Assistant")) assisstantCount++;
                    }
                }
            }
        }

        StringBuilder report = new StringBuilder();
        report.append("==================================================\n");
        report.append("             ADMIN POSITION WORKLOAD REPORT       \n");
        report.append("    Period: ").append(startDate).append(" to ").append(endDate).append("\n");
        report.append("==================================================\n\n");

        report.append(String.format("%-25s %-15s\n", "POSITION", "TOTAL BOOKINGS HANDLED"));
        report.append("--------------------------------------------------\n");
        report.append(String.format("%-25s %-15d\n", "Facility Manager", managerCount));
        report.append(String.format("%-25s %-15d\n", "Facility Assisstant", assisstantCount));

        report.append("\n=================== END OF REPORT ===================");
        return report.toString();
    }
}
