/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package boundary;
import UtilitiesClass.Input;
import control.BookingManagement;
import control.AdminManagement;
import control.StudentManagement;
import control.ScheduleControl;
import control.FacilityManagement;

/**
 *
 * @author User
 */
public class MainMenu extends javax.swing.JFrame {
    
    
    private FacilityManagement facilityManager;
    private StudentManagement studentManager;
    private AdminManagement adminManager;
    private BookingManagement bookingManager;
    private ScheduleControl scheduleManager;
    
    private StudentUI studentUI;
    private AdminUI adminUI;

    public MainMenu(AdminManagement adminManager, StudentManagement studentManager, BookingManagement bookingManager, FacilityManagement facilityManager, ScheduleControl scheduleManager) {
        initComponents();
        setSize(1173, 811);
  
        setLocationRelativeTo(null);
        
        this.studentUI = new StudentUI(bookingManager, scheduleManager, facilityManager);
        this.adminUI = new AdminUI(adminManager, studentManager, bookingManager, facilityManager, scheduleManager);
        
        this.studentManager = studentManager;
        this.adminManager = adminManager;
        this.bookingManager = bookingManager;
        this.facilityManager = facilityManager;
        this.scheduleManager = scheduleManager;
        
    }
    
    public void backToMenu() {
        MainMenu newMenu = new MainMenu(adminManager, studentManager, bookingManager, facilityManager, scheduleManager);
        newMenu.setVisible(true);
        this.dispose();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        TARUMTLOGO = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        studentBT = new javax.swing.JButton();
        adminBT = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        TARUMTLOGO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/tarumt-logo.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 17;
        gridBagConstraints.ipady = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(23, 28, 0, 0);
        getContentPane().add(TARUMTLOGO, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        studentBT.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        studentBT.setText("STUDENT");
        studentBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentBTActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 98;
        gridBagConstraints.ipady = 23;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(70, 267, 0, 264);
        jPanel1.add(studentBT, gridBagConstraints);

        adminBT.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        adminBT.setText("ADMINISTRATOR");
        adminBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminBTActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 9;
        gridBagConstraints.ipady = 23;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 268, 0, 264);
        jPanel1.add(adminBT, gridBagConstraints);

        jButton3.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jButton3.setText("EXIT");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 156;
        gridBagConstraints.ipady = 23;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 268, 157, 264);
        jPanel1.add(jButton3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 261;
        gridBagConstraints.ipady = 151;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        getContentPane().add(jPanel1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void adminBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminBTActionPerformed
       AdminUI adminUI = new AdminUI(adminManager, studentManager, bookingManager, facilityManager, scheduleManager);
       this.setContentPane(adminUI);
       this.revalidate();
       this.repaint();
    }//GEN-LAST:event_adminBTActionPerformed

    private void studentBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentBTActionPerformed
        StudentUI studentUI = new StudentUI(bookingManager, scheduleManager, facilityManager);
        this.setContentPane(studentUI);
        this.revalidate();
        this.repaint();
    }//GEN-LAST:event_studentBTActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton3ActionPerformed

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new MainMenu().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel TARUMTLOGO;
    private javax.swing.JButton adminBT;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton studentBT;
    // End of variables declaration//GEN-END:variables
}
