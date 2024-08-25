package org.mrbs.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class MeetingRoom {
    private String roomId;
    private String roomType;
    private int roomCredits;
    private int roomCapacity;
    private Set<Amenity> addedAmenities;
    private TreeSet<Meeting> meetings;

    public MeetingRoom(String roomId, String roomType, int roomCredits, int roomCapacity, Set<Amenity> addedAmenities, TreeSet<Meeting> meetings) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.roomCredits = roomCredits;
        this.roomCapacity = roomCapacity;
        this.addedAmenities = addedAmenities;
        this.meetings = meetings;
    }

    public MeetingRoom(String roomId, int roomCredits, String roomType, int roomCapacity) {
        this.roomId = roomId;
        this.roomCredits = roomCredits;
        this.roomType = roomType;
        this.roomCapacity = roomCapacity;
        this.addedAmenities = new HashSet<>();
        this.meetings = new TreeSet<>();
    }

    @Override
    public String toString() {
        return "MeetingRoom{" +
                "roomId='" + roomId + '\'' +
                ", roomType='" + roomType + '\'' +
                ", roomCredits=" + roomCredits +
                ", roomCapacity=" + roomCapacity +
                ", addedAmenities=" + addedAmenities +
                '}';
    }

    public TreeSet<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(TreeSet<Meeting> meetings) {
        this.meetings = meetings;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getRoomCredits() {
        return roomCredits;
    }

    public void setRoomCredits(int roomCredits) {
        this.roomCredits = roomCredits;
    }

    public int getRoomCapacity() {
        return roomCapacity;
    }

    public void setRoomCapacity(int roomCapacity) {
        this.roomCapacity = roomCapacity;
    }

    public Set<Amenity> getAddedAmenities() {
        return addedAmenities;
    }

    public void setAddedAmenities(Set<Amenity> addedAmenities) {
        this.addedAmenities = addedAmenities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeetingRoom that = (MeetingRoom) o;
        return Objects.equals(roomId, that.roomId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomType);
    }
}