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
    
    public Facility getFacilityById(String facilityId) {
        return facilityCollection.getFacilityById(facilityId);
    }
 
    public ListInterface<Facility> searchFacility(String keyword, String type) {
        return facilityCollection.searchFacility(keyword, type);
    }
    
    public ListInterface<Facility> searchFacility(String id) {
        return facilityCollection.searchFacility(id);
    }
    
    public void sortFacilities(String sortBy, String order) {
        facilityCollection.sortFacilities(sortBy, order);
    }
    
    public String generateDistributionCSV() {
        adt.ListInterface<entity.Facility> list = facilityCollection.getAllFacilities();
        adt.ListInterface<String> uniqueTypes = new adt.SingleLinkedList<>();

        for (int i = 0; i < list.size(); i++) {
            String type = list.get(i).getType();
            boolean exists = false;
            for (int j = 0; j < uniqueTypes.size(); j++) {
                if (uniqueTypes.get(j).equalsIgnoreCase(type)) {
                    exists = true; break;
                }
            }
            if (!exists) uniqueTypes.add(type);
        }

        StringBuilder csv = new StringBuilder();
        csv.append("Facility Type,Total Quantity,Combined Capacity\n");

        for (int i = 0; i < uniqueTypes.size(); i++) {
            String currentType = uniqueTypes.get(i);
            int count = 0;
            int totalCapacity = 0;

            for (int j = 0; j < list.size(); j++) {
                entity.Facility f = list.get(j);
                if (f.getType().equalsIgnoreCase(currentType)) {
                    count++;
                    totalCapacity += f.getMaxCapacity();
                }
            }
            
            csv.append(currentType).append(",")
               .append(count).append(",")
               .append(totalCapacity).append("\n");
        }

        return csv.toString();
    }
    
    public String generateDistributionReport() {
        adt.ListInterface<Facility> list = facilityCollection.getAllFacilities();
        adt.ListInterface<String> uniqueTypes = new adt.SingleLinkedList<>();

        for (int i = 0; i < list.size(); i++) {
            String type = list.get(i).getType();
            boolean exists = false;

            for (int j = 0; j < uniqueTypes.size(); j++) {
                if (uniqueTypes.get(j).equalsIgnoreCase(type)) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                uniqueTypes.add(type);
            }
        }

        StringBuilder report = new StringBuilder();
        report.append("=====================================================\n");
        report.append("               FACILITY DISTRIBUTION REPORT\n");
        report.append("=====================================================\n\n");

        int grandTotalFacilities = 0;
        int grandTotalCapacity = 0;

        for (int i = 0; i < uniqueTypes.size(); i++) {
            String currentType = uniqueTypes.get(i);
            int count = 0;
            int totalCapacity = 0;

            for (int j = 0; j < list.size(); j++) {
                Facility f = list.get(j);
                if (f.getType().equalsIgnoreCase(currentType)) {
                    count++;
                    totalCapacity += f.getMaxCapacity();
                }
            }

            report.append(String.format("Facility Type : %s\n", currentType.toUpperCase()));
            report.append(String.format("  -> Total Quantity    : %d facilities\n", count));
            report.append(String.format("  -> Combined Capacity : %d people\n", totalCapacity));
            report.append("-----------------------------------------------------\n");

            grandTotalFacilities += count;
            grandTotalCapacity += totalCapacity;
        }

        report.append(String.format("\nGRAND TOTAL FACILITIES : %d\n", grandTotalFacilities));
        report.append(String.format("GRAND TOTAL CAPACITY   : %d people\n", grandTotalCapacity));
        report.append("=====================================================\n");
        report.append("          End of Report - Generated Successfully\n");

        return report.toString();
    }
}
 