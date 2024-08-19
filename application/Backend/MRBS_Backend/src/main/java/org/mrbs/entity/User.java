package org.mrbs.entity;

import java.util.TreeSet;
import java.time.LocalDateTime;

public class User {
    private int userId;
    private String userName;
    private String userEmail;
    private long userNumber;
    private UserRole userRole;
    private int credits;
    private TreeSet<Meeting> meetings;

    private static int idGenerator = 0;

    public User(int userId, String userName, String userEmail, long userNumber, UserRole userRole, int credits, TreeSet<Meeting> meetings) {
        this.userId = idGenerator++;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userNumber = userNumber;
        this.userRole = userRole;
        this.credits = credits;
        this.meetings = meetings;
    }

    public User(int userId, String userName, String userEmail, long userNumber, UserRole userRole) {
        this.userId = idGenerator++;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userNumber = userNumber;
        this.userRole = userRole;
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

    public long getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(long userNumber) {
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

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setMeetings(TreeSet<Meeting> meetings) {
        this.meetings = meetings;
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
