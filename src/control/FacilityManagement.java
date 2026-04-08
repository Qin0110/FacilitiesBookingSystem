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
                return monthNames[month] + " " + year; 
            }
        }
 
        return "UNKNOWN MONTH";
    }
   
   public adt.ListInterface<String> getAvailableYears(adt.ListInterface<entity.Booking> allBookings) {
        adt.ListInterface<String> availableYears = new adt.SingleLinkedList<>();
        
        for (int i = 0; i < allBookings.size(); i++) {
            entity.Booking b = allBookings.get(i);
            try {
                String dateStr = b.getScheduleSession().split(" ")[0]; 
                String year = dateStr.split("-")[2];
                
                boolean found = false;
                for (int j = 0; j < availableYears.size(); j++) {
                    if (availableYears.get(j).equals(year)) {
                        found = true;
                        break;
                    }
                }
                
                if (!found) {
                    availableYears.add(year);
                }
            } catch (Exception e) {
            }
        }
        
        for (int i = 1; i < availableYears.size(); i++) {
            String currentYear = availableYears.get(i);
            int j = i - 1;
            
            while (j >= 0 && availableYears.get(j).compareTo(currentYear) < 0) {
                availableYears.set(j + 1, availableYears.get(j));
                j--;
            }
            availableYears.set(j + 1, currentYear);
        }
        
        return availableYears;
    }
   
    private String getMonthName(String monthNumber) {
        try {
            int m = Integer.parseInt(monthNumber);
            String[] monthNames = {"", "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
            if (m >= 1 && m <= 12) return monthNames[m];
        } catch (Exception e) {}
        return monthNumber;
    }
  
    
    public String generateFacilityPopularityReport(adt.ListInterface<entity.Booking> allBookings, String targetDay, String targetMonth, String targetYear) {
        
        targetDay = (targetDay == null || targetDay.equalsIgnoreCase("none") || targetDay.trim().isEmpty()) ? null : targetDay;
        targetMonth = (targetMonth == null || targetMonth.equalsIgnoreCase("none") || targetMonth.trim().isEmpty()) ? null : targetMonth;
        targetYear = (targetYear == null || targetYear.equalsIgnoreCase("none") || targetYear.trim().isEmpty()) ? null : targetYear;

        String timeframeLabel = "";
        if (targetDay == null && targetMonth == null && targetYear == null) {
            timeframeLabel = "All Time";
        } else {
            if (targetDay != null) timeframeLabel += targetDay + " ";
            if (targetMonth != null) timeframeLabel += getMonthName(targetMonth) + " ";
            if (targetYear != null) timeframeLabel += targetYear;
        }

        StringBuilder report = new StringBuilder();
        
        report.append("+---------------------------------------------------+\n");
        report.append("|            FACILITY POPULARITY REPORT             |\n");
        report.append("+---------------------------------------------------+\n");
        report.append(String.format("| Timeframe: %-38s |\n", timeframeLabel.trim()));

        if (allBookings.isEmpty()) {
            report.append("| Total Valid Bookings: 0                           |\n");
            report.append("+---------------------------------------------------+\n\n");
            report.append("  No bookings have been made in the system yet.\n");
            return report.toString();
        }

        adt.ListInterface<String> facilityTypes = new adt.SingleLinkedList<>();
        adt.ListInterface<Integer> typeCounts = new adt.SingleLinkedList<>();
        int validBookings = 0;

        for (int i = 0; i < allBookings.size(); i++) {
            entity.Booking b = allBookings.get(i);
            
            try {
                String dateStr = b.getScheduleSession().split(" ")[0]; 
                String[] parts = dateStr.split("-");
                String bDay = parts[0];
                String bMonth = parts[1];
                String bYear = parts[2];

                if (targetDay != null && !targetDay.equals(bDay)) continue;
                if (targetMonth != null && !targetMonth.equals(bMonth)) continue;
                if (targetYear != null && !targetYear.equals(bYear)) continue;

                if (b.getSchedule() != null && b.getSchedule().getFacility() != null) {
                    String type = b.getSchedule().getFacility().getType();
                    validBookings++;
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
            } catch (Exception e) {
            }
        }

        report.append(String.format("| Total Valid Bookings: %-27d |\n", validBookings));
        report.append("+---------------------------------------------------+\n\n");

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

        if (facilityTypes.isEmpty() || validBookings == 0) {
            report.append("  No facility data matches this specific timeframe.\n");
        } else {
            report.append("[ ACTIVITY BREAKDOWN ]\n\n");
            for (int i = 0; i < facilityTypes.size(); i++) {
                int count = typeCounts.get(i);
                String type = facilityTypes.get(i);
                
                double percentage = ((double) count / validBookings) * 100;
                
                int filledBars = (int) (percentage / 5); 
                StringBuilder barChart = new StringBuilder();
              
                
                report.append(String.format("  %-28s [ %2d ]  %5.1f%%  %s\n", 
                        type, count, percentage, barChart.toString()));
            }
        }

        report.append("\n+---------------------------------------------------+\n");
        report.append("|                  End of Report                    |\n");
        report.append("+---------------------------------------------------+\n");

        return report.toString();
    }
    
    private double calculateBookingDuration(String startTimeStr, String endTimeStr) {
        try {
            String[] startParts = startTimeStr.split(":");
            String[] endParts = endTimeStr.split(":");

            double startHour = Double.parseDouble(startParts[0]);
            double startMin = Double.parseDouble(startParts[1]) / 60.0; 

            double endHour = Double.parseDouble(endParts[0]);
            double endMin = Double.parseDouble(endParts[1]) / 60.0;

            double duration = (endHour + endMin) - (startHour + startMin);
            
            return duration > 0 ? duration : 0; 
        } catch (Exception e) {
            return 0; 
        }
    }
    
   public String generateFacilityUtilizationReport(adt.ListInterface<entity.Booking> allBookings, String targetDay, String targetMonth, String targetYear) {
        
        targetDay = (targetDay == null || targetDay.equalsIgnoreCase("none") || targetDay.trim().isEmpty()) ? null : targetDay;
        targetMonth = (targetMonth == null || targetMonth.equalsIgnoreCase("none") || targetMonth.trim().isEmpty()) ? null : targetMonth;
        targetYear = (targetYear == null || targetYear.equalsIgnoreCase("none") || targetYear.trim().isEmpty()) ? null : targetYear;

        String timeframeLabel = "";
        if (targetDay == null && targetMonth == null && targetYear == null) {
            timeframeLabel = "All Time";
        } else {
            if (targetDay != null) timeframeLabel += targetDay + " ";
            if (targetMonth != null) timeframeLabel += getMonthName(targetMonth) + " ";
            if (targetYear != null) timeframeLabel += targetYear;
        }

        adt.ListInterface<String> facilityNames = new adt.SingleLinkedList<>();
        adt.ListInterface<Double> facilityHours = new adt.SingleLinkedList<>();
        
        int validSessionsCount = 0;
        double totalSystemHours = 0;

        for (int i = 0; i < allBookings.size(); i++) {
            entity.Booking b = allBookings.get(i);
            
            String status = b.getStatus();
            if (status.equalsIgnoreCase("Cancelled") || 
                status.equalsIgnoreCase("Not Attended") || 
                status.equalsIgnoreCase("Pending")) {
                continue; 
            }

            
            String dateStr = b.getScheduleSession().split(" ")[0]; 
            String[] dateParts = dateStr.split("-");
            String bDay = dateParts[0];
            String bMonth = dateParts[1];
            String bYear = dateParts[2];

            if (targetDay != null && !targetDay.equals(bDay)) continue;
            if (targetMonth != null && !targetMonth.equals(bMonth)) continue;
            if (targetYear != null && !targetYear.equals(bYear)) continue;

            String[] timeParts = b.getScheduleSession().split(" ");
            if (timeParts.length >= 4) {
                String startTime = timeParts[1];
                String endTime = timeParts[3];  

                double duration = calculateBookingDuration(startTime, endTime);

                if (duration > 0 && b.getSchedule() != null && b.getSchedule().getFacility() != null) {
                    validSessionsCount++;
                    totalSystemHours += duration;

                    String rawName = b.getSchedule().getFacility().getName();
                    String combinedName = rawName.replaceAll("[0-9\\-]", "").trim();

                    boolean found = false;
                    for (int j = 0; j < facilityNames.size(); j++) {
                        if (facilityNames.get(j).equalsIgnoreCase(combinedName)) {
                            facilityHours.set(j, facilityHours.get(j) + duration);
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        facilityNames.add(combinedName);
                        facilityHours.add(duration);
                    }
                }
            }
           
        }

        for (int i = 1; i < facilityNames.size(); i++) {
            double currentHours = facilityHours.get(i);
            String currentName = facilityNames.get(i);
            
            int j = i - 1;
            while (j >= 0 && facilityHours.get(j) < currentHours) {
                facilityHours.set(j + 1, facilityHours.get(j));
                facilityNames.set(j + 1, facilityNames.get(j));
                j--;
            }
            facilityHours.set(j + 1, currentHours);
            facilityNames.set(j + 1, currentName);
        }

        StringBuilder report = new StringBuilder();
        report.append("=========================================================\n");
        report.append("              FACILITY UTILIZATION REPORT                \n");
        report.append("=========================================================\n");
        report.append(">> Timeframe                       : ").append(timeframeLabel.trim()).append("\n");
        report.append(String.format(">> Total Active Sessions Parsed    : %d\n", validSessionsCount));
        report.append(String.format(">> Total Accumulated System Time   : %.1f Hours\n\n", totalSystemHours));

        if (facilityNames.isEmpty()) {
            report.append(">> No active usage data available for this timeframe.\n\n");
        } else {
            report.append("---------------------------------------------------------\n");
            report.append(String.format(" %-35s | %-15s \n", "Facility Category", "Total Hours Used"));
            report.append("---------------------------------------------------------\n");
            
            for (int i = 0; i < facilityNames.size(); i++) {
                String name = facilityNames.get(i);
                double hours = facilityHours.get(i);
                
                if (name.length() > 33) name = name.substring(0, 30) + "...";
                
                report.append(String.format(" %-35s | %8.1f hrs \n", name, hours));
            }
            report.append("---------------------------------------------------------\n");
        }

        return report.toString();
    }
   
    public String generateOperationalEfficiencyReport(adt.ListInterface<entity.Booking> allBookings, String targetMonth, String targetYear) {
        
        targetMonth = (targetMonth == null || targetMonth.equalsIgnoreCase("none") || targetMonth.trim().isEmpty()) ? null : targetMonth;
        targetYear = (targetYear == null || targetYear.equalsIgnoreCase("none") || targetYear.trim().isEmpty()) ? null : targetYear;

        String timeframeLabel = (targetMonth == null && targetYear == null) ? "All Time" : 
                                ((targetMonth != null ? targetMonth + " " : "") + (targetYear != null ? targetYear : "")).trim();

        adt.ListInterface<String> facilityNames = new adt.SingleLinkedList<>();
        adt.ListInterface<Double> usedHours = new adt.SingleLinkedList<>();
        adt.ListInterface<Double> wastedHours = new adt.SingleLinkedList<>(); 
        
        double totalSystemWastedHours = 0;

        for (int i = 0; i < allBookings.size(); i++) {
            entity.Booking b = allBookings.get(i);
            
            String status = b.getStatus().toUpperCase();
            if (status.equals("PENDING") || status.equals("IN PROGRESS")) continue;

            try {
                String dateStr = b.getScheduleSession().split(" ")[0]; 
                String[] dateParts = dateStr.split("-");
                if (targetMonth != null && !targetMonth.equals(dateParts[1])) continue;
                if (targetYear != null && !targetYear.equals(dateParts[2])) continue;

                String[] timeParts = b.getScheduleSession().split(" ");
                if (timeParts.length >= 4) {
                    double duration = calculateBookingDuration(timeParts[1], timeParts[3]);
                    
                    if (duration > 0 && b.getSchedule() != null && b.getSchedule().getFacility() != null) {
                        
                        String rawName = b.getSchedule().getFacility().getName();
                        String combinedName = rawName.replaceAll("[0-9\\-]", "").trim(); 
                        
                        boolean isWasted = status.equals("CANCELLED") || status.equals("NOT ATTENDED");
                        if (isWasted) totalSystemWastedHours += duration;

                        boolean found = false;
                        for (int j = 0; j < facilityNames.size(); j++) {
                            if (facilityNames.get(j).equalsIgnoreCase(combinedName)) {
                                if (isWasted) {
                                    wastedHours.set(j, wastedHours.get(j) + duration);
                                } else {
                                    usedHours.set(j, usedHours.get(j) + duration);
                                }
                                found = true;
                                break;
                            }
                        }

                        if (!found) {
                            facilityNames.add(combinedName);
                            usedHours.add(isWasted ? 0.0 : duration);
                            wastedHours.add(isWasted ? duration : 0.0);
                        }
                    }
                }
            } catch (Exception e) { }
        }

        for (int i = 1; i < facilityNames.size(); i++) {
            double currentWaste = wastedHours.get(i);
            double currentUsed = usedHours.get(i);
            String currentName = facilityNames.get(i);
            
            int j = i - 1;
            while (j >= 0 && wastedHours.get(j) < currentWaste) {
                wastedHours.set(j + 1, wastedHours.get(j));
                usedHours.set(j + 1, usedHours.get(j));
                facilityNames.set(j + 1, facilityNames.get(j));
                j--;
            }
            wastedHours.set(j + 1, currentWaste);
            usedHours.set(j + 1, currentUsed);
            facilityNames.set(j + 1, currentName);
        }

        StringBuilder report = new StringBuilder();
        report.append("===========================================================================\n");
        report.append("              OPERATIONAL EFFICIENCY & RESOURCE WASTE REPORT               \n");
        report.append("===========================================================================\n");
        report.append(">> Timeframe : ").append(timeframeLabel).append("\n");
        report.append(">> Metric    : 'Wasted Hours' = Cancelled or No-Show Bookings\n");
        report.append(String.format(">> Total System Wasted Time : %.1f Hours\n\n", totalSystemWastedHours));

        if (facilityNames.isEmpty()) {
            report.append("  No historical booking data available for this timeframe.\n\n");
        } else {
            report.append("---------------------------------------------------------------------------\n");
            report.append(String.format(" %-24s | %-10s | %-10s | %-15s \n", "Facility Category", "Used Hrs", "Wasted Hrs", "Efficiency Rate"));
            report.append("---------------------------------------------------------------------------\n");
            
            for (int i = 0; i < facilityNames.size(); i++) {
                String name = facilityNames.get(i);
                double used = usedHours.get(i);
                double wasted = wastedHours.get(i);
                double total = used + wasted;
                
                double efficiency = (total == 0) ? 0 : (used / total) * 100;
                
                if (name.length() > 22) name = name.substring(0, 19) + "...";
                
                String alert = (efficiency < 75.0 && wasted > 0) ? "[POOR]" : "[OK]";
                
                report.append(String.format(" %-24s | %8.1f   | %8.1f   | %6.1f%% %-8s \n", name, used, wasted, efficiency, alert));
            }
            report.append("---------------------------------------------------------------------------\n");
            
            if (totalSystemWastedHours > 50.0) {
                report.append("\n [!] SYSTEM WARNING: High rate of resource waste detected.\n");
                report.append("     Recommendation: Implement temporary booking bans for repeat No-Shows.\n");
                report.append("\n===========================================================================\n");

                
            }
        }

        return report.toString();
    }
   
    
    
}
 