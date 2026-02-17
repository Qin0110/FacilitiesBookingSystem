package dao;

import adt.ArrayList;
import adt.ListInterface;
import entity.Facility;
import java.io.*;

/**
 * Handles file operations for Facility data.
 * @author User
 */
public class FacilityDAO {
    
    private String fileName = "facilities.dat"; 

    public void saveToFile(ListInterface<Facility> facilityList) {
        File file = new File(fileName);
        try {
            ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
            ooStream.writeObject(facilityList);
            ooStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nFile not found");
        } catch (IOException ex) {
            System.out.println("\nCannot save to file");
        }
    }

    public ListInterface<Facility> retrieveFromFile() {
        File file = new File(fileName);
        ListInterface<Facility> facilityList = new ArrayList<>(); // Default to empty list
        
        if (file.exists()) {
            try {
                ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
                facilityList = (ArrayList<Facility>) oiStream.readObject();
                oiStream.close();
            } catch (FileNotFoundException ex) {
                System.out.println("\nNo such file.");
            } catch (IOException ex) {
                System.out.println("\nCannot read from file.");
            } catch (ClassNotFoundException ex) {
                System.out.println("\nClass not found.");
            }
        }
        return facilityList;
    }
}