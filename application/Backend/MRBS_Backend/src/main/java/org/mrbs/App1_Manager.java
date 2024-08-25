package org.mrbs;

import org.mrbs.beanfactory.BeanFactory;
import org.mrbs.controller.ManagerController;
import org.mrbs.entity.Amenity;
import org.mrbs.entity.User;
import org.mrbs.entity.UserRole;
import org.mrbs.service.impl.AmenityService;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class App1_Manager {
    public static void main(String[] args) {
//        ManagerController managerController = new ManagerController();
        AmenityService amenityService = new AmenityService();

        Scanner sc = new Scanner(System.in);
        BeanFactory factory = new BeanFactory();
        ManagerController managerController = factory.getManagerController();
//        User u1 = new User(99, "Pratyush", "pratyush.bajaj123@gmail.com", 9897476644L, UserRole.ADMIN);
        User u = new User(20, "Pratyush", "pratyush.bajaj123@gmail.com", 9897476644L, UserRole.MANAGER,2000);
//        managerController.addManag(u);
        // Example of viewing all managers
        managerController.viewAllManagers();
//        System.out.println("All Managers:");
//        for (User manager : managers) {
//            System.out.println(manager.getUserName() + " (ID: " + manager.getUserId() + ")");
//        }
        managerController.resetManagerCredits(u.getUserId());
        // Example of viewing a specific manager's details
        int managerId = 20; // Replace with a valid manager ID
        User manager = managerController.viewManagerById(managerId);
        if (manager != null) {
            System.out.println("\nManager Details:");
            System.out.println("Name: " + manager.getUserName());
            System.out.println("Email: " + manager.getUserEmail());
            System.out.println("Credits: " + manager.getCredits());
        } else {
            System.out.println("Manager not found with ID: " + managerId);
        }

        // Example of booking a meeting room
        int roomCost = 100; // Example cost
        String roomId = "R123"; // Example room ID
        LocalDateTime startTime = LocalDateTime.of(2024, 8, 25, 10, 0); // Example start time
        LocalDateTime endTime = LocalDateTime.of(2024, 8, 25, 12, 0); // Example end time
        Set<Integer> amenityIds = new HashSet<>();
        amenityIds.add(1); // Projector
        amenityIds.add(2); // WiFi Connection

        boolean bookingSuccess = managerController.bookRoom(u, roomCost, roomId, startTime, endTime, amenityIds);
        if (bookingSuccess) {
            System.out.println("\nMeeting room booked successfully.");
        } else {
            System.out.println("\nFailed to book the meeting room.");
        }

        // Example of viewing all amenities
        System.out.println("\nAll Available Amenities:");
//        for (Amenity amenity : amenityService.getAllAmenities().values()) {
//            System.out.println("Amenity: " + amenity.getAmenityName() + ", Cost: " + amenity.getAmenityCost());
//        }

        // Example of adding a new amenity
        Amenity newAmenity = new Amenity("Sound System", 20, 8);
        amenityService.addAmenity(newAmenity);
        System.out.println("\nAdded new amenity: " + newAmenity.getAmenityName());

        // Display updated list of amenities
//        System.out.println("\nUpdated Amenities List:");
//        for (Amenity amenity : amenityService.getAllAmenities().values()) {
//            System.out.println("Amenity: " + amenity.getAmenityName() + ", Cost: " + amenity.getAmenityCost());
//        }
    }
}
