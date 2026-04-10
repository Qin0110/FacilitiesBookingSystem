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
                System.out.println("Generating default past bookings...");
                
                // --- AUGUST 2025 ---
                addDefaultBooking(schedules, "B100", "25WMR00001", 0, "05-08-2025 09:00 - 10:00", "Completed", "A1001");
                addDefaultBooking(schedules, "B101", "25WMR00002", 1, "07-08-2025 11:00 - 12:00", "Completed", "A1002");
                addDefaultBooking(schedules, "B102", "25WMR00003", 2, "06-08-2025 14:00 - 15:00", "Not Attended", null);
                addDefaultBooking(schedules, "B103", "25WMR00004", 3, "08-08-2025 16:00 - 17:00", "Completed", "A1003");

                // --- SEPTEMBER 2025 ---
                addDefaultBooking(schedules, "B104", "25WMR00005", 4, "02-09-2025 08:00 - 09:00", "Completed", "A1004");
                addDefaultBooking(schedules, "B105", "25WMR00006", 5, "04-09-2025 10:00 - 11:00", "Completed", "A1005");
                addDefaultBooking(schedules, "B106", "25WMR00007", 0, "03-09-2025 13:00 - 14:00", "Not Attended", null);
                addDefaultBooking(schedules, "B107", "25WMR00008", 1, "05-09-2025 15:00 - 16:00", "Completed", "A1006");
                addDefaultBooking(schedules, "B108", "25WMR00009", 2, "04-09-2025 17:00 - 18:00", "Completed", "A1007");

                // --- OCTOBER 2025 ---
                addDefaultBooking(schedules, "B109", "25WMR00010", 3, "17-10-2025 09:00 - 10:00", "Completed", "A1008");
                addDefaultBooking(schedules, "B110", "25WMR00011", 4, "16-10-2025 12:00 - 13:00", "Cancelled", "A1009");
                addDefaultBooking(schedules, "B111", "25WMR00012", 5, "15-10-2025 14:00 - 15:00", "Not Attended", null);
                addDefaultBooking(schedules, "B112", "25WMR00013", 0, "15-10-2025 16:00 - 17:00", "Completed", "A1001");

                // --- NOVEMBER 2025 ---
                addDefaultBooking(schedules, "B113", "25WMR00014", 1, "04-11-2025 08:00 - 09:00", "Completed", "A1002");
                addDefaultBooking(schedules, "B114", "25WMR00015", 2, "05-11-2025 10:00 - 11:00", "Completed", "A1003");
                addDefaultBooking(schedules, "B115", "25WMR00016", 3, "05-11-2025 13:00 - 14:00", "Not Attended", null);
                addDefaultBooking(schedules, "B116", "25WMR00017", 4, "06-11-2025 15:00 - 16:00", "Completed", "A1004");

                // --- DECEMBER 2025 ---
                addDefaultBooking(schedules, "B117", "25WMR00018", 5, "02-12-2025 09:00 - 10:00", "Completed", "A1005");
                addDefaultBooking(schedules, "B118", "25WMR00019", 0, "03-12-2025 11:00 - 12:00", "Completed", "A1006");
                addDefaultBooking(schedules, "B119", "25WMR00020", 1, "03-12-2025 14:00 - 15:00", "Not Attended", null);
                addDefaultBooking(schedules, "B120", "25WMR00021", 2, "04-12-2025 16:00 - 17:00", "Completed", "A1007");

                // --- JANUARY 2026 ---
                addDefaultBooking(schedules, "B121", "25WMR00001", 0, "10-01-2026 08:00 - 09:00", "Completed", "A1001");
                addDefaultBooking(schedules, "B122", "25WMR00002", 1, "10-01-2026 10:00 - 11:00", "Completed", "A1002");
                addDefaultBooking(schedules, "B123", "25WMR00003", 2, "11-01-2026 12:00 - 13:00", "Completed", "A1003");
                addDefaultBooking(schedules, "B124", "25WMR00004", 3, "11-01-2026 14:00 - 15:00", "Completed", "A1004");
                addDefaultBooking(schedules, "B125", "25WMR00005", 4, "12-01-2026 16:00 - 17:00", "Cancelled", "A1005");
                addDefaultBooking(schedules, "B126", "25WMR00006", 5, "13-01-2026 08:00 - 09:00", "Cancelled", "A1006");
                addDefaultBooking(schedules, "B127", "25WMR00007", 0, "14-01-2026 10:00 - 11:00", "Not Attended", "A1007");
                addDefaultBooking(schedules, "B128", "25WMR00008", 1, "15-01-2026 12:00 - 13:00", "Completed", "A1008");
                addDefaultBooking(schedules, "B129", "25WMR00009", 2, "16-01-2026 14:00 - 15:00", "Completed", "A1009");
                addDefaultBooking(schedules, "B130", "25WMR00010", 3, "17-01-2026 16:00 - 17:00", "Completed", "A1001");
                addDefaultBooking(schedules, "B131", "25WMR00011", 4, "18-01-2026 08:00 - 09:00", "Cancelled", "A1002");
                addDefaultBooking(schedules, "B132", "25WMR00012", 5, "19-01-2026 10:00 - 11:00", "Cancelled", "A1003");
                addDefaultBooking(schedules, "B133", "25WMR00013", 0, "20-01-2026 12:00 - 13:00", "Completed", "A1004");
                addDefaultBooking(schedules, "B134", "25WMR00014", 1, "21-01-2026 14:00 - 15:00", "Completed", "A1005");
                addDefaultBooking(schedules, "B135", "25WMR00015", 2, "22-01-2026 16:00 - 17:00", "Completed", "A1006");
                addDefaultBooking(schedules, "B136", "25WMR00016", 3, "23-01-2026 08:00 - 09:00", "Completed", "A1007");
                addDefaultBooking(schedules, "B137", "25WMR00017", 4, "24-01-2026 10:00 - 11:00", "Completed", "A1008");
                addDefaultBooking(schedules, "B138", "25WMR00018", 5, "25-01-2026 12:00 - 13:00", "Completed", "A1009");
                addDefaultBooking(schedules, "B139", "25WMR00019", 0, "26-01-2026 14:00 - 15:00", "Completed", "A1001");
                addDefaultBooking(schedules, "B140", "25WMR00020", 1, "27-01-2026 16:00 - 17:00", "Completed", "A1002");

                // --- FEBRUARY 2026 ---
                addDefaultBooking(schedules, "B141", "25WMR00021", 2, "05-02-2026 08:00 - 09:00", "Completed", "A1003");
                addDefaultBooking(schedules, "B142", "25WMR00022", 3, "06-02-2026 10:00 - 11:00", "Completed", "A1004");
                addDefaultBooking(schedules, "B143", "25WMR00023", 4, "07-02-2026 12:00 - 13:00", "Completed", "A1005");
                addDefaultBooking(schedules, "B144", "25WMR00024", 5, "08-02-2026 14:00 - 15:00", "Completed", "A1006");
                addDefaultBooking(schedules, "B145", "25WMR00025", 0, "09-02-2026 16:00 - 17:00", "Not Attended", "A1007");
                addDefaultBooking(schedules, "B146", "25WMR00026", 1, "10-02-2026 08:00 - 09:00", "Completed", "A1008");
                addDefaultBooking(schedules, "B147", "25WMR00027", 2, "11-02-2026 10:00 - 11:00", "Cancelled", "A1009");
                addDefaultBooking(schedules, "B148", "25WMR00028", 3, "12-02-2026 12:00 - 13:00", "Completed", "A1001");
                addDefaultBooking(schedules, "B149", "25WMR00029", 4, "13-02-2026 14:00 - 15:00", "Not Attended", "A1002");
                addDefaultBooking(schedules, "B150", "25WMR00030", 5, "14-02-2026 16:00 - 17:00", "Cancelled", "A1003");
                addDefaultBooking(schedules, "B151", "25WMR00031", 0, "15-02-2026 08:00 - 09:00", "Completed", "A1004");
                addDefaultBooking(schedules, "B152", "25WMR00032", 1, "16-02-2026 10:00 - 11:00", "Completed", "A1005");
                addDefaultBooking(schedules, "B153", "25WMR00033", 2, "17-02-2026 12:00 - 13:00", "Cancelled", "A1006");
                addDefaultBooking(schedules, "B154", "25WMR00034", 3, "18-02-2026 14:00 - 15:00", "Completed", "A1007");
                addDefaultBooking(schedules, "B155", "25WMR00035", 4, "19-02-2026 16:00 - 17:00", "Completed", "A1008");
                addDefaultBooking(schedules, "B156", "25WMR00036", 5, "20-02-2026 08:00 - 09:00", "Completed", "A1009");
                addDefaultBooking(schedules, "B157", "25WMR00037", 0, "21-02-2026 10:00 - 11:00", "Completed", "A1001");
                addDefaultBooking(schedules, "B158", "25WMR00038", 1, "22-02-2026 12:00 - 13:00", "Completed", "A1002");
                addDefaultBooking(schedules, "B159", "25WMR00039", 2, "23-02-2026 14:00 - 15:00", "Completed", "A1003");
                addDefaultBooking(schedules, "B160", "25WMR00040", 3, "24-02-2026 16:00 - 17:00", "Completed", "A1004");

                // --- MARCH 2026 ---
                addDefaultBooking(schedules, "B161", "25WMR00041", 4, "10-03-2026 08:00 - 09:00", "Cancelled", "A1005");
                addDefaultBooking(schedules, "B162", "25WMR00042", 5, "11-03-2026 10:00 - 11:00", "Completed", "A1006");
                addDefaultBooking(schedules, "B163", "25WMR00043", 0, "12-03-2026 12:00 - 13:00", "Completed", "A1007");
                addDefaultBooking(schedules, "B164", "25WMR00001", 1, "13-03-2026 14:00 - 15:00", "Completed", "A1008");
                addDefaultBooking(schedules, "B165", "25WMR00002", 2, "14-03-2026 16:00 - 17:00", "Cancelled", "A1009");
                addDefaultBooking(schedules, "B166", "25WMR00003", 3, "15-03-2026 08:00 - 09:00", "Completed", "A1001");
                addDefaultBooking(schedules, "B167", "25WMR00004", 4, "16-03-2026 10:00 - 11:00", "Completed", "A1002");
                addDefaultBooking(schedules, "B168", "25WMR00005", 5, "17-03-2026 12:00 - 13:00", "Cancelled", "A1003");
                addDefaultBooking(schedules, "B169", "25WMR00006", 0, "18-03-2026 14:00 - 15:00", "Completed", "A1004");
                addDefaultBooking(schedules, "B170", "25WMR00007", 1, "19-03-2026 16:00 - 17:00", "Completed", "A1005");
                addDefaultBooking(schedules, "B171", "25WMR00008", 2, "20-03-2026 08:00 - 09:00", "Not Attended", "A1006");
                addDefaultBooking(schedules, "B172", "25WMR00009", 3, "21-03-2026 10:00 - 11:00", "Not Attended", "A1007");
                addDefaultBooking(schedules, "B173", "25WMR00010", 4, "22-03-2026 12:00 - 13:00", "Not Attended", "A1008");
                addDefaultBooking(schedules, "B174", "25WMR00011", 5, "23-03-2026 14:00 - 15:00", "Cancelled", "A1009");
                addDefaultBooking(schedules, "B175", "25WMR00012", 0, "24-03-2026 16:00 - 17:00", "Completed", "A1001");
                addDefaultBooking(schedules, "B176", "25WMR00013", 1, "25-03-2026 08:00 - 09:00", "Cancelled", "A1002");
                addDefaultBooking(schedules, "B177", "25WMR00014", 2, "26-03-2026 10:00 - 11:00", "Completed", "A1003");
                addDefaultBooking(schedules, "B178", "25WMR00015", 3, "27-03-2026 12:00 - 13:00", "Completed", "A1004");
                addDefaultBooking(schedules, "B179", "25WMR00016", 4, "28-03-2026 14:00 - 15:00", "Completed", "A1005");
                addDefaultBooking(schedules, "B180", "25WMR00017", 5, "29-03-2026 16:00 - 17:00", "Completed", "A1006");

                addDefaultBooking(schedules, "B181", "25WMR00018", 0, "09-04-2026 08:00 - 09:00", "Completed", "A1007");
                addDefaultBooking(schedules, "B182", "25WMR00019", 1, "09-04-2026 10:00 - 11:00", "Not Attended", "A1008");
                addDefaultBooking(schedules, "B183", "25WMR00020", 2, "10-04-2026 12:00 - 13:00", "Confirmed", null);
                addDefaultBooking(schedules, "B184", "25WMR00021", 3, "10-04-2026 14:00 - 15:00", "Confirmed", null);
                addDefaultBooking(schedules, "B185", "25WMR00022", 4, "11-04-2026 16:00 - 17:00", "Confirmed", null);
                
                saveData(); 
                System.out.println("Success! bookings.dat has been updated with default.");
            } else {
                System.out.println("Warning: Cannot generate default bookings because schedules.dat is empty!");
            }
        }
    }
    
    private void addDefaultBooking(ListInterface<Schedule> schedules, String bId, String sId, int facIdx, String sessionStr, String status, String adminId) {
        Schedule targetSched = schedules.get(facIdx % schedules.size()); 
        
        java.time.LocalDateTime arrival = null;
        java.time.LocalDateTime leaving = null;

        if (status.equals("Completed") || status.equals("In Progress")) {
            try {
                String[] parts = sessionStr.split(" ");
                String date = parts[0];
                String startTimeStr = parts[1];
                String endTimeStr = parts[3];
                
                java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                java.time.LocalDateTime sessionStart = java.time.LocalDateTime.parse(date + " " + startTimeStr, formatter);
                java.time.LocalDateTime sessionEnd = java.time.LocalDateTime.parse(date + " " + endTimeStr, formatter);
                
                arrival = sessionStart.minusMinutes(5); // Arrived 5 mins early
                if (status.equals("Completed")) {
                    leaving = sessionEnd.plusMinutes(5); // Left 5 mins after it ended
                }
            } catch (Exception e) {
                System.out.println("Error parsing dummy dates.");
            }
        }
        
        Booking b = new Booking(bId, sId, targetSched, 0, arrival, leaving);
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