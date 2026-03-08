package control;

import adt.ListInterface;
import dao.FacilityDAO;
import entity.Facility;

public class FacilityManagement {

    private ListInterface<Facility> facilityList;
    private FacilityDAO facilityDAO = new FacilityDAO();
    
    public FacilityManagement() {
        this.facilityList = facilityDAO.getAllFacilities();
    }

    public FacilityManagement(ListInterface<Facility> sharedList) {
        this.facilityList = sharedList;
    }

    public String addNewFacility(String id, String name, String type, String location, int minCapacity, int maxCapacity) {
        if(id == null || id.isBlank()) 
            return "Error: Facility ID cannot be Empty";
        if(id.length() > 15) return 
                "Error: Facility ID length must be between 1 - 15";
        if(facilityDAO.isFacilityExists(id)) 
            return "Error: A facility with ID '" + id + "' already exists.";
        
        if(name == null || name.isBlank()) 
            return "Error: Facility Name cannot be empty";
        if(name.length() > 150) 
            return "Error: Facility Name length must be between 1 - 150";

        Facility newFacility = new Facility(id, name, type, location, minCapacity, maxCapacity);

        if (facilityDAO.addFacility(newFacility)) {
            facilityList.add(newFacility);
            return "Facility Successfully Added";
        } else {
            return "Error: Could not save to Database.";
        }
    }
    
    public ListInterface<Facility> getAllFacilities() {
        return this.facilityList;
    }
   
    public String removeFacility(String facilityId) {
        if (facilityDAO.deleteFacility(facilityId)) {
            for (int i = 0; i < facilityList.size(); i++) {
                if (facilityList.get(i).getFacilityId().equals(facilityId)) {
                    
                    break;
                }
            }
            this.facilityList = facilityDAO.getAllFacilities(); 
            return "Facility Successfully Removed";
        }
        return "Error: Could not delete from Database.";
    }
    
    public String updateFacility(String id, String name, String type, String location, int minCapacity, int maxCapacity) {
        Facility updatedFacility = new Facility(id, name, type, location, minCapacity, maxCapacity);
        
        if (facilityDAO.updateFacility(updatedFacility)) {
            this.facilityList = facilityDAO.getAllFacilities();
            return "Facility Successfully Updated";
        }
        return "Error: Could not update Database.";
    }
    
    
    public adt.ListInterface<entity.Facility> searchFacility(String id, String type) {
        
        adt.ListInterface<entity.Facility> searchResults = new adt.ArrayList<>();
        
        for (int i = 0; i < facilityList.size(); i++) {
            entity.Facility f = facilityList.get(i);
            
            boolean matchesId = f.getFacilityId().toLowerCase().contains(id.toLowerCase());
            boolean matchesType = (type == null || type.equals("ALL") || f.getType().equals(type));
            
            if (matchesId && matchesType) {
                searchResults.add(f); 
            }
        }
        
        return searchResults;
    }
    
    public adt.ListInterface<entity.Facility> searchFacility(String id) {
        
        return searchFacility(id, "ALL"); 
        
    }
}