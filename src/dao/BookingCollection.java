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
                addDefaultBooking(schedules, "B101", "25WMR00002", 7, "07-08-2025 11:00 - 12:00", "Completed", "A1002");
                addDefaultBooking(schedules, "B102", "25WMR00003", 14, "06-08-2025 14:00 - 15:00", "Not Attended", null);
                addDefaultBooking(schedules, "B103", "25WMR00004", 21, "08-08-2025 16:00 - 17:00", "Completed", "A1003");

                // --- SEPTEMBER 2025 ---
                addDefaultBooking(schedules, "B104", "25WMR00005", 28, "02-09-2025 08:00 - 09:00", "Completed", "A1004");
                addDefaultBooking(schedules, "B105", "25WMR00006", 35, "04-09-2025 10:00 - 11:00", "Completed", "A1005");
                addDefaultBooking(schedules, "B106", "25WMR00007", 42, "03-09-2025 13:00 - 14:00", "Not Attended", null);
                addDefaultBooking(schedules, "B107", "25WMR00008", 3, "05-09-2025 15:00 - 16:00", "Completed", "A1006");
                addDefaultBooking(schedules, "B108", "25WMR00009", 10, "04-09-2025 17:00 - 18:00", "Completed", "A1007");

                // --- OCTOBER 2025 ---
                addDefaultBooking(schedules, "B109", "25WMR00010", 17, "17-10-2025 09:00 - 10:00", "Completed", "A1008");
                addDefaultBooking(schedules, "B110", "25WMR00011", 24, "16-10-2025 12:00 - 13:00", "Cancelled", "A1009");
                addDefaultBooking(schedules, "B111", "25WMR00012", 31, "15-10-2025 14:00 - 15:00", "Not Attended", null);
                addDefaultBooking(schedules, "B112", "25WMR00013", 38, "15-10-2025 16:00 - 17:00", "Completed", "A1001");

                // --- NOVEMBER 2025 ---
                addDefaultBooking(schedules, "B113", "25WMR00014", 45, "04-11-2025 08:00 - 09:00", "Completed", "A1002");
                addDefaultBooking(schedules, "B114", "25WMR00015", 2, "05-11-2025 10:00 - 11:00", "Completed", "A1003");
                addDefaultBooking(schedules, "B115", "25WMR00016", 9, "05-11-2025 13:00 - 14:00", "Not Attended", null);
                addDefaultBooking(schedules, "B116", "25WMR00017", 16, "06-11-2025 15:00 - 16:00", "Completed", "A1004");

                // --- DECEMBER 2025 ---
                addDefaultBooking(schedules, "B117", "25WMR00018", 23, "02-12-2025 09:00 - 10:00", "Completed", "A1005");
                addDefaultBooking(schedules, "B118", "25WMR00019", 30, "03-12-2025 11:00 - 12:00", "Completed", "A1006");
                addDefaultBooking(schedules, "B119", "25WMR00020", 37, "03-12-2025 14:00 - 15:00", "Not Attended", null);
                addDefaultBooking(schedules, "B120", "25WMR00021", 4, "04-12-2025 16:00 - 17:00", "Completed", "A1007");

                // --- JANUARY 2026 ---
                addDefaultBooking(schedules, "B121", "25WMR00001", 11, "10-01-2026 08:00 - 09:00", "Completed", "A1001");
                addDefaultBooking(schedules, "B122", "25WMR00002", 18, "10-01-2026 10:00 - 11:00", "Completed", "A1002");
                addDefaultBooking(schedules, "B123", "25WMR00003", 25, "11-01-2026 12:00 - 13:00", "Completed", "A1003");
                addDefaultBooking(schedules, "B124", "25WMR00004", 32, "11-01-2026 14:00 - 15:00", "Completed", "A1004");
                addDefaultBooking(schedules, "B125", "25WMR00005", 39, "12-01-2026 16:00 - 17:00", "Cancelled", "A1005");
                addDefaultBooking(schedules, "B126", "25WMR00006", 46, "13-01-2026 08:00 - 09:00", "Cancelled", "A1006");
                addDefaultBooking(schedules, "B127", "25WMR00007", 1, "14-01-2026 10:00 - 11:00", "Not Attended", "A1007");
                addDefaultBooking(schedules, "B128", "25WMR00008", 8, "15-01-2026 12:00 - 13:00", "Completed", "A1008");
                addDefaultBooking(schedules, "B129", "25WMR00009", 15, "16-01-2026 14:00 - 15:00", "Completed", "A1009");
                addDefaultBooking(schedules, "B130", "25WMR00010", 22, "17-01-2026 16:00 - 17:00", "Completed", "A1001");
                addDefaultBooking(schedules, "B131", "25WMR00011", 29, "18-01-2026 08:00 - 09:00", "Cancelled", "A1002");
                addDefaultBooking(schedules, "B132", "25WMR00012", 36, "19-01-2026 10:00 - 11:00", "Cancelled", "A1003");
                addDefaultBooking(schedules, "B133", "25WMR00013", 43, "20-01-2026 12:00 - 13:00", "Completed", "A1004");
                addDefaultBooking(schedules, "B134", "25WMR00014", 5, "21-01-2026 14:00 - 15:00", "Completed", "A1005");
                addDefaultBooking(schedules, "B135", "25WMR00015", 12, "22-01-2026 16:00 - 17:00", "Completed", "A1006");
                addDefaultBooking(schedules, "B136", "25WMR00016", 19, "23-01-2026 08:00 - 09:00", "Completed", "A1007");
                addDefaultBooking(schedules, "B137", "25WMR00017", 26, "24-01-2026 10:00 - 11:00", "Completed", "A1008");
                addDefaultBooking(schedules, "B138", "25WMR00018", 33, "25-01-2026 12:00 - 13:00", "Completed", "A1009");
                addDefaultBooking(schedules, "B139", "25WMR00019", 40, "26-01-2026 14:00 - 15:00", "Completed", "A1001");
                addDefaultBooking(schedules, "B140", "25WMR00020", 47, "27-01-2026 16:00 - 17:00", "Completed", "A1002");

                // --- FEBRUARY 2026 ---
                addDefaultBooking(schedules, "B141", "25WMR00021", 6, "05-02-2026 08:00 - 09:00", "Completed", "A1003");
                addDefaultBooking(schedules, "B142", "25WMR00022", 13, "06-02-2026 10:00 - 11:00", "Completed", "A1004");
                addDefaultBooking(schedules, "B143", "25WMR00023", 20, "07-02-2026 12:00 - 13:00", "Completed", "A1005");
                addDefaultBooking(schedules, "B144", "25WMR00024", 27, "08-02-2026 14:00 - 15:00", "Completed", "A1006");
                addDefaultBooking(schedules, "B145", "25WMR00025", 34, "09-02-2026 16:00 - 17:00", "Not Attended", "A1007");
                addDefaultBooking(schedules, "B146", "25WMR00026", 41, "10-02-2026 08:00 - 09:00", "Completed", "A1008");
                addDefaultBooking(schedules, "B147", "25WMR00027", 48, "11-02-2026 10:00 - 11:00", "Cancelled", "A1009");
                addDefaultBooking(schedules, "B148", "25WMR00028", 0, "12-02-2026 12:00 - 13:00", "Completed", "A1001");
                addDefaultBooking(schedules, "B149", "25WMR00029", 7, "13-02-2026 14:00 - 15:00", "Not Attended", "A1002");
                addDefaultBooking(schedules, "B150", "25WMR00030", 14, "14-02-2026 16:00 - 17:00", "Cancelled", "A1003");
                addDefaultBooking(schedules, "B151", "25WMR00031", 21, "15-02-2026 08:00 - 09:00", "Completed", "A1004");
                addDefaultBooking(schedules, "B152", "25WMR00032", 28, "16-02-2026 10:00 - 11:00", "Completed", "A1005");
                addDefaultBooking(schedules, "B153", "25WMR00033", 35, "17-02-2026 12:00 - 13:00", "Cancelled", "A1006");
                addDefaultBooking(schedules, "B154", "25WMR00034", 42, "18-02-2026 14:00 - 15:00", "Completed", "A1007");
                addDefaultBooking(schedules, "B155", "25WMR00035", 2, "19-02-2026 16:00 - 17:00", "Completed", "A1008");
                addDefaultBooking(schedules, "B156", "25WMR00036", 9, "20-02-2026 08:00 - 09:00", "Completed", "A1009");
                addDefaultBooking(schedules, "B157", "25WMR00037", 16, "21-02-2026 10:00 - 11:00", "Completed", "A1001");
                addDefaultBooking(schedules, "B158", "25WMR00038", 23, "22-02-2026 12:00 - 13:00", "Completed", "A1002");
                addDefaultBooking(schedules, "B159", "25WMR00039", 30, "23-02-2026 14:00 - 15:00", "Completed", "A1003");
                addDefaultBooking(schedules, "B160", "25WMR00040", 37, "24-02-2026 16:00 - 17:00", "Completed", "A1004");

                // --- MARCH 2026 ---
                addDefaultBooking(schedules, "B161", "25WMR00041", 44, "10-03-2026 08:00 - 09:00", "Cancelled", "A1005");
                addDefaultBooking(schedules, "B162", "25WMR00042", 4, "11-03-2026 10:00 - 11:00", "Completed", "A1006");
                addDefaultBooking(schedules, "B163", "25WMR00043", 11, "12-03-2026 12:00 - 13:00", "Completed", "A1007");
                addDefaultBooking(schedules, "B164", "25WMR00001", 18, "13-03-2026 14:00 - 15:00", "Completed", "A1008");
                addDefaultBooking(schedules, "B165", "25WMR00002", 25, "14-03-2026 16:00 - 17:00", "Cancelled", "A1009");
                addDefaultBooking(schedules, "B166", "25WMR00003", 32, "15-03-2026 08:00 - 09:00", "Completed", "A1001");
                addDefaultBooking(schedules, "B167", "25WMR00004", 39, "16-03-2026 10:00 - 11:00", "Completed", "A1002");
                addDefaultBooking(schedules, "B168", "25WMR00005", 46, "17-03-2026 12:00 - 13:00", "Cancelled", "A1003");
                addDefaultBooking(schedules, "B169", "25WMR00006", 1, "18-03-2026 14:00 - 15:00", "Completed", "A1004");
                addDefaultBooking(schedules, "B170", "25WMR00007", 8, "19-03-2026 16:00 - 17:00", "Completed", "A1005");
                addDefaultBooking(schedules, "B171", "25WMR00008", 15, "20-03-2026 08:00 - 09:00", "Not Attended", "A1006");
                addDefaultBooking(schedules, "B172", "25WMR00009", 22, "21-03-2026 10:00 - 11:00", "Not Attended", "A1007");
                addDefaultBooking(schedules, "B173", "25WMR00010", 29, "22-03-2026 12:00 - 13:00", "Not Attended", "A1008");
                addDefaultBooking(schedules, "B174", "25WMR00011", 36, "23-03-2026 14:00 - 15:00", "Cancelled", "A1009");
                addDefaultBooking(schedules, "B175", "25WMR00012", 43, "24-03-2026 16:00 - 17:00", "Completed", "A1001");
                addDefaultBooking(schedules, "B176", "25WMR00013", 3, "25-03-2026 08:00 - 09:00", "Cancelled", "A1002");
                addDefaultBooking(schedules, "B177", "25WMR00014", 10, "26-03-2026 10:00 - 11:00", "Completed", "A1003");
                addDefaultBooking(schedules, "B178", "25WMR00015", 17, "27-03-2026 12:00 - 13:00", "Completed", "A1004");
                addDefaultBooking(schedules, "B179", "25WMR00016", 24, "28-03-2026 14:00 - 15:00", "Completed", "A1005");
                addDefaultBooking(schedules, "B180", "25WMR00017", 31, "29-03-2026 16:00 - 17:00", "Completed", "A1006");

                addDefaultBooking(schedules, "B181", "25WMR00018", 38, "09-04-2026 08:00 - 09:00", "Completed", "A1007");
                addDefaultBooking(schedules, "B182", "25WMR00019", 45, "09-04-2026 10:00 - 11:00", "Not Attended", "A1008");
                addDefaultBooking(schedules, "B183", "25WMR00020", 5, "10-04-2026 12:00 - 13:00", "Confirmed", null);
                addDefaultBooking(schedules, "B184", "25WMR00021", 12, "10-04-2026 14:00 - 15:00", "Confirmed", null);
                addDefaultBooking(schedules, "B185", "25WMR00022", 19, "11-04-2026 16:00 - 17:00", "Confirmed", null);
                
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