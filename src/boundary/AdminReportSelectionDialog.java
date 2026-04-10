/**
 *
 * @author Ng Wei Hao
 */

package boundary;

public class AdminReportSelectionDialog extends javax.swing.JDialog {
    
    public AdminReportSelectionDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAdvanced = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnDetailed = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        btnSummary1 = new javax.swing.JButton();
        btnSummary2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnAdvanced.setText("Cross-Position Trends");
        btnAdvanced.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdvancedActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Select Report Type");

        btnDetailed.setText("Detailed Admin Booking Summary Report");
        btnDetailed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailedActionPerformed(evt);
            }
        });

        cancelButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cancelButton.setForeground(new java.awt.Color(41, 98, 160));
        cancelButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/cancelBT.png"))); // NOI18N
        cancelButton.setText("Cancel");
        cancelButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(41, 98, 160), 1, true));
        cancelButton.setFocusPainted(false);
        cancelButton.setIconTextGap(10);
        cancelButton.setOpaque(true);
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        btnSummary1.setText("Admin Position Summary Report");
        btnSummary1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSummary1ActionPerformed(evt);
            }
        });

        btnSummary2.setText("Admin Booking Handle Summary Report");
        btnSummary2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSummary2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 176, Short.MAX_VALUE))
                    .addComponent(btnSummary2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSummary1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdvanced, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDetailed, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSummary1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(btnSummary2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDetailed, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAdvanced, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdvancedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdvancedActionPerformed
        this.dispose();
        AdminReportDateDialog dateDialog = new AdminReportDateDialog(null, true, 2);
        dateDialog.setLocationRelativeTo(null);
        dateDialog.setVisible(true);
    }//GEN-LAST:event_btnAdvancedActionPerformed

    private void btnDetailedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailedActionPerformed
        this.dispose();
        AdminReportDateDialog dateDialog = new AdminReportDateDialog(null, true, 1);
        dateDialog.setLocationRelativeTo(null);
        dateDialog.setVisible(true);
    }//GEN-LAST:event_btnDetailedActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.dispose(); 
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void btnSummary1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSummary1ActionPerformed
        control.AdminManagement adminManager = new control.AdminManagement();

        String reportText = adminManager.generateAdminReport();

        javax.swing.JTextArea textArea = new javax.swing.JTextArea(reportText);
        textArea.setEditable(false);
        textArea.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 14));
        textArea.setMargin(new java.awt.Insets(10, 10, 10, 10));

        javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(textArea);
        scrollPane.setPreferredSize(new java.awt.Dimension(700, 500));

        Object[] options = {"Close", "Save as TXT"};

        int choice = javax.swing.JOptionPane.showOptionDialog(this,
            scrollPane,
            "Admin Summary Report",
            javax.swing.JOptionPane.YES_NO_OPTION,
            javax.swing.JOptionPane.PLAIN_MESSAGE,
            null,
            options,
            options[0]);

        // Save to TXT logic
        if (choice == 1) {
            javax.swing.JFileChooser fileChooser = new javax.swing.JFileChooser();
            fileChooser.setDialogTitle("Save Report as TXT");
            fileChooser.setSelectedFile(new java.io.File("Admin_Summary_Report.txt"));

            int userSelection = fileChooser.showSaveDialog(this);

            if (userSelection == javax.swing.JFileChooser.APPROVE_OPTION) {
                java.io.File fileToSave = fileChooser.getSelectedFile();

                if (!fileToSave.getName().toLowerCase().endsWith(".txt")) {
                    fileToSave = new java.io.File(fileToSave.getParentFile(), fileToSave.getName() + ".txt");
                }

                try (java.io.PrintWriter writer = new java.io.PrintWriter(fileToSave)) {
                    writer.print(reportText);
                    javax.swing.JOptionPane.showMessageDialog(this,
                        "Report saved successfully to:\n" + fileToSave.getAbsolutePath(),
                        "Save Successful",
                        javax.swing.JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    javax.swing.JOptionPane.showMessageDialog(this,
                        "Error saving file: " + e.getMessage(),
                        "Save Error",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_btnSummary1ActionPerformed

    private void btnSummary2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSummary2ActionPerformed
        this.dispose();
        AdminReportDateDialog dateDialog = new AdminReportDateDialog(null, true, 3);
        dateDialog.setLocationRelativeTo(null);
        dateDialog.setVisible(true);
    }//GEN-LAST:event_btnSummary2ActionPerformed

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
            java.util.logging.Logger.getLogger(AdminReportSelectionDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminReportSelectionDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminReportSelectionDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminReportSelectionDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AdminReportSelectionDialog dialog = new AdminReportSelectionDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdvanced;
    private javax.swing.JButton btnDetailed;
    private javax.swing.JButton btnSummary1;
    private javax.swing.JButton btnSummary2;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
