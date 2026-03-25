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
    
    public String generateDistributionCSV() {
        adt.ListInterface<entity.Facility> list = facilityCollection.getAllFacilities();
        adt.ListInterface<String> uniqueTypes = new adt.SingleLinkedList<>();

        // PHASE 1: Find unique types
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

        // PHASE 2: Build the CSV String
        StringBuilder csv = new StringBuilder();
        // The CSV Header row
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
            
            // Append the data separated by commas
            csv.append(currentType).append(",")
               .append(count).append(",")
               .append(totalCapacity).append("\n");
        }

        return csv.toString();
    }
    public String generateDistributionReport() {
        adt.ListInterface<Facility> list = facilityCollection.getAllFacilities();
        adt.ListInterface<String> uniqueTypes = new adt.SingleLinkedList<>();

        // PHASE 1: Find all unique Facility Types
        for (int i = 0; i < list.size(); i++) {
            String type = list.get(i).getType();
            boolean exists = false;

            // Check if we already found this type
            for (int j = 0; j < uniqueTypes.size(); j++) {
                if (uniqueTypes.get(j).equalsIgnoreCase(type)) {
                    exists = true;
                    break;
                }
            }
            // If it's a new type, add it to our unique list
            if (!exists) {
                uniqueTypes.add(type);
            }
        }

        // PHASE 2 & 3: Calculate stats and build the text report
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

            // Search the whole database to count this specific type
            for (int j = 0; j < list.size(); j++) {
                Facility f = list.get(j);
                if (f.getType().equalsIgnoreCase(currentType)) {
                    count++;
                    totalCapacity += f.getMaxCapacity();
                }
            }

            // Append the stats to the report
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
 