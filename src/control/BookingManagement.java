/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.ListInterface;
import dao.BookingCollection;
import entity.Booking;
import entity.Student;
import entity.Schedule;


public class BookingManagement {
    private BookingCollection bookingCollection;
    private StudentManagement studentManager;
    private ScheduleManagement scheduleManager;
    
    
    public BookingManagement(){
        this.bookingCollection = new BookingCollection(); 
    }

    public BookingManagement(StudentManagement studentManager, ScheduleManagement scheduleManager) {
        this.bookingCollection = new BookingCollection();
        this.studentManager = studentManager;
        this.scheduleManager = scheduleManager;
    }

    public String createBooking(String studentId, String scheduleId) {
        Student student = studentManager.getStudentById(studentId);
        if (student == null) 
            return "INVALID_STUDENT";

        Schedule schedule = scheduleManager.getScheduleById(scheduleId);
        if (schedule == null) 
            return "INVALID_SCHEDULE";

        if (schedule.getAvailability().equalsIgnoreCase("Available")) {
            String generateBookingId = bookingCollection.getLatestBookingId();
            Booking newBooking = new Booking(generateBookingId, studentId, scheduleId, schedule.getStartingTime(), schedule.getEndingTime());
            newBooking.setStatus("Confirmed");

            
            bookingCollection.add(newBooking);
            scheduleManager.updateScheduleStatus(scheduleId, "Booked");
              
            return "SUCCESS";
        } else {
            return "FULL";
        }
    }

    public boolean cancelBooking(String bookingId, String studentId, String scheduleId) {
        Booking targetBooking = getBooking(bookingId, studentId, scheduleId);
        
        if (targetBooking != null && targetBooking.getStatus().equals("Confirmed")) {
            bookingCollection.updateBookingStatus(bookingId, "Cancelled");
            
            scheduleManager.updateScheduleStatus(scheduleId, "Available");
            
            return true;
        }
        return false; 
    }

    public Booking getBooking(String bookingId, String studentId, String scheduleId) {
        return bookingCollection.getBooking(bookingId, studentId, scheduleId);
    }
    
    
    // for admin kott
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
}