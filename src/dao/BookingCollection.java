package dao;

import adt.ListInterface;
import UtilitiesClass.DATFile;
import entity.Booking;
import java.io.Serializable;

public class BookingCollection implements Serializable {
    
    private static final String FILE_NAME = "src/Data/bookings.dat";

    private ListInterface<Booking> bookingList;

    public BookingCollection() {
        bookingList = DATFile.loadFromFile(FILE_NAME);
    }
    
    private void saveData() {
        java.io.File directory = new java.io.File("src/Data");
        if (!directory.exists()) {
            directory.mkdir();
        }
        DATFile.saveToFile(FILE_NAME, bookingList);
    }

    public boolean add(Booking booking) {
        boolean result = bookingList.add(booking);
        if (result) {
            saveData(); 
        }
        return result;
    }

    public ListInterface<Booking> getAllBookings() {
        return bookingList;
    }
    
    //specific for the cancel
    public Booking getBooking(String bookingId, String studentId, String scheduleId) {
        for (int i = 0; i < bookingList.size(); i++) {
            Booking b = bookingList.get(i);
            if (b.getBookingId().equals(bookingId) && 
                b.getStudentId().equals(studentId) && 
                b.getScheduleId().equals(scheduleId)) {
                return b;
            }
        }
        return null;
    }

    public boolean updateBookingStatus(String bookingId, String newStatus) {
        for (int i = 0; i < bookingList.size(); i++) {
            Booking b = bookingList.get(i);
            if (b.getBookingId().equals(bookingId)) {
                b.setStatus(newStatus);

                bookingList.set(i, b); 
                saveData();
                return true;
            }
        }
        return false;
    }
    
    public boolean remove(String bookingId) {
        for (int i = 0; i < bookingList.size(); i++) {
            if (bookingList.get(i).getBookingId().equals(bookingId)) {
                bookingList.remove(i);
                saveData(); // Save the array to .dat after removing
                return true;
            }
        }
        return false;
    }
    
    public String getLatestBookingId() {
        if (bookingList.isEmpty()) {
            return "B100"; 
        }
        
        Booking lastBooking = bookingList.get(bookingList.size() - 1);
        String lastId = lastBooking.getBookingId(); 
        
        int idNum = Integer.parseInt(lastId.substring(1)); 
        return "B" + (idNum + 1); 
    }
}