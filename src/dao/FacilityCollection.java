package dao;

import adt.ListInterface;
import UtilitiesClass.DATFile;
import entity.Facility;
import java.io.Serializable;
 
public class FacilityCollection implements Serializable {
    
    private static final String FILE_NAME = "src/Data/facilities.dat";

    private ListInterface<Facility> facilityList;
 
    public FacilityCollection() {
        facilityList = DATFile.loadFromFile(FILE_NAME);

        if (facilityList.isEmpty()) {
            initDummyData();
            saveData(); 
        }
    }
    
    private void saveData() {
        java.io.File directory = new java.io.File("src/Data");
        if (!directory.exists()) {
            directory.mkdir();
        }
        DATFile.saveToFile(FILE_NAME, facilityList);
    }
 
    private void initDummyData() {
        for (int i = 1; i <= 20; i++) {
            facilityList.add(new Facility("GYM-" + String.format("%02d", i), "Gymnasium " + i, "Sports", "Club House", 1, 1));
            facilityList.add(new Facility("POOL-" + String.format("%02d", i), "Swimming Pool " + i, "Sports", "Club House", 1, 1));
            facilityList.add(new Facility("Q-" + String.format("%02d", i), "Individual Study Room Q" + i, "Library Study Room", "Library Level 3", 1, 1));
        }

        for (int i = 1; i <= 10; i++) {
            facilityList.add(new Facility("SP-FOYER-" + i, "S.P. Foyer " + i, "Event Space", "Club House", 1, 50));
            facilityList.add(new Facility("BADM-" + String.format("%02d", i), "Badminton Court " + i, "Sports", "Sports Complex", 1, 8));
        }

        for (int i = 1; i <= 2; i++) {
            facilityList.add(new Facility("TENNIS-" + String.format("%02d", i), "Tennis Court " + i, "Sports", "Sports Complex", 1, 8));
            facilityList.add(new Facility("SQUASH-" + String.format("%02d", i), "Squash Court " + i, "Sports", "Sports Complex", 1, 6));
        }

        for (int i = 1; i <= 4; i++) {
            facilityList.add(new Facility("PICKLE-" + String.format("%02d", i), "Pickleball Court " + i, "Sports", "Sports Complex", 1, 8));
            facilityList.add(new Facility("PICKLE-ARENA-" + String.format("%02d", i), "Arena Pickleball Court " + i, "Sports", "TAR UMT Arena", 1, 8));
            facilityList.add(new Facility("CC-" + String.format("%02d", i), "Discussion Room (1PC)", "Cyber Centre Discussion Room", "Cyber Centre", 2, 4));
            facilityList.add(new Facility("CC-" + String.format("%02d", i + 4), "Discussion Room (2PC)", "Cyber Centre Discussion Room", "Cyber Centre", 5, 8));
            facilityList.add(new Facility("CC-" + String.format("%02d", i + 8), "Discussion Room with Projector (2PC)", "Cyber Centre Discussion Room", "Cyber Centre", 5, 8));
        }

        for (int i = 1; i <= 10; i++) {
            facilityList.add(new Facility("A-" + String.format("%02d", i), "Discussion Room", "Library Study Room", "Library Level 1", 2, 4));
            facilityList.add(new Facility("B-" + String.format("%02d", i), "Discussion Room with PC", "Library Study Room", "Library Level 2", 2, 4));
        }

        for (int i = 11; i <= 15; i++) {
            facilityList.add(new Facility("A-" + String.format("%02d", i), "Discussion Room", "Library Study Room", "Library Level 1", 5, 6));
            facilityList.add(new Facility("B-" + String.format("%02d", i), "Discussion Room with PC", "Library Study Room", "Library Level 2", 5, 6));
            facilityList.add(new Facility("A-" + String.format("%02d", i + 5), "Discussion Room", "Library Study Room", "Library Level 1", 6, 8));
            facilityList.add(new Facility("B-" + String.format("%02d", i + 5), "Discussion Room with PC", "Library Study Room", "Library Level 2", 6, 8));
        }

        facilityList.add(new Facility("KARAOKE-01", "Guest/Karaoke Room", "Recreation", "Student Centre", 1, 10));
        facilityList.add(new Facility("SNOOKER-01", "Snooker Table 1", "Recreation", "Clubhouse", 1, 6));
        facilityList.add(new Facility("ARENA-FUT", "Futsal Court", "Sports", "TAR UMT Arena", 1, 16));
        facilityList.add(new Facility("ARENA-HND", "Handball Court", "Sports", "TAR UMT Arena", 1, 16));
    }
 
    public boolean add(Facility facility) {
        if (isFacilityExists(facility.getFacilityId())) 
            return false;
        
        boolean result = facilityList.add(facility);
        
        if (result) 
            saveData();  
        
        return result;
    }
 
    public ListInterface<Facility> getAllFacilities() {
        return facilityList;
    }
 
    public Facility getFacilityById(String facilityId) {
        for (int i = 0; i < facilityList.size(); i++) {
            Facility f = facilityList.get(i);
            if (f.getFacilityId().equalsIgnoreCase(facilityId)) {
                return f;
            }
        }
        
        return null;
    }
 
    public boolean isFacilityExists(String facilityId) {
        return getFacilityById(facilityId) != null;
    }
 
    public boolean remove(String facilityId) {
        for (int i = 0; i < facilityList.size(); i++) {
            if (facilityList.get(i).getFacilityId().equalsIgnoreCase(facilityId)) {
                facilityList.remove(i);
                saveData();      
                return true;
            }
        }
        
        return false;
    }
 

    public boolean set(Facility element) {
         for (int i = 0; i < facilityList.size(); i++) {
            if (facilityList.get(i).getFacilityId().equalsIgnoreCase(element.getFacilityId())) {
                facilityList.set(i, element);
                saveData();      
                return true;
            }
        }
         
        return false;
    }
 
    public int getTotalFacilities() {
        return facilityList.size();
    }
    
    public ListInterface<Facility> searchFacility(String keyword, String type) {
        ListInterface<Facility> results = new adt.SingleLinkedList<>(); // Make sure to use the full path or import it

        for (int i = 0; i < facilityList.size(); i++) {     
            Facility f = facilityList.get(i);

            boolean matchesKeyword = f.getFacilityId().toLowerCase().contains(keyword.toLowerCase())
                                  || f.getName().toLowerCase().contains(keyword.toLowerCase());

            boolean matchesType = (type == null || type.equals("ALL")
                                  || f.getType().equals(type));

            if (matchesKeyword && matchesType) {
                results.add(f);                       
            }
        }
        return results; 
    }

    public ListInterface<Facility> searchFacility(String id) {
        return searchFacility(id, "ALL");
    }

 
}