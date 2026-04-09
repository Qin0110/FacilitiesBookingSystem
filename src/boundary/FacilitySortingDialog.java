/**
 * @author Lee Guan Wei
 */

package boundary;

import control.FacilityManagement;

public class FacilitySortingDialog extends javax.swing.JDialog {
    
    private FacilityManagement facilityManager;


    public FacilitySortingDialog(java.awt.Frame parent, boolean modal, FacilityManagement facilityManager) {
        super(parent, modal);
        initComponents();
        setTitle("Sorting By");
        this.setResizable(false);


        
        this.facilityManager = facilityManager;
        
        String savedSort1 = facilityManager.getCurrentSort1();
        String savedSort2 = facilityManager.getCurrentSort2();
        String savedOrder = facilityManager.getCurrentOrder();
        
        primarySortCB.setSelectedItem(savedSort1);
        
        secondarySortCB.setSelectedItem(savedSort2);
        
        jComboBox3.setSelectedItem(savedOrder);
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        primarySortLabel = new javax.swing.JLabel();
        secondarySortLabel = new javax.swing.JLabel();
        secondarySortLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        primarySortCB = new javax.swing.JComboBox<>();
        secondarySortCB = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        applyBT = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        primarySortLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        primarySortLabel.setText("Sort I:");

        secondarySortLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        secondarySortLabel.setText("Sort II:");

        secondarySortLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        secondarySortLabel1.setText("Order:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(primarySortLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(secondarySortLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(secondarySortLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(primarySortLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(secondarySortLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(secondarySortLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        primarySortCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Name", "Type", "Location", "Capacity" }));
        primarySortCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                primarySortCBActionPerformed(evt);
            }
        });

        secondarySortCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None", "ID", "Name", "Type", "Location", "Capacity" }));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ASC", "DESC" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(primarySortCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(secondarySortCB, javax.swing.GroupLayout.Alignment.TRAILING, 0, 201, Short.MAX_VALUE)
                    .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(primarySortCB, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(secondarySortCB, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        applyBT.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        applyBT.setText("Apply");
        applyBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyBTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(applyBT, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(applyBT, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void applyBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyBTActionPerformed
        String primarySort = primarySortCB.getSelectedItem().toString();
        String secondarySort = secondarySortCB.getSelectedItem().toString();
        String order = jComboBox3.getSelectedItem().toString(); 

        if (secondarySort.equals("None") || secondarySort.equals(primarySort)) {
            secondarySort = null; 
        }

        facilityManager.sortFacilities(primarySort, secondarySort, order);

        this.dispose();
    }//GEN-LAST:event_applyBTActionPerformed

    private void primarySortCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_primarySortCBActionPerformed
       if (primarySortCB.getSelectedItem() == null || secondarySortCB == null) {
            return;
        }

        Object selectedObj = secondarySortCB.getSelectedItem();
        String currentSort2 = (selectedObj != null) ? selectedObj.toString() : "None";

        String selectedSort1 = primarySortCB.getSelectedItem().toString();

        secondarySortCB.removeAllItems();
        secondarySortCB.addItem("None"); 

        String[] allAttributes = {"ID", "Name", "Type", "Location", "Capacity"};
        for (String attr : allAttributes) {
            if (!attr.equals(selectedSort1)) {
                secondarySortCB.addItem(attr);
            }
        }

        if (!currentSort2.equals(selectedSort1)) {
            secondarySortCB.setSelectedItem(currentSort2);
        } else {
            secondarySortCB.setSelectedItem("None");
        }
    }//GEN-LAST:event_primarySortCBActionPerformed

 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton applyBT;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JComboBox<String> primarySortCB;
    private javax.swing.JLabel primarySortLabel;
    private javax.swing.JComboBox<String> secondarySortCB;
    private javax.swing.JLabel secondarySortLabel;
    private javax.swing.JLabel secondarySortLabel1;
    // End of variables declaration//GEN-END:variables
}
