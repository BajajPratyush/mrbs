package org.mrbs.controller;


import org.mrbs.entity.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.mrbs.entity.UserRole;
import org.mrbs.service.intf.ManagerServiceIntf;

public class ManagerController {
    private ManagerServiceIntf service;


    public ManagerController(ManagerServiceIntf service) {
        this.service = service;
    }

    public void addManag(User user)
    {
        service.addMan(user);
    }

    public boolean bookRoom(User u, int roomCost, String roomId, LocalDateTime startTime, LocalDateTime endTime, Set<Integer> amenityIds) {
        if (u.getUserRole() != UserRole.MANAGER) {
            System.out.println("Unauthorized Access");
            System.exit(0);
        }

        try {
            boolean success = service.bookRoom(u.getUserId(), roomCost, roomId, startTime, endTime, amenityIds);
            if (success) {
                System.out.println("Room booked successfully");
            } else {
                System.out.println("Failed to book the room. Please try again.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while booking the room: " + e.getMessage());
        }
        return false;
    }

    public void viewAllManagers() {

        List<User> managers = service.viewAllManagers();
        System.out.println("Displaying All Managers:");
        for (User manager : managers) {
            System.out.println("Manager ID: " + manager.getUserId() + ", Name: " + manager.getUserName() +
                    ", Email: " + manager.getUserEmail() + ", Credits: " + manager.getCredits());
        }
    }

    public User viewManagerById( int managerId) {
        User manager = null;
        try {
            manager = service.viewManagerById(managerId);
            if (manager != null) {
                System.out.println("Manager Details:");
                System.out.println("ID: " + manager.getUserId());
                System.out.println("Name: " + manager.getUserName());
                System.out.println("Email: " + manager.getUserEmail());
                System.out.println("Credits: " + manager.getCredits());
            } else {
                System.out.println("Manager not found with ID: " + managerId);
            }
        } catch (Exception e) {
            System.out.println("An error occurred while retrieving manager details: " + e.getMessage());
        }
        return manager;
    }

    public void resetManagerCredits(int managerId) {
        try {
            service.resetManagerCredits(managerId);
            System.out.println("Manager credits reset successfully.");
        } catch (Exception e) {
            System.out.println("An error occurred while resetting credits: " + e.getMessage());
        }
    }
}

