package boundary;
import javax.swing.table.DefaultTableModel;
import control.ScheduleControl;
import entity.*;
import adt.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Component;
import java.awt.Color;
import javax.swing.JTable;

public class ScheduleUI extends javax.swing.JFrame {
    private ScheduleControl myControl;
    private ListInterface<Schedule> allSchedule;
    
    public ScheduleUI() {
        initComponents();
        
        // Setup table styling
        jTable1.setShowGrid(true);
        jTable1.setGridColor(Color.LIGHT_GRAY);
        GroupBorderRenderer renderer = new GroupBorderRenderer();
        for (int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
        
        // Initialize control and fetch data
        myControl = new control.ScheduleControl();
        allSchedule = myControl.getAllSchedule();
        
        // 2. Populate the JComboBox with unique dates
        jComboBox1.removeAllItems(); // Remove NetBeans default "Item 1", "Item 2"
        
        for (int i = 0; i < allSchedule.size(); i++) {
            String dateString = allSchedule.get(i).getDate();
            
            // Check if the date is already in the dropdown to avoid duplicates
            boolean exists = false;
            for (int j = 0; j < jComboBox1.getItemCount(); j++) {
                if (jComboBox1.getItemAt(j).equals(dateString)) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                jComboBox1.addItem(dateString);
            }
        }
        
        // 3. Add an ActionListener to trigger when the user picks a new date
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String selectedDate = (String) jComboBox1.getSelectedItem();
                if (selectedDate != null) {
                    updateTableForDate(selectedDate);
                }
            }
        });
        
        // 4. Load the table with the first date initially
        if (jComboBox1.getItemCount() > 0) {
            updateTableForDate((String) jComboBox1.getItemAt(0));
        }
    }

    // 5. This new method handles filtering the table data based on the chosen date
    private void updateTableForDate(String targetDate) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
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


class GroupBorderRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

        // Get the default cell setup
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // 1. Check if this row is the start of a new facility
        // We know it is a new facility if Column 0 is NOT empty!
        Object facilityValue = table.getModel().getValueAt(row, 0);
        boolean isNewFacility = (facilityValue != null && !facilityValue.toString().isEmpty());

        // 2. Draw the thick black line at the top (skipping the very first row of the table)
        if (isNewFacility && row > 0) {
            // createMatteBorder(top, left, bottom, right, color)
            javax.swing.border.Border thickLine = javax.swing.BorderFactory.createMatteBorder(2, 0, 0, 0, java.awt.Color.BLACK);
            // Add some padding so the text doesn't touch the line
            javax.swing.border.Border padding = javax.swing.BorderFactory.createEmptyBorder(2, 4, 2, 4); 
            // Combine them
            ((javax.swing.JComponent) cell).setBorder(javax.swing.BorderFactory.createCompoundBorder(thickLine, padding));
        } else {
            // For all other normal rows, just apply standard padding
            ((javax.swing.JComponent) cell).setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 4, 2, 4));
        }

        // 3. Let's color the Availability column (Column 2) based on the status!
        if (column == 2 && value != null && !isSelected) {
            String status = value.toString().trim();
            switch (status) {
                case "Available": 
                    cell.setBackground(new java.awt.Color(144, 238, 144)); // Green
                    break;
                case "Booked": 
                    cell.setBackground(new java.awt.Color(255, 182, 193)); // Red
                    break;
                case "Maintenance": 
                    cell.setBackground(new java.awt.Color(255, 250, 205)); // Yellow
                    break;
                default: 
                    cell.setBackground(java.awt.Color.WHITE); 
                    break;
            }
        } else if (!isSelected) {
            cell.setBackground(java.awt.Color.WHITE); // Keep other columns white
        }
        
        // Force the background colors to show up in NetBeans
        ((javax.swing.JLabel) cell).setOpaque(true);

        return cell;
    }
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        deleteFacilitySchedule = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        deleteScheduleSession = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        modifyScheduleSession = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("All Schedule");

        deleteFacilitySchedule.setText("Delete Facility Schedule");
        deleteFacilitySchedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteFacilityScheduleActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        deleteScheduleSession.setText("Delete Schedule Session");
        deleteScheduleSession.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteScheduleSessionActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Facilitiy", "Time", "Status"
            }
        ));
        jTable1.setToolTipText("");
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMinWidth(150);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(150);
        }

        modifyScheduleSession.setText("Modify Schedule Session");
        modifyScheduleSession.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyScheduleSessionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(deleteFacilitySchedule, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(deleteScheduleSession, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(modifyScheduleSession, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(171, 171, 171)
                        .addComponent(deleteFacilitySchedule, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(deleteScheduleSession, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(modifyScheduleSession, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void deleteFacilityScheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteFacilityScheduleActionPerformed
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please select any row belonging to the facility you want to delete.");
            return;
        }

        String facility = getFacilityForSelectedRow(selectedRow);
        String currentDate = (String) jComboBox1.getSelectedItem();

        // Confirm deletion
        int confirm = javax.swing.JOptionPane.showConfirmDialog(
                this, 
                "Are you sure you want to permanently delete the ENTIRE schedule for " + facility + " on " + currentDate + "?\nAll sessions will be removed.",
                "Confirm Delete Facility", 
                javax.swing.JOptionPane.YES_NO_OPTION,
                javax.swing.JOptionPane.WARNING_MESSAGE
        );

        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            int targetIndex = findScheduleIndex(facility, currentDate);
            if (targetIndex != -1) {
                myControl.deleteFacilititySchedule(targetIndex);
                updateTableForDate(currentDate); // Refresh table
            }
        }
    }//GEN-LAST:event_deleteFacilityScheduleActionPerformed

    private void deleteScheduleSessionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteScheduleSessionActionPerformed
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please select a time session row to delete.");
            return;
        }

        String facility = getFacilityForSelectedRow(selectedRow);
        String timeSlot = (String) jTable1.getValueAt(selectedRow, 1);
        String currentDate = (String) jComboBox1.getSelectedItem();

        // Confirm deletion
        int confirm = javax.swing.JOptionPane.showConfirmDialog(
                this, 
                "Are you sure you want to permanently delete the " + timeSlot + " session for " + facility + "?",
                "Confirm Delete Session", 
                javax.swing.JOptionPane.YES_NO_OPTION,
                javax.swing.JOptionPane.WARNING_MESSAGE
        );

        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            int targetIndex = findScheduleIndex(facility, currentDate);
            if (targetIndex != -1) {
                int timeIndex = findTimeIndex(targetIndex, timeSlot);
                if (timeIndex != -1) {
                    myControl.deleteScheduleSession(targetIndex, timeIndex);
                    updateTableForDate(currentDate); // Refresh table
                }
            }
        }
    }//GEN-LAST:event_deleteScheduleSessionActionPerformed

    private void modifyScheduleSessionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyScheduleSessionActionPerformed
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please select a time session row to modify.");
            return;
        }
    
        // 1. Get the data from the selected row
        String facility = getFacilityForSelectedRow(selectedRow);
        String timeSlot = (String) jTable1.getValueAt(selectedRow, 1);
        String currentStatus = (String) jTable1.getValueAt(selectedRow, 2);
        String currentDate = (String) jComboBox1.getSelectedItem();

        // 2. Pop-up to ask for the new status
        String[] options = {ScheduleSession.STATUS_AVAILABLE, ScheduleSession.STATUS_BOOKED, ScheduleSession.STATUS_MAINTENANCE, ScheduleSession.STATUS_NOT_AVAILABLE};
        String newStatus = (String) javax.swing.JOptionPane.showInputDialog(
                this, 
                "Modify Status for " + facility + " at " + timeSlot + "\nCurrent Status: " + currentStatus,
                "Modify Session",
                javax.swing.JOptionPane.QUESTION_MESSAGE,
                null, 
                options, 
                currentStatus
        );

        // 3. If they picked a new status, update the data
        if (newStatus != null && !newStatus.equals(currentStatus)) {
            // Find the schedule index based on facility and date
            int targetIndex = findScheduleIndex(facility, currentDate);
            
            if (targetIndex != -1) {
                // Since your list is 0-indexed, we need to find the specific time slot index
                int timeIndex = findTimeIndex(targetIndex, timeSlot);
                if (timeIndex != -1) {
                    myControl.modifyScheduleStatus(targetIndex, timeIndex, newStatus);
                    updateTableForDate(currentDate); // Refresh the table to show the new color!
                    javax.swing.JOptionPane.showMessageDialog(this, "Status updated successfully.");
                }
            }
        }
    }//GEN-LAST:event_modifyScheduleSessionActionPerformed

    
    private String getFacilityForSelectedRow(int row) {
        for (int i = row; i >= 0; i--) {
            String val = (String) jTable1.getValueAt(i, 0);
            if (val != null && !val.trim().isEmpty()) {
                return val;
            }
        }
        return "";
    }

    // Finds the main schedule object index based on name AND date
    private int findScheduleIndex(String facilityName, String date) {
        for (int i = 0; i < allSchedule.size(); i++) {
            Schedule s = allSchedule.get(i);
            if (s.getFacility().getName().equals(facilityName) && s.getDate().equals(date)) {
                return i;
            }
        }
        return -1; // Not found
    }

    // Finds the specific time slot index within a schedule
    private int findTimeIndex(int scheduleIndex, String timeString) {
        ListInterface<ScheduleSession> sessions = allSchedule.get(scheduleIndex).getSessions();
        for (int i = 0; i < sessions.size(); i++) {
            if (sessions.get(i).getSession().equals(timeString)) {
                return i;
            }
        }
        return -1; // Not found
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ScheduleUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ScheduleUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ScheduleUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ScheduleUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ScheduleUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton deleteFacilitySchedule;
    private javax.swing.JButton deleteScheduleSession;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton modifyScheduleSession;
    // End of variables declaration//GEN-END:variables
}