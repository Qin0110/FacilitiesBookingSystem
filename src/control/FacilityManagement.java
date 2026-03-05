package control;

import adt.ArrayList;
import adt.ListInterface;
import dao.FacilityDAO;
import entity.Facility;

/**
 * Controls the logic for managing facilities.
 */
public class FacilityManagement {

    private ListInterface<Facility> facilityList;
    private FacilityDAO facilityDAO = new FacilityDAO();
    
    public FacilityManagement(){
    }

    public FacilityManagement(ListInterface<Facility> sharedList) {
        this.facilityList = sharedList;
    }

    public String addNewFacility(String id, String name, String type, String location, int capacity){
        
        String message; 
        
        if (!facilityDAO.isFacilityExists(id)) 
           return ("Error: A facility with ID '" + id + "' already exists.");

        entity.Facility newFacility = new entity.Facility(id, name, type, location, capacity);

        message = facilityDAO.addFacility(newFacility);
              
        return message;
    }
    
    public ArrayList<entity.Facility> getAllFacilities() {
        return facilityDAO.getAllFacilities();
    }

   
    public boolean removeFacility(String facilityId) {
        return facilityDAO.deleteFacility(facilityId);
    }
    
        public String updateFacility(String id, String name, String type, String location, int capacity){
        entity.Facility updatedFacility = new entity.Facility(id, name, type, location, capacity);
        return facilityDAO.updateFacility(updatedFacility);
    }
    
        
    public ArrayList<Facility> searchFacility(String id){
        return facilityDAO.getFacility(id);
    }
    
    
    
}