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

    public boolean cancelBooking(String bookingId, String facilityId, String date, String timeSession) {
        Booking targetBooking = bookingCollection.getBookingById(bookingId);
        
        if (targetBooking != null && targetBooking.getStatus().equals("Confirmed")) {
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
            b.setArrivalTime(java.time.LocalDateTime.now());
            b.setStatus("In Progress");
            bookingCollection.updateBookingStatus(bookingId, "In Progress"); 
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
}