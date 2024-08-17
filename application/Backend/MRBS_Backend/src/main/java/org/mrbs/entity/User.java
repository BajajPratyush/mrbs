package org.mrbs.entity;

import java.util.TreeSet;
import java.time.LocalDateTime;

public class User {
    private int userId;
    private String userName;
    private String userEmail;
    private int userNumber;
    private UserRole userRole;
    private TreeSet<Meeting> meetings;

    private static int idGenerator = 0;

    public User(String userName, String userEmail, int userNumber, UserRole userRole) {
        this.userId = idGenerator++;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userNumber = userNumber;
        this.userRole = userRole;
        this.meetings = new TreeSet<>();
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public TreeSet<Meeting> getMeetings() {
        return meetings;
    }

    public void addMeeting(LocalDateTime startTime, LocalDateTime endTime, String room) {
        this.meetings.add(new Meeting(startTime, endTime, room));
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userNumber=" + userNumber +
                ", userRole=" + userRole +
                ", meetings=" + meetings +
                '}';
    }
}
