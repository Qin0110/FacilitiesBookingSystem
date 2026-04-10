/**
 *
 * @author Ng Wei Hao
 */
package boundary;

public class AdminReportDateDialog extends javax.swing.JDialog {
    private int reportType;
    private control.AdminManagement adminManagement = new control.AdminManagement();
 
    public AdminReportDateDialog(java.awt.Frame parent, boolean modal, int reportType) {
        super(parent, modal);
        this.reportType = reportType;
        initComponents();
        
        if (reportType != 1 ) {
            jLabel3.setVisible(false);
            txtAdminId.setVisible(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtStartDate = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtEndDate = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtAdminId = new javax.swing.JTextField();
        generateButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Start Date (dd-MM-yyyy):");

        jLabel2.setText("End Date (dd-MM-yyyy):");

        jLabel3.setText("Admin ID :");

        generateButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        generateButton.setForeground(new java.awt.Color(41, 98, 160));
        generateButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/editBT.png"))); // NOI18N
        generateButton.setText("Generate");
        generateButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(41, 98, 160), 1, true));
        generateButton.setFocusPainted(false);
        generateButton.setIconTextGap(10);
        generateButton.setOpaque(true);
        generateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateButtonActionPerformed(evt);
            }
        });

        backButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        backButton.setForeground(new java.awt.Color(41, 98, 160));
        backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/backBT.png"))); // NOI18N
        backButton.setText("Back");
        backButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(41, 98, 160), 1, true));
        backButton.setFocusPainted(false);
        backButton.setIconTextGap(10);
        backButton.setOpaque(true);
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
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
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEndDate))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAdminId))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(generateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtStartDate, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(txtEndDate, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(txtAdminId, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(generateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void generateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateButtonActionPerformed
        String startStr = txtStartDate.getText().trim();
        String endStr = txtEndDate.getText().trim();
        String adminIdStr = txtAdminId.getText().trim();

        // Basic empty check
        if (startStr.isEmpty() || endStr.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please enter both dates.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (reportType == 1 && adminIdStr.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please enter an Admin ID.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate Dates
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy");
        java.time.LocalDate startDate = null;
        java.time.LocalDate endDate = null;

        try {
            startDate = java.time.LocalDate.parse(startStr, formatter);
            endDate = java.time.LocalDate.parse(endStr, formatter);
            if (startDate.isAfter(endDate)) {
                javax.swing.JOptionPane.showMessageDialog(this, "Start Date cannot be after End Date.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (java.time.format.DateTimeParseException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Invalid date format! Use dd-MM-yyyy", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Generate the report string based on selection
        String finalReportText = "";
        String reportTitle = "";
        String defaultFileName = "";


        if (reportType == 1) {
            finalReportText = adminManagement.generateDetailedReport(adminIdStr, startDate, endDate);

            if (finalReportText.startsWith("Error:")) {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "Admin ID '" + adminIdStr + "' not found in the system.",
                    "Admin Not Found",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }

            reportTitle = "Admin Activity Ledger";
            defaultFileName = "Admin_Ledger_" + adminIdStr + ".txt";
        }
        else if (reportType == 2) {
            finalReportText = adminManagement.generateCrossPositionReport(startDate, endDate); 
            reportTitle = "Cross-Position Trends";
            defaultFileName = "Trends_Report.txt";
        }
        else if (reportType == 3) {
            finalReportText = adminManagement.generateSummaryReport(startDate, endDate); 
            reportTitle = "Admin Booking Summary Report";
            defaultFileName = "Admin_Summary_Report.txt";
        }
        

        this.dispose();


        javax.swing.JTextArea textArea = new javax.swing.JTextArea(finalReportText);
        textArea.setEditable(false);
        textArea.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 14));
        textArea.setMargin(new java.awt.Insets(10, 10, 10, 10));

        javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(textArea);
        scrollPane.setPreferredSize(new java.awt.Dimension(700, 500));

        Object[] options = {"Close", "Save as TXT"};

        int choice = javax.swing.JOptionPane.showOptionDialog(null,
            scrollPane,
            reportTitle,
            javax.swing.JOptionPane.YES_NO_OPTION,
            javax.swing.JOptionPane.PLAIN_MESSAGE,
            null,
            options,
            options[0]);

        // Save logic
        if (choice == 1) {
            javax.swing.JFileChooser fileChooser = new javax.swing.JFileChooser();
            fileChooser.setDialogTitle("Save Report as TXT");
            fileChooser.setSelectedFile(new java.io.File(defaultFileName));

            int userSelection = fileChooser.showSaveDialog(null);

            if (userSelection == javax.swing.JFileChooser.APPROVE_OPTION) {
                java.io.File fileToSave = fileChooser.getSelectedFile();

                if (!fileToSave.getName().toLowerCase().endsWith(".txt")) {
                    fileToSave = new java.io.File(fileToSave.getParentFile(), fileToSave.getName() + ".txt");
                }

                try (java.io.PrintWriter writer = new java.io.PrintWriter(fileToSave)) {
                    writer.print(finalReportText);
                    javax.swing.JOptionPane.showMessageDialog(null,
                        "Report saved successfully to:\n" + fileToSave.getAbsolutePath(),
                        "Save Successful",
                        javax.swing.JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    javax.swing.JOptionPane.showMessageDialog(null,
                        "Error saving file: " + e.getMessage(),
                        "Save Error",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_generateButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        this.dispose();

        boundary.AdminReportSelectionDialog selectionDialog = new boundary.AdminReportSelectionDialog(null, true);
        selectionDialog.setLocationRelativeTo(null);
        selectionDialog.setVisible(true);
    }//GEN-LAST:event_backButtonActionPerformed

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
            java.util.logging.Logger.getLogger(AdminReportDateDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminReportDateDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminReportDateDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminReportDateDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AdminReportDateDialog dialog = new AdminReportDateDialog(new javax.swing.JFrame(), true, 1);
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
    private javax.swing.JButton backButton;
    private javax.swing.JButton generateButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txtAdminId;
    private javax.swing.JTextField txtEndDate;
    private javax.swing.JTextField txtStartDate;
    // End of variables declaration//GEN-END:variables
}
