/**
 * @author Lee Guan Wei
 */


package control;

import dao.FacilityCollection;
import adt.ListInterface;
import adt.SingleLinkedList;
import entity.Facility;
 
public class FacilityManagement {
 
    private FacilityCollection facilityCollection;
    
    private String currentSort1 = "ID";    
    private String currentSort2 = "None";   
    private String currentOrder = "ASC";
 
    public FacilityManagement() {
        this.facilityCollection = new FacilityCollection();
    }
 
    public FacilityManagement(FacilityCollection sharedCollection) {
        this.facilityCollection = sharedCollection;
    }
    
    public String getCurrentSort1() { 
        return currentSort1; 
    }
    
    public String getCurrentSort2() {
        return currentSort2; 
    }
    
    public String getCurrentOrder() {
        return currentOrder; 
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
    
    public void sortFacilities(String sortBy1, String sortBy2, String order) {
        
        this.currentSort1 = sortBy1;
        this.currentSort2 = (sortBy2 == null || sortBy2.isEmpty()) ? "None" : sortBy2;
        this.currentOrder = order;

        adt.ListInterface<entity.Facility> allFacilities = facilityCollection.getAllFacilities();

        allFacilities.sort(new adt.Comparator<entity.Facility>() {
            @Override
            public int compare(entity.Facility a, entity.Facility b) {
                
                int cmp = compareSingleAttribute(a, b, sortBy1);

                if (cmp == 0 && sortBy2 != null && !sortBy2.isEmpty()) {
                    cmp = compareSingleAttribute(a, b, sortBy2);
                }

                if (order.equalsIgnoreCase("desc")) {
                    cmp = -cmp; 
                }

                return cmp;
            }
        });
    }

    private int compareSingleAttribute(entity.Facility a, entity.Facility b, String attribute) {
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
    
   private String extractMonthYear(String dateStr) {
        String[] parts = dateStr.split("-");
        if (parts.length >= 3) {
            int month = Integer.parseInt(parts[1]);
            String year = parts[2];
            String[] monthNames = {"", "JAN", "FEB", "MAR", "APR", "MAY", "JUN", 
                                   "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
            if (month >= 1 && month <= 12) {
                return monthNames[month] + " " + year; // Returns "MAR 2026"
            }
        }
 
        return "UNKNOWN MONTH";
    }
  
    
    public String generateFacilityPopularityReport(adt.ListInterface<entity.Booking> allBookings) {
        StringBuilder report = new StringBuilder();
        report.append("=====================================================\n");
        report.append("       MONTHLY FACILITY POPULARITY REPORT\n");
        report.append("=====================================================\n\n");
        report.append(String.format("Total Bookings Analyzed : %d\n\n", allBookings.size()));

        if (allBookings.isEmpty()) {
            report.append("No bookings have been made yet.\n");
            return report.toString();
        }

        // =========================================================
        // 1. FIND ALL UNIQUE MONTHS IN THE SYSTEM
        // =========================================================
        adt.ListInterface<String> uniqueMonths = new adt.SingleLinkedList<>();
        for (int i = 0; i < allBookings.size(); i++) {
            entity.Booking b = allBookings.get(i);
            
            // Grab the date portion of the session (e.g., "27-03-2026")
            String dateStr = b.getScheduleSession().split(" ")[0]; 
            String monthYear = extractMonthYear(dateStr);

            boolean found = false;
            for (int j = 0; j < uniqueMonths.size(); j++) {
                if (uniqueMonths.get(j).equals(monthYear)) {
                    found = true; break;
                }
            }
            if (!found) {
                uniqueMonths.add(monthYear);
            }
        }

        // =========================================================
        // 2. GENERATE STATS FOR EACH MONTH INDIVIDUALLY
        // =========================================================
        for (int m = 0; m < uniqueMonths.size(); m++) {
            String targetMonth = uniqueMonths.get(m);
            report.append(String.format("--- %s ---\n", targetMonth));

            adt.ListInterface<String> facilityTypes = new adt.SingleLinkedList<>();
            adt.ListInterface<Integer> typeCounts = new adt.SingleLinkedList<>();
            int monthlyTotal = 0;

            // Group and count ONLY for the current target month
            for (int i = 0; i < allBookings.size(); i++) {
                entity.Booking b = allBookings.get(i);
                String dateStr = b.getScheduleSession().split(" ")[0];
                String bMonthYear = extractMonthYear(dateStr);

                // If the booking matches the month we are currently analyzing...
                if (bMonthYear.equals(targetMonth) && b.getSchedule() != null && b.getSchedule().getFacility() != null) {
                    String type = b.getSchedule().getFacility().getType();
                    monthlyTotal++;
                    boolean found = false;

                    for (int j = 0; j < facilityTypes.size(); j++) {
                        if (facilityTypes.get(j).equalsIgnoreCase(type)) {
                            typeCounts.set(j, typeCounts.get(j) + 1);
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        facilityTypes.add(type);
                        typeCounts.add(1);
                    }
                }
            }

            // Sort the data for this specific month (Highest to lowest)
            for (int i = 1; i < facilityTypes.size(); i++) {
                int currentCount = typeCounts.get(i);
                String currentType = facilityTypes.get(i);

                int j = i - 1;
                while (j >= 0 && typeCounts.get(j) < currentCount) {
                    typeCounts.set(j + 1, typeCounts.get(j));
                    facilityTypes.set(j + 1, facilityTypes.get(j));
                    j--;
                }
                typeCounts.set(j + 1, currentCount);
                facilityTypes.set(j + 1, currentType);
            }

            // Append this month's results to the overall report
            if (facilityTypes.isEmpty()) {
                report.append("  No facility data for this month.\n");
            } else {
                for (int i = 0; i < facilityTypes.size(); i++) {
                    report.append(String.format("  %-28s : %d bookings\n",
                            facilityTypes.get(i),
                            typeCounts.get(i)));
                }
            }
            report.append(String.format("  [Total %s Bookings: %d]\n\n", targetMonth, monthlyTotal));
        }

        report.append("=====================================================\n");
        report.append("                   End of Report\n");

        return report.toString();
    }
    
    
}
 