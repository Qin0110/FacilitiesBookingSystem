/**
 * @author Mak Xuan Er
 */


package dao;

import adt.ListInterface;
import UtilitiesClass.DATFile;
import entity.Booking;
import entity.Schedule;
import java.io.Serializable;

public class BookingCollection implements Serializable {
    
    private static final String FILE_NAME = "src/Data/bookings.dat";

    private ListInterface<Booking> bookingList;

    public BookingCollection() {
        bookingList = DATFile.loadFromFile(FILE_NAME);
        
        if (bookingList.isEmpty()) {
            ListInterface<Schedule> schedules = DATFile.loadFromFile("src/Data/schedules.dat");
            
            if (schedules != null && !schedules.isEmpty()) {
                System.out.println("Generating 65 default past bookings...");

                // --- JANUARY 2026 (20 Records) ---
                addDefaultBooking(schedules, "B100", "25WMR00001", 0, "05-01-2026 08:00 - 09:00", "Completed", "A1001");
                addDefaultBooking(schedules, "B101", "25WMR00002", 1, "08-01-2026 10:00 - 11:00", "Completed", "A1002");
                addDefaultBooking(schedules, "B102", "25WMR00003", 2, "12-01-2026 14:00 - 15:00", "Cancelled", null);
                addDefaultBooking(schedules, "B103", "25WMR00004", 3, "15-01-2026 16:00 - 17:00", "Completed", "A1004");
                addDefaultBooking(schedules, "B104", "25WMR00005", 4, "18-01-2026 09:00 - 10:00", "Not Attended", null);
                addDefaultBooking(schedules, "B105", "25WMR00037", 5, "20-01-2026 11:00 - 12:00", "Completed", "A1001");
                addDefaultBooking(schedules, "B106", "25WMR00038", 0, "22-01-2026 13:00 - 14:00", "Completed", "A1005");
                addDefaultBooking(schedules, "B107", "25WMR00039", 1, "25-01-2026 15:00 - 16:00", "Cancelled", null);
                addDefaultBooking(schedules, "B108", "25WMR00040", 2, "28-01-2026 17:00 - 18:00", "Completed", "A1002");
                addDefaultBooking(schedules, "B109", "25WMR00041", 3, "30-01-2026 19:00 - 20:00", "Completed", "A1003");
                addDefaultBooking(schedules, "B110", "25WMR00006", 4, "02-01-2026 09:00 - 10:00", "Completed", "A1006");
                addDefaultBooking(schedules, "B111", "25WMR00007", 5, "04-01-2026 11:00 - 12:00", "Cancelled", null);
                addDefaultBooking(schedules, "B112", "25WMR00008", 0, "07-01-2026 14:00 - 15:00", "Completed", "A1007");
                addDefaultBooking(schedules, "B113", "25WMR00009", 1, "09-01-2026 16:00 - 17:00", "Completed", "A1008");
                addDefaultBooking(schedules, "B114", "25WMR00010", 2, "11-01-2026 10:00 - 11:00", "Not Attended", null);
                addDefaultBooking(schedules, "B115", "25WMR00011", 3, "14-01-2026 12:00 - 13:00", "Completed", "A1009");
                addDefaultBooking(schedules, "B116", "25WMR00012", 4, "16-01-2026 15:00 - 16:00", "Completed", "A1001");
                addDefaultBooking(schedules, "B117", "25WMR00013", 5, "19-01-2026 17:00 - 18:00", "Cancelled", null);
                addDefaultBooking(schedules, "B118", "25WMR00014", 0, "23-01-2026 08:00 - 09:00", "Completed", "A1002");
                addDefaultBooking(schedules, "B119", "25WMR00015", 1, "27-01-2026 19:00 - 20:00", "Completed", "A1003");

                // --- FEBRUARY 2026 (20 Records) ---
                addDefaultBooking(schedules, "B120", "25WMR00042", 4, "02-02-2026 09:00 - 10:00", "Completed", "A1006");
                addDefaultBooking(schedules, "B121", "25WMR00043", 5, "04-02-2026 11:00 - 12:00", "Completed", "A1001");
                addDefaultBooking(schedules, "B122", "25WMR00001", 0, "07-02-2026 14:00 - 15:00", "Cancelled", null);
                addDefaultBooking(schedules, "B123", "25WMR00002", 1, "10-02-2026 16:00 - 17:00", "Completed", "A1002");
                addDefaultBooking(schedules, "B124", "25WMR00003", 2, "14-02-2026 10:00 - 11:00", "Completed", "A1007");
                addDefaultBooking(schedules, "B125", "25WMR00004", 3, "18-02-2026 12:00 - 13:00", "Not Attended", null);
                addDefaultBooking(schedules, "B126", "25WMR00005", 4, "20-02-2026 15:00 - 16:00", "Completed", "A1008");
                addDefaultBooking(schedules, "B127", "25WMR00037", 5, "23-02-2026 17:00 - 18:00", "Completed", "A1009");
                addDefaultBooking(schedules, "B128", "25WMR00038", 0, "25-02-2026 08:00 - 09:00", "Cancelled", null);
                addDefaultBooking(schedules, "B129", "25WMR00039", 1, "27-02-2026 19:00 - 20:00", "Completed", "A1001");
                addDefaultBooking(schedules, "B130", "25WMR00016", 2, "01-02-2026 10:00 - 11:00", "Completed", "A1004");
                addDefaultBooking(schedules, "B131", "25WMR00017", 3, "03-02-2026 13:00 - 14:00", "Cancelled", null);
                addDefaultBooking(schedules, "B132", "25WMR00018", 4, "05-02-2026 15:00 - 16:00", "Completed", "A1005");
                addDefaultBooking(schedules, "B133", "25WMR00019", 5, "08-02-2026 16:00 - 17:00", "Completed", "A1006");
                addDefaultBooking(schedules, "B134", "25WMR00020", 0, "11-02-2026 09:00 - 10:00", "Not Attended", null);
                addDefaultBooking(schedules, "B135", "25WMR00021", 1, "13-02-2026 11:00 - 12:00", "Completed", "A1007");
                addDefaultBooking(schedules, "B136", "25WMR00022", 2, "16-02-2026 14:00 - 15:00", "Completed", "A1008");
                addDefaultBooking(schedules, "B137", "25WMR00023", 3, "19-02-2026 17:00 - 18:00", "Cancelled", null);
                addDefaultBooking(schedules, "B138", "25WMR00024", 4, "22-02-2026 19:00 - 20:00", "Completed", "A1009");
                addDefaultBooking(schedules, "B139", "25WMR00025", 5, "26-02-2026 20:00 - 21:00", "Completed", "A1001");

                // --- MARCH 2026 (20 Records) ---
                addDefaultBooking(schedules, "B140", "25WMR00040", 2, "03-03-2026 10:00 - 11:00", "Completed", "A1002");
                addDefaultBooking(schedules, "B141", "25WMR00041", 3, "06-03-2026 13:00 - 14:00", "Completed", "A1003");
                addDefaultBooking(schedules, "B142", "25WMR00042", 4, "09-03-2026 15:00 - 16:00", "Completed", "A1004");
                addDefaultBooking(schedules, "B143", "25WMR00043", 5, "12-03-2026 16:00 - 17:00", "Not Attended", null);
                addDefaultBooking(schedules, "B144", "25WMR00001", 0, "15-03-2026 09:00 - 10:00", "Completed", "A1005");
                addDefaultBooking(schedules, "B145", "25WMR00002", 1, "18-03-2026 11:00 - 12:00", "Cancelled", null);
                addDefaultBooking(schedules, "B146", "25WMR00003", 2, "21-03-2026 14:00 - 15:00", "Completed", "A1006");
                addDefaultBooking(schedules, "B147", "25WMR00004", 3, "24-03-2026 17:00 - 18:00", "Completed", "A1007");
                addDefaultBooking(schedules, "B148", "25WMR00005", 4, "28-03-2026 19:00 - 20:00", "Cancelled", null);
                addDefaultBooking(schedules, "B149", "25WMR00037", 5, "31-03-2026 20:00 - 21:00", "Completed", "A1008");
                addDefaultBooking(schedules, "B150", "25WMR00026", 0, "02-03-2026 08:00 - 09:00", "Completed", "A1002");
                addDefaultBooking(schedules, "B151", "25WMR00027", 1, "05-03-2026 10:00 - 11:00", "Cancelled", null);
                addDefaultBooking(schedules, "B152", "25WMR00028", 2, "08-03-2026 12:00 - 13:00", "Completed", "A1003");
                addDefaultBooking(schedules, "B153", "25WMR00029", 3, "11-03-2026 14:00 - 15:00", "Completed", "A1004");
                addDefaultBooking(schedules, "B154", "25WMR00030", 4, "14-03-2026 16:00 - 17:00", "Not Attended", null);
                addDefaultBooking(schedules, "B155", "25WMR00031", 5, "17-03-2026 09:00 - 10:00", "Completed", "A1005");
                addDefaultBooking(schedules, "B156", "25WMR00032", 0, "20-03-2026 11:00 - 12:00", "Completed", "A1006");
                addDefaultBooking(schedules, "B157", "25WMR00033", 1, "23-03-2026 13:00 - 14:00", "Cancelled", null);
                addDefaultBooking(schedules, "B158", "25WMR00034", 2, "26-03-2026 15:00 - 16:00", "Completed", "A1007");
                addDefaultBooking(schedules, "B159", "25WMR00035", 3, "29-03-2026 17:00 - 18:00", "Completed", "A1008");

                // --- APRIL 2026 (5 Records) ---
                addDefaultBooking(schedules, "B160", "25WMR00038", 4, "01-04-2026 08:00 - 09:00", "Completed", "A1009");
                addDefaultBooking(schedules, "B161", "25WMR00039", 5, "03-04-2026 10:00 - 11:00", "Completed", "A1001");
                addDefaultBooking(schedules, "B162", "25WMR00040", 0, "05-04-2026 12:00 - 13:00", "Cancelled", null);
                addDefaultBooking(schedules, "B163", "25WMR00041", 1, "06-04-2026 14:00 - 15:00", "Completed", "A1002");
                addDefaultBooking(schedules, "B164", "25WMR00042", 2, "07-04-2026 16:00 - 17:00", "Completed", "A1003");
                
                saveData(); 
                System.out.println("Success! bookings.dat has been updated with default.");
            } else {
                System.out.println("Warning: Cannot generate default bookings because schedules.dat is empty!");
            }
        }
    }
    
    private void addDefaultBooking(ListInterface<Schedule> schedules, String bId, String sId, int facIdx, String sessionStr, String status, String adminId) {
        Schedule targetSched = schedules.get(facIdx % schedules.size()); 
        Booking b = new Booking(bId, sId, targetSched, 0, null, null);
        b.setScheduleSession(sessionStr);
        b.setStatus(status);
        if (adminId != null) {
            b.setApprovedBy(adminId);
        }
        bookingList.add(b);
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
    
    public Booking getBookingById(String bookingId) {
        for (int i = 0; i < bookingList.size(); i++) {
            Booking b = bookingList.get(i);
            if (b.getBookingId().equalsIgnoreCase(bookingId)) {
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
        
        // Added a quick safety catch here just in case an old test booking 
        // with a weird ID format (like "Test1") gets saved to your file!
        try {
            int idNum = Integer.parseInt(lastId.substring(1)); 
            return "B" + (idNum + 1); 
        } catch (Exception e) {
            return "B" + (bookingList.size() + 100); 
        }
    }
}