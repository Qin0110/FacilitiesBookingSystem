/**
 * @author Ng Wei Hao
 */

package control;
import adt.SingleLinkedList;
import adt.ListInterface;
import entity.Student;

import dao.StudentCollection;
import dao.BookingCollection;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StudentManagement {
    
    StudentCollection studentCollection = new StudentCollection();
    BookingCollection bookingCollection = new BookingCollection(); 
    public StudentManagement() {}
    
    public String addNewStudent(String name, String programme, String email, String phoneNumber) {
         String id = generateNextStudentId();
         if (id == null) {
            return "Error: Maximum student limit (9999) reached! Cannot add more students.";
         }
         
         ListInterface<Student> studentList = this.getAllStudents(); 

         // Loop through the list to check for duplicates
         for (int i = 0; i < studentList.size(); i++) {
             Student existingStudent = studentList.get(i);
             
             if (existingStudent.getEmail().equalsIgnoreCase(email)) {
                 return "Error: This Email is already registered!"; 
             }
             if (existingStudent.getPhoneNumber().equals(phoneNumber)) {
                 return "Error: This Phone Number is already registered!"; 
             }
         }

         // If no duplicates are found, create and save the student
         try {
             Student newStudent = new Student(id, name, programme, email, phoneNumber);
             studentCollection.addStudent(newStudent);
             return "Student added successfully! ID: " + id;
         } catch (IllegalArgumentException e) {
             return "Error: " + e.getMessage(); 
         }
    }
    
    public String generateNextStudentId() {
        ListInterface<Student> studentList = studentCollection.getAllStudent();
        String prefix = "25WMR";
        
        if (studentList.size() >= 9999) {
            return null; 
        }

        if (studentList.isEmpty()) {
            return prefix + "00001";
        }

        int highestNum = 0;

        for (int i = 0; i < studentList.size(); i++) {
            String currentId = studentList.get(i).getStudentId();
            try {
                // Extract after "25WMR" (length is 5)
                int currentNum = Integer.parseInt(currentId.substring(5));
                if (currentNum > highestNum) {
                    highestNum = currentNum;
                }
            } catch (Exception e) { }// Skip invalid formats 
        }

        if (highestNum >= 9999) return null;

        // Generate ID with leading zeros 
        return prefix + String.format("%05d", highestNum + 1);
    }

    public ListInterface<Student> getAllStudents() {
        return studentCollection.getAllStudent();
    }

    public boolean removeStudent(String studentId) {
        return studentCollection.removeStudent(studentId);
    }

    public String updateStudent(String id, String name, String programme, String email, String phone) {

        ListInterface<Student> studentList = this.getAllStudents(); 
    
        // Loop through the list to check for duplicates
        for (int i = 0; i < studentList.size(); i++) {
            Student existingStudent = studentList.get(i);

            //  Skip the student info currently updating
            if (existingStudent.getStudentId().equalsIgnoreCase(id)) {
                continue; 
            }

            // checking other student records, id no need check since it cannot edit
            if (existingStudent.getEmail().equalsIgnoreCase(email)) {
                return "Error: This Email is already registered to another student!"; 
            }
            if (existingStudent.getPhoneNumber().equals(phone)) {
                return "Error: This Phone Number is already registered to another student!"; 
            }
        }

        // update the student
        try {
            Student updatedStudent = new Student(id, name, programme, email, phone);
            studentCollection.updateStudentInfo(updatedStudent); 
            return updatedStudent.getName() + " with Student ID " + 
                   updatedStudent.getStudentId() + " has been successfully updated!";
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        }
    }
    
    public ListInterface<Student> searchStudentForTable(String idOrName,String programme, String sortBy, String sortDirection) {
        ListInterface<Student> searchResults = new SingleLinkedList<>();
        ListInterface<Student> allStudent = studentCollection.getAllStudent();
 
        for (int i = 0; i < allStudent.size(); i++) {
            Student student = allStudent.get(i);
 
            boolean matchesId   = student.getStudentId().toLowerCase().contains(idOrName.toLowerCase())
                                || student.getName().toLowerCase().contains(idOrName.toLowerCase());
            boolean matchesType = (programme == null || programme.equalsIgnoreCase("ALL") || student.getProgramme().equalsIgnoreCase(programme));
 
            if (matchesId && matchesType) {
                searchResults.add(student);
            }
        }
        // Sort the filtered list based on the combo box selection
        if (sortBy != null && !sortBy.trim().isEmpty()) {
            
            // Check want ascending or descending order
            boolean isAscending = sortDirection.equals("Ascending");
            
            searchResults.sort(new adt.Comparator<entity.Student>() {
                @Override
                public int compare(entity.Student s1, entity.Student s2) {
                    int result = 0; 
                    switch (sortBy) {
                        case "Student ID":
                            result = s1.getStudentId().compareToIgnoreCase(s2.getStudentId());
                            break;
                        case "Name":
                            result = s1.getName().compareToIgnoreCase(s2.getName());
                            break;
                        case "Programme":
                            result = s1.getProgramme().compareToIgnoreCase(s2.getProgramme());
                            break;
                        case "Email":
                            result = s1.getEmail().compareToIgnoreCase(s2.getEmail());
                            break;
                        case "Phone Number":
                            result = s1.getPhoneNumber().compareToIgnoreCase(s2.getPhoneNumber());
                            break;
                    }
                    
                    // If descending, just reverse result (multiply by -1)
                    return isAscending ? result : (result * -1);
                }
            });
        }

        return searchResults;
    }
    
    public Student getStudentById(String studentId) {
            return studentCollection.getStudentById(studentId);
    }
    
    public boolean validateMember(String studentId, String studentName) {
        Student s = this.getStudentById(studentId);
        
        if (s != null && s.getName().equalsIgnoreCase(studentName.trim())) {
            return true; 
        }
        return false; 
    }
    
    public String generateStudentReport() {
        adt.ListInterface<entity.Student> allStudents = studentCollection.getAllStudent();

        int totalStudents = allStudents.size();
        
        int dataScienceCount = 0;
        int softwareEngCount = 0;
        int softwareDevCount = 0;

        for (int i = 0; i < totalStudents; i++) {
            String prog = allStudents.get(i).getProgramme();
            if (prog.equalsIgnoreCase("Data Science")) dataScienceCount++;
            else if (prog.equalsIgnoreCase("Software Engineering")) softwareEngCount++;
            else if (prog.equalsIgnoreCase("Software Developer")) softwareDevCount++;
        }

        StringBuilder report = new StringBuilder();
        report.append("==================================================\n");
        report.append("             STUDENT SUMMARY REPORT               \n");
        report.append("==================================================\n\n");

        report.append("--- OVERALL STATISTICS ---\n");
        report.append("Total Students Registered : ").append(totalStudents).append("\n\n");

        report.append("--- PROGRAMME BREAKDOWN ---\n");
        report.append("Data Science              : ").append(dataScienceCount).append("\n");
        report.append("Software Engineering      : ").append(softwareEngCount).append("\n");
        report.append("Software Developer        : ").append(softwareDevCount).append("\n\n");

        report.append("--- DETAILED STUDENT LIST ---\n");
        report.append(String.format("%-15s %-25s %-25s\n", "STUDENT ID", "NAME", "PROGRAMME"));
        report.append("-----------------------------------------------------------------\n");

        for (int i = 0; i < totalStudents; i++) {
            entity.Student s = allStudents.get(i);
            report.append(String.format("%-15s %-25s %-25s\n", 
                s.getStudentId(), s.getName(), s.getProgramme()));
        }

        report.append("\n=================== END OF REPORT ===================");

        return report.toString();
    }
    
    public String generateSummaryReport(LocalDate startDate, LocalDate endDate) {
        adt.ListInterface<entity.Booking> allBookings = bookingCollection.getAllBookings(); 

        int totalBookingsInRange = 0;
        int sportsCount = 0;
        int studyRoomCount = 0;
        int libraryStudyRoomCount = 0;
        int eventSpaceCount = 0;
        int cyberCentreDiscussionRoomCount = 0;
        int RecreationCount = 0;
               
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
                
                totalBookingsInRange++;
                String facilityType = s.getFacility().getType(); 

                if (facilityType.equalsIgnoreCase("Sports")) sportsCount++;
                else if (facilityType.equalsIgnoreCase("Study Room")) studyRoomCount++;
                else if (facilityType.equalsIgnoreCase("Library Study Room")) libraryStudyRoomCount++;
                else if (facilityType.equalsIgnoreCase("Cyber Centre Discussion Room")) cyberCentreDiscussionRoomCount++;
                else if (facilityType.equalsIgnoreCase("Event Space")) eventSpaceCount++;   
                else if (facilityType.equalsIgnoreCase("Recreation")) RecreationCount++;
            }
        }

        StringBuilder report = new StringBuilder();
        report.append("==================================================\n");
        report.append("       FACILITY UTILIZATION SUMMARY REPORT        \n");
        report.append("    Period: ").append(startDate).append(" to ").append(endDate).append("\n");
        report.append("==================================================\n\n");

        report.append("--- OVERALL STATISTICS ---\n");
        report.append("Total Facilities Booked : ").append(totalBookingsInRange).append("\n\n");

        report.append("--- BREAKDOWN BY FACILITY TYPE ---\n");
        report.append(String.format("%-20s : %d\n", "Sports Facilities", sportsCount));
        report.append(String.format("%-20s : %d\n", "Study Rooms", studyRoomCount));
        report.append(String.format("%-20s : %d\n", "Library Study Room", libraryStudyRoomCount));
        report.append(String.format("%-20s : %d\n", "Cyber Centre Discussion Room", cyberCentreDiscussionRoomCount));
        report.append(String.format("%-20s : %d\n", "Event Space", eventSpaceCount));
        report.append(String.format("%-20s : %d\n", "Recreation", RecreationCount));


        report.append("\n=================== END OF REPORT ===================");
        return report.toString();
    }
    
    public String generateDetailedReport(String studentId, LocalDate startDate, LocalDate endDate) {
        adt.ListInterface<entity.Booking> allBookings = bookingCollection.getAllBookings();
        entity.Student student = studentCollection.getStudentById(studentId); 

        if (student == null) {
            return "Error: Student ID " + studentId + " not found.";
        }

        StringBuilder report = new StringBuilder();
        report.append("=========================================================================\n");
        report.append("                  STUDENT ACTIVITY & UTILIZATION LEDGER                  \n");
        report.append("=========================================================================\n");
        report.append("Student Name : ").append(student.getName()).append("\n");
        report.append("Student ID   : ").append(studentId).append("\n");
        report.append("Period       : ").append(startDate).append(" to ").append(endDate).append("\n\n");

            report.append(String.format("%-12s %-23s %-15s %-20s\n", "DATE", "FACILITY", "SLOT", "STATUS"));
        report.append("-------------------------------------------------------------------------\n");

        boolean foundRecords = false;

        for (int i = 0; i < allBookings.size(); i++) {
            entity.Booking b = allBookings.get(i);

            if (b.getStudentId().equalsIgnoreCase(studentId)) {
                entity.Schedule s = b.getSchedule();
                
                String realDate = b.getScheduleSession().substring(0, 10);
                LocalDate bDate = LocalDate.parse(realDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

                if (!bDate.isBefore(startDate) && !bDate.isAfter(endDate)) {

                    foundRecords = true;
                    
                    String[] timeStrings = s.getTimeString();
                    String slot = timeStrings[0] + " - " + timeStrings[1]; 

                    String status = b.getStatus(); 

                    report.append(String.format("%-12s %-23s %-15s %-20s\n", 
                        realDate, s.getFacility().getName(), slot, status));
                }
            }
        }

        if (!foundRecords) report.append("No bookings found for this student in the selected period.\n");
        report.append("\n=========================== END OF REPORT ===========================");

        return report.toString();
    }
    
    public String generateCrossProgrammeReport(LocalDate startDate, LocalDate endDate) {
        adt.ListInterface<entity.Booking> allBookings = bookingCollection.getAllBookings();

        int dSCount = 0; 
        int seCount = 0; 
        int sdCount = 0;
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
                entity.Student student = studentCollection.getStudentById(b.getStudentId());
                if (student != null) {
                    String prog = student.getProgramme();
                    if (prog.equalsIgnoreCase("Data Science")) dSCount++;
                    else if (prog.equalsIgnoreCase("Software Engineering")) seCount++;
                    else if (prog.equalsIgnoreCase("Software Developer")) sdCount++;
                }
            }
        }

        StringBuilder report = new StringBuilder();
        report.append("==================================================\n");
        report.append("           CROSS-PROGRAMME TRENDS REPORT          \n");
        report.append("    Period: ").append(startDate).append(" to ").append(endDate).append("\n");
        report.append("==================================================\n\n");

        report.append(String.format("%-25s %-15s\n", "PROGRAMME", "TOTAL BOOKINGS"));
        report.append("--------------------------------------------------\n");
        report.append(String.format("%-25s %-15d\n", "Data Science", dSCount));
        report.append(String.format("%-25s %-15d\n", "Software Engineering", seCount));
        report.append(String.format("%-25s %-15d\n", "Software Developer", sdCount));

        report.append("\n=================== END OF REPORT ===================");
        return report.toString();
    }
    

}
