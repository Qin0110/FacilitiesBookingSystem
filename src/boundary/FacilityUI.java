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
        String currentOption = null;
        
        Object selected = typeCB.getSelectedItem();
            if (selected != null) {
                String selStr = selected.toString().trim();
                if (!selStr.isEmpty()) {
                    currentOption = selStr;
                }
            }
        
        typeCB.removeAllItems();
        typeCB.addItem("ALL");
     
        for (int i = 0; i < facilities.size(); i++) {

            entity.Facility f = facilities.get(i);

            model.addRow(new Object[]{
                f.getFacilityId(),
                f.getName(),
                f.getType(),
                f.getLocation(),
                f.getMinCapacity(),
                f.getMaxCapacity()
            });
            
             String type = f.getType();
            boolean exists = false;

     
            for (int j = 0; j < typeCB.getItemCount(); j++) {
                
                if (typeCB.getItemAt(j).equals(type)) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                typeCB.addItem(type);
            }
        }
        
        if(currentOption == null)
            typeCB.setSelectedItem("ALL");
        else 
            typeCB.setSelectedItem(currentOption);
    }
    
    private void loadTableData(String id) {
        adt.ArrayList<entity.Facility> searchResults;
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        
        Object selected = typeCB.getSelectedItem();
        String type = (selected != null) ? selected.toString() : "ALL";


        if (type.equals("ALL")) {
                searchResults = facilityManager.searchFacility(id);
        } else {
            searchResults = facilityManager.searchFacility(id, type);
        }

        for (int i = 0; i < searchResults.size(); i++) {

            entity.Facility f = searchResults.get(i);

            model.addRow(new Object[]{
                f.getFacilityId(),
                f.getName(),
                f.getType(),
                f.getLocation(),
                f.getMinCapacity(),
                f.getMaxCapacity()
            });
        }
        
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        facilitySearchTF = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        typeCB = new javax.swing.JComboBox<>();

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

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Facility ID", "Name", "Type", "Location", "Min Capacity", "Max Capacity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        facilitySearchTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facilitySearchTFActionPerformed(evt);
            }
        });
        facilitySearchTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                facilitySearchTFKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                facilitySearchTFKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                facilitySearchTFKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel1.setText("Facility");

        typeCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeCBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 639, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(typeCB, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(facilitySearchTF)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(facilitySearchTF, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(typeCB, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        FacilityDialog dialog = new FacilityDialog(null, true, this.facilityManager);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
        loadTableData();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int selectedRow = jTable1.getSelectedRow();
        String message;

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

            message = facilityManager.removeFacility(facilityId);

            if (message.equals("Facility Successfully Removed")) {
                javax.swing.JOptionPane.showMessageDialog(this, message);

                loadTableData(); 
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, 
                    message, 
                    "Database Error", 
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int selectedRow = jTable1.getSelectedRow();
        String id, name, type, location;
        int minCapacity, maxCapacity;

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
        minCapacity = Integer.parseInt(jTable1.getValueAt(selectedRow, 4).toString());
        maxCapacity = Integer.parseInt(jTable1.getValueAt(selectedRow, 5).toString());
        
        entity.Facility selectedFacility = new entity.Facility(id, name, type, location, minCapacity, maxCapacity);
        
        FacilityDialog dialog = new FacilityDialog(null, true, this.facilityManager, selectedFacility);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
        
        loadTableData();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void facilitySearchTFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_facilitySearchTFKeyPressed
   
    }//GEN-LAST:event_facilitySearchTFKeyPressed

    private void facilitySearchTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_facilitySearchTFKeyTyped
        if(facilitySearchTF.getText().trim().length() >= 15 && evt.getKeyCode() != java.awt.event.KeyEvent.VK_BACK_SPACE){
            evt.consume();
            return;
        }
    }//GEN-LAST:event_facilitySearchTFKeyTyped

    private void facilitySearchTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_facilitySearchTFKeyReleased
        String id = facilitySearchTF.getText().trim();

        if (id.isEmpty()) {
            loadTableData();
            return;
        }

        loadTableData(id);
    }//GEN-LAST:event_facilitySearchTFKeyReleased

    private void facilitySearchTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facilitySearchTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_facilitySearchTFActionPerformed

    private void typeCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeCBActionPerformed
        String id = facilitySearchTF.getText().trim();

        loadTableData(id);
    }//GEN-LAST:event_typeCBActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField facilitySearchTF;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> typeCB;
    // End of variables declaration//GEN-END:variables
}
