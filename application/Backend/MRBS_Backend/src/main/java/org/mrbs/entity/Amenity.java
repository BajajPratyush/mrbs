package org.mrbs.entity;

public class Amenity {
    private String amenityName;
    private int amenityCreds;
    private int amenityId;

    public Amenity(String amenityName, int amenityCreds, int amenityId) {
        this.amenityName = amenityName;
        this.amenityCreds = amenityCreds;
        this.amenityId = amenityId;
    }

    public String getAmenityName() {
        return amenityName;
    }

    public void setAmenityName(String amenityName) {
        this.amenityName = amenityName;
    }

    public int getAmenityCreds() {
        return amenityCreds;
    }

    public void setAmenityCreds(int amenityCreds) {
        this.amenityCreds = amenityCreds;
    }

    public int getAmenityId() {
        return amenityId;
    }

    public void setAmenityId(int amenityId) {
        this.amenityId = amenityId;
    }

    @Override
    public String toString() {
        return "Amenity{" +
                "amenityName='" + amenityName + '\'' +
                ", amenityCreds=" + amenityCreds +
                ", amenityId=" + amenityId +
                '}';
    }
}
