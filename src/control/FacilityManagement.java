package control;

 
import dao.FacilityCollection;
import adt.ListInterface;
import adt.SingleLinkedList;
import entity.Facility;
 
public class FacilityManagement {
 
    private FacilityCollection facilityCollection;
 
    public FacilityManagement() {
        this.facilityCollection = new FacilityCollection();
    }
 
    public FacilityManagement(FacilityCollection sharedCollection) {
        this.facilityCollection = sharedCollection;
    }
 
    public String addNewFacility(String id, String name, String type, String location, int minCapacity, int maxCapacity) {

        if (id == null || id.isBlank())
            return "Error: Facility ID cannot be empty";
        if (id.length() > 15)
            return "Error: Facility ID length must be between 1 - 15";
        if (facilityCollection.isFacilityExists(id))
            return "Error: A facility with ID '" + id + "' already exists.";
        if (name == null || name.isBlank())
            return "Error: Facility Name cannot be empty";
        if (name.length() > 150)
            return "Error: Facility Name length must be between 1 - 150";
        if (minCapacity > maxCapacity)
            return "Error: Min Capacity cannot be greater than Max Capacity";
 
        Facility newFacility = new Facility(id, name, type, location, minCapacity, maxCapacity);
 
        if (facilityCollection.add(newFacility)) {
            return "Facility Successfully Added";
        } else {
            return "Error: Could not add facility.";
        }
    }
 
    public ListInterface<Facility> getAllFacilities() {
        return facilityCollection.getAllFacilities();
    }
 
    public String removeFacility(String facilityId) {
        if (facilityCollection.remove(facilityId)) {
            return "Facility Successfully Removed";
        }
        return "Error: Facility not found.";
    }
 
    public String updateFacility(String id, String name, String type, String location, int minCapacity, int maxCapacity) {
        if (name == null || name.isBlank())
            return "Error: Facility Name cannot be empty";
        if (name.length() > 150)
            return "Error: Facility Name length must be between 1 - 150";
        if (minCapacity > maxCapacity)
            return "Error: Min Capacity cannot be greater than Max Capacity";
 
        Facility updatedFacility = new Facility(id, name, type, location, minCapacity, maxCapacity);
 
        if (facilityCollection.set(updatedFacility)) {
            return "Facility Successfully Updated";
        }
        return "Error: Facility not found.";
    }
 
    public ListInterface<Facility> searchFacility(String keyword, String type) {
        ListInterface<Facility> results = new SingleLinkedList<>();
        ListInterface<Facility> facilityList = facilityCollection.getAllFacilities();

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
    
    public Facility getFacilityById(String facilityId) {
        return facilityCollection.getFacilityById(facilityId);
    }
 
    public ListInterface<Facility> searchFacility(String id) {
        return searchFacility(id, "ALL");
    }
    
    public ListInterface<Facility> sortFacilities(String sortBy, String order) {
        ListInterface<Facility> list = facilityCollection.getAllFacilities();
        int n = list.size();

        for (int i = 0; i < n - 1; i++) {
            int indexOfSmallest = i;

            for (int j = i + 1; j < n; j++) {
                int cmp = compareBy(list.get(j), list.get(indexOfSmallest), sortBy);

                if (order.equalsIgnoreCase("desc")) cmp = -cmp;

                if (cmp < 0) {
                    indexOfSmallest = j;
                }
            }

            if (indexOfSmallest != i) {
                Facility temp = list.get(i);
                list.set(i,              list.get(indexOfSmallest));
                list.set(indexOfSmallest, temp);
            }
        }
        return list;
    }
    private int compareBy(Facility a, Facility b, String attribute) {
        switch (attribute.toLowerCase()) {
            case "id":       
                return a.getFacilityId().compareToIgnoreCase(b.getFacilityId());
            case "name":     
                return a.getName().compareToIgnoreCase(b.getName());
            case "type":     
                return a.getType().compareToIgnoreCase(b.getType());
            case "location": 
                return a.getLocation().compareToIgnoreCase(b.getLocation());
            case "capacity": 
                return Integer.compare(a.getMaxCapacity(), b.getMaxCapacity());
            default:         
                return 0;
        }
    }
}
 