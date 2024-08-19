package org.mrbs.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Meeting implements Comparable<Meeting> {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String roomId;

    public Meeting(LocalDateTime startTime, LocalDateTime endTime, String room) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.roomId = room;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getRoom() {
        return roomId;
    }

    public void setRoom(String room) {
        this.roomId = room;
    }

    @Override
    public int compareTo(Meeting other) {
        return this.startTime.compareTo(other.startTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return Objects.equals(startTime, meeting.startTime) &&
                Objects.equals(endTime, meeting.endTime) &&
                Objects.equals(roomId, meeting.roomId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, endTime, roomId);
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", room='" + roomId + '\'' +
                '}';
    }
}
