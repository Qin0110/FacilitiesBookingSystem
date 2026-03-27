package control;

import adt.ListInterface;
import dao.BookingCollection;
import entity.Booking;
import entity.Student;
import entity.Schedule;
import entity.ScheduleSession;

public class BookingManagement {
    private BookingCollection bookingCollection;
    private StudentManagement studentManager;
    private ScheduleControl scheduleManager; // Assuming this acts like ScheduleControl
    
    public BookingManagement(){
        this.bookingCollection = new BookingCollection(); 
    }

    public BookingManagement(StudentManagement studentManager, ScheduleControl scheduleManager) {
        this.bookingCollection = new BookingCollection();
        this.studentManager = studentManager;
        this.scheduleManager = scheduleManager;
    }

    /**
     * Replaces the need for a single Schedule ID.
     * <p>Returns an array containing [scheduleIndex, sessionIndex]. Returns null if not found.
     */
    private int[] findScheduleIndices(String facilityId, String date, String timeSession) {
        ListInterface<Schedule> allSchedules = scheduleManager.getAllSchedule(); 
        
        for (int i = 0; i < allSchedules.size(); i++) {
            Schedule s = allSchedules.get(i);
            if (s.getFacility().getFacilityId().equals(facilityId) && s.getDate().equals(date)) {
                for (int j = 0; j < s.getSessions().size(); j++) {
                    if (s.getSessions().get(j).getSession().equals(timeSession)) {
                        return new int[]{i, j};
                    }
                }
            }
        }
        return null;
    }

    // ------------------------------------------------------------------

    public String createBooking(String studentId, String facilityId, String date, String timeSession) {
        Student student = studentManager.getStudentById(studentId);
        if (student == null) 
            return "INVALID_STUDENT";
        
        int[] indices = findScheduleIndices(facilityId, date, timeSession);
        
        if (indices == null) {
            return "INVALID_SCHEDULE";
        }
        
        int dailyBookings = countStudentBookingsPerDay(studentId, date);
        if(dailyBookings >= 2)
            return "LIMIT_REACHED";
        
        
        int scheduleIndex = indices[0];
        int sessionIndex = indices[1];
        
        Schedule targetSchedule = scheduleManager.getAllSchedule().get(scheduleIndex);
        ScheduleSession targetSession = targetSchedule.getSessions().get(sessionIndex);

        if (targetSession.getAvailability().equalsIgnoreCase("Available")) {
            String generateBookingId = bookingCollection.getLatestBookingId();
            Booking newBooking = new Booking(generateBookingId, studentId, targetSchedule, sessionIndex, null, null);
            newBooking.setStatus("Confirmed");

            bookingCollection.add(newBooking);
            
            scheduleManager.modifyScheduleStatus(scheduleIndex, sessionIndex, "Booked");
              
            return "SUCCESS";
        } else {
            return "FULL";
        }
    }

    public boolean cancelBooking(String studentId, String bookingId, String facilityId, String date, String timeSession) {
        Booking targetBooking = bookingCollection.getBookingById(bookingId);
        if (targetBooking != null && 
                targetBooking.getStatus().equals("Confirmed") && 
                targetBooking.getStudentId().equals(studentId)) {
            bookingCollection.updateBookingStatus(bookingId, "Cancelled");
            
            int[] indices = findScheduleIndices(facilityId, date, timeSession);
            if (indices != null) {
                scheduleManager.modifyScheduleStatus(indices[0], indices[1], ScheduleSession.STATUS_AVAILABLE);
            }
            return true;
        }
        return false; 
    }

    // Updated to remove scheduleId requirement since bookingId should be unique
    public Booking getBookingById(String bookingId){
        return bookingCollection.getBookingById(bookingId);
    }
    
    // For admin
    public boolean removeBooking(String bookingId) {
        return bookingCollection.remove(bookingId);
    }
    
    public ListInterface<Booking> getAllBookings() {
        return bookingCollection.getAllBookings();
    }
    
    public ListInterface<entity.Booking> searchBooking(String query, String criteria) {
        adt.ListInterface<Booking> allBookings = bookingCollection.getAllBookings();

        if (query == null || query.trim().isEmpty()) {
            return allBookings; 
        }

        String lowerCaseQuery = query.toLowerCase().trim();
        adt.ListInterface<Booking> searchResults = new adt.SingleLinkedList<>();

        for (int i = 0; i < allBookings.size(); i++) {
            Booking b = allBookings.get(i);
            boolean matchesSearch = false;

            String facilityName = "";
            if (b.getSchedule() != null && b.getSchedule().getFacility() != null) {
                facilityName = b.getSchedule().getFacility().getName().toLowerCase();
            }

            switch (criteria.toUpperCase()) {
                case "BOOKING ID":
                    matchesSearch = b.getBookingId().toLowerCase().contains(lowerCaseQuery);
                    break;
                case "STUDENT ID":
                    matchesSearch = b.getStudentId().toLowerCase().contains(lowerCaseQuery);
                    break;
                case "FACILITY NAME":
                    matchesSearch = facilityName.contains(lowerCaseQuery);
                    break;
                case "DATE/SESSION":
                    matchesSearch = b.getScheduleSession().toLowerCase().contains(lowerCaseQuery);
                    break;
                case "STATUS":
                    matchesSearch = b.getStatus().toLowerCase().contains(lowerCaseQuery);
                    break;
                default: 
                    matchesSearch = b.getBookingId().toLowerCase().contains(lowerCaseQuery) ||
                                    b.getStudentId().toLowerCase().contains(lowerCaseQuery) ||
                                    b.getStatus().toLowerCase().contains(lowerCaseQuery) ||
                                    b.getScheduleSession().toLowerCase().contains(lowerCaseQuery) || 
                                    facilityName.contains(lowerCaseQuery);
                    break;
            }

            if (matchesSearch) {
                searchResults.add(b);
            }
        }

        return searchResults;
    }
    
    public adt.ListInterface<entity.Booking> sortBookings(adt.ListInterface<entity.Booking> listToSort, String criteria, boolean ascending) {
        adt.ListInterface<entity.Booking> sortedList = new adt.SingleLinkedList<>();
        for (int i = 0; i < listToSort.size(); i++) {
            sortedList.add(listToSort.get(i));
        }

        int n = sortedList.size();
        
        if (n <= 1) {
            return sortedList;
        } 
        for (int i = 1; i < n; i++) {
            entity.Booking currentItem = sortedList.get(i);
            int j = i - 1;

            while (j >= 0) {
                entity.Booking itemToCompare = sortedList.get(j);
                
                int cmp = compareBy(itemToCompare, currentItem, criteria);
                
                if (!ascending) cmp = -cmp;

                if (cmp > 0) {
                    sortedList.set(j + 1, itemToCompare);
                    j--;
                } else {
                    break; 
                }
            }
            sortedList.set(j + 1, currentItem);
        }
        return sortedList;
    }


    private int compareBy(entity.Booking a, entity.Booking b, String attribute) {
        switch (attribute.toUpperCase()) {
            case "BOOKING ID":
                return a.getBookingId().compareToIgnoreCase(b.getBookingId());
            case "STUDENT ID":
                return a.getStudentId().compareToIgnoreCase(b.getStudentId());
            case "STATUS":
                return a.getStatus().compareToIgnoreCase(b.getStatus());
            case "DATE":
                return a.getScheduleSession().compareToIgnoreCase(b.getScheduleSession());
            default:
                return 0; 
        }
    }
    
    public boolean validateMember(String studentId, String studentName) {
        Student s = studentManager.getStudentById(studentId);
        
        if (s != null && s.getName().equalsIgnoreCase(studentName.trim())) {
            return true; 
        }
        return false; 
    }
    
    public boolean checkInStudent(String bookingId) {
        Booking b = bookingCollection.getBookingById(bookingId); 
        
        if (b != null && b.getStatus().equals("Pending")) {
            b.setStatus("In Progress");
            b.setArrivalTime(java.time.LocalDateTime.now());
            return true;
        }
        return false;
    }
    
    public boolean checkOutStudent(String bookingId) {
        Booking b = bookingCollection.getBookingById(bookingId); 
        
        if (b != null && b.getStatus().equals("In Progress")) {
            b.setLeavingTime(java.time.LocalDateTime.now());
            b.setStatus("Completed");
            
            bookingCollection.updateBookingStatus(bookingId, "Completed"); 
            return true;
        }
        return false;
    }
    
    public void refreshBookingStatuses() {
        java.time.LocalDateTime now = java.time.LocalDateTime.now();
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        
        
        adt.ListInterface<entity.Booking> allBookings = this.getAllBookings(); 
        
        for (int i = 0; i < allBookings.size(); i++) {
            entity.Booking b = allBookings.get(i);
            String currentStatus = b.getStatus();
            
            if (currentStatus.equals("Confirmed") || currentStatus.equals("Pending") || currentStatus.equals("In Progress")) {
                
                try {
                    String[] sessionParts = b.getScheduleSession().split(" ");
                    String dateStr = sessionParts[0]; 
                    String startTimeStr = sessionParts[1]; 
                    String endTimeStr = sessionParts[3];
                    
                    java.time.LocalDateTime bookingStartTime = java.time.LocalDateTime.parse(dateStr + " " + startTimeStr, formatter);
                    java.time.LocalDateTime bookingEndTime = java.time.LocalDateTime.parse(dateStr + " " + endTimeStr, formatter);
                    
                    if (currentStatus.equals("Confirmed") || currentStatus.equals("Pending")) {
                        java.time.LocalDateTime checkInOpens = bookingStartTime.minusMinutes(15); 
                        java.time.LocalDateTime checkInCloses = bookingStartTime.plusMinutes(15); 
                        
                        if (now.isAfter(checkInCloses)) {
                            b.setStatus("Not Attended");
                        } 
                        else if ((now.isEqual(checkInOpens) || now.isAfter(checkInOpens)) && currentStatus.equals("Confirmed")) {
                            b.setStatus("Pending");
                        }
                    }
                    
                    if (currentStatus.equals("In Progress")) {
                        java.time.LocalDateTime autoCheckOutTime = bookingEndTime.plusMinutes(15);
                        
                        if (now.isAfter(autoCheckOutTime)) {
                            b.setStatus("Completed");
                            b.setLeavingTime(now); 
                        }
                    }
                    
                } catch (Exception e) {
                    System.out.println("Error parsing time for automation on booking: " + b.getBookingId());
                }
            }
        }
        
    }
    
    private int countStudentBookingsPerDay(String studentId, String date) {
        int count = 0;
        ListInterface<Booking> allBookings = bookingCollection.getAllBookings();
        
        for (int i = 0; i < allBookings.size(); i++) {
            Booking b = allBookings.get(i);
            
            if (b.getStudentId().equalsIgnoreCase(studentId) && !b.getStatus().equals("Cancelled")) {
                
                String bookingDate = b.getScheduleSession().split(" ")[0];
                
                if (bookingDate.equals(date)) {
                    count++;
                }
            }
        }
        return count;
    }

    public String generateBookingReport() {
        adt.ListInterface<entity.Booking> allBookings = bookingCollection.getAllBookings();
        
        int totalBookings = allBookings.size();
        int pendingCount = 0, confirmedCount = 0, inProgressCount = 0;
        int completedCount = 0, cancelledCount = 0, notAttendedCount = 0;
        
        adt.ListInterface<String> facilityNames = new adt.SingleLinkedList<>();
        adt.ListInterface<Integer> facilityCounts = new adt.SingleLinkedList<>();
        
        for (int i = 0; i < totalBookings; i++) {
            entity.Booking b = allBookings.get(i);
            
            String status = b.getStatus();
            if (status.equalsIgnoreCase("Pending")) pendingCount++;
            else if (status.equalsIgnoreCase("Confirmed")) confirmedCount++;
            else if (status.equalsIgnoreCase("In Progress")) inProgressCount++;
            else if (status.equalsIgnoreCase("Completed")) completedCount++;
            else if (status.equalsIgnoreCase("Cancelled")) cancelledCount++;
            else if (status.equalsIgnoreCase("Not Attended")) notAttendedCount++;
            
            if (b.getSchedule() != null && b.getSchedule().getFacility() != null) {
                String fName = b.getSchedule().getFacility().getName();
                
                boolean found = false;
                for (int j = 0; j < facilityNames.size(); j++) {
                    if (facilityNames.get(j).equalsIgnoreCase(fName)) {
                        facilityCounts.set(j, facilityCounts.get(j) + 1);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    facilityNames.add(fName);
                    facilityCounts.add(1);
                }
            }
        }
        //temporary sorting (wait 
        int numFacilities = facilityNames.size();
        
        for (int i = 1; i < numFacilities; i++) {
            int currentCount = facilityCounts.get(i);
            String currentName = facilityNames.get(i);
            
            int j = i - 1;
            while (j >= 0 && facilityCounts.get(j) < currentCount) {
                facilityCounts.set(j + 1, facilityCounts.get(j));
                facilityNames.set(j + 1, facilityNames.get(j));
                j--;
            }
            facilityCounts.set(j + 1, currentCount);
            facilityNames.set(j + 1, currentName);
        }
        
        StringBuilder report = new StringBuilder();
        report.append("=====================================================\n");
        report.append("               BOOKING SUMMARY REPORT\n");
        report.append("=====================================================\n\n");
        
        report.append(String.format("Total Bookings in System : %d\n\n", totalBookings));
        
        report.append("--- BOOKING STATUS BREAKDOWN ---\n");
        report.append(String.format("Completed\t:    %d\n", completedCount));
        report.append(String.format("In Progress\t:    %d\n", inProgressCount));
        report.append(String.format("Confirmed\t:    %d\n", confirmedCount));
        report.append(String.format("Pending\t\t:    %d\n", pendingCount));
        report.append(String.format("Cancelled\t:    %d\n", cancelledCount));
        report.append(String.format("Not Attended\t:    %d\n\n", notAttendedCount));
        
        report.append("--- FACILITY POPULARITY (Total Bookings) ---\n");
        if (facilityNames.isEmpty()) {
            report.append("No facilities booked yet.\n");
        } else {
            for (int i = 0; i < facilityNames.size(); i++) {
                report.append(String.format("%-25s : %d bookings\n", facilityNames.get(i), facilityCounts.get(i)));
            }
        }
        
        report.append("\n=====================================================\n");
        report.append("\t\t    End of Report\n");
        
        return report.toString();
    }
    
}