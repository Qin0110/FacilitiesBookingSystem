/**
 * @author Lee Ka Qin
 */

package boundary;

import adt.ListInterface;
import control.ScheduleControl;
import entity.Schedule;
import entity.ScheduleSession;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ScheduleUI extends javax.swing.JPanel {

    private ScheduleControl myControl;
    private ListInterface<Schedule> allSchedule;
    
    private control.BookingManagement bookingManager;
    
    public ScheduleUI(ScheduleControl sharedControl,control.BookingManagement bookingManager) {
        initComponents();
        this.myControl = sharedControl;
        this.allSchedule = myControl.getAllSchedule();
        
        this.bookingManager = bookingManager;
        
        sortingComboBox.removeAllItems();
        sortingComboBox.addItem("Default (Chronological)");
        sortingComboBox.addItem("By Facility & Status");
        
        scheduleTable.setShowGrid(true);
        scheduleTable.setGridColor(Color.LIGHT_GRAY);
        GroupBorderRenderer renderer = new GroupBorderRenderer(2, true);       
        for (int i = 0; i < scheduleTable.getColumnCount(); i++) {
            scheduleTable.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }        
        
        dateCB.removeAllItems(); 
        for (int i = 0; i < allSchedule.size(); i++) {
            String dateString = allSchedule.get(i).getDate();
            
            boolean exists = false;
            for (int j = 0; j < dateCB.getItemCount(); j++) {
                if (dateCB.getItemAt(j).equals(dateString)) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                dateCB.addItem(dateString);
            }
        }
        
        java.time.LocalDate today = java.time.LocalDate.now();
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String todayString = today.format(formatter);
        
        dateCB.setSelectedItem(todayString);
        
      
        if (dateCB.getSelectedIndex() == -1 && dateCB.getItemCount() > 0) {
            dateCB.setSelectedIndex(0);
        }
        
        if (dateCB.getSelectedItem() != null) {
            updateTableForDate((String) dateCB.getSelectedItem());
        }
    }
    
    private void updateTableForDate(String targetDate) {
        this.allSchedule = myControl.getAllSchedule();
        DefaultTableModel model = (DefaultTableModel) scheduleTable.getModel();
        model.setRowCount(0); // Clear the current rows
        
        for (int i = 0; i < allSchedule.size(); i++) {
            Schedule currentSchedule = allSchedule.get(i);
            
            // Only add rows if the schedule's date matches the dropdown date!
            if (currentSchedule.getDate().equals(targetDate)) {
                String facilityName = currentSchedule.getFacility().getName();
                ListInterface<ScheduleSession> sessions = currentSchedule.getSessions();
                
                // Note: I changed this to j = 0 so it doesn't skip your 08:00 session!
                for (int j = 0; j < sessions.size(); j++) { 
                    ScheduleSession currentSession = sessions.get(j);
                    
                    model.addRow(new Object[]{facilityName, currentSession.getSession(), currentSession.getAvailability()});
                    facilityName = ""; // Clear so it only shows on the first row of the group
                }
            }
        }
    }
    
    private void displaySearchResults(ListInterface<Schedule> searchResults, String targetDate) {
        DefaultTableModel model = (DefaultTableModel) scheduleTable.getModel();
        model.setRowCount(0);
        
        for (int i = 0; i < searchResults.size(); i++) {
            Schedule currentSchedule = searchResults.get(i);
            
            if (currentSchedule.getDate().equals(targetDate)) {
                String facilityName = currentSchedule.getFacility().getName();
                ListInterface<ScheduleSession> sessions = currentSchedule.getSessions();
                
                for (int j = 0; j < sessions.size(); j++) { 
                    ScheduleSession currentSession = sessions.get(j);
                    
                    model.addRow(new Object[]{facilityName, currentSession.getSession(), currentSession.getAvailability()});
                    facilityName = ""; 
                }
            }
        }
    }
     
    private String getFacilityForSelectedRow(int row) {
        for (int i = row; i >= 0; i--) {
            String val = (String) scheduleTable.getValueAt(i, 0);
            if (val != null && !val.trim().isEmpty()) {
                return val;
            }
        }
        return "";
    }


    private int findScheduleIndex(String facilityName, String date) {
        for (int i = 0; i < allSchedule.size(); i++) {
            Schedule s = allSchedule.get(i);
            if (s.getFacility().getName().equals(facilityName) && s.getDate().equals(date)) {
                return i;
            }
        }
        return -1;
    }


    private int findTimeIndex(int scheduleIndex, String timeString) {
        ListInterface<ScheduleSession> sessions = allSchedule.get(scheduleIndex).getSessions();
        for (int i = 0; i < sessions.size(); i++) {
            if (sessions.get(i).getSession().equals(timeString)) {
                return i;
            }
        }
        return -1;
    }


    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        scheduleTable = new javax.swing.JTable();
        dateCB = new javax.swing.JComboBox<>();
        sortingComboBox = new javax.swing.JComboBox<>();
        searchTextField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        deleteFacilitySchedule = new javax.swing.JButton();
        deleteScheduleSession = new javax.swing.JButton();
        modifyScheduleSession = new javax.swing.JButton();
        annualReport = new javax.swing.JButton();
        peakHourAnalysis = new javax.swing.JButton();

        scheduleTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Facilitiy", "Time", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scheduleTable.setToolTipText("");
        jScrollPane1.setViewportView(scheduleTable);

        dateCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateCBActionPerformed(evt);
            }
        });

        sortingComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        sortingComboBox.setSelectedIndex(-1);
        sortingComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortingComboBoxActionPerformed(evt);
            }
        });

        searchTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTextFieldActionPerformed(evt);
            }
        });

        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(dateCB, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(searchButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sortingComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateCB, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(sortingComboBox)
                    .addComponent(searchButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchTextField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                .addContainerGap())
        );

        deleteFacilitySchedule.setText("Delete Facility Schedule");
        deleteFacilitySchedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteFacilityScheduleActionPerformed(evt);
            }
        });

        deleteScheduleSession.setText("Delete Schedule Session");
        deleteScheduleSession.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteScheduleSessionActionPerformed(evt);
            }
        });

        modifyScheduleSession.setText("Modify Schedule Session");
        modifyScheduleSession.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyScheduleSessionActionPerformed(evt);
            }
        });

        annualReport.setText("Annual Report");
        annualReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annualReportActionPerformed(evt);
            }
        });

        peakHourAnalysis.setText("Peak Hour Analysis");
        peakHourAnalysis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                peakHourAnalysisActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(deleteFacilitySchedule, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(deleteScheduleSession, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(modifyScheduleSession, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
            .addComponent(annualReport, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
            .addComponent(peakHourAnalysis, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addComponent(deleteFacilitySchedule, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(deleteScheduleSession, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(modifyScheduleSession, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(annualReport, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(peakHourAnalysis, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void dateCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateCBActionPerformed
        String selectedDate = (String) dateCB.getSelectedItem();
        if (selectedDate != null) {
            updateTableForDate(selectedDate);
        }
    }//GEN-LAST:event_dateCBActionPerformed

    private void deleteFacilityScheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteFacilityScheduleActionPerformed
        int selectedRow = scheduleTable.getSelectedRow();
        if (selectedRow == -1) {
            javax.swing.JOptionPane.showMessageDialog(null, "Please select any row belonging to the facility you want to delete.");
            return;
        }

        String facility = getFacilityForSelectedRow(selectedRow);
        String currentDate = (String) dateCB.getSelectedItem();

        int targetIndex = findScheduleIndex(facility, currentDate);
        if (targetIndex != -1) {
            String facilityId = allSchedule.get(targetIndex).getFacility().getFacilityId();
            adt.ListInterface<entity.ScheduleSession> sessions = allSchedule.get(targetIndex).getSessions();
            
            //Scan all sessions for this day and collect conflicting Booking IDs
            adt.ListInterface<String> conflicts = new adt.SingleLinkedList<>();
            for (int i = 0; i < sessions.size(); i++) {
                String timeSlot = sessions.get(i).getSession();
                String bookingId = bookingManager.getActiveBookingIdForSchedule(facilityId, currentDate, timeSlot);
                if (bookingId != null) {
                    conflicts.add(bookingId);
                }
            }
            
          
            if (!conflicts.isEmpty()) {
                int choice = javax.swing.JOptionPane.showConfirmDialog(this, 
                    "Warning: This facility has " + conflicts.size() + " active student booking(s) on this date.\n\n" +
                    "Deleting this schedule will permanently CANCEL ALL associated bookings.\n" +
                    "Do you want to proceed?", 
                    "Delete Booked Facility Schedule?", 
                    javax.swing.JOptionPane.YES_NO_OPTION,
                    javax.swing.JOptionPane.WARNING_MESSAGE);
                    
                if (choice == javax.swing.JOptionPane.YES_OPTION) {
                    for (int i = 0; i < conflicts.size(); i++) {
                        bookingManager.cancelBooking(conflicts.get(i));
                    }

                    if (myControl.deleteFacilititySchedule(targetIndex)) {
                        javax.swing.JOptionPane.showMessageDialog(this, "Facility schedule deleted and all " + conflicts.size() + " related bookings cancelled.");
                    }
                    updateTableForDate(currentDate);
                }
                return; 
            }
            int confirm = javax.swing.JOptionPane.showConfirmDialog(
                null,
                "Are you sure you want to permanently delete the ENTIRE schedule for " + facility + " on " + currentDate + "?\nAll sessions will be removed.",
                "Confirm Delete Facility",
                javax.swing.JOptionPane.YES_NO_OPTION,
                javax.swing.JOptionPane.WARNING_MESSAGE
            );

            if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                if(myControl.deleteFacilititySchedule(targetIndex))
                    javax.swing.JOptionPane.showMessageDialog(null, "The facility schedule was deleted.");
                else
                    javax.swing.JOptionPane.showMessageDialog(null, "Failed to delete the facility schedule.");
                updateTableForDate(currentDate); 
            }
        }
    }//GEN-LAST:event_deleteFacilityScheduleActionPerformed

    private void deleteScheduleSessionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteScheduleSessionActionPerformed
        int selectedRow = scheduleTable.getSelectedRow();
        if (selectedRow == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please select a time session row to delete.");
            return;
        }

        String facility = getFacilityForSelectedRow(selectedRow);
        String timeSlot = (String) scheduleTable.getValueAt(selectedRow, 1);
        String currentDate = (String) dateCB.getSelectedItem();
        
        int targetIndex = findScheduleIndex(facility, currentDate);
        if (targetIndex != -1) {
            int timeIndex = findTimeIndex(targetIndex, timeSlot);
            if (timeIndex != -1) {
                String facilityId = allSchedule.get(targetIndex).getFacility().getFacilityId();
                String conflictingBookingId = bookingManager.getActiveBookingIdForSchedule(facilityId, currentDate, timeSlot);
                
                if (conflictingBookingId != null) {
                    int choice = javax.swing.JOptionPane.showConfirmDialog(this, 
                        "Warning: This session is currently booked by a student (Booking ID: " + conflictingBookingId + ").\n\n" +
                        "Deleting this session will permanently CANCEL the student's booking.\n" +
                        "Do you want to proceed?", 
                        "Delete Booked Session?", 
                        javax.swing.JOptionPane.YES_NO_OPTION,
                        javax.swing.JOptionPane.WARNING_MESSAGE);
                        
                    if (choice == javax.swing.JOptionPane.YES_OPTION) {
                        bookingManager.cancelBooking(conflictingBookingId);
                        myControl.deleteScheduleSession(targetIndex, timeIndex);
                        updateTableForDate(currentDate);
                        javax.swing.JOptionPane.showMessageDialog(this, "Session deleted and booking cancelled.");
                    }
                    return; 
                }
                int confirm = javax.swing.JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to permanently delete the " + timeSlot + " session for " + facility + "?",
                    "Confirm Delete Session",
                    javax.swing.JOptionPane.YES_NO_OPTION,
                    javax.swing.JOptionPane.WARNING_MESSAGE
                );

                if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                    myControl.deleteScheduleSession(targetIndex, timeIndex);
                    updateTableForDate(currentDate); 
                }
            }
        }
    }//GEN-LAST:event_deleteScheduleSessionActionPerformed

    private void modifyScheduleSessionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyScheduleSessionActionPerformed
        int selectedRow = scheduleTable.getSelectedRow();
        if (selectedRow == -1) {
            javax.swing.JOptionPane.showMessageDialog(null, "Please select a time session row to modify.");
            return;
        }

        String facility = getFacilityForSelectedRow(selectedRow);
        String timeSlot = (String) scheduleTable.getValueAt(selectedRow, 1);
        String currentStatus = (String) scheduleTable.getValueAt(selectedRow, 2);
        String currentDate = (String) dateCB.getSelectedItem();

        String[] options = {ScheduleSession.STATUS_AVAILABLE, ScheduleSession.STATUS_BOOKED, ScheduleSession.STATUS_MAINTENANCE, ScheduleSession.STATUS_NOT_AVAILABLE};
        String newStatus = (String) javax.swing.JOptionPane.showInputDialog(
            null,
            "Modify Status for " + facility + " at " + timeSlot + "\nCurrent Status: " + currentStatus,
            "Modify Session",
            javax.swing.JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            currentStatus
        );

        if (newStatus != null && !newStatus.equals(currentStatus)) {
            // Find the schedule index based on facility and date
            int targetIndex = findScheduleIndex(facility, currentDate);

            if (targetIndex != -1) {
                int timeIndex = findTimeIndex(targetIndex, timeSlot);
                if (timeIndex != -1) {
                    if (newStatus.equals(ScheduleSession.STATUS_AVAILABLE) || 
                        newStatus.equals(ScheduleSession.STATUS_MAINTENANCE) || 
                        newStatus.equals(ScheduleSession.STATUS_NOT_AVAILABLE)) {
                        
                        String facilityId = allSchedule.get(targetIndex).getFacility().getFacilityId();
                        
                        String conflictingBookingId = bookingManager.getActiveBookingIdForSchedule(facilityId, currentDate, timeSlot);
                        
                        if (conflictingBookingId != null) {
                            int choice = javax.swing.JOptionPane.showConfirmDialog(this, 
                                "Warning: This slot is currently booked by a student (Booking ID: " + conflictingBookingId + ").\n\n" +
                                "Forcing this slot to '" + newStatus + "' will permanently CANCEL the student's booking.\n" +
                                "Do you want to proceed?", 
                                "Cancel Student Booking?", 
                                javax.swing.JOptionPane.YES_NO_OPTION,
                                javax.swing.JOptionPane.WARNING_MESSAGE);
                            
                            if (choice == javax.swing.JOptionPane.YES_OPTION) {
                                bookingManager.cancelBooking(conflictingBookingId);
                                
                                myControl.modifyScheduleStatus(targetIndex, timeIndex, newStatus);
                                
                                javax.swing.JOptionPane.showMessageDialog(this, "Success! Booking cancelled and schedule changed to " + newStatus + ".");
                                updateTableForDate(currentDate); 
                                return;
                            } else {
                                return; 
                            }
                        }
                    }
                    myControl.modifyScheduleStatus(targetIndex, timeIndex, newStatus);
                    updateTableForDate(currentDate); // Refresh the table to show the new color!
                    javax.swing.JOptionPane.showMessageDialog(null, "Status updated successfully.");
                }
            }
        }
    }//GEN-LAST:event_modifyScheduleSessionActionPerformed

    private void sortingComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortingComboBoxActionPerformed
        if (myControl == null) return; 
        
        int selectedIndex = sortingComboBox.getSelectedIndex();
        if (selectedIndex == -1) return;
        
        switch (selectedIndex) {
            case 0:
                myControl.sortSchedules(); 
                myControl.sortSessionsByTime();
                break;
            case 1:
                myControl.sortByFacilityAndStatus();
                break;
        }

        String currentDate = (String) dateCB.getSelectedItem();
        if (currentDate != null) {
            updateTableForDate(currentDate);
        }
    }//GEN-LAST:event_sortingComboBoxActionPerformed

    private void searchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchTextFieldActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        String query = searchTextField.getText().trim();
        String currentDate = (String) dateCB.getSelectedItem();
        
        if (query.isEmpty()) {
            if (currentDate != null) updateTableForDate(currentDate);
            return;
        }
        
        if (currentDate != null) {
            ListInterface<Schedule> results = myControl.search(query, currentDate);
            
            if (results.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "No facilities found matching '" + query + "'.\nMake sure the spelling is correct!", 
                    "Not Found", 
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
            } else {
                displaySearchResults(results, currentDate);
            }
        }
    }//GEN-LAST:event_searchButtonActionPerformed

    private void annualReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annualReportActionPerformed
        javax.swing.JFrame reportFrame = new javax.swing.JFrame("Annual Facility Usage Report");
        reportFrame.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        reportFrame.add(new ScheduleReport(this.myControl)); 
        reportFrame.pack();
        reportFrame.setLocationRelativeTo(null); 
        reportFrame.setVisible(true);
    }//GEN-LAST:event_annualReportActionPerformed

    private void peakHourAnalysisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_peakHourAnalysisActionPerformed
        SchedulePeakHourAnalysis peakWindow = new SchedulePeakHourAnalysis(this.myControl);
        peakWindow.setTitle("Peak Hours Analysis");
        peakWindow.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        peakWindow.setLocationRelativeTo(null); 
        peakWindow.setVisible(true);
    }//GEN-LAST:event_peakHourAnalysisActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton annualReport;
    private javax.swing.JComboBox<String> dateCB;
    private javax.swing.JButton deleteFacilitySchedule;
    private javax.swing.JButton deleteScheduleSession;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton modifyScheduleSession;
    private javax.swing.JButton peakHourAnalysis;
    private javax.swing.JTable scheduleTable;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JComboBox<String> sortingComboBox;
    // End of variables declaration//GEN-END:variables
}

