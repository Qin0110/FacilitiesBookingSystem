/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package boundary;

import control.BookingManagement;
import control.StudentManagement; 
import control.FacilityManagement;
import control.ScheduleManagement;

public class AdminUI extends javax.swing.JFrame {

    private StudentManagement studentManager;
    private BookingManagement bookingManager;
    private FacilityManagement facilityManager;
    private ScheduleManagement scheduleManager;
    
    
    public AdminUI() {
        initComponents();
    }
    
    public AdminUI(StudentManagement studentControl, BookingManagement bookingControl, FacilityManagement facilityControl, ScheduleManagement scheduleControl) {
        this.studentManager = studentControl;
        this.bookingManager = bookingControl;
        this.facilityManager = facilityControl;
        this.scheduleManager = scheduleControl;
        initComponents();
    }
    
    public void openFacilityManagement() {
        // Example: facilityManager.openFacilityWindow();
    }

    public void openScheduleManagement() {
        // Logic to open schedule window
    }

    public void openMaintenanceManagement() {
        // Logic to open maintenance window
    }

    public void openBookingManagement() {
        // Logic to open booking window
    }

    public void generateReports() {
        // Logic to trigger report generation
    }

    // --- User Management Actions ---

    public void actionRegisterStudent() {
        // Logic to read from GUI TextFields and pass to studentManager
    }

    public void actionUpdateProgramme() {
        // Logic to read from GUI TextFields and update
    }

    public void actionDeleteStudent() {
        // Logic to confirm deletion via a GUI pop-up
    }

    public void actionViewAllStudents() {
        // Logic to populate a JTable with student data
    }
    
    private void showPanel(javax.swing.JPanel newPanel) {
        mainContentPanel.removeAll(); // clear the current screen
        mainContentPanel.add(newPanel, java.awt.BorderLayout.NORTH); // add the new panel
        mainContentPanel.revalidate(); // recalculate the layout
        mainContentPanel.repaint(); // redraw the screen to show the new panel
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        facilityManagmenetBT = new javax.swing.JButton();
        userManagementBT = new javax.swing.JButton();
        scheduleManagementBT = new javax.swing.JButton();
        maintenanceManagementBT = new javax.swing.JButton();
        BookingBT = new javax.swing.JButton();
        reportBT = new javax.swing.JButton();
        logoutBT = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        mainContentPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        facilityManagmenetBT.setText("Facility Management");
        facilityManagmenetBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facilityManagmenetBTActionPerformed(evt);
            }
        });

        userManagementBT.setText("User Management (Student & Staff)");
        userManagementBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userManagementBTActionPerformed(evt);
            }
        });

        scheduleManagementBT.setText("Schedule Managemenet");
        scheduleManagementBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scheduleManagementBTActionPerformed(evt);
            }
        });

        maintenanceManagementBT.setText("Maintenance Management");
        maintenanceManagementBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maintenanceManagementBTActionPerformed(evt);
            }
        });

        BookingBT.setText("Booking & Waitlist Override");
        BookingBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BookingBTActionPerformed(evt);
            }
        });

        reportBT.setText("Generate System Reports");
        reportBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportBTActionPerformed(evt);
            }
        });

        logoutBT.setText("Logout ");
        logoutBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(reportBT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(facilityManagmenetBT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(userManagementBT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(scheduleManagementBT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(maintenanceManagementBT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(BookingBT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(398, 398, 398))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(logoutBT, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(userManagementBT, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(facilityManagmenetBT, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(scheduleManagementBT, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(maintenanceManagementBT, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BookingBT, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(reportBT, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(logoutBT, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(227, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 342, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 113, Short.MAX_VALUE)
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/tarumt-logo.png"))); // NOI18N

        mainContentPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 820, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mainContentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(815, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mainContentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(682, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void facilityManagmenetBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facilityManagmenetBTActionPerformed
        FacilityUI facilityUI = new FacilityUI(this.facilityManager);
        showPanel(facilityUI);
    }//GEN-LAST:event_facilityManagmenetBTActionPerformed

    private void userManagementBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userManagementBTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userManagementBTActionPerformed

    private void scheduleManagementBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scheduleManagementBTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_scheduleManagementBTActionPerformed

    private void maintenanceManagementBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maintenanceManagementBTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_maintenanceManagementBTActionPerformed

    private void BookingBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BookingBTActionPerformed
        AdminBookingUI adminBookingUI = new AdminBookingUI(this.bookingManager, this.scheduleManager, this.facilityManager);
        showPanel(adminBookingUI);
    }//GEN-LAST:event_BookingBTActionPerformed

    private void reportBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportBTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_reportBTActionPerformed

    private void logoutBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBTActionPerformed
        this.dispose();
    }//GEN-LAST:event_logoutBTActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BookingBT;
    private javax.swing.JButton facilityManagmenetBT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton logoutBT;
    private javax.swing.JPanel mainContentPanel;
    private javax.swing.JButton maintenanceManagementBT;
    private javax.swing.JButton reportBT;
    private javax.swing.JButton scheduleManagementBT;
    private javax.swing.JButton userManagementBT;
    // End of variables declaration//GEN-END:variables
}
