/**
 * @author Lee Ka Qin
 */


package control;

import java.time.*;
import java.time.format.DateTimeFormatter;
import entity.Facility;
import entity.Schedule;
import entity.ScheduleSession;
import adt.*;
import java.time.LocalTime;
import java.io.Serializable;
import dao.ScheduleCollection;
import dao.FacilityCollection;

public class ScheduleControl implements Serializable{    
    // Change to LinkedList
    private ListInterface<Schedule> schedule;
    private ScheduleCollection scheduleDAO;
    private ListInterface<Facility> facilities;
    private FacilityCollection facilityDAO;
    
    // Constructor
    public ScheduleControl(){
        this.scheduleDAO = new ScheduleCollection();
        this.schedule = scheduleDAO.retrieveSchedules();
        this.facilityDAO = new FacilityCollection();
        this.facilities = facilityDAO.getAllFacilities();
        
        if (this.schedule == null) {
            this.schedule = new SingleLinkedList<>();
        }
        
        this.verifyAndPatchUpcomingSchedules();
    }
    
    private void verifyAndPatchUpcomingSchedules(){
        boolean dataWasAdded = false;
        LocalDate today = LocalDate.now();
        
        for (int dayOffset = 0; dayOffset < 3; dayOffset++) {
            LocalDate targetDate = today.plusDays(dayOffset);
            String targetDateString = targetDate.format(Schedule.DATE_FORMATTER);
            
            for (int i = 0; i < facilities.size(); i++) {
                String currentFacility = facilities.get(i).getName();
                boolean scheduleExists = false;
                
                for (int j = 0; j < this.schedule.size(); j++) {
                    Schedule existingSchedule = schedule.get(j);
                    if (existingSchedule.getFacility().getName().equals(currentFacility) && existingSchedule.getDate().equals(targetDateString)) {
                        scheduleExists = true;
                        break;
                    }
                }
                
                if (!scheduleExists) {
                    Schedule newSchedule = new Schedule(facilities.get(i), targetDate);
                    newSchedule.setSessions(this.divideSession(newSchedule));
                    schedule.add(newSchedule);
                    dataWasAdded = true;
                }
            }
        }
        
        if (dataWasAdded) {
            scheduleDAO.saveSchedules(this.schedule);
        }
    }
    
    private ListInterface<ScheduleSession> divideSession(Schedule schedule){
        ListInterface<ScheduleSession> sessions = new SingleLinkedList<>();
        
        LocalTime currentTime = schedule.getTime()[0];
        while(currentTime.isBefore(schedule.getTime()[1])){
            LocalTime nextTime = currentTime.plusHours(1);
            String sessionString = schedule.convertTime(currentTime) + " - " + schedule.convertTime(nextTime);
            
            ScheduleSession newSession = new ScheduleSession(sessionString, ScheduleSession.STATUS_AVAILABLE);
            
            sessions.add(newSession);
            currentTime = nextTime;
        }
        return sessions; 
    }
    
    // Use when add new facility
    public void addNewSchedule(Facility facility){
        Schedule newSchedule = new Schedule(facility);
        try {
            newSchedule.setSessions(this.divideSession(newSchedule));
            schedule.add(newSchedule);
        } catch(Exception e) {
            System.out.println("Failed to add new schedule. Error: " + e);
        }
    }
    
    public void displayAllSchedule(){
        for (int i = 0; i < schedule.size(); i++) {
             System.out.println(schedule.get(i));
        }
    }
    
    public ListInterface<Schedule> getAllSchedule(){
        return this.schedule;
    }
    
    public void initializeDefaultSchedules(){
        try {
            LocalDate today = LocalDate.now();
            for (int dayOffset = 0; dayOffset < 3; dayOffset++) {
                LocalDate targetDate = today.plusDays(dayOffset);
                for (int i = 0; i < facilities.size(); i++) {
                    Schedule newSchedule = new Schedule(facilities.get(i), targetDate);
                    newSchedule.setSessions(divideSession(newSchedule));
                    schedule.add(newSchedule);
                }
            }
        } catch(Exception e) {
            System.out.println("Failed to initialize default schedule. Error: " + e);
        }
        
    }
    
    public String getAllSchedulesText(){
        return schedule.toString();
    }
    
    /**
     * Function that return integer index number, index 0 is facility associate with specific date index.
     * <p> getSpecificSchedule[1] is session index.
     * @param facilityName
     * @param date
     * @param session
     * @return [facilityDateIndex, sessionIndex]
     */
    public int[] getSpecificScheduleIndex(String facilityName, String date, String session){
        int facilityDateIndex = -1;
        int sessionIndex = -1;
        for (int i = 0; i < schedule.size(); i++) {
            if (schedule.get(i).getFacility().getName().equals(facilityName) && 
                    schedule.get(i).getDate().equals(date)) {
                facilityDateIndex = i;
                ListInterface<ScheduleSession> currentFacilitySession = schedule.get(i).getSessions();
                for (int j = 0; j < currentFacilitySession.size(); j++) {
                    if (currentFacilitySession.get(j).getSession().equals(session)) {
                        sessionIndex = j;
                        break;
                    }
                }
            }
        }
        return new int[]{facilityDateIndex, sessionIndex};
    }
    
    public boolean deleteFacilititySchedule(int index){
        try {
            if(schedule.remove(index)){
                //System.out.println("Deletion Complete.");
                scheduleDAO.saveSchedules(this.schedule);
                return true;
            } 
//            else {
//                System.out.println("Deletion Failed.");
//            }
        } catch(Exception e) {
            System.out.println("Failed to perform deletion. Error: " + e);
        }
        
        return false;
    }
    
    public boolean deleteScheduleSession(int facilityIndex, int timeIndex){
        boolean indicator = false;
        try {
            indicator = schedule.get(facilityIndex).getSessions().remove(timeIndex);
            this.sortSchedules();
            this.sortSessionsByTime();
            scheduleDAO.saveSchedules(this.schedule);
        } catch(Exception e){
            System.out.println("Failed to delete schedule session via index number. Error: " + e);
        }
        return indicator;
    }
    
    public boolean deleteScheduleSession(String facility, int timeIndex){
        boolean indicator = false;
        try {
            for (int i = 0; i < schedule.size(); i++) {
                if (schedule.get(i).getFacility().equals(facility)) {
                    indicator = schedule.get(i).getSessions().remove(timeIndex);
                    break;
                }
            }
            this.sortSchedules();
            this.sortSessionsByTime();
            scheduleDAO.saveSchedules(this.schedule);
        } catch(Exception e){
            System.out.println("Failed to delete schedule session via facility name. Error: " + e);
        }
        return indicator;
    }
    
    // Use case, compare string of the time .equals(String time), get the time index
    public void modifyScheduleStatus(int index, int time_index, String status){
        try {
            schedule.get(index).getSessions().get(time_index).setAvailability(status);
            this.sortSchedules();
            this.sortSessionsByTime();
            scheduleDAO.saveSchedules(this.schedule);
        } catch (Exception e){
            System.out.println("Failed to modify. Error: " + e);
        }
    }
    
    public adt.ListInterface<entity.Schedule> getThreeDaySchedules() {
        adt.ListInterface<entity.Schedule> filteredList = new adt.SingleLinkedList<>();
        java.time.LocalDate today = java.time.LocalDate.now();
        
        String[] validDates = new String[3];
        for (int i = 0; i < 3; i++) {
            validDates[i] = today.plusDays(i).format(entity.Schedule.DATE_FORMATTER);
        }

        for (int i = 0; i < this.schedule.size(); i++) {
            entity.Schedule s = this.schedule.get(i);
            String scheduleDate = s.getDate();
            
            if (scheduleDate.equals(validDates[0]) || 
                scheduleDate.equals(validDates[1]) || 
                scheduleDate.equals(validDates[2])) {
                
                filteredList.add(s);
            }
        }
        
        return filteredList;
    }
    
    
    public void sortSchedules() {
        if (schedule == null || schedule.size() <= 1) {
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        this.schedule.sort(new adt.Comparator<Schedule>() {
            @Override
            public int compare(Schedule s1, Schedule s2) {
                LocalDate date1 = LocalDate.parse(s1.getDate(), formatter);
                LocalDate date2 = LocalDate.parse(s2.getDate(), formatter);
                
                int dateComparison = date1.compareTo(date2);
                
                if (dateComparison != 0) {
                    return dateComparison;
                } 
             
                else {
                    return s1.getFacility().getName().compareToIgnoreCase(s2.getFacility().getName());
                }
            }
        });
    }
    
    public void sortByFacilityAndStatus(){
        this.sortSchedules();
        for (int i = 0; i < this.schedule.size(); i++) {
            this.schedule.get(i).getSessions().sort(new Comparator<ScheduleSession>(){
                @Override
                public int compare(ScheduleSession session1, ScheduleSession session2){
                    int statusCompare = session1.getAvailability().compareToIgnoreCase(session2.getAvailability());
                    if (statusCompare != 0) {
                        return statusCompare;
                    }
                    return session1.getSession().compareToIgnoreCase(session2.getSession());
                }
            });
        }
    }
    
    public void sortSessionsByTime() {
        for (int i = 0; i < this.schedule.size(); i++) {
            this.schedule.get(i).getSessions().sort(new Comparator<ScheduleSession>(){
                @Override
                public int compare(ScheduleSession s1, ScheduleSession s2){
                    return s1.getSession().compareToIgnoreCase(s2.getSession());
                }
            });
        }
    }
    
    public ListInterface<Schedule> search(String facilityName, String date){
        ListInterface<Schedule> retrievedData = new SingleLinkedList<>();
        for (int i = 0; i < this.schedule.size(); i++) {
            if (this.schedule.get(i).getFacility().getName().toLowerCase().contains(facilityName.toLowerCase()) && 
                    this.schedule.get(i).getDate().equals(date)) {
                retrievedData.add(this.schedule.get(i));
            }
        }
        return retrievedData;
    }
    
    /**
     * Generates a monthly report of facility usage, grouping similar facilities 
     * (e.g., "Swimming Pool 1" and "Swimming Pool 2" become "Swimming Pool").
     * @param targetYear The year to report on
     * @return A formatted String containing the usage report
     */
    public String annualReport(int targetYear) {
        StringBuilder report = new StringBuilder();
        ListInterface<String> facilityCategory = new SingleLinkedList<>();
        ListInterface<String> monthsContains = new SingleLinkedList<>();
        int monthIndex = 0;
        int facilityIndex = 0;
        
        for (int i = 0; i < this.schedule.size(); i++) {
            boolean facilityExist = false;
            boolean monthExist = false;

            String categoryName = this.schedule.get(i).getFacility().getName()
                    .replaceAll("\\s*[A-Za-z]?\\d+$", "").trim();
            String currentMonth = LocalDate.parse(this.schedule.get(i).getDate(), Schedule.DATE_FORMATTER)
                    .getMonth().toString();
            int currentYear = LocalDate.parse(this.schedule.get(i).getDate(), Schedule.DATE_FORMATTER)
                    .getYear();

            if (targetYear != currentYear) continue;

            for (int j = 0; j < facilityCategory.size(); j++) {
                if (categoryName.equals(facilityCategory.get(j))) {
                    facilityExist = true;
                    break;
                }
            }
            for (int j = 0; j < monthsContains.size(); j++) {
                if (currentMonth.equals(monthsContains.get(j))) {
                    monthExist = true;
                    break;
                }
            }

            if (!facilityExist) facilityCategory.add(categoryName);
            if (!monthExist) monthsContains.add(currentMonth);
        }
        
        int[][] bookedStatus = new int[monthsContains.size()][facilityCategory.size()];
        int[][] maintenanceStatus = new int[monthsContains.size()][facilityCategory.size()];
        int[][] notAvailableStatus = new int[monthsContains.size()][facilityCategory.size()];
        
        // Months Loop
        for (int i = 0; i < monthsContains.size(); i++) {
            // Loop Schedule
            for (int j = 0; j < this.schedule.size(); j++) {
                int currentYear = LocalDate.parse(this.schedule.get(j).getDate(), Schedule.DATE_FORMATTER).getYear();
                if (currentYear != targetYear) {
                    continue;
                }
                if (LocalDate.parse(this.schedule.get(j).getDate(), Schedule.DATE_FORMATTER).getMonth().toString().equals(monthsContains.get(i))) {
                    // Loop Facility Name
                    for (int k = 0; k < facilityCategory.size(); k++) {
                        if (facilityCategory.get(k).equals(this.schedule.get(j).getFacility().getName().replaceAll("\\s*[A-Za-z]?\\d+$", "").trim())) {
                            // Loop the Schedule Session
                            for (int l = 0; l < this.schedule.get(j).getSessions().size(); l++) {
                                String status = this.schedule.get(j).getSessions().get(l).getAvailability();
                                if (status.equals(ScheduleSession.STATUS_BOOKED)) {
                                    bookedStatus[i][k] ++;
                                } else if (status.equals(ScheduleSession.STATUS_MAINTENANCE)) {
                                    maintenanceStatus[i][k] ++;
                                } else if (status.equals(ScheduleSession.STATUS_NOT_AVAILABLE)) {
                                    notAvailableStatus[i][k] ++;
                                }
                            }
                        }
                    }
                }
            }
        }
        
//        report.append("\nFacility found: \n");
//        for (int i = 0; i < facilityCategory.size(); i++) {
//            report.append(facilityCategory.get(i) + "\n");
//        }
//        report.append("\nMonths found: \n");
//        for (int i = 0; i < monthsContains.size(); i++) {
//            report.append(monthsContains.get(i) + "\n");
//        }
        report.append("=".repeat(40) + " " + targetYear + " Report " + "=".repeat(40) + "\n");
        for (int i = 0; i < bookedStatus.length; i++) {
            report.append("Months: " + monthsContains.get(i) + "\n");
            report.append(String.format("%-40s %-15s %-18s %-20s%n",
                    "Facility", "Booked Count", "Maintain Count", "Not Available Count"));
            report.append("-".repeat(100) + "\n");
            for (int j = 0; j < maintenanceStatus[i].length; j++) {
                report.append(String.format("%-40s %-15d %-18d %-20d%n",
                        facilityCategory.get(j),
                        bookedStatus[i][j],
                        maintenanceStatus[i][j],
                        notAvailableStatus[i][j]
                ));
            }
            report.append("\n");
        }
        return report.toString();
    }
    
    /**
     * Generates a detailed Peak Hours report for a specific month and year,
     * broken down by individual facility categories.
     * @param targetYear The year to analyze
     * @param targetMonth The month to analyze (1-12)
     * @return Formatted String Report with a visual bar chart per facility
     */
    public String monthlyFacilityPeakReport(int targetYear, int targetMonth) {
        StringBuilder report = new StringBuilder();
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy");

        String[] categories = new String[100];
        int categoryCount = 0;

        String[] timeSlots = new String[50];
        int slotCount = 0;

        for (int i = 0; i < this.schedule.size(); i++) {
            entity.Schedule s = this.schedule.get(i);
            java.time.LocalDate date = java.time.LocalDate.parse(s.getDate(), formatter);

            if (date.getYear() != targetYear || date.getMonthValue() != targetMonth) continue;

            String categoryName = s.getFacility().getName().replaceAll("\\s*\\d+$", "").trim();

            boolean catFound = false;
            for (int c = 0; c < categoryCount; c++) {
                if (categories[c].equals(categoryName)) { catFound = true; break; }
            }
            if (!catFound) categories[categoryCount++] = categoryName;

            adt.ListInterface<entity.ScheduleSession> sessions = s.getSessions();
            for (int k = 0; k < sessions.size(); k++) {
                if (sessions.get(k).getAvailability().equals(entity.ScheduleSession.STATUS_BOOKED)) {
                    String time = sessions.get(k).getSession();
                    boolean timeFound = false;
                    for (int t = 0; t < slotCount; t++) {
                        if (timeSlots[t].equals(time)) { timeFound = true; break; }
                    }
                    if (!timeFound) timeSlots[slotCount++] = time;
                }
            }
        }

        if (categoryCount == 0 || slotCount == 0) {
            return "No booking data found for " + targetMonth + "/" + targetYear + ".";
        }

        for (int i = 0; i < slotCount - 1; i++) {
            for (int j = 0; j < slotCount - i - 1; j++) {
                if (timeSlots[j].compareTo(timeSlots[j + 1]) > 0) {
                    String temp = timeSlots[j];
                    timeSlots[j] = timeSlots[j + 1];
                    timeSlots[j + 1] = temp;
                }
            }
        }

        int[][] counts = new int[categoryCount][slotCount];
        
        for (int i = 0; i < this.schedule.size(); i++) {
            entity.Schedule s = this.schedule.get(i);
            java.time.LocalDate date = java.time.LocalDate.parse(s.getDate(), formatter);

            if (date.getYear() != targetYear || date.getMonthValue() != targetMonth) continue;

            String categoryName = s.getFacility().getName().replaceAll("\\s*\\d+$", "").trim();

            int cIndex = -1;
            for (int c = 0; c < categoryCount; c++) {
                if (categories[c].equals(categoryName)) { cIndex = c; break; }
            }

            adt.ListInterface<entity.ScheduleSession> sessions = s.getSessions();
            for (int k = 0; k < sessions.size(); k++) {
                if (sessions.get(k).getAvailability().equals(entity.ScheduleSession.STATUS_BOOKED)) {
                    String time = sessions.get(k).getSession();

                    int tIndex = -1;
                    for (int t = 0; t < slotCount; t++) {
                        if (timeSlots[t].equals(time)) { tIndex = t; break; }
                    }
                    
                    if (cIndex != -1 && tIndex != -1) counts[cIndex][tIndex]++;
                }
            }
        }
        String monthName = java.time.Month.of(targetMonth).getDisplayName(java.time.format.TextStyle.FULL, java.util.Locale.ENGLISH);
        
        report.append("=========================================================================\n");
        report.append(String.format("          FACILITY PEAK HOURS REPORT: %s %d\n", monthName.toUpperCase(), targetYear));
        report.append("=========================================================================\n\n");

        for (int c = 0; c < categoryCount; c++) {
            // First, scan this category to find its total bookings and its absolute highest peak
            int totalCatBookings = 0;
            int maxPeakForFacility = 0;
            for (int t = 0; t < slotCount; t++) {
                totalCatBookings += counts[c][t];
                if (counts[c][t] > maxPeakForFacility) {
                    maxPeakForFacility = counts[c][t];
                }
            }

            if (totalCatBookings > 0) {
                report.append(String.format("--- %s (Total Bookings: %d) ---\n", categories[c].toUpperCase(), totalCatBookings));
                report.append(String.format("%-15s | %-8s | %s\n", "Time Slot", "Bookings", "Visual Distribution"));
                report.append("-------------------------------------------------------------------------\n");

                for (int t = 0; t < slotCount; t++) {
                    int count = counts[c][t];
                    if (count > 0) { 
                        StringBuilder bar = new StringBuilder();
                        for (int b = 0; b < count; b++) {
                            bar.append("■");
                        }
                        
                        String peakLabel = (count == maxPeakForFacility) ? "  <-- [PEAK HOUR]" : "";
                        report.append(String.format("%-15s | %-8d | %s%s\n", timeSlots[t], count, bar.toString(), peakLabel));
                    }
                }
                report.append("\n"); 
            }
        }
        report.append("=========================================================================\n");
        
        return report.toString();
    }
}
