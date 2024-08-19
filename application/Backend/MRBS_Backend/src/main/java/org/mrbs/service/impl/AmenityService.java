package org.mrbs.service.impl;

import org.mrbs.entity.Amenity;
import org.mrbs.service.intf.AmenityServiceIntf;

import java.util.HashMap;
import java.util.Map;

public class AmenityService implements AmenityServiceIntf {

    public static Map<Integer, Amenity> amenities;

    public AmenityService() {
        amenities = new HashMap<>();
        initializeAmenities(); // Initialize with predefined amenities if needed
    }

     public void initializeAmenities() {
        // Add predefined amenities here, for example:
        amenities.put(2, new Amenity("WiFi Connection", 10, 2));
        amenities.put(1, new Amenity("Projector", 5, 1));
         amenities.put(3, new Amenity("Conference Call Facility", 15, 3));
         amenities.put(4, new Amenity("Whiteboard", 5, 4));
         amenities.put(5,new Amenity("Water Dispenser",5,5));
         amenities.put(6,new Amenity("TV",10,6));
         amenities.put(7,new Amenity("Coffee Machine",10,7));
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
