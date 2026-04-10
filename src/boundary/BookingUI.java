/**
 * @author Mak Xuan Er
 */
package boundary;

import control.BookingManagement;
import control.FacilityManagement;
import control.ScheduleControl;
import control.StudentManagement;
import entity.Schedule;
import entity.ScheduleSession;
import adt.*;

/**
 *
 * @author user
 */
public class BookingUI extends javax.swing.JPanel {

    private BookingManagement bookingManager;
    private ScheduleControl scheduleManager;
    private FacilityManagement facilityManager;
    private StudentManagement studentManager;
    private ListInterface<Schedule> allSchedule;
    
    public BookingUI(BookingManagement bookingManager, ScheduleControl scheduleManager, FacilityManagement facilityManager, StudentManagement studentManager) {
        initComponents();

        sortingComboBox.removeAllItems();
        sortingComboBox.addItem("Default (Chronological)");
        sortingComboBox.addItem("By Facility & Status");
        
        this.bookingManager = bookingManager;
        this.scheduleManager = scheduleManager;
        this.facilityManager = facilityManager;
        this.studentManager = studentManager;
        GroupBorderRenderer renderer = new GroupBorderRenderer(3, false); 
        for (int i = 0; i < ScheduleTable.getColumnCount(); i++) {
            ScheduleTable.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
        dateCB.removeAllItems();
        java.time.LocalDate today = java.time.LocalDate.now();
        for (int dayOffset = 0; dayOffset < 3; dayOffset++) {
            java.time.LocalDate targetDate = today.plusDays(dayOffset);
            String targetDateString = targetDate.format(entity.Schedule.DATE_FORMATTER);
            dateCB.addItem(targetDateString);
        }
        if (dateCB.getItemCount() > 0) {
            dateCB.setSelectedIndex(0);
        }
        loadTableData();
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                loadTableData();
            }
        });
    }
    
    private void loadTableData() {
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) ScheduleTable.getModel();
        model.setRowCount(0);
        
        this.allSchedule = scheduleManager.getThreeDaySchedules();
        
        //Formatter to remove the 'T' 
        /*java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        for (int i = 0; i < this.allSchedule.size(); i++) {
            entity.Schedule s = this.allSchedule.get(i);
            String facilityName = s.getFacility().getName();
            String date = s.getDate();
            ListInterface<ScheduleSession> sessions = s.getSessions();
            
            for (int j = 0; j < sessions.size(); j++) {
                //if (s.getAvailability().equals("Available")) {
                    model.addRow(new Object[]{
                        facilityName, 
                        date, 
                        s.getSessions().get(j).getSession(),
                        s.getSessions().get(j).getAvailability()
                    });
                //}
            }
        }*/
        String selectedDate = (String) dateCB.getSelectedItem();
        if (selectedDate == null || this.allSchedule == null) return;

        // Grab the current time and a formatter to check against
        java.time.LocalDateTime now = java.time.LocalDateTime.now();
        java.time.format.DateTimeFormatter dateFormatter = java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy");

        for (int i = 0; i < this.allSchedule.size(); i++) {
            entity.Schedule s = this.allSchedule.get(i);
            
            if (s.getDate().equals(selectedDate)) {
                
                String facilityName = s.getFacility().getName();
                String date = s.getDate();
                adt.ListInterface<ScheduleSession> sessions = s.getSessions();

                for (int j = 0; j < sessions.size(); j++) {
                    String sessionStr = sessions.get(j).getSession();
                    
                    String startTimeStr = sessionStr.split(" ")[0]; 
                        
                    java.time.LocalDate schedDate = java.time.LocalDate.parse(date, dateFormatter);
                    java.time.LocalTime startTime = java.time.LocalTime.parse(startTimeStr);
                    java.time.LocalDateTime sessionStartDateTime = java.time.LocalDateTime.of(schedDate, startTime);

                    if (sessionStartDateTime.isBefore(now)) {
                        model.addRow(new Object[]{
                            facilityName, 
                            date, 
                            sessionStr,
                            "Expired" 
                        });
                    } else {
                        model.addRow(new Object[]{
                            facilityName,
                            date,
                            sessionStr,
                            sessions.get(j).getAvailability()
                        });
                    }
                        
              
                }
            }
        }
        
        
    }

    private void displaySearchResults(adt.ListInterface<entity.Schedule> searchResults, String selectedDate) {
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) ScheduleTable.getModel();
        model.setRowCount(0); // Clear the current rows
        
        java.time.LocalDateTime now = java.time.LocalDateTime.now();
        java.time.format.DateTimeFormatter dateFormatter = java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy");

        for (int i = 0; i < searchResults.size(); i++) {
            entity.Schedule s = searchResults.get(i);
            
            if (s.getDate().equals(selectedDate)) {
                String facilityName = s.getFacility().getName();
                String date = s.getDate();
                adt.ListInterface<ScheduleSession> sessions = s.getSessions();

                for (int j = 0; j < sessions.size(); j++) {
                    String sessionStr = sessions.get(j).getSession();
                    String startTimeStr = sessionStr.split(" ")[0]; 
                        
                    java.time.LocalDate schedDate = java.time.LocalDate.parse(date, dateFormatter);
                    java.time.LocalTime startTime = java.time.LocalTime.parse(startTimeStr);
                    java.time.LocalDateTime sessionStartDateTime = java.time.LocalDateTime.of(schedDate, startTime);

                    // Check if it is expired before drawing!
                    if (sessionStartDateTime.isBefore(now)) {
                        model.addRow(new Object[]{facilityName, date, sessionStr, "Expired"});
                    } else {
                        model.addRow(new Object[]{facilityName, date, sessionStr, sessions.get(j).getAvailability()});
                    }
                }
            }
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        ScheduleTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        idText1 = new javax.swing.JLabel();
        StudentIdTxtField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        FacilityName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        SessionField = new javax.swing.JTextField();
        dateCB = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        BookButton = new javax.swing.JButton();
        sortingComboBox = new javax.swing.JComboBox<>();
        searchButton = new javax.swing.JButton();
        searchField = new javax.swing.JTextField();

        jScrollPane1.setBorder(null);

        ScheduleTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Facility Name", "Date", "Session", "Availability"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ScheduleTable.setMinimumSize(new java.awt.Dimension(300, 300));
        ScheduleTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ScheduleTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(ScheduleTable);

        jPanel1.setMinimumSize(new java.awt.Dimension(100, 200));

        idText1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        idText1.setText("Student ID:");

        StudentIdTxtField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StudentIdTxtFieldActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel1.setText("Facility: ");

        FacilityName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FacilityNameActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel3.setText("Date: ");

        jLabel4.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel4.setText("Session: ");

        SessionField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SessionFieldActionPerformed(evt);
            }
        });

        dateCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateCBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(idText1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(dateCB, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FacilityName, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(StudentIdTxtField, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SessionField, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(StudentIdTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idText1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(FacilityName, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dateCB, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(SessionField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(192, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Facility Booking System");
        jLabel2.setMaximumSize(new java.awt.Dimension(270, 120));
        jLabel2.setMinimumSize(new java.awt.Dimension(270, 120));

        BookButton.setText("Book Facility");
        BookButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BookButtonActionPerformed(evt);
            }
        });

        sortingComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        sortingComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortingComboBoxActionPerformed(evt);
            }
        });

        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        searchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BookButton))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(179, 179, 179)
                                .addComponent(searchButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sortingComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 2, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sortingComboBox, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                            .addComponent(searchButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(searchField))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BookButton)
                        .addGap(34, 34, 34))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BookButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BookButtonActionPerformed
        String studentId = StudentIdTxtField.getText().trim().toUpperCase();
        String facility = FacilityName.getText().trim();
        String date = dateCB.getSelectedItem().toString().trim();
        String session = SessionField.getText().trim();
        String facilityId = new String();

        if (studentId.isEmpty() || facility.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please fill in all fields!", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int[] selectedScheduleIndex = scheduleManager.getSpecificScheduleIndex(facility, date, session);
        
        if (selectedScheduleIndex[0] == -1 || selectedScheduleIndex[1] == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Invalid Facility or Session! Please select a valid slot from the table.", 
                "Selection Error", 
                javax.swing.JOptionPane.ERROR_MESSAGE);
            return; 
        }
        
        if (selectedScheduleIndex[0] != -1 && selectedScheduleIndex[1] != -1) {
            facilityId = scheduleManager.getAllSchedule().get(selectedScheduleIndex[0]).getFacility().getFacilityId();

            entity.Facility selectedFacility = facilityManager.getFacilityById(facilityId);

            if (selectedFacility != null &&
                !selectedFacility.getFacilityId().toUpperCase().startsWith("Q") &&
                (selectedFacility.getType().equalsIgnoreCase("Library Study Room")||
                    selectedFacility.getType().equalsIgnoreCase("Cyber Centre Discussion Room"))){
                String numberOfMemberStr = javax.swing.JOptionPane.showInputDialog(this, "How Many Group Member would you like to invite?\n Enter number (1-5)");
                int memberNeeded = 0;

                if(numberOfMemberStr == null){
                    return;
                }

                try{
                    memberNeeded = Integer.parseInt(numberOfMemberStr.trim());
                    if(memberNeeded < 1 || memberNeeded >5){
                        javax.swing.JOptionPane.showMessageDialog(this,"invalid amount! Please enter a number between 1 and 5!",
                            "Invalid Number",
                            javax.swing.JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }catch(NumberFormatException e){
                    javax.swing.JOptionPane.showMessageDialog(this, "Invalid Input, Please input an integer",
                        "Input Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                    return;
                }

                adt.ListInterface<String> enteredIds = new adt.SingleLinkedList<>();

                // Validation for repeating id
                String mainBookerId = studentId.trim().toUpperCase();

                for (int i = 1; i <= memberNeeded; i++) {

                    String memberId = javax.swing.JOptionPane.showInputDialog(this, "Discussion Room Requires Group!\nEnter Member " + i + " Student ID:");
                    if (memberId == null || memberId.trim().isEmpty()) return;

                    memberId = memberId.trim().toUpperCase();

                    // Validation for mainbookert
                    if (memberId.equals(mainBookerId)) {
                        javax.swing.JOptionPane.showMessageDialog(this,
                            "You cannot use your own ID!",
                            "Duplicate ID",
                            javax.swing.JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Validation for other member
                    boolean isDuplicate = false;
                    for (int j = 0; j < enteredIds.size(); j++) {
                        if (enteredIds.get(j).equals(memberId)) {
                            isDuplicate = true;
                            break;
                        }
                    }

                    if (isDuplicate) {
                        javax.swing.JOptionPane.showMessageDialog(this,
                            "You already entered this ID! Each member must be a different student.",
                            "Duplicate ID",
                            javax.swing.JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String memberName = javax.swing.JOptionPane.showInputDialog(this, "Enter member " + i + " Full Name:");
                    if (memberName == null || memberName.trim().isEmpty()) return;

                    boolean isValid = studentManager.validateMember(memberId, memberName);

                    if (!isValid) {
                        javax.swing.JOptionPane.showMessageDialog(this,
                            "Validation Failed!\nThe ID or Name for Member " + i + " is incorrect.",
                            "Security Check Failed",
                            javax.swing.JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // save id for checkingvalidation purposes
                    enteredIds.add(memberId);
                }

                javax.swing.JOptionPane.showMessageDialog(this, "Group Verified! Proceeding with booking...", "Verified", javax.swing.JOptionPane.INFORMATION_MESSAGE);

            }
        }

        String result = bookingManager.createBooking(studentId, facilityId, date, session);

        // Pop ups
        if (result.equals("SUCCESS")) {
            javax.swing.JOptionPane.showMessageDialog(this, "Booking Successful!", "Success", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            StudentIdTxtField.setText("");
            FacilityName.setText("");
            SessionField.setText("");

            loadTableData();
        }
        else if (result.equals("FULL")) {
            javax.swing.JOptionPane.showMessageDialog(this, "This facility is already booked! Please choose an Available schedule.", "Facility Full", javax.swing.JOptionPane.WARNING_MESSAGE);
        }
        else if(result.equals("LIMIT_REACHED")){
            javax.swing.JOptionPane.showMessageDialog(this, "Booking Limit Exceeded!\nYou can only make a maximum of 2 bookings per day", "Limit Reached", javax.swing.JOptionPane.WARNING_MESSAGE);

        
        }
        else {
            javax.swing.JOptionPane.showMessageDialog(this, "Error: " + result, "Failed", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BookButtonActionPerformed

    private void ScheduleTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ScheduleTableMouseClicked
        int row = ScheduleTable.getSelectedRow();
        if (row == -1) return;
        
        String facility = ScheduleTable.getValueAt(row, 0).toString();
        String date = ScheduleTable.getValueAt(row, 1).toString();
        String session = ScheduleTable.getValueAt(row, 2).toString();
        String status = ScheduleTable.getValueAt(row, 3).toString(); // Grab the status
        
        if (status.equals("Expired") || status.equals("Booked") || status.equals("Not Available") || status.equals("Maintenance")) {
            ScheduleTable.clearSelection(); 
            FacilityName.setText("");       
            SessionField.setText("");
            return;
        }
        
        FacilityName.setText(facility);
        SessionField.setText(session);
    }//GEN-LAST:event_ScheduleTableMouseClicked

    private void StudentIdTxtFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StudentIdTxtFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_StudentIdTxtFieldActionPerformed

    private void FacilityNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FacilityNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FacilityNameActionPerformed

    private void SessionFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SessionFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SessionFieldActionPerformed

    private void dateCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateCBActionPerformed
        loadTableData();
    }//GEN-LAST:event_dateCBActionPerformed

    private void sortingComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortingComboBoxActionPerformed
        if (scheduleManager == null) return; 
        
        int selectedIndex = sortingComboBox.getSelectedIndex();
        if (selectedIndex == -1) return;
        
        switch (selectedIndex) {
            case 0:
                scheduleManager.sortSchedules(); 
                scheduleManager.sortSessionsByTime();
                break;
            case 1:
                scheduleManager.sortByFacilityAndStatus();
                break;
        }
        loadTableData();
    }//GEN-LAST:event_sortingComboBoxActionPerformed

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchFieldActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        String query = searchField.getText().trim();
        String currentDate = (String) dateCB.getSelectedItem();
        
        if (query.isEmpty()) {
            loadTableData(); // Reload normal table if empty
            return;
        }
        
        if (currentDate != null) {
            adt.ListInterface<entity.Schedule> results = scheduleManager.search(query, currentDate);
            
            if (results.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "No facilities found matching '" + query + "'.", 
                    "Not Found", 
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
            } else {
                displaySearchResults(results, currentDate);
            }
        }
    }//GEN-LAST:event_searchButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BookButton;
    private javax.swing.JTextField FacilityName;
    private javax.swing.JTable ScheduleTable;
    private javax.swing.JTextField SessionField;
    private javax.swing.JTextField StudentIdTxtField;
    private javax.swing.JComboBox<String> dateCB;
    private javax.swing.JLabel idText1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchField;
    private javax.swing.JComboBox<String> sortingComboBox;
    // End of variables declaration//GEN-END:variables
}
