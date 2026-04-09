/**
 * @author Ng WEi Hao
 */
package boundary;
import control.AdminManagement;

public class AdminInAdminModeUI extends javax.swing.JPanel {
    private AdminManagement adminManager;

    public AdminInAdminModeUI() {
        initComponents();
    }
    
    public AdminInAdminModeUI(AdminManagement adminManager) {
        initComponents();
        this.adminManager = adminManager;
        
        
        loadTableData();
    }
    
     private void loadTableData() {
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) adminTable.getModel();
        model.setRowCount(0);

        String idOrName = adminSearchTextField.getText().trim();
        if (idOrName.equals("Search by ID or Name")) { 
            idOrName = ""; 
        }

        Object selectedPosition = positionComboBox.getSelectedItem();
        String position = (selectedPosition != null) ? selectedPosition.toString() : "ALL";
 
        Object selectedSort = sortByComboBox.getSelectedItem();
        String sortBy = (selectedSort != null) ? selectedSort.toString() : "";
        
        Object selectedDirection = sortByAscDscComboBox.getSelectedItem();
        String sortDirection = (selectedDirection != null) ? selectedDirection.toString() : "Ascending";
        
        adt.ListInterface<entity.Admin> searchResults = adminManager.searchAdminForTable(idOrName, position, sortBy, sortDirection);

        for (int i = 0 ; i < searchResults.size() ; i++ ){
            entity.Admin s = searchResults.get(i);
            model.addRow(new Object[]{
                s.getAdminId(),
                s.getName(),
                s.getPosition(),
                s.getEmail(),
                s.getPhoneNumber()
            });
        }

        if (searchResults.size() == 0 && !idOrName.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "No admin information found containing ID or Name: " + idOrName, 
                "Search Results", 
                javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        AddButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        adminTable = new javax.swing.JTable();
        adminSearchTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        positionComboBox = new javax.swing.JComboBox<>();
        sortByText = new javax.swing.JLabel();
        sortByComboBox = new javax.swing.JComboBox<>();
        reportButton = new javax.swing.JButton();
        sortByAscDscComboBox = new javax.swing.JComboBox<>();

        AddButton.setText("Add Admin");
        AddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddButtonActionPerformed(evt);
            }
        });

        removeButton.setText("Remove Admin");
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });

        updateButton.setText("Update Admin");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        jScrollPane1.setBorder(null);

        adminTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Admin ID", "Name", "Position", "Email", "Phone Number"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(adminTable);

        adminSearchTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminSearchTextFieldActionPerformed(evt);
            }
        });
        adminSearchTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                adminSearchTextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                adminSearchTextFieldKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel1.setText("ADMIN");

        positionComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ALL", "Facility Manager", "Facility Assistant" }));
        positionComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                positionComboBoxActionPerformed(evt);
            }
        });

        sortByText.setText("Sort By:");

        sortByComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin ID", "Name", "Position", "Email", "Phone Number" }));
        sortByComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortByComboBoxActionPerformed(evt);
            }
        });

        reportButton.setText("Genarate Admin Report");
        reportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportButtonActionPerformed(evt);
            }
        });

        sortByAscDscComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ascending", "Descending" }));
        sortByAscDscComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortByAscDscComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(removeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(positionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(adminSearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sortByText)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(sortByComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(sortByAscDscComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(adminSearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(positionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sortByText, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sortByComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(sortByAscDscComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(AddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(removeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(reportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void AddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddButtonActionPerformed
        AdminDialog dialog = new AdminDialog(null, true, this.adminManager);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
        loadTableData();
    }//GEN-LAST:event_AddButtonActionPerformed

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
        int selectedRow = adminTable.getSelectedRow();

        if (selectedRow == -1) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Please select a Admin from the table first",
                "No Selection",
                javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        String adminId = adminTable.getValueAt(selectedRow, 0).toString();
        String name = adminTable.getValueAt(selectedRow, 1).toString();

        int confirm = javax.swing.JOptionPane.showConfirmDialog(this,
            "Are you sure you want to remove " + name + " (" + adminId + ")?",
            "Confirm Deletion",
            javax.swing.JOptionPane.YES_NO_OPTION,
            javax.swing.JOptionPane.WARNING_MESSAGE);

        if (confirm == javax.swing.JOptionPane.YES_OPTION) {

            boolean success = adminManager.removeAdmin(adminId);

            if (success) {
                javax.swing.JOptionPane.showMessageDialog(this, "Admin removed successfully.");

                loadTableData();
            } else {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "Failed to remove admin.",
                    "Database Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_removeButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        int selectedRow = adminTable.getSelectedRow();
        String id, name, position, email, phoneNumber;

        if (selectedRow == -1) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Please select a admin from the table first",
                "No Selection",
                javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        id = adminTable.getValueAt(selectedRow, 0).toString();
        name = adminTable.getValueAt(selectedRow, 1).toString();
        position = adminTable.getValueAt(selectedRow, 2).toString();
        email = adminTable.getValueAt(selectedRow, 3).toString();
        phoneNumber = adminTable.getValueAt(selectedRow, 4).toString();

        entity.Admin selectedAdmin = new entity.Admin(id, name, position, email, phoneNumber);

        AdminDialog dialog = new AdminDialog(null, true, this.adminManager, selectedAdmin);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);

        loadTableData();

    }//GEN-LAST:event_updateButtonActionPerformed

    private void adminSearchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminSearchTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_adminSearchTextFieldActionPerformed

    private void adminSearchTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_adminSearchTextFieldKeyReleased
        loadTableData();
    }//GEN-LAST:event_adminSearchTextFieldKeyReleased

    private void positionComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_positionComboBoxActionPerformed
        loadTableData();
    }//GEN-LAST:event_positionComboBoxActionPerformed

    private void adminSearchTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_adminSearchTextFieldKeyTyped
        if (adminSearchTextField.getText().trim().length() >= 15
            && evt.getKeyCode() != java.awt.event.KeyEvent.VK_BACK_SPACE) {
            evt.consume();
        }
    }//GEN-LAST:event_adminSearchTextFieldKeyTyped

    private void sortByComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortByComboBoxActionPerformed
        loadTableData();
    }//GEN-LAST:event_sortByComboBoxActionPerformed
    
    private void reportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportButtonActionPerformed
        AdminReportSelectionDialog selectionDialog = new AdminReportSelectionDialog(null, true);
        selectionDialog.setLocationRelativeTo(null); 
        selectionDialog.setVisible(true);
    }//GEN-LAST:event_reportButtonActionPerformed

    private void sortByAscDscComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortByAscDscComboBoxActionPerformed
        loadTableData();
    }//GEN-LAST:event_sortByAscDscComboBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddButton;
    private javax.swing.JTextField adminSearchTextField;
    private javax.swing.JTable adminTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> positionComboBox;
    private javax.swing.JButton removeButton;
    private javax.swing.JButton reportButton;
    private javax.swing.JComboBox<String> sortByAscDscComboBox;
    private javax.swing.JComboBox<String> sortByComboBox;
    private javax.swing.JLabel sortByText;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}
