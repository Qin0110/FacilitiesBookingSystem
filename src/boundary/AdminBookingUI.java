package boundary;
import control.BookingManagement;
import control.FacilityManagement;
import control.ScheduleControl;

public class AdminBookingUI extends javax.swing.JPanel {

    /**
     * Creates new form AdminBookingUI
     */
    
    private BookingManagement bookingManager;
    
    public AdminBookingUI(BookingManagement bm, ScheduleControl sm, FacilityManagement fm) {
        initComponents(); 
        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        
        AdminBookingTable.setShowGrid(false);
        AdminBookingTable.setIntercellSpacing(new java.awt.Dimension(0, 0));
        
        this.bookingManager = bm;
        
        this.bookingManager.refreshBookingStatuses();
        
        javax.swing.Timer automationTimer = new javax.swing.Timer(60000, new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                bookingManager.refreshBookingStatuses();
                
                loadBookingTable(TxtField.getText().trim());
            }
        });
        automationTimer.start(); 

        loadBookingTable("");
    }
    
    private void loadBookingTable(String filterQuery) {
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) AdminBookingTable.getModel();
        model.setRowCount(0); 

        String searchCriteria = "All";
        if (SearchComboBox != null) {
            searchCriteria = SearchComboBox.getSelectedItem().toString();
        }

        adt.ListInterface<entity.Booking> searchResults = bookingManager.searchBooking(filterQuery, searchCriteria);
        
        String sortCriteria = "Booking ID";
        boolean isAscending = true;
        
        if (SortCriteriaComboBox1 != null && SortOrderComboBox != null) {
            sortCriteria = SortCriteriaComboBox1.getSelectedItem().toString();             
            isAscending = SortOrderComboBox.getSelectedItem().toString().equals("Ascending");
        }        
        adt.ListInterface<entity.Booking> finalSortedList = bookingManager.sortBookings(searchResults, sortCriteria, isAscending);

        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        for (int i = 0; i < finalSortedList.size(); i++) {
            entity.Booking b = finalSortedList.get(i);
            
            String facilityName = "Unknown";
            if (b.getSchedule() != null && b.getSchedule().getFacility() != null) {
                facilityName = b.getSchedule().getFacility().getName(); 
            }
                
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
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        TxtField = new javax.swing.JTextField();
        SearchBooking1 = new javax.swing.JButton();
        SearchComboBox = new javax.swing.JComboBox<>();
        BookingReportButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        AdminBookingTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        SortCriteriaComboBox1 = new javax.swing.JComboBox<>();
        SortOrderComboBox = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setLayout(new java.awt.BorderLayout());

        jPanel1.setMinimumSize(new java.awt.Dimension(200, 200));
        jPanel1.setPreferredSize(new java.awt.Dimension(300, 427));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Search (ID, Facility, etc):");

        TxtField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtFieldActionPerformed(evt);
            }
        });

        SearchBooking1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/searchBT.png"))); // NOI18N
        SearchBooking1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchBooking1ActionPerformed(evt);
            }
        });

        SearchComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Booking ID", "Student ID", "Facility Name", "Date/Session", "Status" }));

        BookingReportButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BookingReportButton.setText("Generate Report");
        BookingReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BookingReportButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(BookingReportButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(TxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(SearchComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SearchBooking1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SearchComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SearchBooking1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 263, Short.MAX_VALUE)
                .addComponent(BookingReportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

        jPanel2.setPreferredSize(new java.awt.Dimension(1103, 30));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1103, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jPanel5.setPreferredSize(new java.awt.Dimension(1103, 90));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Booking History");

        SortCriteriaComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Booking ID", "Student ID", "Date", "Status" }));
        SortCriteriaComboBox1.setPreferredSize(new java.awt.Dimension(100, 25));
        SortCriteriaComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SortCriteriaComboBox1ActionPerformed(evt);
            }
        });

        SortOrderComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ascending", "Descending" }));
        SortOrderComboBox.setPreferredSize(new java.awt.Dimension(100, 25));
        SortOrderComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SortOrderComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(300, 300, 300)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 270, Short.MAX_VALUE)
                .addComponent(SortCriteriaComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SortOrderComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SortCriteriaComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SortOrderComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel5, java.awt.BorderLayout.PAGE_START);

        jPanel6.setPreferredSize(new java.awt.Dimension(30, 443));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 443, Short.MAX_VALUE)
        );

        add(jPanel6, java.awt.BorderLayout.LINE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void SearchBooking1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchBooking1ActionPerformed
        String filterQuery = TxtField.getText().trim().toUpperCase();
        loadBookingTable(filterQuery);
    }//GEN-LAST:event_SearchBooking1ActionPerformed

    private void TxtFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtFieldActionPerformed

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
            
            loadBookingTable(TxtField.getText().trim());
        }
    }//GEN-LAST:event_AdminBookingTableMouseClicked

    private void SortCriteriaComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SortCriteriaComboBox1ActionPerformed
        loadBookingTable(TxtField.getText().trim());
    }//GEN-LAST:event_SortCriteriaComboBox1ActionPerformed

    private void SortOrderComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SortOrderComboBoxActionPerformed
        loadBookingTable(TxtField.getText().trim());
    }//GEN-LAST:event_SortOrderComboBoxActionPerformed

    private void BookingReportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BookingReportButtonActionPerformed
        String reportText = bookingManager.generateBookingReport();
        
        javax.swing.JTextArea textArea = new javax.swing.JTextArea(reportText);
        textArea.setEditable(false);
        textArea.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 14)); 
        textArea.setMargin(new java.awt.Insets(10, 10, 10, 10)); 
        
        javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(textArea);
        scrollPane.setPreferredSize(new java.awt.Dimension(450, 500));
        
        Object[] options = {"Close", "Save as TXT"};
        
        int choice = javax.swing.JOptionPane.showOptionDialog(this, 
            scrollPane, 
            "Booking Summary Report", 
            javax.swing.JOptionPane.YES_NO_OPTION, 
            javax.swing.JOptionPane.PLAIN_MESSAGE, 
            null, 
            options, 
            options[0]); 
            
        if (choice == 1) {
            javax.swing.JFileChooser fileChooser = new javax.swing.JFileChooser();
            fileChooser.setDialogTitle("Save Report as TXT");
            
            fileChooser.setSelectedFile(new java.io.File("Booking_Report.txt"));
            
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
    }//GEN-LAST:event_BookingReportButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable AdminBookingTable;
    private javax.swing.JButton BookingReportButton;
    private javax.swing.JButton SearchBooking1;
    private javax.swing.JComboBox<String> SearchComboBox;
    private javax.swing.JComboBox<String> SortCriteriaComboBox1;
    private javax.swing.JComboBox<String> SortOrderComboBox;
    private javax.swing.JTextField TxtField;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
