package org.mrbs.entity;

import java.time.LocalDateTime;
import java.util.TreeSet;

public class User {
    private int userId;
    private String userName;
    private String userEmail;
    private Long userNumber;
    private UserRole userRole;
    private int credits;
    private TreeSet<Meeting> meetings;

//    private static int idGenerator = 0;
//
    public User(int userId, String userName, String userEmail, Long userNumber, UserRole userRole) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userNumber = userNumber;
        this.userRole = userRole;
    }

    public User(int userId, String userName, String userEmail, Long userNumber, UserRole userRole, int credits) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userNumber = userNumber;
        this.userRole = userRole;
        this.credits = credits;
    }

    public User(int userId, String userName, String userEmail, Long userNumber, UserRole userRole, int credits, Meeting m) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userNumber = userNumber;
        this.userRole = userRole;
        this.credits = credits;
        this.meetings.add(m);
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

    public Long getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(Long userNumber) {
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

    public void setMeetings(Meeting meetings) {
        this.meetings.add(meetings);
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
