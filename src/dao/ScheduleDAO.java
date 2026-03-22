//package dao;
//
//import adt.ArrayList;
//import adt.ListInterface;
//import entity.Schedule;
//import UtilitiesClass.DatabaseConnection;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.time.LocalDateTime;
//
//public class ScheduleDAO {
//
//    public ListInterface<Schedule> getAllSchedules() {
//        ListInterface<Schedule> scheduleList = new ArrayList<>();
//        String sql = "SELECT * FROM Schedule";
//
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement query = conn.prepareStatement(sql);
//             ResultSet rs = query.executeQuery()) {
//
//            while (rs.next()) {
//                String id = rs.getString("scheduleId");
//                String facId = rs.getString("facilityId");
//                LocalDateTime start = rs.getTimestamp("startingTime").toLocalDateTime();
//                LocalDateTime end = rs.getTimestamp("endingTime").toLocalDateTime();
//                String availability = rs.getString("availability");
//
//                Schedule s = new Schedule(id, facId, start, end);
//                s.setAvailability(availability);
//                scheduleList.add(s);
//            }
//
//        } catch (SQLException e) {
//            System.out.println("Error fetching schedules: " + e.getMessage());
//        }
//
//        return scheduleList;
//    }
//}