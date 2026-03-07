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

    public String addNewFacility(String id, String name, String type, String location, int minCapacity, int maxCapacity){
        
        String message; 
        if(id.isBlank() || id.isEmpty())
            return ("Error: Facility ID cannot be Empty");
        else if(id.length()< 0 || id.length()> 15)
            return ("Error: Facility ID length must in between 1 - 15 ");
        else if (facilityDAO.isFacilityExists(id)) 
           return ("Error: A facility with ID '" + id + "' already exists.");
       
        if(name.isBlank() ||name.isEmpty() || name.trim().equals(""))
            return ("Error: Facility Name cannot be empty");
        else if(name.length() < 0 || name.length() > 150)
            return ("Error: Facility Name length must in between 1 - 150");
        

        entity.Facility newFacility = new entity.Facility(id, name, type, location, minCapacity, maxCapacity);

        message = facilityDAO.addFacility(newFacility);
              
        return message;
    }
    
    public ArrayList<entity.Facility> getAllFacilities() {
        return facilityDAO.getAllFacilities();
    }

   
    public String removeFacility(String facilityId) {
        return facilityDAO.deleteFacility(facilityId);
    }
    
        public String updateFacility(String id, String name, String type, String location, int minCapacity, int maxCapacity){
        entity.Facility updatedFacility = new entity.Facility(id, name, type, location, minCapacity, maxCapacity);
        return facilityDAO.updateFacility(updatedFacility);
    }
    
        
    public ArrayList<Facility> searchFacility(String id){
        return facilityDAO.getFacility(id);
    }
    
    
    
}