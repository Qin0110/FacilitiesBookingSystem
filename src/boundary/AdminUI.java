/**
 * @author Ng WEi Hao
 */
package boundary;
import control.BookingManagement;
import control.StudentManagement; 
import control.FacilityManagement;
import control.ScheduleControl;
import control.AdminManagement;

public class AdminUI extends javax.swing.JPanel {
    
    
    private StudentManagement studentManager;
    private AdminManagement adminManager;
    private BookingManagement bookingManager;
    private FacilityManagement facilityManager;
    private ScheduleControl scheduleManager;

    public AdminUI(AdminManagement adminControl, StudentManagement studentControl, BookingManagement bookingControl, FacilityManagement facilityControl, ScheduleControl scheduleControl) {
        this.studentManager = studentControl;
        this.adminManager = adminControl;
        this.bookingManager = bookingControl;
        this.facilityManager = facilityControl;
        this.scheduleManager = scheduleControl;
        initComponents();
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
        studentManagementBT = new javax.swing.JButton();
        scheduleManagementBT = new javax.swing.JButton();
        BookingBT = new javax.swing.JButton();
        logoutBT = new javax.swing.JButton();
        adminManagementBT = new javax.swing.JButton();
        mainContentPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        facilityManagmenetBT.setText("Facility Management");
        facilityManagmenetBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facilityManagmenetBTActionPerformed(evt);
            }
        });

        studentManagementBT.setText("Student Management");
        studentManagementBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentManagementBTActionPerformed(evt);
            }
        });

        scheduleManagementBT.setText("Schedule Managemenet");
        scheduleManagementBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scheduleManagementBTActionPerformed(evt);
            }
        });

        BookingBT.setText("Booking History");
        BookingBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BookingBTActionPerformed(evt);
            }
        });

        logoutBT.setText("Logout ");
        logoutBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBTActionPerformed(evt);
            }
        });

        adminManagementBT.setText("Admin Management");
        adminManagementBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminManagementBTActionPerformed(evt);
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(studentManagementBT, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(adminManagementBT, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(facilityManagmenetBT, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(scheduleManagementBT, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(10, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BookingBT, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(logoutBT, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(adminManagementBT, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(studentManagementBT, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(facilityManagmenetBT, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(scheduleManagementBT, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BookingBT, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(logoutBT, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(387, Short.MAX_VALUE))
        );

        mainContentPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mainContentPanel.setLayout(new java.awt.BorderLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/tarumt-logo.png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 312, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/systemTitle.png"))); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, 468, 81));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1155, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mainContentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mainContentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void facilityManagmenetBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facilityManagmenetBTActionPerformed
        FacilityUI facilityUI = new FacilityUI(facilityManager, bookingManager);
        showPanel(facilityUI);
    }//GEN-LAST:event_facilityManagmenetBTActionPerformed

    private void studentManagementBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentManagementBTActionPerformed
        StudentInAdminModeUI studentUI = new StudentInAdminModeUI(this.studentManager);
        showPanel(studentUI);
    }//GEN-LAST:event_studentManagementBTActionPerformed

    private void scheduleManagementBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scheduleManagementBTActionPerformed
        ScheduleUI scheduleUI = new ScheduleUI(this.scheduleManager, this.bookingManager);
        showPanel(scheduleUI);
        mainContentPanel.setLayout(new java.awt.BorderLayout());
        mainContentPanel.add(scheduleUI, java.awt.BorderLayout.NORTH);
    }//GEN-LAST:event_scheduleManagementBTActionPerformed

    private void BookingBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BookingBTActionPerformed
        AdminBookingUI adminBookingUI = new AdminBookingUI(this.bookingManager, this.scheduleManager, this.facilityManager);
        showPanel(adminBookingUI);
    }//GEN-LAST:event_BookingBTActionPerformed

    private void logoutBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBTActionPerformed
        java.awt.Window window = javax.swing.SwingUtilities.getWindowAncestor(this);

        if (window instanceof MainMenu) {
            MainMenu mainMenu = (MainMenu) window;
            mainMenu.backToMenu();
        }
    }//GEN-LAST:event_logoutBTActionPerformed

    private void adminManagementBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminManagementBTActionPerformed
        AdminInAdminModeUI adminUI = new AdminInAdminModeUI(this.adminManager);
        showPanel(adminUI);
    }//GEN-LAST:event_adminManagementBTActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BookingBT;
    private javax.swing.JButton adminManagementBT;
    private javax.swing.JButton facilityManagmenetBT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton logoutBT;
    private javax.swing.JPanel mainContentPanel;
    private javax.swing.JButton scheduleManagementBT;
    private javax.swing.JButton studentManagementBT;
    // End of variables declaration//GEN-END:variables
}
