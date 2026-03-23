package entity;

import java.time.*;
import java.time.format.DateTimeFormatter;
import adt.*;
import java.io.Serializable;

public class Schedule implements Serializable{
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    
    // Invoke Facilities facility classes. Change here
    private Facility facility;
//    private String scheduleId;
    private LocalTime startTime = LocalTime.of(8, 0); // May Have differnet start time
    private LocalTime endTime = LocalTime.of(21, 0); // May Have different end time
    private LocalDate date = LocalDate.now();
    
    private ListInterface<ScheduleSession> sessions = new SingleLinkedList<>();
    
    public Schedule(Facility facility){
        this.facility = facility;
    }
    
    public Schedule(Facility facility, LocalDate date){
        this(facility);
        this.date = date;
    }
    
    public Schedule(Facility facility, ListInterface<ScheduleSession> sessions){
        this(facility);
        this.sessions = sessions;
    }
    
    public void setSessions(ListInterface<ScheduleSession> sessions){
        this.sessions = sessions;
    }
    
//    public ScheduleSession getParticularSession(int index){
//        return sessions.get(index);
//    }
    
    public ListInterface<ScheduleSession> getSessions(){
        return this.sessions;
    }

    public Facility getFacility(){
        return this.facility;
    }
    
    public LocalTime[] getTime(){
        return new LocalTime[] {this.startTime, this.endTime};
    }
    
    public String[] getTimeString(){
        return new String[] {startTime.format(TIME_FORMATTER), endTime.format(TIME_FORMATTER)};
    }
    
    public String getDate(){
        return this.date.format(DATE_FORMATTER);
    }
    
    public void setDate(LocalDate date){
        this.date = date;
    }
    
    public String convertTime(LocalTime time){
        return time.format(TIME_FORMATTER);
    }
    
    public void setStartTime(LocalTime startTime){
        this.startTime = startTime;
    }
    
    public void setEndTime(LocalTime endTime){
        this.endTime = endTime;
    }
    
    public void setTime(LocalTime startTime, LocalTime endTime){
        this.setStartTime(startTime);
        this.setEndTime(endTime);
    }
    
    public String toString(){
        return this.facility + ": " + "\n" +
                this.sessions.toString() + "\n";
    }
}
