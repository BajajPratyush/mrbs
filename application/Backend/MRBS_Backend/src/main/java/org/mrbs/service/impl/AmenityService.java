package org.mrbs.service.impl;

import org.mrbs.entity.Amenity;
import org.mrbs.service.AmenityServiceIntf;

import java.util.HashMap;
import java.util.Map;

public class AmenityService implements AmenityServiceIntf {

    private final Map<Integer, Amenity> amenities;

    public AmenityService() {
        this.amenities = new HashMap<>();
        initializeAmenities(); // Initialize with predefined amenities if needed
    }

     public void initializeAmenities() {
        // Add predefined amenities here, for example:
        amenities.put(2, new Amenity("WiFi Connection", 10, 2));
        amenities.put(1, new Amenity("Projector", 5, 1));
         amenities.put(3, new Amenity("Conference Call Facility", 15, 3));
         amenities.put(4, new Amenity("Whiteboard", 5, 4));
        // Add more amenities as needed
    }

    public Amenity getAmenity(int amenityId) {
        return amenities.get(amenityId);
    }

    public void addAmenity(Amenity amenity) {
        amenities.put(amenity.getAmenityId(), amenity);
    }

    public void removeAmenity(int amenityId) {
        amenities.remove(amenityId);
    }

    public Map<Integer, Amenity> getAllAmenities() {
        return amenities;
    }
}
