/**
 * @author Mak Xuan Er
 */


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
    private ScheduleControl scheduleManager;
    private AdminManagement adminManager;

    public BookingManagement(StudentManagement studentManager, ScheduleControl scheduleManager, AdminManagement adminManager) {
        this.bookingCollection = new BookingCollection();
        this.studentManager = studentManager;
        this.scheduleManager = scheduleManager;
        this.adminManager = adminManager;
    }

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
    
    public boolean cancelBooking(String bookingId) {
        entity.Booking targetBooking = bookingCollection.getBookingById(bookingId);

        if (targetBooking != null && (targetBooking.getStatus().equals("Confirmed") || targetBooking.getStatus().equals("Pending"))) {
            targetBooking.setStatus("Cancelled");
            bookingCollection.updateBookingStatus(bookingId, "Cancelled");

            try {
                String facilityId = targetBooking.getSchedule().getFacility().getFacilityId();

                String[] parts = targetBooking.getScheduleSession().split(" ");
                String date = parts[0];
                    String timeSession = parts[1] + " " + parts[2] + " " + parts[3]; 

                int[] indices = findScheduleIndices(facilityId, date, timeSession);
                if (indices != null) {
                    scheduleManager.modifyScheduleStatus(indices[0], indices[1], "Available");
                }
            } catch (Exception e) {
                System.out.println("Error auto-syncing schedule: " + e.getMessage());
            }

            return true;
        }
        return false;
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

    public boolean removeBooking(String bookingId) {
        return bookingCollection.remove(bookingId);
    }
    
    public Booking getBookingById(String bookingId){
        return bookingCollection.getBookingById(bookingId);
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

        adt.Comparator<entity.Booking> bookingComparator = new adt.Comparator<entity.Booking>() {
            @Override
            public int compare(entity.Booking a, entity.Booking b) {
                int cmp = 0;
                
                switch (criteria.toUpperCase()) {
                    case "BOOKING ID":
                        cmp = a.getBookingId().compareToIgnoreCase(b.getBookingId());
                        break;
                    case "STUDENT ID":
                        cmp = a.getStudentId().compareToIgnoreCase(b.getStudentId());
                        break;
                    case "STATUS":
                        cmp = a.getStatus().compareToIgnoreCase(b.getStatus());
                        break;
                    case "SESSION":
                        try {
                            java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                            
                            String[] partsA = a.getScheduleSession().split(" ");
                            String cleanStringA = partsA[0] + " " + partsA[1]; 
                            
                            String[] partsB = b.getScheduleSession().split(" ");
                            String cleanStringB = partsB[0] + " " + partsB[1];
                            
                            java.time.LocalDateTime dateA = java.time.LocalDateTime.parse(cleanStringA, formatter);
                            java.time.LocalDateTime dateB = java.time.LocalDateTime.parse(cleanStringB, formatter);
                            
                            cmp = dateA.compareTo(dateB);
                            
                        } catch (Exception e) {
                            cmp = a.getScheduleSession().compareToIgnoreCase(b.getScheduleSession());
                        }
                        break;
                    default:
                        cmp = a.getBookingId().compareToIgnoreCase(b.getBookingId());
                }
                return ascending ? cmp : -cmp;
            }
        };
        sortedList.sort(bookingComparator);
        return sortedList;
    }
    
    public boolean checkInStudent(String bookingId, String adminId) {
        Booking b = bookingCollection.getBookingById(bookingId); 

        if (adminId == null || adminId.trim().isEmpty()) {
            return false; 
        }
              
        if (adminManager.getAdminById(adminId) == null) {
            return false; 
        }
        
        if (b != null && b.getStatus().equals("Pending") ) {
            b.setStatus("In Progress");
            b.setArrivalTime(java.time.LocalDateTime.now());
            b.setApprovedBy(adminId);
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
    
    public String getActiveBookingIdForSchedule(String facilityId, String date, String timeSession) {
        adt.ListInterface<entity.Booking> allBookings = bookingCollection.getAllBookings();
        
        for (int i = 0; i < allBookings.size(); i++) {
            entity.Booking b = allBookings.get(i);
            String status = b.getStatus();
            
            if (status.equals("Confirmed") || status.equals("Pending")) {
                if (b.getSchedule() != null && 
                    b.getSchedule().getFacility().getFacilityId().equals(facilityId) &&
                    b.getScheduleSession().contains(date) &&
                    b.getScheduleSession().contains(timeSession)) {
                    
                    return b.getBookingId(); 
                }
            }
        }
        return null; 
    }

    public String generateBookingReport(java.time.LocalDate startDate, java.time.LocalDate endDate, String timeframeLabel) {
        adt.ListInterface<entity.Booking> allBookings = bookingCollection.getAllBookings();
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy");
        
        int totalBookings = 0;
        int pendingCount = 0, confirmedCount = 0, inProgressCount = 0;
        int completedCount = 0, cancelledCount = 0, notAttendedCount = 0;
        
        int morningCount = 0, afternoonCount = 0, eveningCount = 0;
        
        adt.ListInterface<String> facilityNames = new adt.SingleLinkedList<>();
        adt.ListInterface<Integer> facilityCounts = new adt.SingleLinkedList<>();
        
        for (int i = 0; i < allBookings.size(); i++) {
            entity.Booking b = allBookings.get(i);
            
            try {
                String dateString = b.getScheduleSession().split(" ")[0]; 
                java.time.LocalDate bookingDate = java.time.LocalDate.parse(dateString, formatter);
                
                // Time Filter Logic
                if (startDate != null && bookingDate.isBefore(startDate)) continue; 
                if (endDate != null && bookingDate.isAfter(endDate)) continue; 
                
                // --- PEAK USAGE MATH ---
                String startTimeStr = b.getScheduleSession().split(" ")[1]; // Gets "08:00"
                int startHour = Integer.parseInt(startTimeStr.split(":")[0]); // Extracts the '8'
                
                if (startHour >= 8 && startHour < 12) morningCount++;
                else if (startHour >= 12 && startHour < 17) afternoonCount++;
                else if (startHour >= 17) eveningCount++;
                
            } catch (Exception e) {
                continue; 
            }

            totalBookings++;
            
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

        for (int i = 1; i < facilityNames.size(); i++) {
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
        
        double compRate = totalBookings == 0 ? 0 : (completedCount * 100.0) / totalBookings;
        double cancRate = totalBookings == 0 ? 0 : (cancelledCount * 100.0) / totalBookings;
        int otherCount = pendingCount + confirmedCount + inProgressCount;
        double otherRate = totalBookings == 0 ? 0 : (otherCount * 100.0) / totalBookings;
        double noShowRate = totalBookings == 0 ? 0 : (notAttendedCount * 100.0) / totalBookings;

        StringBuilder report = new StringBuilder();
        report.append("=====================================================\n");
        report.append("               BOOKING SUMMARY REPORT\n");
        report.append("=====================================================\n");
        report.append(" Timeframe: ").append(timeframeLabel).append("\n\n");
        
        report.append(String.format(" Total Bookings   : %d\n\n", totalBookings));
        
        report.append(" -------------- SYSTEM HEALTH METRICS ---------------\n");
        report.append(String.format(" Completion Rate  : %.1f%% (%d bookings)\n", compRate, completedCount));
        report.append(String.format(" Cancellation Rate: %.1f%% (%d bookings)\n", cancRate, cancelledCount));
        report.append(String.format(" No-Show Rate     : %.1f%% (%d bookings)\n", noShowRate, notAttendedCount));
        report.append(String.format(" Other (Active)   : %.1f%% (%d bookings)\n\n", otherRate, otherCount));

        report.append(" ----------------- PEAK USAGE TIMES -----------------\n");
        report.append(String.format(" Morning   (8am - 12pm) : %d bookings\n", morningCount));
        report.append(String.format(" Afternoon (12pm - 5pm) : %d bookings\n", afternoonCount));
        report.append(String.format(" Evening   (5pm - 10pm) : %d bookings\n\n", eveningCount));
        
        report.append(" --------------- FACILITY POPULARITY ----------------\n");
        if (facilityNames.isEmpty()) report.append(" No facilities booked in this period.\n");
        else {
            for (int i = 0; i < facilityNames.size(); i++) {
                report.append(String.format(" %-25s : %d bookings\n", facilityNames.get(i), facilityCounts.get(i)));
            }
        }
        
        report.append("\n ------------- BOOKING STATUS BREAKDOWN -------------\n");
        report.append(String.format(" Completed        : %d\n", completedCount));
        report.append(String.format(" Cancelled        : %d\n", cancelledCount));
        report.append(String.format(" Not Attended     : %d\n", notAttendedCount));
        report.append(String.format(" Confirmed        : %d\n", confirmedCount));
        report.append(String.format(" Pending          : %d\n", pendingCount));
        report.append(String.format(" In Progress      : %d\n\n", inProgressCount));
        report.append("\n=====================================================\n");
        report.append("                    End of Report\n");
        return report.toString();
    }
}