/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package boundary;

import adt.ArrayList;
import control.FacilityManagement;

public class FacilityUI extends javax.swing.JPanel {
    
    private FacilityManagement facilityManager;
    
    public FacilityUI() {
        boolean searchFacilityID = false;
        initComponents();
    }
    
    public FacilityUI(FacilityManagement facilityManager){
        initComponents();
        this.facilityManager = facilityManager;
        loadTableData();

    }
    
    private void loadTableData() {
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        adt.ArrayList<entity.Facility> facilities = facilityManager.getAllFacilities();

        for (int i = 0; i < facilities.size(); i++) {

            entity.Facility f = facilities.get(i);

            model.addRow(new Object[]{
                f.getFacilityId(),
                f.getName(),
                f.getType(),
                f.getLocation(),
                f.getCapacity()
            });
        }
    }
    
    private void loadTableData(String id) {
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        adt.ArrayList<entity.Facility> searchResults = facilityManager.searchFacility(id);     

        for (int i = 0; i < searchResults.size(); i++) {

            entity.Facility f = searchResults.get(i);

            model.addRow(new Object[]{
                f.getFacilityId(),
                f.getName(),
                f.getType(),
                f.getLocation(),
                f.getCapacity()
            });
        }
        
        if (searchResults.size() == 0) {
        javax.swing.JOptionPane.showMessageDialog(this, 
            "No facilities found containing ID: " + id, 
            "Search Results", 
            javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        facilitySearchBT = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        facilitySearchTF = new javax.swing.JTextField();

        jButton1.setText("Add Facility");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Remove Facility");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Update Facility");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        facilitySearchBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/searchBT.png"))); // NOI18N
        facilitySearchBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facilitySearchBTActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Facility ID", "Name", "Type", "Location", "Capacity"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(facilitySearchTF, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(facilitySearchBT))
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(facilitySearchBT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(facilitySearchTF))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        FacilityDialog dialog = new FacilityDialog(null, true, this.facilityManager);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int selectedRow = jTable1.getSelectedRow();

        if (selectedRow == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Please select a facility from the table first", 
                "No Selection", 
                javax.swing.JOptionPane.WARNING_MESSAGE);
            return; 
        }

        String facilityId = jTable1.getValueAt(selectedRow, 0).toString();
        String facilityName = jTable1.getValueAt(selectedRow, 1).toString();

        int confirm = javax.swing.JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to remove " + facilityName + " (" + facilityId + ")?", 
            "Confirm Deletion", 
            javax.swing.JOptionPane.YES_NO_OPTION, 
            javax.swing.JOptionPane.WARNING_MESSAGE);

        if (confirm == javax.swing.JOptionPane.YES_OPTION) {

            boolean success = facilityManager.removeFacility(facilityId);

            if (success) {
                javax.swing.JOptionPane.showMessageDialog(this, "Facility removed successfully.");

                loadTableData(); 
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "Failed to remove facility. Ensure it is not currently tied to any active bookings.", 
                    "Database Error", 
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int selectedRow = jTable1.getSelectedRow();
        String id, name, type, location;
        int capacity;

        if (selectedRow == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Please select a facility from the table first", 
                "No Selection", 
                javax.swing.JOptionPane.WARNING_MESSAGE);
            return; 
        }
        id = jTable1.getValueAt(selectedRow, 0).toString();
        name = jTable1.getValueAt(selectedRow, 1).toString();
        type = jTable1.getValueAt(selectedRow, 2).toString();
        location = jTable1.getValueAt(selectedRow, 3).toString();
        capacity = Integer.parseInt(jTable1.getValueAt(selectedRow, 4).toString());
        
        entity.Facility selectedFacility = new entity.Facility(id, name, type, location, capacity);
        
        FacilityDialog dialog = new FacilityDialog(null, true, this.facilityManager, selectedFacility);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
        
        loadTableData();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void facilitySearchBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facilitySearchBTActionPerformed
        String id = facilitySearchTF.getText().trim();
        if (id.isEmpty()) {
            loadTableData();
            return;
        }
        
        loadTableData(id);
    }//GEN-LAST:event_facilitySearchBTActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton facilitySearchBT;
    private javax.swing.JTextField facilitySearchTF;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
