package org.mrbs.dao.impl;

import org.mrbs.dao.intf.UserDaoIntf;
import org.mrbs.entity.Meeting;
import org.mrbs.entity.User;
import org.mrbs.model.exceptions.UserXMLProcessingException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class  UserDaoImpl implements UserDaoIntf {

    @Override
    public Set<Meeting> showAllMeetings(User user) {
        return user.getMeetings();
    }

    @Override
    public void addUsers(List<User> users) throws UserXMLProcessingException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/mrbs";
        String user = "root";
        String password = "Bajaj@123";

        String query = "INSERT INTO users (user_id, name, email, phone, role) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = con.prepareStatement(query)) {

            for (User u : users) {
                stmt.setInt(1, u.getUserId());
                stmt.setString(2, u.getUserName());
                stmt.setString(3, u.getUserEmail());
                stmt.setLong(4, u.getUserNumber());
                stmt.setString(5, u.getUserRole().toString());
                stmt.addBatch();
            }

            stmt.executeBatch();
        } catch (SQLException e) {
            throw new UserXMLProcessingException("Failed to add users to the database :"+e);
        }
    }
}
