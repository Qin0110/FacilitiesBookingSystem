/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.ArrayList;
import adt.ListInterface;
import entity.Booking;
import entity.Student;
import entity.Schedule;


public class BookingManagement {
    
    // Can remove if no need dis anymore
    private class WaitlistRecord {
        String scheduleId;
        ListInterface<Student> waitingStudents;

        WaitlistRecord(String scheduleId) {
            this.scheduleId = scheduleId;
            this.waitingStudents = new ArrayList<>(); 
        }
    }

    private ListInterface<Booking> bookingList;
    private ListInterface<WaitlistRecord> masterWaitlist; 

    private StudentManagement studentManager;
    private ScheduleManagement scheduleManager;
    
    private dao.BookingDAO bookingDAO = new dao.BookingDAO();
    
    public BookingManagement(){
    }

    public BookingManagement(StudentManagement studentManager, ScheduleManagement scheduleManager) {
        this.bookingList = bookingDAO.getAllBookings();
        this.masterWaitlist = new ArrayList<>();
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
            String generateBookingId = bookingDAO.getLatestBookingId();
            Booking newBooking = new Booking(generateBookingId, studentId, scheduleId, schedule.getStartingTime(), schedule.getEndingTime());
            newBooking.setStatus("Confirmed");
            bookingList.add(newBooking);
            bookingDAO.addBooking(newBooking);
            scheduleManager.updateScheduleStatus(scheduleId, "Booked");
              
            return "SUCCESS";
        } else {
            //deleted waitilist
            return "FULL";
        }
    }

    public boolean cancelBooking(String bookingId, String studentId, String scheduleId) {
        Booking targetBooking = getBooking(bookingId, studentId, scheduleId);
        
        if (targetBooking != null && targetBooking.getStatus().equals("Confirmed")) {
            targetBooking.setStatus("Cancelled");
            bookingDAO.updateBookingStatus(targetBooking.getBookingId(), "Cancelled");
            
            scheduleManager.updateScheduleStatus(scheduleId, "Available");
            
            return true;
        }
        return false; 
    }

    public Booking getBooking(String bookingId, String studentId, String scheduleId) {
        for (int i = 0; i < bookingList.size(); i++) {
            Booking b = bookingList.get(i);
            if (b.getBookingId().equals(bookingId) && b.getStudentId().equals(studentId) && b.getScheduleId().equals(scheduleId)) {
                return b;
            }
        }
        return null;
    }
    
    
    // for admin kott
    public boolean removeBooking(String bookingId) {
        boolean success = bookingDAO.deleteBooking(bookingId);
        
        if (success) {
            for (int i = 0; i < bookingList.size(); i++) {
                if (bookingList.get(i).getBookingId().equals(bookingId)) {
                    bookingList.remove(i);
                    break;
                }
            }
        }
        return success;
    }
    
    public adt.ListInterface<entity.Booking> getAllBookings() {
        return this.bookingList;
    }
}