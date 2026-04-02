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
        int facilityDateIndex = 0;
        int sessionIndex = 0;
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
                    scheduleDAO.saveSchedules(this.schedule);
                    break;
                }
            }
        } catch(Exception e){
            System.out.println("Failed to delete schedule session via facility name. Error: " + e);
        }
        return indicator;
    }
    
    // Use case, compare string of the time .equals(String time), get the time index
    public void modifyScheduleStatus(int index, int time_index, String status){
        try {
            schedule.get(index).getSessions().get(time_index).setAvailability(status);
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
    


}
