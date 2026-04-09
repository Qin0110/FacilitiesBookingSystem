
//@author: Lee Guan Wei
package boundary;

public class FacilityReportDateSelection extends javax.swing.JDialog {
    private String selectedDay = "None";
    private String selectedMonth = "None";
    private String selectedYear = "None";
    private boolean confirmed = false;
    private final String[] monthNames = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};

    public FacilityReportDateSelection(java.awt.Frame parent, boolean modal, adt.ListInterface<String> availableYears) {
        super(parent, modal);
        initComponents(); 
        this.setResizable(false);

        yearCB.removeAllItems();
        yearCB.addItem("None");
        for (int i = 0; i < availableYears.size(); i++) {
            yearCB.addItem(availableYears.get(i));
        }

        monthCB.removeAllItems();
        monthCB.addItem("None");
        for (String m : monthNames) {
            monthCB.addItem(m);
        }
        updateDays();

        this.setTitle("Select Report Timeframe");
        this.setLocationRelativeTo(parent);
    }
    

    public boolean isConfirmed() { 
        return confirmed; 
    }
    
    public String getSelectedDay() { 
        return selectedDay; 
    }
    
    public String getSelectedMonth() {
        return selectedMonth; 
    }
    
    public String getSelectedYear() {
        return selectedYear; 
    }
    
    private void updateDays() {
        String selectedM = (String) monthCB.getSelectedItem();
        String selectedY = (String) yearCB.getSelectedItem();
        
        String currentDay = (String) dayCB.getSelectedItem();
        
        dayCB.removeAllItems();
        dayCB.addItem("None");
        
        int maxDays = 31; 
        
        if (selectedM != null && !selectedM.equals("None")) {
            if (selectedM.equals("FEB")) {
                maxDays = 28;
                if (selectedY != null && !selectedY.equals("None")) {
                 
                    int y = Integer.parseInt(selectedY);
                    if ((y % 4 == 0 && y % 100 != 0) || (y % 400 == 0)) {
                        maxDays = 29;
                    }
                }
            } else if (selectedM.equals("APR") || selectedM.equals("JUN") || 
                       selectedM.equals("SEP") || selectedM.equals("NOV")) {
                maxDays = 30;
            }
        }
        
        for (int i = 1; i <= maxDays; i++) {
            dayCB.addItem(String.format("%02d", i));
        }
        
        if (currentDay != null && !currentDay.equals("None")) {
        
            int d = Integer.parseInt(currentDay);
            if (d <= maxDays) {
                dayCB.setSelectedItem(currentDay);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        monthLabel = new javax.swing.JLabel();
        dayLabel = new javax.swing.JLabel();
        yearLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        monthCB = new javax.swing.JComboBox<>();
        yearCB = new javax.swing.JComboBox<>();
        dayCB = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        cancelBT2 = new javax.swing.JButton();
        confirmBT2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        monthLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        monthLabel.setText("Month:");

        dayLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        dayLabel.setText("Day:");

        yearLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        yearLabel.setText("Year:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dayLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(yearLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(monthLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(dayLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(monthLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(yearLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        monthCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthCBActionPerformed(evt);
            }
        });

        yearCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearCBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(yearCB, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dayCB, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(monthCB, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(dayCB, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(monthCB, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(yearCB, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        cancelBT2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cancelBT2.setText("Cancel");
        cancelBT2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBT2ActionPerformed(evt);
            }
        });

        confirmBT2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        confirmBT2.setText("Confirm");
        confirmBT2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmBT2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(cancelBT2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(confirmBT2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cancelBT2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
            .addComponent(confirmBT2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelBT2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBT2ActionPerformed
        this.confirmed = false; 
        this.dispose();
    }//GEN-LAST:event_cancelBT2ActionPerformed

    private void confirmBT2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmBT2ActionPerformed
        this.selectedDay = dayCB.getSelectedItem().toString();
        this.selectedYear = yearCB.getSelectedItem().toString();
        
        String displayMonth = monthCB.getSelectedItem().toString();
        if (displayMonth.equals("None")) {
            this.selectedMonth = "None";
        } else {
            for (int i = 0; i < monthNames.length; i++) {
                if (monthNames[i].equals(displayMonth)) {
                    this.selectedMonth = String.format("%02d", (i + 1)); // Turns "MAR" back to "03"
                    break;
                }
            }
        }
       
        this.confirmed = true; 
        this.dispose();   
    }//GEN-LAST:event_confirmBT2ActionPerformed

    private void monthCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthCBActionPerformed
        updateDays();
    }//GEN-LAST:event_monthCBActionPerformed

    private void yearCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearCBActionPerformed
        updateDays();
    }//GEN-LAST:event_yearCBActionPerformed


   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelBT2;
    private javax.swing.JButton confirmBT2;
    private javax.swing.JComboBox<String> dayCB;
    private javax.swing.JLabel dayLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JComboBox<String> monthCB;
    private javax.swing.JLabel monthLabel;
    private javax.swing.JComboBox<String> yearCB;
    private javax.swing.JLabel yearLabel;
    // End of variables declaration//GEN-END:variables
}
