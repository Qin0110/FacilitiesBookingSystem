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

        adt.ListInterface<String> studentIds = new adt.SingleLinkedList<>();
        adt.ListInterface<Integer> studentCounts = new adt.SingleLinkedList<>();

        int totalBookingsInRange = 0;
                
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
                String currentStudentId = b.getStudentId(); 

                int index = studentIds.indexOf(currentStudentId);
                
                if (index == -1) {
                    studentIds.add(currentStudentId);
                    studentCounts.add(1);
                } 
                else {
                    int currentCount = studentCounts.get(index);
                    studentCounts.set(index, currentCount + 1);
                }
            }
        }

        StringBuilder report = new StringBuilder();
        report.append("========================================================\n");
        report.append("       STUDENT UTILIZATION SUMMARY REPORT         \n");
        report.append("            Period: ").append(startDate).append(" to ").append(endDate).append("\n");
        report.append("========================================================\n\n");

        report.append("--- OVERALL STATISTICS ---\n");
        report.append("Total Valid Bookings : ").append(totalBookingsInRange).append("\n\n");

        report.append("--- BREAKDOWN BY STUDENT ID ---\n");
        report.append(String.format("%-25s %-15s\n", "STUDENT ID", "TOTAL BOOKINGS"));
        report.append("--------------------------------------------------------\n");

        for (int i = 0; i < studentIds.size(); i++) {
            report.append(String.format("%-25s %-15d\n", studentIds.get(i), studentCounts.get(i)));
        }

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

            report.append(String.format("%-12s %-25s %-15s %-20s\n", "DATE", "FACILITY", "SLOT", "STATUS"));
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

                    report.append(String.format("%-12s %-25s %-15s %-20s\n", 
                        realDate, s.getFacility().getName(), slot, status));
                }
            }
        }

        if (!foundRecords) report.append("No bookings found for this student in the selected period.\n");
        report.append("\n=========================== END OF REPORT ==============================");

        return report.toString();
    }
    
   public String generateCrossProgrammeReport(LocalDate startDate, LocalDate endDate) {
        adt.ListInterface<entity.Booking> allBookings = bookingCollection.getAllBookings();

        int overallDs = 0; 
        int overallSe = 0; 
        int overallSd = 0;
        
        // Index 0 = Data Science, Index 1 = Software Eng, Index 2 = Software Dev
        int[] libCounts = new int[3];
        int[] spoCounts = new int[3];
        int[] cybCounts = new int[3];
        int[] recCounts = new int[3];
        int[] eveCounts = new int[3];
        
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
                if (student == null) continue;

                // see which programme this student is it
                String prog = student.getProgramme();
                int progIndex = -1;
                if (prog.equalsIgnoreCase("Data Science")) progIndex = 0;
                else if (prog.equalsIgnoreCase("Software Engineering")) progIndex = 1;
                else if (prog.equalsIgnoreCase("Software Developer")) progIndex = 2;

                if (progIndex != -1) {
                    // Count for Overall Chart 
                    if (progIndex == 0) overallDs++;
                    else if (progIndex == 1) overallSe++;
                    else if (progIndex == 2) overallSd++;

                    // Count for Facility-Specific Chart 
                    String facilityType = s.getFacility().getType();
                    if (facilityType.equalsIgnoreCase("Library Study Room") || facilityType.equalsIgnoreCase("Study Room")) {
                        libCounts[progIndex]++;
                    } else if (facilityType.equalsIgnoreCase("Sports")) {
                        spoCounts[progIndex]++;
                    } else if (facilityType.equalsIgnoreCase("Cyber Centre Discussion Room")) {
                        cybCounts[progIndex]++;
                    } else if (facilityType.equalsIgnoreCase("Recreation")) {
                        recCounts[progIndex]++;
                    } else if (facilityType.equalsIgnoreCase("Event Space")) {
                        eveCounts[progIndex]++;
                    }
                }
            }
        }

        StringBuilder report = new StringBuilder();
        int maxBlocks = 8; // Standard height for all charts to keep them neat

        report.append("========================================================================\n");
        report.append("                    CROSS-PROGRAMME TRENDS REPORT                       \n");
        report.append("               Period: ").append(startDate).append(" to ").append(endDate).append("\n");
        report.append("========================================================================\n\n");


        report.append(">>> OVERALL DISTRIBUTION (ALL FACILITIES) <<<\n");
        report.append("------------------------------------------------------------------------\n");

        int totalOverall = overallDs + overallSe + overallSd;
        if (totalOverall == 0) {
            report.append("  (No valid bookings found in the selected period)\n\n\n");
        } else {
            double pDs = ((double) overallDs / totalOverall) * 100;
            double pSe = ((double) overallSe / totalOverall) * 100;
            double pSd = ((double) overallSd / totalOverall) * 100;

            // Overall Data Table
            report.append(String.format("%-25s %-15s %-15s\n", "PROGRAMME", "TOTAL BOOKINGS", "PERCENTAGE OF TOTAL"));
            report.append("------------------------------------------------------------------------\n");
            report.append(String.format("%-25s %-15d %.2f%%\n", "Data Science", overallDs, pDs));
            report.append(String.format("%-25s %-15d %.2f%%\n", "Software Engineering", overallSe, pSe));
            report.append(String.format("%-25s %-15d %.2f%%\n", "Software Developer", overallSd, pSd));
            report.append("\n");

            // Overall Bar Chart
            double maxOverallPct = Math.max(pDs, Math.max(pSe, pSd));
            if (maxOverallPct == 0) maxOverallPct = 1;

            int hDs = (int) Math.round((pDs / maxOverallPct) * maxBlocks);
            int hSe = (int) Math.round((pSe / maxOverallPct) * maxBlocks);
            int hSd = (int) Math.round((pSd / maxOverallPct) * maxBlocks);

            for (int h = maxBlocks; h > 0; h--) {
                String b1 = (hDs >= h) ? "   ######" : "";
                String b2 = (hSe >= h) ? "   ######" : "";
                String b3 = (hSd >= h) ? "   ######" : "";
                report.append(String.format("%-24s %-24s %-24s\n", b1, b2, b3));
            }
            report.append("------------------------------------------------------------------------\n");
            report.append(String.format("%-24s %-24s %-24s\n\n\n\n\n", "Data Science", "Software Engineering", "Software Developer"));
        }

        report.append("========================================================================\n");
        report.append("                   FACILITY-SPECIFIC BREAKDOWN                          \n");
        report.append("========================================================================\n\n");

        String[] facilityNames = {
            "Library Study Room", "Sports", "Cyber Centre Discussion Room", "Recreation", "Event Space"
        };
        int[][] allCounts = {libCounts, spoCounts, cybCounts, recCounts, eveCounts};

        for (int f = 0; f < facilityNames.length; f++) {
            String facName = facilityNames[f];
            int[] counts = allCounts[f];

            int total = counts[0] + counts[1] + counts[2];

            report.append(">>> FACILITY: ").append(facName.toUpperCase()).append(" <<<\n");
            report.append("------------------------------------------------------------------------\n");

            if (total == 0) {
                report.append("  (No bookings found for this facility in the selected period)\n\n\n");
                continue; 
            }

            double p0 = ((double) counts[0] / total) * 100;
            double p1 = ((double) counts[1] / total) * 100;
            double p2 = ((double) counts[2] / total) * 100;

            // Specific Data Table
            report.append(String.format("%-25s %-15s %-15s\n", "PROGRAMME", "TOTAL BOOKINGS", "PERCENTAGE OF TOTAL"));
            report.append("------------------------------------------------------------------------\n");
            report.append(String.format("%-25s %-15d %.2f%%\n", "Data Science", counts[0], p0));
            report.append(String.format("%-25s %-15d %.2f%%\n", "Software Engineering", counts[1], p1));
            report.append(String.format("%-25s %-15d %.2f%%\n", "Software Developer", counts[2], p2));
            report.append("\n");

            // Specific Bar Chart
            double maxPct = Math.max(p0, Math.max(p1, p2));
            if (maxPct == 0) maxPct = 1;

            int h0 = (int) Math.round((p0 / maxPct) * maxBlocks);
            int h1 = (int) Math.round((p1 / maxPct) * maxBlocks);
            int h2 = (int) Math.round((p2 / maxPct) * maxBlocks);

            for (int h = maxBlocks; h > 0; h--) {
                String b1 = (h0 >= h) ? "   ######" : "";
                String b2 = (h1 >= h) ? "   ######" : "";
                String b3 = (h2 >= h) ? "   ######" : "";
                report.append(String.format("%-24s %-24s %-24s\n", b1, b2, b3));
            }
            
            report.append("------------------------------------------------------------------------\n");
            report.append(String.format("%-24s %-22s %-24s\n\n\n\n\n", "Data Science", "Software Engineering", "Software Developer"));
        }

        report.append("=========================== END OF REPORT ==============================\n");
        return report.toString();
    }
}
