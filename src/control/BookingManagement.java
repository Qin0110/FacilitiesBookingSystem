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
    
    public BookingManagement(){
    }

    public BookingManagement(StudentManagement studentManager, ScheduleManagement scheduleManager) {
        this.bookingList = new ArrayList<>();
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
            Booking newBooking = new Booking(schedule.getFacilityId(), studentId, scheduleId, schedule.getStartingTime(), schedule.getEndingTime());
            newBooking.setStatus("Confirmed");
            bookingList.add(newBooking);
            scheduleManager.updateScheduleStatus(scheduleId, "Booked");
            
            return "SUCCESS";
        } else {
            addToWaitlist(scheduleId, student);
            return "WAITLISTED";
        }
    }

    public boolean cancelBooking(String facilityId, String studentId, String scheduleId) {
        Booking targetBooking = getBooking(facilityId, studentId, scheduleId);
        
        if (targetBooking != null && targetBooking.getStatus().equals("Confirmed")) {
            targetBooking.setStatus("Cancelled");
            
            WaitlistRecord record = getWaitlistRecord(scheduleId);
            
            if (record != null && !record.waitingStudents.isEmpty()) {
                
                Student nextInLine = record.waitingStudents.get(0);
                record.waitingStudents.remove(0); 
                
                Booking automatedBooking = new Booking(facilityId, nextInLine.getStudentId(), scheduleId, targetBooking.getArrivalTime(), targetBooking.getLeavingTime());
                automatedBooking.setStatus("Confirmed");
                bookingList.add(automatedBooking);
                
            } else {
                scheduleManager.updateScheduleStatus(scheduleId, "Available");
            }
            return true;
        }
        return false; 
    }

    private void addToWaitlist(String scheduleId, Student student) {
        WaitlistRecord record = getWaitlistRecord(scheduleId);
        
        if (record == null) {
            record = new WaitlistRecord(scheduleId);
            masterWaitlist.add(record);
        }
        
        record.waitingStudents.add(student);
    }

    private WaitlistRecord getWaitlistRecord(String scheduleId) {
        for (int i = 0; i < masterWaitlist.size(); i++) {
            WaitlistRecord record = masterWaitlist.get(i);
            if (record.scheduleId.equals(scheduleId)) {
                return record;
            }
        }
        return null;
    }

 
    public Booking getBooking(String facilityId, String studentId, String scheduleId) {
        for (int i = 0; i < bookingList.size(); i++) {
            Booking b = bookingList.get(i);
            if (b.getBookingId().equals(facilityId) && b.getStudentId().equals(studentId) && b.getScheduleId().equals(scheduleId)) {
                return b;
            }
        }
        return null;
    }
}