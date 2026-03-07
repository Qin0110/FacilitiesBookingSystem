/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package boundary;

import control.FacilityManagement;

public class FacilityDialog extends javax.swing.JDialog {
    
    private FacilityManagement facilityManager;
    private boolean isUpdateMode = false;
  
    public FacilityDialog(java.awt.Frame parent, boolean modal, control.FacilityManagement facilityManager) {
        super(parent, modal);
        initComponents();
        this.facilityManager = facilityManager;
    }
    
    public FacilityDialog(java.awt.Frame parent, boolean modal, control.FacilityManagement manager, entity.Facility facilityToEdit) {
        super(parent, modal);
        initComponents();
        this.facilityManager = manager;
        this.isUpdateMode = true;
        
        idTF.setText(facilityToEdit.getFacilityId());
        nameTF.setText(facilityToEdit.getName());
        typeCB.setSelectedItem(facilityToEdit.getType());
        locationCB.setSelectedItem(facilityToEdit.getLocation());
        minCapacityCB.setSelectedItem(String.valueOf(facilityToEdit.getMinCapacity()));
        maxCapacityCB.setSelectedItem(String.valueOf(facilityToEdit.getMaxCapacity()));

         
        idTF.setEnabled(false); 
        
        saveBT.setText("Update Facility");
        this.setTitle("Update Facility Details");
    }

    
    private void updateMaxCapacity(){
        if(minCapacityCB.getSelectedItem() == null)
            return;
        
        int minValue = Integer.parseInt(minCapacityCB.getSelectedItem().toString());
        
        if 
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        idText = new javax.swing.JLabel();
        nameText = new javax.swing.JLabel();
        typeText = new javax.swing.JLabel();
        locationText = new javax.swing.JLabel();
        idTF = new javax.swing.JTextField();
        nameTF = new javax.swing.JTextField();
        typeCB = new javax.swing.JComboBox<>();
        minCapacityCB = new javax.swing.JComboBox<>();
        cancelBT = new javax.swing.JButton();
        saveBT = new javax.swing.JButton();
        minCapacityText = new javax.swing.JLabel();
        locationCB = new javax.swing.JComboBox<>();
        maxCapacityCB = new javax.swing.JComboBox<>();
        maxCapacityText1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        idText.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        idText.setText("ID");

        nameText.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        nameText.setText("Name");

        typeText.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        typeText.setText("Type");

        locationText.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        locationText.setText("Location");

        typeCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sports", "Recreation", "Event Space", " " }));

        minCapacityCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "25", "50", " " }));

        cancelBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/cancelBT.png"))); // NOI18N
        cancelBT.setText("Cancel");
        cancelBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBTActionPerformed(evt);
            }
        });

        saveBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/saveBT.png"))); // NOI18N
        saveBT.setText("Save");
        saveBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBTActionPerformed(evt);
            }
        });

        minCapacityText.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        minCapacityText.setText("Min Capacity");

        locationCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sport Complex", "TAR UMT Arena", "Club House" }));
        locationCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                locationCBActionPerformed(evt);
            }
        });

        maxCapacityCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "25", "50", " " }));
        maxCapacityCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maxCapacityCBActionPerformed(evt);
            }
        });

        maxCapacityText1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        maxCapacityText1.setText("Max Capacity");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cancelBT, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(160, 160, 160)
                        .addComponent(saveBT, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(39, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(idText, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(locationText, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(typeText, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nameText, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(minCapacityText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(idTF, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(locationCB, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(typeCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(nameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(minCapacityCB, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(maxCapacityText1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(maxCapacityCB, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(idText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(idTF, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameText, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(typeText, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(typeCB, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(locationText, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(locationCB, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(minCapacityText, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(minCapacityCB, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maxCapacityText1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maxCapacityCB, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelBT, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveBT, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBTActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelBTActionPerformed

    private void saveBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBTActionPerformed
        String id = idTF.getText().trim();
        String name = nameTF.getText().trim();
        String type = typeCB.getSelectedItem().toString();
        String location = locationCB.getSelectedItem().toString();
        String minCapacityStr = minCapacityCB.getSelectedItem().toString();
        String maxCapacityStr = maxCapacityCB.getSelectedItem().toString();

        String message;
                
        if (id.isEmpty() || name.isEmpty() || location.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Please fill in all text fields!", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return; 
        }
        
        try {
            int minCapacity = Integer.parseInt(minCapacityStr);      
            int maxCapacity = Integer.parseInt(maxCapacityStr);            

            
            if (isUpdateMode) {
                message = facilityManager.updateFacility(id, name, type, location, minCapacity, maxCapacity);
            } else {
                message = facilityManager.addNewFacility(id, name, type, location, minCapacity, maxCapacity);
            }
            
            if (message.equals("Facility Successfully Added") || message.equals("Facility Successfully Updated")) {
                javax.swing.JOptionPane.showMessageDialog(this, message);
                this.dispose(); 
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, message, "Database Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "System Error: Capacity is not a valid number.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_saveBTActionPerformed

    private void locationCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_locationCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_locationCBActionPerformed

    private void maxCapacityCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maxCapacityCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_maxCapacityCBActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelBT;
    private javax.swing.JTextField idTF;
    private javax.swing.JLabel idText;
    private javax.swing.JComboBox<String> locationCB;
    private javax.swing.JLabel locationText;
    private javax.swing.JComboBox<String> maxCapacityCB;
    private javax.swing.JLabel maxCapacityText1;
    private javax.swing.JComboBox<String> minCapacityCB;
    private javax.swing.JLabel minCapacityText;
    private javax.swing.JTextField nameTF;
    private javax.swing.JLabel nameText;
    private javax.swing.JButton saveBT;
    private javax.swing.JComboBox<String> typeCB;
    private javax.swing.JLabel typeText;
    // End of variables declaration//GEN-END:variables
}
