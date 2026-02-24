/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;


import adt.ArrayList;
import adt.ListInterface;
import entity.Schedule;
import java.time.LocalDateTime;


public class ScheduleMaintenance {

    private ListInterface<Schedule> scheduleList;

    public ScheduleMaintenance() {
        this.scheduleList = new ArrayList<>();
       
    }

    public boolean addSchedule(Schedule newSchedule) {
        if (getScheduleById(newSchedule.getScheduleId()) != null) {
            return false; 
        }
        
        scheduleList.add(newSchedule);
        return true; 
    }


    public Schedule getScheduleById(String scheduleId) {
        for (int i = 0; i < scheduleList.size(); i++) {
            Schedule s = scheduleList.get(i);
            if (s.getScheduleId().equals(scheduleId)) {
                return s; // Schedule found!
            }
        }
        return null;
    }

    public ListInterface<Schedule> getSchedulesByFacility(String facilityId) {
        ListInterface<Schedule> filteredList = new ArrayList<>();
        
        for (int i = 0; i < scheduleList.size(); i++) {
            Schedule s = scheduleList.get(i);
            if (s.getFacilityId().equals(facilityId)) {
                filteredList.add(s);
            }
        }
        return filteredList;
    }

    public ListInterface<Schedule> getAllSchedules() {
        return scheduleList;
    }
    
    public boolean updateScheduleStatus(String scheduleId, String newStatus) {
        Schedule s = getScheduleById(scheduleId);
        if (s != null) {
            s.setAvailability(newStatus);
            return true;
        }
        return false; 
    }

    public boolean deleteSchedule(String scheduleId) {
        for (int i = 0; i < scheduleList.size(); i++) {
            Schedule s = scheduleList.get(i);
            if (s.getScheduleId().equals(scheduleId)) {
                return scheduleList.remove(i); 
            }
        }
        return false;
    }
}