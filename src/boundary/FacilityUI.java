/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package boundary;

import control.FacilityManagement;

public class FacilityUI extends javax.swing.JPanel {
    
    private FacilityManagement facilityManager;
    
   
 
    public FacilityUI(FacilityManagement facilityManager) {
        initComponents();
        
        this.facilityManager = facilityManager;
        loadTableData();
    }
    
    private void loadTableData() {
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) facilityTable.getModel();
        model.setRowCount(0);

        adt.ListInterface<entity.Facility> facilities = facilityManager.getAllFacilities();

        String currentTypeOption = null;
        Object selected = typeCB.getSelectedItem();
        if (selected != null) {
            String selStr = selected.toString().trim();
            if (!selStr.isEmpty()) 
                currentTypeOption = selStr;
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
            if (!exists) typeCB.addItem(type);
        }

        if (currentTypeOption == null) 
            typeCB.setSelectedItem("ALL");
        else                       
            typeCB.setSelectedItem(currentTypeOption);
            
    }
 
    private void loadTableData(String id) {
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) facilityTable.getModel();
        model.setRowCount(0);

        Object selected = typeCB.getSelectedItem();
        String type = (selected != null) ? selected.toString() : "ALL";
        
        adt.ListInterface<entity.Facility> searchResults = facilityManager.searchFacility(id, type);

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

    
    private void saveToFile(String defaultFileName, String fileContent, String dialogTitle) {
        javax.swing.JFileChooser fileChooser = new javax.swing.JFileChooser();
        fileChooser.setDialogTitle(dialogTitle);
        fileChooser.setSelectedFile(new java.io.File(defaultFileName));
        
        int userSelection = fileChooser.showSaveDialog(this);
        
        if (userSelection == javax.swing.JFileChooser.APPROVE_OPTION) {
            java.io.File fileToSave = fileChooser.getSelectedFile();
            
            try (java.io.FileWriter writer = new java.io.FileWriter(fileToSave)) {
                writer.write(fileContent);
                javax.swing.JOptionPane.showMessageDialog(this, "File successfully downloaded to:\n" + fileToSave.getAbsolutePath());
            } catch (java.io.IOException e) {
                javax.swing.JOptionPane.showMessageDialog(this, "Error saving file: " + e.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        facilityTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        typeCB = new javax.swing.JComboBox<>();
        facilitySearchTF = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        facilityTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(facilityTable);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jButton1.setText("Add Facility");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Update Facility");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Generate Report");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton2.setText("Remove Facility");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(151, Short.MAX_VALUE))
        );

        typeCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeCBActionPerformed(evt);
            }
        });

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

        jButton5.setText("Sort By");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(typeCB, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(facilitySearchTF, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(typeCB, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(facilitySearchTF)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel1.setText("Facility");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 128, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(223, 223, 223))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 516, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        FacilityDialog dialog = new FacilityDialog(null, true, this.facilityManager);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
        loadTableData(facilitySearchTF.getText().trim());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int selectedRow = facilityTable.getSelectedRow();
 
        if (selectedRow == -1) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Please select a facility from the table first",
                "No Selection", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
 
        String facilityId   = facilityTable.getValueAt(selectedRow, 0).toString();
        String facilityName = facilityTable.getValueAt(selectedRow, 1).toString();
 
        int confirm = javax.swing.JOptionPane.showConfirmDialog(this,
            "Are you sure you want to remove " + facilityName + " (" + facilityId + ")?", "Confirm Deletion", javax.swing.JOptionPane.YES_NO_OPTION, javax.swing.JOptionPane.WARNING_MESSAGE);
 
        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            String message = facilityManager.removeFacility(facilityId);
 
            if (message.equals("Facility Successfully Removed")) {
                javax.swing.JOptionPane.showMessageDialog(this, message);
                loadTableData(facilitySearchTF.getText().trim());

            } else {
                javax.swing.JOptionPane.showMessageDialog(this, message, "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
          int selectedRow = facilityTable.getSelectedRow();
 
        if (selectedRow == -1) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Please select a facility from the table first",
                "No Selection", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
 
        String id       = facilityTable.getValueAt(selectedRow, 0).toString();
        String name     = facilityTable.getValueAt(selectedRow, 1).toString();
        String type     = facilityTable.getValueAt(selectedRow, 2).toString();
        String location = facilityTable.getValueAt(selectedRow, 3).toString();
        int minCapacity = Integer.parseInt(facilityTable.getValueAt(selectedRow, 4).toString());
        int maxCapacity = Integer.parseInt(facilityTable.getValueAt(selectedRow, 5).toString());
 
        entity.Facility selectedFacility = new entity.Facility(id, name, type, location, minCapacity, maxCapacity);
 
        FacilityDialog dialog = new FacilityDialog(null, true, this.facilityManager, selectedFacility);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
        loadTableData(facilitySearchTF.getText().trim());

    }//GEN-LAST:event_jButton3ActionPerformed

    private void facilitySearchTFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_facilitySearchTFKeyPressed
   
    }//GEN-LAST:event_facilitySearchTFKeyPressed

    private void facilitySearchTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_facilitySearchTFKeyTyped
        if (facilitySearchTF.getText().trim().length() >= 15
                && evt.getKeyCode() != java.awt.event.KeyEvent.VK_BACK_SPACE) {
            evt.consume();
        }
    }//GEN-LAST:event_facilitySearchTFKeyTyped

    private void facilitySearchTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_facilitySearchTFKeyReleased
        String id = facilitySearchTF.getText().trim();

        loadTableData(id);
    }//GEN-LAST:event_facilitySearchTFKeyReleased

    private void facilitySearchTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facilitySearchTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_facilitySearchTFActionPerformed

    private void typeCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeCBActionPerformed
        String id = facilitySearchTF.getText().trim();

        loadTableData(id);
    }//GEN-LAST:event_typeCBActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String visualReport = facilityManager.generateDistributionReport();

        // 2. Build the visual pane
        javax.swing.JTextArea textArea = new javax.swing.JTextArea(visualReport);
        textArea.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 14));
        textArea.setEditable(false);
        textArea.setBackground(new java.awt.Color(240, 240, 240));

        javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(textArea);
        scrollPane.setPreferredSize(new java.awt.Dimension(450, 450));

        // 3. Create the custom buttons for the popup
        Object[] options = {"Close", "Save as .TXT (Visual)", "Download .CSV (Excel)"};

        // 4. Show the dialog!
        int choice = javax.swing.JOptionPane.showOptionDialog(
                this, 
                scrollPane, 
                "Facility Distribution Report", 
                javax.swing.JOptionPane.YES_NO_CANCEL_OPTION, 
                javax.swing.JOptionPane.PLAIN_MESSAGE, 
                null, 
                options, 
                options[0] // Default button is "Close"
        );

        // 5. Handle the user's click
        if (choice == 1) { 
            // They clicked "Save as .TXT"
            saveToFile("Facility_Distribution_Report.txt", visualReport, "Save Visual Report");
            
        } else if (choice == 2) { 
            // They clicked "Download .CSV"
            // We call the CSV backend method we made earlier!
            String csvData = facilityManager.generateDistributionCSV(); 
            saveToFile("Facility_Data.csv", csvData, "Download CSV Data");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        FacilitySortingDialog dialog = new FacilitySortingDialog(null, true, this.facilityManager);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
        loadTableData();
    }//GEN-LAST:event_jButton5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField facilitySearchTF;
    private javax.swing.JTable facilityTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> typeCB;
    // End of variables declaration//GEN-END:variables
}
