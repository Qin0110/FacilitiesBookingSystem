package UtilitiesClass;

import adt.ListInterface;
import adt.SingleLinkedList;
import dao.FacilityCollection;
import dao.ScheduleCollection;
import entity.Facility;
import entity.Schedule;
import entity.ScheduleSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class ScheduleGenerator {
    public static void main(String[] args) {
        String[] session = {
            "08:00 - 09:00",
            "09:00 - 10:00",
            "10:00 - 11:00",
            "11:00 - 12:00",
            "12:00 - 13:00",
            "13:00 - 14:00",
            "14:00 - 15:00",
            "15:00 - 16:00",
            "16:00 - 17:00",
            "17:00 - 18:00",
            "18:00 - 19:00",
            "19:00 - 20:00",
            "20:00 - 21:00"
        };
        String[] sessionStatus = {
            ScheduleSession.STATUS_MAINTENANCE,
            ScheduleSession.STATUS_NOT_AVAILABLE
        };
        System.out.println("Starting Schedule Generation from Data...");
        
        Random rand = new Random();
        
        FacilityCollection facilityDAO = new FacilityCollection();
        ListInterface<Facility> facilities = facilityDAO.getAllFacilities();
        
        if (facilities == null || facilities.isEmpty()) {
            System.out.println("Error: No facilities found! Please run your main system to create facilities first.");
            return;
        }

        ListInterface<Schedule> historicalSchedules = new SingleLinkedList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        
        ListInterface<String> rawData = bookedData();
        
        System.out.println("Found " + rawData.size() + " records to process...");

        for (int i = 0; i < rawData.size(); i++) {
            String row = rawData.get(i);
            
            String[] parts = row.split(","); 
            String dateStr = parts[0];
            String sessionTime = parts[1];
            String status = parts[2];
            
            LocalDate parsedDate = LocalDate.parse(dateStr, formatter);
            
            Facility randomFacility = facilities.get(rand.nextInt(facilities.size()));
            
            Schedule pastSchedule = new Schedule(randomFacility, parsedDate);
            
            ListInterface<ScheduleSession> sessionList = new SingleLinkedList<>();
            sessionList.add(new ScheduleSession(sessionTime, status));

            int numSessionsToGenerate = rand.nextInt(session.length); 

            boolean[] usedIndexes = new boolean[session.length];


            for (int k = 0; k < session.length; k++) {
                if (session[k].equals(sessionTime)) {
                    usedIndexes[k] = true;
                    break; 
                }
            }

            int addedCount = 0;
            while (addedCount < numSessionsToGenerate) {

                int randomIndex = rand.nextInt(session.length);

                if (usedIndexes[randomIndex] == false) {

                    usedIndexes[randomIndex] = true;

                    String randomStatus = sessionStatus[rand.nextInt(sessionStatus.length)];

                    sessionList.add(new ScheduleSession(session[randomIndex], randomStatus));

                    addedCount++;
                }

                boolean allUsed = true;
                for (int j = 0; j < usedIndexes.length; j++) {
                    if (usedIndexes[j] == false) {
                        allUsed = false;
                        break;
                    }
                }
                if (allUsed) {
                    break;
                }
            }
            
            pastSchedule.setSessions(sessionList);
            
            historicalSchedules.add(pastSchedule);
        }
        
        ScheduleCollection scheduleDAO = new ScheduleCollection();
        scheduleDAO.saveSchedules(historicalSchedules);
    }

    public static ListInterface<String> bookedData() {
        ListInterface<String> dataList = new SingleLinkedList<>();
        
        // --- AUGUST 2025 ---
        dataList.add("05-08-2025,09:00 - 10:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("12-08-2025,11:00 - 12:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("18-08-2025,14:00 - 15:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("25-08-2025,16:00 - 17:00," + ScheduleSession.STATUS_BOOKED); 

        // --- SEPTEMBER 2025 ---
        dataList.add("02-09-2025,08:00 - 09:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("09-09-2025,10:00 - 11:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("16-09-2025,13:00 - 14:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("23-09-2025,15:00 - 16:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("30-09-2025,17:00 - 18:00," + ScheduleSession.STATUS_BOOKED); 

        // --- OCTOBER 2025 ---
        dataList.add("07-10-2025,09:00 - 10:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("14-10-2025,12:00 - 13:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("21-10-2025,14:00 - 15:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("28-10-2025,16:00 - 17:00," + ScheduleSession.STATUS_BOOKED); 

        // --- NOVEMBER 2025 ---
        dataList.add("04-11-2025,08:00 - 09:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("11-11-2025,10:00 - 11:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("18-11-2025,13:00 - 14:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("25-11-2025,15:00 - 16:00," + ScheduleSession.STATUS_BOOKED); 

        // --- DECEMBER 2025 ---
        dataList.add("02-12-2025,09:00 - 10:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("09-12-2025,11:00 - 12:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("16-12-2025,14:00 - 15:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("23-12-2025,16:00 - 17:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("30-12-2025,18:00 - 19:00," + ScheduleSession.STATUS_BOOKED); 

        // --- JANUARY 2026 ---
        dataList.add("05-01-2026,08:00 - 09:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("08-01-2026,10:00 - 11:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("15-01-2026,16:00 - 17:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("18-01-2026,09:00 - 10:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("20-01-2026,11:00 - 12:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("22-01-2026,13:00 - 14:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("28-01-2026,17:00 - 18:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("30-01-2026,19:00 - 20:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("02-01-2026,09:00 - 10:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("07-01-2026,14:00 - 15:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("09-01-2026,16:00 - 17:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("11-01-2026,10:00 - 11:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("14-01-2026,12:00 - 13:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("16-01-2026,15:00 - 16:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("23-01-2026,08:00 - 09:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("27-01-2026,19:00 - 20:00," + ScheduleSession.STATUS_BOOKED); 

        // --- FEBRUARY 2026 ---
        dataList.add("02-02-2026,09:00 - 10:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("04-02-2026,11:00 - 12:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("10-02-2026,16:00 - 17:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("14-02-2026,10:00 - 11:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("18-02-2026,12:00 - 13:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("20-02-2026,15:00 - 16:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("23-02-2026,17:00 - 18:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("27-02-2026,19:00 - 20:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("01-02-2026,10:00 - 11:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("05-02-2026,15:00 - 16:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("08-02-2026,16:00 - 17:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("11-02-2026,09:00 - 10:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("13-02-2026,11:00 - 12:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("16-02-2026,14:00 - 15:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("22-02-2026,19:00 - 20:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("26-02-2026,20:00 - 21:00," + ScheduleSession.STATUS_BOOKED); 

        // --- MARCH 2026 ---
        dataList.add("03-03-2026,10:00 - 11:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("06-03-2026,13:00 - 14:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("09-03-2026,15:00 - 16:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("12-03-2026,16:00 - 17:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("15-03-2026,09:00 - 10:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("21-03-2026,14:00 - 15:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("24-03-2026,17:00 - 18:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("31-03-2026,20:00 - 21:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("02-03-2026,08:00 - 09:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("08-03-2026,12:00 - 13:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("11-03-2026,14:00 - 15:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("14-03-2026,16:00 - 17:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("17-03-2026,09:00 - 10:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("20-03-2026,11:00 - 12:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("26-03-2026,15:00 - 16:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("29-03-2026,17:00 - 18:00," + ScheduleSession.STATUS_BOOKED); 

        // --- APRIL 2026 ---
        dataList.add("01-04-2026,08:00 - 09:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("03-04-2026,10:00 - 11:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("06-04-2026,14:00 - 15:00," + ScheduleSession.STATUS_BOOKED); 
        dataList.add("07-04-2026,16:00 - 17:00," + ScheduleSession.STATUS_BOOKED); 

        return dataList;
    }
}