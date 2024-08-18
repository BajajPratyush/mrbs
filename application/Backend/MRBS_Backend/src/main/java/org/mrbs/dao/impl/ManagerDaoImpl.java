package org.mrbs.dao.impl;

import org.mrbs.dao.intf.ManagerDaoIntf;
import org.mrbs.entity.Meeting;
import org.mrbs.entity.User;
import org.mrbs.entity.UserRole;

import java.util.*;
import java.sql.*;
import java.util.ArrayList;

import static java.sql.DriverManager.getConnection;

public class ManagerDaoImpl implements ManagerDaoIntf{

    private final String jdbcUrl = "jdbc:mysql://localhost:3306/yourdb";
    private final String jdbcUser = "root";
    private final String jdbcPassword = "root";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
    }

    public List<User> findAllManagers() {
        List<User> managers = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE role = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, UserRole.MANAGER.name());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                User manager = mapResultSetToUser(rs);
                manager.setMeetings(findMeetingsByUserId(manager.getUserId()));
                managers.add(manager);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return managers;
    }

    private User mapResultSetToUser(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getInt("phone"),
                UserRole.valueOf(rs.getString("role")),
                rs.getInt("credits")
        );
    }


    private TreeSet<Meeting> findMeetingsByUserId(int userId) {
        TreeSet<Meeting> meetings = new TreeSet<>();
        String sql = "SELECT * FROM meetings WHERE user_id = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Meeting meeting = new Meeting(
                        rs.getTimestamp("start_time").toLocalDateTime(),
                        rs.getTimestamp("end_time").toLocalDateTime(),
                        rs.getString("title")
                );
                meetings.add(meeting);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return meetings;
    }

    public User findManagerById(int id) {
        String sql = "SELECT * FROM users WHERE id = ? AND role = ?";
        User manager = null;

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setString(2, UserRole.MANAGER.name());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                manager = new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getInt("phone"),
                        UserRole.MANAGER,
                        rs.getInt("credits")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return manager;
    }

    public void updateManager(User manager) {
        String sql = "UPDATE users SET name = ?, email = ?, phone = ?, credits = ? WHERE id = ? AND role = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, manager.getUserName());
            stmt.setString(2, manager.getUserEmail());
            stmt.setInt(3, manager.getUserNumber());
            stmt.setInt(4, manager.getCredits());
            stmt.setLong(5, manager.getUserId());
            stmt.setString(6, manager.getUserRole().name());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}




