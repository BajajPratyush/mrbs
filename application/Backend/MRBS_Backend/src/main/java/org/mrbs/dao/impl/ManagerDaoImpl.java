package org.mrbs.dao.impl;

import org.mrbs.dao.intf.ManagerDaoIntf;
import org.mrbs.entity.Meeting;
import org.mrbs.entity.User;
import org.mrbs.entity.UserRole;
import org.mrbs.model.exceptions.ManagerNotFound;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ManagerDaoImpl implements ManagerDaoIntf{


    private final String jdbcUrl = "jdbc:mysql://localhost:3306/mrbsdb";
    private final String jdbcUser = "root";
    private final String jdbcPassword = "root";

    public Connection getConnection() throws SQLException, ClassNotFoundException {
//        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
        return conn;
    }


    public boolean bookRoomById(int managerId, int roomCost, String roomId, LocalDateTime startTime, LocalDateTime endTime, Set<Integer> amenityIds) throws ManagerNotFound, SQLException {
        String insertMeetingSQL = "INSERT INTO meetings (start_time, end_time, room_id) VALUES (?, ?, ?)";
        String insertUserMeetingSQL = "INSERT INTO user_meetings (user_id, meeting_id) VALUES (?, ?)";
        String updateUserCreditsSQL = "UPDATE users SET credits = credits - ? WHERE user_id = ?";
        String checkManagerCreditsSQL = "SELECT credits FROM users WHERE user_id = ?";

        Connection conn = null;
        PreparedStatement meetingStmt = null;
        PreparedStatement userMeetingStmt = null;
        PreparedStatement updateCreditsStmt = null;
        PreparedStatement checkCreditsStmt = null;
        ResultSet generatedKeys = null;
        ResultSet creditsRS = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);

            // Check if the manager has enough credits
            checkCreditsStmt = conn.prepareStatement(checkManagerCreditsSQL);
            checkCreditsStmt.setInt(1, managerId);
            creditsRS = checkCreditsStmt.executeQuery();

            if (creditsRS.next()) {
                int managerCredits = creditsRS.getInt("credits");

                if (managerCredits < roomCost) {
                    System.out.println("Insufficient credits to book the room.");
                    conn.rollback();
                    return false;
                }

                // Insert the meeting into the meetings table
                meetingStmt = conn.prepareStatement(insertMeetingSQL, Statement.RETURN_GENERATED_KEYS);
                meetingStmt.setTimestamp(1, Timestamp.valueOf(startTime));
                meetingStmt.setTimestamp(2, Timestamp.valueOf(endTime));
                meetingStmt.setString(3, roomId);
                meetingStmt.executeUpdate();

                generatedKeys = meetingStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int meetingId = generatedKeys.getInt(1);

                    // Link the meeting to the manager in the user_meetings table
                    userMeetingStmt = conn.prepareStatement(insertUserMeetingSQL);
                    userMeetingStmt.setInt(1, managerId);
                    userMeetingStmt.setInt(2, meetingId);
                    userMeetingStmt.executeUpdate();

                    // Deduct the room cost from the manager's credits
                    updateCreditsStmt = conn.prepareStatement(updateUserCreditsSQL);
                    updateCreditsStmt.setInt(1, roomCost);
                    updateCreditsStmt.setInt(2, managerId);
                    updateCreditsStmt.executeUpdate();

                    conn.commit();
                    return true;
                } else {
                    conn.rollback();
                    return false;
                }
            } else {
                System.out.println("Manager not found.");
                conn.rollback();
                return false;
            }
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback();
            }
//            e.printStackTrace();
            return true;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void AddManager(User u){
        String sql = "INSERT INTO users(user_id, user_name, user_email, user_number, user_role, credits) values(?,?,?,?,?,?)";
        try(Connection con = DriverManager.getConnection(jdbcUrl,jdbcUser,jdbcPassword)){

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, u.getUserId());
            stmt.setString(2, u.getUserName());
            stmt.setString(3, u.getUserEmail());
            stmt.setLong(4, u.getUserNumber());
            stmt.setString(5, u.getUserRole().toString());
            stmt.setInt(6, u.getCredits());
            int rs = stmt.executeUpdate();

            if (rs> 0) {
                System.out.println("Manager added successfully!");
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<User> findAllManagers()  {
        List<User> managers = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE user_role = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, UserRole.MANAGER.name());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                User manager = mapResultSetToUser(rs);
//                manager.setMeetings(findMeetingsByUserId(manager.getUserId()));
                managers.add(manager);
            }
        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
        }

        return managers;
    }

    private User mapResultSetToUser(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt("user_id"),
                rs.getString("user_name"),
                rs.getString("user_email"),
                rs.getLong("user_number"),
                UserRole.valueOf(rs.getString("user_role")),
                rs.getInt("credits")
//                findMeetingsByUserId(rs.getInt("user_id"))
        );
    }


    private Meeting findMeetingsByUserId(int userId) {
//        TreeSet<Meeting> meetings = new TreeSet<>();
//        Meeting meetings = new Meeting();
        Meeting meetings= null;
        String sql = "SELECT m.start_time, m.end_time, m.room_id " +
                "FROM meetings m " +
                "JOIN user_meetings um ON m.meeting_id = um.meeting_id " +
                "WHERE um.user_id = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                meetings = new Meeting(
                        rs.getTimestamp("start_time").toLocalDateTime(),
                        rs.getTimestamp("end_time").toLocalDateTime(),
                        rs.getString("room_id")
                );
                return (meetings);
            }
        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
        }

        return meetings;
    }

    public User findManagerById(int id) throws ManagerNotFound {
        String sql = "SELECT * FROM users WHERE user_id = ? AND user_role = ?";
        User manager = null;

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setString(2, UserRole.MANAGER.name());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                manager = new User(
                        rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("user_email"),
                        rs.getLong("user_number"),
                        UserRole.valueOf(rs.getString("user_role")),
                        rs.getInt("credits")
//                        findMeetingsByUserId(rs.getInt("user_id"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (Exception e){
            throw new ManagerNotFound(e);
        }
        return manager;
    }

    public void updateManager(User manager) throws ManagerNotFound , SQLException{
        String sql = "UPDATE users SET user_name = ?, user_email = ?, user_phone = ?, credits = ? WHERE user_id = ? AND user_role = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, manager.getUserName());
            stmt.setString(2, manager.getUserEmail());
            stmt.setLong(3, manager.getUserNumber());
            stmt.setInt(4, manager.getCredits());
            stmt.setLong(5, manager.getUserId());
            stmt.setString(6, manager.getUserRole().name());
            stmt.executeUpdate();
        } catch (SQLException e) {
//            e.printStackTrace();
        }
        catch (Exception e)
        {
            throw new ManagerNotFound(e);
        }
    }

}




