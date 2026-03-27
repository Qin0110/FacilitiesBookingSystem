package dao;

import adt.*;
import control.*;
import entity.Schedule;
import java.io.*;

public class ScheduleCollection {
    private final String DIRECTORY_NAME = "src/Data";
    private final String FILE_NAME = DIRECTORY_NAME + "/schedules.dat";
    
    public void saveSchedules(ListInterface<Schedule> scheduleList) {
        try {
//            File directory = new File(DIRECTORY_NAME);
//            if(!directory.exists()){
//                directory.mkdir();
//            }
            
            FileOutputStream fileOut = new FileOutputStream(FILE_NAME);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            
            objectOut.writeObject(scheduleList);
            
            objectOut.close();
            fileOut.close();
            System.out.println("Schedules successfully saved to " + FILE_NAME);
            
        } catch (IOException e) {
            System.out.println("Error saving schedules to file: " + e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked") // Suppresses the warning when casting the object back to a ListInterface
    public ListInterface<Schedule> retrieveSchedules() {
        File file = new File(FILE_NAME);
        
        // If the file doesn't exist yet (e.g., first time running the program), return null
        if (!file.exists()) {
            return null;
        }

        try {
            FileInputStream fileIn = new FileInputStream(FILE_NAME);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            
            // Read the bytes and cast them back into your specific ADT
            ListInterface<Schedule> loadedList = (ListInterface<Schedule>) objectIn.readObject();
            
            objectIn.close();
            fileIn.close();
            System.out.println("Schedules successfully loaded from " + FILE_NAME);
            return loadedList;
            
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading schedules from file: " + e.getMessage());
            return null; // Return null if the file is corrupted so the system can generate defaults instead of crashing
        }
    }
}
