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
    
    public Booking getBookingById(String bookingId){
        return bookingCollection.getBookingById(bookingId);
    }
    
    
    // for admin kott
    public boolean removeBooking(String bookingId) {
        return bookingCollection.remove(bookingId);
    }
    
    public ListInterface<Booking> getAllBookings() {
        return bookingCollection.getAllBookings();
    }
    
    //maybe could put in student
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
            bookingCollection.updateBookingStatus(bookingId, "In Progress"); // Saves to .dat
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