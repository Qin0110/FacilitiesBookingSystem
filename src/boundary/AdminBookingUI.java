package boundary;
import control.BookingManagement;
import control.FacilityManagement;
import control.ScheduleControl;

public class AdminBookingUI extends javax.swing.JPanel {

    /**
     * Creates new form AdminBookingUI
     */
    
    private BookingManagement bookingManager;
    private ScheduleControl scheduleManager;
    private FacilityManagement facilityManager;
    
    public AdminBookingUI(BookingManagement bm, ScheduleControl sm, FacilityManagement fm) {
        initComponents(); 
        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        
        AdminBookingTable.setShowGrid(false);
        AdminBookingTable.setIntercellSpacing(new java.awt.Dimension(0, 0));
        
        this.bookingManager = bm;
        this.scheduleManager = sm;
        this.facilityManager = fm;
        
        this.bookingManager.refreshBookingStatuses();
        
        javax.swing.Timer automationTimer = new javax.swing.Timer(60000, new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                bookingManager.refreshBookingStatuses();
                
                // Redraw the table so the Admin sees the text change from Confirmed to Pending live!
                loadBookingTable(StudentIdTxtField.getText().trim());
            }
        });
        automationTimer.start(); 

        loadBookingTable("");
    }
    
    private void loadBookingTable(String filterId) {
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) AdminBookingTable.getModel();
        model.setRowCount(0); 

        adt.ListInterface<entity.Booking> allBookings = bookingManager.getAllBookings();

        for (int i = 0; i < allBookings.size(); i++) {
            entity.Booking b = allBookings.get(i);
            
            if (filterId.isEmpty() || b.getStudentId().equalsIgnoreCase(filterId)) {
                
                String schedId = b.getScheduleSession();
                
                //This is just for validation?
                //entity.Schedule s = scheduleManager.getScheduleById(schedId);
                
                String facilityName = "Unknown";
                java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                
//                if (s != null) {
//                    adt.ListInterface<entity.Facility> resultsList = facilityManager.searchFacility(s.getFacilityId());
//                    if (resultsList != null && resultsList.size() > 0) {
//                        facilityName = resultsList.get(0).getName(); 
//                    }
//                }
                
                model.addRow(new Object[]{
                    b.getBookingId(),
                    b.getStudentId(),
                    b.getScheduleSession(),
                    facilityName,   
                    b.getApprovedBy() == null ? "N/A" : b.getApprovedBy(),
                    b.getArrivalTime() == null ? "Not Arrived" : b.getArrivalTime().format(formatter),
                    b.getLeavingTime() == null ? "Not Left" : b.getLeavingTime().format(formatter),
                    b.getStatus()
                });
            }
        }
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        StudentIdTxtField = new javax.swing.JTextField();
        SearchBooking1 = new javax.swing.JButton();
        ViewAllButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        AdminBookingTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Booking History");
        add(jLabel1, java.awt.BorderLayout.PAGE_START);

        jPanel1.setMinimumSize(new java.awt.Dimension(200, 200));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Search Student ID:");

        StudentIdTxtField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StudentIdTxtFieldActionPerformed(evt);
            }
        });

        SearchBooking1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/searchBT.png"))); // NOI18N
        SearchBooking1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchBooking1ActionPerformed(evt);
            }
        });

        ViewAllButton.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        ViewAllButton.setText("View All");
        ViewAllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewAllButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(StudentIdTxtField)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(33, 33, 33))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(ViewAllButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SearchBooking1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(10, 10, 10))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(StudentIdTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(SearchBooking1, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(ViewAllButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(254, Short.MAX_VALUE))
        );

        add(jPanel1, java.awt.BorderLayout.LINE_START);

        jScrollPane1.setBorder(null);

        AdminBookingTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Booking ID", "Student ID", "Session", "Facility Name", "Admin", "Arrival Time", "Leaving Time", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        AdminBookingTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AdminBookingTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(AdminBookingTable);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1091, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        add(jPanel2, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void SearchBooking1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchBooking1ActionPerformed
        String studentId = StudentIdTxtField.getText().trim().toUpperCase();

        if (studentId.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please enter your Student ID first!");
            return;
        }

        loadBookingTable(studentId);
    }//GEN-LAST:event_SearchBooking1ActionPerformed

    private void ViewAllButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewAllButtonActionPerformed
        StudentIdTxtField.setText("");
        loadBookingTable("");
    }//GEN-LAST:event_ViewAllButtonActionPerformed

    private void StudentIdTxtFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StudentIdTxtFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_StudentIdTxtFieldActionPerformed

    private void AdminBookingTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AdminBookingTableMouseClicked
        if (evt.getClickCount() == 2) {
            
            int row = AdminBookingTable.getSelectedRow();
            if (row == -1) return; // Safety check

            String bookingId = AdminBookingTable.getValueAt(row, 0).toString();
            String studentId = AdminBookingTable.getValueAt(row, 1).toString();
            String session = AdminBookingTable.getValueAt(row, 2).toString();
            String facilityName = AdminBookingTable.getValueAt(row, 3).toString();
            String admin = AdminBookingTable.getValueAt(row, 4).toString();
            String arrival = AdminBookingTable.getValueAt(row, 5).toString();
            String leaving = AdminBookingTable.getValueAt(row, 6).toString();
            String status = AdminBookingTable.getValueAt(row, 7).toString();

            java.awt.Window parentWindow = javax.swing.SwingUtilities.getWindowAncestor(this);
            java.awt.Frame parentFrame = (parentWindow instanceof java.awt.Frame) ? (java.awt.Frame) parentWindow : null;

    
            BookingHistoryDialog detailsDialog = new BookingHistoryDialog(
                    parentFrame, 
                    true, 
                    bookingManager, bookingId, studentId, session, facilityName, admin, arrival, leaving, status
            );
            
            detailsDialog.setLocationRelativeTo(this);
            detailsDialog.setVisible(true);
            
            loadBookingTable(StudentIdTxtField.getText().trim());
        }
    }//GEN-LAST:event_AdminBookingTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable AdminBookingTable;
    private javax.swing.JButton SearchBooking1;
    private javax.swing.JTextField StudentIdTxtField;
    private javax.swing.JButton ViewAllButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
